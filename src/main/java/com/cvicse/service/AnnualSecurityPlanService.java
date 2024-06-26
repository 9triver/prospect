package com.cvicse.service;

import com.cvicse.domain.AnnualSecurityPlan;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.AnnualSecurityPlan}.
 */
public interface AnnualSecurityPlanService {
    /**
     * Save a annualSecurityPlan.
     *
     * @param annualSecurityPlan the entity to save.
     * @return the persisted entity.
     */
    AnnualSecurityPlan save(AnnualSecurityPlan annualSecurityPlan);

    /**
     * Updates a annualSecurityPlan.
     *
     * @param annualSecurityPlan the entity to update.
     * @return the persisted entity.
     */
    AnnualSecurityPlan update(AnnualSecurityPlan annualSecurityPlan);

    /**
     * Partially updates a annualSecurityPlan.
     *
     * @param annualSecurityPlan the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AnnualSecurityPlan> partialUpdate(AnnualSecurityPlan annualSecurityPlan);

    /**
     * Get all the annualSecurityPlans.
     *
     * @return the list of entities.
     */
    List<AnnualSecurityPlan> findAll();

    /**
     * Get the "id" annualSecurityPlan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AnnualSecurityPlan> findOne(String id);

    /**
     * Delete the "id" annualSecurityPlan.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
