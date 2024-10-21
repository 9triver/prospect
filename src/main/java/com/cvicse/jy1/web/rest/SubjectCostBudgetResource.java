package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.SubjectCostBudget;
import com.cvicse.jy1.repository.SubjectCostBudgetRepository;
import com.cvicse.jy1.service.SubjectCostBudgetService;
import com.cvicse.jy1.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.SubjectCostBudget}.
 */
@RestController
@RequestMapping("/api/subject-cost-budgets")
public class SubjectCostBudgetResource {

    private static final Logger log = LoggerFactory.getLogger(SubjectCostBudgetResource.class);

    private static final String ENTITY_NAME = "subjectCostBudget";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SubjectCostBudgetService subjectCostBudgetService;

    private final SubjectCostBudgetRepository subjectCostBudgetRepository;

    public SubjectCostBudgetResource(
        SubjectCostBudgetService subjectCostBudgetService,
        SubjectCostBudgetRepository subjectCostBudgetRepository
    ) {
        this.subjectCostBudgetService = subjectCostBudgetService;
        this.subjectCostBudgetRepository = subjectCostBudgetRepository;
    }

    /**
     * {@code POST  /subject-cost-budgets} : Create a new subjectCostBudget.
     *
     * @param subjectCostBudget the subjectCostBudget to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subjectCostBudget, or with status {@code 400 (Bad Request)} if the subjectCostBudget has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<SubjectCostBudget> createSubjectCostBudget(@Valid @RequestBody SubjectCostBudget subjectCostBudget)
        throws URISyntaxException {
        log.debug("REST request to save SubjectCostBudget : {}", subjectCostBudget);
        if (subjectCostBudget.getId() != null) {
            throw new BadRequestAlertException("A new subjectCostBudget cannot already have an ID", ENTITY_NAME, "idexists");
        }
        subjectCostBudget = subjectCostBudgetService.save(subjectCostBudget);
        return ResponseEntity.created(new URI("/api/subject-cost-budgets/" + subjectCostBudget.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, subjectCostBudget.getId().toString()))
            .body(subjectCostBudget);
    }

    /**
     * {@code PUT  /subject-cost-budgets/:id} : Updates an existing subjectCostBudget.
     *
     * @param id the id of the subjectCostBudget to save.
     * @param subjectCostBudget the subjectCostBudget to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subjectCostBudget,
     * or with status {@code 400 (Bad Request)} if the subjectCostBudget is not valid,
     * or with status {@code 500 (Internal Server Error)} if the subjectCostBudget couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SubjectCostBudget> updateSubjectCostBudget(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SubjectCostBudget subjectCostBudget
    ) throws URISyntaxException {
        log.debug("REST request to update SubjectCostBudget : {}, {}", id, subjectCostBudget);
        if (subjectCostBudget.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, subjectCostBudget.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!subjectCostBudgetRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        subjectCostBudget = subjectCostBudgetService.update(subjectCostBudget);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, subjectCostBudget.getId().toString()))
            .body(subjectCostBudget);
    }

    /**
     * {@code PATCH  /subject-cost-budgets/:id} : Partial updates given fields of an existing subjectCostBudget, field will ignore if it is null
     *
     * @param id the id of the subjectCostBudget to save.
     * @param subjectCostBudget the subjectCostBudget to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subjectCostBudget,
     * or with status {@code 400 (Bad Request)} if the subjectCostBudget is not valid,
     * or with status {@code 404 (Not Found)} if the subjectCostBudget is not found,
     * or with status {@code 500 (Internal Server Error)} if the subjectCostBudget couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SubjectCostBudget> partialUpdateSubjectCostBudget(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SubjectCostBudget subjectCostBudget
    ) throws URISyntaxException {
        log.debug("REST request to partial update SubjectCostBudget partially : {}, {}", id, subjectCostBudget);
        if (subjectCostBudget.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, subjectCostBudget.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!subjectCostBudgetRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SubjectCostBudget> result = subjectCostBudgetService.partialUpdate(subjectCostBudget);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, subjectCostBudget.getId().toString())
        );
    }

    /**
     * {@code GET  /subject-cost-budgets} : get all the subjectCostBudgets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subjectCostBudgets in body.
     */
    @GetMapping("")
    public List<SubjectCostBudget> getAllSubjectCostBudgets() {
        log.debug("REST request to get all SubjectCostBudgets");
        return subjectCostBudgetService.findAll();
    }

    /**
     * {@code GET  /subject-cost-budgets/:id} : get the "id" subjectCostBudget.
     *
     * @param id the id of the subjectCostBudget to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subjectCostBudget, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SubjectCostBudget> getSubjectCostBudget(@PathVariable("id") Long id) {
        log.debug("REST request to get SubjectCostBudget : {}", id);
        Optional<SubjectCostBudget> subjectCostBudget = subjectCostBudgetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(subjectCostBudget);
    }

    /**
     * {@code DELETE  /subject-cost-budgets/:id} : delete the "id" subjectCostBudget.
     *
     * @param id the id of the subjectCostBudget to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubjectCostBudget(@PathVariable("id") Long id) {
        log.debug("REST request to delete SubjectCostBudget : {}", id);
        subjectCostBudgetService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
