package com.nam_nguyen_03.gira.user.service;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import com.nam_nguyen_03.gira.common.exception.BusinessException;
import com.nam_nguyen_03.gira.common.model.PageRequestModel;
import com.nam_nguyen_03.gira.common.model.PageResponseModel;
import com.nam_nguyen_03.gira.common.util.ServiceHelper;
import com.nam_nguyen_03.gira.common.util.UserPrincipal;
import com.nam_nguyen_03.gira.role.model.GiraGroup;
import com.nam_nguyen_03.gira.role.repository.GiraGroupRepository;
import com.nam_nguyen_03.gira.user.dto.UserDetailsResponseDTO;
import com.nam_nguyen_03.gira.user.dto.UserResponseDTO;
import com.nam_nguyen_03.gira.user.dto.UserUpdateDTO;
import com.nam_nguyen_03.gira.user.mapper.UserMapper;
import com.nam_nguyen_03.gira.user.model.GiraUser;
import com.nam_nguyen_03.gira.user.model.UserStatus;
import com.nam_nguyen_03.gira.user.repository.GiraUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@PropertySources({ @PropertySource("classpath:/validation/message.properties") })
public class UserServiceImpl  implements UserService {

    @Autowired
    private GiraUserRepository giraUserRepository;

    @Value("${user.email.existed}")
    private String messagesExistsEmail;

    @Value("${user.email.invalid}")
    private String messagesEmailInvalid;

    @Value("${entity.id.invalid}")
    private String errorsIdInvalid;

    @Value("${user.not-found}")
    private String errorsUserNotFound;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private GiraGroupRepository giraGroupRepository;

    @Autowired
    private ServiceHelper<GiraGroup> serviceGroupHelper;

    @Autowired
    private ServiceHelper<GiraUser> serviceUserHelper;

    @Value("${group.not-found}")
    private String errorsGroupNotFound;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public UserResponseDTO updateMyProfile(UserUpdateDTO rq) {
        String usernameCurrent = UserPrincipal.getUsernameCurrent();

        GiraUser userCurrent = giraUserRepository.findByUsername(usernameCurrent).get();

        GiraUser user = setUpdateUser(userCurrent, rq);
       
        return UserMapper.INSTANCE.toUserResponseDTO(giraUserRepository.save(user));
    }
    
    @Override
    public UserResponseDTO updateUser(UserUpdateDTO rq, String id) {
        GiraUser userCurrent = serviceUserHelper.getEntityById(id, giraUserRepository, errorsUserNotFound);
        GiraUser user = setUpdateUser(userCurrent, rq);
        return UserMapper.INSTANCE.toUserResponseDTO(giraUserRepository.save(user));
    }

    private GiraUser setUpdateUser(GiraUser userCurrent, UserUpdateDTO rq) {

        if(serviceUserHelper.isValidString(rq.getDisplayName())){
            userCurrent.setDisplayName(rq.getDisplayName());
        }

        if(serviceUserHelper.isValidString(rq.getEmail())){
            if( !VALID_EMAIL_ADDRESS_REGEX.matcher(rq.getEmail()).find()){
                throw new BusinessException(messagesEmailInvalid);
            }

            if(!userCurrent.getEmail().equals(rq.getEmail()) && giraUserRepository.existsByEmail(rq.getEmail())){
                throw new BusinessException(messagesExistsEmail);
            }
            userCurrent.setEmail(rq.getEmail());
        }

        if(serviceUserHelper.isValidString(rq.getFirstName())){
            userCurrent.setFirstName(rq.getFirstName());
        }

        if(serviceUserHelper.isValidString(rq.getLastName())){
            userCurrent.setLastName(rq.getLastName());
        }

        if(serviceUserHelper.isValidString(rq.getAvatar())){
            userCurrent.setAvatar(rq.getAvatar());
        }

        if(serviceUserHelper.isValidString(rq.getDepartment())){
            userCurrent.setDepartment(rq.getDepartment());
        }

        if(serviceUserHelper.isValidString(rq.getMajor())){
            userCurrent.setMajor(rq.getMajor());
        }

        if(serviceUserHelper.isValidString(rq.getHobbies())){
            userCurrent.setHobbies(rq.getHobbies());
        }

        if(serviceUserHelper.isValidString(rq.getFacebook())){
            userCurrent.setFacebook(rq.getFacebook());
        }
        return userCurrent;
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
            rp =  giraUserRepository.searchByUsername(valueSearch, pageable);
        }

