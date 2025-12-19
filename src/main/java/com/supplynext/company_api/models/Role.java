package com.supplynext.company_api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@Entity
public class Role extends GlobalRecord{
    // sales_manager
    @Column(unique = true, nullable = false)
    String roleId;
    String roleName;
    @ManyToMany
    List<Operation> operations;
}
