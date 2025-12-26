package com.supplynext.company_api.services;

import com.supplynext.company_api.dto.CompanyLoginResp;
import com.supplynext.company_api.dto.UserLoginDto;
import com.supplynext.company_api.dto.UserLoginRespDto;
import com.supplynext.company_api.exceptions.InvalidCredentialsException;
import com.supplynext.company_api.exceptions.UnAuthorizedException;
import com.supplynext.company_api.models.*;
import com.supplynext.company_api.repositories.UserRepository;
import com.supplynext.company_api.security.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    UserService userService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    CompanyEmployeeService companyEmployeeService;

    public UserLoginRespDto loginUser(UserLoginDto userLoginDto){
        String email = userLoginDto.getEmail();
        String password = userLoginDto.getPassword();
        User user = userService.getUserByEmail(email);
        if(user.getPassword().equals(password)){
            Company company = companyEmployeeService.getEmployeeCompanyDetails(user.getSysId());
            UserLoginRespDto respDto = new UserLoginRespDto();
            respDto.setFullName(user.getFullName());
            respDto.setEmail(user.getEmail());
            respDto.setPhoneNumber(user.getPhoneNumber());
            respDto.setAddressLine1(user.getAddressLine1());
            respDto.setAddressLine2(user.getAddressLine2());
            respDto.setAddressLine3(user.getAddressLine3());
            respDto.setPincode(user.getPincode());
            respDto.setRoles(user.getRoles());
            respDto.setCompanyUser(true);
            CompanyLoginResp companyLoginResp = new CompanyLoginResp();
            companyLoginResp.setCompanyId(company.getCompanyId());
            companyLoginResp.setCompanyName(company.getCompanyName());
            companyLoginResp.setLegalName(company.getLegalName());
            companyLoginResp.setCompanyType(company.getCompanyType());
            companyLoginResp.setCompanyLogoUrl(company.getCompanyLogoUrl());
            companyLoginResp.setAddressLine1(company.getAddressLine1());
            companyLoginResp.setAddressLine2(company.getAddressLine2());
            companyLoginResp.setAddressLine3(company.getAddressLine3());
            companyLoginResp.setCity(company.getCity());
            companyLoginResp.setState(company.getState());
            companyLoginResp.setCountry(company.getCountry());
            companyLoginResp.setPincode(company.getPincode());
            companyLoginResp.setGeoLatitude(company.getGeoLatitude());
            companyLoginResp.setGeoLongitude(company.getGeoLongitude());
            respDto.setCompanyLoginResp(companyLoginResp);
            List<String> roleNames = this.getRoleNames(user.getRoles());
            String jwtToken = jwtUtil.generateJwtToken(user.getEmail(), roleNames);
            respDto.setToken(jwtToken);
            return respDto;
        }else{
            throw new InvalidCredentialsException(String.format("Invalid credentials entered by user"));
        }
    }

    public User getUserDetailsByUserSession(String token){
        Claims claims = jwtUtil.decryptToken(token);
        String email = claims.get("email", String.class);
        return userService.getUserByEmail(email);
    }

    public User isUserHavingAccessToPerformOperation(String token,
                                                        String operationName){
        User user = this.getUserDetailsByUserSession(token);
        List<Role> roles = user.getRoles();

        for(Role role : roles){
            List<Operation> operations = role.getOperations();
            for(Operation operation : operations){
                if(operation.getOperationName().equals(operationName)){
                    return user;
                }
            }
        }
        throw new UnAuthorizedException("User is not allowed to perform operation");
    }

    public List<String> getRoleNames(List<Role> roles){
        List<String> roleNames = new ArrayList<>();
        for(Role role : roles){
            roleNames.add(role.getRoleName());
        }
        return roleNames;
    }
}
