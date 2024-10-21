package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.TechnicalCondition;
import com.cvicse.jy1.repository.TechnicalConditionRepository;
import com.cvicse.jy1.service.TechnicalConditionService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.TechnicalCondition}.
 */
@RestController
@RequestMapping("/api/technical-conditions")
public class TechnicalConditionResource {

    private static final Logger log = LoggerFactory.getLogger(TechnicalConditionResource.class);

    private static final String ENTITY_NAME = "technicalCondition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TechnicalConditionService technicalConditionService;

    private final TechnicalConditionRepository technicalConditionRepository;

    public TechnicalConditionResource(
        TechnicalConditionService technicalConditionService,
        TechnicalConditionRepository technicalConditionRepository
    ) {
        this.technicalConditionService = technicalConditionService;
        this.technicalConditionRepository = technicalConditionRepository;
    }

    /**
     * {@code POST  /technical-conditions} : Create a new technicalCondition.
     *
     * @param technicalCondition the technicalCondition to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new technicalCondition, or with status {@code 400 (Bad Request)} if the technicalCondition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TechnicalCondition> createTechnicalCondition(@RequestBody TechnicalCondition technicalCondition)
        throws URISyntaxException {
        log.debug("REST request to save TechnicalCondition : {}", technicalCondition);
        if (technicalCondition.getId() != null) {
            throw new BadRequestAlertException("A new technicalCondition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        technicalCondition = technicalConditionService.save(technicalCondition);
        return ResponseEntity.created(new URI("/api/technical-conditions/" + technicalCondition.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, technicalCondition.getId().toString()))
            .body(technicalCondition);
    }

    /**
     * {@code PUT  /technical-conditions/:id} : Updates an existing technicalCondition.
     *
     * @param id the id of the technicalCondition to save.
     * @param technicalCondition the technicalCondition to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated technicalCondition,
     * or with status {@code 400 (Bad Request)} if the technicalCondition is not valid,
     * or with status {@code 500 (Internal Server Error)} if the technicalCondition couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TechnicalCondition> updateTechnicalCondition(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody TechnicalCondition technicalCondition
    ) throws URISyntaxException {
        log.debug("REST request to update TechnicalCondition : {}, {}", id, technicalCondition);
        if (technicalCondition.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, technicalCondition.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!technicalConditionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        technicalCondition = technicalConditionService.update(technicalCondition);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, technicalCondition.getId().toString()))
            .body(technicalCondition);
    }

    /**
     * {@code PATCH  /technical-conditions/:id} : Partial updates given fields of an existing technicalCondition, field will ignore if it is null
     *
     * @param id the id of the technicalCondition to save.
     * @param technicalCondition the technicalCondition to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated technicalCondition,
     * or with status {@code 400 (Bad Request)} if the technicalCondition is not valid,
     * or with status {@code 404 (Not Found)} if the technicalCondition is not found,
     * or with status {@code 500 (Internal Server Error)} if the technicalCondition couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TechnicalCondition> partialUpdateTechnicalCondition(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody TechnicalCondition technicalCondition
    ) throws URISyntaxException {
        log.debug("REST request to partial update TechnicalCondition partially : {}, {}", id, technicalCondition);
        if (technicalCondition.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, technicalCondition.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!technicalConditionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TechnicalCondition> result = technicalConditionService.partialUpdate(technicalCondition);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, technicalCondition.getId().toString())
        );
    }

    /**
     * {@code GET  /technical-conditions} : get all the technicalConditions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of technicalConditions in body.
     */
    @GetMapping("")
    public List<TechnicalCondition> getAllTechnicalConditions() {
        log.debug("REST request to get all TechnicalConditions");
        return technicalConditionService.findAll();
    }

    /**
     * {@code GET  /technical-conditions/:id} : get the "id" technicalCondition.
     *
     * @param id the id of the technicalCondition to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the technicalCondition, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TechnicalCondition> getTechnicalCondition(@PathVariable("id") Integer id) {
        log.debug("REST request to get TechnicalCondition : {}", id);
        Optional<TechnicalCondition> technicalCondition = technicalConditionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(technicalCondition);
    }

    /**
     * {@code DELETE  /technical-conditions/:id} : delete the "id" technicalCondition.
     *
     * @param id the id of the technicalCondition to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnicalCondition(@PathVariable("id") Integer id) {
        log.debug("REST request to delete TechnicalCondition : {}", id);
        technicalConditionService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
