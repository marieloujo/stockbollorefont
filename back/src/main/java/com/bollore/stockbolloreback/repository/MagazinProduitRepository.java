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
    List<MagazinProduit> findByMagazin_Id(Long id);

    List<MagazinProduit> findByProduit_Id(Long id);
}
