package com.nam_nguyen_03.gira.user.service;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
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

    @Value("${user.email.invalid}")
    private String messagesEmailInvalid;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public UserResponseDTO updateMyProfile(UserUpdateDTO rq) {
        String usernameCurrent = UserPrincipal.getUsernameCurrent();

        GiraUser userCurrent = repository.findByUsername(usernameCurrent).get();

        GiraUser user = setUpdateUser(userCurrent, rq);
       
        return UserMapper.INSTANCE.toUserResponseDTO(repository.save(user));
    }
    
    @Override
    public UserResponseDTO updateUser(UserUpdateDTO rq, String id) {
        GiraUser userCurrent = getUserById(id);
        GiraUser user = setUpdateUser(userCurrent, rq);
        return UserMapper.INSTANCE.toUserResponseDTO(repository.save(user));
    }

    private GiraUser setUpdateUser(GiraUser userCurrent, UserUpdateDTO rq) {


        if(checkString(rq.getDisplayName())){
            userCurrent.setDisplayName(rq.getDisplayName());
        }

        if(checkString(rq.getEmail())){
            if( !VALID_EMAIL_ADDRESS_REGEX.matcher(rq.getEmail()).find()){
                throw new BusinessException(messagesEmailInvalid);
            }

            if(!userCurrent.getEmail().equals(rq.getEmail()) && repository.existsByEmail(rq.getEmail())){
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
        return userCurrent;
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

        if (null != fieldNameSort && fieldNameSort.matches("username|displayName|email|firstName|lastName")) {
            pageable = PageRequest.of(page, size, isAscending ? Sort.by(fieldNameSort).ascending() : Sort.by(fieldNameSort).descending());

        }


        //username
        if("username".equals(fieldNameSearch)){
            rp =  repository.searchByUsername(valueSearch, pageable);
        }

        //display name
        if("displayName".equals(fieldNameSearch)){
            rp =  repository.searchByDisplayName(valueSearch, pageable);
        }

        //email
        if("email".equals(fieldNameSearch)){
            rp =  repository.searchByEmail(valueSearch, pageable);
        }

        //first name
        if("firstName".equals(fieldNameSearch)){
            rp =  repository.searchByFirstName(valueSearch, pageable);
        }

        //last name
        if("lastName".equals(fieldNameSearch)){
            rp =  repository.searchByLastName(valueSearch, pageable);
        }

        //if firstName not existed then search all
        if(rp == null ){
            rp = repository.findAll(pageable);
        }

        return new PageResponseModel<>(rp.getNumber() + 1, rp.getTotalPages(), 
            rp.getContent().stream().map(UserMapper.INSTANCE::toUserResponseDTO).collect(Collectors.toList()));
    }

    @Override
    public void deleteById(String id) {

        repository.delete(getUserById(id));
    }

    private GiraUser getUserById(String id){
        UUID uuid;
        try{
            uuid = UUID.fromString(id);
        }catch(Exception e){
            throw new BusinessException("id invalid");
        }
        
        Optional<GiraUser> userOpt = repository.findById(uuid);

        if(userOpt.isEmpty()){
            throw new BusinessException("user not found");
        }
        return userOpt.get();
    }

    @Override
    public UserResponseDTO getUserResponseById(String id) {
        
        return UserMapper.INSTANCE.toUserResponseDTO(getUserById(id));
    }
    
}
