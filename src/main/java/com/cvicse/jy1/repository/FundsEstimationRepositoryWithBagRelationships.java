package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.FundsEstimation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface FundsEstimationRepositoryWithBagRelationships {
    Optional<FundsEstimation> fetchBagRelationships(Optional<FundsEstimation> fundsEstimation);

    List<FundsEstimation> fetchBagRelationships(List<FundsEstimation> fundsEstimations);

    Page<FundsEstimation> fetchBagRelationships(Page<FundsEstimation> fundsEstimations);
}
