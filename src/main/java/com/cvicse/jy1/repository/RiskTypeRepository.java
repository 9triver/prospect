package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.RiskType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RiskType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RiskTypeRepository extends JpaRepository<RiskType, Integer> {}
