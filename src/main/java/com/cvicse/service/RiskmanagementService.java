package com.cvicse.service;

import com.cvicse.domain.Riskmanagement;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Riskmanagement}.
 */
public interface RiskmanagementService {
    /**
     * Save a riskmanagement.
     *
     * @param riskmanagement the entity to save.
     * @return the persisted entity.
     */
    Riskmanagement save(Riskmanagement riskmanagement);

    /**
     * Updates a riskmanagement.
     *
     * @param riskmanagement the entity to update.
     * @return the persisted entity.
     */
    Riskmanagement update(Riskmanagement riskmanagement);

    /**
     * Partially updates a riskmanagement.
     *
     * @param riskmanagement the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Riskmanagement> partialUpdate(Riskmanagement riskmanagement);

    /**
     * Get all the riskmanagements.
     *
     * @return the list of entities.
     */
    List<Riskmanagement> findAll();

    /**
     * Get the "id" riskmanagement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Riskmanagement> findOne(String id);

    /**
     * Delete the "id" riskmanagement.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
