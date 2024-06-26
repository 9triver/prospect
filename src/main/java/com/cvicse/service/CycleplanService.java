package com.cvicse.service;

import com.cvicse.domain.Cycleplan;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Cycleplan}.
 */
public interface CycleplanService {
    /**
     * Save a cycleplan.
     *
     * @param cycleplan the entity to save.
     * @return the persisted entity.
     */
    Cycleplan save(Cycleplan cycleplan);

    /**
     * Updates a cycleplan.
     *
     * @param cycleplan the entity to update.
     * @return the persisted entity.
     */
    Cycleplan update(Cycleplan cycleplan);

    /**
     * Partially updates a cycleplan.
     *
     * @param cycleplan the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Cycleplan> partialUpdate(Cycleplan cycleplan);

    /**
     * Get all the cycleplans.
     *
     * @return the list of entities.
     */
    List<Cycleplan> findAll();

    /**
     * Get the "id" cycleplan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Cycleplan> findOne(String id);

    /**
     * Delete the "id" cycleplan.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
