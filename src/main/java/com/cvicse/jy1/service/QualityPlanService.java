package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.QualityPlan;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.QualityPlan}.
 */
public interface QualityPlanService {
    /**
     * Save a qualityPlan.
     *
     * @param qualityPlan the entity to save.
     * @return the persisted entity.
     */
    QualityPlan save(QualityPlan qualityPlan);

    /**
     * Updates a qualityPlan.
     *
     * @param qualityPlan the entity to update.
     * @return the persisted entity.
     */
    QualityPlan update(QualityPlan qualityPlan);

    /**
     * Partially updates a qualityPlan.
     *
     * @param qualityPlan the entity to update partially.
     * @return the persisted entity.
     */
    Optional<QualityPlan> partialUpdate(QualityPlan qualityPlan);

    /**
     * Get all the qualityPlans.
     *
     * @return the list of entities.
     */
    List<QualityPlan> findAll();

    /**
     * Get the "id" qualityPlan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QualityPlan> findOne(String id);

    /**
     * Delete the "id" qualityPlan.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
