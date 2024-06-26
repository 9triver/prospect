package com.cvicse.service;

import com.cvicse.domain.Integratedmanagement;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Integratedmanagement}.
 */
public interface IntegratedmanagementService {
    /**
     * Save a integratedmanagement.
     *
     * @param integratedmanagement the entity to save.
     * @return the persisted entity.
     */
    Integratedmanagement save(Integratedmanagement integratedmanagement);

    /**
     * Updates a integratedmanagement.
     *
     * @param integratedmanagement the entity to update.
     * @return the persisted entity.
     */
    Integratedmanagement update(Integratedmanagement integratedmanagement);

    /**
     * Partially updates a integratedmanagement.
     *
     * @param integratedmanagement the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Integratedmanagement> partialUpdate(Integratedmanagement integratedmanagement);

    /**
     * Get all the integratedmanagements.
     *
     * @return the list of entities.
     */
    List<Integratedmanagement> findAll();

    /**
     * Get the "id" integratedmanagement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Integratedmanagement> findOne(Long id);

    /**
     * Delete the "id" integratedmanagement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
