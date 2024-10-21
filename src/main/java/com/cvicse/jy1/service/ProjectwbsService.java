package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.Projectpbs;
import com.cvicse.jy1.domain.Projectwbs;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.Projectwbs}.
 */
public interface ProjectwbsService {
    /**
     * Save a projectwbs.
     *
     * @param projectwbs the entity to save.
     * @return the persisted entity.
     */
    Projectwbs save(Projectwbs projectwbs);

    /**
     * Updates a projectwbs.
     *
     * @param projectwbs the entity to update.
     * @return the persisted entity.
     */
    Projectwbs update(Projectwbs projectwbs);

    /**
     * Partially updates a projectwbs.
     *
     * @param projectwbs the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Projectwbs> partialUpdate(Projectwbs projectwbs);

    /**
     * Get all the projectwbs.
     *
     * @return the list of entities.
     */
    List<Projectwbs> findAll();

    /**
     * Get all the projectwbs with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Projectwbs> findAllWithEagerRelationships(Pageable pageable);

    List<Projectwbs> findAllWithEagerRelationships();

    /**
     * Get the "id" projectwbs.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Projectwbs> findOne(String id);

    /**
     * Delete the "id" projectwbs.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
