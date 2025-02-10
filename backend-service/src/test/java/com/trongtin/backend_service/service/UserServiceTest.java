package com.trongtin.backend_service.service;

import com.trongtin.backend_service.common.Gender;
import com.trongtin.backend_service.common.UserStatus;
import com.trongtin.backend_service.common.UserType;
import com.trongtin.backend_service.dto.request.AddressRequest;
import com.trongtin.backend_service.dto.request.UserCreationRequest;
import com.trongtin.backend_service.dto.request.UserUpdateRequest;
import com.trongtin.backend_service.dto.response.UserPageResponse;
import com.trongtin.backend_service.dto.response.UserResponse;
import com.trongtin.backend_service.exception.ResourceNotFoundException;
import com.trongtin.backend_service.model.UserEntity;
import com.trongtin.backend_service.repository.AddressRepository;
import com.trongtin.backend_service.repository.UserRepository;
import com.trongtin.backend_service.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;  // ✅ Cái này mới đúng cho Mockito

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Sử dụng Mockito
class UserServiceTest {
    private static UserService userService;
    private static UserEntity tayJava;
    private static UserEntity johnDoe;
    private @Mock UserRepository userRepository;
    private @Mock AddressRepository addressRepository;
    private @Mock PasswordEncoder passwordEncoder;
 //   private @Mock EmailService emailService;
    @BeforeAll
    static void beforeAll() {
        // Dữ liệu giả lập
        tayJava = new UserEntity();
        tayJava.setId(1L);
        tayJava.setFirstName("Tay");
        tayJava.setLastName("Java");
        tayJava.setGender(Gender.MALE);
        tayJava.setBirthday(new Date());
        tayJava.setEmail("quoctay87@gmail.com");
        tayJava.setPhone("0975118228");
        tayJava.setUsername("tayjava");
        tayJava.setPassword("password");
        tayJava.setType(UserType.USER);
        tayJava.setStatus(UserStatus.ACTIVE);

        johnDoe = new UserEntity();
        johnDoe.setId(2L);
        johnDoe.setFirstName("John");
        johnDoe.setLastName("Doe");
        johnDoe.setGender(Gender.FEMALE);
        johnDoe.setBirthday(new Date());
        johnDoe.setEmail("johndoe@gmail.com");
        johnDoe.setPhone("0123456789");
        johnDoe.setUsername("johndoe");
        johnDoe.setPassword("password");
        johnDoe.setType(UserType.USER);
        johnDoe.setStatus(UserStatus.INACTIVE);
    }

    @BeforeEach
    void beforeEach() {
        userService = new UserServiceImpl(userRepository, addressRepository, passwordEncoder);
    }
    @Test
    void testGetUserList_Success() {
        // Giả lập phương thức search của UserRepository
        Page<UserEntity> userPage = new PageImpl<>(List.of(tayJava, johnDoe));
        when(userRepository.findAll(any(Pageable.class))).thenReturn(userPage);

        // Gọi phương thức cần kiểm tra
        UserPageResponse result = userService.findAll(null, null, 0, 20);

        assertNotNull(result);
        assertEquals(2, result.totalElements);
        //assertEquals("tayjava", result.getUsers().get(0).getUsername());
    }
    @Test
    void testSearchUser_Success() {
        // Giả lập phương thức search của UserRepository
        Page<UserEntity> userPage = new PageImpl<>(List.of(tayJava));
        when(userRepository.searchByKeyword(any(), any(Pageable.class))).thenReturn(userPage);

        // Gọi phương thức cần kiểm tra
        UserPageResponse result = userService.findAll("tay", null, 0, 20);

        assertNotNull(result);
        assertEquals(1, result.totalElements);
       // assertEquals("tayjava", result.getUsers().get(0).getUsername());
    }
    @Test
    void testGetUserList_Empty() {
        // Giả lập hành vi của UserRepository
        Page<UserEntity> userPage = new PageImpl<>(List.of());
        when(userRepository.findAll(any(Pageable.class))).thenReturn(userPage);

        // Gọi phương thức cần kiểm tra
        UserPageResponse result = userService.findAll(null, null, 0, 20);

        assertNotNull(result);
        assertEquals(0, result.getUsers().size());
    }

    @Test
    void testGetUserById_Failure() {
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> userService.findById(10L));
        assertEquals("User not found", thrown.getMessage());
    }

    @Test
    void testSave_Success() {
        // Giả lập hành vi của UserRepository
        when(userRepository.save(any(UserEntity.class))).thenReturn(tayJava);

        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setFirstName("Tay");
        userCreationRequest.setLastName("Java");
        userCreationRequest.setGender(Gender.MALE);
        userCreationRequest.setBirthday(new Date());
        userCreationRequest.setEmail("quoctay87@gmail.com");
        userCreationRequest.setPhone("0975118228");
        userCreationRequest.setUsername("tayjava");

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setApartmentNumber("ApartmentNumber");
        addressRequest.setFloor("Floor");
        addressRequest.setBuilding("Building");
        addressRequest.setStreetNumber("StreetNumber");
        addressRequest.setStreet("Street");
        addressRequest.setCity("City");
        addressRequest.setCountry("Country");
        addressRequest.setAddressType(1);
        userCreationRequest.setAddresses(List.of(addressRequest));

        // Gọi phương thức cần kiểm tra
        long result = userService.save(userCreationRequest);

        // Kiểm tra kết quả
       // assertNotNull(result);
        assertEquals(1L, result);
    }

    @Test
    void testUpdate_Success() {
        Long userId = 2L;

        UserEntity updatedUser = new UserEntity();
        updatedUser.setId(userId);
        updatedUser.setFirstName("Jane");
        updatedUser.setLastName("Smith");
        updatedUser.setGender(Gender.FEMALE);
        updatedUser.setBirthday(new Date());
        updatedUser.setEmail("janesmith@gmail.com");
        updatedUser.setPhone("0123456789");
        updatedUser.setUsername("janesmith");
        updatedUser.setType(UserType.USER);
        updatedUser.setStatus(UserStatus.ACTIVE);

        // Giả lập hành vi của UserRepository
        when(userRepository.findById(userId)).thenReturn(Optional.of(johnDoe));
        when(userRepository.save(any(UserEntity.class))).thenReturn(updatedUser);

        UserUpdateRequest updateRequest = new UserUpdateRequest();
        updateRequest.setId(userId);
        updateRequest.setFirstName("Jane");
        updateRequest.setLastName("Smith");
        updateRequest.setGender(Gender.MALE);
        updateRequest.setBirthday(new Date());
        updateRequest.setEmail("janesmith@gmail.com");
        updateRequest.setPhone("0123456789");
        updateRequest.setUsername("janesmith");

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setApartmentNumber("ApartmentNumber");
        addressRequest.setFloor("Floor");
        addressRequest.setBuilding("Building");
        addressRequest.setStreetNumber("StreetNumber");
        addressRequest.setStreet("Street");
        addressRequest.setCity("City");
        addressRequest.setCountry("Country");
        addressRequest.setAddressType(1);
        updateRequest.setAddresses(List.of(addressRequest));

        // Gọi phương thức cần kiểm tra
        userService.update(updateRequest);

        UserResponse result = userService.findById(userId);

        assertEquals("janesmith", result.getUsername());
        assertEquals("janesmith@gmail.com", result.getEmail());
    }

}