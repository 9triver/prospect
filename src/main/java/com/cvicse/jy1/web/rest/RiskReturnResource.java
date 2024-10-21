package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.RiskReturn;
import com.cvicse.jy1.repository.RiskReturnRepository;
import com.cvicse.jy1.service.RiskReturnService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.RiskReturn}.
 */
@RestController
@RequestMapping("/api/risk-returns")
public class RiskReturnResource {

    private static final Logger log = LoggerFactory.getLogger(RiskReturnResource.class);

    private static final String ENTITY_NAME = "riskReturn";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RiskReturnService riskReturnService;

    private final RiskReturnRepository riskReturnRepository;

    public RiskReturnResource(RiskReturnService riskReturnService, RiskReturnRepository riskReturnRepository) {
        this.riskReturnService = riskReturnService;
        this.riskReturnRepository = riskReturnRepository;
    }

    /**
     * {@code POST  /risk-returns} : Create a new riskReturn.
     *
     * @param riskReturn the riskReturn to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new riskReturn, or with status {@code 400 (Bad Request)} if the riskReturn has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<RiskReturn> createRiskReturn(@RequestBody RiskReturn riskReturn) throws URISyntaxException {
        log.debug("REST request to save RiskReturn : {}", riskReturn);
        if (riskReturn.getId() != null) {
            throw new BadRequestAlertException("A new riskReturn cannot already have an ID", ENTITY_NAME, "idexists");
        }
        riskReturn = riskReturnService.save(riskReturn);
        return ResponseEntity.created(new URI("/api/risk-returns/" + riskReturn.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, riskReturn.getId().toString()))
            .body(riskReturn);
    }

    /**
     * {@code PUT  /risk-returns/:id} : Updates an existing riskReturn.
     *
     * @param id the id of the riskReturn to save.
     * @param riskReturn the riskReturn to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskReturn,
     * or with status {@code 400 (Bad Request)} if the riskReturn is not valid,
     * or with status {@code 500 (Internal Server Error)} if the riskReturn couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RiskReturn> updateRiskReturn(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody RiskReturn riskReturn
    ) throws URISyntaxException {
        log.debug("REST request to update RiskReturn : {}, {}", id, riskReturn);
        if (riskReturn.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, riskReturn.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!riskReturnRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        riskReturn = riskReturnService.update(riskReturn);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskReturn.getId().toString()))
            .body(riskReturn);
    }

    /**
     * {@code PATCH  /risk-returns/:id} : Partial updates given fields of an existing riskReturn, field will ignore if it is null
     *
     * @param id the id of the riskReturn to save.
     * @param riskReturn the riskReturn to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskReturn,
     * or with status {@code 400 (Bad Request)} if the riskReturn is not valid,
     * or with status {@code 404 (Not Found)} if the riskReturn is not found,
     * or with status {@code 500 (Internal Server Error)} if the riskReturn couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RiskReturn> partialUpdateRiskReturn(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody RiskReturn riskReturn
    ) throws URISyntaxException {
        log.debug("REST request to partial update RiskReturn partially : {}, {}", id, riskReturn);
        if (riskReturn.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, riskReturn.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!riskReturnRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RiskReturn> result = riskReturnService.partialUpdate(riskReturn);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskReturn.getId().toString())
        );
    }

    /**
     * {@code GET  /risk-returns} : get all the riskReturns.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of riskReturns in body.
     */
    @GetMapping("")
    public List<RiskReturn> getAllRiskReturns() {
        log.debug("REST request to get all RiskReturns");
        return riskReturnService.findAll();
    }

    /**
     * {@code GET  /risk-returns/:id} : get the "id" riskReturn.
     *
     * @param id the id of the riskReturn to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the riskReturn, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RiskReturn> getRiskReturn(@PathVariable("id") Integer id) {
        log.debug("REST request to get RiskReturn : {}", id);
        Optional<RiskReturn> riskReturn = riskReturnService.findOne(id);
        return ResponseUtil.wrapOrNotFound(riskReturn);
    }

    /**
     * {@code DELETE  /risk-returns/:id} : delete the "id" riskReturn.
     *
     * @param id the id of the riskReturn to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRiskReturn(@PathVariable("id") Integer id) {
        log.debug("REST request to delete RiskReturn : {}", id);
        riskReturnService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
