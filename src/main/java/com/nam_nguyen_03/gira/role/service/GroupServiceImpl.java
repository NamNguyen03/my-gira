package com.nam_nguyen_03.gira.role.service;

import java.util.stream.Collectors;

import com.nam_nguyen_03.gira.common.model.PageRequestModel;
import com.nam_nguyen_03.gira.common.model.PageResponseModel;
import com.nam_nguyen_03.gira.common.util.ServiceHelper;
import com.nam_nguyen_03.gira.role.dto.GroupDTO;
import com.nam_nguyen_03.gira.role.dto.GroupResponseDTO;
import com.nam_nguyen_03.gira.role.mapper.GroupMapper;
import com.nam_nguyen_03.gira.role.model.GiraGroup;
import com.nam_nguyen_03.gira.role.model.GiraRole;
import com.nam_nguyen_03.gira.role.repository.GiraGroupRepository;
import com.nam_nguyen_03.gira.role.repository.GiraRoleRepository;

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
public class GroupServiceImpl implements GroupService {
    
    @Autowired
    private GiraGroupRepository groupsRepository;

    @Value("${group.not-found}")
    private String errorsGroupNotFound;

    @Autowired
    private GiraRoleRepository roleRepository;

    @Autowired
    private ServiceHelper<GiraGroup> serviceGroupHelper;

    @Autowired 
    private ServiceHelper<GiraRole> serviceRoleHelper;

    @Value("${role.not-found}")
    private String errorsRoleNotFound;

    @Override
    public GroupResponseDTO save(GroupDTO group) {

        return GroupMapper.INSTANCE.toGroupResponseDTO(groupsRepository.save(GroupMapper.INSTANCE.toModel(group)));
    }

    @Override
    public PageResponseModel<GroupResponseDTO> search(PageRequestModel pageRequestModel) {
        int page = pageRequestModel.getPageCurrent()-1;
        int size = pageRequestModel.getItemPerPage();
        boolean isAscending = pageRequestModel.isIncrementSort();
        String fieldNameSearch = pageRequestModel.getFieldNameSearch();
        String fieldNameSort = pageRequestModel.getFieldNameSort();
        String valueSearch = pageRequestModel.getValueSearch();
        Pageable pageable = PageRequest.of(page, size);
        Page<GiraGroup> rp = null;

        if ("name".equals(fieldNameSort)) {
            pageable = PageRequest.of(page, size, isAscending ? Sort.by(fieldNameSort).ascending() : Sort.by(fieldNameSort).descending());

        }

        if("name".equals(fieldNameSearch)){
            rp =  groupsRepository.searchByName(valueSearch, pageable);
        }

        if(rp == null){
            rp = groupsRepository.findAll(pageable);
        }

        return new PageResponseModel<>(rp.getNumber() + 1, rp.getTotalPages(), 
            rp.getContent().stream().map(GroupMapper.INSTANCE::toGroupResponseDTO).collect(Collectors.toList()));
    }

    @Override
    public GroupResponseDTO insertRole(String idGroup, String idRole) {
        GiraGroup group = serviceGroupHelper.getEntityById(idGroup, groupsRepository, errorsGroupNotFound);
        GiraRole role = serviceRoleHelper.getEntityById(idRole, roleRepository, errorsRoleNotFound);
       
        group.addRole(role);
        
        return GroupMapper.INSTANCE.toGroupResponseDTO(groupsRepository.save(group));
    }

}
