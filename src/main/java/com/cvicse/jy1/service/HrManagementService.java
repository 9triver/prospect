package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.HrManagement;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.HrManagement}.
 */
public interface HrManagementService {
    /**
     * Save a hrManagement.
     *
     * @param hrManagement the entity to save.
     * @return the persisted entity.
     */
    HrManagement save(HrManagement hrManagement);

    /**
     * Updates a hrManagement.
     *
     * @param hrManagement the entity to update.
     * @return the persisted entity.
     */
    HrManagement update(HrManagement hrManagement);

    /**
     * Partially updates a hrManagement.
     *
     * @param hrManagement the entity to update partially.
     * @return the persisted entity.
     */
    Optional<HrManagement> partialUpdate(HrManagement hrManagement);

    /**
     * Get all the hrManagements.
     *
     * @return the list of entities.
     */
    List<HrManagement> findAll();

    /**
     * Get the "id" hrManagement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HrManagement> findOne(Integer id);

    /**
     * Delete the "id" hrManagement.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
