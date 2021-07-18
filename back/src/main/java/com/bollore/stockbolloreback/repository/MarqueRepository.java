package com.bollore.stockbolloreback.repository;

import com.bollore.stockbolloreback.models.Marque;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the Marque entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MarqueRepository extends JpaRepository<Marque, Long> {

    /**
     * Find all by order by created date desc list.
     *
     * @return the list
     */
    List<Marque> findAllByOrderByCreatedDateDesc();
}
