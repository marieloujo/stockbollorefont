package com.bollore.stockbolloreback.repository;

import com.bollore.stockbolloreback.models.ServiceB;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the ServiceB entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceRepository extends JpaRepository<ServiceB, Long> {

    /**
     * Find all by order by created date desc list.
     *
     * @return the list
     */
    List<ServiceB> findAllByOrderByCreatedDateDesc();
}
