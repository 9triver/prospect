package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.FundsEstimation;
import com.cvicse.jy1.repository.FundsEstimationRepository;
import com.cvicse.jy1.service.FundsEstimationService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.FundsEstimation}.
 */
@RestController
@RequestMapping("/api/funds-estimations")
public class FundsEstimationResource {

    private static final Logger log = LoggerFactory.getLogger(FundsEstimationResource.class);

    private static final String ENTITY_NAME = "fundsEstimation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FundsEstimationService fundsEstimationService;

    private final FundsEstimationRepository fundsEstimationRepository;

    public FundsEstimationResource(FundsEstimationService fundsEstimationService, FundsEstimationRepository fundsEstimationRepository) {
        this.fundsEstimationService = fundsEstimationService;
        this.fundsEstimationRepository = fundsEstimationRepository;
    }

    /**
     * {@code POST  /funds-estimations} : Create a new fundsEstimation.
     *
     * @param fundsEstimation the fundsEstimation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fundsEstimation, or with status {@code 400 (Bad Request)} if the fundsEstimation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FundsEstimation> createFundsEstimation(@RequestBody FundsEstimation fundsEstimation) throws URISyntaxException {
        log.debug("REST request to save FundsEstimation : {}", fundsEstimation);
        if (fundsEstimation.getId() != null) {
            throw new BadRequestAlertException("A new fundsEstimation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fundsEstimation = fundsEstimationService.save(fundsEstimation);
        return ResponseEntity.created(new URI("/api/funds-estimations/" + fundsEstimation.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, fundsEstimation.getId()))
            .body(fundsEstimation);
    }

    /**
     * {@code PUT  /funds-estimations/:id} : Updates an existing fundsEstimation.
     *
     * @param id the id of the fundsEstimation to save.
     * @param fundsEstimation the fundsEstimation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fundsEstimation,
     * or with status {@code 400 (Bad Request)} if the fundsEstimation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fundsEstimation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FundsEstimation> updateFundsEstimation(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody FundsEstimation fundsEstimation
    ) throws URISyntaxException {
        log.debug("REST request to update FundsEstimation : {}, {}", id, fundsEstimation);
        if (fundsEstimation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fundsEstimation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fundsEstimationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        fundsEstimation = fundsEstimationService.update(fundsEstimation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fundsEstimation.getId()))
            .body(fundsEstimation);
    }

    /**
     * {@code PATCH  /funds-estimations/:id} : Partial updates given fields of an existing fundsEstimation, field will ignore if it is null
     *
     * @param id the id of the fundsEstimation to save.
     * @param fundsEstimation the fundsEstimation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fundsEstimation,
     * or with status {@code 400 (Bad Request)} if the fundsEstimation is not valid,
     * or with status {@code 404 (Not Found)} if the fundsEstimation is not found,
     * or with status {@code 500 (Internal Server Error)} if the fundsEstimation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FundsEstimation> partialUpdateFundsEstimation(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody FundsEstimation fundsEstimation
    ) throws URISyntaxException {
        log.debug("REST request to partial update FundsEstimation partially : {}, {}", id, fundsEstimation);
        if (fundsEstimation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fundsEstimation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fundsEstimationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FundsEstimation> result = fundsEstimationService.partialUpdate(fundsEstimation);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fundsEstimation.getId())
        );
    }

    /**
     * {@code GET  /funds-estimations} : get all the fundsEstimations.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fundsEstimations in body.
     */
    @GetMapping("")
    public List<FundsEstimation> getAllFundsEstimations(
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get all FundsEstimations");
        return fundsEstimationService.findAll();
    }

    /**
     * {@code GET  /funds-estimations/:id} : get the "id" fundsEstimation.
     *
     * @param id the id of the fundsEstimation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fundsEstimation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FundsEstimation> getFundsEstimation(@PathVariable("id") String id) {
        log.debug("REST request to get FundsEstimation : {}", id);
        Optional<FundsEstimation> fundsEstimation = fundsEstimationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fundsEstimation);
    }

    /**
     * {@code DELETE  /funds-estimations/:id} : delete the "id" fundsEstimation.
     *
     * @param id the id of the fundsEstimation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFundsEstimation(@PathVariable("id") String id) {
        log.debug("REST request to delete FundsEstimation : {}", id);
        fundsEstimationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
