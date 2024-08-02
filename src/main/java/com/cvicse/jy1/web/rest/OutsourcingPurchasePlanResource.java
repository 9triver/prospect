package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.OutsourcingPurchasePlan;
import com.cvicse.jy1.repository.OutsourcingPurchasePlanRepository;
import com.cvicse.jy1.service.OutsourcingPurchasePlanService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.OutsourcingPurchasePlan}.
 */
@RestController
@RequestMapping("/api/outsourcing-purchase-plans")
public class OutsourcingPurchasePlanResource {

    private static final Logger log = LoggerFactory.getLogger(OutsourcingPurchasePlanResource.class);

    private static final String ENTITY_NAME = "outsourcingPurchasePlan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OutsourcingPurchasePlanService outsourcingPurchasePlanService;

    private final OutsourcingPurchasePlanRepository outsourcingPurchasePlanRepository;

    public OutsourcingPurchasePlanResource(
        OutsourcingPurchasePlanService outsourcingPurchasePlanService,
        OutsourcingPurchasePlanRepository outsourcingPurchasePlanRepository
    ) {
        this.outsourcingPurchasePlanService = outsourcingPurchasePlanService;
        this.outsourcingPurchasePlanRepository = outsourcingPurchasePlanRepository;
    }

    /**
     * {@code POST  /outsourcing-purchase-plans} : Create a new outsourcingPurchasePlan.
     *
     * @param outsourcingPurchasePlan the outsourcingPurchasePlan to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new outsourcingPurchasePlan, or with status {@code 400 (Bad Request)} if the outsourcingPurchasePlan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OutsourcingPurchasePlan> createOutsourcingPurchasePlan(
        @RequestBody OutsourcingPurchasePlan outsourcingPurchasePlan
    ) throws URISyntaxException {
        log.debug("REST request to save OutsourcingPurchasePlan : {}", outsourcingPurchasePlan);
        if (outsourcingPurchasePlan.getId() != null) {
            throw new BadRequestAlertException("A new outsourcingPurchasePlan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        outsourcingPurchasePlan = outsourcingPurchasePlanService.save(outsourcingPurchasePlan);
        return ResponseEntity.created(new URI("/api/outsourcing-purchase-plans/" + outsourcingPurchasePlan.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, outsourcingPurchasePlan.getId()))
            .body(outsourcingPurchasePlan);
    }

    /**
     * {@code PUT  /outsourcing-purchase-plans/:id} : Updates an existing outsourcingPurchasePlan.
     *
     * @param id the id of the outsourcingPurchasePlan to save.
     * @param outsourcingPurchasePlan the outsourcingPurchasePlan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outsourcingPurchasePlan,
     * or with status {@code 400 (Bad Request)} if the outsourcingPurchasePlan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the outsourcingPurchasePlan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OutsourcingPurchasePlan> updateOutsourcingPurchasePlan(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody OutsourcingPurchasePlan outsourcingPurchasePlan
    ) throws URISyntaxException {
        log.debug("REST request to update OutsourcingPurchasePlan : {}, {}", id, outsourcingPurchasePlan);
        if (outsourcingPurchasePlan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outsourcingPurchasePlan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!outsourcingPurchasePlanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        outsourcingPurchasePlan = outsourcingPurchasePlanService.update(outsourcingPurchasePlan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, outsourcingPurchasePlan.getId()))
            .body(outsourcingPurchasePlan);
    }

    /**
     * {@code PATCH  /outsourcing-purchase-plans/:id} : Partial updates given fields of an existing outsourcingPurchasePlan, field will ignore if it is null
     *
     * @param id the id of the outsourcingPurchasePlan to save.
     * @param outsourcingPurchasePlan the outsourcingPurchasePlan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outsourcingPurchasePlan,
     * or with status {@code 400 (Bad Request)} if the outsourcingPurchasePlan is not valid,
     * or with status {@code 404 (Not Found)} if the outsourcingPurchasePlan is not found,
     * or with status {@code 500 (Internal Server Error)} if the outsourcingPurchasePlan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OutsourcingPurchasePlan> partialUpdateOutsourcingPurchasePlan(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody OutsourcingPurchasePlan outsourcingPurchasePlan
    ) throws URISyntaxException {
        log.debug("REST request to partial update OutsourcingPurchasePlan partially : {}, {}", id, outsourcingPurchasePlan);
        if (outsourcingPurchasePlan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outsourcingPurchasePlan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!outsourcingPurchasePlanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OutsourcingPurchasePlan> result = outsourcingPurchasePlanService.partialUpdate(outsourcingPurchasePlan);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, outsourcingPurchasePlan.getId())
        );
    }

    /**
     * {@code GET  /outsourcing-purchase-plans} : get all the outsourcingPurchasePlans.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of outsourcingPurchasePlans in body.
     */
    @GetMapping("")
    public List<OutsourcingPurchasePlan> getAllOutsourcingPurchasePlans(
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get all OutsourcingPurchasePlans");
        return outsourcingPurchasePlanService.findAll();
    }

    /**
     * {@code GET  /outsourcing-purchase-plans/:id} : get the "id" outsourcingPurchasePlan.
     *
     * @param id the id of the outsourcingPurchasePlan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the outsourcingPurchasePlan, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OutsourcingPurchasePlan> getOutsourcingPurchasePlan(@PathVariable("id") String id) {
        log.debug("REST request to get OutsourcingPurchasePlan : {}", id);
        Optional<OutsourcingPurchasePlan> outsourcingPurchasePlan = outsourcingPurchasePlanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(outsourcingPurchasePlan);
    }

    /**
     * {@code DELETE  /outsourcing-purchase-plans/:id} : delete the "id" outsourcingPurchasePlan.
     *
     * @param id the id of the outsourcingPurchasePlan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOutsourcingPurchasePlan(@PathVariable("id") String id) {
        log.debug("REST request to delete OutsourcingPurchasePlan : {}", id);
        outsourcingPurchasePlanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
