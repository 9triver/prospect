package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.QualityObjectives;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the QualityObjectives entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QualityObjectivesRepository extends JpaRepository<QualityObjectives, Integer> {}
