package com.cvicse.service;

import com.cvicse.domain.Progressplanreturns;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Progressplanreturns}.
 */
public interface ProgressplanreturnsService {
    /**
     * Save a progressplanreturns.
     *
     * @param progressplanreturns the entity to save.
     * @return the persisted entity.
     */
    Progressplanreturns save(Progressplanreturns progressplanreturns);

    /**
     * Updates a progressplanreturns.
     *
     * @param progressplanreturns the entity to update.
     * @return the persisted entity.
     */
    Progressplanreturns update(Progressplanreturns progressplanreturns);

    /**
     * Partially updates a progressplanreturns.
     *
     * @param progressplanreturns the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Progressplanreturns> partialUpdate(Progressplanreturns progressplanreturns);

    /**
     * Get all the progressplanreturns.
     *
     * @return the list of entities.
     */
    List<Progressplanreturns> findAll();

    /**
     * Get the "id" progressplanreturns.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Progressplanreturns> findOne(String id);

    /**
     * Delete the "id" progressplanreturns.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
