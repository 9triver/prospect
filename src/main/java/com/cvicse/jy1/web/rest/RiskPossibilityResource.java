package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.RiskPossibility;
import com.cvicse.jy1.repository.RiskPossibilityRepository;
import com.cvicse.jy1.service.RiskPossibilityService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.RiskPossibility}.
 */
@RestController
@RequestMapping("/api/risk-possibilities")
public class RiskPossibilityResource {

    private static final Logger log = LoggerFactory.getLogger(RiskPossibilityResource.class);

    private static final String ENTITY_NAME = "riskPossibility";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RiskPossibilityService riskPossibilityService;

    private final RiskPossibilityRepository riskPossibilityRepository;

    public RiskPossibilityResource(RiskPossibilityService riskPossibilityService, RiskPossibilityRepository riskPossibilityRepository) {
        this.riskPossibilityService = riskPossibilityService;
        this.riskPossibilityRepository = riskPossibilityRepository;
    }

    /**
     * {@code POST  /risk-possibilities} : Create a new riskPossibility.
     *
     * @param riskPossibility the riskPossibility to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new riskPossibility, or with status {@code 400 (Bad Request)} if the riskPossibility has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<RiskPossibility> createRiskPossibility(@RequestBody RiskPossibility riskPossibility) throws URISyntaxException {
        log.debug("REST request to save RiskPossibility : {}", riskPossibility);
        if (riskPossibility.getId() != null) {
            throw new BadRequestAlertException("A new riskPossibility cannot already have an ID", ENTITY_NAME, "idexists");
        }
        riskPossibility = riskPossibilityService.save(riskPossibility);
        return ResponseEntity.created(new URI("/api/risk-possibilities/" + riskPossibility.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, riskPossibility.getId().toString()))
            .body(riskPossibility);
    }

    /**
     * {@code PUT  /risk-possibilities/:id} : Updates an existing riskPossibility.
     *
     * @param id the id of the riskPossibility to save.
     * @param riskPossibility the riskPossibility to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskPossibility,
     * or with status {@code 400 (Bad Request)} if the riskPossibility is not valid,
     * or with status {@code 500 (Internal Server Error)} if the riskPossibility couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RiskPossibility> updateRiskPossibility(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody RiskPossibility riskPossibility
    ) throws URISyntaxException {
        log.debug("REST request to update RiskPossibility : {}, {}", id, riskPossibility);
        if (riskPossibility.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, riskPossibility.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!riskPossibilityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        riskPossibility = riskPossibilityService.update(riskPossibility);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskPossibility.getId().toString()))
            .body(riskPossibility);
    }

    /**
     * {@code PATCH  /risk-possibilities/:id} : Partial updates given fields of an existing riskPossibility, field will ignore if it is null
     *
     * @param id the id of the riskPossibility to save.
     * @param riskPossibility the riskPossibility to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskPossibility,
     * or with status {@code 400 (Bad Request)} if the riskPossibility is not valid,
     * or with status {@code 404 (Not Found)} if the riskPossibility is not found,
     * or with status {@code 500 (Internal Server Error)} if the riskPossibility couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RiskPossibility> partialUpdateRiskPossibility(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody RiskPossibility riskPossibility
    ) throws URISyntaxException {
        log.debug("REST request to partial update RiskPossibility partially : {}, {}", id, riskPossibility);
        if (riskPossibility.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, riskPossibility.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!riskPossibilityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RiskPossibility> result = riskPossibilityService.partialUpdate(riskPossibility);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskPossibility.getId().toString())
        );
    }

    /**
     * {@code GET  /risk-possibilities} : get all the riskPossibilities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of riskPossibilities in body.
     */
    @GetMapping("")
    public List<RiskPossibility> getAllRiskPossibilities() {
        log.debug("REST request to get all RiskPossibilities");
        return riskPossibilityService.findAll();
    }

    /**
     * {@code GET  /risk-possibilities/:id} : get the "id" riskPossibility.
     *
     * @param id the id of the riskPossibility to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the riskPossibility, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RiskPossibility> getRiskPossibility(@PathVariable("id") Integer id) {
        log.debug("REST request to get RiskPossibility : {}", id);
        Optional<RiskPossibility> riskPossibility = riskPossibilityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(riskPossibility);
    }

    /**
     * {@code DELETE  /risk-possibilities/:id} : delete the "id" riskPossibility.
     *
     * @param id the id of the riskPossibility to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRiskPossibility(@PathVariable("id") Integer id) {
        log.debug("REST request to delete RiskPossibility : {}", id);
        riskPossibilityService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
