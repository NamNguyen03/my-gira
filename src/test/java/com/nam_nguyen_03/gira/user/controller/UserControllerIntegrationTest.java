package com.nam_nguyen_03.gira.user.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.nam_nguyen_03.gira.common.model.PageRequestModel;
import com.nam_nguyen_03.gira.common.model.PageResponseModel;
import com.nam_nguyen_03.gira.user.service.UserService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
    
    @MockBean
	private UserService service;
	
	@Autowired
	private MockMvc mockMvc;

    @WithMockUser("nam")
    @Test
    public void givenJsonObject_whenExistsUserIsUsedSearchUser_theReturnPageUser() throws Exception{
        when(service.search(new PageRequestModel(1, 10, null, true, null, null))).thenReturn(new PageResponseModel<>(1,10,new ArrayList<>()));
		
        String json = "content\":{\"pageCurrent\":1,\"totalPage\":10,\"items\":[]";

		mockMvc.perform(get("/api/v1/users"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string(containsString(json)));
    }
}
