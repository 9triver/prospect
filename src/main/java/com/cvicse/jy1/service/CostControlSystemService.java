package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.CostControlSystem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.CostControlSystem}.
 */
public interface CostControlSystemService {
    /**
     * Save a costControlSystem.
     *
     * @param costControlSystem the entity to save.
     * @return the persisted entity.
     */
    CostControlSystem save(CostControlSystem costControlSystem);

    /**
     * Updates a costControlSystem.
     *
     * @param costControlSystem the entity to update.
     * @return the persisted entity.
     */
    CostControlSystem update(CostControlSystem costControlSystem);

    /**
     * Partially updates a costControlSystem.
     *
     * @param costControlSystem the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CostControlSystem> partialUpdate(CostControlSystem costControlSystem);

    /**
     * Get all the costControlSystems.
     *
     * @return the list of entities.
     */
    List<CostControlSystem> findAll();

    /**
     * Get all the costControlSystems with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CostControlSystem> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" costControlSystem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CostControlSystem> findOne(String id);

    /**
     * Delete the "id" costControlSystem.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
