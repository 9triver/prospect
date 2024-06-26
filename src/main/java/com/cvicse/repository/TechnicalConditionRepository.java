package com.cvicse.repository;

import com.cvicse.domain.TechnicalCondition;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TechnicalCondition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TechnicalConditionRepository extends JpaRepository<TechnicalCondition, String> {}
