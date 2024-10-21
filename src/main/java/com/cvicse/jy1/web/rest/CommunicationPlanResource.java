package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.CommunicationPlan;
import com.cvicse.jy1.repository.CommunicationPlanRepository;
import com.cvicse.jy1.service.CommunicationPlanService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.CommunicationPlan}.
 */
@RestController
@RequestMapping("/api/communication-plans")
public class CommunicationPlanResource {

    private static final Logger log = LoggerFactory.getLogger(CommunicationPlanResource.class);

    private static final String ENTITY_NAME = "communicationPlan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommunicationPlanService communicationPlanService;

    private final CommunicationPlanRepository communicationPlanRepository;

    public CommunicationPlanResource(
        CommunicationPlanService communicationPlanService,
        CommunicationPlanRepository communicationPlanRepository
    ) {
        this.communicationPlanService = communicationPlanService;
        this.communicationPlanRepository = communicationPlanRepository;
    }

    /**
     * {@code POST  /communication-plans} : Create a new communicationPlan.
     *
     * @param communicationPlan the communicationPlan to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new communicationPlan, or with status {@code 400 (Bad Request)} if the communicationPlan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CommunicationPlan> createCommunicationPlan(@RequestBody CommunicationPlan communicationPlan)
        throws URISyntaxException {
        log.debug("REST request to save CommunicationPlan : {}", communicationPlan);
        if (communicationPlan.getId() != null) {
            throw new BadRequestAlertException("A new communicationPlan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        communicationPlan = communicationPlanService.save(communicationPlan);
        return ResponseEntity.created(new URI("/api/communication-plans/" + communicationPlan.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, communicationPlan.getId().toString()))
            .body(communicationPlan);
    }

    /**
     * {@code PUT  /communication-plans/:id} : Updates an existing communicationPlan.
     *
     * @param id the id of the communicationPlan to save.
     * @param communicationPlan the communicationPlan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated communicationPlan,
     * or with status {@code 400 (Bad Request)} if the communicationPlan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the communicationPlan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CommunicationPlan> updateCommunicationPlan(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody CommunicationPlan communicationPlan
    ) throws URISyntaxException {
        log.debug("REST request to update CommunicationPlan : {}, {}", id, communicationPlan);
        if (communicationPlan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, communicationPlan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!communicationPlanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        communicationPlan = communicationPlanService.update(communicationPlan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, communicationPlan.getId().toString()))
            .body(communicationPlan);
    }

    /**
     * {@code PATCH  /communication-plans/:id} : Partial updates given fields of an existing communicationPlan, field will ignore if it is null
     *
     * @param id the id of the communicationPlan to save.
     * @param communicationPlan the communicationPlan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated communicationPlan,
     * or with status {@code 400 (Bad Request)} if the communicationPlan is not valid,
     * or with status {@code 404 (Not Found)} if the communicationPlan is not found,
     * or with status {@code 500 (Internal Server Error)} if the communicationPlan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CommunicationPlan> partialUpdateCommunicationPlan(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody CommunicationPlan communicationPlan
    ) throws URISyntaxException {
        log.debug("REST request to partial update CommunicationPlan partially : {}, {}", id, communicationPlan);
        if (communicationPlan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, communicationPlan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!communicationPlanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CommunicationPlan> result = communicationPlanService.partialUpdate(communicationPlan);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, communicationPlan.getId().toString())
        );
    }

    /**
     * {@code GET  /communication-plans} : get all the communicationPlans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of communicationPlans in body.
     */
    @GetMapping("")
    public List<CommunicationPlan> getAllCommunicationPlans() {
        log.debug("REST request to get all CommunicationPlans");
        return communicationPlanService.findAll();
    }

    /**
     * {@code GET  /communication-plans/:id} : get the "id" communicationPlan.
     *
     * @param id the id of the communicationPlan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the communicationPlan, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CommunicationPlan> getCommunicationPlan(@PathVariable("id") Integer id) {
        log.debug("REST request to get CommunicationPlan : {}", id);
        Optional<CommunicationPlan> communicationPlan = communicationPlanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(communicationPlan);
    }

    /**
     * {@code DELETE  /communication-plans/:id} : delete the "id" communicationPlan.
     *
     * @param id the id of the communicationPlan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommunicationPlan(@PathVariable("id") Integer id) {
        log.debug("REST request to delete CommunicationPlan : {}", id);
        communicationPlanService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
