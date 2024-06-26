package com.cvicse.service;

import com.cvicse.domain.OutsourcingmanagementWbs;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.OutsourcingmanagementWbs}.
 */
public interface OutsourcingmanagementWbsService {
    /**
     * Save a outsourcingmanagementWbs.
     *
     * @param outsourcingmanagementWbs the entity to save.
     * @return the persisted entity.
     */
    OutsourcingmanagementWbs save(OutsourcingmanagementWbs outsourcingmanagementWbs);

    /**
     * Updates a outsourcingmanagementWbs.
     *
     * @param outsourcingmanagementWbs the entity to update.
     * @return the persisted entity.
     */
    OutsourcingmanagementWbs update(OutsourcingmanagementWbs outsourcingmanagementWbs);

    /**
     * Partially updates a outsourcingmanagementWbs.
     *
     * @param outsourcingmanagementWbs the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OutsourcingmanagementWbs> partialUpdate(OutsourcingmanagementWbs outsourcingmanagementWbs);

    /**
     * Get all the outsourcingmanagementWbs.
     *
     * @return the list of entities.
     */
    List<OutsourcingmanagementWbs> findAll();

    /**
     * Get all the OutsourcingmanagementWbs where Outsourcingmanagement is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<OutsourcingmanagementWbs> findAllWhereOutsourcingmanagementIsNull();

    /**
     * Get the "id" outsourcingmanagementWbs.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OutsourcingmanagementWbs> findOne(String id);

    /**
     * Delete the "id" outsourcingmanagementWbs.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
