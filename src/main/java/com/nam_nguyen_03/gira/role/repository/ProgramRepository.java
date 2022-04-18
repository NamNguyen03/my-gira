package com.nam_nguyen_03.gira.role.repository;

import java.util.UUID;

import com.nam_nguyen_03.gira.role.model.GiraProgram;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<GiraProgram, UUID>  {
   
    @Query( value =  "Select p from GiraProgram p where p.name like %:name% ")
    Page<GiraProgram> searchByName(@Param("name") String valueSearch, Pageable pageable);

    boolean existsByName(String name);

}
