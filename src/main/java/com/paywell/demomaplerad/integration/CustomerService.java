package com.paywell.demomaplerad.integration;

import com.paywell.demomaplerad.dto.request.FullCustomerEnrollRequest;
import com.paywell.demomaplerad.dto.request.UpgradeCustomerTierOneRequest;
import com.paywell.demomaplerad.dto.request.UpgradeCustomerTierTwoRequest;
import com.paywell.demomaplerad.dto.response.TierOneUpgradeResponse;
import com.paywell.demomaplerad.dto.response.TierTwoUpgradeResponse;
import com.paywell.demomaplerad.exceptions.EmailExistsException;
import com.paywell.demomaplerad.integration.payload.requests.Registration;
import com.paywell.demomaplerad.integration.payload.response.RegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {


    public RegistrationResponse registerUser(Registration registration) throws EmailExistsException;

    TierOneUpgradeResponse upgradeToTierOne(UpgradeCustomerTierOneRequest customerTierOneRequest);

    TierTwoUpgradeResponse upgradeToTierTwo(UpgradeCustomerTierTwoRequest request);

    TierTwoUpgradeResponse enrollCustomer(FullCustomerEnrollRequest request);
}
