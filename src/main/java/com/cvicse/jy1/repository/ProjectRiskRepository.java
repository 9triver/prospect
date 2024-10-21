package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.ProjectRisk;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ProjectRisk entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectRiskRepository extends JpaRepository<ProjectRisk, Integer> {}
