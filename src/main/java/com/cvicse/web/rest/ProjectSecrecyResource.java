package com.cvicse.web.rest;

import com.cvicse.domain.ProjectSecrecy;
import com.cvicse.repository.ProjectSecrecyRepository;
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
 * REST controller for managing {@link com.cvicse.domain.ProjectSecrecy}.
 */
@RestController
@RequestMapping("/api/project-secrecies")
@Transactional
public class ProjectSecrecyResource {

    private final Logger log = LoggerFactory.getLogger(ProjectSecrecyResource.class);

    private static final String ENTITY_NAME = "projectSecrecy";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectSecrecyRepository projectSecrecyRepository;

    public ProjectSecrecyResource(ProjectSecrecyRepository projectSecrecyRepository) {
        this.projectSecrecyRepository = projectSecrecyRepository;
    }

    /**
     * {@code POST  /project-secrecies} : Create a new projectSecrecy.
     *
     * @param projectSecrecy the projectSecrecy to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectSecrecy, or with status {@code 400 (Bad Request)} if the projectSecrecy has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ProjectSecrecy> createProjectSecrecy(@RequestBody ProjectSecrecy projectSecrecy) throws URISyntaxException {
        log.debug("REST request to save ProjectSecrecy : {}", projectSecrecy);
        if (projectSecrecy.getId() != null) {
            throw new BadRequestAlertException("A new projectSecrecy cannot already have an ID", ENTITY_NAME, "idexists");
        }
        projectSecrecy = projectSecrecyRepository.save(projectSecrecy);
        return ResponseEntity.created(new URI("/api/project-secrecies/" + projectSecrecy.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, projectSecrecy.getId().toString()))
            .body(projectSecrecy);
    }

    /**
     * {@code PUT  /project-secrecies/:id} : Updates an existing projectSecrecy.
     *
     * @param id the id of the projectSecrecy to save.
     * @param projectSecrecy the projectSecrecy to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectSecrecy,
     * or with status {@code 400 (Bad Request)} if the projectSecrecy is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectSecrecy couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProjectSecrecy> updateProjectSecrecy(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProjectSecrecy projectSecrecy
    ) throws URISyntaxException {
        log.debug("REST request to update ProjectSecrecy : {}, {}", id, projectSecrecy);
        if (projectSecrecy.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectSecrecy.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectSecrecyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        projectSecrecy = projectSecrecyRepository.save(projectSecrecy);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectSecrecy.getId().toString()))
            .body(projectSecrecy);
    }

    /**
     * {@code PATCH  /project-secrecies/:id} : Partial updates given fields of an existing projectSecrecy, field will ignore if it is null
     *
     * @param id the id of the projectSecrecy to save.
     * @param projectSecrecy the projectSecrecy to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectSecrecy,
     * or with status {@code 400 (Bad Request)} if the projectSecrecy is not valid,
     * or with status {@code 404 (Not Found)} if the projectSecrecy is not found,
     * or with status {@code 500 (Internal Server Error)} if the projectSecrecy couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProjectSecrecy> partialUpdateProjectSecrecy(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProjectSecrecy projectSecrecy
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProjectSecrecy partially : {}, {}", id, projectSecrecy);
        if (projectSecrecy.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectSecrecy.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectSecrecyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProjectSecrecy> result = projectSecrecyRepository
            .findById(projectSecrecy.getId())
            .map(existingProjectSecrecy -> {
                if (projectSecrecy.getProjectname() != null) {
                    existingProjectSecrecy.setProjectname(projectSecrecy.getProjectname());
                }
                if (projectSecrecy.getDescription() != null) {
                    existingProjectSecrecy.setDescription(projectSecrecy.getDescription());
                }
                if (projectSecrecy.getCreatetime() != null) {
                    existingProjectSecrecy.setCreatetime(projectSecrecy.getCreatetime());
                }
                if (projectSecrecy.getAuditStatus() != null) {
                    existingProjectSecrecy.setAuditStatus(projectSecrecy.getAuditStatus());
                }

                return existingProjectSecrecy;
            })
            .map(projectSecrecyRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectSecrecy.getId().toString())
        );
    }

    /**
     * {@code GET  /project-secrecies} : get all the projectSecrecies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectSecrecies in body.
     */
    @GetMapping("")
    public List<ProjectSecrecy> getAllProjectSecrecies() {
        log.debug("REST request to get all ProjectSecrecies");
        return projectSecrecyRepository.findAll();
    }

    /**
     * {@code GET  /project-secrecies/:id} : get the "id" projectSecrecy.
     *
     * @param id the id of the projectSecrecy to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectSecrecy, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProjectSecrecy> getProjectSecrecy(@PathVariable("id") Long id) {
        log.debug("REST request to get ProjectSecrecy : {}", id);
        Optional<ProjectSecrecy> projectSecrecy = projectSecrecyRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(projectSecrecy);
    }

    /**
     * {@code DELETE  /project-secrecies/:id} : delete the "id" projectSecrecy.
     *
     * @param id the id of the projectSecrecy to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectSecrecy(@PathVariable("id") Long id) {
        log.debug("REST request to delete ProjectSecrecy : {}", id);
        projectSecrecyRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
