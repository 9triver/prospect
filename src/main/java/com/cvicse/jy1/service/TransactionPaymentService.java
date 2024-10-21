package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.TransactionPayment;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.TransactionPayment}.
 */
public interface TransactionPaymentService {
    /**
     * Save a transactionPayment.
     *
     * @param transactionPayment the entity to save.
     * @return the persisted entity.
     */
    TransactionPayment save(TransactionPayment transactionPayment);

    /**
     * Updates a transactionPayment.
     *
     * @param transactionPayment the entity to update.
     * @return the persisted entity.
     */
    TransactionPayment update(TransactionPayment transactionPayment);

    /**
     * Partially updates a transactionPayment.
     *
     * @param transactionPayment the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TransactionPayment> partialUpdate(TransactionPayment transactionPayment);

    /**
     * Get all the transactionPayments.
     *
     * @return the list of entities.
     */
    List<TransactionPayment> findAll();

    /**
     * Get the "id" transactionPayment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TransactionPayment> findOne(Integer id);

    /**
     * Delete the "id" transactionPayment.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
