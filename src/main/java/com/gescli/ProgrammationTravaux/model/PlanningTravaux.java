package com.gescli.ProgrammationTravaux.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "plannings_travaux")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanningTravaux {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlanning;

    @Column(name = "nom_planning", length = 200, nullable = false)
    private String nomPlanning;

    @Column(name = "date_debut_prevue", nullable = false)
    private LocalDate dateDebutPrevue;

    @Column(name = "date_fin_prevue", nullable = false)
    private LocalDate dateFinPrevue;

    @Column(name = "statut", nullable = false, length = 50)
    private String statut;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipe")
    private EquipeTravaux equipeTravaux;

    @OneToMany(mappedBy = "planningTravaux", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Travail> travaux = new HashSet<>();

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