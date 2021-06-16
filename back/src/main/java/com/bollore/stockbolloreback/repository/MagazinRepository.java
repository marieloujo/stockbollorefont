package com.bollore.stockbolloreback.repository;

import com.bollore.stockbolloreback.models.Magazin;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Magazin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MagazinRepository extends JpaRepository<Magazin, Long> {}
