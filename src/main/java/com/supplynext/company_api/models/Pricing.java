package com.supplynext.company_api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "pricing")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Pricing extends GlobalRecord{
    String priceId;// Pricing-002
    @ManyToOne
    Product product;
    Double basePrice;
    Double tradePrice;
    Double purchasePrice;
    String currency;
    LocalDate effectiveFrom;
    LocalDate effectiveTo;
    String regionCode;
    String priceType;
    String discountType;
    double discountPercentage;
    boolean isGstIncluded;
}
