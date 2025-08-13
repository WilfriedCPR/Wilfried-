package com.gescli.ProgrammationTravaux.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "travaux")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Travail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTravail;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "date_debut_prevue", nullable = false)
    private LocalDate dateDebutPrevue;

    @Column(name = "date_fin_prevue", nullable = false)
    private LocalDate dateFinPrevue;

    @Column(name = "statut_travaux", nullable = false, length = 50)
    private String statutTravaux;

    @Column(name = "rapport_execution", columnDefinition = "TEXT")
    private String rapportExecution;

    @Column(name = "type_branchement", length = 100)
    private String typeBranchement;

    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_derniere_maj")
    private LocalDateTime dateDerniereMaj;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_planning", nullable = false)
    private PlanningTravaux planningTravaux;

    @OneToMany(mappedBy = "travail", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Contrainte> contraintes = new HashSet<>();

    @OneToOne(mappedBy = "travail", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private PVFinTravaux pvFinTravaux;

    @OneToOne(mappedBy = "travail", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private FicheDeRenseignement ficheRenseignement;

    @OneToOne(mappedBy = "travail", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private BMS bms;

    @OneToOne(mappedBy = "travail", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private PVReceptionTravaux pvReceptionTravaux;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "id_demande_materiel", referencedColumnName = "idDemandeMateriel")
    private DemandeDeMateriel demandeMateriel;

    @PrePersist
    protected void onCreate() {
        this.dateCreation = LocalDateTime.now();
        this.dateDerniereMaj = LocalDateTime.now();
        if (this.statutTravaux == null || this.statutTravaux.isEmpty()) {
            this.statutTravaux = "PLANIFIE";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.dateDerniereMaj = LocalDateTime.now();
    }
}
