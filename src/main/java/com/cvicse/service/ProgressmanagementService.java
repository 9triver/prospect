package com.cvicse.service;

import com.cvicse.domain.Progressmanagement;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Progressmanagement}.
 */
public interface ProgressmanagementService {
    /**
     * Save a progressmanagement.
     *
     * @param progressmanagement the entity to save.
     * @return the persisted entity.
     */
    Progressmanagement save(Progressmanagement progressmanagement);

    /**
     * Updates a progressmanagement.
     *
     * @param progressmanagement the entity to update.
     * @return the persisted entity.
     */
    Progressmanagement update(Progressmanagement progressmanagement);

    /**
     * Partially updates a progressmanagement.
     *
     * @param progressmanagement the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Progressmanagement> partialUpdate(Progressmanagement progressmanagement);

    /**
     * Get all the progressmanagements.
     *
     * @return the list of entities.
     */
    List<Progressmanagement> findAll();

    /**
     * Get the "id" progressmanagement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Progressmanagement> findOne(String id);

    /**
     * Delete the "id" progressmanagement.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
