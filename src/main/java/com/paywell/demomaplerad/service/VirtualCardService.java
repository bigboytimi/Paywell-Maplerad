package com.paywell.demomaplerad.service;

import com.paywell.demomaplerad.dto.request.CardFundRequest;
import com.paywell.demomaplerad.dto.request.VirtualCardRequest;
import com.paywell.demomaplerad.dto.response.CardStatusResponse;
import com.paywell.demomaplerad.dto.response.CardFundResponse;
import com.paywell.demomaplerad.dto.response.VirtualCardResponse;
import org.springframework.stereotype.Service;

@Service
public interface VirtualCardService {

    VirtualCardResponse createCardRequest(VirtualCardRequest request);

    CardFundResponse fundCard(Long cardId, CardFundRequest request);

    CardStatusResponse freezeCardReq(Long cardId);

    CardStatusResponse unfreezeCardReq(Long cardId);
}
