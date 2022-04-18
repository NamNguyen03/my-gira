package com.nam_nguyen_03.gira.role.service;

import com.nam_nguyen_03.gira.common.model.PageRequestModel;
import com.nam_nguyen_03.gira.common.model.PageResponseModel;
import com.nam_nguyen_03.gira.role.dto.GroupDTO;
import com.nam_nguyen_03.gira.role.dto.GroupResponseDTO;

public interface GroupService {

    GroupResponseDTO save(GroupDTO group);

    PageResponseModel<GroupResponseDTO> search(PageRequestModel pageRequestModel);

    GroupResponseDTO insertRole(String idGroup, String idRole);

}
