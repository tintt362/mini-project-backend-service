package com.trongtin.backend_service.service.impl;

import com.trongtin.backend_service.dto.request.UserCreationRequest;
import com.trongtin.backend_service.dto.request.UserPasswordRequest;
import com.trongtin.backend_service.dto.request.UserUpdateRequest;
import com.trongtin.backend_service.dto.response.UserPageResponse;
import com.trongtin.backend_service.dto.response.UserResponse;
import com.trongtin.backend_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserPageResponse findAll(String keyword, String sort, int page, int size) {
        return null;
    }

    @Override
    public UserResponse findById(Long id) {
        return null;
    }

    @Override
    public UserResponse findByUsername(String username) {
        return null;
    }

    @Override
    public UserResponse findByEmail(String email) {
        return null;
    }

    @Override
    public long save(UserCreationRequest req) {
        log.info("Save user: {}",req.toString());
        return 0;
    }

    @Override
    public void update(UserUpdateRequest req) {

    }

    @Override
    public void changePassword(UserPasswordRequest req) {

    }

    @Override
    public void delete(Long id) {

    }
}
