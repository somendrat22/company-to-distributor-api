package com.supplynext.company_api.services;

import com.supplynext.company_api.models.Operation;
import com.supplynext.company_api.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationService {
    @Autowired
    OperationRepository operationRepository;

    public List<Operation> fetchAllOperations(){
        return operationRepository.findAll();
    }
}
