package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.RiskType;
import com.cvicse.jy1.repository.RiskTypeRepository;
import com.cvicse.jy1.service.RiskTypeService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.RiskType}.
 */
@RestController
@RequestMapping("/api/risk-types")
public class RiskTypeResource {

    private static final Logger log = LoggerFactory.getLogger(RiskTypeResource.class);

    private static final String ENTITY_NAME = "riskType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RiskTypeService riskTypeService;

    private final RiskTypeRepository riskTypeRepository;

    public RiskTypeResource(RiskTypeService riskTypeService, RiskTypeRepository riskTypeRepository) {
        this.riskTypeService = riskTypeService;
        this.riskTypeRepository = riskTypeRepository;
    }

    /**
     * {@code POST  /risk-types} : Create a new riskType.
     *
     * @param riskType the riskType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new riskType, or with status {@code 400 (Bad Request)} if the riskType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<RiskType> createRiskType(@RequestBody RiskType riskType) throws URISyntaxException {
        log.debug("REST request to save RiskType : {}", riskType);
        if (riskType.getId() != null) {
            throw new BadRequestAlertException("A new riskType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        riskType = riskTypeService.save(riskType);
        return ResponseEntity.created(new URI("/api/risk-types/" + riskType.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, riskType.getId().toString()))
            .body(riskType);
    }

    /**
     * {@code PUT  /risk-types/:id} : Updates an existing riskType.
     *
     * @param id the id of the riskType to save.
     * @param riskType the riskType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskType,
     * or with status {@code 400 (Bad Request)} if the riskType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the riskType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RiskType> updateRiskType(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody RiskType riskType
    ) throws URISyntaxException {
        log.debug("REST request to update RiskType : {}, {}", id, riskType);
        if (riskType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, riskType.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!riskTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        riskType = riskTypeService.update(riskType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskType.getId().toString()))
            .body(riskType);
    }

    /**
     * {@code PATCH  /risk-types/:id} : Partial updates given fields of an existing riskType, field will ignore if it is null
     *
     * @param id the id of the riskType to save.
     * @param riskType the riskType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskType,
     * or with status {@code 400 (Bad Request)} if the riskType is not valid,
     * or with status {@code 404 (Not Found)} if the riskType is not found,
     * or with status {@code 500 (Internal Server Error)} if the riskType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RiskType> partialUpdateRiskType(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody RiskType riskType
    ) throws URISyntaxException {
        log.debug("REST request to partial update RiskType partially : {}, {}", id, riskType);
        if (riskType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, riskType.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!riskTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RiskType> result = riskTypeService.partialUpdate(riskType);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskType.getId().toString())
        );
    }

    /**
     * {@code GET  /risk-types} : get all the riskTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of riskTypes in body.
     */
    @GetMapping("")
    public List<RiskType> getAllRiskTypes() {
        log.debug("REST request to get all RiskTypes");
        return riskTypeService.findAll();
    }

    /**
     * {@code GET  /risk-types/:id} : get the "id" riskType.
     *
     * @param id the id of the riskType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the riskType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RiskType> getRiskType(@PathVariable("id") Integer id) {
        log.debug("REST request to get RiskType : {}", id);
        Optional<RiskType> riskType = riskTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(riskType);
    }

    /**
     * {@code DELETE  /risk-types/:id} : delete the "id" riskType.
     *
     * @param id the id of the riskType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRiskType(@PathVariable("id") Integer id) {
        log.debug("REST request to delete RiskType : {}", id);
        riskTypeService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
