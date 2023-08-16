package com.paywell.demomaplerad.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;


@Component
public class AuthorizedIpSecurity {

    private static final String[] ALLOWED_IPS = {
            "54.216.8.72",
            "54.173.54.49",
            "52.215.16.239",
            "52.55.123.25",
            "52.6.93.106",
            "63.33.109.123",
            "44.228.126.217",
            "50.112.21.217",
            "52.24.126.164",
            "54.148.139.208"
    };
    public boolean check(Authentication authentication, HttpServletRequest request){
        WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails) authentication.getPrincipal();
        String userIp = webAuthenticationDetails.getRemoteAddress();


        for (String ip : ALLOWED_IPS){
            if (ip.equals(userIp)){
                return true;
            }
        }
        return false;
    }
}
