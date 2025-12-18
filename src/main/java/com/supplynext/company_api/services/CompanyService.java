package com.supplynext.company_api.services;

import com.supplynext.company_api.dto.CompanyOnboardingRequestDto;
import com.supplynext.company_api.models.Company;
import com.supplynext.company_api.utilities.MappingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CompanyService {

    @Autowired
    DocumentService documentService;
    @Autowired
    MappingUtility mappingUtility;

    public void startOnboarding(
           MultipartFile gstCertificate,
           MultipartFile panCard,
           MultipartFile companyRegistrationDoc,
           MultipartFile companyLogo,
           CompanyOnboardingRequestDto companyDetails
    ){

        // 1. We need to create company record
        // 2. We need to create company admin accounr
        // 3. If we are creating company admin account then we need to create role and operation as well
        // 4. We send mail to company admin
        Company company = mappingUtility.mapCompanyDetailsToCompany(companyDetails);

        documentService.uploadDocument(gstCertificate, "gstCertificate", "gst-certificate", "company");
    }
}
