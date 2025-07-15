package com.wilfried.gestionproduits.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String fabricant;

    @OneToOne(mappedBy = "detailProduit")
    private Produit produit;
}