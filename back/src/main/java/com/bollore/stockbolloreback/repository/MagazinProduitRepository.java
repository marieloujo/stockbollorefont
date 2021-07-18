package com.bollore.stockbolloreback.repository;


import com.bollore.stockbolloreback.models.MagazinProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the MagazinProduit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MagazinProduitRepository extends JpaRepository<MagazinProduit, Long> {
    /**
     * Find by magazin id list.
     *
     * @param id the id
     * @return the list
     */
    List<MagazinProduit> findByMagazin_Id(Long id);

    /**
     * Find by produit id list.
     *
     * @param id the id
     * @return the list
     */
    List<MagazinProduit> findByProduit_Id(Long id);

    /**
     * Find all by order by created date desc list.
     *
     * @return the list
     */
    List<MagazinProduit> findAllByOrderByCreatedDateDesc();
}
