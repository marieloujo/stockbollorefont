package com.bollore.stockbolloreback.repository;

import com.bollore.stockbolloreback.models.StatusProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Etat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatusProduitRepository extends JpaRepository<StatusProduit, Long> {
}
