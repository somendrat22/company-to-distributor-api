package com.supplynext.company_api.dto;

import com.supplynext.company_api.models.Document;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyOnboardingRequestDto {
    private String companyName;
    private String legalName;
    private String gstNumber;
    private String panNumber;
    private String cinNumber;
    private String companyType;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String state;
    private String country;
    private int pincode;
    private String geoLatitude;
    private String geoLongitude;
    private String supportEmail;
    private String supportPhoneNumber;
    private String bankAccountNumber;
    private String bankName;
    private String ifscCode;
    private String creditLimitForDistributors;
}
