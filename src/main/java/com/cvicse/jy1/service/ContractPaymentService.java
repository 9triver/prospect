package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.ContractPayment;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.ContractPayment}.
 */
public interface ContractPaymentService {
    /**
     * Save a contractPayment.
     *
     * @param contractPayment the entity to save.
     * @return the persisted entity.
     */
    ContractPayment save(ContractPayment contractPayment);

    /**
     * Updates a contractPayment.
     *
     * @param contractPayment the entity to update.
     * @return the persisted entity.
     */
    ContractPayment update(ContractPayment contractPayment);

    /**
     * Partially updates a contractPayment.
     *
     * @param contractPayment the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ContractPayment> partialUpdate(ContractPayment contractPayment);

    /**
     * Get all the contractPayments.
     *
     * @return the list of entities.
     */
    List<ContractPayment> findAll();

    /**
     * Get the "id" contractPayment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ContractPayment> findOne(Integer id);

    /**
     * Delete the "id" contractPayment.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
