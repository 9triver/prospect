package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.TechnicalCondition;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TechnicalCondition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TechnicalConditionRepository extends JpaRepository<TechnicalCondition, Integer> {}
