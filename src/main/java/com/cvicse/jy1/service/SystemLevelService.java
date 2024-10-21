package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.SystemLevel;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.SystemLevel}.
 */
public interface SystemLevelService {
    /**
     * Save a systemLevel.
     *
     * @param systemLevel the entity to save.
     * @return the persisted entity.
     */
    SystemLevel save(SystemLevel systemLevel);

    /**
     * Updates a systemLevel.
     *
     * @param systemLevel the entity to update.
     * @return the persisted entity.
     */
    SystemLevel update(SystemLevel systemLevel);

    /**
     * Partially updates a systemLevel.
     *
     * @param systemLevel the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SystemLevel> partialUpdate(SystemLevel systemLevel);

    /**
     * Get all the systemLevels.
     *
     * @return the list of entities.
     */
    List<SystemLevel> findAll();

    /**
     * Get the "id" systemLevel.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SystemLevel> findOne(Integer id);

    /**
     * Delete the "id" systemLevel.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
