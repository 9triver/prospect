package com.cvicse.repository;

import com.cvicse.domain.Riskidentification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Riskidentification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RiskidentificationRepository extends JpaRepository<Riskidentification, String> {}
