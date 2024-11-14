package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.ContractPayment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface ContractPaymentRepositoryWithBagRelationships {
    Optional<ContractPayment> fetchBagRelationships(Optional<ContractPayment> contractPayment);

    List<ContractPayment> fetchBagRelationships(List<ContractPayment> contractPayments);

    Page<ContractPayment> fetchBagRelationships(Page<ContractPayment> contractPayments);
}
