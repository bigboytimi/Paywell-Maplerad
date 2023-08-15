package com.paywell.demomaplerad.data_seeder;

import com.paywell.demomaplerad.model.enums.ERole;
import com.paywell.demomaplerad.model.Role;
import com.paywell.demomaplerad.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Role adminRole = new Role(ERole.ROLE_ADMIN);
            Role userRole = new Role(ERole.ROLE_USER);

            roleRepository.save(adminRole);
            roleRepository.save(userRole);
        }
    }
}
