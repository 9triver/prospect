package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.ProjectRisk;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface ProjectRiskRepositoryWithBagRelationships {
    Optional<ProjectRisk> fetchBagRelationships(Optional<ProjectRisk> projectRisk);

    List<ProjectRisk> fetchBagRelationships(List<ProjectRisk> projectRisks);

    Page<ProjectRisk> fetchBagRelationships(Page<ProjectRisk> projectRisks);
}
