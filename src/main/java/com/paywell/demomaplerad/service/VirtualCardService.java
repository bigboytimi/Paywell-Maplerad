package com.paywell.demomaplerad.service;

import com.paywell.demomaplerad.dto.request.CardFundRequest;
import com.paywell.demomaplerad.dto.request.VirtualCardRequest;
import com.paywell.demomaplerad.dto.response.CardResponse;
import com.paywell.demomaplerad.dto.response.CardStatusResponse;
import com.paywell.demomaplerad.dto.response.CardFundResponse;
import com.paywell.demomaplerad.dto.response.VirtualCardResponse;
import org.springframework.stereotype.Service;

@Service
public interface VirtualCardService {

    VirtualCardResponse createCardRequest(VirtualCardRequest request);

    CardFundResponse fundCard(String cardId, CardFundRequest request);

    CardStatusResponse freezeCardReq(String cardId);

    CardStatusResponse unfreezeCardReq(String cardId);
    CardResponse getCard(String reference);
}
