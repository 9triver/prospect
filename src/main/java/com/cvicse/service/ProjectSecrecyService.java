package com.cvicse.service;

import com.cvicse.domain.ProjectSecrecy;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.ProjectSecrecy}.
 */
public interface ProjectSecrecyService {
    /**
     * Save a projectSecrecy.
     *
     * @param projectSecrecy the entity to save.
     * @return the persisted entity.
     */
    ProjectSecrecy save(ProjectSecrecy projectSecrecy);

    /**
     * Updates a projectSecrecy.
     *
     * @param projectSecrecy the entity to update.
     * @return the persisted entity.
     */
    ProjectSecrecy update(ProjectSecrecy projectSecrecy);

    /**
     * Partially updates a projectSecrecy.
     *
     * @param projectSecrecy the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProjectSecrecy> partialUpdate(ProjectSecrecy projectSecrecy);

    /**
     * Get all the projectSecrecies.
     *
     * @return the list of entities.
     */
    List<ProjectSecrecy> findAll();

    /**
     * Get the "id" projectSecrecy.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProjectSecrecy> findOne(Long id);

    /**
     * Delete the "id" projectSecrecy.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
