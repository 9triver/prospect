package com.cvicse.service;

import com.cvicse.domain.Pbsbaseline;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Pbsbaseline}.
 */
public interface PbsbaselineService {
    /**
     * Save a pbsbaseline.
     *
     * @param pbsbaseline the entity to save.
     * @return the persisted entity.
     */
    Pbsbaseline save(Pbsbaseline pbsbaseline);

    /**
     * Updates a pbsbaseline.
     *
     * @param pbsbaseline the entity to update.
     * @return the persisted entity.
     */
    Pbsbaseline update(Pbsbaseline pbsbaseline);

    /**
     * Partially updates a pbsbaseline.
     *
     * @param pbsbaseline the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Pbsbaseline> partialUpdate(Pbsbaseline pbsbaseline);

    /**
     * Get all the pbsbaselines.
     *
     * @return the list of entities.
     */
    List<Pbsbaseline> findAll();

    /**
     * Get the "id" pbsbaseline.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Pbsbaseline> findOne(String id);

    /**
     * Delete the "id" pbsbaseline.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
