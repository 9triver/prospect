package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.ProgressPlan;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.ProgressPlan}.
 */
public interface ProgressPlanService {
    /**
     * Save a progressPlan.
     *
     * @param progressPlan the entity to save.
     * @return the persisted entity.
     */
    ProgressPlan save(ProgressPlan progressPlan);

    /**
     * Updates a progressPlan.
     *
     * @param progressPlan the entity to update.
     * @return the persisted entity.
     */
    ProgressPlan update(ProgressPlan progressPlan);

    /**
     * Partially updates a progressPlan.
     *
     * @param progressPlan the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProgressPlan> partialUpdate(ProgressPlan progressPlan);

    /**
     * Get all the progressPlans.
     *
     * @return the list of entities.
     */
    List<ProgressPlan> findAll();

    /**
     * Get all the progressPlans with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProgressPlan> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" progressPlan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProgressPlan> findOne(String id);

    /**
     * Delete the "id" progressPlan.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
