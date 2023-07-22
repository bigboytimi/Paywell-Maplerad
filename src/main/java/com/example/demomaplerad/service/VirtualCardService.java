package com.example.demomaplerad.service;

import com.example.demomaplerad.dto.request.CardFundRequest;
import com.example.demomaplerad.dto.request.VirtualCardRequest;
import com.example.demomaplerad.dto.response.CardStatusResponse;
import com.example.demomaplerad.dto.response.CardFundResponse;
import com.example.demomaplerad.dto.response.VirtualCardResponse;
import org.springframework.stereotype.Service;

@Service
public interface VirtualCardService {

    VirtualCardResponse createCardRequest(VirtualCardRequest request);

    CardFundResponse fundCard(Long cardId, CardFundRequest request);

    CardStatusResponse freezeCardReq(Long cardId);
}
