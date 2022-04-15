package com.nam_nguyen_03.gira.user.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import com.nam_nguyen_03.gira.user.model.GiraUser;
import com.nam_nguyen_03.gira.user.model.UserStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
public class GiraUserRepositoryIntegrationTest {

    @Autowired
    private GiraUserRepository repository;


    @BeforeEach
    public void setup(){
        repository.deleteAll();
        GiraUser user1 = GiraUser.builder()
            .username("namTest")
            .id(UUID.randomUUID())
            .password("password")
            .displayName("user1")
            .email("bbbTest@gmail.com")
            .status(UserStatus.ACTIVE)
            .build();
        repository.saveAndFlush(user1);

        GiraUser user2 = GiraUser.builder()
            .username("toanTest")
            .id(UUID.randomUUID())
            .displayName("user2")
            .password("password")
            .email("dTest@gmail.com")
            .status(UserStatus.PERMANENT_BLOCKED)
            .build();
        repository.saveAndFlush(user2);

        GiraUser user3 = GiraUser.builder()
            .username("hauTest")
            .id(UUID.randomUUID())
            .password("password")
            .displayName("user3")
            .email("aaaTest@gmail.com")
            .status(UserStatus.ACTIVE)
            .build();
        repository.saveAndFlush(user3);

        GiraUser user4 = GiraUser.builder()
            .username("phanTest")
            .id(UUID.randomUUID())
            .password("password")
            .displayName("phan")
            .email("aTest@gmail.com")
            .status(UserStatus.ACTIVE)
            .build();
        repository.saveAndFlush(user4);
    }

    @Test
    public void whenExistsUserIsUsedFindAll_thenReturnListUser(){
        
        List<GiraUser> users = repository.findAll();

        assertEquals(4 , users.size());
    }

    @Test
    public void existsByUsernameIsUsedSearch_theReturnPageUser(){
        Page<GiraUser> users = repository.searchByUsername("an", PageRequest.of(0, 10, Sort.by("email").ascending()));
        
        // namTest, toanTest, hauTest,              phanTest
        //          dTest@gmail.com                 aTest@gmail.com

        assertEquals(2 , users.getContent().size(), "search may be failed");

        assertEquals("aTest@gmail.com", users.getContent().get(0).getEmail(), "sorted by username may be failed");

        assertEquals("dTest@gmail.com", users.getContent().get(1).getEmail(), "sorted or search by username may be failed");

        for(int i = 0 ; i < 30; i++){
            GiraUser user = GiraUser.builder()
                .username("phanTest" + i)
                .id(UUID.randomUUID())
                .password("password")
                .displayName("phan")
                .email(i+"user4Test@gmail.com")
                .status(UserStatus.ACTIVE)
                .build();
            repository.saveAndFlush(user);
        }
        Page<GiraUser> users2 = repository.searchByUsername("an", PageRequest.of(2, 10));

        assertEquals(10 , users2.getContent().size(), "page able may be failed");
        assertEquals("phanTest18" , users2.getContent().get(0).getUsername(), "page able may be failed");

        
    }
    

    // @Test
    // public void existsByEmailIsUsedSearch_theReturnPageUser(){
    //     repository.deleteAll();
    //     user2.setEmail("123nnn@gmail.com");
    //     user4.setEmail("145nnn@gmail.com");
    //     repository.saveAndFlush(user1);
    //     repository.saveAndFlush(user2); 
    //     repository.saveAndFlush(user3);
    //     repository.saveAndFlush(user4);

    //     //bbbTest@gmail.com,    123nnn@gmail.com,   aaaTest@gmail.com,  145nnn@gmail.com
    // }
}







