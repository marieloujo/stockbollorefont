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
    /**
     * Find by demande id list.
     *
     * @param id the id
     * @return the list
     */
    List<DemandeProduit> findByDemande_Id(Long id);

    /**
     * Find all by order by created date desc list.
     *
     * @return the list
     */
    List<DemandeProduit> findAllByOrderByCreatedDateDesc();

    /**
     * Find top 10 by order by created date desc list.
     *
     * @return the list
     */
    List<DemandeProduit> findTop10ByOrderByCreatedDateDesc();

    /**
     * Find all by livrer is false order by created date desc list.
     *
     * @return the list
     */
    List<DemandeProduit> findAllByLivrerIsFalseOrderByCreatedDateDesc();

    /**
     * Find by produit id list.
     *
     * @param id the id
     * @return the list
     */
    List<DemandeProduit> findByProduit_Id(Long id);

    /**
     * Find all by created by is between list.
     *
     * @param datesaisine1 the datesaisine 1
     * @param datesaisine2 the datesaisine 2
     * @return the list
     */
    List<DemandeProduit> findAllByCreatedByIsBetween(Instant datesaisine1, Instant datesaisine2);

    /**
     * Find all by demande date heure is between list.
     *
     * @param datedemande1 the datedemande 1
     * @param datedemande2 the datedemande 2
     * @return the list
     */
    List<DemandeProduit> findAllByDemande_DateHeureIsBetween(Instant datedemande1, Instant datedemande2);

    /**
     * Find by date demande retour is null list.
     *
     * @return the list
     */
    List<DemandeProduit> findByDateDemandeRetourIsNull();

    /**
     * Find by status and date demande retour is null list.
     *
     * @param status the status
     * @return the list
     */
    List<DemandeProduit> findByStatusAndDateDemandeRetourIsNull(EnumDemandeStatus status);

    /**
     * Find by status in and date demande retour is null list.
     *
     * @param statusList the status list
     * @return the list
     */
    List<DemandeProduit> findByStatusInAndDateDemandeRetourIsNull(List<EnumDemandeStatus> statusList);

    /**
     * Find by status in order by created date desc list.
     *
     * @param statusList the status list
     * @return the list
     */
    List<DemandeProduit> findByStatusInOrderByCreatedDateDesc(List<EnumDemandeStatus> statusList);

    /**
     * Find by status in or produit status in order by created date desc list.
     *
     * @param statusList            the status list
     * @param enumProduitStatusList the enum produit status list
     * @return the list
     */
    List<DemandeProduit> findByStatusInOrProduitStatusInOrderByCreatedDateDesc(List<EnumDemandeStatus> statusList, List<EnumProduitStatus> enumProduitStatusList);

    /**
     * Find by status not in order by created date desc list.
     *
     * @param statusList the status list
     * @return the list
     */
    List<DemandeProduit> findByStatusNotInOrderByCreatedDateDesc(List<EnumDemandeStatus> statusList);

}
