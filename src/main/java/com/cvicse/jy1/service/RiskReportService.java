package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.RiskReport;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.RiskReport}.
 */
public interface RiskReportService {
    /**
     * Save a riskReport.
     *
     * @param riskReport the entity to save.
     * @return the persisted entity.
     */
    RiskReport save(RiskReport riskReport);

    /**
     * Updates a riskReport.
     *
     * @param riskReport the entity to update.
     * @return the persisted entity.
     */
    RiskReport update(RiskReport riskReport);

    /**
     * Partially updates a riskReport.
     *
     * @param riskReport the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RiskReport> partialUpdate(RiskReport riskReport);

    /**
     * Get all the riskReports.
     *
     * @return the list of entities.
     */
    List<RiskReport> findAll();

    /**
     * Get the "id" riskReport.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RiskReport> findOne(Integer id);

    /**
     * Delete the "id" riskReport.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
