package com.supplynext.company_api.repositories;

import com.supplynext.company_api.models.Company;
import com.supplynext.company_api.models.CompanyEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
}
