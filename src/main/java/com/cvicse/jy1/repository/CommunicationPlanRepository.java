package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.CommunicationPlan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CommunicationPlan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommunicationPlanRepository extends JpaRepository<CommunicationPlan, Integer> {}
