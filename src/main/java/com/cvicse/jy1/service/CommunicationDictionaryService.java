package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.CommunicationDictionary;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.CommunicationDictionary}.
 */
public interface CommunicationDictionaryService {
    /**
     * Save a communicationDictionary.
     *
     * @param communicationDictionary the entity to save.
     * @return the persisted entity.
     */
    CommunicationDictionary save(CommunicationDictionary communicationDictionary);

    /**
     * Updates a communicationDictionary.
     *
     * @param communicationDictionary the entity to update.
     * @return the persisted entity.
     */
    CommunicationDictionary update(CommunicationDictionary communicationDictionary);

    /**
     * Partially updates a communicationDictionary.
     *
     * @param communicationDictionary the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CommunicationDictionary> partialUpdate(CommunicationDictionary communicationDictionary);

    /**
     * Get all the communicationDictionaries.
     *
     * @return the list of entities.
     */
    List<CommunicationDictionary> findAll();

    /**
     * Get the "id" communicationDictionary.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommunicationDictionary> findOne(Integer id);

    /**
     * Delete the "id" communicationDictionary.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
