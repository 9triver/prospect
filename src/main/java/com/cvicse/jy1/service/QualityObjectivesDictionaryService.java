package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.QualityObjectivesDictionary;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.QualityObjectivesDictionary}.
 */
public interface QualityObjectivesDictionaryService {
    /**
     * Save a qualityObjectivesDictionary.
     *
     * @param qualityObjectivesDictionary the entity to save.
     * @return the persisted entity.
     */
    QualityObjectivesDictionary save(QualityObjectivesDictionary qualityObjectivesDictionary);

    /**
     * Updates a qualityObjectivesDictionary.
     *
     * @param qualityObjectivesDictionary the entity to update.
     * @return the persisted entity.
     */
    QualityObjectivesDictionary update(QualityObjectivesDictionary qualityObjectivesDictionary);

    /**
     * Partially updates a qualityObjectivesDictionary.
     *
     * @param qualityObjectivesDictionary the entity to update partially.
     * @return the persisted entity.
     */
    Optional<QualityObjectivesDictionary> partialUpdate(QualityObjectivesDictionary qualityObjectivesDictionary);

    /**
     * Get all the qualityObjectivesDictionaries.
     *
     * @return the list of entities.
     */
    List<QualityObjectivesDictionary> findAll();

    /**
     * Get the "id" qualityObjectivesDictionary.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QualityObjectivesDictionary> findOne(Integer id);

    /**
     * Delete the "id" qualityObjectivesDictionary.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
