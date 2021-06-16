package com.bollore.stockbolloreback.repository;

import com.bollore.stockbolloreback.models.Produit;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the Produit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    /*List<Produit> findByMagazin_Id(Long magazin_id);*/

    List<Produit> findByMarque_Id(Long marque_id);

    List<Produit> findByGamme_Id(Long gamme_id);

    List<Produit> findByModele_Id(Long modele_id);

    Optional<Produit> findById(Long produit_id);

}
