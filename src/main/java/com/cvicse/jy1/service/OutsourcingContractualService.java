package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.OutsourcingContractual;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.OutsourcingContractual}.
 */
public interface OutsourcingContractualService {
    /**
     * Save a outsourcingContractual.
     *
     * @param outsourcingContractual the entity to save.
     * @return the persisted entity.
     */
    OutsourcingContractual save(OutsourcingContractual outsourcingContractual);

    /**
     * Updates a outsourcingContractual.
     *
     * @param outsourcingContractual the entity to update.
     * @return the persisted entity.
     */
    OutsourcingContractual update(OutsourcingContractual outsourcingContractual);

    /**
     * Partially updates a outsourcingContractual.
     *
     * @param outsourcingContractual the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OutsourcingContractual> partialUpdate(OutsourcingContractual outsourcingContractual);

    /**
     * Get all the outsourcingContractuals.
     *
     * @return the list of entities.
     */
    List<OutsourcingContractual> findAll();

    /**
     * Get all the outsourcingContractuals with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OutsourcingContractual> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" outsourcingContractual.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OutsourcingContractual> findOne(String id);

    /**
     * Delete the "id" outsourcingContractual.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
