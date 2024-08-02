package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.OutsourcingPurchasePlan;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface OutsourcingPurchasePlanRepositoryWithBagRelationships {
    Optional<OutsourcingPurchasePlan> fetchBagRelationships(Optional<OutsourcingPurchasePlan> outsourcingPurchasePlan);

    List<OutsourcingPurchasePlan> fetchBagRelationships(List<OutsourcingPurchasePlan> outsourcingPurchasePlans);

    Page<OutsourcingPurchasePlan> fetchBagRelationships(Page<OutsourcingPurchasePlan> outsourcingPurchasePlans);
}
