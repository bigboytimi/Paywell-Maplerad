package com.example.demomaplerad.service.implementation;

import com.example.demomaplerad.dto.request.dtos.AddressDTO;
import com.example.demomaplerad.dto.request.dtos.PhoneDTO;
import com.example.demomaplerad.dto.request.SignupRequest;
import com.example.demomaplerad.dto.response.SignupResponse;
import com.example.demomaplerad.model.Address;
import com.example.demomaplerad.model.Customer;
import com.example.demomaplerad.model.Phone;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperService {

    private final PasswordEncoder encoder;

    public static Phone mapPhoneDtoToPhone(PhoneDTO phoneRequest){
        return Phone.builder()
                .phone_number(phoneRequest.getPhone_number())
                .phone_country_code(phoneRequest.getPhone_country_code())
                .build();
    }

    public static Address mapAddressDtoToAddress(AddressDTO addressRequest){
        return Address.builder()
                .street(addressRequest.getStreet())
                .state(addressRequest.getState())
                .city(addressRequest.getCity())
                .postal_code(addressRequest.getPostal_code())
                .country(addressRequest.getCountry())
                .build();
    }

    public static Customer mapDtoToCustomer(SignupRequest request){
        return Customer.builder()
                .email(request.getEmail())
                .first_name(request.getFirstName())
                .middle_name(request.getMiddleName())
                .last_name(request.getLastName())
                .dob(request.getDob())
                .identification_number(request.getIdentification_number())
                .phone(mapPhoneDtoToPhone(request.getPhoneDetails()))
                .address(mapAddressDtoToAddress(request.getAddressDetails()))
                .build();

    }

    public static SignupResponse mapCustomerToResponse(Customer customer){
        return SignupResponse.builder()
                .id(customer.getCustomer_id())
                .firstName(customer.getFirst_name())
                .lastName(customer.getLast_name())
                .email(customer.getEmail())
                .message("Customer entity successfully saved")
                .build();
    }

}
