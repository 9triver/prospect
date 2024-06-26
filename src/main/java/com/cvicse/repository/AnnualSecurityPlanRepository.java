package com.cvicse.repository;

import com.cvicse.domain.AnnualSecurityPlan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AnnualSecurityPlan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnnualSecurityPlanRepository extends JpaRepository<AnnualSecurityPlan, String> {}
