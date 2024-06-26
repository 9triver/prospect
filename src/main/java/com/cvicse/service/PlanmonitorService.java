package com.cvicse.service;

import com.cvicse.domain.Planmonitor;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Planmonitor}.
 */
public interface PlanmonitorService {
    /**
     * Save a planmonitor.
     *
     * @param planmonitor the entity to save.
     * @return the persisted entity.
     */
    Planmonitor save(Planmonitor planmonitor);

    /**
     * Updates a planmonitor.
     *
     * @param planmonitor the entity to update.
     * @return the persisted entity.
     */
    Planmonitor update(Planmonitor planmonitor);

    /**
     * Partially updates a planmonitor.
     *
     * @param planmonitor the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Planmonitor> partialUpdate(Planmonitor planmonitor);

    /**
     * Get all the planmonitors.
     *
     * @return the list of entities.
     */
    List<Planmonitor> findAll();

    /**
     * Get the "id" planmonitor.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Planmonitor> findOne(String id);

    /**
     * Delete the "id" planmonitor.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
