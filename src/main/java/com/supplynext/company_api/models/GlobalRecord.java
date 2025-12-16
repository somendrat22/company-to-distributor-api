package com.supplynext.company_api.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "global_records")
public class GlobalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID sysId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne
    private User createdBy;
    @ManyToMany
    private List<User> updatedBy;
}
