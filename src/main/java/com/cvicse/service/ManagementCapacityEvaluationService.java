package com.cvicse.service;

import com.cvicse.domain.ManagementCapacityEvaluation;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.ManagementCapacityEvaluation}.
 */
public interface ManagementCapacityEvaluationService {
    /**
     * Save a managementCapacityEvaluation.
     *
     * @param managementCapacityEvaluation the entity to save.
     * @return the persisted entity.
     */
    ManagementCapacityEvaluation save(ManagementCapacityEvaluation managementCapacityEvaluation);

    /**
     * Updates a managementCapacityEvaluation.
     *
     * @param managementCapacityEvaluation the entity to update.
     * @return the persisted entity.
     */
    ManagementCapacityEvaluation update(ManagementCapacityEvaluation managementCapacityEvaluation);

    /**
     * Partially updates a managementCapacityEvaluation.
     *
     * @param managementCapacityEvaluation the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ManagementCapacityEvaluation> partialUpdate(ManagementCapacityEvaluation managementCapacityEvaluation);

    /**
     * Get all the managementCapacityEvaluations.
     *
     * @return the list of entities.
     */
    List<ManagementCapacityEvaluation> findAll();

    /**
     * Get the "id" managementCapacityEvaluation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ManagementCapacityEvaluation> findOne(String id);

    /**
     * Delete the "id" managementCapacityEvaluation.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
