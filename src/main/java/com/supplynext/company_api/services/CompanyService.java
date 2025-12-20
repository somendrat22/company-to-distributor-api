package com.supplynext.company_api.services;

import com.supplynext.company_api.dto.CompanyOnboardingRequestDto;
import com.supplynext.company_api.models.Company;
import com.supplynext.company_api.models.CompanyEmployee;
import com.supplynext.company_api.repositories.CompanyRepository;
import com.supplynext.company_api.utilities.CommonUtility;
import com.supplynext.company_api.utilities.MappingUtility;
import io.imagekit.sdk.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class CompanyService {

    @Autowired
    DocumentService documentService;
    @Autowired
    MappingUtility mappingUtility;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CompanyEmployeeService companyEmployeeService;

    public void startOnboarding(
           MultipartFile gstCertificate,
           MultipartFile panCard,
           MultipartFile companyRegistrationDoc,
           MultipartFile companyLogo,
           CompanyOnboardingRequestDto companyDetails
    ) throws ForbiddenException, TooManyRequestsException, InternalServerException, UnauthorizedException, BadRequestException, UnknownException, IOException {

        // 1. We need to create company record
        // 2. We need to create company admin accounr
        // 3. If we are creating company admin account then we need to create role and operation as well
        // 4. We send mail to company admin
        Company company = mappingUtility.mapCompanyDetailsToCompany(companyDetails);
        company = this.save(company);
        CompanyEmployee admin = companyEmployeeService.createFirstAdminAccount(company);
        String folderName = "company/" + company.getCompanyId();
        // Admin Account for the company
        documentService.uploadDocument(gstCertificate, "gstCertificate", "gst-certificate", folderName);
        documentService.uploadDocument(panCard, "panCard", "pan-card", folderName);
        documentService.uploadDocument(panCard, "companyRegistrationDoc", "company-registration-doc", folderName);
        documentService.uploadDocument(panCard, "companyLogo", "company-logo", folderName);
        // Mail the company admin that your account registered succes fully
    }



    public Company save(Company company){
        return companyRepository.save(company);
    }
}
