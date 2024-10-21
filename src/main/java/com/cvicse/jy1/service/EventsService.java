package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.Events;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.Events}.
 */
public interface EventsService {
    /**
     * Save a events.
     *
     * @param events the entity to save.
     * @return the persisted entity.
     */
    Events save(Events events);

    /**
     * Updates a events.
     *
     * @param events the entity to update.
     * @return the persisted entity.
     */
    Events update(Events events);

    /**
     * Partially updates a events.
     *
     * @param events the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Events> partialUpdate(Events events);

    /**
     * Get all the events.
     *
     * @return the list of entities.
     */
    List<Events> findAll();

    /**
     * Get the "id" events.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Events> findOne(String id);

    /**
     * Delete the "id" events.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
