package com.nam_nguyen_03.gira.role.service;


import com.nam_nguyen_03.gira.common.model.PageRequestModel;
import com.nam_nguyen_03.gira.common.model.PageResponseModel;
import com.nam_nguyen_03.gira.role.dto.ProgramDTO;
import com.nam_nguyen_03.gira.role.dto.ProgramResponseDTO;
import com.nam_nguyen_03.gira.role.dto.UpdateProgramDTO;

public interface ProgramService {

    PageResponseModel<ProgramResponseDTO> search(PageRequestModel pageRequestModel);

    ProgramResponseDTO save(ProgramDTO program);

    void deleteById(String id);

    ProgramResponseDTO update(String id, UpdateProgramDTO program);

}
