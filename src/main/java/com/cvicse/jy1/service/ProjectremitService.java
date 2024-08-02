package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.Projectremit;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.Projectremit}.
 */
public interface ProjectremitService {
    /**
     * Save a projectremit.
     *
     * @param projectremit the entity to save.
     * @return the persisted entity.
     */
    Projectremit save(Projectremit projectremit);

    /**
     * Updates a projectremit.
     *
     * @param projectremit the entity to update.
     * @return the persisted entity.
     */
    Projectremit update(Projectremit projectremit);

    /**
     * Partially updates a projectremit.
     *
     * @param projectremit the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Projectremit> partialUpdate(Projectremit projectremit);

    /**
     * Get all the projectremits.
     *
     * @return the list of entities.
     */
    List<Projectremit> findAll();

    /**
     * Get the "id" projectremit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Projectremit> findOne(String id);

    /**
     * Delete the "id" projectremit.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
