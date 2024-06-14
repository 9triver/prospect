package com.cvicse.web.rest;

import com.cvicse.domain.ManagementCapacityEvaluation;
import com.cvicse.repository.ManagementCapacityEvaluationRepository;
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
 * REST controller for managing {@link com.cvicse.domain.ManagementCapacityEvaluation}.
 */
@RestController
@RequestMapping("/api/management-capacity-evaluations")
@Transactional
public class ManagementCapacityEvaluationResource {

    private final Logger log = LoggerFactory.getLogger(ManagementCapacityEvaluationResource.class);

    private static final String ENTITY_NAME = "managementCapacityEvaluation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ManagementCapacityEvaluationRepository managementCapacityEvaluationRepository;

    public ManagementCapacityEvaluationResource(ManagementCapacityEvaluationRepository managementCapacityEvaluationRepository) {
        this.managementCapacityEvaluationRepository = managementCapacityEvaluationRepository;
    }

    /**
     * {@code POST  /management-capacity-evaluations} : Create a new managementCapacityEvaluation.
     *
     * @param managementCapacityEvaluation the managementCapacityEvaluation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new managementCapacityEvaluation, or with status {@code 400 (Bad Request)} if the managementCapacityEvaluation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ManagementCapacityEvaluation> createManagementCapacityEvaluation(
        @RequestBody ManagementCapacityEvaluation managementCapacityEvaluation
    ) throws URISyntaxException {
        log.debug("REST request to save ManagementCapacityEvaluation : {}", managementCapacityEvaluation);
        if (managementCapacityEvaluation.getId() != null) {
            throw new BadRequestAlertException("A new managementCapacityEvaluation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        managementCapacityEvaluation = managementCapacityEvaluationRepository.save(managementCapacityEvaluation);
        return ResponseEntity.created(new URI("/api/management-capacity-evaluations/" + managementCapacityEvaluation.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, managementCapacityEvaluation.getId().toString())
            )
            .body(managementCapacityEvaluation);
    }

    /**
     * {@code PUT  /management-capacity-evaluations/:id} : Updates an existing managementCapacityEvaluation.
     *
     * @param id the id of the managementCapacityEvaluation to save.
     * @param managementCapacityEvaluation the managementCapacityEvaluation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated managementCapacityEvaluation,
     * or with status {@code 400 (Bad Request)} if the managementCapacityEvaluation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the managementCapacityEvaluation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ManagementCapacityEvaluation> updateManagementCapacityEvaluation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ManagementCapacityEvaluation managementCapacityEvaluation
    ) throws URISyntaxException {
        log.debug("REST request to update ManagementCapacityEvaluation : {}, {}", id, managementCapacityEvaluation);
        if (managementCapacityEvaluation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, managementCapacityEvaluation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!managementCapacityEvaluationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        managementCapacityEvaluation = managementCapacityEvaluationRepository.save(managementCapacityEvaluation);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, managementCapacityEvaluation.getId().toString())
            )
            .body(managementCapacityEvaluation);
    }

    /**
     * {@code PATCH  /management-capacity-evaluations/:id} : Partial updates given fields of an existing managementCapacityEvaluation, field will ignore if it is null
     *
     * @param id the id of the managementCapacityEvaluation to save.
     * @param managementCapacityEvaluation the managementCapacityEvaluation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated managementCapacityEvaluation,
     * or with status {@code 400 (Bad Request)} if the managementCapacityEvaluation is not valid,
     * or with status {@code 404 (Not Found)} if the managementCapacityEvaluation is not found,
     * or with status {@code 500 (Internal Server Error)} if the managementCapacityEvaluation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ManagementCapacityEvaluation> partialUpdateManagementCapacityEvaluation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ManagementCapacityEvaluation managementCapacityEvaluation
    ) throws URISyntaxException {
        log.debug("REST request to partial update ManagementCapacityEvaluation partially : {}, {}", id, managementCapacityEvaluation);
        if (managementCapacityEvaluation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, managementCapacityEvaluation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!managementCapacityEvaluationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ManagementCapacityEvaluation> result = managementCapacityEvaluationRepository
            .findById(managementCapacityEvaluation.getId())
            .map(existingManagementCapacityEvaluation -> {
                if (managementCapacityEvaluation.getYear() != null) {
                    existingManagementCapacityEvaluation.setYear(managementCapacityEvaluation.getYear());
                }
                if (managementCapacityEvaluation.getDeprotment() != null) {
                    existingManagementCapacityEvaluation.setDeprotment(managementCapacityEvaluation.getDeprotment());
                }
                if (managementCapacityEvaluation.getCreatetime() != null) {
                    existingManagementCapacityEvaluation.setCreatetime(managementCapacityEvaluation.getCreatetime());
                }
                if (managementCapacityEvaluation.getStatus() != null) {
                    existingManagementCapacityEvaluation.setStatus(managementCapacityEvaluation.getStatus());
                }
                if (managementCapacityEvaluation.getTotalmark() != null) {
                    existingManagementCapacityEvaluation.setTotalmark(managementCapacityEvaluation.getTotalmark());
                }
                if (managementCapacityEvaluation.getMark() != null) {
                    existingManagementCapacityEvaluation.setMark(managementCapacityEvaluation.getMark());
                }
                if (managementCapacityEvaluation.getRatingpersonname() != null) {
                    existingManagementCapacityEvaluation.setRatingpersonname(managementCapacityEvaluation.getRatingpersonname());
                }
                if (managementCapacityEvaluation.getRatingdepartment() != null) {
                    existingManagementCapacityEvaluation.setRatingdepartment(managementCapacityEvaluation.getRatingdepartment());
                }
                if (managementCapacityEvaluation.getRatingtimg() != null) {
                    existingManagementCapacityEvaluation.setRatingtimg(managementCapacityEvaluation.getRatingtimg());
                }

                return existingManagementCapacityEvaluation;
            })
            .map(managementCapacityEvaluationRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, managementCapacityEvaluation.getId().toString())
        );
    }

    /**
     * {@code GET  /management-capacity-evaluations} : get all the managementCapacityEvaluations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of managementCapacityEvaluations in body.
     */
    @GetMapping("")
    public List<ManagementCapacityEvaluation> getAllManagementCapacityEvaluations() {
        log.debug("REST request to get all ManagementCapacityEvaluations");
        return managementCapacityEvaluationRepository.findAll();
    }

    /**
     * {@code GET  /management-capacity-evaluations/:id} : get the "id" managementCapacityEvaluation.
     *
     * @param id the id of the managementCapacityEvaluation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the managementCapacityEvaluation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ManagementCapacityEvaluation> getManagementCapacityEvaluation(@PathVariable("id") Long id) {
        log.debug("REST request to get ManagementCapacityEvaluation : {}", id);
        Optional<ManagementCapacityEvaluation> managementCapacityEvaluation = managementCapacityEvaluationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(managementCapacityEvaluation);
    }

    /**
     * {@code DELETE  /management-capacity-evaluations/:id} : delete the "id" managementCapacityEvaluation.
     *
     * @param id the id of the managementCapacityEvaluation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManagementCapacityEvaluation(@PathVariable("id") Long id) {
        log.debug("REST request to delete ManagementCapacityEvaluation : {}", id);
        managementCapacityEvaluationRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
