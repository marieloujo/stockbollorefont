package com.bollore.stockbolloreback.repository;

import com.bollore.stockbolloreback.models.ServiceB;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ServiceB entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceRepository extends JpaRepository<ServiceB, Long> {}
