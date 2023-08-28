package com.paywell.demomaplerad.service.impl;

import com.paywell.demomaplerad.dto.request.CardFundRequest;
import com.paywell.demomaplerad.dto.request.VirtualCardRequest;
import com.paywell.demomaplerad.dto.response.*;
import com.paywell.demomaplerad.exceptions.*;
import com.paywell.demomaplerad.integration.impl.MapleradService;
import com.paywell.demomaplerad.integration.payload.response.StatusResponse;
import com.paywell.demomaplerad.integration.payload.requests.Card;
import com.paywell.demomaplerad.model.*;
import com.paywell.demomaplerad.model.enums.CardBrand;
import com.paywell.demomaplerad.model.enums.CardType;
import com.paywell.demomaplerad.model.enums.Currency;
import com.paywell.demomaplerad.model.enums.TransactionType;
import com.paywell.demomaplerad.repository.CustomerRepository;
import com.paywell.demomaplerad.repository.TransactionRepository;
import com.paywell.demomaplerad.repository.VirtualCardRepository;
import com.paywell.demomaplerad.repository.WalletRepository;
import com.paywell.demomaplerad.service.VirtualCardService;
import com.paywell.demomaplerad.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VirtualCardServiceImpl implements VirtualCardService {
    private final CustomerRepository customerRepository;
    private final WalletRepository walletRepository;
    private final VirtualCardRepository cardRepository;
    private final TransactionRepository transactionRepository;
    private final MapleradService mapleradService;

    private final PasswordEncoder encoder;
    @Override
    public VirtualCardResponse createCardRequest(VirtualCardRequest request) {
        /*
        Get User email from Security context and confirm user is registered
         */
        String userEmail = SecurityUtils.getUserEmail();

        User user = customerRepository.findByEmail(userEmail)
                .orElseThrow(()-> new UserNotFoundException("User not found"));

        if(walletRepository.existsByCustomerAndCurrency(user, request.getCurrency())){
            throw new CardAlreadyExistsException("Card already exists");
        }

        Wallet wallet = walletRepository.findWalletByCustomerAndCurrency(user, request.getCurrency())
                .orElseThrow(()-> new NoWalletFoundException("Customer has no wallet for the specific currency"));

        Card card = Card.builder()
                .customer_id(user.getId())
                .brand(request.getCardBrand())
                .auto_approve(true)
                .currency(request.getCurrency().name())
                .amount(new BigDecimal(0))
                .type(request.getCardType())
                .build();


        CardCreationResponse response = mapleradService.createCard(card);

        JSONObject jsonObject = new JSONObject(response);

        String reference = jsonObject.getString("reference");

        /*
        Call Maplerad Service to get the card details
         */

        CardDetailsResponse cardDetails = mapleradService.getCard(reference);

        Address address = Address.builder()
                .street(cardDetails.getData().getAddress().getStreet())
                .city(cardDetails.getData().getAddress().getCity())
                .country(cardDetails.getData().getAddress().getCity())
                .postalCode(cardDetails.getData().getAddress().getPostal_code())
                .state(cardDetails.getData().getAddress().getState())
                .build();


        VirtualCard savedVirtualCard = cardRepository.save(VirtualCard.builder()
                .reference(reference)
                .cardName(cardDetails.getData().getName())
                .currency(Currency.valueOf(cardDetails.getData().getCurrency()))
                .cardNumber(cardDetails.getData().getCard_number())
                .cardPin(encoder.encode(request.getPin()))
                .address(address)
                .cvv(cardDetails.getData().getCvv())
                .balance(cardDetails.getData().getBalance())
                .expiry(cardDetails.getData().getExpiry())
                .wallet (wallet)
                .isDisabled(false)
                .type(CardType.valueOf(cardDetails.getData().getType()))
                .issuer(CardBrand.valueOf(cardDetails.getData().getIssuer()))
                .maskedPan(cardDetails.getData().getMasked_pan())
                .user(user)
                .build());
//
        return VirtualCardResponse.builder()
                .id(savedVirtualCard.getId())
                .reference(savedVirtualCard.getReference())
                .cardOwnerName(savedVirtualCard.getCardName())
                .cardNumber(savedVirtualCard.getCardNumber())
                .cardCvv(savedVirtualCard.getCvv())
                .cardType(savedVirtualCard.getType().name())
                .cardBrand(savedVirtualCard.getIssuer().name())
                .cardBalance(savedVirtualCard.getBalance())
                .status("Card successfully created")
                .build();
    }

    @Override
    public CardFundResponse fundCard(String cardId, CardFundRequest request) {
        String userEmail = SecurityUtils.getUserEmail();


        User user = customerRepository.findByEmail(userEmail).orElseThrow(()-> new UserNotFoundException("User not found"));


        VirtualCard card = cardRepository.findById(cardId).orElseThrow(()-> new NoWalletFoundException("Wallet does not exist"));


        Wallet wallet = card.getWallet();

        if (!card.getUser().equals(user)){
            throw new CardRequestFailedException("Card belongs to another user");
        }

        if (card.isDisabled()){
            throw new DisabledCardException("Disabled: Cannot use this card.");
        }

        card.setBalance(card.getBalance().add(request.getAmount()));
        card.setUpdatedAt(LocalDateTime.now());


        VirtualCard savedCard = cardRepository.save(card);

        wallet.setAvailableBalance(wallet.getAvailableBalance().subtract(request.getAmount()));
        walletRepository.save(wallet);

        Transaction transaction = Transaction.builder()
                .amount(request.getAmount())
                .date(LocalDate.now())
                .card(card)
                .transactionType(TransactionType.WALLET_TO_CARD)
                .build();

        transactionRepository.save(transaction);
        return CardFundResponse.builder()
                .id(savedCard.getId())
                .assigned_cardId(savedCard.getReference())
                .balance(savedCard.getBalance())
                .currency(savedCard.getCurrency())
                .isDisabled(false)
                .issuer(savedCard.getIssuer())
                .type(savedCard.getType())
                .build();
    }

    @Override
    public CardStatusResponse freezeCardReq(String cardId) {
        String userEmail = SecurityUtils.getUserEmail();

        User user = customerRepository.findByEmail(userEmail).orElseThrow(()-> new UserNotFoundException("User not found"));

        VirtualCard card = cardRepository.findById(cardId).orElseThrow(() -> new CardNotFoundException("Card not found"));

        if(!card.getUser().equals(user)){
            throw new UserNotFoundException("Card belongs to another user");
        }

        StatusResponse response = mapleradService.freezeCard(card.getReference());

        if(response.getMessage().equals("Successfully disabled card")){
            card.setDisabled(true);

            VirtualCard updatedVirtualCard = cardRepository.save(card);

            return CardStatusResponse.builder()
                    .status("Card successfully disabled")
                    .currency(updatedVirtualCard.getCurrency())
                    .id(updatedVirtualCard.getId())
                    .isDisabled(updatedVirtualCard.isDisabled())
                    .issuer(updatedVirtualCard.getIssuer())
                    .type(updatedVirtualCard.getType())
                    .build();

        } else {
            throw new CardRequestFailedException("Request to disable card failed");
        }
    }

    @Override
    public CardStatusResponse unfreezeCardReq(String cardId) {
        String userEmail = SecurityUtils.getUserEmail();

        User user = customerRepository.findByEmail(userEmail).orElseThrow(()-> new UserNotFoundException("User not found"));

        VirtualCard card = cardRepository.findById(cardId).orElseThrow(() -> new CardNotFoundException("Card not found"));

        if(!card.getUser().equals(user)){
            throw new UserNotFoundException("Card belongs to another user");
        }

        StatusResponse statusResponse = mapleradService.unfreezeCard(card.getReference());

        if (statusResponse.getMessage().equals("Successfully enabled card")){
            card.setDisabled(true);

            VirtualCard updatedVirtualCard = cardRepository.save(card);


            return CardStatusResponse.builder()
                    .status("Card successfully enabled")
                    .currency(updatedVirtualCard.getCurrency())
                    .id(updatedVirtualCard.getId())
                    .isDisabled(updatedVirtualCard.isDisabled())
                    .issuer(updatedVirtualCard.getIssuer())
                    .type(updatedVirtualCard.getType())
                    .build();
        } else{
            throw new CardRequestFailedException("Request to enable card failed");
        }
    }

    @Override
    public CardResponse getCard(String reference) {
        String userEmail = SecurityUtils.getUserEmail();

        User user = customerRepository.findByEmail(userEmail).orElseThrow(()-> new UserNotFoundException("User not found"));


        CardDetailsResponse cardDetails = mapleradService.getCard(reference);
        VirtualCard card = cardRepository.findById(cardDetails.getData().getId()).orElseThrow(() -> new CardNotFoundException("Card not found"));

        if(!card.getUser().equals(user)){
            throw new UserNotFoundException("Card belongs to another user");
        }

        if (!card.getReference().equals(reference)){
            throw new CardRequestFailedException("This reference is invalid");
        }
        return cardDetails.getData();
    }

}
