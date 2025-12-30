package com.supplynext.company_api.services;

import com.supplynext.company_api.dto.CompanyOnboardingRequestDto;
import com.supplynext.company_api.dto.CreateRoleRequestDto;
import com.supplynext.company_api.dto.InviteEmployeeDto;
import com.supplynext.company_api.models.*;
import com.supplynext.company_api.repositories.CompanyRepository;
import com.supplynext.company_api.utilities.CommonUtility;
import com.supplynext.company_api.utilities.MappingUtility;
import io.imagekit.sdk.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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
    @Autowired
    RoleService roleService;
    @Autowired
    MailService mailService;

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
        List<Document> documents = new ArrayList<>();
        Document gCertificate = documentService.uploadDocument(gstCertificate, "gstCertificate", "gst-certificate", folderName);
        documents.add(gCertificate);
        Document pCard = documentService.uploadDocument(panCard, "panCard", "pan-card", folderName);
        documents.add(pCard);
        Document cinDoc = documentService.uploadDocument(companyRegistrationDoc, "companyRegistrationDoc", "company-registration-doc", folderName);
        documents.add(cinDoc);
        Document imageDoc = documentService.uploadDocument(companyLogo, "companyLogo", "company-logo", folderName);
        documents.add(imageDoc);
        company.setDocumentList(documents);
        company.setCompanyLogoUrl(imageDoc.getDocumentUrl());
        this.save(company);
        // Mail the company admin that your account registered succes fully
    }


    public List<Role> getCompanyRoleByUser(User user){
        // Before getting company roles -> we need to get company details
        // We need to indetify the user company
        Company company = companyEmployeeService.getEmployeeCompanyDetails(user.getSysId());
        // We got the company we need to get the roles of the company
        // CompanyService will call RoleService to get roles by company
        return roleService.getRolesByCompanyName(company.getLegalName());
    }

    public Role createRoleForCompanyByUserSession(User user, CreateRoleRequestDto createRoleRequestDto){
        Company company = companyEmployeeService.getEmployeeCompanyDetails(user.getSysId());
        // Role Service
        return roleService.createRoleForCompany(company.getLegalName(),
                createRoleRequestDto,
                user
                );
    }

    public CompanyEmployee inviteEmployeeToInviterOrg(
            User inviter,
            InviteEmployeeDto inviteEmployeeDto
    ) {
        log.info("Starting employee invitation process. InviterSysId={}", inviter.getSysId());
        log.debug("InviteEmployeeDto received: {}", inviteEmployeeDto);

        // Fetch company details of inviter
        log.info("Fetching company details for inviterSysId={}", inviter.getSysId());
        Company company =
                companyEmployeeService.getEmployeeCompanyDetails(inviter.getSysId());

        log.info("Company details fetched successfully. CompanyId={}", company.getSysId());

        // Create employee record for company
        log.info("Creating company employee record for CompanyId={}", company.getSysId());
        CompanyEmployee companyEmployee =
                companyEmployeeService.createEmployeeForCompany(
                        company,
                        inviter,
                        inviteEmployeeDto
                );

        log.info("Company employee created successfully. CompanyEmployeeId={}",
                companyEmployee.getSysId());

        // Send invitation email
        log.info("Sending invitation email to employee. CompanyEmployeeId={}",
                companyEmployee.getSysId());
        mailService.sendInviteEmployeeMail(companyEmployee, inviter);

        log.info("Invitation email sent successfully. CompanyEmployeeId={}",
                companyEmployee.getSysId());

        return companyEmployee;
    }


    public Company save(Company company){
        return companyRepository.save(company);
    }
}
