package com.cvicse.repository;

import com.cvicse.domain.Riskmanagement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Riskmanagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RiskmanagementRepository extends JpaRepository<Riskmanagement, Long> {}
