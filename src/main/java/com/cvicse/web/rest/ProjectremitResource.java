package com.cvicse.web.rest;

import com.cvicse.domain.Projectremit;
import com.cvicse.repository.ProjectremitRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Projectremit}.
 */
@RestController
@RequestMapping("/api/projectremits")
@Transactional
public class ProjectremitResource {

    private final Logger log = LoggerFactory.getLogger(ProjectremitResource.class);

    private static final String ENTITY_NAME = "projectremit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectremitRepository projectremitRepository;

    public ProjectremitResource(ProjectremitRepository projectremitRepository) {
        this.projectremitRepository = projectremitRepository;
    }

    /**
     * {@code POST  /projectremits} : Create a new projectremit.
     *
     * @param projectremit the projectremit to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectremit, or with status {@code 400 (Bad Request)} if the projectremit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Projectremit> createProjectremit(@RequestBody Projectremit projectremit) throws URISyntaxException {
        log.debug("REST request to save Projectremit : {}", projectremit);
        if (projectremit.getId() != null) {
            throw new BadRequestAlertException("A new projectremit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        projectremit = projectremitRepository.save(projectremit);
        return ResponseEntity.created(new URI("/api/projectremits/" + projectremit.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, projectremit.getId()))
            .body(projectremit);
    }

    /**
     * {@code PUT  /projectremits/:id} : Updates an existing projectremit.
     *
     * @param id the id of the projectremit to save.
     * @param projectremit the projectremit to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectremit,
     * or with status {@code 400 (Bad Request)} if the projectremit is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectremit couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Projectremit> updateProjectremit(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Projectremit projectremit
    ) throws URISyntaxException {
        log.debug("REST request to update Projectremit : {}, {}", id, projectremit);
        if (projectremit.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectremit.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectremitRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        projectremit = projectremitRepository.save(projectremit);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectremit.getId()))
            .body(projectremit);
    }

    /**
     * {@code PATCH  /projectremits/:id} : Partial updates given fields of an existing projectremit, field will ignore if it is null
     *
     * @param id the id of the projectremit to save.
     * @param projectremit the projectremit to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectremit,
     * or with status {@code 400 (Bad Request)} if the projectremit is not valid,
     * or with status {@code 404 (Not Found)} if the projectremit is not found,
     * or with status {@code 500 (Internal Server Error)} if the projectremit couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Projectremit> partialUpdateProjectremit(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Projectremit projectremit
    ) throws URISyntaxException {
        log.debug("REST request to partial update Projectremit partially : {}, {}", id, projectremit);
        if (projectremit.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectremit.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectremitRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Projectremit> result = projectremitRepository
            .findById(projectremit.getId())
            .map(existingProjectremit -> {
                if (projectremit.getRemit() != null) {
                    existingProjectremit.setRemit(projectremit.getRemit());
                }
                if (projectremit.getOutdeportment() != null) {
                    existingProjectremit.setOutdeportment(projectremit.getOutdeportment());
                }
                if (projectremit.getIndeportment() != null) {
                    existingProjectremit.setIndeportment(projectremit.getIndeportment());
                }
                if (projectremit.getProjectname() != null) {
                    existingProjectremit.setProjectname(projectremit.getProjectname());
                }
                if (projectremit.getDeportment() != null) {
                    existingProjectremit.setDeportment(projectremit.getDeportment());
                }
                if (projectremit.getProjectleader() != null) {
                    existingProjectremit.setProjectleader(projectremit.getProjectleader());
                }
                if (projectremit.getSecretlevel() != null) {
                    existingProjectremit.setSecretlevel(projectremit.getSecretlevel());
                }
                if (projectremit.getAuditStatus() != null) {
                    existingProjectremit.setAuditStatus(projectremit.getAuditStatus());
                }

                return existingProjectremit;
            })
            .map(projectremitRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectremit.getId())
        );
    }

    /**
     * {@code GET  /projectremits} : get all the projectremits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectremits in body.
     */
    @GetMapping("")
    public List<Projectremit> getAllProjectremits() {
        log.debug("REST request to get all Projectremits");
        return projectremitRepository.findAll();
    }

    /**
     * {@code GET  /projectremits/:id} : get the "id" projectremit.
     *
     * @param id the id of the projectremit to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectremit, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Projectremit> getProjectremit(@PathVariable("id") String id) {
        log.debug("REST request to get Projectremit : {}", id);
        Optional<Projectremit> projectremit = projectremitRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(projectremit);
    }

    /**
     * {@code DELETE  /projectremits/:id} : delete the "id" projectremit.
     *
     * @param id the id of the projectremit to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectremit(@PathVariable("id") String id) {
        log.debug("REST request to delete Projectremit : {}", id);
        projectremitRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
