package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.ProjectRisk;
import com.cvicse.jy1.repository.ProjectRiskRepository;
import com.cvicse.jy1.service.ProjectRiskService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.ProjectRisk}.
 */
@RestController
@RequestMapping("/api/project-risks")
public class ProjectRiskResource {

    private static final Logger log = LoggerFactory.getLogger(ProjectRiskResource.class);

    private static final String ENTITY_NAME = "projectRisk";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectRiskService projectRiskService;

    private final ProjectRiskRepository projectRiskRepository;

    public ProjectRiskResource(ProjectRiskService projectRiskService, ProjectRiskRepository projectRiskRepository) {
        this.projectRiskService = projectRiskService;
        this.projectRiskRepository = projectRiskRepository;
    }

    /**
     * {@code POST  /project-risks} : Create a new projectRisk.
     *
     * @param projectRisk the projectRisk to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectRisk, or with status {@code 400 (Bad Request)} if the projectRisk has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ProjectRisk> createProjectRisk(@RequestBody ProjectRisk projectRisk) throws URISyntaxException {
        log.debug("REST request to save ProjectRisk : {}", projectRisk);
        if (projectRisk.getId() != null) {
            throw new BadRequestAlertException("A new projectRisk cannot already have an ID", ENTITY_NAME, "idexists");
        }
        projectRisk = projectRiskService.save(projectRisk);
        return ResponseEntity.created(new URI("/api/project-risks/" + projectRisk.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, projectRisk.getId().toString()))
            .body(projectRisk);
    }

    /**
     * {@code PUT  /project-risks/:id} : Updates an existing projectRisk.
     *
     * @param id the id of the projectRisk to save.
     * @param projectRisk the projectRisk to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectRisk,
     * or with status {@code 400 (Bad Request)} if the projectRisk is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectRisk couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProjectRisk> updateProjectRisk(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody ProjectRisk projectRisk
    ) throws URISyntaxException {
        log.debug("REST request to update ProjectRisk : {}, {}", id, projectRisk);
        if (projectRisk.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectRisk.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectRiskRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        projectRisk = projectRiskService.update(projectRisk);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectRisk.getId().toString()))
            .body(projectRisk);
    }

    /**
     * {@code PATCH  /project-risks/:id} : Partial updates given fields of an existing projectRisk, field will ignore if it is null
     *
     * @param id the id of the projectRisk to save.
     * @param projectRisk the projectRisk to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectRisk,
     * or with status {@code 400 (Bad Request)} if the projectRisk is not valid,
     * or with status {@code 404 (Not Found)} if the projectRisk is not found,
     * or with status {@code 500 (Internal Server Error)} if the projectRisk couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProjectRisk> partialUpdateProjectRisk(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody ProjectRisk projectRisk
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProjectRisk partially : {}, {}", id, projectRisk);
        if (projectRisk.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectRisk.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectRiskRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProjectRisk> result = projectRiskService.partialUpdate(projectRisk);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectRisk.getId().toString())
        );
    }

    /**
     * {@code GET  /project-risks} : get all the projectRisks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectRisks in body.
     */
    @GetMapping("")
    public List<ProjectRisk> getAllProjectRisks() {
        log.debug("REST request to get all ProjectRisks");
        return projectRiskService.findAll();
    }

    /**
     * {@code GET  /project-risks/:id} : get the "id" projectRisk.
     *
     * @param id the id of the projectRisk to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectRisk, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProjectRisk> getProjectRisk(@PathVariable("id") Integer id) {
        log.debug("REST request to get ProjectRisk : {}", id);
        Optional<ProjectRisk> projectRisk = projectRiskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projectRisk);
    }

    /**
     * {@code DELETE  /project-risks/:id} : delete the "id" projectRisk.
     *
     * @param id the id of the projectRisk to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectRisk(@PathVariable("id") Integer id) {
        log.debug("REST request to delete ProjectRisk : {}", id);
        projectRiskService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
