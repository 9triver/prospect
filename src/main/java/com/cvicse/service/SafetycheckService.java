package com.cvicse.service;

import com.cvicse.domain.Safetycheck;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Safetycheck}.
 */
public interface SafetycheckService {
    /**
     * Save a safetycheck.
     *
     * @param safetycheck the entity to save.
     * @return the persisted entity.
     */
    Safetycheck save(Safetycheck safetycheck);

    /**
     * Updates a safetycheck.
     *
     * @param safetycheck the entity to update.
     * @return the persisted entity.
     */
    Safetycheck update(Safetycheck safetycheck);

    /**
     * Partially updates a safetycheck.
     *
     * @param safetycheck the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Safetycheck> partialUpdate(Safetycheck safetycheck);

    /**
     * Get all the safetychecks.
     *
     * @return the list of entities.
     */
    List<Safetycheck> findAll();

    /**
     * Get the "id" safetycheck.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Safetycheck> findOne(String id);

    /**
     * Delete the "id" safetycheck.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
