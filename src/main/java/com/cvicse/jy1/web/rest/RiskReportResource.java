package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.RiskReport;
import com.cvicse.jy1.repository.RiskReportRepository;
import com.cvicse.jy1.service.RiskReportService;
import com.cvicse.jy1.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.cvicse.jy1.domain.RiskReport}.
 */
@RestController
@RequestMapping("/api/risk-reports")
public class RiskReportResource {

    private static final Logger log = LoggerFactory.getLogger(RiskReportResource.class);

    private static final String ENTITY_NAME = "riskReport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RiskReportService riskReportService;

    private final RiskReportRepository riskReportRepository;

    public RiskReportResource(RiskReportService riskReportService, RiskReportRepository riskReportRepository) {
        this.riskReportService = riskReportService;
        this.riskReportRepository = riskReportRepository;
    }

    /**
     * {@code POST  /risk-reports} : Create a new riskReport.
     *
     * @param riskReport the riskReport to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new riskReport, or with status {@code 400 (Bad Request)} if the riskReport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<RiskReport> createRiskReport(@RequestBody RiskReport riskReport) throws URISyntaxException {
        log.debug("REST request to save RiskReport : {}", riskReport);
        if (riskReport.getId() != null) {
            throw new BadRequestAlertException("A new riskReport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        riskReport = riskReportService.save(riskReport);
        return ResponseEntity.created(new URI("/api/risk-reports/" + riskReport.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, riskReport.getId().toString()))
            .body(riskReport);
    }

    /**
     * {@code PUT  /risk-reports/:id} : Updates an existing riskReport.
     *
     * @param id the id of the riskReport to save.
     * @param riskReport the riskReport to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskReport,
     * or with status {@code 400 (Bad Request)} if the riskReport is not valid,
     * or with status {@code 500 (Internal Server Error)} if the riskReport couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RiskReport> updateRiskReport(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody RiskReport riskReport
    ) throws URISyntaxException {
        log.debug("REST request to update RiskReport : {}, {}", id, riskReport);
        if (riskReport.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, riskReport.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!riskReportRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        riskReport = riskReportService.update(riskReport);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskReport.getId().toString()))
            .body(riskReport);
    }

    /**
     * {@code PATCH  /risk-reports/:id} : Partial updates given fields of an existing riskReport, field will ignore if it is null
     *
     * @param id the id of the riskReport to save.
     * @param riskReport the riskReport to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskReport,
     * or with status {@code 400 (Bad Request)} if the riskReport is not valid,
     * or with status {@code 404 (Not Found)} if the riskReport is not found,
     * or with status {@code 500 (Internal Server Error)} if the riskReport couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RiskReport> partialUpdateRiskReport(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody RiskReport riskReport
    ) throws URISyntaxException {
        log.debug("REST request to partial update RiskReport partially : {}, {}", id, riskReport);
        if (riskReport.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, riskReport.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!riskReportRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RiskReport> result = riskReportService.partialUpdate(riskReport);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskReport.getId().toString())
        );
    }

    /**
     * {@code GET  /risk-reports} : get all the riskReports.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of riskReports in body.
     */
    @GetMapping("")
    public List<RiskReport> getAllRiskReports() {
        log.debug("REST request to get all RiskReports");
        return riskReportService.findAll();
    }

    /**
     * {@code GET  /risk-reports/:id} : get the "id" riskReport.
     *
     * @param id the id of the riskReport to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the riskReport, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RiskReport> getRiskReport(@PathVariable("id") Integer id) {
        log.debug("REST request to get RiskReport : {}", id);
        Optional<RiskReport> riskReport = riskReportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(riskReport);
    }

    /**
     * {@code DELETE  /risk-reports/:id} : delete the "id" riskReport.
     *
     * @param id the id of the riskReport to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRiskReport(@PathVariable("id") Integer id) {
        log.debug("REST request to delete RiskReport : {}", id);
        riskReportService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
