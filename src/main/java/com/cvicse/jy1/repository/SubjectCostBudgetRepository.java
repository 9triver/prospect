package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.SubjectCostBudget;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SubjectCostBudget entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubjectCostBudgetRepository extends JpaRepository<SubjectCostBudget, Long> {}
