package com.cvicse.repository;

import com.cvicse.domain.Planstrategy;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Planstrategy entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanstrategyRepository extends JpaRepository<Planstrategy, Long> {}
