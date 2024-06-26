package com.cvicse.web.rest;

import com.cvicse.domain.Planmonitor;
import com.cvicse.repository.PlanmonitorRepository;
import com.cvicse.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.cvicse.domain.Planmonitor}.
 */
@RestController
@RequestMapping("/api/planmonitors")
@Transactional
public class PlanmonitorResource {

    private final Logger log = LoggerFactory.getLogger(PlanmonitorResource.class);

    private static final String ENTITY_NAME = "planmonitor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlanmonitorRepository planmonitorRepository;

    public PlanmonitorResource(PlanmonitorRepository planmonitorRepository) {
        this.planmonitorRepository = planmonitorRepository;
    }

    /**
     * {@code POST  /planmonitors} : Create a new planmonitor.
     *
     * @param planmonitor the planmonitor to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new planmonitor, or with status {@code 400 (Bad Request)} if the planmonitor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Planmonitor> createPlanmonitor(@RequestBody Planmonitor planmonitor) throws URISyntaxException {
        log.debug("REST request to save Planmonitor : {}", planmonitor);
        if (planmonitor.getId() != null) {
            throw new BadRequestAlertException("A new planmonitor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        planmonitor = planmonitorRepository.save(planmonitor);
        return ResponseEntity.created(new URI("/api/planmonitors/" + planmonitor.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, planmonitor.getId()))
            .body(planmonitor);
    }

    /**
     * {@code PUT  /planmonitors/:id} : Updates an existing planmonitor.
     *
     * @param id the id of the planmonitor to save.
     * @param planmonitor the planmonitor to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planmonitor,
     * or with status {@code 400 (Bad Request)} if the planmonitor is not valid,
     * or with status {@code 500 (Internal Server Error)} if the planmonitor couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Planmonitor> updatePlanmonitor(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Planmonitor planmonitor
    ) throws URISyntaxException {
        log.debug("REST request to update Planmonitor : {}, {}", id, planmonitor);
        if (planmonitor.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, planmonitor.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!planmonitorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        planmonitor = planmonitorRepository.save(planmonitor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, planmonitor.getId()))
            .body(planmonitor);
    }

    /**
     * {@code PATCH  /planmonitors/:id} : Partial updates given fields of an existing planmonitor, field will ignore if it is null
     *
     * @param id the id of the planmonitor to save.
     * @param planmonitor the planmonitor to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planmonitor,
     * or with status {@code 400 (Bad Request)} if the planmonitor is not valid,
     * or with status {@code 404 (Not Found)} if the planmonitor is not found,
     * or with status {@code 500 (Internal Server Error)} if the planmonitor couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Planmonitor> partialUpdatePlanmonitor(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Planmonitor planmonitor
    ) throws URISyntaxException {
        log.debug("REST request to partial update Planmonitor partially : {}, {}", id, planmonitor);
        if (planmonitor.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, planmonitor.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!planmonitorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Planmonitor> result = planmonitorRepository
            .findById(planmonitor.getId())
            .map(existingPlanmonitor -> {
                if (planmonitor.getMonth() != null) {
                    existingPlanmonitor.setMonth(planmonitor.getMonth());
                }
                if (planmonitor.getType() != null) {
                    existingPlanmonitor.setType(planmonitor.getType());
                }
                if (planmonitor.getYear() != null) {
                    existingPlanmonitor.setYear(planmonitor.getYear());
                }
                if (planmonitor.getSecretlevel() != null) {
                    existingPlanmonitor.setSecretlevel(planmonitor.getSecretlevel());
                }
                if (planmonitor.getStatus() != null) {
                    existingPlanmonitor.setStatus(planmonitor.getStatus());
                }

                return existingPlanmonitor;
            })
            .map(planmonitorRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, planmonitor.getId())
        );
    }

    /**
     * {@code GET  /planmonitors} : get all the planmonitors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of planmonitors in body.
     */
    @GetMapping("")
    public List<Planmonitor> getAllPlanmonitors() {
        log.debug("REST request to get all Planmonitors");
        return planmonitorRepository.findAll();
    }

    /**
     * {@code GET  /planmonitors/:id} : get the "id" planmonitor.
     *
     * @param id the id of the planmonitor to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the planmonitor, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Planmonitor> getPlanmonitor(@PathVariable("id") String id) {
        log.debug("REST request to get Planmonitor : {}", id);
        Optional<Planmonitor> planmonitor = planmonitorRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(planmonitor);
    }

    /**
     * {@code DELETE  /planmonitors/:id} : delete the "id" planmonitor.
     *
     * @param id the id of the planmonitor to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanmonitor(@PathVariable("id") String id) {
        log.debug("REST request to delete Planmonitor : {}", id);
        planmonitorRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
