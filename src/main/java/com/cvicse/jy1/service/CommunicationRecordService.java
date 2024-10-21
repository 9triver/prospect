package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.CommunicationRecord;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.CommunicationRecord}.
 */
public interface CommunicationRecordService {
    /**
     * Save a communicationRecord.
     *
     * @param communicationRecord the entity to save.
     * @return the persisted entity.
     */
    CommunicationRecord save(CommunicationRecord communicationRecord);

    /**
     * Updates a communicationRecord.
     *
     * @param communicationRecord the entity to update.
     * @return the persisted entity.
     */
    CommunicationRecord update(CommunicationRecord communicationRecord);

    /**
     * Partially updates a communicationRecord.
     *
     * @param communicationRecord the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CommunicationRecord> partialUpdate(CommunicationRecord communicationRecord);

    /**
     * Get all the communicationRecords.
     *
     * @return the list of entities.
     */
    List<CommunicationRecord> findAll();

    /**
     * Get the "id" communicationRecord.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommunicationRecord> findOne(Integer id);

    /**
     * Delete the "id" communicationRecord.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
