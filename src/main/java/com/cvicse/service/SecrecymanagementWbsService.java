package com.cvicse.service;

import com.cvicse.domain.SecrecymanagementWbs;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.SecrecymanagementWbs}.
 */
public interface SecrecymanagementWbsService {
    /**
     * Save a secrecymanagementWbs.
     *
     * @param secrecymanagementWbs the entity to save.
     * @return the persisted entity.
     */
    SecrecymanagementWbs save(SecrecymanagementWbs secrecymanagementWbs);

    /**
     * Updates a secrecymanagementWbs.
     *
     * @param secrecymanagementWbs the entity to update.
     * @return the persisted entity.
     */
    SecrecymanagementWbs update(SecrecymanagementWbs secrecymanagementWbs);

    /**
     * Partially updates a secrecymanagementWbs.
     *
     * @param secrecymanagementWbs the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SecrecymanagementWbs> partialUpdate(SecrecymanagementWbs secrecymanagementWbs);

    /**
     * Get all the secrecymanagementWbs.
     *
     * @return the list of entities.
     */
    List<SecrecymanagementWbs> findAll();

    /**
     * Get all the SecrecymanagementWbs where Secrecymanagement is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<SecrecymanagementWbs> findAllWhereSecrecymanagementIsNull();

    /**
     * Get the "id" secrecymanagementWbs.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SecrecymanagementWbs> findOne(String id);

    /**
     * Delete the "id" secrecymanagementWbs.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
