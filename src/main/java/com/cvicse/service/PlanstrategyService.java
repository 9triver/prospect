package com.cvicse.service;

import com.cvicse.domain.Planstrategy;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Planstrategy}.
 */
public interface PlanstrategyService {
    /**
     * Save a planstrategy.
     *
     * @param planstrategy the entity to save.
     * @return the persisted entity.
     */
    Planstrategy save(Planstrategy planstrategy);

    /**
     * Updates a planstrategy.
     *
     * @param planstrategy the entity to update.
     * @return the persisted entity.
     */
    Planstrategy update(Planstrategy planstrategy);

    /**
     * Partially updates a planstrategy.
     *
     * @param planstrategy the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Planstrategy> partialUpdate(Planstrategy planstrategy);

    /**
     * Get all the planstrategies.
     *
     * @return the list of entities.
     */
    List<Planstrategy> findAll();

    /**
     * Get the "id" planstrategy.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Planstrategy> findOne(String id);

    /**
     * Delete the "id" planstrategy.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
