package com.cvicse.service;

import com.cvicse.domain.Document;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Document}.
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
     * Get all the Document where Cycleplan is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Document> findAllWhereCycleplanIsNull();
    /**
     * Get all the Document where Annualplan is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Document> findAllWhereAnnualplanIsNull();
    /**
     * Get all the Document where Monthplan is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Document> findAllWhereMonthplanIsNull();
    /**
     * Get all the Document where Progressplanreturns is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Document> findAllWhereProgressplanreturnsIsNull();
    /**
     * Get all the Document where Auditedbudget is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Document> findAllWhereAuditedbudgetIsNull();

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
