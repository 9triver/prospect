package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.ProjectBudget;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface ProjectBudgetRepositoryWithBagRelationships {
    Optional<ProjectBudget> fetchBagRelationships(Optional<ProjectBudget> projectBudget);

    List<ProjectBudget> fetchBagRelationships(List<ProjectBudget> projectBudgets);

    Page<ProjectBudget> fetchBagRelationships(Page<ProjectBudget> projectBudgets);
}
