package com.cvicse.service;

import com.cvicse.domain.ProjectHumanresourcesplan;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.ProjectHumanresourcesplan}.
 */
public interface ProjectHumanresourcesplanService {
    /**
     * Save a projectHumanresourcesplan.
     *
     * @param projectHumanresourcesplan the entity to save.
     * @return the persisted entity.
     */
    ProjectHumanresourcesplan save(ProjectHumanresourcesplan projectHumanresourcesplan);

    /**
     * Updates a projectHumanresourcesplan.
     *
     * @param projectHumanresourcesplan the entity to update.
     * @return the persisted entity.
     */
    ProjectHumanresourcesplan update(ProjectHumanresourcesplan projectHumanresourcesplan);

    /**
     * Partially updates a projectHumanresourcesplan.
     *
     * @param projectHumanresourcesplan the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProjectHumanresourcesplan> partialUpdate(ProjectHumanresourcesplan projectHumanresourcesplan);

    /**
     * Get all the projectHumanresourcesplans.
     *
     * @return the list of entities.
     */
    List<ProjectHumanresourcesplan> findAll();

    /**
     * Get the "id" projectHumanresourcesplan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProjectHumanresourcesplan> findOne(String id);

    /**
     * Delete the "id" projectHumanresourcesplan.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
