package com.cvicse.service;

import com.cvicse.domain.Fundsmanagement;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Fundsmanagement}.
 */
public interface FundsmanagementService {
    /**
     * Save a fundsmanagement.
     *
     * @param fundsmanagement the entity to save.
     * @return the persisted entity.
     */
    Fundsmanagement save(Fundsmanagement fundsmanagement);

    /**
     * Updates a fundsmanagement.
     *
     * @param fundsmanagement the entity to update.
     * @return the persisted entity.
     */
    Fundsmanagement update(Fundsmanagement fundsmanagement);

    /**
     * Partially updates a fundsmanagement.
     *
     * @param fundsmanagement the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Fundsmanagement> partialUpdate(Fundsmanagement fundsmanagement);

    /**
     * Get all the fundsmanagements.
     *
     * @return the list of entities.
     */
    List<Fundsmanagement> findAll();

    /**
     * Get all the Fundsmanagement where Comprehensivecontrol is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Fundsmanagement> findAllWhereComprehensivecontrolIsNull();

    /**
     * Get the "id" fundsmanagement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Fundsmanagement> findOne(String id);

    /**
     * Delete the "id" fundsmanagement.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
