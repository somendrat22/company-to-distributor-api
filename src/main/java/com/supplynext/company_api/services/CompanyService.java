package com.supplynext.company_api.services;

import com.supplynext.company_api.dto.CompanyOnboardingRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CompanyService {

    public void startOnboarding(
           MultipartFile gstCertificate,
           MultipartFile panCard,
           MultipartFile companyRegistrationDoc,
           MultipartFile companyLogo,
           CompanyOnboardingRequestDto companyDetails
    ){
        // How we can upload all these files ?
        // We need to upload it on imageKit
        // How we can upload it on imageKit ?
    }
}
