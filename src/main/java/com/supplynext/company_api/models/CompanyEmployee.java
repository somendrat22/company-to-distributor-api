package com.supplynext.company_api.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "company_employees")
public class CompanyEmployee extends User{
    private String companyEmployeeId;
    @ManyToOne
    private Company company;
}
