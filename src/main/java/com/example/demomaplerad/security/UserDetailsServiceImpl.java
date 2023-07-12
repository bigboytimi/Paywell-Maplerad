package com.example.demomaplerad.security;

import com.example.demomaplerad.model.Customer;
import com.example.demomaplerad.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
    CustomerRepository customerRepository;
    @Override
    @Transactional
    public UserDetails loadByUsername(String username) throws UsernameNotFoundException {
        Customer user = customerRepository.findByCustomerName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return CustomUserDetails.build(user);
    }
}
