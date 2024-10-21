package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.Workbag;
import com.cvicse.jy1.repository.WorkbagRepository;
import com.cvicse.jy1.service.WorkbagService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.Workbag}.
 */
@RestController
@RequestMapping("/api/workbags")
public class WorkbagResource {

    private static final Logger log = LoggerFactory.getLogger(WorkbagResource.class);

    private static final String ENTITY_NAME = "workbag";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkbagService workbagService;

    private final WorkbagRepository workbagRepository;

    public WorkbagResource(WorkbagService workbagService, WorkbagRepository workbagRepository) {
        this.workbagService = workbagService;
        this.workbagRepository = workbagRepository;
    }

    /**
     * {@code POST  /workbags} : Create a new workbag.
     *
     * @param workbag the workbag to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workbag, or with status {@code 400 (Bad Request)} if the workbag has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Workbag> createWorkbag(@RequestBody Workbag workbag) throws URISyntaxException {
        log.debug("REST request to save Workbag : {}", workbag);
        if (workbag.getId() != null) {
            throw new BadRequestAlertException("A new workbag cannot already have an ID", ENTITY_NAME, "idexists");
        }
        workbag = workbagService.save(workbag);
        return ResponseEntity.created(new URI("/api/workbags/" + workbag.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, workbag.getId()))
            .body(workbag);
    }

    /**
     * {@code PUT  /workbags/:id} : Updates an existing workbag.
     *
     * @param id the id of the workbag to save.
     * @param workbag the workbag to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workbag,
     * or with status {@code 400 (Bad Request)} if the workbag is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workbag couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Workbag> updateWorkbag(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Workbag workbag
    ) throws URISyntaxException {
        log.debug("REST request to update Workbag : {}, {}", id, workbag);
        if (workbag.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workbag.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workbagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        workbag = workbagService.update(workbag);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, workbag.getId()))
            .body(workbag);
    }

    /**
     * {@code PATCH  /workbags/:id} : Partial updates given fields of an existing workbag, field will ignore if it is null
     *
     * @param id the id of the workbag to save.
     * @param workbag the workbag to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workbag,
     * or with status {@code 400 (Bad Request)} if the workbag is not valid,
     * or with status {@code 404 (Not Found)} if the workbag is not found,
     * or with status {@code 500 (Internal Server Error)} if the workbag couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Workbag> partialUpdateWorkbag(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Workbag workbag
    ) throws URISyntaxException {
        log.debug("REST request to partial update Workbag partially : {}, {}", id, workbag);
        if (workbag.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workbag.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workbagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Workbag> result = workbagService.partialUpdate(workbag);

        return ResponseUtil.wrapOrNotFound(result, HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, workbag.getId()));
    }

    /**
     * {@code GET  /workbags} : get all the workbags.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workbags in body.
     */
    @GetMapping("")
    public List<Workbag> getAllWorkbags(
        @RequestParam(name = "filter", required = false) String filter,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        if ("outsourcingcontract-is-null".equals(filter)) {
            log.debug("REST request to get all Workbags where outsourcingContract is null");
            return workbagService.findAllWhereOutsourcingContractIsNull();
        }
        log.debug("REST request to get all Workbags");
        return workbagService.findAllWithEagerRelationships();
    }

    /**
     * {@code GET  /workbags/:id} : get the "id" workbag.
     *
     * @param id the id of the workbag to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workbag, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Workbag> getWorkbag(@PathVariable("id") String id) {
        log.debug("REST request to get Workbag : {}", id);
        Optional<Workbag> workbag = workbagService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workbag);
    }

    /**
     * {@code DELETE  /workbags/:id} : delete the "id" workbag.
     *
     * @param id the id of the workbag to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkbag(@PathVariable("id") String id) {
        log.debug("REST request to delete Workbag : {}", id);
        workbagService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
