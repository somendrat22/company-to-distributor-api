package com.supplynext.company_api.services;

import com.supplynext.company_api.dto.InviteEmployeeDto;
import com.supplynext.company_api.enums.UserStatus;
import com.supplynext.company_api.models.Company;
import com.supplynext.company_api.models.CompanyEmployee;
import com.supplynext.company_api.models.Role;
import com.supplynext.company_api.models.User;
import com.supplynext.company_api.repositories.CompanyEmployeeRepository;
import com.supplynext.company_api.repositories.CompanyRepository;
import com.supplynext.company_api.utilities.CommonUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CompanyEmployeeService {

    @Autowired
    CompanyEmployeeRepository companyEmployeeRepository;
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;

    public CompanyEmployee save(CompanyEmployee companyEmployee){
        return  companyEmployeeRepository.save(companyEmployee);
    }

    public CompanyEmployee createFirstAdminAccount(Company company){
        User botUser = userService.getBotUser();
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
        Role role = roleService.createFirstAdminRoleForCompany(company, botUser);
        companyEmployee.setRoles(List.of(role));
        // We need to create the role for the company admin
        return this.save(companyEmployee);
    }

   public CompanyEmployee createEmployeeForCompany(
           Company company,
           User inviter,
           InviteEmployeeDto inviteEmployeeDto
   ){

        CompanyEmployee companyEmployee = new CompanyEmployee();
        companyEmployee.setEmail(inviteEmployeeDto.getEmail());
        companyEmployee.setPassword(CommonUtility.generateRandomPassword(15));
        companyEmployee.setFullName(inviteEmployeeDto.getFullName());
        companyEmployee.setPincode(inviteEmployeeDto.getPincode());
        companyEmployee.setAddressLine1(inviteEmployeeDto.getAddressLine1());
        companyEmployee.setAddressLine2(inviter.getAddressLine2());
        companyEmployee.setAddressLine3(inviter.getAddressLine3());
        companyEmployee.setCompany(company);
        companyEmployee.setCreatedAt(LocalDateTime.now());
        companyEmployee.setCompanyEmployeeId(CommonUtility.COMPANY_EMPLOYEE_ENTITY_NAME);
        companyEmployee.setPhoneNumber(inviteEmployeeDto.getPhoneNumber());
        companyEmployee.setStatus(UserStatus.INVITED.toString());
        companyEmployee.setUpdatedAt(LocalDateTime.now());
        companyEmployee.setRoles(roleService.fetchAllRolesBySysId(inviteEmployeeDto.getRoles()));
        return this.save(companyEmployee);

   }

    public Company getEmployeeCompanyDetails(UUID userSysId){
        CompanyEmployee companyEmployee = companyEmployeeRepository.findById(userSysId).orElse(null);
        return companyEmployee.getCompany();
    }
}
