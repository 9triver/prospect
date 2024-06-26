package com.cvicse.service;

import com.cvicse.domain.Progressplan;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Progressplan}.
 */
public interface ProgressplanService {
    /**
     * Save a progressplan.
     *
     * @param progressplan the entity to save.
     * @return the persisted entity.
     */
    Progressplan save(Progressplan progressplan);

    /**
     * Updates a progressplan.
     *
     * @param progressplan the entity to update.
     * @return the persisted entity.
     */
    Progressplan update(Progressplan progressplan);

    /**
     * Partially updates a progressplan.
     *
     * @param progressplan the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Progressplan> partialUpdate(Progressplan progressplan);

    /**
     * Get all the progressplans.
     *
     * @return the list of entities.
     */
    List<Progressplan> findAll();

    /**
     * Get all the Progressplan where Comprehensivecontrol is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Progressplan> findAllWhereComprehensivecontrolIsNull();
    /**
     * Get all the Progressplan where Progressplanreturns is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Progressplan> findAllWhereProgressplanreturnsIsNull();

    /**
     * Get the "id" progressplan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Progressplan> findOne(String id);

    /**
     * Delete the "id" progressplan.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
