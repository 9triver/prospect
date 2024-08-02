package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.Technical;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.Technical}.
 */
public interface TechnicalService {
    /**
     * Save a technical.
     *
     * @param technical the entity to save.
     * @return the persisted entity.
     */
    Technical save(Technical technical);

    /**
     * Updates a technical.
     *
     * @param technical the entity to update.
     * @return the persisted entity.
     */
    Technical update(Technical technical);

    /**
     * Partially updates a technical.
     *
     * @param technical the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Technical> partialUpdate(Technical technical);

    /**
     * Get all the technicals.
     *
     * @return the list of entities.
     */
    List<Technical> findAll();

    /**
     * Get all the technicals with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Technical> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" technical.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Technical> findOne(String id);

    /**
     * Delete the "id" technical.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
