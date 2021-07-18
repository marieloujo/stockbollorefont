package com.bollore.stockbolloreback.repository;

import com.bollore.stockbolloreback.models.Magazin;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the Magazin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MagazinRepository extends JpaRepository<Magazin, Long> {

    /**
     * Find all by order by created date desc list.
     *
     * @return the list
     */
    List<Magazin> findAllByOrderByCreatedDateDesc();
}
