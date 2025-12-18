package com.supplynext.company_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyOnboardingRequestDto {

    private String name;


    private String gstNumber;


    private String panNumber;


    private String address;


    private String contactPersonName;


    private String contactEmail;

    private  String contactPhone;


    private String country;


    private String password;


    private String industryCategory;


    private String businessType;


    private Integer yearOfEstablishment;


    private Integer numberOfEmployees;


    private String bankAccountNumber;


    private String ifscOrSwift;


    private String bankName;

    private String branchAddress;

    private String shippingCapabilities;

    private String paymentTerms;

    private String annualRevenueRange;
}
