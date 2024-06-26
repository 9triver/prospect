package com.cvicse.service;

import com.cvicse.domain.Comprehensivecontrol;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Comprehensivecontrol}.
 */
public interface ComprehensivecontrolService {
    /**
     * Save a comprehensivecontrol.
     *
     * @param comprehensivecontrol the entity to save.
     * @return the persisted entity.
     */
    Comprehensivecontrol save(Comprehensivecontrol comprehensivecontrol);

    /**
     * Updates a comprehensivecontrol.
     *
     * @param comprehensivecontrol the entity to update.
     * @return the persisted entity.
     */
    Comprehensivecontrol update(Comprehensivecontrol comprehensivecontrol);

    /**
     * Partially updates a comprehensivecontrol.
     *
     * @param comprehensivecontrol the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Comprehensivecontrol> partialUpdate(Comprehensivecontrol comprehensivecontrol);

    /**
     * Get all the comprehensivecontrols.
     *
     * @return the list of entities.
     */
    List<Comprehensivecontrol> findAll();

    /**
     * Get the "id" comprehensivecontrol.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Comprehensivecontrol> findOne(String id);

    /**
     * Delete the "id" comprehensivecontrol.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
