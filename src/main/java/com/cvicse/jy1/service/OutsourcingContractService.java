package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.OutsourcingContract;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.OutsourcingContract}.
 */
public interface OutsourcingContractService {
    /**
     * Save a outsourcingContract.
     *
     * @param outsourcingContract the entity to save.
     * @return the persisted entity.
     */
    OutsourcingContract save(OutsourcingContract outsourcingContract);

    /**
     * Updates a outsourcingContract.
     *
     * @param outsourcingContract the entity to update.
     * @return the persisted entity.
     */
    OutsourcingContract update(OutsourcingContract outsourcingContract);

    /**
     * Partially updates a outsourcingContract.
     *
     * @param outsourcingContract the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OutsourcingContract> partialUpdate(OutsourcingContract outsourcingContract);

    /**
     * Get all the outsourcingContracts.
     *
     * @return the list of entities.
     */
    List<OutsourcingContract> findAll();

    /**
     * Get the "id" outsourcingContract.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OutsourcingContract> findOne(Integer id);

    /**
     * Delete the "id" outsourcingContract.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
