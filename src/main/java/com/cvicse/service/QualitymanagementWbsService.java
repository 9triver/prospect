package com.cvicse.service;

import com.cvicse.domain.QualitymanagementWbs;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.QualitymanagementWbs}.
 */
public interface QualitymanagementWbsService {
    /**
     * Save a qualitymanagementWbs.
     *
     * @param qualitymanagementWbs the entity to save.
     * @return the persisted entity.
     */
    QualitymanagementWbs save(QualitymanagementWbs qualitymanagementWbs);

    /**
     * Updates a qualitymanagementWbs.
     *
     * @param qualitymanagementWbs the entity to update.
     * @return the persisted entity.
     */
    QualitymanagementWbs update(QualitymanagementWbs qualitymanagementWbs);

    /**
     * Partially updates a qualitymanagementWbs.
     *
     * @param qualitymanagementWbs the entity to update partially.
     * @return the persisted entity.
     */
    Optional<QualitymanagementWbs> partialUpdate(QualitymanagementWbs qualitymanagementWbs);

    /**
     * Get all the qualitymanagementWbs.
     *
     * @return the list of entities.
     */
    List<QualitymanagementWbs> findAll();

    /**
     * Get all the QualitymanagementWbs where Qualitymanagement is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<QualitymanagementWbs> findAllWhereQualitymanagementIsNull();

    /**
     * Get the "id" qualitymanagementWbs.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QualitymanagementWbs> findOne(String id);

    /**
     * Delete the "id" qualitymanagementWbs.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
