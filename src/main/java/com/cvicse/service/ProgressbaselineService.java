package com.cvicse.service;

import com.cvicse.domain.Progressbaseline;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Progressbaseline}.
 */
public interface ProgressbaselineService {
    /**
     * Save a progressbaseline.
     *
     * @param progressbaseline the entity to save.
     * @return the persisted entity.
     */
    Progressbaseline save(Progressbaseline progressbaseline);

    /**
     * Updates a progressbaseline.
     *
     * @param progressbaseline the entity to update.
     * @return the persisted entity.
     */
    Progressbaseline update(Progressbaseline progressbaseline);

    /**
     * Partially updates a progressbaseline.
     *
     * @param progressbaseline the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Progressbaseline> partialUpdate(Progressbaseline progressbaseline);

    /**
     * Get all the progressbaselines.
     *
     * @return the list of entities.
     */
    List<Progressbaseline> findAll();

    /**
     * Get the "id" progressbaseline.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Progressbaseline> findOne(String id);

    /**
     * Delete the "id" progressbaseline.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
