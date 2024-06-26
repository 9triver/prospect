package com.cvicse.web.rest;

import com.cvicse.domain.ProjectHumanresourcesplan;
import com.cvicse.repository.ProjectHumanresourcesplanRepository;
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
 * REST controller for managing {@link com.cvicse.domain.ProjectHumanresourcesplan}.
 */
@RestController
@RequestMapping("/api/project-humanresourcesplans")
@Transactional
public class ProjectHumanresourcesplanResource {

    private final Logger log = LoggerFactory.getLogger(ProjectHumanresourcesplanResource.class);

    private static final String ENTITY_NAME = "projectHumanresourcesplan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectHumanresourcesplanRepository projectHumanresourcesplanRepository;

    public ProjectHumanresourcesplanResource(ProjectHumanresourcesplanRepository projectHumanresourcesplanRepository) {
        this.projectHumanresourcesplanRepository = projectHumanresourcesplanRepository;
    }

    /**
     * {@code POST  /project-humanresourcesplans} : Create a new projectHumanresourcesplan.
     *
     * @param projectHumanresourcesplan the projectHumanresourcesplan to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectHumanresourcesplan, or with status {@code 400 (Bad Request)} if the projectHumanresourcesplan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ProjectHumanresourcesplan> createProjectHumanresourcesplan(
        @RequestBody ProjectHumanresourcesplan projectHumanresourcesplan
    ) throws URISyntaxException {
        log.debug("REST request to save ProjectHumanresourcesplan : {}", projectHumanresourcesplan);
        if (projectHumanresourcesplan.getId() != null) {
            throw new BadRequestAlertException("A new projectHumanresourcesplan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        projectHumanresourcesplan = projectHumanresourcesplanRepository.save(projectHumanresourcesplan);
        return ResponseEntity.created(new URI("/api/project-humanresourcesplans/" + projectHumanresourcesplan.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, projectHumanresourcesplan.getId()))
            .body(projectHumanresourcesplan);
    }

    /**
     * {@code PUT  /project-humanresourcesplans/:id} : Updates an existing projectHumanresourcesplan.
     *
     * @param id the id of the projectHumanresourcesplan to save.
     * @param projectHumanresourcesplan the projectHumanresourcesplan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectHumanresourcesplan,
     * or with status {@code 400 (Bad Request)} if the projectHumanresourcesplan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectHumanresourcesplan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProjectHumanresourcesplan> updateProjectHumanresourcesplan(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ProjectHumanresourcesplan projectHumanresourcesplan
    ) throws URISyntaxException {
        log.debug("REST request to update ProjectHumanresourcesplan : {}, {}", id, projectHumanresourcesplan);
        if (projectHumanresourcesplan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectHumanresourcesplan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectHumanresourcesplanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        projectHumanresourcesplan = projectHumanresourcesplanRepository.save(projectHumanresourcesplan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectHumanresourcesplan.getId()))
            .body(projectHumanresourcesplan);
    }

    /**
     * {@code PATCH  /project-humanresourcesplans/:id} : Partial updates given fields of an existing projectHumanresourcesplan, field will ignore if it is null
     *
     * @param id the id of the projectHumanresourcesplan to save.
     * @param projectHumanresourcesplan the projectHumanresourcesplan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectHumanresourcesplan,
     * or with status {@code 400 (Bad Request)} if the projectHumanresourcesplan is not valid,
     * or with status {@code 404 (Not Found)} if the projectHumanresourcesplan is not found,
     * or with status {@code 500 (Internal Server Error)} if the projectHumanresourcesplan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProjectHumanresourcesplan> partialUpdateProjectHumanresourcesplan(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ProjectHumanresourcesplan projectHumanresourcesplan
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProjectHumanresourcesplan partially : {}, {}", id, projectHumanresourcesplan);
        if (projectHumanresourcesplan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectHumanresourcesplan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectHumanresourcesplanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProjectHumanresourcesplan> result = projectHumanresourcesplanRepository
            .findById(projectHumanresourcesplan.getId())
            .map(existingProjectHumanresourcesplan -> {
                if (projectHumanresourcesplan.getProjectname() != null) {
                    existingProjectHumanresourcesplan.setProjectname(projectHumanresourcesplan.getProjectname());
                }
                if (projectHumanresourcesplan.getClientname() != null) {
                    existingProjectHumanresourcesplan.setClientname(projectHumanresourcesplan.getClientname());
                }
                if (projectHumanresourcesplan.getPlandate() != null) {
                    existingProjectHumanresourcesplan.setPlandate(projectHumanresourcesplan.getPlandate());
                }
                if (projectHumanresourcesplan.getSecretlevel() != null) {
                    existingProjectHumanresourcesplan.setSecretlevel(projectHumanresourcesplan.getSecretlevel());
                }
                if (projectHumanresourcesplan.getAuditStatus() != null) {
                    existingProjectHumanresourcesplan.setAuditStatus(projectHumanresourcesplan.getAuditStatus());
                }

                return existingProjectHumanresourcesplan;
            })
            .map(projectHumanresourcesplanRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectHumanresourcesplan.getId())
        );
    }

    /**
     * {@code GET  /project-humanresourcesplans} : get all the projectHumanresourcesplans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectHumanresourcesplans in body.
     */
    @GetMapping("")
    public List<ProjectHumanresourcesplan> getAllProjectHumanresourcesplans() {
        log.debug("REST request to get all ProjectHumanresourcesplans");
        return projectHumanresourcesplanRepository.findAll();
    }

    /**
     * {@code GET  /project-humanresourcesplans/:id} : get the "id" projectHumanresourcesplan.
     *
     * @param id the id of the projectHumanresourcesplan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectHumanresourcesplan, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProjectHumanresourcesplan> getProjectHumanresourcesplan(@PathVariable("id") String id) {
        log.debug("REST request to get ProjectHumanresourcesplan : {}", id);
        Optional<ProjectHumanresourcesplan> projectHumanresourcesplan = projectHumanresourcesplanRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(projectHumanresourcesplan);
    }

    /**
     * {@code DELETE  /project-humanresourcesplans/:id} : delete the "id" projectHumanresourcesplan.
     *
     * @param id the id of the projectHumanresourcesplan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectHumanresourcesplan(@PathVariable("id") String id) {
        log.debug("REST request to delete ProjectHumanresourcesplan : {}", id);
        projectHumanresourcesplanRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
