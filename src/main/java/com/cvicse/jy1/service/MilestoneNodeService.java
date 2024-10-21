package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.MilestoneNode;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.MilestoneNode}.
 */
public interface MilestoneNodeService {
    /**
     * Save a milestoneNode.
     *
     * @param milestoneNode the entity to save.
     * @return the persisted entity.
     */
    MilestoneNode save(MilestoneNode milestoneNode);

    /**
     * Updates a milestoneNode.
     *
     * @param milestoneNode the entity to update.
     * @return the persisted entity.
     */
    MilestoneNode update(MilestoneNode milestoneNode);

    /**
     * Partially updates a milestoneNode.
     *
     * @param milestoneNode the entity to update partially.
     * @return the persisted entity.
     */
    Optional<MilestoneNode> partialUpdate(MilestoneNode milestoneNode);

    /**
     * Get all the milestoneNodes.
     *
     * @return the list of entities.
     */
    List<MilestoneNode> findAll();

    /**
     * Get the "id" milestoneNode.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MilestoneNode> findOne(Integer id);

    /**
     * Delete the "id" milestoneNode.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
