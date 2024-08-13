package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.ProjectTotalwbs;
import com.cvicse.jy1.repository.ProjectTotalwbsRepository;
import com.cvicse.jy1.service.ProjectTotalwbsService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.ProjectTotalwbs}.
 */
@RestController
@RequestMapping("/api/projecttotalwbs")
public class ProjectTotalwbsResource {

    private static final Logger log = LoggerFactory.getLogger(ProjectTotalwbsResource.class);

    private static final String ENTITY_NAME = "projectTotalwbs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectTotalwbsService projectTotalwbsService;

    private final ProjectTotalwbsRepository projectTotalwbsRepository;

    public ProjectTotalwbsResource(ProjectTotalwbsService projectTotalwbsService, ProjectTotalwbsRepository projectTotalwbsRepository) {
        this.projectTotalwbsService = projectTotalwbsService;
        this.projectTotalwbsRepository = projectTotalwbsRepository;
    }

    /**
     * {@code POST  /projecttotalwbs} : Create a new projectTotalwbs.
     *
     * @param projectTotalwbs the projectTotalwbs to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectTotalwbs, or with status {@code 400 (Bad Request)} if the projectTotalwbs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ProjectTotalwbs> createProjectTotalwbs(@RequestBody ProjectTotalwbs projectTotalwbs) throws URISyntaxException {
        log.debug("REST request to save ProjectTotalwbs : {}", projectTotalwbs);
        if (projectTotalwbs.getId() != null) {
            throw new BadRequestAlertException("A new projectTotalwbs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        projectTotalwbs = projectTotalwbsService.save(projectTotalwbs);
        return ResponseEntity.created(new URI("/api/projecttotalwbs/" + projectTotalwbs.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, projectTotalwbs.getId()))
            .body(projectTotalwbs);
    }

    /**
     * {@code PUT  /projecttotalwbs/:id} : Updates an existing projectTotalwbs.
     *
     * @param id the id of the projectTotalwbs to save.
     * @param projectTotalwbs the projectTotalwbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectTotalwbs,
     * or with status {@code 400 (Bad Request)} if the projectTotalwbs is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectTotalwbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProjectTotalwbs> updateProjectTotalwbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ProjectTotalwbs projectTotalwbs
    ) throws URISyntaxException {
        log.debug("REST request to update ProjectTotalwbs : {}, {}", id, projectTotalwbs);
        if (projectTotalwbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectTotalwbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectTotalwbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        projectTotalwbs = projectTotalwbsService.update(projectTotalwbs);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectTotalwbs.getId()))
            .body(projectTotalwbs);
    }

    /**
     * {@code PATCH  /projecttotalwbs/:id} : Partial updates given fields of an existing projectTotalwbs, field will ignore if it is null
     *
     * @param id the id of the projectTotalwbs to save.
     * @param projectTotalwbs the projectTotalwbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectTotalwbs,
     * or with status {@code 400 (Bad Request)} if the projectTotalwbs is not valid,
     * or with status {@code 404 (Not Found)} if the projectTotalwbs is not found,
     * or with status {@code 500 (Internal Server Error)} if the projectTotalwbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProjectTotalwbs> partialUpdateProjectTotalwbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ProjectTotalwbs projectTotalwbs
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProjectTotalwbs partially : {}, {}", id, projectTotalwbs);
        if (projectTotalwbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectTotalwbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectTotalwbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProjectTotalwbs> result = projectTotalwbsService.partialUpdate(projectTotalwbs);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectTotalwbs.getId())
        );
    }

    /**
     * {@code GET  /projecttotalwbs} : get all the projectTotalwbs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectTotalwbs in body.
     */
    @GetMapping("")
    public List<ProjectTotalwbs> getAllProjectTotalwbs() {
        log.debug("REST request to get all ProjectTotalwbs");
        return projectTotalwbsService.findAll();
    }

    /**
     * {@code GET  /projecttotalwbs/:id} : get the "id" projectTotalwbs.
     *
     * @param id the id of the projectTotalwbs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectTotalwbs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProjectTotalwbs> getProjectTotalwbs(@PathVariable("id") String id) {
        log.debug("REST request to get ProjectTotalwbs : {}", id);
        Optional<ProjectTotalwbs> projectTotalwbs = projectTotalwbsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projectTotalwbs);
    }

    /**
     * {@code DELETE  /projecttotalwbs/:id} : delete the "id" projectTotalwbs.
     *
     * @param id the id of the projectTotalwbs to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectTotalwbs(@PathVariable("id") String id) {
        log.debug("REST request to delete ProjectTotalwbs : {}", id);
        projectTotalwbsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
