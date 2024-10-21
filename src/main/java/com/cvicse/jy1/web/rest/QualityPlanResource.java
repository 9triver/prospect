package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.QualityPlan;
import com.cvicse.jy1.repository.QualityPlanRepository;
import com.cvicse.jy1.service.QualityPlanService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.QualityPlan}.
 */
@RestController
@RequestMapping("/api/quality-plans")
public class QualityPlanResource {

    private static final Logger log = LoggerFactory.getLogger(QualityPlanResource.class);

    private static final String ENTITY_NAME = "qualityPlan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QualityPlanService qualityPlanService;

    private final QualityPlanRepository qualityPlanRepository;

    public QualityPlanResource(QualityPlanService qualityPlanService, QualityPlanRepository qualityPlanRepository) {
        this.qualityPlanService = qualityPlanService;
        this.qualityPlanRepository = qualityPlanRepository;
    }

    /**
     * {@code POST  /quality-plans} : Create a new qualityPlan.
     *
     * @param qualityPlan the qualityPlan to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qualityPlan, or with status {@code 400 (Bad Request)} if the qualityPlan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<QualityPlan> createQualityPlan(@RequestBody QualityPlan qualityPlan) throws URISyntaxException {
        log.debug("REST request to save QualityPlan : {}", qualityPlan);
        if (qualityPlan.getId() != null) {
            throw new BadRequestAlertException("A new qualityPlan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        qualityPlan = qualityPlanService.save(qualityPlan);
        return ResponseEntity.created(new URI("/api/quality-plans/" + qualityPlan.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, qualityPlan.getId()))
            .body(qualityPlan);
    }

    /**
     * {@code PUT  /quality-plans/:id} : Updates an existing qualityPlan.
     *
     * @param id the id of the qualityPlan to save.
     * @param qualityPlan the qualityPlan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualityPlan,
     * or with status {@code 400 (Bad Request)} if the qualityPlan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qualityPlan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<QualityPlan> updateQualityPlan(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody QualityPlan qualityPlan
    ) throws URISyntaxException {
        log.debug("REST request to update QualityPlan : {}, {}", id, qualityPlan);
        if (qualityPlan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualityPlan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualityPlanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        qualityPlan = qualityPlanService.update(qualityPlan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualityPlan.getId()))
            .body(qualityPlan);
    }

    /**
     * {@code PATCH  /quality-plans/:id} : Partial updates given fields of an existing qualityPlan, field will ignore if it is null
     *
     * @param id the id of the qualityPlan to save.
     * @param qualityPlan the qualityPlan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualityPlan,
     * or with status {@code 400 (Bad Request)} if the qualityPlan is not valid,
     * or with status {@code 404 (Not Found)} if the qualityPlan is not found,
     * or with status {@code 500 (Internal Server Error)} if the qualityPlan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<QualityPlan> partialUpdateQualityPlan(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody QualityPlan qualityPlan
    ) throws URISyntaxException {
        log.debug("REST request to partial update QualityPlan partially : {}, {}", id, qualityPlan);
        if (qualityPlan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualityPlan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualityPlanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QualityPlan> result = qualityPlanService.partialUpdate(qualityPlan);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualityPlan.getId())
        );
    }

    /**
     * {@code GET  /quality-plans} : get all the qualityPlans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qualityPlans in body.
     */
    @GetMapping("")
    public List<QualityPlan> getAllQualityPlans() {
        log.debug("REST request to get all QualityPlans");
        return qualityPlanService.findAll();
    }

    /**
     * {@code GET  /quality-plans/:id} : get the "id" qualityPlan.
     *
     * @param id the id of the qualityPlan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qualityPlan, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<QualityPlan> getQualityPlan(@PathVariable("id") String id) {
        log.debug("REST request to get QualityPlan : {}", id);
        Optional<QualityPlan> qualityPlan = qualityPlanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qualityPlan);
    }

    /**
     * {@code DELETE  /quality-plans/:id} : delete the "id" qualityPlan.
     *
     * @param id the id of the qualityPlan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQualityPlan(@PathVariable("id") String id) {
        log.debug("REST request to delete QualityPlan : {}", id);
        qualityPlanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
