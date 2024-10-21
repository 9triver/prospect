package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.Frontline;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.Frontline}.
 */
public interface FrontlineService {
    /**
     * Save a frontline.
     *
     * @param frontline the entity to save.
     * @return the persisted entity.
     */
    Frontline save(Frontline frontline);

    /**
     * Updates a frontline.
     *
     * @param frontline the entity to update.
     * @return the persisted entity.
     */
    Frontline update(Frontline frontline);

    /**
     * Partially updates a frontline.
     *
     * @param frontline the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Frontline> partialUpdate(Frontline frontline);

    /**
     * Get all the frontlines.
     *
     * @return the list of entities.
     */
    List<Frontline> findAll();

    /**
     * Get the "id" frontline.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Frontline> findOne(String id);

    /**
     * Delete the "id" frontline.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
