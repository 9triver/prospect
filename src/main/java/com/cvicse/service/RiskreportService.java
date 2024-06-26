package com.cvicse.service;

import com.cvicse.domain.Riskreport;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Riskreport}.
 */
public interface RiskreportService {
    /**
     * Save a riskreport.
     *
     * @param riskreport the entity to save.
     * @return the persisted entity.
     */
    Riskreport save(Riskreport riskreport);

    /**
     * Updates a riskreport.
     *
     * @param riskreport the entity to update.
     * @return the persisted entity.
     */
    Riskreport update(Riskreport riskreport);

    /**
     * Partially updates a riskreport.
     *
     * @param riskreport the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Riskreport> partialUpdate(Riskreport riskreport);

    /**
     * Get all the riskreports.
     *
     * @return the list of entities.
     */
    List<Riskreport> findAll();

    /**
     * Get the "id" riskreport.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Riskreport> findOne(String id);

    /**
     * Delete the "id" riskreport.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
