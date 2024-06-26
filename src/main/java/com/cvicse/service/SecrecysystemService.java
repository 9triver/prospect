package com.cvicse.service;

import com.cvicse.domain.Secrecysystem;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Secrecysystem}.
 */
public interface SecrecysystemService {
    /**
     * Save a secrecysystem.
     *
     * @param secrecysystem the entity to save.
     * @return the persisted entity.
     */
    Secrecysystem save(Secrecysystem secrecysystem);

    /**
     * Updates a secrecysystem.
     *
     * @param secrecysystem the entity to update.
     * @return the persisted entity.
     */
    Secrecysystem update(Secrecysystem secrecysystem);

    /**
     * Partially updates a secrecysystem.
     *
     * @param secrecysystem the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Secrecysystem> partialUpdate(Secrecysystem secrecysystem);

    /**
     * Get all the secrecysystems.
     *
     * @return the list of entities.
     */
    List<Secrecysystem> findAll();

    /**
     * Get all the Secrecysystem where ProjectSecrecy is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Secrecysystem> findAllWhereProjectSecrecyIsNull();

    /**
     * Get the "id" secrecysystem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Secrecysystem> findOne(String id);

    /**
     * Delete the "id" secrecysystem.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
