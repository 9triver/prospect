package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.Qualitytozero;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.Qualitytozero}.
 */
public interface QualitytozeroService {
    /**
     * Save a qualitytozero.
     *
     * @param qualitytozero the entity to save.
     * @return the persisted entity.
     */
    Qualitytozero save(Qualitytozero qualitytozero);

    /**
     * Updates a qualitytozero.
     *
     * @param qualitytozero the entity to update.
     * @return the persisted entity.
     */
    Qualitytozero update(Qualitytozero qualitytozero);

    /**
     * Partially updates a qualitytozero.
     *
     * @param qualitytozero the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Qualitytozero> partialUpdate(Qualitytozero qualitytozero);

    /**
     * Get all the qualitytozeros.
     *
     * @return the list of entities.
     */
    List<Qualitytozero> findAll();

    /**
     * Get the "id" qualitytozero.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Qualitytozero> findOne(Integer id);

    /**
     * Delete the "id" qualitytozero.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
