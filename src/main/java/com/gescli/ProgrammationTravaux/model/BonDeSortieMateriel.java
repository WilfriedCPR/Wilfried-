package com.gescli.ProgrammationTravaux.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bons_de_sortie_materiel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonDeSortieMateriel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBonSortie;

    @Column(name = "date_emission", nullable = false)
    private LocalDate dateEmission;

    @Column(name = "quantite_demandee", nullable = false)
    private Integer quantiteDemandee;

    @Column(name = "statut", nullable = false, length = 50)
    private String statut;

    @Column(name = "signature", length = 255)
    private String signature;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_demande_materiel", nullable = false)
    private DemandeDeMateriel demandeMateriel;

    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_derniere_maj")
    private LocalDateTime dateDerniereMaj;

    @PrePersist
    protected void onCreate() {
        this.dateCreation = LocalDateTime.now();
        this.dateDerniereMaj = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dateDerniereMaj = LocalDateTime.now();
    }
}