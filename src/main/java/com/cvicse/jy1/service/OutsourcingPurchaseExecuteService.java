package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.OutsourcingPurchaseExecute;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.OutsourcingPurchaseExecute}.
 */
public interface OutsourcingPurchaseExecuteService {
    /**
     * Save a outsourcingPurchaseExecute.
     *
     * @param outsourcingPurchaseExecute the entity to save.
     * @return the persisted entity.
     */
    OutsourcingPurchaseExecute save(OutsourcingPurchaseExecute outsourcingPurchaseExecute);

    /**
     * Updates a outsourcingPurchaseExecute.
     *
     * @param outsourcingPurchaseExecute the entity to update.
     * @return the persisted entity.
     */
    OutsourcingPurchaseExecute update(OutsourcingPurchaseExecute outsourcingPurchaseExecute);

    /**
     * Partially updates a outsourcingPurchaseExecute.
     *
     * @param outsourcingPurchaseExecute the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OutsourcingPurchaseExecute> partialUpdate(OutsourcingPurchaseExecute outsourcingPurchaseExecute);

    /**
     * Get all the outsourcingPurchaseExecutes.
     *
     * @return the list of entities.
     */
    List<OutsourcingPurchaseExecute> findAll();

    /**
     * Get the "id" outsourcingPurchaseExecute.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OutsourcingPurchaseExecute> findOne(String id);

    /**
     * Delete the "id" outsourcingPurchaseExecute.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
