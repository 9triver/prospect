package com.cvicse.service;

import com.cvicse.domain.Pbssubmanage;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Pbssubmanage}.
 */
public interface PbssubmanageService {
    /**
     * Save a pbssubmanage.
     *
     * @param pbssubmanage the entity to save.
     * @return the persisted entity.
     */
    Pbssubmanage save(Pbssubmanage pbssubmanage);

    /**
     * Updates a pbssubmanage.
     *
     * @param pbssubmanage the entity to update.
     * @return the persisted entity.
     */
    Pbssubmanage update(Pbssubmanage pbssubmanage);

    /**
     * Partially updates a pbssubmanage.
     *
     * @param pbssubmanage the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Pbssubmanage> partialUpdate(Pbssubmanage pbssubmanage);

    /**
     * Get all the pbssubmanages.
     *
     * @return the list of entities.
     */
    List<Pbssubmanage> findAll();

    /**
     * Get all the Pbssubmanage where Pbsmanage is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Pbssubmanage> findAllWherePbsmanageIsNull();
    /**
     * Get all the Pbssubmanage where Wbsmanage is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Pbssubmanage> findAllWhereWbsmanageIsNull();

    /**
     * Get the "id" pbssubmanage.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Pbssubmanage> findOne(String id);

    /**
     * Delete the "id" pbssubmanage.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
