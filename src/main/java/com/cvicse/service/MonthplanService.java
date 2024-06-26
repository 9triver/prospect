package com.cvicse.service;

import com.cvicse.domain.Monthplan;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Monthplan}.
 */
public interface MonthplanService {
    /**
     * Save a monthplan.
     *
     * @param monthplan the entity to save.
     * @return the persisted entity.
     */
    Monthplan save(Monthplan monthplan);

    /**
     * Updates a monthplan.
     *
     * @param monthplan the entity to update.
     * @return the persisted entity.
     */
    Monthplan update(Monthplan monthplan);

    /**
     * Partially updates a monthplan.
     *
     * @param monthplan the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Monthplan> partialUpdate(Monthplan monthplan);

    /**
     * Get all the monthplans.
     *
     * @return the list of entities.
     */
    List<Monthplan> findAll();

    /**
     * Get all the Monthplan where Cycleplan is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Monthplan> findAllWhereCycleplanIsNull();
    /**
     * Get all the Monthplan where Annualplan is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Monthplan> findAllWhereAnnualplanIsNull();

    /**
     * Get the "id" monthplan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Monthplan> findOne(String id);

    /**
     * Delete the "id" monthplan.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
