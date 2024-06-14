package com.cvicse.web.rest;

import com.cvicse.domain.Projectcharge;
import com.cvicse.repository.ProjectchargeRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Projectcharge}.
 */
@RestController
@RequestMapping("/api/projectcharges")
@Transactional
public class ProjectchargeResource {

    private final Logger log = LoggerFactory.getLogger(ProjectchargeResource.class);

    private static final String ENTITY_NAME = "projectcharge";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectchargeRepository projectchargeRepository;

    public ProjectchargeResource(ProjectchargeRepository projectchargeRepository) {
        this.projectchargeRepository = projectchargeRepository;
    }

    /**
     * {@code POST  /projectcharges} : Create a new projectcharge.
     *
     * @param projectcharge the projectcharge to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectcharge, or with status {@code 400 (Bad Request)} if the projectcharge has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Projectcharge> createProjectcharge(@RequestBody Projectcharge projectcharge) throws URISyntaxException {
        log.debug("REST request to save Projectcharge : {}", projectcharge);
        if (projectcharge.getId() != null) {
            throw new BadRequestAlertException("A new projectcharge cannot already have an ID", ENTITY_NAME, "idexists");
        }
        projectcharge = projectchargeRepository.save(projectcharge);
        return ResponseEntity.created(new URI("/api/projectcharges/" + projectcharge.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, projectcharge.getId().toString()))
            .body(projectcharge);
    }

    /**
     * {@code PUT  /projectcharges/:id} : Updates an existing projectcharge.
     *
     * @param id the id of the projectcharge to save.
     * @param projectcharge the projectcharge to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectcharge,
     * or with status {@code 400 (Bad Request)} if the projectcharge is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectcharge couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Projectcharge> updateProjectcharge(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Projectcharge projectcharge
    ) throws URISyntaxException {
        log.debug("REST request to update Projectcharge : {}, {}", id, projectcharge);
        if (projectcharge.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectcharge.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectchargeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        projectcharge = projectchargeRepository.save(projectcharge);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectcharge.getId().toString()))
            .body(projectcharge);
    }

    /**
     * {@code PATCH  /projectcharges/:id} : Partial updates given fields of an existing projectcharge, field will ignore if it is null
     *
     * @param id the id of the projectcharge to save.
     * @param projectcharge the projectcharge to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectcharge,
     * or with status {@code 400 (Bad Request)} if the projectcharge is not valid,
     * or with status {@code 404 (Not Found)} if the projectcharge is not found,
     * or with status {@code 500 (Internal Server Error)} if the projectcharge couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Projectcharge> partialUpdateProjectcharge(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Projectcharge projectcharge
    ) throws URISyntaxException {
        log.debug("REST request to partial update Projectcharge partially : {}, {}", id, projectcharge);
        if (projectcharge.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectcharge.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectchargeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Projectcharge> result = projectchargeRepository
            .findById(projectcharge.getId())
            .map(existingProjectcharge -> {
                if (projectcharge.getProjectname() != null) {
                    existingProjectcharge.setProjectname(projectcharge.getProjectname());
                }
                if (projectcharge.getFormid() != null) {
                    existingProjectcharge.setFormid(projectcharge.getFormid());
                }
                if (projectcharge.getSecretlevel() != null) {
                    existingProjectcharge.setSecretlevel(projectcharge.getSecretlevel());
                }
                if (projectcharge.getRequestdeportment() != null) {
                    existingProjectcharge.setRequestdeportment(projectcharge.getRequestdeportment());
                }
                if (projectcharge.getChargetype() != null) {
                    existingProjectcharge.setChargetype(projectcharge.getChargetype());
                }
                if (projectcharge.getChargecontent() != null) {
                    existingProjectcharge.setChargecontent(projectcharge.getChargecontent());
                }

                return existingProjectcharge;
            })
            .map(projectchargeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectcharge.getId().toString())
        );
    }

    /**
     * {@code GET  /projectcharges} : get all the projectcharges.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectcharges in body.
     */
    @GetMapping("")
    public List<Projectcharge> getAllProjectcharges(@RequestParam(name = "filter", required = false) String filter) {
        if ("cycleplan-is-null".equals(filter)) {
            log.debug("REST request to get all Projectcharges where cycleplan is null");
            return StreamSupport.stream(projectchargeRepository.findAll().spliterator(), false)
                .filter(projectcharge -> projectcharge.getCycleplan() == null)
                .toList();
        }

        if ("annualplan-is-null".equals(filter)) {
            log.debug("REST request to get all Projectcharges where annualplan is null");
            return StreamSupport.stream(projectchargeRepository.findAll().spliterator(), false)
                .filter(projectcharge -> projectcharge.getAnnualplan() == null)
                .toList();
        }

        if ("monthplan-is-null".equals(filter)) {
            log.debug("REST request to get all Projectcharges where monthplan is null");
            return StreamSupport.stream(projectchargeRepository.findAll().spliterator(), false)
                .filter(projectcharge -> projectcharge.getMonthplan() == null)
                .toList();
        }

        if ("pbsbaseline-is-null".equals(filter)) {
            log.debug("REST request to get all Projectcharges where pbsbaseline is null");
            return StreamSupport.stream(projectchargeRepository.findAll().spliterator(), false)
                .filter(projectcharge -> projectcharge.getPbsbaseline() == null)
                .toList();
        }

        if ("wbsbaseline-is-null".equals(filter)) {
            log.debug("REST request to get all Projectcharges where wbsbaseline is null");
            return StreamSupport.stream(projectchargeRepository.findAll().spliterator(), false)
                .filter(projectcharge -> projectcharge.getWbsbaseline() == null)
                .toList();
        }
        log.debug("REST request to get all Projectcharges");
        return projectchargeRepository.findAll();
    }

    /**
     * {@code GET  /projectcharges/:id} : get the "id" projectcharge.
     *
     * @param id the id of the projectcharge to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectcharge, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Projectcharge> getProjectcharge(@PathVariable("id") Long id) {
        log.debug("REST request to get Projectcharge : {}", id);
        Optional<Projectcharge> projectcharge = projectchargeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(projectcharge);
    }

    /**
     * {@code DELETE  /projectcharges/:id} : delete the "id" projectcharge.
     *
     * @param id the id of the projectcharge to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectcharge(@PathVariable("id") Long id) {
        log.debug("REST request to delete Projectcharge : {}", id);
        projectchargeRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
