package com.gescli.ProgrammationTravaux.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pv_reception_travaux")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PVReceptionTravaux {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPvReception;

    @Column(name = "date_pv", nullable = false)
    private LocalDate datePv;

    @Column(name = "observations", columnDefinition = "TEXT")
    private String observations;

    @Column(name = "statut_pv", length = 50)
    private String statutPv;

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