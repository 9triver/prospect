package com.cvicse.service;

import com.cvicse.domain.Contractualfunds;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Contractualfunds}.
 */
public interface ContractualfundsService {
    /**
     * Save a contractualfunds.
     *
     * @param contractualfunds the entity to save.
     * @return the persisted entity.
     */
    Contractualfunds save(Contractualfunds contractualfunds);

    /**
     * Updates a contractualfunds.
     *
     * @param contractualfunds the entity to update.
     * @return the persisted entity.
     */
    Contractualfunds update(Contractualfunds contractualfunds);

    /**
     * Partially updates a contractualfunds.
     *
     * @param contractualfunds the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Contractualfunds> partialUpdate(Contractualfunds contractualfunds);

    /**
     * Get all the contractualfunds.
     *
     * @return the list of entities.
     */
    List<Contractualfunds> findAll();

    /**
     * Get the "id" contractualfunds.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Contractualfunds> findOne(String id);

    /**
     * Delete the "id" contractualfunds.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
