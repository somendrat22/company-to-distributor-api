package com.supplynext.company_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "companies")
@Inheritance(strategy = InheritanceType.JOINED)
public class Company extends GlobalRecord{
    private String companyId; //COMP-0111
    private String companyName;
    private String legalName;
    // Business & Compliance
    private String gstNumber;
    private String panNumber;
    private String cinNumber;
    private String companyType;
    private String companyLogoUrl;
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
    private boolean isKycCompleted;
    // Contact Info
    private String supportEmail;
    private String supportPhoneNumber;
    // Finance & banking details
    private String bankAccountNumber;
    private String bankName;
    private String ifscCode;
    private String creditLimitForDistributors;
    @OneToMany
    List<Document> documentList;
    private String status;
}

