package com.nam_nguyen_03.gira.role.repository;

import java.util.UUID;

import com.nam_nguyen_03.gira.role.model.GiraRole;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GiraRoleRepository extends JpaRepository<GiraRole, UUID> {

    @Query( value =  "Select r from GiraRole r where r.name like %:name% ")
    Page<GiraRole> searchByName(@Param("name") String name, Pageable pageable);

    boolean existsByName(String name);

}
