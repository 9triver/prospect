package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.Work;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.Work}.
 */
public interface WorkService {
    /**
     * Save a work.
     *
     * @param work the entity to save.
     * @return the persisted entity.
     */
    Work save(Work work);

    /**
     * Updates a work.
     *
     * @param work the entity to update.
     * @return the persisted entity.
     */
    Work update(Work work);

    /**
     * Partially updates a work.
     *
     * @param work the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Work> partialUpdate(Work work);

    /**
     * Get all the works.
     *
     * @return the list of entities.
     */
    List<Work> findAll();

    /**
     * Get the "id" work.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Work> findOne(String id);

    /**
     * Delete the "id" work.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
