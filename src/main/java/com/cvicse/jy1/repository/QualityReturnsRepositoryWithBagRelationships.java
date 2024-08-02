package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.QualityReturns;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface QualityReturnsRepositoryWithBagRelationships {
    Optional<QualityReturns> fetchBagRelationships(Optional<QualityReturns> qualityReturns);

    List<QualityReturns> fetchBagRelationships(List<QualityReturns> qualityReturns);

    Page<QualityReturns> fetchBagRelationships(Page<QualityReturns> qualityReturns);
}
