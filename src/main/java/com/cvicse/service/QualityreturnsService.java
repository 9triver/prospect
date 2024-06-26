package com.cvicse.service;

import com.cvicse.domain.Qualityreturns;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Qualityreturns}.
 */
public interface QualityreturnsService {
    /**
     * Save a qualityreturns.
     *
     * @param qualityreturns the entity to save.
     * @return the persisted entity.
     */
    Qualityreturns save(Qualityreturns qualityreturns);

    /**
     * Updates a qualityreturns.
     *
     * @param qualityreturns the entity to update.
     * @return the persisted entity.
     */
    Qualityreturns update(Qualityreturns qualityreturns);

    /**
     * Partially updates a qualityreturns.
     *
     * @param qualityreturns the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Qualityreturns> partialUpdate(Qualityreturns qualityreturns);

    /**
     * Get all the qualityreturns.
     *
     * @return the list of entities.
     */
    List<Qualityreturns> findAll();

    /**
     * Get all the Qualityreturns where Qualityobjectives is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Qualityreturns> findAllWhereQualityobjectivesIsNull();

    /**
     * Get the "id" qualityreturns.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Qualityreturns> findOne(String id);

    /**
     * Delete the "id" qualityreturns.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
