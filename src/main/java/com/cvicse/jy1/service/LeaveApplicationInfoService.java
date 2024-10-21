package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.LeaveApplicationInfo;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.LeaveApplicationInfo}.
 */
public interface LeaveApplicationInfoService {
    /**
     * Save a leaveApplicationInfo.
     *
     * @param leaveApplicationInfo the entity to save.
     * @return the persisted entity.
     */
    LeaveApplicationInfo save(LeaveApplicationInfo leaveApplicationInfo);

    /**
     * Updates a leaveApplicationInfo.
     *
     * @param leaveApplicationInfo the entity to update.
     * @return the persisted entity.
     */
    LeaveApplicationInfo update(LeaveApplicationInfo leaveApplicationInfo);

    /**
     * Partially updates a leaveApplicationInfo.
     *
     * @param leaveApplicationInfo the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LeaveApplicationInfo> partialUpdate(LeaveApplicationInfo leaveApplicationInfo);

    /**
     * Get all the leaveApplicationInfos.
     *
     * @return the list of entities.
     */
    List<LeaveApplicationInfo> findAll();

    /**
     * Get the "id" leaveApplicationInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LeaveApplicationInfo> findOne(Integer id);

    /**
     * Delete the "id" leaveApplicationInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
