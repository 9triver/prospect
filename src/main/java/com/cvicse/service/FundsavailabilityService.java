package com.cvicse.service;

import com.cvicse.domain.Fundsavailability;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Fundsavailability}.
 */
public interface FundsavailabilityService {
    /**
     * Save a fundsavailability.
     *
     * @param fundsavailability the entity to save.
     * @return the persisted entity.
     */
    Fundsavailability save(Fundsavailability fundsavailability);

    /**
     * Updates a fundsavailability.
     *
     * @param fundsavailability the entity to update.
     * @return the persisted entity.
     */
    Fundsavailability update(Fundsavailability fundsavailability);

    /**
     * Partially updates a fundsavailability.
     *
     * @param fundsavailability the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Fundsavailability> partialUpdate(Fundsavailability fundsavailability);

    /**
     * Get all the fundsavailabilities.
     *
     * @return the list of entities.
     */
    List<Fundsavailability> findAll();

    /**
     * Get the "id" fundsavailability.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Fundsavailability> findOne(String id);

    /**
     * Delete the "id" fundsavailability.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
