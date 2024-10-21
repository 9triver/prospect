package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.ProjectRisk;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.ProjectRisk}.
 */
public interface ProjectRiskService {
    /**
     * Save a projectRisk.
     *
     * @param projectRisk the entity to save.
     * @return the persisted entity.
     */
    ProjectRisk save(ProjectRisk projectRisk);

    /**
     * Updates a projectRisk.
     *
     * @param projectRisk the entity to update.
     * @return the persisted entity.
     */
    ProjectRisk update(ProjectRisk projectRisk);

    /**
     * Partially updates a projectRisk.
     *
     * @param projectRisk the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProjectRisk> partialUpdate(ProjectRisk projectRisk);

    /**
     * Get all the projectRisks.
     *
     * @return the list of entities.
     */
    List<ProjectRisk> findAll();

    /**
     * Get the "id" projectRisk.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProjectRisk> findOne(Integer id);

    /**
     * Delete the "id" projectRisk.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
