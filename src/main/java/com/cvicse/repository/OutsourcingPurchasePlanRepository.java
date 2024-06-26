package com.cvicse.repository;

import com.cvicse.domain.OutsourcingPurchasePlan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OutsourcingPurchasePlan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OutsourcingPurchasePlanRepository extends JpaRepository<OutsourcingPurchasePlan, String> {}
