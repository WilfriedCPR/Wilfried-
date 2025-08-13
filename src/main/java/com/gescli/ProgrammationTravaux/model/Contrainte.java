package com.gescli.ProgrammationTravaux.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "contraintes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contrainte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrainte;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "statut", length = 50)
    private String statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_travail", nullable = false)
    private Travail travail;

    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @PrePersist
    protected void onCreate() {
        this.dateCreation = LocalDateTime.now();
        if (this.statut == null || this.statut.isEmpty()) {
            this.statut = "A_RESOUDRE";
        }
    }
}