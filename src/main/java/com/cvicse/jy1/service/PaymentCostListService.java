package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.PaymentCostList;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.PaymentCostList}.
 */
public interface PaymentCostListService {
    /**
     * Save a paymentCostList.
     *
     * @param paymentCostList the entity to save.
     * @return the persisted entity.
     */
    PaymentCostList save(PaymentCostList paymentCostList);

    /**
     * Updates a paymentCostList.
     *
     * @param paymentCostList the entity to update.
     * @return the persisted entity.
     */
    PaymentCostList update(PaymentCostList paymentCostList);

    /**
     * Partially updates a paymentCostList.
     *
     * @param paymentCostList the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PaymentCostList> partialUpdate(PaymentCostList paymentCostList);

    /**
     * Get all the paymentCostLists.
     *
     * @return the list of entities.
     */
    List<PaymentCostList> findAll();

    /**
     * Get the "id" paymentCostList.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PaymentCostList> findOne(Integer id);

    /**
     * Delete the "id" paymentCostList.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
