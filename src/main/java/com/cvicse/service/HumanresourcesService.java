package com.cvicse.service;

import com.cvicse.domain.Humanresources;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Humanresources}.
 */
public interface HumanresourcesService {
    /**
     * Save a humanresources.
     *
     * @param humanresources the entity to save.
     * @return the persisted entity.
     */
    Humanresources save(Humanresources humanresources);

    /**
     * Updates a humanresources.
     *
     * @param humanresources the entity to update.
     * @return the persisted entity.
     */
    Humanresources update(Humanresources humanresources);

    /**
     * Partially updates a humanresources.
     *
     * @param humanresources the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Humanresources> partialUpdate(Humanresources humanresources);

    /**
     * Get all the humanresources.
     *
     * @return the list of entities.
     */
    List<Humanresources> findAll();

    /**
     * Get the "id" humanresources.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Humanresources> findOne(String id);

    /**
     * Delete the "id" humanresources.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
