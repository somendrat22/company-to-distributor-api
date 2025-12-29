package com.supplynext.company_api.repositories;

import com.supplynext.company_api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    @Query("SELECT r FROM Role r WHERE r.roleName LIKE CONCAT(:companyName, '_%')")
    List<Role> fetchRolesByCompanyName(@Param("companyName") String companyName);
}
