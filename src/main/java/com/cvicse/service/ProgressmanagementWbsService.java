package com.cvicse.service;

import com.cvicse.domain.ProgressmanagementWbs;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.ProgressmanagementWbs}.
 */
public interface ProgressmanagementWbsService {
    /**
     * Save a progressmanagementWbs.
     *
     * @param progressmanagementWbs the entity to save.
     * @return the persisted entity.
     */
    ProgressmanagementWbs save(ProgressmanagementWbs progressmanagementWbs);

    /**
     * Updates a progressmanagementWbs.
     *
     * @param progressmanagementWbs the entity to update.
     * @return the persisted entity.
     */
    ProgressmanagementWbs update(ProgressmanagementWbs progressmanagementWbs);

    /**
     * Partially updates a progressmanagementWbs.
     *
     * @param progressmanagementWbs the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProgressmanagementWbs> partialUpdate(ProgressmanagementWbs progressmanagementWbs);

    /**
     * Get all the progressmanagementWbs.
     *
     * @return the list of entities.
     */
    List<ProgressmanagementWbs> findAll();

    /**
     * Get all the ProgressmanagementWbs where Progressmanagement is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<ProgressmanagementWbs> findAllWhereProgressmanagementIsNull();

    /**
     * Get the "id" progressmanagementWbs.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProgressmanagementWbs> findOne(String id);

    /**
     * Delete the "id" progressmanagementWbs.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
