package com.cvicse.service;

import com.cvicse.domain.IntegratedmanagementWbs;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.IntegratedmanagementWbs}.
 */
public interface IntegratedmanagementWbsService {
    /**
     * Save a integratedmanagementWbs.
     *
     * @param integratedmanagementWbs the entity to save.
     * @return the persisted entity.
     */
    IntegratedmanagementWbs save(IntegratedmanagementWbs integratedmanagementWbs);

    /**
     * Updates a integratedmanagementWbs.
     *
     * @param integratedmanagementWbs the entity to update.
     * @return the persisted entity.
     */
    IntegratedmanagementWbs update(IntegratedmanagementWbs integratedmanagementWbs);

    /**
     * Partially updates a integratedmanagementWbs.
     *
     * @param integratedmanagementWbs the entity to update partially.
     * @return the persisted entity.
     */
    Optional<IntegratedmanagementWbs> partialUpdate(IntegratedmanagementWbs integratedmanagementWbs);

    /**
     * Get all the integratedmanagementWbs.
     *
     * @return the list of entities.
     */
    List<IntegratedmanagementWbs> findAll();

    /**
     * Get all the IntegratedmanagementWbs where Integratedmanagement is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<IntegratedmanagementWbs> findAllWhereIntegratedmanagementIsNull();

    /**
     * Get the "id" integratedmanagementWbs.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<IntegratedmanagementWbs> findOne(String id);

    /**
     * Delete the "id" integratedmanagementWbs.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
