package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.RiskReturn;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.RiskReturn}.
 */
public interface RiskReturnService {
    /**
     * Save a riskReturn.
     *
     * @param riskReturn the entity to save.
     * @return the persisted entity.
     */
    RiskReturn save(RiskReturn riskReturn);

    /**
     * Updates a riskReturn.
     *
     * @param riskReturn the entity to update.
     * @return the persisted entity.
     */
    RiskReturn update(RiskReturn riskReturn);

    /**
     * Partially updates a riskReturn.
     *
     * @param riskReturn the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RiskReturn> partialUpdate(RiskReturn riskReturn);

    /**
     * Get all the riskReturns.
     *
     * @return the list of entities.
     */
    List<RiskReturn> findAll();

    /**
     * Get the "id" riskReturn.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RiskReturn> findOne(Integer id);

    /**
     * Delete the "id" riskReturn.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
