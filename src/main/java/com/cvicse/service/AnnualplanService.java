package com.cvicse.service;

import com.cvicse.domain.Annualplan;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Annualplan}.
 */
public interface AnnualplanService {
    /**
     * Save a annualplan.
     *
     * @param annualplan the entity to save.
     * @return the persisted entity.
     */
    Annualplan save(Annualplan annualplan);

    /**
     * Updates a annualplan.
     *
     * @param annualplan the entity to update.
     * @return the persisted entity.
     */
    Annualplan update(Annualplan annualplan);

    /**
     * Partially updates a annualplan.
     *
     * @param annualplan the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Annualplan> partialUpdate(Annualplan annualplan);

    /**
     * Get all the annualplans.
     *
     * @return the list of entities.
     */
    List<Annualplan> findAll();

    /**
     * Get all the Annualplan where Cycleplan is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Annualplan> findAllWhereCycleplanIsNull();

    /**
     * Get the "id" annualplan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Annualplan> findOne(String id);

    /**
     * Delete the "id" annualplan.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
