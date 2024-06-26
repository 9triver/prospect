package com.cvicse.service;

import com.cvicse.domain.Auditedbudget;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Auditedbudget}.
 */
public interface AuditedbudgetService {
    /**
     * Save a auditedbudget.
     *
     * @param auditedbudget the entity to save.
     * @return the persisted entity.
     */
    Auditedbudget save(Auditedbudget auditedbudget);

    /**
     * Updates a auditedbudget.
     *
     * @param auditedbudget the entity to update.
     * @return the persisted entity.
     */
    Auditedbudget update(Auditedbudget auditedbudget);

    /**
     * Partially updates a auditedbudget.
     *
     * @param auditedbudget the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Auditedbudget> partialUpdate(Auditedbudget auditedbudget);

    /**
     * Get all the auditedbudgets.
     *
     * @return the list of entities.
     */
    List<Auditedbudget> findAll();

    /**
     * Get all the Auditedbudget where Fundsavailability is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Auditedbudget> findAllWhereFundsavailabilityIsNull();

    /**
     * Get the "id" auditedbudget.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Auditedbudget> findOne(String id);

    /**
     * Delete the "id" auditedbudget.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
