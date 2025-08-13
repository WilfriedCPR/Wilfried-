package com.gescli.ProgrammationTravaux.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "demandes_materiel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeDeMateriel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDemandeMateriel;

    @Column(name = "date_demande", nullable = false)
    private LocalDateTime dateDemande;

    @Column(name = "statut_demande", nullable = false, length = 50)
    private String statutDemande;

    @OneToMany(mappedBy = "demandeMateriel", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BonDeSortieMateriel> bonsDeSortie = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "demande_materiel_detail",
        joinColumns = @JoinColumn(name = "id_demande_materiel"),
        inverseJoinColumns = @JoinColumn(name = "id_materiel")
    )
    private Set<Materiel> materiels = new HashSet<>();

    @OneToOne(mappedBy = "demandeMateriel")
    private Travail travail;

    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_derniere_maj")
    private LocalDateTime dateDerniereMaj;

    @PrePersist
    protected void onCreate() {
        this.dateCreation = LocalDateTime.now();
        this.dateDerniereMaj = LocalDateTime.now();
        if (this.dateDemande == null) {
            this.dateDemande = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.dateDerniereMaj = LocalDateTime.now();
    }
}