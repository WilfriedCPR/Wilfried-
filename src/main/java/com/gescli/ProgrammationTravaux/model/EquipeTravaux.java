package com.gescli.ProgrammationTravaux.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "equipes_travaux")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipeTravaux {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipe;

    @Column(name = "nom_equipe", nullable = false, unique = true, length = 100)
    private String nomEquipe;

    @Column(name = "localisation", length = 100)
    private String localisation;

    @OneToOne(mappedBy = "equipeTravaux", fetch = FetchType.LAZY)
    private PlanningTravaux planningTravaux;

    @OneToMany(mappedBy = "equipeTravaux", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Agent> agents = new HashSet<>();

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
