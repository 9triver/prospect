package com.cvicse.service;

import com.cvicse.domain.RiskmanagementWbs;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.RiskmanagementWbs}.
 */
public interface RiskmanagementWbsService {
    /**
     * Save a riskmanagementWbs.
     *
     * @param riskmanagementWbs the entity to save.
     * @return the persisted entity.
     */
    RiskmanagementWbs save(RiskmanagementWbs riskmanagementWbs);

    /**
     * Updates a riskmanagementWbs.
     *
     * @param riskmanagementWbs the entity to update.
     * @return the persisted entity.
     */
    RiskmanagementWbs update(RiskmanagementWbs riskmanagementWbs);

    /**
     * Partially updates a riskmanagementWbs.
     *
     * @param riskmanagementWbs the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RiskmanagementWbs> partialUpdate(RiskmanagementWbs riskmanagementWbs);

    /**
     * Get all the riskmanagementWbs.
     *
     * @return the list of entities.
     */
    List<RiskmanagementWbs> findAll();

    /**
     * Get all the RiskmanagementWbs where Riskmanagement is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<RiskmanagementWbs> findAllWhereRiskmanagementIsNull();

    /**
     * Get the "id" riskmanagementWbs.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RiskmanagementWbs> findOne(String id);

    /**
     * Delete the "id" riskmanagementWbs.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
