package com.supplynext.company_api.models;

import com.supplynext.company_api.CompanyApiApplication;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "products")
@Entity
public class Product extends GlobalRecord{
    private String productId;
    @ManyToOne
    Company manufacturerCompany;
    @Column(unique = true)
    String skuCode;
    String brandName;
    String category;
    String status;
    String packType;
    String packSize;
    String unitsPerCase;
    String uom;
    String weightPerUnit;
    String shelfLifeInDays;
    String hsnCode;
    Double taxRate;
    boolean isReturnable;
    String description;
    @OneToMany
    List<Document> productImages;
}
