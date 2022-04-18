package com.nam_nguyen_03.gira.role.service;

import java.util.stream.Collectors;
import com.nam_nguyen_03.gira.common.model.PageRequestModel;
import com.nam_nguyen_03.gira.common.model.PageResponseModel;
import com.nam_nguyen_03.gira.role.dto.ProgramDTO;
import com.nam_nguyen_03.gira.role.dto.ProgramResponseDTO;
import com.nam_nguyen_03.gira.role.mapper.ProgramMapper;
import com.nam_nguyen_03.gira.role.model.GiraProgram;
import com.nam_nguyen_03.gira.role.repository.ProgramRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProgramServiceImpl implements ProgramService {
    
    @Autowired
    private ProgramRepository programRepository;

    @Override
    public PageResponseModel<ProgramResponseDTO> search(PageRequestModel pageRequestModel) {
        int page = pageRequestModel.getPageCurrent()-1;
        int size = pageRequestModel.getItemPerPage();
        boolean isAscending = pageRequestModel.isIncrementSort();
        String fieldNameSearch = pageRequestModel.getFieldNameSearch();
        String fieldNameSort = pageRequestModel.getFieldNameSort();
        String valueSearch = pageRequestModel.getValueSearch();
        Pageable pageable = PageRequest.of(page, size);
        Page<GiraProgram> rp = null;

        if ("name".equals(fieldNameSort)) {
            pageable = PageRequest.of(page, size, isAscending ? Sort.by(fieldNameSort).ascending() : Sort.by(fieldNameSort).descending());

        }
        
        if("name".equals(fieldNameSearch)){
            rp =  programRepository.searchByName(valueSearch, pageable);
        }

        if(rp == null){
            rp = programRepository.findAll(pageable);
        }
        return new PageResponseModel<>(rp.getNumber() + 1, rp.getTotalPages(), 
            rp.getContent().stream().map(ProgramMapper.INSTANCE::toProgramResponseDTO).collect(Collectors.toList()));
    }

    @Override
    public ProgramResponseDTO save(ProgramDTO program) {
    
        GiraProgram rq = ProgramMapper.INSTANCE.toModel(program);
        rq.generateIdentityKey();
        
        return ProgramMapper.INSTANCE.toProgramResponseDTO(programRepository.save(rq)) ;
    } 
}
