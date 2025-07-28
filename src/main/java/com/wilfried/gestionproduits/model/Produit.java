package com.wilfried.gestionproduits.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Getter
@Setter
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private double prix;

    @OneToOne(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true)
    private DetailProduit detailProduit;

    @ManyToMany
    @JoinTable(
        name = "produit_categorie",
        joinColumns = @JoinColumn(name = "produit_id"),
        inverseJoinColumns = @JoinColumn(name = "categorie_id")
    )
    private Set<Categorie> categories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;
}
