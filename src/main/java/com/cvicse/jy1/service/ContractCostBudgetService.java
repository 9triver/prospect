package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.ContractCostBudget;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.ContractCostBudget}.
 */
public interface ContractCostBudgetService {
    /**
     * Save a contractCostBudget.
     *
     * @param contractCostBudget the entity to save.
     * @return the persisted entity.
     */
    ContractCostBudget save(ContractCostBudget contractCostBudget);

    /**
     * Updates a contractCostBudget.
     *
     * @param contractCostBudget the entity to update.
     * @return the persisted entity.
     */
    ContractCostBudget update(ContractCostBudget contractCostBudget);

    /**
     * Partially updates a contractCostBudget.
     *
     * @param contractCostBudget the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ContractCostBudget> partialUpdate(ContractCostBudget contractCostBudget);

    /**
     * Get all the contractCostBudgets.
     *
     * @return the list of entities.
     */
    List<ContractCostBudget> findAll();

    /**
     * Get all the contractCostBudgets with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ContractCostBudget> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" contractCostBudget.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ContractCostBudget> findOne(String id);

    /**
     * Delete the "id" contractCostBudget.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
