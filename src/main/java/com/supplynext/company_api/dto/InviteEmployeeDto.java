package com.supplynext.company_api.dto;

import com.supplynext.company_api.models.Role;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InviteEmployeeDto {
    private String fullName;
    private String email;
    private String status;
    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    List<UUID> roles;
    private int pincode;
}
