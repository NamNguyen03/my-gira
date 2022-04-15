package com.nam_nguyen_03.gira.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.UUID;

import com.nam_nguyen_03.gira.common.model.PageRequestModel;
import com.nam_nguyen_03.gira.common.model.PageResponseModel;
import com.nam_nguyen_03.gira.user.dto.UserResponseDTO;
import com.nam_nguyen_03.gira.user.model.GiraUser;
import com.nam_nguyen_03.gira.user.model.UserStatus;
import com.nam_nguyen_03.gira.user.repository.GiraUserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


@SpringBootTest
public class UserServiceIntegrationTest {
    
    @Mock
    private GiraUserRepository repository;

    @InjectMocks
    private UserService userService = new UserServiceImpl();


    private final Page<GiraUser> pageResponse = Mockito.mock(Page.class);

    @BeforeEach
    public void setup(){

        GiraUser user1 = GiraUser.builder()
            .username("nanTest")
            .id(UUID.randomUUID())
            .password("password")
            .displayName("user1")
            .email("user1Test@gmail.com")
            .status(UserStatus.ACTIVE)
            .build();

        GiraUser user2 = GiraUser.builder()
            .username("toanTest")
            .id(UUID.randomUUID())
            .displayName("user2")
            .password("password")
            .email("toanTest@gmail.com")
            .status(UserStatus.PERMANENT_BLOCKED)
            .build();

        GiraUser user3 = GiraUser.builder()
            .username("hanTest")
            .id(UUID.randomUUID())
            .password("password")
            .displayName("user3")
            .email("user3Test@gmail.com")
            .status(UserStatus.ACTIVE)
            .build();

        GiraUser user4 = GiraUser.builder()
            .username("phanTest")
            .id(UUID.randomUUID())
            .password("password")
            .displayName("phan")
            .email("user4Test@gmail.com")
            .status(UserStatus.ACTIVE)
            .build();
        
        when(repository.searchByUsername("Test", PageRequest.of(2, 10))).thenReturn(pageResponse);
        when(pageResponse.getContent()).thenReturn(Arrays.asList(user1, user2, user3, user4));
        when(pageResponse.getTotalPages()).thenReturn(100);
        when(pageResponse.getNumber()).thenReturn(2);
    }

    @Test
    public void whenExistsUserIsUsedFindAll_thenReturnListUser(){
        
        PageRequestModel rq = new PageRequestModel(3,10,"",false,"username","Test");

        PageResponseModel<UserResponseDTO> users = userService.search(rq);

        assertEquals(4 , users.getItems().size());
        assertEquals(100, users.getTotalPage());
    }

}
