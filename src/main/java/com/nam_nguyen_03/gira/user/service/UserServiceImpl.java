package com.nam_nguyen_03.gira.user.service;
import java.util.stream.Collectors;

import com.nam_nguyen_03.gira.common.exception.BusinessException;
import com.nam_nguyen_03.gira.common.model.PageRequestModel;
import com.nam_nguyen_03.gira.common.model.PageResponseModel;
import com.nam_nguyen_03.gira.common.util.UserPrincipal;
import com.nam_nguyen_03.gira.user.dto.UserResponseDTO;
import com.nam_nguyen_03.gira.user.dto.UserUpdateDTO;
import com.nam_nguyen_03.gira.user.mapper.UserMapper;
import com.nam_nguyen_03.gira.user.model.GiraUser;
import com.nam_nguyen_03.gira.user.repository.GiraUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@PropertySources({ @PropertySource("classpath:/validation/message.properties") })
public class UserServiceImpl  implements UserService {

    @Autowired
    private GiraUserRepository repository;

    @Value("${user.email.existed}")
    private String messagesExistsEmail;

    @Override
    public UserResponseDTO updateMyProfile(UserUpdateDTO rq) {
        String usernameCurrent = UserPrincipal.getUsernameCurrent();

        GiraUser userCurrent = repository.findByUsername(usernameCurrent).get();

        if(checkString(rq.getPassword())){
            userCurrent.setPassword(rq.getPassword());
        }

        if(checkString(rq.getDisplayName())){
            userCurrent.setDisplayName(rq.getDisplayName());
        }

        if(checkString(rq.getEmail())){
            if(repository.existsByEmail(rq.getEmail())){
                throw new BusinessException(messagesExistsEmail);
            }
            userCurrent.setEmail(rq.getEmail());
        }

        if(checkString(rq.getFirstName())){
            userCurrent.setFirstName(rq.getFirstName());
        }

        if(checkString(rq.getLastName())){
            userCurrent.setLastName(rq.getLastName());
        }

        if(checkString(rq.getAvatar())){
            userCurrent.setAvatar(rq.getAvatar());
        }

        if(checkString(rq.getDepartment())){
            userCurrent.setDepartment(rq.getDepartment());
        }

        if(checkString(rq.getMajor())){
            userCurrent.setMajor(rq.getMajor());
        }

        if(checkString(rq.getHobbies())){
            userCurrent.setHobbies(rq.getHobbies());
        }

        if(checkString(rq.getFacebook())){
            userCurrent.setFacebook(rq.getFacebook());
        }
        return UserMapper.INSTANCE.toUserResponseDTO(repository.save(userCurrent));
    }
    
    boolean checkString(String s){
        if(s == null || s.length() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public PageResponseModel<UserResponseDTO> search(PageRequestModel pageRequestModel) {
        int page = pageRequestModel.getPageCurrent()-1;
        int size = pageRequestModel.getItemPerPage();
        boolean isAscending = pageRequestModel.isIncrementSort();
        String fieldNameSearch = pageRequestModel.getFieldNameSearch();
        String fieldNameSort = pageRequestModel.getFieldNameSort();
        String valueSearch = pageRequestModel.getValueSearch();
        Pageable pageable = PageRequest.of(page, size);
        Page<GiraUser> rp = null;

        if(fieldNameSort != null && fieldNameSort.length() > 0) {
            pageable = PageRequest.of(page, size , isAscending ? Sort.by(fieldNameSort).ascending(): Sort.by(fieldNameSort).descending());
        }


        //username
        if("username".equals(fieldNameSearch)){
            rp =  repository.SearchByUsername(valueSearch, pageable);
        }

        //display name

        //email

        //first name

        //last name
        if(rp == null ){
            throw new BusinessException("not content");
        }
        return new PageResponseModel<>(rp.getNumber() + 1, rp.getTotalPages(), 
            rp.getContent().stream().map(UserMapper.INSTANCE::toUserResponseDTO).collect(Collectors.toList()) );
    }
}
