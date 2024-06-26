package com.cvicse.service;

import com.cvicse.domain.Planexecute;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.domain.Planexecute}.
 */
public interface PlanexecuteService {
    /**
     * Save a planexecute.
     *
     * @param planexecute the entity to save.
     * @return the persisted entity.
     */
    Planexecute save(Planexecute planexecute);

    /**
     * Updates a planexecute.
     *
     * @param planexecute the entity to update.
     * @return the persisted entity.
     */
    Planexecute update(Planexecute planexecute);

    /**
     * Partially updates a planexecute.
     *
     * @param planexecute the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Planexecute> partialUpdate(Planexecute planexecute);

    /**
     * Get all the planexecutes.
     *
     * @return the list of entities.
     */
    List<Planexecute> findAll();

    /**
     * Get all the Planexecute where Monthplan is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Planexecute> findAllWhereMonthplanIsNull();

    /**
     * Get the "id" planexecute.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Planexecute> findOne(String id);

    /**
     * Delete the "id" planexecute.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
