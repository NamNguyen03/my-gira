package com.nam_nguyen_03.gira.role.repository;

import java.util.UUID;

import com.nam_nguyen_03.gira.role.model.GiraGroup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GiraGroupRepository extends JpaRepository<GiraGroup, UUID> {

    @Query( value =  "Select g from GiraGroup g where g.name like %:name% ")
    Page<GiraGroup> searchByName(@Param("name") String name, Pageable pageable);

    boolean existsByName(String name);

}
