package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.ProjectBudget;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ProjectBudget entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectBudgetRepository extends JpaRepository<ProjectBudget, Long> {}
