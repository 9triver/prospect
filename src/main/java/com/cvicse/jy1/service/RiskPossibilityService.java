package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.RiskPossibility;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.RiskPossibility}.
 */
public interface RiskPossibilityService {
    /**
     * Save a riskPossibility.
     *
     * @param riskPossibility the entity to save.
     * @return the persisted entity.
     */
    RiskPossibility save(RiskPossibility riskPossibility);

    /**
     * Updates a riskPossibility.
     *
     * @param riskPossibility the entity to update.
     * @return the persisted entity.
     */
    RiskPossibility update(RiskPossibility riskPossibility);

    /**
     * Partially updates a riskPossibility.
     *
     * @param riskPossibility the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RiskPossibility> partialUpdate(RiskPossibility riskPossibility);

    /**
     * Get all the riskPossibilities.
     *
     * @return the list of entities.
     */
    List<RiskPossibility> findAll();

    /**
     * Get the "id" riskPossibility.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RiskPossibility> findOne(Integer id);

    /**
     * Delete the "id" riskPossibility.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
