package com.bollore.stockbolloreback.repository;

import com.bollore.stockbolloreback.models.Demande;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the Demande entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {

    /**
     * Find all by order by created date desc list.
     *
     * @return the list
     */
    List<Demande> findAllByOrderByCreatedDateDesc();
}
