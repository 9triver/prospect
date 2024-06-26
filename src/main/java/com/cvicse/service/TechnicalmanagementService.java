package com.cvicse.service;

import com.cvicse.domain.Technicalmanagement;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Technicalmanagement}.
 */
public interface TechnicalmanagementService {
    /**
     * Save a technicalmanagement.
     *
     * @param technicalmanagement the entity to save.
     * @return the persisted entity.
     */
    Technicalmanagement save(Technicalmanagement technicalmanagement);

    /**
     * Updates a technicalmanagement.
     *
     * @param technicalmanagement the entity to update.
     * @return the persisted entity.
     */
    Technicalmanagement update(Technicalmanagement technicalmanagement);

    /**
     * Partially updates a technicalmanagement.
     *
     * @param technicalmanagement the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Technicalmanagement> partialUpdate(Technicalmanagement technicalmanagement);

    /**
     * Get all the technicalmanagements.
     *
     * @return the list of entities.
     */
    List<Technicalmanagement> findAll();

    /**
     * Get the "id" technicalmanagement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Technicalmanagement> findOne(String id);

    /**
     * Delete the "id" technicalmanagement.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
