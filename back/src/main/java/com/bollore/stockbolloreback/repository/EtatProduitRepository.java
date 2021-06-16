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
    List<EtatProduit> findByEtat_Id(Long id);

    List<EtatProduit> findByProduit_Id(Long id);
}
