package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.QualityPlan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the QualityPlan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QualityPlanRepository extends JpaRepository<QualityPlan, String> {}
