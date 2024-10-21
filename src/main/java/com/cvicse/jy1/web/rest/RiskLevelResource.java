package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.RiskLevel;
import com.cvicse.jy1.repository.RiskLevelRepository;
import com.cvicse.jy1.service.RiskLevelService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.RiskLevel}.
 */
@RestController
@RequestMapping("/api/risk-levels")
public class RiskLevelResource {

    private static final Logger log = LoggerFactory.getLogger(RiskLevelResource.class);

    private static final String ENTITY_NAME = "riskLevel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RiskLevelService riskLevelService;

    private final RiskLevelRepository riskLevelRepository;

    public RiskLevelResource(RiskLevelService riskLevelService, RiskLevelRepository riskLevelRepository) {
        this.riskLevelService = riskLevelService;
        this.riskLevelRepository = riskLevelRepository;
    }

    /**
     * {@code POST  /risk-levels} : Create a new riskLevel.
     *
     * @param riskLevel the riskLevel to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new riskLevel, or with status {@code 400 (Bad Request)} if the riskLevel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<RiskLevel> createRiskLevel(@RequestBody RiskLevel riskLevel) throws URISyntaxException {
        log.debug("REST request to save RiskLevel : {}", riskLevel);
        if (riskLevel.getId() != null) {
            throw new BadRequestAlertException("A new riskLevel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        riskLevel = riskLevelService.save(riskLevel);
        return ResponseEntity.created(new URI("/api/risk-levels/" + riskLevel.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, riskLevel.getId().toString()))
            .body(riskLevel);
    }

    /**
     * {@code PUT  /risk-levels/:id} : Updates an existing riskLevel.
     *
     * @param id the id of the riskLevel to save.
     * @param riskLevel the riskLevel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskLevel,
     * or with status {@code 400 (Bad Request)} if the riskLevel is not valid,
     * or with status {@code 500 (Internal Server Error)} if the riskLevel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RiskLevel> updateRiskLevel(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody RiskLevel riskLevel
    ) throws URISyntaxException {
        log.debug("REST request to update RiskLevel : {}, {}", id, riskLevel);
        if (riskLevel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, riskLevel.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!riskLevelRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        riskLevel = riskLevelService.update(riskLevel);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskLevel.getId().toString()))
            .body(riskLevel);
    }

    /**
     * {@code PATCH  /risk-levels/:id} : Partial updates given fields of an existing riskLevel, field will ignore if it is null
     *
     * @param id the id of the riskLevel to save.
     * @param riskLevel the riskLevel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskLevel,
     * or with status {@code 400 (Bad Request)} if the riskLevel is not valid,
     * or with status {@code 404 (Not Found)} if the riskLevel is not found,
     * or with status {@code 500 (Internal Server Error)} if the riskLevel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RiskLevel> partialUpdateRiskLevel(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody RiskLevel riskLevel
    ) throws URISyntaxException {
        log.debug("REST request to partial update RiskLevel partially : {}, {}", id, riskLevel);
        if (riskLevel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, riskLevel.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!riskLevelRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RiskLevel> result = riskLevelService.partialUpdate(riskLevel);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskLevel.getId().toString())
        );
    }

    /**
     * {@code GET  /risk-levels} : get all the riskLevels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of riskLevels in body.
     */
    @GetMapping("")
    public List<RiskLevel> getAllRiskLevels() {
        log.debug("REST request to get all RiskLevels");
        return riskLevelService.findAll();
    }

    /**
     * {@code GET  /risk-levels/:id} : get the "id" riskLevel.
     *
     * @param id the id of the riskLevel to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the riskLevel, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RiskLevel> getRiskLevel(@PathVariable("id") Integer id) {
        log.debug("REST request to get RiskLevel : {}", id);
        Optional<RiskLevel> riskLevel = riskLevelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(riskLevel);
    }

    /**
     * {@code DELETE  /risk-levels/:id} : delete the "id" riskLevel.
     *
     * @param id the id of the riskLevel to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRiskLevel(@PathVariable("id") Integer id) {
        log.debug("REST request to delete RiskLevel : {}", id);
        riskLevelService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
