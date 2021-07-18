package com.bollore.stockbolloreback.repository;

import com.bollore.stockbolloreback.models.StatusProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the Etat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatusProduitRepository extends JpaRepository<StatusProduit, Long> {
    /**
     * Find all by order by created date desc list.
     *
     * @return the list
     */
    List<StatusProduit> findAllByOrderByCreatedDateDesc();
}
