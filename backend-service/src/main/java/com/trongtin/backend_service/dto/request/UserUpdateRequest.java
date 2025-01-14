package com.trongtin.backend_service.dto.request;

import com.trongtin.backend_service.common.Gender;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class UserUpdateRequest implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date birthday;
    private String username;
    private String email;
    private String phone;
    private List<AddressRequest> addresses;
}