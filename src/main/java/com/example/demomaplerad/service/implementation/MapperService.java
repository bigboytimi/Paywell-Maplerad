package com.example.demomaplerad.service.implementation;

import com.example.demomaplerad.dto.request.AddressDTO;
import com.example.demomaplerad.dto.request.PhoneDTO;
import com.example.demomaplerad.dto.request.SignupRequest;
import com.example.demomaplerad.model.Address;
import com.example.demomaplerad.model.Customer;
import com.example.demomaplerad.model.Phone;
import org.springframework.stereotype.Component;

@Component
public class MapperService {

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
                .password(request.getPassword())
                .identification_number(request.getIdentification_number())
                .phone(mapPhoneDtoToPhone(request.getPhoneDetails()))
                .address(mapAddressDtoToAddress(request.getAddressDetails()))
                .build();

    }

}
