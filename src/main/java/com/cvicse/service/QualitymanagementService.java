package com.cvicse.service;

import com.cvicse.domain.Qualitymanagement;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Qualitymanagement}.
 */
public interface QualitymanagementService {
    /**
     * Save a qualitymanagement.
     *
     * @param qualitymanagement the entity to save.
     * @return the persisted entity.
     */
    Qualitymanagement save(Qualitymanagement qualitymanagement);

    /**
     * Updates a qualitymanagement.
     *
     * @param qualitymanagement the entity to update.
     * @return the persisted entity.
     */
    Qualitymanagement update(Qualitymanagement qualitymanagement);

    /**
     * Partially updates a qualitymanagement.
     *
     * @param qualitymanagement the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Qualitymanagement> partialUpdate(Qualitymanagement qualitymanagement);

    /**
     * Get all the qualitymanagements.
     *
     * @return the list of entities.
     */
    List<Qualitymanagement> findAll();

    /**
     * Get the "id" qualitymanagement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Qualitymanagement> findOne(String id);

    /**
     * Delete the "id" qualitymanagement.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
