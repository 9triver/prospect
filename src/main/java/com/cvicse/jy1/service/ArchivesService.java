package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.Archives;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.Archives}.
 */
public interface ArchivesService {
    /**
     * Save a archives.
     *
     * @param archives the entity to save.
     * @return the persisted entity.
     */
    Archives save(Archives archives);

    /**
     * Updates a archives.
     *
     * @param archives the entity to update.
     * @return the persisted entity.
     */
    Archives update(Archives archives);

    /**
     * Partially updates a archives.
     *
     * @param archives the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Archives> partialUpdate(Archives archives);

    /**
     * Get all the archives.
     *
     * @return the list of entities.
     */
    List<Archives> findAll();

    /**
     * Get the "id" archives.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Archives> findOne(String id);

    /**
     * Delete the "id" archives.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
