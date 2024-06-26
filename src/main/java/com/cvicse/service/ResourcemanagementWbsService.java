package com.cvicse.service;

import com.cvicse.domain.ResourcemanagementWbs;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.ResourcemanagementWbs}.
 */
public interface ResourcemanagementWbsService {
    /**
     * Save a resourcemanagementWbs.
     *
     * @param resourcemanagementWbs the entity to save.
     * @return the persisted entity.
     */
    ResourcemanagementWbs save(ResourcemanagementWbs resourcemanagementWbs);

    /**
     * Updates a resourcemanagementWbs.
     *
     * @param resourcemanagementWbs the entity to update.
     * @return the persisted entity.
     */
    ResourcemanagementWbs update(ResourcemanagementWbs resourcemanagementWbs);

    /**
     * Partially updates a resourcemanagementWbs.
     *
     * @param resourcemanagementWbs the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ResourcemanagementWbs> partialUpdate(ResourcemanagementWbs resourcemanagementWbs);

    /**
     * Get all the resourcemanagementWbs.
     *
     * @return the list of entities.
     */
    List<ResourcemanagementWbs> findAll();

    /**
     * Get all the ResourcemanagementWbs where Resourcemanagement is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<ResourcemanagementWbs> findAllWhereResourcemanagementIsNull();

    /**
     * Get the "id" resourcemanagementWbs.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ResourcemanagementWbs> findOne(String id);

    /**
     * Delete the "id" resourcemanagementWbs.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
