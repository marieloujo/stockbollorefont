package com.bollore.stockbolloreback.repository;

import com.bollore.stockbolloreback.models.Profil;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the Profil entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfilRepository extends JpaRepository<Profil, Long> {

    /**
     * Find all by order by created date desc list.
     *
     * @return the list
     */
    List<Profil> findAllByOrderByCreatedDateDesc();
}
