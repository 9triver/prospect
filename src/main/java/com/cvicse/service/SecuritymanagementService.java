package com.cvicse.service;

import com.cvicse.domain.Securitymanagement;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Securitymanagement}.
 */
public interface SecuritymanagementService {
    /**
     * Save a securitymanagement.
     *
     * @param securitymanagement the entity to save.
     * @return the persisted entity.
     */
    Securitymanagement save(Securitymanagement securitymanagement);

    /**
     * Updates a securitymanagement.
     *
     * @param securitymanagement the entity to update.
     * @return the persisted entity.
     */
    Securitymanagement update(Securitymanagement securitymanagement);

    /**
     * Partially updates a securitymanagement.
     *
     * @param securitymanagement the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Securitymanagement> partialUpdate(Securitymanagement securitymanagement);

    /**
     * Get all the securitymanagements.
     *
     * @return the list of entities.
     */
    List<Securitymanagement> findAll();

    /**
     * Get the "id" securitymanagement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Securitymanagement> findOne(String id);

    /**
     * Delete the "id" securitymanagement.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
