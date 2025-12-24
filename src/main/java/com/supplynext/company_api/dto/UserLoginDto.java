package com.supplynext.company_api.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserLoginDto {
    String email;
    String password;
}
