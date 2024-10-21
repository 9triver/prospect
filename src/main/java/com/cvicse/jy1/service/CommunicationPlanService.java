package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.CommunicationPlan;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.CommunicationPlan}.
 */
public interface CommunicationPlanService {
    /**
     * Save a communicationPlan.
     *
     * @param communicationPlan the entity to save.
     * @return the persisted entity.
     */
    CommunicationPlan save(CommunicationPlan communicationPlan);

    /**
     * Updates a communicationPlan.
     *
     * @param communicationPlan the entity to update.
     * @return the persisted entity.
     */
    CommunicationPlan update(CommunicationPlan communicationPlan);

    /**
     * Partially updates a communicationPlan.
     *
     * @param communicationPlan the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CommunicationPlan> partialUpdate(CommunicationPlan communicationPlan);

    /**
     * Get all the communicationPlans.
     *
     * @return the list of entities.
     */
    List<CommunicationPlan> findAll();

    /**
     * Get the "id" communicationPlan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommunicationPlan> findOne(Integer id);

    /**
     * Delete the "id" communicationPlan.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
