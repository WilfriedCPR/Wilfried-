package com.gescli.ProgrammationTravaux.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bulletins_mise_en_service")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BMS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBMS;

    @Column(name = "date_emission", nullable = false)
    private LocalDate dateEmission;

    @Column(name = "date_mise_en_service", nullable = false)
    private LocalDate dateMiseEnService;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_travail", unique = true, nullable = false)
    private Travail travail;

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