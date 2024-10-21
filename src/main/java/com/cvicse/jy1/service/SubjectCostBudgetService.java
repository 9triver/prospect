package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.SubjectCostBudget;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.SubjectCostBudget}.
 */
public interface SubjectCostBudgetService {
    /**
     * Save a subjectCostBudget.
     *
     * @param subjectCostBudget the entity to save.
     * @return the persisted entity.
     */
    SubjectCostBudget save(SubjectCostBudget subjectCostBudget);

    /**
     * Updates a subjectCostBudget.
     *
     * @param subjectCostBudget the entity to update.
     * @return the persisted entity.
     */
    SubjectCostBudget update(SubjectCostBudget subjectCostBudget);

    /**
     * Partially updates a subjectCostBudget.
     *
     * @param subjectCostBudget the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SubjectCostBudget> partialUpdate(SubjectCostBudget subjectCostBudget);

    /**
     * Get all the subjectCostBudgets.
     *
     * @return the list of entities.
     */
    List<SubjectCostBudget> findAll();

    /**
     * Get the "id" subjectCostBudget.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SubjectCostBudget> findOne(Long id);

    /**
     * Delete the "id" subjectCostBudget.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
