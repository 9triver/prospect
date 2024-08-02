package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.ProgressPlan;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface ProgressPlanRepositoryWithBagRelationships {
    Optional<ProgressPlan> fetchBagRelationships(Optional<ProgressPlan> progressPlan);

    List<ProgressPlan> fetchBagRelationships(List<ProgressPlan> progressPlans);

    Page<ProgressPlan> fetchBagRelationships(Page<ProgressPlan> progressPlans);
}
