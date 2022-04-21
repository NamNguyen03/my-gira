package com.nam_nguyen_03.gira.role.controller;

import javax.validation.Valid;

import com.nam_nguyen_03.gira.common.model.PageRequestModel;
import com.nam_nguyen_03.gira.common.model.PageResponseModel;
import com.nam_nguyen_03.gira.common.util.ResponseHelper;
import com.nam_nguyen_03.gira.role.dto.ProgramDTO;
import com.nam_nguyen_03.gira.role.dto.ProgramResponseDTO;
import com.nam_nguyen_03.gira.role.dto.UpdateProgramDTO;
import com.nam_nguyen_03.gira.role.service.ProgramService;

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
@RequestMapping("api/v1/programs")
public class ProgramController {

    @Autowired
    private ProgramService service;

    @PostMapping
    public Object addProgram(@Valid @RequestBody ProgramDTO program, BindingResult result){
        if(result.hasErrors()) {
			return ResponseHelper.getResponse(result, HttpStatus.BAD_REQUEST, true);
		}

        ProgramResponseDTO rp  = service.save(program);

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
        
        PageResponseModel<ProgramResponseDTO> rp = service.search(new PageRequestModel(
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
    public Object deleteProgram(@PathVariable("id") String id){

        service.deleteById(id);

        return ResponseHelper.getResponse("", HttpStatus.OK, false);
    }

    @PutMapping("{id}")
    public Object updateProgram(@PathVariable("id") String id, 
        @RequestBody UpdateProgramDTO program, 
        BindingResult result){

        
        
        ProgramResponseDTO rp  = service.update(id, program);

        return ResponseHelper.getResponse(rp, HttpStatus.OK, false);
    }
}
