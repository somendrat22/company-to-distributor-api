package com.supplynext.company_api.controllers;

import com.supplynext.company_api.dto.UserLoginDto;
import com.supplynext.company_api.dto.UserLoginRespDto;
import com.supplynext.company_api.exceptions.InvalidCredentialsException;
import com.supplynext.company_api.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/c2d/api/v1/user")
public class UserController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody UserLoginDto userLoginDto){
        log.info("Inside Login User Method");
        try{
            UserLoginRespDto loginResp = authService.loginUser(userLoginDto);
            return new ResponseEntity(loginResp, HttpStatus.OK);
        }catch (InvalidCredentialsException e){

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
