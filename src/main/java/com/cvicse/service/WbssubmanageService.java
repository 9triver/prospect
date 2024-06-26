package com.cvicse.service;

import com.cvicse.domain.Wbssubmanage;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Wbssubmanage}.
 */
public interface WbssubmanageService {
    /**
     * Save a wbssubmanage.
     *
     * @param wbssubmanage the entity to save.
     * @return the persisted entity.
     */
    Wbssubmanage save(Wbssubmanage wbssubmanage);

    /**
     * Updates a wbssubmanage.
     *
     * @param wbssubmanage the entity to update.
     * @return the persisted entity.
     */
    Wbssubmanage update(Wbssubmanage wbssubmanage);

    /**
     * Partially updates a wbssubmanage.
     *
     * @param wbssubmanage the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Wbssubmanage> partialUpdate(Wbssubmanage wbssubmanage);

    /**
     * Get all the wbssubmanages.
     *
     * @return the list of entities.
     */
    List<Wbssubmanage> findAll();

    /**
     * Get all the Wbssubmanage where Wbsmanage is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Wbssubmanage> findAllWhereWbsmanageIsNull();

    /**
     * Get the "id" wbssubmanage.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Wbssubmanage> findOne(String id);

    /**
     * Delete the "id" wbssubmanage.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
