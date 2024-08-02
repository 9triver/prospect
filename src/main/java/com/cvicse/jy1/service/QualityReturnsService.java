package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.QualityReturns;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.QualityReturns}.
 */
public interface QualityReturnsService {
    /**
     * Save a qualityReturns.
     *
     * @param qualityReturns the entity to save.
     * @return the persisted entity.
     */
    QualityReturns save(QualityReturns qualityReturns);

    /**
     * Updates a qualityReturns.
     *
     * @param qualityReturns the entity to update.
     * @return the persisted entity.
     */
    QualityReturns update(QualityReturns qualityReturns);

    /**
     * Partially updates a qualityReturns.
     *
     * @param qualityReturns the entity to update partially.
     * @return the persisted entity.
     */
    Optional<QualityReturns> partialUpdate(QualityReturns qualityReturns);

    /**
     * Get all the qualityReturns.
     *
     * @return the list of entities.
     */
    List<QualityReturns> findAll();

    /**
     * Get all the qualityReturns with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QualityReturns> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" qualityReturns.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QualityReturns> findOne(String id);

    /**
     * Delete the "id" qualityReturns.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
