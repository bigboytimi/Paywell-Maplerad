package com.paywell.demomaplerad.service;

import com.paywell.demomaplerad.dto.request.FullCustomerEnrollRequest;
import com.paywell.demomaplerad.dto.request.SignupRequest;
import com.paywell.demomaplerad.dto.request.UpgradeCustomerTierOneRequest;
import com.paywell.demomaplerad.dto.request.UpgradeCustomerTierTwoRequest;
import com.paywell.demomaplerad.dto.response.SignupResponse;
import com.paywell.demomaplerad.exceptions.EmailExistsException;

public interface CustomerEnrollmentService {

    SignupResponse registerNewCustomer(SignupRequest request) throws EmailExistsException;

    String upgradeToTierOne(UpgradeCustomerTierOneRequest customerTierOneRequest) throws EmailExistsException;

    String upgradeToTierTwo(UpgradeCustomerTierTwoRequest customerTierTwoRequest);

    String enrollCustomer(FullCustomerEnrollRequest request);
}
