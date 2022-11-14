package com.obinna.scorecardapi.utility;

import com.obinna.scorecardapi.enums.Gender;
import com.obinna.scorecardapi.enums.Role;
import com.obinna.scorecardapi.model.SuperAdmin;
import com.obinna.scorecardapi.model.User;
import com.obinna.scorecardapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@RequiredArgsConstructor
public class DataLoader {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${admin.password:password}")
    private String adminPassword;
    @Value("${super.email:email}")
    private String adminEmail;

    @Bean
    public CommandLineRunner preloadAdmin() {
        User user = userRepository.findFirstByRole(Role.SUPER_ADMIN).orElse(null);
        if (user == null) {
            return args -> {
                SuperAdmin user1 = new SuperAdmin("Chika", "Nwobi", Gender.MALE, adminEmail, Role.SUPER_ADMIN, passwordEncoder.encode(adminPassword),true);
                userRepository.save(user1);
            };
        }
        return null;
    }
}