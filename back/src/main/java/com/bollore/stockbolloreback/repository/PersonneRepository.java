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
    /**
     * Find by service b id list.
     *
     * @param id the id
     * @return the list
     */
    List<Personne> findByServiceB_Id(Long id);

    /**
     * Find by profil id list.
     *
     * @param id the id
     * @return the list
     */
    List<Personne> findByProfil_Id(Long id);

    /**
     * Find by username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<Personne> findByUsername(String username);

    /**
     * Exists by username boolean.
     *
     * @param username the username
     * @return the boolean
     */
    Boolean existsByUsername(String username);

    /**
     * Exists by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    Boolean existsByEmail(String email);

    /**
     * Find all by order by created date desc list.
     *
     * @return the list
     */
    List<Personne> findAllByOrderByCreatedDateDesc();

}
