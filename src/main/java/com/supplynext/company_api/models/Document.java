package com.supplynext.company_api.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "documents")
public class Document extends GlobalRecord{
    String documentName;
    String documentOriginalName;
    String documentType;
    String documentUrl;
}
