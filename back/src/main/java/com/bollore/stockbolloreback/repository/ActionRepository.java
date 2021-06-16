package com.bollore.stockbolloreback.repository;

import com.bollore.stockbolloreback.models.ActionB;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ActionB entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActionRepository extends JpaRepository<ActionB, Long> {}
