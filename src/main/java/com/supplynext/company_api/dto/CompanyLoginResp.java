package com.supplynext.company_api.dto;

import lombok.Data;

@Data
public class CompanyLoginResp {
    private String companyId; //COMP-0111
    private String companyName;
    private String legalName;
    private String companyType;
    // Address
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String state;
    private String country;
    private int pincode;
    private String geoLatitude;
    private String geoLongitude;
    private String companyLogoUrl;
}
