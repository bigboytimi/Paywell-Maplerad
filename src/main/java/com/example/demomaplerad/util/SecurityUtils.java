package com.example.demomaplerad.util;

import com.example.demomaplerad.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

public class SecurityUtils {

    public static String getUserEmail(){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getEmail();
    }
}
