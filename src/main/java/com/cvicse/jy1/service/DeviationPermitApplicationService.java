package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.DeviationPermitApplication;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.DeviationPermitApplication}.
 */
public interface DeviationPermitApplicationService {
    /**
     * Save a deviationPermitApplication.
     *
     * @param deviationPermitApplication the entity to save.
     * @return the persisted entity.
     */
    DeviationPermitApplication save(DeviationPermitApplication deviationPermitApplication);

    /**
     * Updates a deviationPermitApplication.
     *
     * @param deviationPermitApplication the entity to update.
     * @return the persisted entity.
     */
    DeviationPermitApplication update(DeviationPermitApplication deviationPermitApplication);

    /**
     * Partially updates a deviationPermitApplication.
     *
     * @param deviationPermitApplication the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DeviationPermitApplication> partialUpdate(DeviationPermitApplication deviationPermitApplication);

    /**
     * Get all the deviationPermitApplications.
     *
     * @return the list of entities.
     */
    List<DeviationPermitApplication> findAll();

    /**
     * Get the "id" deviationPermitApplication.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DeviationPermitApplication> findOne(Integer id);

    /**
     * Delete the "id" deviationPermitApplication.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
