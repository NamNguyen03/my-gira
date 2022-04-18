package com.nam_nguyen_03.gira.role.controller;

import javax.validation.Valid;

import com.nam_nguyen_03.gira.common.model.PageRequestModel;
import com.nam_nguyen_03.gira.common.model.PageResponseModel;
import com.nam_nguyen_03.gira.common.util.ResponseHelper;
import com.nam_nguyen_03.gira.role.dto.GroupDTO;
import com.nam_nguyen_03.gira.role.dto.GroupResponseDTO;
import com.nam_nguyen_03.gira.role.service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/groups")
public class GroupController {
 
    @Autowired
    private GroupService service;

    @PostMapping
    public Object addRole(@Valid @RequestBody GroupDTO group, BindingResult result){
        
        if(result.hasErrors()) {
			return ResponseHelper.getResponse(result, HttpStatus.BAD_REQUEST, true);
		}

        GroupResponseDTO rp  = service.save(group);

        return ResponseHelper.getResponse(rp, HttpStatus.OK, false);
    }

    @GetMapping
    public Object search(@RequestParam(value = "pageCurrent", defaultValue = "1") int pageCurrent,
        @RequestParam(value = "itemPerPage", defaultValue = "10") int itemPerPage,
        @RequestParam(value = "fieldNameSort", required = false) String fieldNameSort,
        @RequestParam(value = "isIncrementSort", defaultValue = "true") boolean isIncrementSort,
        @RequestParam(value = "fieldNameSearch", required = false) String fieldNameSearch,
        @RequestParam(value = "valueFieldNameSearch", required = false) String valueFieldNameSearch
        ){
        
        PageResponseModel<GroupResponseDTO> rp = service.search(new PageRequestModel(
            pageCurrent,
            itemPerPage,
            fieldNameSort,
            isIncrementSort,
            fieldNameSearch,
            valueFieldNameSearch
        ));

        return ResponseHelper.getResponse(rp, HttpStatus.OK, false);
    }

    @PostMapping("{idGroup}/{idRole}")
    public Object insertRole(@PathVariable("idGroup") String idGroup, @PathVariable("idRole") String idRole){
        
        GroupResponseDTO rp = service.insertRole(idGroup, idRole);
        
        return ResponseHelper.getResponse(rp, HttpStatus.OK, false);
    }
}
