package com.cvicse.service;

import com.cvicse.domain.FundsmanagementWbs;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.FundsmanagementWbs}.
 */
public interface FundsmanagementWbsService {
    /**
     * Save a fundsmanagementWbs.
     *
     * @param fundsmanagementWbs the entity to save.
     * @return the persisted entity.
     */
    FundsmanagementWbs save(FundsmanagementWbs fundsmanagementWbs);

    /**
     * Updates a fundsmanagementWbs.
     *
     * @param fundsmanagementWbs the entity to update.
     * @return the persisted entity.
     */
    FundsmanagementWbs update(FundsmanagementWbs fundsmanagementWbs);

    /**
     * Partially updates a fundsmanagementWbs.
     *
     * @param fundsmanagementWbs the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FundsmanagementWbs> partialUpdate(FundsmanagementWbs fundsmanagementWbs);

    /**
     * Get all the fundsmanagementWbs.
     *
     * @return the list of entities.
     */
    List<FundsmanagementWbs> findAll();

    /**
     * Get all the FundsmanagementWbs where Fundsmanagement is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<FundsmanagementWbs> findAllWhereFundsmanagementIsNull();

    /**
     * Get the "id" fundsmanagementWbs.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FundsmanagementWbs> findOne(String id);

    /**
     * Delete the "id" fundsmanagementWbs.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
