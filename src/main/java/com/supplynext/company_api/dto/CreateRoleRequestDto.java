package com.supplynext.company_api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleRequestDto {
    String roleName;
    List<UUID> operationsSysId;
}
