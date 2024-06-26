package com.cvicse.service;

import com.cvicse.domain.Riskidentification;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Riskidentification}.
 */
public interface RiskidentificationService {
    /**
     * Save a riskidentification.
     *
     * @param riskidentification the entity to save.
     * @return the persisted entity.
     */
    Riskidentification save(Riskidentification riskidentification);

    /**
     * Updates a riskidentification.
     *
     * @param riskidentification the entity to update.
     * @return the persisted entity.
     */
    Riskidentification update(Riskidentification riskidentification);

    /**
     * Partially updates a riskidentification.
     *
     * @param riskidentification the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Riskidentification> partialUpdate(Riskidentification riskidentification);

    /**
     * Get all the riskidentifications.
     *
     * @return the list of entities.
     */
    List<Riskidentification> findAll();

    /**
     * Get all the Riskidentification where Riskreport is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Riskidentification> findAllWhereRiskreportIsNull();

    /**
     * Get the "id" riskidentification.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Riskidentification> findOne(String id);

    /**
     * Delete the "id" riskidentification.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
