package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.TechnicalCondition;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.TechnicalCondition}.
 */
public interface TechnicalConditionService {
    /**
     * Save a technicalCondition.
     *
     * @param technicalCondition the entity to save.
     * @return the persisted entity.
     */
    TechnicalCondition save(TechnicalCondition technicalCondition);

    /**
     * Updates a technicalCondition.
     *
     * @param technicalCondition the entity to update.
     * @return the persisted entity.
     */
    TechnicalCondition update(TechnicalCondition technicalCondition);

    /**
     * Partially updates a technicalCondition.
     *
     * @param technicalCondition the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TechnicalCondition> partialUpdate(TechnicalCondition technicalCondition);

    /**
     * Get all the technicalConditions.
     *
     * @return the list of entities.
     */
    List<TechnicalCondition> findAll();

    /**
     * Get all the technicalConditions with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TechnicalCondition> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" technicalCondition.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TechnicalCondition> findOne(String id);

    /**
     * Delete the "id" technicalCondition.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
