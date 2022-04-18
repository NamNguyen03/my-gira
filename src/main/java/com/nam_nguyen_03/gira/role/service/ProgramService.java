package com.nam_nguyen_03.gira.role.service;

import com.nam_nguyen_03.gira.common.model.PageRequestModel;
import com.nam_nguyen_03.gira.common.model.PageResponseModel;
import com.nam_nguyen_03.gira.role.dto.ProgramDTO;
import com.nam_nguyen_03.gira.role.dto.ProgramResponseDTO;

public interface ProgramService {

    PageResponseModel<ProgramResponseDTO> search(PageRequestModel pageRequestModel);

    ProgramResponseDTO save(ProgramDTO program);

}
