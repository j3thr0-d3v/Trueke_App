package com.j3thr0.trueke.truekeapi.service;

import com.j3thr0.trueke.truekeapi.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (authService.findByUsername(username).isPresent())
            return authService.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("No user with surname: " + username));
        else
            return authService.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("No user with surname: " + username));
    }
}
