package com.cvicse.service;

import com.cvicse.domain.Totalbudget;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Totalbudget}.
 */
public interface TotalbudgetService {
    /**
     * Save a totalbudget.
     *
     * @param totalbudget the entity to save.
     * @return the persisted entity.
     */
    Totalbudget save(Totalbudget totalbudget);

    /**
     * Updates a totalbudget.
     *
     * @param totalbudget the entity to update.
     * @return the persisted entity.
     */
    Totalbudget update(Totalbudget totalbudget);

    /**
     * Partially updates a totalbudget.
     *
     * @param totalbudget the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Totalbudget> partialUpdate(Totalbudget totalbudget);

    /**
     * Get all the totalbudgets.
     *
     * @return the list of entities.
     */
    List<Totalbudget> findAll();

    /**
     * Get all the Totalbudget where Comprehensivecontrol is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Totalbudget> findAllWhereComprehensivecontrolIsNull();
    /**
     * Get all the Totalbudget where Auditedbudget is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Totalbudget> findAllWhereAuditedbudgetIsNull();

    /**
     * Get the "id" totalbudget.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Totalbudget> findOne(String id);

    /**
     * Delete the "id" totalbudget.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
