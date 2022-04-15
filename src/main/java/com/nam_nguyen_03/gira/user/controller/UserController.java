package com.nam_nguyen_03.gira.user.controller;

import javax.validation.Valid;

import com.nam_nguyen_03.gira.common.model.PageRequestModel;
import com.nam_nguyen_03.gira.common.model.PageResponseModel;
import com.nam_nguyen_03.gira.common.util.ResponseHelper;
import com.nam_nguyen_03.gira.user.dto.UserResponseDTO;
import com.nam_nguyen_03.gira.user.dto.UserUpdateDTO;
import com.nam_nguyen_03.gira.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    
    @Autowired 
    private UserService userService;

    @PutMapping("me")
    public Object updateMyProfile(@Valid @RequestBody UserUpdateDTO rq, BindingResult result){
        if(result.hasErrors()) {
			return ResponseHelper.getResponse(result, HttpStatus.BAD_REQUEST, true);
		}

        UserResponseDTO rp = userService.updateMyProfile(rq);

        return ResponseHelper.getResponse(rp, HttpStatus.OK, false);
    }

    @PutMapping("{id}")
    public Object updateUser(
        @PathVariable("id") String id,    
        @Valid @RequestBody UserUpdateDTO rq, 
        BindingResult result){
        
        if(result.hasErrors()) {
			return ResponseHelper.getResponse(result, HttpStatus.BAD_REQUEST, true);
		}

        UserResponseDTO rp = userService.updateUser(rq, id);

        return ResponseHelper.getResponse(rp, HttpStatus.OK, false);
    }

    @GetMapping()
    public Object search(@RequestParam(value = "pageCurrent", defaultValue = "1") int pageCurrent,
        @RequestParam(value = "itemPerPage", defaultValue = "10") int itemPerPage,
        @RequestParam(value = "fieldNameSort", required = false) String fieldNameSort,
        @RequestParam(value = "isIncrementSort", defaultValue = "true") boolean isIncrementSort,
        @RequestParam(value = "fieldNameSearch", required = false) String fieldNameSearch,
        @RequestParam(value = "valueFieldNameSearch", required = false) String valueFieldNameSearch
        ){
        
        PageResponseModel<UserResponseDTO> rp = userService.search(new PageRequestModel(
			pageCurrent,
			itemPerPage,
			fieldNameSort,
			isIncrementSort,
			fieldNameSearch,
			valueFieldNameSearch
		));

        return ResponseHelper.getResponse(rp, HttpStatus.OK, false);
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable("id") String id){

        userService.deleteById(id);

        return ResponseHelper.getResponse("", HttpStatus.OK, false);
    }
}
