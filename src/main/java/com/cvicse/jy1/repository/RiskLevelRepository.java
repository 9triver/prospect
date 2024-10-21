package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.RiskLevel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RiskLevel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RiskLevelRepository extends JpaRepository<RiskLevel, Integer> {}
