package com.cvicse.repository;

import com.cvicse.domain.Planreturns;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Planreturns entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanreturnsRepository extends JpaRepository<Planreturns, String> {}
