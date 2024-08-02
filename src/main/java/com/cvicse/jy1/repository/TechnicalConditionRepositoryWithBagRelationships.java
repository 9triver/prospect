package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.TechnicalCondition;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface TechnicalConditionRepositoryWithBagRelationships {
    Optional<TechnicalCondition> fetchBagRelationships(Optional<TechnicalCondition> technicalCondition);

    List<TechnicalCondition> fetchBagRelationships(List<TechnicalCondition> technicalConditions);

    Page<TechnicalCondition> fetchBagRelationships(Page<TechnicalCondition> technicalConditions);
}
