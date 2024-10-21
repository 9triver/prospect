package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.SporadicPurchasePayment;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.SporadicPurchasePayment}.
 */
public interface SporadicPurchasePaymentService {
    /**
     * Save a sporadicPurchasePayment.
     *
     * @param sporadicPurchasePayment the entity to save.
     * @return the persisted entity.
     */
    SporadicPurchasePayment save(SporadicPurchasePayment sporadicPurchasePayment);

    /**
     * Updates a sporadicPurchasePayment.
     *
     * @param sporadicPurchasePayment the entity to update.
     * @return the persisted entity.
     */
    SporadicPurchasePayment update(SporadicPurchasePayment sporadicPurchasePayment);

    /**
     * Partially updates a sporadicPurchasePayment.
     *
     * @param sporadicPurchasePayment the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SporadicPurchasePayment> partialUpdate(SporadicPurchasePayment sporadicPurchasePayment);

    /**
     * Get all the sporadicPurchasePayments.
     *
     * @return the list of entities.
     */
    List<SporadicPurchasePayment> findAll();

    /**
     * Get the "id" sporadicPurchasePayment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SporadicPurchasePayment> findOne(Integer id);

    /**
     * Delete the "id" sporadicPurchasePayment.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
