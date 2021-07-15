package com.bollore.stockbolloreback.repository;


import com.bollore.stockbolloreback.enumeration.EnumDemandeStatus;
import com.bollore.stockbolloreback.enumeration.EnumProduitStatus;
import com.bollore.stockbolloreback.models.DemandeProduit;
import com.bollore.stockbolloreback.models.MagazinProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

/**
 * Spring Data SQL repository for the DemandeProduit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DemandeProduitRepository extends JpaRepository<DemandeProduit, Long> {
    List<DemandeProduit> findByDemande_Id(Long id);

    List<DemandeProduit> findAllByOrderByCreatedDateDesc();

    List<DemandeProduit> findTop10ByOrderByCreatedDateDesc();

    List<DemandeProduit> findAllByLivrerIsFalseOrderByCreatedDateDesc();

    List<DemandeProduit> findByProduit_Id(Long id);

    List<DemandeProduit> findAllByCreatedByIsBetween(Instant datesaisine1, Instant datesaisine2);

    List<DemandeProduit> findAllByDemande_DateHeureIsBetween(Instant datedemande1, Instant datedemande2);

    List<DemandeProduit> findByDateDemandeRetourIsNull();

    List<DemandeProduit> findByStatusAndDateDemandeRetourIsNull(EnumDemandeStatus status);

    List<DemandeProduit> findByStatusInAndDateDemandeRetourIsNull(List<EnumDemandeStatus> statusList);

    List<DemandeProduit> findByStatusInOrderByCreatedDateDesc(List<EnumDemandeStatus> statusList);

    List<DemandeProduit> findByStatusInOrProduitStatusInOrderByCreatedDateDesc(List<EnumDemandeStatus> statusList, List<EnumProduitStatus> enumProduitStatusList);

    List<DemandeProduit> findByStatusNotInOrderByCreatedDateDesc(List<EnumDemandeStatus> statusList);

}
