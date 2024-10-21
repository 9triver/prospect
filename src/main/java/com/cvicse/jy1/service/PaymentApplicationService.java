package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.PaymentApplication;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.PaymentApplication}.
 */
public interface PaymentApplicationService {
    /**
     * Save a paymentApplication.
     *
     * @param paymentApplication the entity to save.
     * @return the persisted entity.
     */
    PaymentApplication save(PaymentApplication paymentApplication);

    /**
     * Updates a paymentApplication.
     *
     * @param paymentApplication the entity to update.
     * @return the persisted entity.
     */
    PaymentApplication update(PaymentApplication paymentApplication);

    /**
     * Partially updates a paymentApplication.
     *
     * @param paymentApplication the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PaymentApplication> partialUpdate(PaymentApplication paymentApplication);

    /**
     * Get all the paymentApplications.
     *
     * @return the list of entities.
     */
    List<PaymentApplication> findAll();

    /**
     * Get the "id" paymentApplication.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PaymentApplication> findOne(Integer id);

    /**
     * Delete the "id" paymentApplication.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
