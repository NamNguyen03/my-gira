package com.nam_nguyen_03.gira.role.service;

import java.util.stream.Collectors;

import com.nam_nguyen_03.gira.common.model.PageRequestModel;
import com.nam_nguyen_03.gira.common.model.PageResponseModel;
import com.nam_nguyen_03.gira.common.util.ServiceHelper;
import com.nam_nguyen_03.gira.role.dto.RoleDTO;
import com.nam_nguyen_03.gira.role.dto.RoleResponseDTO;
import com.nam_nguyen_03.gira.role.mapper.RoleMapper;
import com.nam_nguyen_03.gira.role.model.GiraProgram;
import com.nam_nguyen_03.gira.role.model.GiraRole;
import com.nam_nguyen_03.gira.role.repository.GiraRoleRepository;
import com.nam_nguyen_03.gira.role.repository.ProgramRepository;

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
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private GiraRoleRepository roleRepository;
    
    @Autowired
    private ProgramRepository programRepository;

    @Value("${role.not-found}")
    private String errorsRoleNotFound;

    @Value("${program.not-found}")
    private String errorsProgramNotFound;

    @Autowired
    private ServiceHelper<GiraRole> serviceRoleHelper;

    @Autowired
    private ServiceHelper<GiraProgram> serviceProgramHelper;

    @Override
    public RoleResponseDTO save(RoleDTO role) {
        
        return RoleMapper.INSTANCE.toRoleResponseDTO(roleRepository.save(RoleMapper.INSTANCE.toModel(role)));
    }

    @Override
    public PageResponseModel<RoleResponseDTO> search(PageRequestModel pageRequestModel) {
        int page = pageRequestModel.getPageCurrent()-1;
        int size = pageRequestModel.getItemPerPage();
        boolean isAscending = pageRequestModel.isIncrementSort();
        String fieldNameSearch = pageRequestModel.getFieldNameSearch();
        String fieldNameSort = pageRequestModel.getFieldNameSort();
        String valueSearch = pageRequestModel.getValueSearch();
        Pageable pageable = PageRequest.of(page, size);
        Page<GiraRole> rp = null;

        if ("name".equals(fieldNameSort)) {
            pageable = PageRequest.of(page, size, isAscending ? Sort.by(fieldNameSort).ascending() : Sort.by(fieldNameSort).descending());

        }

        if("name".equals(fieldNameSearch)){
            rp =  roleRepository.searchByName(valueSearch, pageable);
        }

        if(rp == null){
            rp = roleRepository.findAll(pageable);
        }

        return new PageResponseModel<>(rp.getNumber() + 1, rp.getTotalPages(), 
            rp.getContent().stream().map(RoleMapper.INSTANCE::toRoleResponseDTO).collect(Collectors.toList()));
    }

    @Override
    public RoleResponseDTO insertProgram(String idRole, String idProgram) {
        GiraProgram program = serviceProgramHelper.getEntityById(idProgram, programRepository, errorsProgramNotFound);
        GiraRole role = serviceRoleHelper.getEntityById(idRole, roleRepository, errorsRoleNotFound);
       
        role.addProgram(program);
        
        return RoleMapper.INSTANCE.toRoleResponseDTO(roleRepository.save(role));
    }


}
