package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.CommunicationFormDictionary;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.CommunicationFormDictionary}.
 */
public interface CommunicationFormDictionaryService {
    /**
     * Save a communicationFormDictionary.
     *
     * @param communicationFormDictionary the entity to save.
     * @return the persisted entity.
     */
    CommunicationFormDictionary save(CommunicationFormDictionary communicationFormDictionary);

    /**
     * Updates a communicationFormDictionary.
     *
     * @param communicationFormDictionary the entity to update.
     * @return the persisted entity.
     */
    CommunicationFormDictionary update(CommunicationFormDictionary communicationFormDictionary);

    /**
     * Partially updates a communicationFormDictionary.
     *
     * @param communicationFormDictionary the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CommunicationFormDictionary> partialUpdate(CommunicationFormDictionary communicationFormDictionary);

    /**
     * Get all the communicationFormDictionaries.
     *
     * @return the list of entities.
     */
    List<CommunicationFormDictionary> findAll();

    /**
     * Get the "id" communicationFormDictionary.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommunicationFormDictionary> findOne(Integer id);

    /**
     * Delete the "id" communicationFormDictionary.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
