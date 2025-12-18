package com.supplynext.company_api.utilities;

import com.supplynext.company_api.dto.CompanyOnboardingRequestDto;
import com.supplynext.company_api.models.Company;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MappingUtility {
    public Company mapCompanyDetailsToCompany(CompanyOnboardingRequestDto companyDetails) {

        Company company = new Company();

        // Primary Key
        company.setCompanyId(CommonUtility.generateCompanyId("COMP"));

        // Basic Info
        company.setCompanyName(companyDetails.getCompanyName());
        company.setLegalName(companyDetails.getLegalName());
        company.setCompanyType(companyDetails.getCompanyType());

        // Business & Compliance
        company.setGstNumber(companyDetails.getGstNumber());
        company.setPanNumber(companyDetails.getPanNumber());
        company.setCinNumber(companyDetails.getCinNumber());

        // Address
        company.setAddressLine1(companyDetails.getAddressLine1());
        company.setAddressLine2(companyDetails.getAddressLine2());
        company.setAddressLine3(companyDetails.getAddressLine3());
        company.setCity(companyDetails.getCity());
        company.setState(companyDetails.getState());
        company.setCountry(companyDetails.getCountry());
        company.setPincode(companyDetails.getPincode());
        company.setGeoLatitude(companyDetails.getGeoLatitude());
        company.setGeoLongitude(companyDetails.getGeoLongitude());

        // Contact Info
        company.setSupportEmail(companyDetails.getSupportEmail());
        company.setSupportPhoneNumber(companyDetails.getSupportPhoneNumber());

        // Finance & Banking
        company.setBankAccountNumber(companyDetails.getBankAccountNumber());
        company.setBankName(companyDetails.getBankName());
        company.setIfscCode(companyDetails.getIfscCode());
        company.setCreditLimitForDistributors(companyDetails.getCreditLimitForDistributors());

        // System Defaults
        company.setKycCompleted(false);
        company.setStatus("PENDING");

        company.setCreatedAt(LocalDateTime.now());
        company.setUpdatedAt(LocalDateTime.now());

        return company;
    }

}
