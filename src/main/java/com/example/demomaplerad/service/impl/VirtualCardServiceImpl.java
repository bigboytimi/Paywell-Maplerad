package com.example.demomaplerad.service.impl;

import com.example.demomaplerad.dto.request.CardFundRequest;
import com.example.demomaplerad.dto.request.VirtualCardRequest;
import com.example.demomaplerad.dto.response.CardFundResponse;
import com.example.demomaplerad.dto.response.VirtualCardResponse;
import com.example.demomaplerad.exceptions.*;
import com.example.demomaplerad.integration.impl.MapleradService;
import com.example.demomaplerad.integration.payload.requests.Card;
import com.example.demomaplerad.integration.payload.response.CardResponse;
import com.example.demomaplerad.model.Transaction;
import com.example.demomaplerad.model.User;
import com.example.demomaplerad.model.VirtualCard;
import com.example.demomaplerad.model.Wallet;
import com.example.demomaplerad.model.enums.CardBrand;
import com.example.demomaplerad.model.enums.CardType;
import com.example.demomaplerad.model.enums.Currency;
import com.example.demomaplerad.model.enums.TransactionType;
import com.example.demomaplerad.repository.CustomerRepository;
import com.example.demomaplerad.repository.TransactionRepository;
import com.example.demomaplerad.repository.VirtualCardRepository;
import com.example.demomaplerad.repository.WalletRepository;
import com.example.demomaplerad.security.CustomUserDetails;
import com.example.demomaplerad.service.VirtualCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Override
    public VirtualCardResponse createCardRequest(VirtualCardRequest request) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String authenticatedUserEmail = userDetails.getEmail();

        User user = customerRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(()-> new UserNotFoundException("User not found"));

        if(walletRepository.existsByCustomerAndCurrency(user, request.getCurrency())){
            throw new CardAlreadyExistsException("Card already exists");
        }

        Wallet wallet = walletRepository.findWalletByCustomerAndCurrency(user, request.getCurrency())
                .orElseThrow(()-> new NoWalletFoundException("Customer has no wallet for the specific currency"));

        Card card = Card.builder()
                .name(user.getFirst_name().concat(" ").concat(user.getLast_name()))
                .brand(request.getCardBrand())
                .currency(request.getCurrency().name())
                .amount(new BigDecimal(1000))
                .type(request.getCardType())
                .build();


        CardResponse cardResponse = mapleradService.createCard(card);

        VirtualCard savedVirtualCard = cardRepository.save(VirtualCard.builder()
                .assigned_id(cardResponse.getId())
                .cardName(cardResponse.getName())
                .currency(Currency.valueOf(cardResponse.getCurrency()))
                .cardNumber(cardResponse.getCard_number())
                .cardPin(request.getPin())
                .createdAt(cardResponse.getCreated_at())
                .updatedAt(cardResponse.getUpdated_at())
                .address(cardResponse.getAddress())
                .cvv(cardResponse.getCvv())
                .balance(cardResponse.getBalance())
                .expiry(cardResponse.getExpiry())
                .wallet (wallet)
                .isDisabled(false)
                .type(CardType.valueOf(cardResponse.getType()))
                .issuer(CardBrand.valueOf(cardResponse.getIssuer()))
                .maskedPan(cardResponse.getMasked_pan())
                .user(user)
                .build());

        return VirtualCardResponse.builder()
                .id(savedVirtualCard.getId())
                .assigned_Id(savedVirtualCard.getAssigned_id())
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
    public CardFundResponse fundCard(Long cardId, CardFundRequest request) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        String authUserEmail = userDetails.getEmail();

        User user = customerRepository.findByEmail(authUserEmail).orElseThrow(()-> new UserNotFoundException("User not found"));


        VirtualCard card = cardRepository.findById(cardId).orElseThrow(()-> new WalletNotFoundException("Wallet does not exist"));


        Wallet wallet = card.getWallet();

        if (!card.getUser().equals(user)){
            throw new InvalidCardRequestException("Card belongs to another user");
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
                .assigned_cardId(savedCard.getAssigned_id())
                .balance(savedCard.getBalance())
                .currency(savedCard.getCurrency())
                .isDisabled(false)
                .issuer(savedCard.getIssuer())
                .type(savedCard.getType())
                .build();
    }
}
