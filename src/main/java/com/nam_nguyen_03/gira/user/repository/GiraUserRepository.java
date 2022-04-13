package com.nam_nguyen_03.gira.user.repository;

import java.util.Optional;
import java.util.UUID;

import com.nam_nguyen_03.gira.user.model.GiraUser;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GiraUserRepository extends JpaRepository<GiraUser, UUID>  {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<GiraUser> findByUsername(String username);

    @Query( value =  "Select u from GiraUser u where u.username like %:username% ")
    Page<GiraUser> SearchByUsername(@Param("username")  String username, Pageable pageable);

}
