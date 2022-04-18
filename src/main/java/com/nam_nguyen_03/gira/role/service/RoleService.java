package com.nam_nguyen_03.gira.role.service;


import com.nam_nguyen_03.gira.common.model.PageRequestModel;
import com.nam_nguyen_03.gira.common.model.PageResponseModel;
import com.nam_nguyen_03.gira.role.dto.RoleDTO;
import com.nam_nguyen_03.gira.role.dto.RoleResponseDTO;

public interface RoleService {

    RoleResponseDTO save(RoleDTO role);

    PageResponseModel<RoleResponseDTO> search(PageRequestModel pageRequestModel);

    RoleResponseDTO insertProgram(String idRole, String idProgram);

}
