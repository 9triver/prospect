package com.cvicse.repository;

import com.cvicse.domain.Qualityreturns;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Qualityreturns entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QualityreturnsRepository extends JpaRepository<Qualityreturns, String> {}
