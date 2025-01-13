package com.trongtin.backend_service.dto.request;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserPasswordRequest implements Serializable {
    private Long id;
    private String password;
    private String confirmPassword;
}