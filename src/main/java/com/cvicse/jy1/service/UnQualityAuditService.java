package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.UnQualityAudit;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.UnQualityAudit}.
 */
public interface UnQualityAuditService {
    /**
     * Save a unQualityAudit.
     *
     * @param unQualityAudit the entity to save.
     * @return the persisted entity.
     */
    UnQualityAudit save(UnQualityAudit unQualityAudit);

    /**
     * Updates a unQualityAudit.
     *
     * @param unQualityAudit the entity to update.
     * @return the persisted entity.
     */
    UnQualityAudit update(UnQualityAudit unQualityAudit);

    /**
     * Partially updates a unQualityAudit.
     *
     * @param unQualityAudit the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UnQualityAudit> partialUpdate(UnQualityAudit unQualityAudit);

    /**
     * Get all the unQualityAudits.
     *
     * @return the list of entities.
     */
    List<UnQualityAudit> findAll();

    /**
     * Get the "id" unQualityAudit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UnQualityAudit> findOne(Integer id);

    /**
     * Delete the "id" unQualityAudit.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
