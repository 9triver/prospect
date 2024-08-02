package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.Officers;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.Officers}.
 */
public interface OfficersService {
    /**
     * Save a officers.
     *
     * @param officers the entity to save.
     * @return the persisted entity.
     */
    Officers save(Officers officers);

    /**
     * Updates a officers.
     *
     * @param officers the entity to update.
     * @return the persisted entity.
     */
    Officers update(Officers officers);

    /**
     * Partially updates a officers.
     *
     * @param officers the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Officers> partialUpdate(Officers officers);

    /**
     * Get all the officers.
     *
     * @return the list of entities.
     */
    List<Officers> findAll();

    /**
     * Get all the officers with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Officers> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" officers.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Officers> findOne(Long id);

    /**
     * Delete the "id" officers.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
