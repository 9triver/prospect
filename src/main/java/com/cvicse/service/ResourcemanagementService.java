package com.cvicse.service;

import com.cvicse.domain.Resourcemanagement;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Resourcemanagement}.
 */
public interface ResourcemanagementService {
    /**
     * Save a resourcemanagement.
     *
     * @param resourcemanagement the entity to save.
     * @return the persisted entity.
     */
    Resourcemanagement save(Resourcemanagement resourcemanagement);

    /**
     * Updates a resourcemanagement.
     *
     * @param resourcemanagement the entity to update.
     * @return the persisted entity.
     */
    Resourcemanagement update(Resourcemanagement resourcemanagement);

    /**
     * Partially updates a resourcemanagement.
     *
     * @param resourcemanagement the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Resourcemanagement> partialUpdate(Resourcemanagement resourcemanagement);

    /**
     * Get all the resourcemanagements.
     *
     * @return the list of entities.
     */
    List<Resourcemanagement> findAll();

    /**
     * Get the "id" resourcemanagement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Resourcemanagement> findOne(String id);

    /**
     * Delete the "id" resourcemanagement.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
