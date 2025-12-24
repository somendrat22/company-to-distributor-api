package com.supplynext.company_api.controllers;

import com.supplynext.company_api.dto.CompanyOnboardingRequestDto;
import com.supplynext.company_api.services.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import javax.management.ObjectName;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/c2d/api/v1/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    ObjectMapper objectMapper = new ObjectMapper();

    // Whenever we upload document to the endpoint we call it as multipart endpoint.
    @PostMapping("/start-onboarding")
    public ResponseEntity startOnBoarding(
            @RequestPart(value = "gstCertificate", required = false) MultipartFile gstCertificate,
            @RequestPart(value = "panCard") MultipartFile panCard,
            @RequestPart(value = "companyRegistrationDocument") MultipartFile companyRegistrationDoc,
            @RequestPart(value = "companyLogo") MultipartFile companyLogo,
            @RequestPart(value = "companyInfo") String companyDetails
            ){
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
}
