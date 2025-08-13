package com.gescli.ProgrammationTravaux.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pv_fin_travaux")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PVFinTravaux {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPV;

    @Column(name = "date_pv", nullable = false)
    private LocalDate datePv;

    @Column(name = "statut", length = 50)
    private String statut;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_travail")
    private Travail travail;

    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_derniere_maj")
    private LocalDateTime dateDerniereMaj;

    @PrePersist
    protected void onCreate() {
        this.dateCreation = LocalDateTime.now();
        this.dateDerniereMaj = LocalDateTime.now();
        if (this.statut == null || this.statut.isEmpty()) {
            this.statut = "EMIS";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.dateDerniereMaj = LocalDateTime.now();
    }
}