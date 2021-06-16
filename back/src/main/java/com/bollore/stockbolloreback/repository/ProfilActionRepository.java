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
    List<ProfilAction> findByProfil_Id(Long id);

    List<ProfilAction> findByActionB_Id(Long id);
}
