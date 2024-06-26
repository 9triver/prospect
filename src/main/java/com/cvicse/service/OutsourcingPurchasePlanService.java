package com.cvicse.service;

import com.cvicse.domain.OutsourcingPurchasePlan;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.OutsourcingPurchasePlan}.
 */
public interface OutsourcingPurchasePlanService {
    /**
     * Save a outsourcingPurchasePlan.
     *
     * @param outsourcingPurchasePlan the entity to save.
     * @return the persisted entity.
     */
    OutsourcingPurchasePlan save(OutsourcingPurchasePlan outsourcingPurchasePlan);

    /**
     * Updates a outsourcingPurchasePlan.
     *
     * @param outsourcingPurchasePlan the entity to update.
     * @return the persisted entity.
     */
    OutsourcingPurchasePlan update(OutsourcingPurchasePlan outsourcingPurchasePlan);

    /**
     * Partially updates a outsourcingPurchasePlan.
     *
     * @param outsourcingPurchasePlan the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OutsourcingPurchasePlan> partialUpdate(OutsourcingPurchasePlan outsourcingPurchasePlan);

    /**
     * Get all the outsourcingPurchasePlans.
     *
     * @return the list of entities.
     */
    List<OutsourcingPurchasePlan> findAll();

    /**
     * Get all the OutsourcingPurchasePlan where OutsourcingPurchaseExecute is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<OutsourcingPurchasePlan> findAllWhereOutsourcingPurchaseExecuteIsNull();

    /**
     * Get the "id" outsourcingPurchasePlan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OutsourcingPurchasePlan> findOne(String id);

    /**
     * Delete the "id" outsourcingPurchasePlan.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
