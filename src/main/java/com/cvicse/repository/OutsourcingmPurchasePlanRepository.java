package com.cvicse.repository;

import com.cvicse.domain.OutsourcingmPurchasePlan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OutsourcingmPurchasePlan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OutsourcingmPurchasePlanRepository extends JpaRepository<OutsourcingmPurchasePlan, Long> {}
