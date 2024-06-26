package com.cvicse.service;

import com.cvicse.domain.Pbsmanage;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Pbsmanage}.
 */
public interface PbsmanageService {
    /**
     * Save a pbsmanage.
     *
     * @param pbsmanage the entity to save.
     * @return the persisted entity.
     */
    Pbsmanage save(Pbsmanage pbsmanage);

    /**
     * Updates a pbsmanage.
     *
     * @param pbsmanage the entity to update.
     * @return the persisted entity.
     */
    Pbsmanage update(Pbsmanage pbsmanage);

    /**
     * Partially updates a pbsmanage.
     *
     * @param pbsmanage the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Pbsmanage> partialUpdate(Pbsmanage pbsmanage);

    /**
     * Get all the pbsmanages.
     *
     * @return the list of entities.
     */
    List<Pbsmanage> findAll();

    /**
     * Get the "id" pbsmanage.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Pbsmanage> findOne(String id);

    /**
     * Delete the "id" pbsmanage.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
