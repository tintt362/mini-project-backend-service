package com.trongtin.backend_service.service;

import com.trongtin.backend_service.dto.request.UserCreationRequest;
import com.trongtin.backend_service.dto.request.UserPasswordRequest;
import com.trongtin.backend_service.dto.request.UserUpdateRequest;
import com.trongtin.backend_service.dto.response.UserPageResponse;
import com.trongtin.backend_service.dto.response.UserResponse;

public interface UserService {

    UserPageResponse findAll(String keyword, String sort, int page, int size);

    UserResponse findById(Long id);

    UserResponse findByUsername(String username);

    UserResponse findByEmail(String email);

    long save(UserCreationRequest req);

    void update(UserUpdateRequest req);

    void changePassword(UserPasswordRequest req);

    void delete(Long id);
}