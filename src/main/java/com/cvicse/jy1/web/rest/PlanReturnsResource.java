package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.PlanReturns;
import com.cvicse.jy1.repository.PlanReturnsRepository;
import com.cvicse.jy1.service.PlanReturnsService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.PlanReturns}.
 */
@RestController
@RequestMapping("/api/plan-returns")
public class PlanReturnsResource {

    private static final Logger log = LoggerFactory.getLogger(PlanReturnsResource.class);

    private static final String ENTITY_NAME = "planReturns";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlanReturnsService planReturnsService;

    private final PlanReturnsRepository planReturnsRepository;

    public PlanReturnsResource(PlanReturnsService planReturnsService, PlanReturnsRepository planReturnsRepository) {
        this.planReturnsService = planReturnsService;
        this.planReturnsRepository = planReturnsRepository;
    }

    /**
     * {@code POST  /plan-returns} : Create a new planReturns.
     *
     * @param planReturns the planReturns to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new planReturns, or with status {@code 400 (Bad Request)} if the planReturns has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<PlanReturns> createPlanReturns(@RequestBody PlanReturns planReturns) throws URISyntaxException {
        log.debug("REST request to save PlanReturns : {}", planReturns);
        if (planReturns.getId() != null) {
            throw new BadRequestAlertException("A new planReturns cannot already have an ID", ENTITY_NAME, "idexists");
        }
        planReturns = planReturnsService.save(planReturns);
        return ResponseEntity.created(new URI("/api/plan-returns/" + planReturns.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, planReturns.getId()))
            .body(planReturns);
    }

    /**
     * {@code PUT  /plan-returns/:id} : Updates an existing planReturns.
     *
     * @param id the id of the planReturns to save.
     * @param planReturns the planReturns to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planReturns,
     * or with status {@code 400 (Bad Request)} if the planReturns is not valid,
     * or with status {@code 500 (Internal Server Error)} if the planReturns couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PlanReturns> updatePlanReturns(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody PlanReturns planReturns
    ) throws URISyntaxException {
        log.debug("REST request to update PlanReturns : {}, {}", id, planReturns);
        if (planReturns.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, planReturns.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!planReturnsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        planReturns = planReturnsService.update(planReturns);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, planReturns.getId()))
            .body(planReturns);
    }

    /**
     * {@code PATCH  /plan-returns/:id} : Partial updates given fields of an existing planReturns, field will ignore if it is null
     *
     * @param id the id of the planReturns to save.
     * @param planReturns the planReturns to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planReturns,
     * or with status {@code 400 (Bad Request)} if the planReturns is not valid,
     * or with status {@code 404 (Not Found)} if the planReturns is not found,
     * or with status {@code 500 (Internal Server Error)} if the planReturns couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PlanReturns> partialUpdatePlanReturns(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody PlanReturns planReturns
    ) throws URISyntaxException {
        log.debug("REST request to partial update PlanReturns partially : {}, {}", id, planReturns);
        if (planReturns.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, planReturns.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!planReturnsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PlanReturns> result = planReturnsService.partialUpdate(planReturns);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, planReturns.getId())
        );
    }

    /**
     * {@code GET  /plan-returns} : get all the planReturns.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of planReturns in body.
     */
    @GetMapping("")
    public List<PlanReturns> getAllPlanReturns() {
        log.debug("REST request to get all PlanReturns");
        return planReturnsService.findAll();
    }

    /**
     * {@code GET  /plan-returns/:id} : get the "id" planReturns.
     *
     * @param id the id of the planReturns to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the planReturns, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PlanReturns> getPlanReturns(@PathVariable("id") String id) {
        log.debug("REST request to get PlanReturns : {}", id);
        Optional<PlanReturns> planReturns = planReturnsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planReturns);
    }

    /**
     * {@code DELETE  /plan-returns/:id} : delete the "id" planReturns.
     *
     * @param id the id of the planReturns to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanReturns(@PathVariable("id") String id) {
        log.debug("REST request to delete PlanReturns : {}", id);
        planReturnsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
