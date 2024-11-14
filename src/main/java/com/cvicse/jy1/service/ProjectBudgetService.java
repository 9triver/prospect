package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.ProjectBudget;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.ProjectBudget}.
 */
public interface ProjectBudgetService {
    /**
     * Save a projectBudget.
     *
     * @param projectBudget the entity to save.
     * @return the persisted entity.
     */
    ProjectBudget save(ProjectBudget projectBudget);

    /**
     * Updates a projectBudget.
     *
     * @param projectBudget the entity to update.
     * @return the persisted entity.
     */
    ProjectBudget update(ProjectBudget projectBudget);

    /**
     * Partially updates a projectBudget.
     *
     * @param projectBudget the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProjectBudget> partialUpdate(ProjectBudget projectBudget);

    /**
     * Get all the projectBudgets.
     *
     * @return the list of entities.
     */
    List<ProjectBudget> findAll();

    /**
     * Get the "id" projectBudget.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProjectBudget> findOne(Long id);

    /**
     * Delete the "id" projectBudget.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
