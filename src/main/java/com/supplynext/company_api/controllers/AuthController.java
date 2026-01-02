package com.supplynext.company_api.controllers;

import com.supplynext.company_api.dto.CreateRoleRequestDto;
import com.supplynext.company_api.exceptions.UnAuthorizedException;
import com.supplynext.company_api.models.Role;
import com.supplynext.company_api.models.User;
import com.supplynext.company_api.services.AuthService;
import com.supplynext.company_api.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/c2d/api/v1/auth")
public class AuthController {


    AuthService authService;
    CompanyService companyService;

    @Autowired
    public AuthController(AuthService authService, CompanyService companyService){
        this.companyService = companyService;
        this.authService = authService;
    }

    @PostMapping("/create-role")
    public ResponseEntity createRole(
            @RequestBody CreateRoleRequestDto createRoleRequestDto,
            @RequestHeader String Authorization
            ){

            User user = authService.isUserHavingAccessToPerformOperation(Authorization, "CREATE_ROLE");
            Role role = companyService.createRoleForCompanyByUserSession(user, createRoleRequestDto);
            return new ResponseEntity(role, HttpStatus.CREATED);

    }
}
