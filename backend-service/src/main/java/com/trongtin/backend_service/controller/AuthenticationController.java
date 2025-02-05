package com.trongtin.backend_service.controller;

import com.trongtin.backend_service.dto.request.SignInRequest;
import com.trongtin.backend_service.dto.response.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Controller")
@Slf4j(topic = "AUTHENTICATION-CONTROLLER")
public class AuthenticationController {

    @Operation(summary = "Access token", description = "Get access token and refresh token by username and password")
    @PostMapping("/access-token")
    public TokenResponse accessToken(@RequestBody SignInRequest request) {
        log.info("Access token request");
        return TokenResponse.builder().accessToken("DUMMY-ACCESS-TOKEN").refreshToken("DUMMY-REFRESH-TOKEN").build();
    }

    @Operation(summary = "Refresh token", description = "Get access token by refresh token")
    @PostMapping("/refresh-token")
    public TokenResponse refreshToken(@RequestBody String refreshToken) {
        log.info("Refresh token request");
        return TokenResponse.builder().accessToken("DUMMY-NEW-ACCESS-TOKEN").refreshToken("DUMMY-REFRESH-TOKEN").build();
    }
}