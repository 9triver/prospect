package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.Workbag;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.Workbag}.
 */
public interface WorkbagService {
    /**
     * Save a workbag.
     *
     * @param workbag the entity to save.
     * @return the persisted entity.
     */
    Workbag save(Workbag workbag);

    /**
     * Updates a workbag.
     *
     * @param workbag the entity to update.
     * @return the persisted entity.
     */
    Workbag update(Workbag workbag);

    /**
     * Partially updates a workbag.
     *
     * @param workbag the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Workbag> partialUpdate(Workbag workbag);

    /**
     * Get all the workbags.
     *
     * @return the list of entities.
     */
    List<Workbag> findAll();

    /**
     * Get all the Workbag where OutsourcingContract is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Workbag> findAllWhereOutsourcingContractIsNull();

    /**
     * Get all the workbags with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Workbag> findAllWithEagerRelationships(Pageable pageable);

    List<Workbag> findAllWithEagerRelationships();

    /**
     * Get the "id" workbag.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Workbag> findOne(String id);

    /**
     * Delete the "id" workbag.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
