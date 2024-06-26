package com.cvicse.repository;

import com.cvicse.domain.Planmonitor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Planmonitor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanmonitorRepository extends JpaRepository<Planmonitor, String> {}
