package com.paywell.demomaplerad.util;

import com.paywell.demomaplerad.exceptions.RoleNotFoundException;
import com.paywell.demomaplerad.model.Role;
import com.paywell.demomaplerad.model.enums.ERole;
import com.paywell.demomaplerad.repository.RoleRepository;
import com.paywell.demomaplerad.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SecurityUtils {
    private final RoleRepository roleRepository;

    public static String getUserEmail(){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getEmail();
    }


}
