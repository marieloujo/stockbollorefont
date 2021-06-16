package com.bollore.stockbolloreback.repository;

import com.bollore.stockbolloreback.models.Personne;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the Personne entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {
    List<Personne> findByServiceB_Id(Long id);

    List<Personne> findByProfil_Id(Long id);

    Optional<Personne> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
