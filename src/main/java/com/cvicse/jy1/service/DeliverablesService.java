package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.Deliverables;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.Deliverables}.
 */
public interface DeliverablesService {
    /**
     * Save a deliverables.
     *
     * @param deliverables the entity to save.
     * @return the persisted entity.
     */
    Deliverables save(Deliverables deliverables);

    /**
     * Updates a deliverables.
     *
     * @param deliverables the entity to update.
     * @return the persisted entity.
     */
    Deliverables update(Deliverables deliverables);

    /**
     * Partially updates a deliverables.
     *
     * @param deliverables the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Deliverables> partialUpdate(Deliverables deliverables);

    /**
     * Get all the deliverables.
     *
     * @return the list of entities.
     */
    List<Deliverables> findAll();

    /**
     * Get all the Deliverables where Projectdeliverables is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Deliverables> findAllWhereProjectdeliverablesIsNull();

    /**
     * Get the "id" deliverables.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Deliverables> findOne(Long id);

    /**
     * Delete the "id" deliverables.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
