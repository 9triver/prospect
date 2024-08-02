package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.Projectpbs;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.Projectpbs}.
 */
public interface ProjectpbsService {
    /**
     * Save a projectpbs.
     *
     * @param projectpbs the entity to save.
     * @return the persisted entity.
     */
    Projectpbs save(Projectpbs projectpbs);

    /**
     * Updates a projectpbs.
     *
     * @param projectpbs the entity to update.
     * @return the persisted entity.
     */
    Projectpbs update(Projectpbs projectpbs);

    /**
     * Partially updates a projectpbs.
     *
     * @param projectpbs the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Projectpbs> partialUpdate(Projectpbs projectpbs);

    /**
     * Get all the projectpbs.
     *
     * @return the list of entities.
     */
    List<Projectpbs> findAll();

    /**
     * Get all the projectpbs with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Projectpbs> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" projectpbs.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Projectpbs> findOne(String id);

    /**
     * Delete the "id" projectpbs.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
