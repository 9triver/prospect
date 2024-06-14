package com.cvicse.web.rest;

import com.cvicse.domain.EvaluationCriteria;
import com.cvicse.repository.EvaluationCriteriaRepository;
import com.cvicse.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.cvicse.domain.EvaluationCriteria}.
 */
@RestController
@RequestMapping("/api/evaluation-criteria")
@Transactional
public class EvaluationCriteriaResource {

    private final Logger log = LoggerFactory.getLogger(EvaluationCriteriaResource.class);

    private static final String ENTITY_NAME = "evaluationCriteria";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EvaluationCriteriaRepository evaluationCriteriaRepository;

    public EvaluationCriteriaResource(EvaluationCriteriaRepository evaluationCriteriaRepository) {
        this.evaluationCriteriaRepository = evaluationCriteriaRepository;
    }

    /**
     * {@code POST  /evaluation-criteria} : Create a new evaluationCriteria.
     *
     * @param evaluationCriteria the evaluationCriteria to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new evaluationCriteria, or with status {@code 400 (Bad Request)} if the evaluationCriteria has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EvaluationCriteria> createEvaluationCriteria(@RequestBody EvaluationCriteria evaluationCriteria)
        throws URISyntaxException {
        log.debug("REST request to save EvaluationCriteria : {}", evaluationCriteria);
        if (evaluationCriteria.getId() != null) {
            throw new BadRequestAlertException("A new evaluationCriteria cannot already have an ID", ENTITY_NAME, "idexists");
        }
        evaluationCriteria = evaluationCriteriaRepository.save(evaluationCriteria);
        return ResponseEntity.created(new URI("/api/evaluation-criteria/" + evaluationCriteria.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, evaluationCriteria.getId().toString()))
            .body(evaluationCriteria);
    }

    /**
     * {@code PUT  /evaluation-criteria/:id} : Updates an existing evaluationCriteria.
     *
     * @param id the id of the evaluationCriteria to save.
     * @param evaluationCriteria the evaluationCriteria to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated evaluationCriteria,
     * or with status {@code 400 (Bad Request)} if the evaluationCriteria is not valid,
     * or with status {@code 500 (Internal Server Error)} if the evaluationCriteria couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EvaluationCriteria> updateEvaluationCriteria(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EvaluationCriteria evaluationCriteria
    ) throws URISyntaxException {
        log.debug("REST request to update EvaluationCriteria : {}, {}", id, evaluationCriteria);
        if (evaluationCriteria.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, evaluationCriteria.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!evaluationCriteriaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        evaluationCriteria = evaluationCriteriaRepository.save(evaluationCriteria);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, evaluationCriteria.getId().toString()))
            .body(evaluationCriteria);
    }

    /**
     * {@code PATCH  /evaluation-criteria/:id} : Partial updates given fields of an existing evaluationCriteria, field will ignore if it is null
     *
     * @param id the id of the evaluationCriteria to save.
     * @param evaluationCriteria the evaluationCriteria to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated evaluationCriteria,
     * or with status {@code 400 (Bad Request)} if the evaluationCriteria is not valid,
     * or with status {@code 404 (Not Found)} if the evaluationCriteria is not found,
     * or with status {@code 500 (Internal Server Error)} if the evaluationCriteria couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EvaluationCriteria> partialUpdateEvaluationCriteria(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EvaluationCriteria evaluationCriteria
    ) throws URISyntaxException {
        log.debug("REST request to partial update EvaluationCriteria partially : {}, {}", id, evaluationCriteria);
        if (evaluationCriteria.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, evaluationCriteria.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!evaluationCriteriaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EvaluationCriteria> result = evaluationCriteriaRepository
            .findById(evaluationCriteria.getId())
            .map(existingEvaluationCriteria -> {
                if (evaluationCriteria.getStandardtype() != null) {
                    existingEvaluationCriteria.setStandardtype(evaluationCriteria.getStandardtype());
                }
                if (evaluationCriteria.getStandardname() != null) {
                    existingEvaluationCriteria.setStandardname(evaluationCriteria.getStandardname());
                }
                if (evaluationCriteria.getMark() != null) {
                    existingEvaluationCriteria.setMark(evaluationCriteria.getMark());
                }

                return existingEvaluationCriteria;
            })
            .map(evaluationCriteriaRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, evaluationCriteria.getId().toString())
        );
    }

    /**
     * {@code GET  /evaluation-criteria} : get all the evaluationCriteria.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of evaluationCriteria in body.
     */
    @GetMapping("")
    public List<EvaluationCriteria> getAllEvaluationCriteria(@RequestParam(name = "filter", required = false) String filter) {
        if ("project-is-null".equals(filter)) {
            log.debug("REST request to get all EvaluationCriterias where project is null");
            return StreamSupport.stream(evaluationCriteriaRepository.findAll().spliterator(), false)
                .filter(evaluationCriteria -> evaluationCriteria.getProject() == null)
                .toList();
        }

        if ("managementcapacityevaluation-is-null".equals(filter)) {
            log.debug("REST request to get all EvaluationCriterias where managementCapacityEvaluation is null");
            return StreamSupport.stream(evaluationCriteriaRepository.findAll().spliterator(), false)
                .filter(evaluationCriteria -> evaluationCriteria.getManagementCapacityEvaluation() == null)
                .toList();
        }
        log.debug("REST request to get all EvaluationCriteria");
        return evaluationCriteriaRepository.findAll();
    }

    /**
     * {@code GET  /evaluation-criteria/:id} : get the "id" evaluationCriteria.
     *
     * @param id the id of the evaluationCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the evaluationCriteria, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EvaluationCriteria> getEvaluationCriteria(@PathVariable("id") Long id) {
        log.debug("REST request to get EvaluationCriteria : {}", id);
        Optional<EvaluationCriteria> evaluationCriteria = evaluationCriteriaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(evaluationCriteria);
    }

    /**
     * {@code DELETE  /evaluation-criteria/:id} : delete the "id" evaluationCriteria.
     *
     * @param id the id of the evaluationCriteria to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluationCriteria(@PathVariable("id") Long id) {
        log.debug("REST request to delete EvaluationCriteria : {}", id);
        evaluationCriteriaRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
