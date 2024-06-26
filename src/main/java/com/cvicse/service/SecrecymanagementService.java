package com.cvicse.service;

import com.cvicse.domain.Secrecymanagement;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Secrecymanagement}.
 */
public interface SecrecymanagementService {
    /**
     * Save a secrecymanagement.
     *
     * @param secrecymanagement the entity to save.
     * @return the persisted entity.
     */
    Secrecymanagement save(Secrecymanagement secrecymanagement);

    /**
     * Updates a secrecymanagement.
     *
     * @param secrecymanagement the entity to update.
     * @return the persisted entity.
     */
    Secrecymanagement update(Secrecymanagement secrecymanagement);

    /**
     * Partially updates a secrecymanagement.
     *
     * @param secrecymanagement the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Secrecymanagement> partialUpdate(Secrecymanagement secrecymanagement);

    /**
     * Get all the secrecymanagements.
     *
     * @return the list of entities.
     */
    List<Secrecymanagement> findAll();

    /**
     * Get the "id" secrecymanagement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Secrecymanagement> findOne(String id);

    /**
     * Delete the "id" secrecymanagement.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
