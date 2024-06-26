package com.cvicse.service;

import com.cvicse.domain.Qualityobjectives;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Qualityobjectives}.
 */
public interface QualityobjectivesService {
    /**
     * Save a qualityobjectives.
     *
     * @param qualityobjectives the entity to save.
     * @return the persisted entity.
     */
    Qualityobjectives save(Qualityobjectives qualityobjectives);

    /**
     * Updates a qualityobjectives.
     *
     * @param qualityobjectives the entity to update.
     * @return the persisted entity.
     */
    Qualityobjectives update(Qualityobjectives qualityobjectives);

    /**
     * Partially updates a qualityobjectives.
     *
     * @param qualityobjectives the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Qualityobjectives> partialUpdate(Qualityobjectives qualityobjectives);

    /**
     * Get all the qualityobjectives.
     *
     * @return the list of entities.
     */
    List<Qualityobjectives> findAll();

    /**
     * Get the "id" qualityobjectives.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Qualityobjectives> findOne(String id);

    /**
     * Delete the "id" qualityobjectives.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
