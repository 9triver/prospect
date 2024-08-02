package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.PlanReturns;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PlanReturns entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanReturnsRepository extends JpaRepository<PlanReturns, String> {}
