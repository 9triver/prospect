package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.MilestoneNode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MilestoneNode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MilestoneNodeRepository extends JpaRepository<MilestoneNode, Integer> {}
