package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.FundSourceList;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.FundSourceList}.
 */
public interface FundSourceListService {
    /**
     * Save a fundSourceList.
     *
     * @param fundSourceList the entity to save.
     * @return the persisted entity.
     */
    FundSourceList save(FundSourceList fundSourceList);

    /**
     * Updates a fundSourceList.
     *
     * @param fundSourceList the entity to update.
     * @return the persisted entity.
     */
    FundSourceList update(FundSourceList fundSourceList);

    /**
     * Partially updates a fundSourceList.
     *
     * @param fundSourceList the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FundSourceList> partialUpdate(FundSourceList fundSourceList);

    /**
     * Get all the fundSourceLists.
     *
     * @return the list of entities.
     */
    List<FundSourceList> findAll();

    /**
     * Get the "id" fundSourceList.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FundSourceList> findOne(Integer id);

    /**
     * Delete the "id" fundSourceList.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
