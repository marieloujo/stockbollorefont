package com.bollore.stockbolloreback.repository;

import com.bollore.stockbolloreback.models.Etat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data SQL repository for the Etat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtatRepository extends JpaRepository<Etat, Long> {
    Etat findByCode(String code);
}
