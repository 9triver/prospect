package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.Document;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.Document}.
 */
public interface DocumentService {
    /**
     * Save a document.
     *
     * @param document the entity to save.
     * @return the persisted entity.
     */
    Document save(Document document);

    /**
     * Updates a document.
     *
     * @param document the entity to update.
     * @return the persisted entity.
     */
    Document update(Document document);

    /**
     * Partially updates a document.
     *
     * @param document the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Document> partialUpdate(Document document);

    /**
     * Get all the documents.
     *
     * @return the list of entities.
     */
    List<Document> findAll();

    /**
     * Get the "id" document.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Document> findOne(String id);

    /**
     * Delete the "id" document.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
