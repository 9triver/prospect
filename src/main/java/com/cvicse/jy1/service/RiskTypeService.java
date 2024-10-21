package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.RiskType;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.RiskType}.
 */
public interface RiskTypeService {
    /**
     * Save a riskType.
     *
     * @param riskType the entity to save.
     * @return the persisted entity.
     */
    RiskType save(RiskType riskType);

    /**
     * Updates a riskType.
     *
     * @param riskType the entity to update.
     * @return the persisted entity.
     */
    RiskType update(RiskType riskType);

    /**
     * Partially updates a riskType.
     *
     * @param riskType the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RiskType> partialUpdate(RiskType riskType);

    /**
     * Get all the riskTypes.
     *
     * @return the list of entities.
     */
    List<RiskType> findAll();

    /**
     * Get the "id" riskType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RiskType> findOne(Integer id);

    /**
     * Delete the "id" riskType.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
