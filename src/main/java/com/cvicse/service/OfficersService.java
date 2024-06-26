package com.cvicse.service;

import com.cvicse.domain.Officers;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Officers}.
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
     * Get all the Officers where Document is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Officers> findAllWhereDocumentIsNull();
    /**
     * Get all the Officers where Planexecute is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Officers> findAllWherePlanexecuteIsNull();
    /**
     * Get all the Officers where Projectcharge is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Officers> findAllWhereProjectchargeIsNull();
    /**
     * Get all the Officers where ApprovalAgent is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Officers> findAllWhereApprovalAgentIsNull();

    /**
     * Get the "id" officers.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Officers> findOne(String id);

    /**
     * Delete the "id" officers.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
