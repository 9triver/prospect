package com.cvicse.repository;

import com.cvicse.domain.EvaluationCriteria;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the EvaluationCriteria entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvaluationCriteriaRepository extends JpaRepository<EvaluationCriteria, String> {}
