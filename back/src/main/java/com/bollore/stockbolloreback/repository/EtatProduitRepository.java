package com.bollore.stockbolloreback.repository;

import com.bollore.stockbolloreback.models.EtatProduit;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the EtatProduit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtatProduitRepository extends JpaRepository<EtatProduit, Long> {
    /**
     * Find by etat id list.
     *
     * @param id the id
     * @return the list
     */
    List<EtatProduit> findByEtat_Id(Long id);

    /**
     * Find by produit id list.
     *
     * @param id the id
     * @return the list
     */
    List<EtatProduit> findByProduit_Id(Long id);

    /**
     * Find all by order by created date desc list.
     *
     * @return the list
     */
    List<EtatProduit> findAllByOrderByCreatedDateDesc();
}
