package com.cvicse.service;

import com.cvicse.domain.Projectcharge;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Projectcharge}.
 */
public interface ProjectchargeService {
    /**
     * Save a projectcharge.
     *
     * @param projectcharge the entity to save.
     * @return the persisted entity.
     */
    Projectcharge save(Projectcharge projectcharge);

    /**
     * Updates a projectcharge.
     *
     * @param projectcharge the entity to update.
     * @return the persisted entity.
     */
    Projectcharge update(Projectcharge projectcharge);

    /**
     * Partially updates a projectcharge.
     *
     * @param projectcharge the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Projectcharge> partialUpdate(Projectcharge projectcharge);

    /**
     * Get all the projectcharges.
     *
     * @return the list of entities.
     */
    List<Projectcharge> findAll();

    /**
     * Get all the Projectcharge where Cycleplan is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Projectcharge> findAllWhereCycleplanIsNull();
    /**
     * Get all the Projectcharge where Annualplan is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Projectcharge> findAllWhereAnnualplanIsNull();
    /**
     * Get all the Projectcharge where Monthplan is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Projectcharge> findAllWhereMonthplanIsNull();
    /**
     * Get all the Projectcharge where Pbsbaseline is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Projectcharge> findAllWherePbsbaselineIsNull();
    /**
     * Get all the Projectcharge where Wbsbaseline is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Projectcharge> findAllWhereWbsbaselineIsNull();

    /**
     * Get the "id" projectcharge.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Projectcharge> findOne(Long id);

    /**
     * Delete the "id" projectcharge.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
