package com.cvicse.service;

import com.cvicse.domain.TechnicalmanagementWbs;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.TechnicalmanagementWbs}.
 */
public interface TechnicalmanagementWbsService {
    /**
     * Save a technicalmanagementWbs.
     *
     * @param technicalmanagementWbs the entity to save.
     * @return the persisted entity.
     */
    TechnicalmanagementWbs save(TechnicalmanagementWbs technicalmanagementWbs);

    /**
     * Updates a technicalmanagementWbs.
     *
     * @param technicalmanagementWbs the entity to update.
     * @return the persisted entity.
     */
    TechnicalmanagementWbs update(TechnicalmanagementWbs technicalmanagementWbs);

    /**
     * Partially updates a technicalmanagementWbs.
     *
     * @param technicalmanagementWbs the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TechnicalmanagementWbs> partialUpdate(TechnicalmanagementWbs technicalmanagementWbs);

    /**
     * Get all the technicalmanagementWbs.
     *
     * @return the list of entities.
     */
    List<TechnicalmanagementWbs> findAll();

    /**
     * Get all the TechnicalmanagementWbs where Technicalmanagement is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<TechnicalmanagementWbs> findAllWhereTechnicalmanagementIsNull();

    /**
     * Get the "id" technicalmanagementWbs.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TechnicalmanagementWbs> findOne(String id);

    /**
     * Delete the "id" technicalmanagementWbs.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
