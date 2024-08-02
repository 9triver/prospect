package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.ContractCostBudget;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface ContractCostBudgetRepositoryWithBagRelationships {
    Optional<ContractCostBudget> fetchBagRelationships(Optional<ContractCostBudget> contractCostBudget);

    List<ContractCostBudget> fetchBagRelationships(List<ContractCostBudget> contractCostBudgets);

    Page<ContractCostBudget> fetchBagRelationships(Page<ContractCostBudget> contractCostBudgets);
}
