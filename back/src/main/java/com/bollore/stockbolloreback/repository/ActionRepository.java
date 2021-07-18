package com.bollore.stockbolloreback.repository;

import com.bollore.stockbolloreback.models.ActionB;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the ActionB entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActionRepository extends JpaRepository<ActionB, Long> {

    /**
     * Find all by order by created date desc list.
     *
     * @return the list
     */
    List<ActionB> findAllByOrderByCreatedDateDesc();
}
