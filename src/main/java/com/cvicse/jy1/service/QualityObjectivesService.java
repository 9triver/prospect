package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.QualityObjectives;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.QualityObjectives}.
 */
public interface QualityObjectivesService {
    /**
     * Save a qualityObjectives.
     *
     * @param qualityObjectives the entity to save.
     * @return the persisted entity.
     */
    QualityObjectives save(QualityObjectives qualityObjectives);

    /**
     * Updates a qualityObjectives.
     *
     * @param qualityObjectives the entity to update.
     * @return the persisted entity.
     */
    QualityObjectives update(QualityObjectives qualityObjectives);

    /**
     * Partially updates a qualityObjectives.
     *
     * @param qualityObjectives the entity to update partially.
     * @return the persisted entity.
     */
    Optional<QualityObjectives> partialUpdate(QualityObjectives qualityObjectives);

    /**
     * Get all the qualityObjectives.
     *
     * @return the list of entities.
     */
    List<QualityObjectives> findAll();

    /**
     * Get all the qualityObjectives with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QualityObjectives> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" qualityObjectives.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QualityObjectives> findOne(String id);

    /**
     * Delete the "id" qualityObjectives.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
