package com.nam_nguyen_03.gira.user.controller;

import javax.validation.Valid;

import com.nam_nguyen_03.gira.common.model.PageRequestModel;
import com.nam_nguyen_03.gira.common.model.PageResponseModel;
import com.nam_nguyen_03.gira.common.util.ResponseHelper;
import com.nam_nguyen_03.gira.security.authorization.GiraPermission;
import com.nam_nguyen_03.gira.user.dto.UserResponseDTO;
import com.nam_nguyen_03.gira.user.dto.UserUpdateDTO;
import com.nam_nguyen_03.gira.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GiraPermission("updateUser")
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

    @GiraPermission("getByIdUser")
    @GetMapping("{id}")
    public Object getUserById(@PathVariable("id") String id){
        
        UserResponseDTO rp = userService.getUserResponseById(id);

        return ResponseHelper.getResponse(rp, HttpStatus.OK, false);
    }

    @GiraPermission("searchUser")
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

    @GiraPermission("deleteUser")
    @DeleteMapping("{id}")
    public Object delete(@PathVariable("id") String id){

        userService.deleteById(id);

        return ResponseHelper.getResponse("", HttpStatus.OK, false);
    }

    @PostMapping("{idUser}/{idGroup}")
    public Object insertRole(@PathVariable("idUser") String idUser, @PathVariable("idGroup") String idGroup){
        UserResponseDTO rp = userService.addGroup(idUser, idGroup);

        return ResponseHelper.getResponse(rp, HttpStatus.OK, false);
    }

    @GetMapping("me")
    public Object getMyProfile(){

        UserResponseDTO rp = userService.getMyProfile();

        return ResponseHelper.getResponse(rp, HttpStatus.OK, false);
    }

    @GiraPermission("updateUser")
    @PutMapping("update-status/{id}")
    public Object updateStatus(@RequestBody String status, @PathVariable("id") String id){

        UserResponseDTO rp = userService.updateStatus(id, status);

        return ResponseHelper.getResponse(rp, HttpStatus.OK, false);
    }

    @PutMapping(value="me/update-password")
    public Object updateMyPassword(@RequestBody String password){
        
        userService.updateMyPassword(password);

        return ResponseHelper.getResponse("", HttpStatus.OK, false);
    }
    
    @GiraPermission("updatePasswordUser")
    @PutMapping("update-password/{id}")
    public Object UpdatePassword(@RequestBody String password, @PathVariable("id") String id){
        
        userService.updatePassword(id, password);

        return ResponseHelper.getResponse("", HttpStatus.OK, false);
    }
}
