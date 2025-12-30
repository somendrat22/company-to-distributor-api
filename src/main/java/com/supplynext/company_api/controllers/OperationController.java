package com.supplynext.company_api.controllers;

import com.supplynext.company_api.exceptions.UnAuthorizedException;
import com.supplynext.company_api.models.Operation;
import com.supplynext.company_api.services.AuthService;
import com.supplynext.company_api.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/c2d/api/v1/operations")
public class OperationController {

    @Autowired
    AuthService authService;
    @Autowired
    OperationService operationService;

    @GetMapping("/all")
    public ResponseEntity fetchAllOperations(@RequestHeader String Authorization){
        try{
            authService.isUserHavingAccessToPerformOperation(Authorization, "VIEW_OPERATIONS");
            List<Operation> operations = operationService.fetchAllOperations();
            return new ResponseEntity(operations, HttpStatus.OK);
        }catch (UnAuthorizedException e){
            HashMap<String, String> resp = new HashMap<>();
            resp.put("message", e.getMessage());
            return new ResponseEntity(resp, HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            HashMap<String, String> resp = new HashMap<>();
            resp.put("message", e.getMessage());
            return new ResponseEntity(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
