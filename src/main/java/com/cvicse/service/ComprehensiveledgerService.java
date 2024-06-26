package com.cvicse.service;

import com.cvicse.domain.Comprehensiveledger;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Comprehensiveledger}.
 */
public interface ComprehensiveledgerService {
    /**
     * Save a comprehensiveledger.
     *
     * @param comprehensiveledger the entity to save.
     * @return the persisted entity.
     */
    Comprehensiveledger save(Comprehensiveledger comprehensiveledger);

    /**
     * Updates a comprehensiveledger.
     *
     * @param comprehensiveledger the entity to update.
     * @return the persisted entity.
     */
    Comprehensiveledger update(Comprehensiveledger comprehensiveledger);

    /**
     * Partially updates a comprehensiveledger.
     *
     * @param comprehensiveledger the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Comprehensiveledger> partialUpdate(Comprehensiveledger comprehensiveledger);

    /**
     * Get all the comprehensiveledgers.
     *
     * @return the list of entities.
     */
    List<Comprehensiveledger> findAll();

    /**
     * Get the "id" comprehensiveledger.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Comprehensiveledger> findOne(String id);

    /**
     * Delete the "id" comprehensiveledger.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
