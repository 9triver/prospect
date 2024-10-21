package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.ProjectBudget;
import com.cvicse.jy1.repository.ProjectBudgetRepository;
import com.cvicse.jy1.service.ProjectBudgetService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.ProjectBudget}.
 */
@RestController
@RequestMapping("/api/project-budgets")
public class ProjectBudgetResource {

    private static final Logger log = LoggerFactory.getLogger(ProjectBudgetResource.class);

    private static final String ENTITY_NAME = "projectBudget";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectBudgetService projectBudgetService;

    private final ProjectBudgetRepository projectBudgetRepository;

    public ProjectBudgetResource(ProjectBudgetService projectBudgetService, ProjectBudgetRepository projectBudgetRepository) {
        this.projectBudgetService = projectBudgetService;
        this.projectBudgetRepository = projectBudgetRepository;
    }

    /**
     * {@code POST  /project-budgets} : Create a new projectBudget.
     *
     * @param projectBudget the projectBudget to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectBudget, or with status {@code 400 (Bad Request)} if the projectBudget has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ProjectBudget> createProjectBudget(@Valid @RequestBody ProjectBudget projectBudget) throws URISyntaxException {
        log.debug("REST request to save ProjectBudget : {}", projectBudget);
        if (projectBudget.getId() != null) {
            throw new BadRequestAlertException("A new projectBudget cannot already have an ID", ENTITY_NAME, "idexists");
        }
        projectBudget = projectBudgetService.save(projectBudget);
        return ResponseEntity.created(new URI("/api/project-budgets/" + projectBudget.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, projectBudget.getId().toString()))
            .body(projectBudget);
    }

    /**
     * {@code PUT  /project-budgets/:id} : Updates an existing projectBudget.
     *
     * @param id the id of the projectBudget to save.
     * @param projectBudget the projectBudget to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectBudget,
     * or with status {@code 400 (Bad Request)} if the projectBudget is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectBudget couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProjectBudget> updateProjectBudget(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ProjectBudget projectBudget
    ) throws URISyntaxException {
        log.debug("REST request to update ProjectBudget : {}, {}", id, projectBudget);
        if (projectBudget.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectBudget.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectBudgetRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        projectBudget = projectBudgetService.update(projectBudget);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectBudget.getId().toString()))
            .body(projectBudget);
    }

    /**
     * {@code PATCH  /project-budgets/:id} : Partial updates given fields of an existing projectBudget, field will ignore if it is null
     *
     * @param id the id of the projectBudget to save.
     * @param projectBudget the projectBudget to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectBudget,
     * or with status {@code 400 (Bad Request)} if the projectBudget is not valid,
     * or with status {@code 404 (Not Found)} if the projectBudget is not found,
     * or with status {@code 500 (Internal Server Error)} if the projectBudget couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProjectBudget> partialUpdateProjectBudget(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ProjectBudget projectBudget
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProjectBudget partially : {}, {}", id, projectBudget);
        if (projectBudget.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectBudget.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectBudgetRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProjectBudget> result = projectBudgetService.partialUpdate(projectBudget);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectBudget.getId().toString())
        );
    }

    /**
     * {@code GET  /project-budgets} : get all the projectBudgets.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectBudgets in body.
     */
    @GetMapping("")
    public List<ProjectBudget> getAllProjectBudgets(
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get all ProjectBudgets");
        return projectBudgetService.findAll();
    }

    /**
     * {@code GET  /project-budgets/:id} : get the "id" projectBudget.
     *
     * @param id the id of the projectBudget to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectBudget, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProjectBudget> getProjectBudget(@PathVariable("id") Long id) {
        log.debug("REST request to get ProjectBudget : {}", id);
        Optional<ProjectBudget> projectBudget = projectBudgetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projectBudget);
    }

    /**
     * {@code DELETE  /project-budgets/:id} : delete the "id" projectBudget.
     *
     * @param id the id of the projectBudget to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectBudget(@PathVariable("id") Long id) {
        log.debug("REST request to delete ProjectBudget : {}", id);
        projectBudgetService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
