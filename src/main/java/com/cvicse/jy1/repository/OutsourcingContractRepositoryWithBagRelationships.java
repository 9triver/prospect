package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.OutsourcingContract;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface OutsourcingContractRepositoryWithBagRelationships {
    Optional<OutsourcingContract> fetchBagRelationships(Optional<OutsourcingContract> outsourcingContract);

    List<OutsourcingContract> fetchBagRelationships(List<OutsourcingContract> outsourcingContracts);

    Page<OutsourcingContract> fetchBagRelationships(Page<OutsourcingContract> outsourcingContracts);
}
