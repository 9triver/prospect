package com.cvicse.repository;

import com.cvicse.domain.ManagementCapacityEvaluation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ManagementCapacityEvaluation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ManagementCapacityEvaluationRepository extends JpaRepository<ManagementCapacityEvaluation, String> {}
