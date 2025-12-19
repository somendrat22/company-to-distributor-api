package com.supplynext.company_api.services;

import com.supplynext.company_api.models.Company;
import com.supplynext.company_api.models.CompanyEmployee;
import com.supplynext.company_api.repositories.CompanyEmployeeRepository;
import com.supplynext.company_api.repositories.CompanyRepository;
import com.supplynext.company_api.utilities.CommonUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CompanyEmployeeService {

    @Autowired
    CompanyEmployeeRepository companyEmployeeRepository;

    public CompanyEmployee save(CompanyEmployee companyEmployee){
        return  companyEmployeeRepository.save(companyEmployee);
    }

    public CompanyEmployee createFirstAdminAccount(Company company){
        CompanyEmployee companyEmployee = new CompanyEmployee();
        companyEmployee.setCompany(company);
        companyEmployee.setCompanyEmployeeId(CommonUtility.generateIdForEntity("COMP-EMP"));
        companyEmployee.setEmail(company.getSupportEmail());
        companyEmployee.setPassword(CommonUtility.generateRandomPassword(15));
        companyEmployee.setCreatedAt(LocalDateTime.now());
        companyEmployee.setUpdatedAt(LocalDateTime.now());
        companyEmployee.setAddressLine1(company.getAddressLine1());
        companyEmployee.setAddressLine2(company.getAddressLine2());
        companyEmployee.setAddressLine3(company.getAddressLine3());
        companyEmployee.setFullName(company.getCompanyName() + " " + "Admin");
        companyEmployee.setPhoneNumber(company.getSupportPhoneNumber());
        companyEmployee.setPincode(company.getPincode());
        // Add Role Functionality
        return this.save(companyEmployee);
    }
}
