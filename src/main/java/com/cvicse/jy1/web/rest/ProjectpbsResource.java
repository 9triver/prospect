package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.Projectpbs;
import com.cvicse.jy1.repository.ProjectpbsRepository;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.cvicse.jy1.domain.Projectpbs}.
 */
@RestController
@RequestMapping("/api/projectpbs")
@Transactional
public class ProjectpbsResource {

    private static final Logger log = LoggerFactory.getLogger(ProjectpbsResource.class);

    private static final String ENTITY_NAME = "projectpbs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectpbsRepository projectpbsRepository;

    public ProjectpbsResource(ProjectpbsRepository projectpbsRepository) {
        this.projectpbsRepository = projectpbsRepository;
    }

    /**
     * {@code POST  /projectpbs} : Create a new projectpbs.
     *
     * @param projectpbs the projectpbs to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectpbs, or with status {@code 400 (Bad Request)} if the projectpbs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Projectpbs> createProjectpbs(@RequestBody Projectpbs projectpbs) throws URISyntaxException {
        log.debug("REST request to save Projectpbs : {}", projectpbs);
        if (projectpbs.getId() != null) {
            throw new BadRequestAlertException("A new projectpbs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        projectpbs = projectpbsRepository.save(projectpbs);
        return ResponseEntity.created(new URI("/api/projectpbs/" + projectpbs.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, projectpbs.getId()))
            .body(projectpbs);
    }

    /**
     * {@code PUT  /projectpbs/:id} : Updates an existing projectpbs.
     *
     * @param id the id of the projectpbs to save.
     * @param projectpbs the projectpbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectpbs,
     * or with status {@code 400 (Bad Request)} if the projectpbs is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectpbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Projectpbs> updateProjectpbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Projectpbs projectpbs
    ) throws URISyntaxException {
        log.debug("REST request to update Projectpbs : {}, {}", id, projectpbs);
        if (projectpbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectpbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectpbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        projectpbs = projectpbsRepository.save(projectpbs);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectpbs.getId()))
            .body(projectpbs);
    }

    /**
     * {@code PATCH  /projectpbs/:id} : Partial updates given fields of an existing projectpbs, field will ignore if it is null
     *
     * @param id the id of the projectpbs to save.
     * @param projectpbs the projectpbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectpbs,
     * or with status {@code 400 (Bad Request)} if the projectpbs is not valid,
     * or with status {@code 404 (Not Found)} if the projectpbs is not found,
     * or with status {@code 500 (Internal Server Error)} if the projectpbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Projectpbs> partialUpdateProjectpbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Projectpbs projectpbs
    ) throws URISyntaxException {
        log.debug("REST request to partial update Projectpbs partially : {}, {}", id, projectpbs);
        if (projectpbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectpbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectpbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Projectpbs> result = projectpbsRepository
            .findById(projectpbs.getId())
            .map(existingProjectpbs -> {
                if (projectpbs.getPbsname() != null) {
                    existingProjectpbs.setPbsname(projectpbs.getPbsname());
                }
                if (projectpbs.getParentpbsid() != null) {
                    existingProjectpbs.setParentpbsid(projectpbs.getParentpbsid());
                }
                if (projectpbs.getDescription() != null) {
                    existingProjectpbs.setDescription(projectpbs.getDescription());
                }
                if (projectpbs.getStarttime() != null) {
                    existingProjectpbs.setStarttime(projectpbs.getStarttime());
                }
                if (projectpbs.getEndtime() != null) {
                    existingProjectpbs.setEndtime(projectpbs.getEndtime());
                }
                if (projectpbs.getProgress() != null) {
                    existingProjectpbs.setProgress(projectpbs.getProgress());
                }
                if (projectpbs.getType() != null) {
                    existingProjectpbs.setType(projectpbs.getType());
                }
                if (projectpbs.getPriorty() != null) {
                    existingProjectpbs.setPriorty(projectpbs.getPriorty());
                }
                if (projectpbs.getSecretlevel() != null) {
                    existingProjectpbs.setSecretlevel(projectpbs.getSecretlevel());
                }
                if (projectpbs.getStatus() != null) {
                    existingProjectpbs.setStatus(projectpbs.getStatus());
                }
                if (projectpbs.getAuditStatus() != null) {
                    existingProjectpbs.setAuditStatus(projectpbs.getAuditStatus());
                }
                if (projectpbs.getWbsid() != null) {
                    existingProjectpbs.setWbsid(projectpbs.getWbsid());
                }
                if (projectpbs.getWorkbag() != null) {
                    existingProjectpbs.setWorkbag(projectpbs.getWorkbag());
                }

                return existingProjectpbs;
            })
            .map(projectpbsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectpbs.getId())
        );
    }

    /**
     * {@code GET  /projectpbs} : get all the projectpbs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectpbs in body.
     */
    @GetMapping("")
    public List<Projectpbs> getAllProjectpbs() {
        log.debug("REST request to get all Projectpbs");
        return projectpbsRepository.findAll();
    }

    /**
     * {@code GET  /projectpbs/:id} : get the "id" projectpbs.
     *
     * @param id the id of the projectpbs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectpbs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Projectpbs> getProjectpbs(@PathVariable("id") String id) {
        log.debug("REST request to get Projectpbs : {}", id);
        Optional<Projectpbs> projectpbs = projectpbsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(projectpbs);
    }

    /**
     * {@code DELETE  /projectpbs/:id} : delete the "id" projectpbs.
     *
     * @param id the id of the projectpbs to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectpbs(@PathVariable("id") String id) {
        log.debug("REST request to delete Projectpbs : {}", id);
        projectpbsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
