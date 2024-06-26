package com.cvicse.service;

import com.cvicse.domain.Role;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Role}.
 */
public interface RoleService {
    /**
     * Save a role.
     *
     * @param role the entity to save.
     * @return the persisted entity.
     */
    Role save(Role role);

    /**
     * Updates a role.
     *
     * @param role the entity to update.
     * @return the persisted entity.
     */
    Role update(Role role);

    /**
     * Partially updates a role.
     *
     * @param role the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Role> partialUpdate(Role role);

    /**
     * Get all the roles.
     *
     * @return the list of entities.
     */
    List<Role> findAll();

    /**
     * Get all the Role where Officers is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Role> findAllWhereOfficersIsNull();

    /**
     * Get the "id" role.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Role> findOne(String id);

    /**
     * Delete the "id" role.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
