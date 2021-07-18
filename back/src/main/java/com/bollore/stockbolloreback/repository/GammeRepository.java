package com.bollore.stockbolloreback.repository;

import com.bollore.stockbolloreback.models.Gamme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Gamme repository.
 */
public interface GammeRepository extends JpaRepository<Gamme, Long> {

    /**
     * Find all by order by created date desc list.
     *
     * @return the list
     */
    List<Gamme> findAllByOrderByCreatedDateDesc();
}
