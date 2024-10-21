package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.CustomerSatisfaction;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.CustomerSatisfaction}.
 */
public interface CustomerSatisfactionService {
    /**
     * Save a customerSatisfaction.
     *
     * @param customerSatisfaction the entity to save.
     * @return the persisted entity.
     */
    CustomerSatisfaction save(CustomerSatisfaction customerSatisfaction);

    /**
     * Updates a customerSatisfaction.
     *
     * @param customerSatisfaction the entity to update.
     * @return the persisted entity.
     */
    CustomerSatisfaction update(CustomerSatisfaction customerSatisfaction);

    /**
     * Partially updates a customerSatisfaction.
     *
     * @param customerSatisfaction the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CustomerSatisfaction> partialUpdate(CustomerSatisfaction customerSatisfaction);

    /**
     * Get all the customerSatisfactions.
     *
     * @return the list of entities.
     */
    List<CustomerSatisfaction> findAll();

    /**
     * Get the "id" customerSatisfaction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CustomerSatisfaction> findOne(Integer id);

    /**
     * Delete the "id" customerSatisfaction.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
