package com.bollore.stockbolloreback.repository;

import com.bollore.stockbolloreback.models.ProfilAction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the ProfilAction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfilActionRepository extends JpaRepository<ProfilAction, Long> {
    /**
     * Find by profil id list.
     *
     * @param id the id
     * @return the list
     */
    List<ProfilAction> findByProfil_Id(Long id);

    /**
     * Find by action b id list.
     *
     * @param id the id
     * @return the list
     */
    List<ProfilAction> findByActionB_Id(Long id);

    /**
     * Find all by order by created date desc list.
     *
     * @return the list
     */
    List<ProfilAction> findAllByOrderByCreatedDateDesc();
}
