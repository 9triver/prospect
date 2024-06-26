package com.cvicse.repository;

import com.cvicse.domain.RiskmanagementWbs;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RiskmanagementWbs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RiskmanagementWbsRepository extends JpaRepository<RiskmanagementWbs, String> {}
