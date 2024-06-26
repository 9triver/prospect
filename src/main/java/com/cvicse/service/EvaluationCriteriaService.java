package com.cvicse.service;

import com.cvicse.domain.EvaluationCriteria;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.EvaluationCriteria}.
 */
public interface EvaluationCriteriaService {
    /**
     * Save a evaluationCriteria.
     *
     * @param evaluationCriteria the entity to save.
     * @return the persisted entity.
     */
    EvaluationCriteria save(EvaluationCriteria evaluationCriteria);

    /**
     * Updates a evaluationCriteria.
     *
     * @param evaluationCriteria the entity to update.
     * @return the persisted entity.
     */
    EvaluationCriteria update(EvaluationCriteria evaluationCriteria);

    /**
     * Partially updates a evaluationCriteria.
     *
     * @param evaluationCriteria the entity to update partially.
     * @return the persisted entity.
     */
    Optional<EvaluationCriteria> partialUpdate(EvaluationCriteria evaluationCriteria);

    /**
     * Get all the evaluationCriteria.
     *
     * @return the list of entities.
     */
    List<EvaluationCriteria> findAll();

    /**
     * Get all the EvaluationCriteria where ManagementCapacityEvaluation is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<EvaluationCriteria> findAllWhereManagementCapacityEvaluationIsNull();

    /**
     * Get the "id" evaluationCriteria.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EvaluationCriteria> findOne(String id);

    /**
     * Delete the "id" evaluationCriteria.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
