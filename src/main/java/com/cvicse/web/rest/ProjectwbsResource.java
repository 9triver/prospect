package com.cvicse.web.rest;

import com.cvicse.domain.Projectwbs;
import com.cvicse.repository.ProjectwbsRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Projectwbs}.
 */
@RestController
@RequestMapping("/api/projectwbs")
@Transactional
public class ProjectwbsResource {

    private final Logger log = LoggerFactory.getLogger(ProjectwbsResource.class);

    private static final String ENTITY_NAME = "projectwbs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectwbsRepository projectwbsRepository;

    public ProjectwbsResource(ProjectwbsRepository projectwbsRepository) {
        this.projectwbsRepository = projectwbsRepository;
    }

    /**
     * {@code POST  /projectwbs} : Create a new projectwbs.
     *
     * @param projectwbs the projectwbs to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectwbs, or with status {@code 400 (Bad Request)} if the projectwbs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Projectwbs> createProjectwbs(@RequestBody Projectwbs projectwbs) throws URISyntaxException {
        log.debug("REST request to save Projectwbs : {}", projectwbs);
        if (projectwbs.getId() != null) {
            throw new BadRequestAlertException("A new projectwbs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        projectwbs = projectwbsRepository.save(projectwbs);
        return ResponseEntity.created(new URI("/api/projectwbs/" + projectwbs.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, projectwbs.getId()))
            .body(projectwbs);
    }

    /**
     * {@code PUT  /projectwbs/:id} : Updates an existing projectwbs.
     *
     * @param id the id of the projectwbs to save.
     * @param projectwbs the projectwbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectwbs,
     * or with status {@code 400 (Bad Request)} if the projectwbs is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectwbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Projectwbs> updateProjectwbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Projectwbs projectwbs
    ) throws URISyntaxException {
        log.debug("REST request to update Projectwbs : {}, {}", id, projectwbs);
        if (projectwbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectwbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectwbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        projectwbs = projectwbsRepository.save(projectwbs);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectwbs.getId()))
            .body(projectwbs);
    }

    /**
     * {@code PATCH  /projectwbs/:id} : Partial updates given fields of an existing projectwbs, field will ignore if it is null
     *
     * @param id the id of the projectwbs to save.
     * @param projectwbs the projectwbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectwbs,
     * or with status {@code 400 (Bad Request)} if the projectwbs is not valid,
     * or with status {@code 404 (Not Found)} if the projectwbs is not found,
     * or with status {@code 500 (Internal Server Error)} if the projectwbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Projectwbs> partialUpdateProjectwbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Projectwbs projectwbs
    ) throws URISyntaxException {
        log.debug("REST request to partial update Projectwbs partially : {}, {}", id, projectwbs);
        if (projectwbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectwbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectwbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Projectwbs> result = projectwbsRepository
            .findById(projectwbs.getId())
            .map(existingProjectwbs -> {
                if (projectwbs.getProjectwbsname() != null) {
                    existingProjectwbs.setProjectwbsname(projectwbs.getProjectwbsname());
                }
                if (projectwbs.getWbsspare1() != null) {
                    existingProjectwbs.setWbsspare1(projectwbs.getWbsspare1());
                }
                if (projectwbs.getWbsspare2() != null) {
                    existingProjectwbs.setWbsspare2(projectwbs.getWbsspare2());
                }
                if (projectwbs.getWbsspare3() != null) {
                    existingProjectwbs.setWbsspare3(projectwbs.getWbsspare3());
                }
                if (projectwbs.getWbsspare4() != null) {
                    existingProjectwbs.setWbsspare4(projectwbs.getWbsspare4());
                }
                if (projectwbs.getWbsspare5() != null) {
                    existingProjectwbs.setWbsspare5(projectwbs.getWbsspare5());
                }

                return existingProjectwbs;
            })
            .map(projectwbsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectwbs.getId())
        );
    }

    /**
     * {@code GET  /projectwbs} : get all the projectwbs.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectwbs in body.
     */
    @GetMapping("")
    public List<Projectwbs> getAllProjectwbs(@RequestParam(name = "filter", required = false) String filter) {
        if ("project-is-null".equals(filter)) {
            log.debug("REST request to get all Projectwbss where project is null");
            return StreamSupport.stream(projectwbsRepository.findAll().spliterator(), false)
                .filter(projectwbs -> projectwbs.getProject() == null)
                .toList();
        }
        log.debug("REST request to get all Projectwbs");
        return projectwbsRepository.findAll();
    }

    /**
     * {@code GET  /projectwbs/:id} : get the "id" projectwbs.
     *
     * @param id the id of the projectwbs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectwbs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Projectwbs> getProjectwbs(@PathVariable("id") String id) {
        log.debug("REST request to get Projectwbs : {}", id);
        Optional<Projectwbs> projectwbs = projectwbsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(projectwbs);
    }

    /**
     * {@code DELETE  /projectwbs/:id} : delete the "id" projectwbs.
     *
     * @param id the id of the projectwbs to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectwbs(@PathVariable("id") String id) {
        log.debug("REST request to delete Projectwbs : {}", id);
        projectwbsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
