package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.RiskReturn;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RiskReturn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RiskReturnRepository extends JpaRepository<RiskReturn, Integer> {}
