package com.supplynext.company_api.services;

import com.supplynext.company_api.models.Operation;
import com.supplynext.company_api.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OperationService {
    @Autowired
    OperationRepository operationRepository;

    public List<Operation> fetchAllOperations(){
        return operationRepository.findAll();
    }

    public List<Operation> fetchOperationsBySysId(List<UUID> sysIds){
        List<Operation> operations = new ArrayList<>();
        for(UUID sysId : sysIds){
            operations.add(operationRepository.findById(sysId).orElse(null));
        }
        return operations;
    }
}
