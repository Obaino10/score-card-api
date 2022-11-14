package com.obinna.scorecardapi.configuration.security;

import com.obinna.scorecardapi.exception.AccountNotActiveException;
import com.obinna.scorecardapi.model.CustomUserDetail;
import com.obinna.scorecardapi.model.User;
import com.obinna.scorecardapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(
                email + " was not found"));
       if(user.getIsAccountActive()) {
           return new CustomUserDetail(user);
       }else
           throw new AccountNotActiveException("User account is not active");

    }
}


