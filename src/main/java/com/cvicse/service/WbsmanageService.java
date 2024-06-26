package com.cvicse.service;

import com.cvicse.domain.Wbsmanage;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Wbsmanage}.
 */
public interface WbsmanageService {
    /**
     * Save a wbsmanage.
     *
     * @param wbsmanage the entity to save.
     * @return the persisted entity.
     */
    Wbsmanage save(Wbsmanage wbsmanage);

    /**
     * Updates a wbsmanage.
     *
     * @param wbsmanage the entity to update.
     * @return the persisted entity.
     */
    Wbsmanage update(Wbsmanage wbsmanage);

    /**
     * Partially updates a wbsmanage.
     *
     * @param wbsmanage the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Wbsmanage> partialUpdate(Wbsmanage wbsmanage);

    /**
     * Get all the wbsmanages.
     *
     * @return the list of entities.
     */
    List<Wbsmanage> findAll();

    /**
     * Get the "id" wbsmanage.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Wbsmanage> findOne(String id);

    /**
     * Delete the "id" wbsmanage.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
