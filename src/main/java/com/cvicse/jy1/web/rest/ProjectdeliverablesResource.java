package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.Projectdeliverables;
import com.cvicse.jy1.repository.ProjectdeliverablesRepository;
import com.cvicse.jy1.service.ProjectdeliverablesService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.Projectdeliverables}.
 */
@RestController
@RequestMapping("/api/projectdeliverables")
public class ProjectdeliverablesResource {

    private static final Logger log = LoggerFactory.getLogger(ProjectdeliverablesResource.class);

    private static final String ENTITY_NAME = "projectdeliverables";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectdeliverablesService projectdeliverablesService;

    private final ProjectdeliverablesRepository projectdeliverablesRepository;

    public ProjectdeliverablesResource(
        ProjectdeliverablesService projectdeliverablesService,
        ProjectdeliverablesRepository projectdeliverablesRepository
    ) {
        this.projectdeliverablesService = projectdeliverablesService;
        this.projectdeliverablesRepository = projectdeliverablesRepository;
    }

    /**
     * {@code POST  /projectdeliverables} : Create a new projectdeliverables.
     *
     * @param projectdeliverables the projectdeliverables to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectdeliverables, or with status {@code 400 (Bad Request)} if the projectdeliverables has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Projectdeliverables> createProjectdeliverables(@RequestBody Projectdeliverables projectdeliverables)
        throws URISyntaxException {
        log.debug("REST request to save Projectdeliverables : {}", projectdeliverables);
        if (projectdeliverables.getId() != null) {
            throw new BadRequestAlertException("A new projectdeliverables cannot already have an ID", ENTITY_NAME, "idexists");
        }
        projectdeliverables = projectdeliverablesService.save(projectdeliverables);
        return ResponseEntity.created(new URI("/api/projectdeliverables/" + projectdeliverables.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, projectdeliverables.getId().toString()))
            .body(projectdeliverables);
    }

    /**
     * {@code PUT  /projectdeliverables/:id} : Updates an existing projectdeliverables.
     *
     * @param id the id of the projectdeliverables to save.
     * @param projectdeliverables the projectdeliverables to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectdeliverables,
     * or with status {@code 400 (Bad Request)} if the projectdeliverables is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectdeliverables couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Projectdeliverables> updateProjectdeliverables(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody Projectdeliverables projectdeliverables
    ) throws URISyntaxException {
        log.debug("REST request to update Projectdeliverables : {}, {}", id, projectdeliverables);
        if (projectdeliverables.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectdeliverables.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectdeliverablesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        projectdeliverables = projectdeliverablesService.update(projectdeliverables);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectdeliverables.getId().toString()))
            .body(projectdeliverables);
    }

    /**
     * {@code PATCH  /projectdeliverables/:id} : Partial updates given fields of an existing projectdeliverables, field will ignore if it is null
     *
     * @param id the id of the projectdeliverables to save.
     * @param projectdeliverables the projectdeliverables to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectdeliverables,
     * or with status {@code 400 (Bad Request)} if the projectdeliverables is not valid,
     * or with status {@code 404 (Not Found)} if the projectdeliverables is not found,
     * or with status {@code 500 (Internal Server Error)} if the projectdeliverables couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Projectdeliverables> partialUpdateProjectdeliverables(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody Projectdeliverables projectdeliverables
    ) throws URISyntaxException {
        log.debug("REST request to partial update Projectdeliverables partially : {}, {}", id, projectdeliverables);
        if (projectdeliverables.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectdeliverables.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectdeliverablesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Projectdeliverables> result = projectdeliverablesService.partialUpdate(projectdeliverables);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectdeliverables.getId().toString())
        );
    }

    /**
     * {@code GET  /projectdeliverables} : get all the projectdeliverables.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectdeliverables in body.
     */
    @GetMapping("")
    public List<Projectdeliverables> getAllProjectdeliverables() {
        log.debug("REST request to get all Projectdeliverables");
        return projectdeliverablesService.findAll();
    }

    /**
     * {@code GET  /projectdeliverables/:id} : get the "id" projectdeliverables.
     *
     * @param id the id of the projectdeliverables to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectdeliverables, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Projectdeliverables> getProjectdeliverables(@PathVariable("id") Integer id) {
        log.debug("REST request to get Projectdeliverables : {}", id);
        Optional<Projectdeliverables> projectdeliverables = projectdeliverablesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projectdeliverables);
    }

    /**
     * {@code DELETE  /projectdeliverables/:id} : delete the "id" projectdeliverables.
     *
     * @param id the id of the projectdeliverables to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectdeliverables(@PathVariable("id") Integer id) {
        log.debug("REST request to delete Projectdeliverables : {}", id);
        projectdeliverablesService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
