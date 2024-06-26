package com.cvicse.service;

import com.cvicse.domain.Outsourcingmanagement;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Outsourcingmanagement}.
 */
public interface OutsourcingmanagementService {
    /**
     * Save a outsourcingmanagement.
     *
     * @param outsourcingmanagement the entity to save.
     * @return the persisted entity.
     */
    Outsourcingmanagement save(Outsourcingmanagement outsourcingmanagement);

    /**
     * Updates a outsourcingmanagement.
     *
     * @param outsourcingmanagement the entity to update.
     * @return the persisted entity.
     */
    Outsourcingmanagement update(Outsourcingmanagement outsourcingmanagement);

    /**
     * Partially updates a outsourcingmanagement.
     *
     * @param outsourcingmanagement the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Outsourcingmanagement> partialUpdate(Outsourcingmanagement outsourcingmanagement);

    /**
     * Get all the outsourcingmanagements.
     *
     * @return the list of entities.
     */
    List<Outsourcingmanagement> findAll();

    /**
     * Get the "id" outsourcingmanagement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Outsourcingmanagement> findOne(String id);

    /**
     * Delete the "id" outsourcingmanagement.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
