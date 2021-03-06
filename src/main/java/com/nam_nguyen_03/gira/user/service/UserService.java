package com.nam_nguyen_03.gira.user.service;

import com.nam_nguyen_03.gira.common.model.PageRequestModel;
import com.nam_nguyen_03.gira.common.model.PageResponseModel;
import com.nam_nguyen_03.gira.user.dto.UserResponseDTO;
import com.nam_nguyen_03.gira.user.dto.UserUpdateDTO;

public interface UserService {

    UserResponseDTO updateMyProfile(UserUpdateDTO rq);

    PageResponseModel<UserResponseDTO> search(PageRequestModel pageRequestModel);

    void deleteById(String id);

    UserResponseDTO updateUser( UserUpdateDTO rq, String id);

    UserResponseDTO getUserResponseById(String id);

    UserResponseDTO addGroup(String idUser, String idGroup);

    UserResponseDTO getMyProfile();

    UserResponseDTO updateStatus(String id, String status);

    void updateMyPassword(String password);

    void updatePassword(String id, String password);
    
    
}
