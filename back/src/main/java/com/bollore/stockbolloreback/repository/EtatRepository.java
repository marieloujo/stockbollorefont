package com.bollore.stockbolloreback.repository;

import com.bollore.stockbolloreback.models.Etat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the Etat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtatRepository extends JpaRepository<Etat, Long> {
    /**
     * Find by code etat.
     *
     * @param code the code
     * @return the etat
     */
    Etat findByCode(String code);

    /**
     * Find all by order by created date desc list.
     *
     * @return the list
     */
    List<Etat> findAllByOrderByCreatedDateDesc();
}
