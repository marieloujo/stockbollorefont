package com.bollore.stockbolloreback.repository;

import com.bollore.stockbolloreback.models.Produit;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The interface Produit repository.
 */
@SuppressWarnings("unused")
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    /*List<Produit> findByMagazin_Id(Long magazin_id);*/

    /**
     * Find by marque id list.
     *
     * @param marque_id the marque id
     * @return the list
     */
    List<Produit> findByMarque_Id(Long marque_id);

    /**
     * Find by gamme id list.
     *
     * @param gamme_id the gamme id
     * @return the list
     */
    List<Produit> findByGamme_Id(Long gamme_id);

    /**
     * Find by modele id list.
     *
     * @param modele_id the modele id
     * @return the list
     */
    List<Produit> findByModele_Id(Long modele_id);

    Optional<Produit> findById(Long produit_id);

    /**
     * Find all by order by created date desc list.
     *
     * @return the list
     */
    List<Produit> findAllByOrderByCreatedDateDesc();

}
