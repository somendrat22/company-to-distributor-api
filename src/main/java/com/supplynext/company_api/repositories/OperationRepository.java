package com.supplynext.company_api.repositories;

import com.supplynext.company_api.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OperationRepository extends JpaRepository<Operation, UUID> {
    public Operation findByOperationName(String name);
    public boolean existsByOperationName(String name);
}
