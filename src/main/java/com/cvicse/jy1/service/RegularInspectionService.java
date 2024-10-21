package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.RegularInspection;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.RegularInspection}.
 */
public interface RegularInspectionService {
    /**
     * Save a regularInspection.
     *
     * @param regularInspection the entity to save.
     * @return the persisted entity.
     */
    RegularInspection save(RegularInspection regularInspection);

    /**
     * Updates a regularInspection.
     *
     * @param regularInspection the entity to update.
     * @return the persisted entity.
     */
    RegularInspection update(RegularInspection regularInspection);

    /**
     * Partially updates a regularInspection.
     *
     * @param regularInspection the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RegularInspection> partialUpdate(RegularInspection regularInspection);

    /**
     * Get all the regularInspections.
     *
     * @return the list of entities.
     */
    List<RegularInspection> findAll();

    /**
     * Get the "id" regularInspection.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RegularInspection> findOne(Integer id);

    /**
     * Delete the "id" regularInspection.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
