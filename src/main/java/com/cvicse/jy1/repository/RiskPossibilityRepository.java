package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.RiskPossibility;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RiskPossibility entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RiskPossibilityRepository extends JpaRepository<RiskPossibility, Integer> {}
