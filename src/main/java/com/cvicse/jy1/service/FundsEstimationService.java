package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.FundsEstimation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.FundsEstimation}.
 */
public interface FundsEstimationService {
    /**
     * Save a fundsEstimation.
     *
     * @param fundsEstimation the entity to save.
     * @return the persisted entity.
     */
    FundsEstimation save(FundsEstimation fundsEstimation);

    /**
     * Updates a fundsEstimation.
     *
     * @param fundsEstimation the entity to update.
     * @return the persisted entity.
     */
    FundsEstimation update(FundsEstimation fundsEstimation);

    /**
     * Partially updates a fundsEstimation.
     *
     * @param fundsEstimation the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FundsEstimation> partialUpdate(FundsEstimation fundsEstimation);

    /**
     * Get all the fundsEstimations.
     *
     * @return the list of entities.
     */
    List<FundsEstimation> findAll();

    /**
     * Get all the fundsEstimations with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FundsEstimation> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" fundsEstimation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FundsEstimation> findOne(String id);

    /**
     * Delete the "id" fundsEstimation.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
