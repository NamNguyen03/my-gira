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
    Page<GiraUser> searchByUsername(@Param("username")  String username, Pageable pageable);

    @Query( value =  "Select u from GiraUser u where u.displayName like %:displayName% ")
    Page<GiraUser> searchByDisplayName(@Param("displayName") String displayName, Pageable pageable);

    @Query( value =  "Select u from GiraUser u where u.email like %:email% ")
    Page<GiraUser> searchByEmail(@Param("email") String email, Pageable pageable);

    @Query( value =  "Select u from GiraUser u where u.firstName like %:firstName% ")
    Page<GiraUser> searchByFirstName(@Param("firstName") String firstName, Pageable pageable);

    @Query( value =  "Select u from GiraUser u where u.lastName like %:lastName% ")
    Page<GiraUser> searchByLastName(@Param("lastName") String lastName, Pageable pageable);

}
