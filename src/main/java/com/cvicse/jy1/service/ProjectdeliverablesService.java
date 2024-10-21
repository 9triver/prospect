package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.Projectdeliverables;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.Projectdeliverables}.
 */
public interface ProjectdeliverablesService {
    /**
     * Save a projectdeliverables.
     *
     * @param projectdeliverables the entity to save.
     * @return the persisted entity.
     */
    Projectdeliverables save(Projectdeliverables projectdeliverables);

    /**
     * Updates a projectdeliverables.
     *
     * @param projectdeliverables the entity to update.
     * @return the persisted entity.
     */
    Projectdeliverables update(Projectdeliverables projectdeliverables);

    /**
     * Partially updates a projectdeliverables.
     *
     * @param projectdeliverables the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Projectdeliverables> partialUpdate(Projectdeliverables projectdeliverables);

    /**
     * Get all the projectdeliverables.
     *
     * @return the list of entities.
     */
    List<Projectdeliverables> findAll();

    /**
     * Get the "id" projectdeliverables.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Projectdeliverables> findOne(Integer id);

    /**
     * Delete the "id" projectdeliverables.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
