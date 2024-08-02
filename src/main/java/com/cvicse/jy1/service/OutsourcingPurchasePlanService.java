package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.OutsourcingPurchasePlan;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.OutsourcingPurchasePlan}.
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
     * Get all the outsourcingPurchasePlans with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OutsourcingPurchasePlan> findAllWithEagerRelationships(Pageable pageable);

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