        //display name
        if("displayName".equals(fieldNameSearch)){
            rp =  giraUserRepository.searchByDisplayName(valueSearch, pageable);
        }

        //email
        if("email".equals(fieldNameSearch)){
            rp =  giraUserRepository.searchByEmail(valueSearch, pageable);
        }

        //first name
        if("firstName".equals(fieldNameSearch)){
            rp =  giraUserRepository.searchByFirstName(valueSearch, pageable);
        }

        //last name
        if("lastName".equals(fieldNameSearch)){
            rp =  giraUserRepository.searchByLastName(valueSearch, pageable);
        }

        //if firstName not existed then search all
        if(rp == null ){
            rp = giraUserRepository.findAll(pageable);
        }

        return new PageResponseModel<>(rp.getNumber() + 1, rp.getTotalPages(), 
            rp.getContent().stream().map(UserMapper.INSTANCE::toUserResponseDTO).collect(Collectors.toList()));
    }

    @Override
    public void deleteById(String id) {

        giraUserRepository.delete(serviceUserHelper.getEntityById(id, giraUserRepository, errorsUserNotFound));
    }

    @Override
    public UserDetailsResponseDTO getUserResponseById(String id) {
        
        return UserMapper.INSTANCE.toUserDetailsResponseDTO(serviceUserHelper.getEntityById(id, giraUserRepository, errorsUserNotFound));
    }

    @Override
    public UserDetailsResponseDTO addGroup(String idUser, String idGroup) {
        
        GiraUser user = serviceUserHelper.getEntityById(idUser, giraUserRepository, errorsUserNotFound);
        GiraGroup group = serviceGroupHelper.getEntityById(idGroup, giraGroupRepository, errorsGroupNotFound);

        user.addGroup(group);

        return UserMapper.INSTANCE.toUserDetailsResponseDTO(giraUserRepository.save(user));
    }

    @Override
    public UserDetailsResponseDTO getMyProfile() {
        String usernameCurrent = UserPrincipal.getUsernameCurrent();

        return UserMapper.INSTANCE.toUserDetailsResponseDTO( giraUserRepository.findByUsername(usernameCurrent).get());
    }

    @Override
    public UserResponseDTO updateStatus(String id, String status) {

        GiraUser user = serviceUserHelper.getEntityById(id, giraUserRepository, errorsUserNotFound);

        if(UserStatus.ACTIVE.toString().equals(status.toUpperCase())){
            user.setStatus(UserStatus.ACTIVE);
        }

        if(UserStatus.PERMANENT_BLOCKED.toString().equals(status.toUpperCase())){
            user.setStatus(UserStatus.PERMANENT_BLOCKED);
        }

        if(UserStatus.TEMPORARY_BLOCKED.toString().equals(status.toUpperCase())){
            user.setStatus(UserStatus.TEMPORARY_BLOCKED);
        }     

       return UserMapper.INSTANCE.toUserResponseDTO(giraUserRepository.save(user));
    }

    @Override
    public void updateMyPassword(String password) {

        String usernameCurrent = UserPrincipal.getUsernameCurrent();

        GiraUser userCurrent = giraUserRepository.findByUsername(usernameCurrent).get();

        userCurrent.setPassword(encoder.encode(password));
        giraUserRepository.save(userCurrent);
    }

    @Override
    public void updatePassword(String id, String password) {
        GiraUser user = serviceUserHelper.getEntityById(id, giraUserRepository, errorsUserNotFound);

        user.setPassword(encoder.encode(password));

        giraUserRepository.save(user);

    }

    @Override
    public UserDetailsResponseDTO removeGroup(String idUser, String idGroup) {
        GiraUser user = serviceUserHelper.getEntityById(idUser, giraUserRepository, errorsUserNotFound);
        GiraGroup group = serviceGroupHelper.getEntityById(idGroup, giraGroupRepository, errorsGroupNotFound);

        user.removeGroup(group);

        return UserMapper.INSTANCE.toUserDetailsResponseDTO(giraUserRepository.save(user));
    }
    
    
}
