package com.supplynext.company_api.repositories;

import com.supplynext.company_api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    @Query(value = "select * from roles where role_name like CONCAT(:companyName, '_%')", nativeQuery = true)
    public List<Role> fetchRolesByCompanyName(String companyName);
}
