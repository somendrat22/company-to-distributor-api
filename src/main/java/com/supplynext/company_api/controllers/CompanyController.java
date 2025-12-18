package com.supplynext.company_api.controllers;

import com.supplynext.company_api.dto.CompanyOnboardingRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import javax.management.ObjectName;

@RestController
@RequestMapping("/c2d/api/v1/company")
public class CompanyController {

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
            CompanyOnboardingRequestDto companyOnboardingRequestDto =  objectMapper.readValue(companyDetails, CompanyOnboardingRequestDto.class);
            return new ResponseEntity<>("Hello", HttpStatus.ACCEPTED);
    }
}
