package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.Letter;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.Letter}.
 */
public interface LetterService {
    /**
     * Save a letter.
     *
     * @param letter the entity to save.
     * @return the persisted entity.
     */
    Letter save(Letter letter);

    /**
     * Updates a letter.
     *
     * @param letter the entity to update.
     * @return the persisted entity.
     */
    Letter update(Letter letter);

    /**
     * Partially updates a letter.
     *
     * @param letter the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Letter> partialUpdate(Letter letter);

    /**
     * Get all the letters.
     *
     * @return the list of entities.
     */
    List<Letter> findAll();

    /**
     * Get the "id" letter.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Letter> findOne(Integer id);

    /**
     * Delete the "id" letter.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
