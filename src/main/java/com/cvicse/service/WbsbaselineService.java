package com.cvicse.service;

import com.cvicse.domain.Wbsbaseline;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Wbsbaseline}.
 */
public interface WbsbaselineService {
    /**
     * Save a wbsbaseline.
     *
     * @param wbsbaseline the entity to save.
     * @return the persisted entity.
     */
    Wbsbaseline save(Wbsbaseline wbsbaseline);

    /**
     * Updates a wbsbaseline.
     *
     * @param wbsbaseline the entity to update.
     * @return the persisted entity.
     */
    Wbsbaseline update(Wbsbaseline wbsbaseline);

    /**
     * Partially updates a wbsbaseline.
     *
     * @param wbsbaseline the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Wbsbaseline> partialUpdate(Wbsbaseline wbsbaseline);

    /**
     * Get all the wbsbaselines.
     *
     * @return the list of entities.
     */
    List<Wbsbaseline> findAll();

    /**
     * Get the "id" wbsbaseline.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Wbsbaseline> findOne(String id);

    /**
     * Delete the "id" wbsbaseline.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
