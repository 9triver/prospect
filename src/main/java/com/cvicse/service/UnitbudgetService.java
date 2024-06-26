package com.cvicse.service;

import com.cvicse.domain.Unitbudget;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Unitbudget}.
 */
public interface UnitbudgetService {
    /**
     * Save a unitbudget.
     *
     * @param unitbudget the entity to save.
     * @return the persisted entity.
     */
    Unitbudget save(Unitbudget unitbudget);

    /**
     * Updates a unitbudget.
     *
     * @param unitbudget the entity to update.
     * @return the persisted entity.
     */
    Unitbudget update(Unitbudget unitbudget);

    /**
     * Partially updates a unitbudget.
     *
     * @param unitbudget the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Unitbudget> partialUpdate(Unitbudget unitbudget);

    /**
     * Get all the unitbudgets.
     *
     * @return the list of entities.
     */
    List<Unitbudget> findAll();

    /**
     * Get all the Unitbudget where Comprehensivecontrol is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Unitbudget> findAllWhereComprehensivecontrolIsNull();
    /**
     * Get all the Unitbudget where Auditedbudget is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Unitbudget> findAllWhereAuditedbudgetIsNull();

    /**
     * Get the "id" unitbudget.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Unitbudget> findOne(String id);

    /**
     * Delete the "id" unitbudget.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
