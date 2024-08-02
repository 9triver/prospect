package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.OutsourcingContractual;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface OutsourcingContractualRepositoryWithBagRelationships {
    Optional<OutsourcingContractual> fetchBagRelationships(Optional<OutsourcingContractual> outsourcingContractual);

    List<OutsourcingContractual> fetchBagRelationships(List<OutsourcingContractual> outsourcingContractuals);

    Page<OutsourcingContractual> fetchBagRelationships(Page<OutsourcingContractual> outsourcingContractuals);
}
