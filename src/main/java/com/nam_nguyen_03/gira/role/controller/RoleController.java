package com.nam_nguyen_03.gira.role.controller;
import javax.validation.Valid;

import com.nam_nguyen_03.gira.common.model.PageRequestModel;
import com.nam_nguyen_03.gira.common.model.PageResponseModel;
import com.nam_nguyen_03.gira.common.util.ResponseHelper;
import com.nam_nguyen_03.gira.role.dto.RoleDTO;
import com.nam_nguyen_03.gira.role.dto.RoleResponseDTO;
import com.nam_nguyen_03.gira.role.service.RoleService;
import com.nam_nguyen_03.gira.security.authorization.GiraPermission;

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
@RequestMapping("api/v1/roles")
public class RoleController {
    
    @Autowired
    private RoleService service;

    @GiraPermission("addRole")
    @PostMapping
    public Object addRole(@Valid @RequestBody RoleDTO role, BindingResult result){
        
        if(result.hasErrors()) {
			return ResponseHelper.getResponse(result, HttpStatus.BAD_REQUEST, true);
		}

        RoleResponseDTO rp  = service.save(role);

        return ResponseHelper.getResponse(rp, HttpStatus.OK, false);
    }

    @GiraPermission("searchRole")
    @GetMapping
    public Object search(@RequestParam(value = "pageCurrent", defaultValue = "1") int pageCurrent,
        @RequestParam(value = "itemPerPage", defaultValue = "10") int itemPerPage,
        @RequestParam(value = "fieldNameSort", required = false) String fieldNameSort,
        @RequestParam(value = "isIncrementSort", defaultValue = "true") boolean isIncrementSort,
        @RequestParam(value = "fieldNameSearch", required = false) String fieldNameSearch,
        @RequestParam(value = "valueFieldNameSearch", required = false) String valueFieldNameSearch
        ){
        
        PageResponseModel<RoleResponseDTO> rp = service.search(new PageRequestModel(
            pageCurrent,
            itemPerPage,
            fieldNameSort,
            isIncrementSort,
            fieldNameSearch,
            valueFieldNameSearch
        ));

        return ResponseHelper.getResponse(rp, HttpStatus.OK, false);
    }

    @GiraPermission("insertProgramRole")
    @PostMapping("{idRole}/{idProgram}")
    public Object insertProgram(@PathVariable("idRole") String idRole, @PathVariable("idProgram") String idProgram){
        RoleResponseDTO rp = service.insertProgram(idRole, idProgram);
        
        return ResponseHelper.getResponse(rp, HttpStatus.OK, false);
    }
}
