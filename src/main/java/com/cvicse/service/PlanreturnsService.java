package com.cvicse.service;

import com.cvicse.domain.Planreturns;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Planreturns}.
 */
public interface PlanreturnsService {
    /**
     * Save a planreturns.
     *
     * @param planreturns the entity to save.
     * @return the persisted entity.
     */
    Planreturns save(Planreturns planreturns);

    /**
     * Updates a planreturns.
     *
     * @param planreturns the entity to update.
     * @return the persisted entity.
     */
    Planreturns update(Planreturns planreturns);

    /**
     * Partially updates a planreturns.
     *
     * @param planreturns the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Planreturns> partialUpdate(Planreturns planreturns);

    /**
     * Get all the planreturns.
     *
     * @return the list of entities.
     */
    List<Planreturns> findAll();

    /**
     * Get all the Planreturns where Planexecute is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Planreturns> findAllWherePlanexecuteIsNull();
    /**
     * Get all the Planreturns where Progressplan is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Planreturns> findAllWhereProgressplanIsNull();

    /**
     * Get the "id" planreturns.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Planreturns> findOne(String id);

    /**
     * Delete the "id" planreturns.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
