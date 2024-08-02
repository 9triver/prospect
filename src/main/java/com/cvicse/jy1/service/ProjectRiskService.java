package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.ProjectRisk;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * Get all the projectRisks with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProjectRisk> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" projectRisk.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProjectRisk> findOne(String id);

    /**
     * Delete the "id" projectRisk.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
