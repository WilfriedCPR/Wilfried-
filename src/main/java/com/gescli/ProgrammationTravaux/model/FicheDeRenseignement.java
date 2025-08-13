package com.gescli.ProgrammationTravaux.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "fiches_renseignement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FicheDeRenseignement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFiche;

    @Column(name = "date_creation", nullable = false)
    private LocalDate dateCreation;

    @Column(name = "observations_terrain", columnDefinition = "TEXT")
    private String observationsTerrain;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_travail", unique = true, nullable = false)
    private Travail travail;

    @Column(name = "date_derniere_maj")
    private LocalDateTime dateDerniereMaj;

    @PrePersist
    protected void onCreate() {
        this.dateCreation = LocalDate.now();
        this.dateDerniereMaj = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dateDerniereMaj = LocalDateTime.now();
    }
}