package com.supplynext.company_api.controllers;

import com.supplynext.company_api.constants.OperationNameConstants;
import com.supplynext.company_api.dto.CompanyOnboardingRequestDto;
import com.supplynext.company_api.dto.InviteEmployeeDto;
import com.supplynext.company_api.exceptions.UnAuthorizedException;
import com.supplynext.company_api.models.CompanyEmployee;
import com.supplynext.company_api.models.Role;
import com.supplynext.company_api.models.User;
import com.supplynext.company_api.services.AuthService;
import com.supplynext.company_api.services.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import javax.management.ObjectName;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/c2d/api/v1/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;
    @Autowired
    AuthService authService;

    ObjectMapper objectMapper = new ObjectMapper();

    // Whenever we upload document to the endpoint we call it as multipart endpoint.
    @PostMapping("/start-onboarding")
    public ResponseEntity startOnBoarding(
            @RequestPart(value = "gstCertificate") MultipartFile gstCertificate,
            @RequestPart(value = "panCard") MultipartFile panCard,
            @RequestPart(value = "companyRegistrationDocument") MultipartFile companyRegistrationDoc,
            @RequestPart(value = "companyLogo") MultipartFile companyLogo,
            @RequestPart(value = "companyInfo") String companyDetails
            ){
            if(gstCertificate == null){
                log.info("Inside gstCertificate");
            }
            CompanyOnboardingRequestDto companyOnboardingRequestDto =  objectMapper.readValue(companyDetails, CompanyOnboardingRequestDto.class);
            try{
                companyService.startOnboarding(gstCertificate, panCard, companyRegistrationDoc, companyLogo, companyOnboardingRequestDto);
                HashMap<String, String> resp = new HashMap<>();
                resp.put("message", "Company Details uploaded successfully");
                return new ResponseEntity(resp, HttpStatus.CREATED);
            }catch (Exception e){
                e.printStackTrace();
                HashMap<String, String> resp = new HashMap<>();
                resp.put("message", e.getMessage());
                return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @GetMapping("/get-roles")
    public ResponseEntity getCompanyRolesByCurrentUserSession(
            @RequestHeader String Authorization
    ){
        try {
            /** Instead of getting user details first thing we should check current user is having access to
             * get the roles or VIEW_ROLE
             * If user is having the access then we should get the user details else we should reject the request
             */
            User user = authService.isUserHavingAccessToPerformOperation(Authorization, "VIEW_ROLE");
            /**
             * After getting user we shoulf call role service to get the roles of the company
             */
            List<Role> roles = companyService.getCompanyRoleByUser(user);
            return new ResponseEntity<>(roles,HttpStatus.OK);
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


    @PostMapping("/invite-employee")
    public ResponseEntity inviteEmployeeToOrg(
            @RequestBody InviteEmployeeDto inviteEmployeeDto,
            @RequestHeader String Authorization
    ) {
        log.info("Invite employee API called");
        log.debug("InviteEmployeeDto received: {}", inviteEmployeeDto);

        try {
            log.info("Validating user authorization for INVITE_EMPLOYEE operation");
            User inviter = authService.isUserHavingAccessToPerformOperation(
                    Authorization,
                    OperationNameConstants.INVITE_EMPLOYEE
            );

            log.info("Authorization successful for userId={}", inviter.getSysId());

            log.info("Inviting employee to organization. Inviter userId={}", inviter.getSysId());
            CompanyEmployee companyEmployee =
                    companyService.inviteEmployeeToInviterOrg(inviter, inviteEmployeeDto);

            log.info("Employee invited successfully. CompanyEmployeeId={}",
                    companyEmployee.getSysId());

            return new ResponseEntity(companyEmployee, HttpStatus.CREATED);

        } catch (UnAuthorizedException e) {
            log.warn("Unauthorized access while inviting employee. Reason={}", e.getMessage());

            HashMap<String, String> resp = new HashMap<>();
            resp.put("message", e.getMessage());
            return new ResponseEntity(resp, HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            log.error("Unexpected error occurred while inviting employee", e);

            HashMap<String, String> resp = new HashMap<>();
            resp.put("message", e.getMessage());
            return new ResponseEntity(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
