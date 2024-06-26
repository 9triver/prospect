package com.cvicse.service;

import com.cvicse.domain.ApprovalAgent;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.ApprovalAgent}.
 */
public interface ApprovalAgentService {
    /**
     * Save a approvalAgent.
     *
     * @param approvalAgent the entity to save.
     * @return the persisted entity.
     */
    ApprovalAgent save(ApprovalAgent approvalAgent);

    /**
     * Updates a approvalAgent.
     *
     * @param approvalAgent the entity to update.
     * @return the persisted entity.
     */
    ApprovalAgent update(ApprovalAgent approvalAgent);

    /**
     * Partially updates a approvalAgent.
     *
     * @param approvalAgent the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ApprovalAgent> partialUpdate(ApprovalAgent approvalAgent);

    /**
     * Get all the approvalAgents.
     *
     * @return the list of entities.
     */
    List<ApprovalAgent> findAll();

    /**
     * Get the "id" approvalAgent.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApprovalAgent> findOne(String id);

    /**
     * Delete the "id" approvalAgent.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
