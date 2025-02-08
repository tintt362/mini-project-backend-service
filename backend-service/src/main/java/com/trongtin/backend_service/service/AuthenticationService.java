package com.trongtin.backend_service.service;


import com.trongtin.backend_service.dto.request.SignInRequest;
import com.trongtin.backend_service.dto.response.TokenResponse;

public interface AuthenticationService {

    TokenResponse getAccessToken(SignInRequest request);

    TokenResponse getRefreshToken(String request);
}