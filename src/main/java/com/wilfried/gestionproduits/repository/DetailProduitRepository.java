package com.wilfried.gestionproduits.repository;

import com.wilfried.gestionproduits.model.DetailProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailProduitRepository extends JpaRepository<DetailProduit, Long> {
}