package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.ContractCostBudget;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ContractCostBudget entity.
 *
 * When extending this class, extend ContractCostBudgetRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface ContractCostBudgetRepository
    extends ContractCostBudgetRepositoryWithBagRelationships, JpaRepository<ContractCostBudget, String> {
    default Optional<ContractCostBudget> findOneWithEagerRelationships(String id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<ContractCostBudget> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<ContractCostBudget> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
