package com.cvicse.service;

import com.cvicse.domain.Project;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Project}.
 */
public interface ProjectService {
    /**
     * Save a project.
     *
     * @param project the entity to save.
     * @return the persisted entity.
     */
    Project save(Project project);

    /**
     * Updates a project.
     *
     * @param project the entity to update.
     * @return the persisted entity.
     */
    Project update(Project project);

    /**
     * Partially updates a project.
     *
     * @param project the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Project> partialUpdate(Project project);

    /**
     * Get all the projects.
     *
     * @return the list of entities.
     */
    List<Project> findAll();

    /**
     * Get all the Project where Comprehensivecontrol is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Project> findAllWhereComprehensivecontrolIsNull();
    /**
     * Get all the Project where Wbsmanage is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Project> findAllWhereWbsmanageIsNull();
    /**
     * Get all the Project where OutsourcingPurchasePlan is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Project> findAllWhereOutsourcingPurchasePlanIsNull();
    /**
     * Get all the Project where ProjectHumanresourcesplan is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Project> findAllWhereProjectHumanresourcesplanIsNull();
    /**
     * Get all the Project where Projectremit is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Project> findAllWhereProjectremitIsNull();
    /**
     * Get all the Project where Humanresources is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Project> findAllWhereHumanresourcesIsNull();
    /**
     * Get all the Project where AnnualSecurityPlan is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Project> findAllWhereAnnualSecurityPlanIsNull();
    /**
     * Get all the Project where ManagementCapacityEvaluation is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Project> findAllWhereManagementCapacityEvaluationIsNull();

    /**
     * Get the "id" project.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Project> findOne(String id);

    /**
     * Delete the "id" project.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
