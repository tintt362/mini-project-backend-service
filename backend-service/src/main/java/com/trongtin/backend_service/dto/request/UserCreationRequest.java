package com.trongtin.backend_service.dto.request;

import com.trongtin.backend_service.common.Gender;
import com.trongtin.backend_service.common.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@ToString
@Setter
@Data
public class UserCreationRequest implements Serializable {
    @NotBlank(message = "firstName must be not blank")
    private String firstName;

    @NotBlank(message = "firstName must be not blank")
    private String lastName;
    private Gender gender;
    private Date birthday;
    private String username;

    @Email(message = "Email invalid")
    private String email;
    private String phone;
    private UserType type;
    private List<AddressRequest> addresses; // home,office
}