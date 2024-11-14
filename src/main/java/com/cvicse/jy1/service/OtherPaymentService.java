package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.OtherPayment;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.OtherPayment}.
 */
public interface OtherPaymentService {
    /**
     * Save a otherPayment.
     *
     * @param otherPayment the entity to save.
     * @return the persisted entity.
     */
    OtherPayment save(OtherPayment otherPayment);

    /**
     * Updates a otherPayment.
     *
     * @param otherPayment the entity to update.
     * @return the persisted entity.
     */
    OtherPayment update(OtherPayment otherPayment);

    /**
     * Partially updates a otherPayment.
     *
     * @param otherPayment the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OtherPayment> partialUpdate(OtherPayment otherPayment);

    /**
     * Get all the otherPayments.
     *
     * @return the list of entities.
     */
    List<OtherPayment> findAll();

    /**
     * Get the "id" otherPayment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OtherPayment> findOne(Integer id);

    /**
     * Delete the "id" otherPayment.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
