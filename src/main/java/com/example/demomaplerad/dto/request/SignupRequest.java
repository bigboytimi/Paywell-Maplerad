package com.example.demomaplerad.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

public class SignupRequest {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String password;
    private String identification_number;
    private String dob;
    private Set<String> role;
}
