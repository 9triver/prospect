package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.ProjectTotalwbs;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.ProjectTotalwbs}.
 */
public interface ProjectTotalwbsService {
    /**
     * Save a projectTotalwbs.
     *
     * @param projectTotalwbs the entity to save.
     * @return the persisted entity.
     */
    ProjectTotalwbs save(ProjectTotalwbs projectTotalwbs);

    /**
     * Updates a projectTotalwbs.
     *
     * @param projectTotalwbs the entity to update.
     * @return the persisted entity.
     */
    ProjectTotalwbs update(ProjectTotalwbs projectTotalwbs);

    /**
     * Partially updates a projectTotalwbs.
     *
     * @param projectTotalwbs the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProjectTotalwbs> partialUpdate(ProjectTotalwbs projectTotalwbs);

    /**
     * Get all the projectTotalwbs.
     *
     * @return the list of entities.
     */
    List<ProjectTotalwbs> findAll();

    /**
     * Get the "id" projectTotalwbs.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProjectTotalwbs> findOne(String id);

    /**
     * Delete the "id" projectTotalwbs.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
