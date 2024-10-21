package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.KeyNodeInspection;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.KeyNodeInspection}.
 */
public interface KeyNodeInspectionService {
    /**
     * Save a keyNodeInspection.
     *
     * @param keyNodeInspection the entity to save.
     * @return the persisted entity.
     */
    KeyNodeInspection save(KeyNodeInspection keyNodeInspection);

    /**
     * Updates a keyNodeInspection.
     *
     * @param keyNodeInspection the entity to update.
     * @return the persisted entity.
     */
    KeyNodeInspection update(KeyNodeInspection keyNodeInspection);

    /**
     * Partially updates a keyNodeInspection.
     *
     * @param keyNodeInspection the entity to update partially.
     * @return the persisted entity.
     */
    Optional<KeyNodeInspection> partialUpdate(KeyNodeInspection keyNodeInspection);

    /**
     * Get all the keyNodeInspections.
     *
     * @return the list of entities.
     */
    List<KeyNodeInspection> findAll();

    /**
     * Get the "id" keyNodeInspection.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<KeyNodeInspection> findOne(Integer id);

    /**
     * Delete the "id" keyNodeInspection.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
