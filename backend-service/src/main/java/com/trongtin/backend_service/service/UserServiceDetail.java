package com.trongtin.backend_service.service;

import com.trongtin.backend_service.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public record UserServiceDetail(UserRepository userRepository) {

    public UserDetailsService userDetailsService() {
        return userRepository::findByUsername;
    }
}