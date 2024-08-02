package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.QualityObjectives;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface QualityObjectivesRepositoryWithBagRelationships {
    Optional<QualityObjectives> fetchBagRelationships(Optional<QualityObjectives> qualityObjectives);

    List<QualityObjectives> fetchBagRelationships(List<QualityObjectives> qualityObjectives);

    Page<QualityObjectives> fetchBagRelationships(Page<QualityObjectives> qualityObjectives);
}
