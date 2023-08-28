package com.paywell.demomaplerad.controllers;

import com.paywell.demomaplerad.dto.request.*;
import com.paywell.demomaplerad.dto.response.SignupResponse;
import com.paywell.demomaplerad.dto.response.GlobalResponse;
import com.paywell.demomaplerad.exceptions.EmailExistsException;
import com.paywell.demomaplerad.service.CustomerEnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class CustomerEnrollmentController {

    private final CustomerEnrollmentService customerEnrollmentService;


    @PostMapping("/register-customer")
    public ResponseEntity<SignupResponse> registerUser(@Valid @RequestBody SignupRequest request) throws EmailExistsException {
        SignupResponse response = customerEnrollmentService.registerNewCustomer(request);
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/upgrade-tier1")
    public ResponseEntity<GlobalResponse<String>> upgradeCustomerToTierOne(@RequestBody UpgradeCustomerTierOneRequest customerTierOneRequest) throws EmailExistsException {
        GlobalResponse<String> response = new GlobalResponse<>(customerEnrollmentService.upgradeToTierOne(customerTierOneRequest));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/upgrade-tier2")
    public ResponseEntity<GlobalResponse<String>> upgradeCustomerToTierTwo(@RequestBody UpgradeCustomerTierTwoRequest customerTierTwoRequest){
        GlobalResponse<String> response = new GlobalResponse<>(customerEnrollmentService.upgradeToTierTwo(customerTierTwoRequest));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/enroll-full")
    public ResponseEntity<GlobalResponse<String>> enrollCustomer(@RequestBody FullCustomerEnrollRequest request){
        GlobalResponse<String> response = new GlobalResponse<>(customerEnrollmentService.enrollCustomer(request));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
