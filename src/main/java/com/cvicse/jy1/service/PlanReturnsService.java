package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.PlanReturns;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.PlanReturns}.
 */
public interface PlanReturnsService {
    /**
     * Save a planReturns.
     *
     * @param planReturns the entity to save.
     * @return the persisted entity.
     */
    PlanReturns save(PlanReturns planReturns);

    /**
     * Updates a planReturns.
     *
     * @param planReturns the entity to update.
     * @return the persisted entity.
     */
    PlanReturns update(PlanReturns planReturns);

    /**
     * Partially updates a planReturns.
     *
     * @param planReturns the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PlanReturns> partialUpdate(PlanReturns planReturns);

    /**
     * Get all the planReturns.
     *
     * @return the list of entities.
     */
    List<PlanReturns> findAll();

    /**
     * Get the "id" planReturns.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PlanReturns> findOne(String id);

    /**
     * Delete the "id" planReturns.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
