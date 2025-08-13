package com.gescli.ProgrammationTravaux.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "materiels")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Materiel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMateriel;

    @Column(name = "nom_materiel", nullable = false, unique = true, length = 255)
    private String nomMateriel;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "quantite_en_stock", nullable = false)
    private Integer quantiteEnStock;

    @ManyToMany(mappedBy = "materiels")
    private Set<DemandeDeMateriel> demandesDeMateriel = new HashSet<>();

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
