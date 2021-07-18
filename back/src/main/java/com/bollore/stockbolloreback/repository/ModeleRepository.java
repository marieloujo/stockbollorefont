package com.bollore.stockbolloreback.repository;

import com.bollore.stockbolloreback.models.Modele;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the Modele entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModeleRepository extends JpaRepository<Modele, Long> {

    /**
     * Find all by order by created date desc list.
     *
     * @return the list
     */
    List<Modele> findAllByOrderByCreatedDateDesc();
}
