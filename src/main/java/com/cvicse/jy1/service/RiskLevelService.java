package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.RiskLevel;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.RiskLevel}.
 */
public interface RiskLevelService {
    /**
     * Save a riskLevel.
     *
     * @param riskLevel the entity to save.
     * @return the persisted entity.
     */
    RiskLevel save(RiskLevel riskLevel);

    /**
     * Updates a riskLevel.
     *
     * @param riskLevel the entity to update.
     * @return the persisted entity.
     */
    RiskLevel update(RiskLevel riskLevel);

    /**
     * Partially updates a riskLevel.
     *
     * @param riskLevel the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RiskLevel> partialUpdate(RiskLevel riskLevel);

    /**
     * Get all the riskLevels.
     *
     * @return the list of entities.
     */
    List<RiskLevel> findAll();

    /**
     * Get the "id" riskLevel.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RiskLevel> findOne(Integer id);

    /**
     * Delete the "id" riskLevel.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
