package com.cvicse.service;

import com.cvicse.domain.Permission;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Permission}.
 */
public interface PermissionService {
    /**
     * Save a permission.
     *
     * @param permission the entity to save.
     * @return the persisted entity.
     */
    Permission save(Permission permission);

    /**
     * Updates a permission.
     *
     * @param permission the entity to update.
     * @return the persisted entity.
     */
    Permission update(Permission permission);

    /**
     * Partially updates a permission.
     *
     * @param permission the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Permission> partialUpdate(Permission permission);

    /**
     * Get all the permissions.
     *
     * @return the list of entities.
     */
    List<Permission> findAll();

    /**
     * Get all the Permission where Role is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Permission> findAllWhereRoleIsNull();

    /**
     * Get the "id" permission.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Permission> findOne(String id);

    /**
     * Delete the "id" permission.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
