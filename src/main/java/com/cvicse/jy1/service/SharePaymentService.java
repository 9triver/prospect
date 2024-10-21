package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.SharePayment;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.SharePayment}.
 */
public interface SharePaymentService {
    /**
     * Save a sharePayment.
     *
     * @param sharePayment the entity to save.
     * @return the persisted entity.
     */
    SharePayment save(SharePayment sharePayment);

    /**
     * Updates a sharePayment.
     *
     * @param sharePayment the entity to update.
     * @return the persisted entity.
     */
    SharePayment update(SharePayment sharePayment);

    /**
     * Partially updates a sharePayment.
     *
     * @param sharePayment the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SharePayment> partialUpdate(SharePayment sharePayment);

    /**
     * Get all the sharePayments.
     *
     * @return the list of entities.
     */
    List<SharePayment> findAll();

    /**
     * Get the "id" sharePayment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SharePayment> findOne(Integer id);

    /**
     * Delete the "id" sharePayment.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
