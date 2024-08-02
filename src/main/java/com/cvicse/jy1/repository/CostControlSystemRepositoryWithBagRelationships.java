package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.CostControlSystem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface CostControlSystemRepositoryWithBagRelationships {
    Optional<CostControlSystem> fetchBagRelationships(Optional<CostControlSystem> costControlSystem);

    List<CostControlSystem> fetchBagRelationships(List<CostControlSystem> costControlSystems);

    Page<CostControlSystem> fetchBagRelationships(Page<CostControlSystem> costControlSystems);
}
