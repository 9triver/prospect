package com.cvicse.service;

import com.cvicse.domain.SecuritymanagementWbs;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.SecuritymanagementWbs}.
 */
public interface SecuritymanagementWbsService {
    /**
     * Save a securitymanagementWbs.
     *
     * @param securitymanagementWbs the entity to save.
     * @return the persisted entity.
     */
    SecuritymanagementWbs save(SecuritymanagementWbs securitymanagementWbs);

    /**
     * Updates a securitymanagementWbs.
     *
     * @param securitymanagementWbs the entity to update.
     * @return the persisted entity.
     */
    SecuritymanagementWbs update(SecuritymanagementWbs securitymanagementWbs);

    /**
     * Partially updates a securitymanagementWbs.
     *
     * @param securitymanagementWbs the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SecuritymanagementWbs> partialUpdate(SecuritymanagementWbs securitymanagementWbs);

    /**
     * Get all the securitymanagementWbs.
     *
     * @return the list of entities.
     */
    List<SecuritymanagementWbs> findAll();

    /**
     * Get all the SecuritymanagementWbs where Securitymanagement is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<SecuritymanagementWbs> findAllWhereSecuritymanagementIsNull();

    /**
     * Get the "id" securitymanagementWbs.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SecuritymanagementWbs> findOne(String id);

    /**
     * Delete the "id" securitymanagementWbs.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
