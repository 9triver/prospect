package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.Deliverables;
import com.cvicse.jy1.repository.DeliverablesRepository;
import com.cvicse.jy1.service.DeliverablesService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.Deliverables}.
 */
@RestController
@RequestMapping("/api/deliverables")
public class DeliverablesResource {

    private static final Logger log = LoggerFactory.getLogger(DeliverablesResource.class);

    private static final String ENTITY_NAME = "deliverables";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeliverablesService deliverablesService;

    private final DeliverablesRepository deliverablesRepository;

    public DeliverablesResource(DeliverablesService deliverablesService, DeliverablesRepository deliverablesRepository) {
        this.deliverablesService = deliverablesService;
        this.deliverablesRepository = deliverablesRepository;
    }

    /**
     * {@code POST  /deliverables} : Create a new deliverables.
     *
     * @param deliverables the deliverables to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deliverables, or with status {@code 400 (Bad Request)} if the deliverables has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Deliverables> createDeliverables(@RequestBody Deliverables deliverables) throws URISyntaxException {
        log.debug("REST request to save Deliverables : {}", deliverables);
        if (deliverables.getId() != null) {
            throw new BadRequestAlertException("A new deliverables cannot already have an ID", ENTITY_NAME, "idexists");
        }
        deliverables = deliverablesService.save(deliverables);
        return ResponseEntity.created(new URI("/api/deliverables/" + deliverables.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, deliverables.getId().toString()))
            .body(deliverables);
    }

    /**
     * {@code PUT  /deliverables/:id} : Updates an existing deliverables.
     *
     * @param id the id of the deliverables to save.
     * @param deliverables the deliverables to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliverables,
     * or with status {@code 400 (Bad Request)} if the deliverables is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deliverables couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Deliverables> updateDeliverables(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Deliverables deliverables
    ) throws URISyntaxException {
        log.debug("REST request to update Deliverables : {}, {}", id, deliverables);
        if (deliverables.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, deliverables.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deliverablesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        deliverables = deliverablesService.update(deliverables);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deliverables.getId().toString()))
            .body(deliverables);
    }

    /**
     * {@code PATCH  /deliverables/:id} : Partial updates given fields of an existing deliverables, field will ignore if it is null
     *
     * @param id the id of the deliverables to save.
     * @param deliverables the deliverables to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliverables,
     * or with status {@code 400 (Bad Request)} if the deliverables is not valid,
     * or with status {@code 404 (Not Found)} if the deliverables is not found,
     * or with status {@code 500 (Internal Server Error)} if the deliverables couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Deliverables> partialUpdateDeliverables(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Deliverables deliverables
    ) throws URISyntaxException {
        log.debug("REST request to partial update Deliverables partially : {}, {}", id, deliverables);
        if (deliverables.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, deliverables.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deliverablesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Deliverables> result = deliverablesService.partialUpdate(deliverables);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deliverables.getId().toString())
        );
    }

    /**
     * {@code GET  /deliverables} : get all the deliverables.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deliverables in body.
     */
    @GetMapping("")
    public List<Deliverables> getAllDeliverables(@RequestParam(name = "filter", required = false) String filter) {
        if ("projectdeliverables-is-null".equals(filter)) {
            log.debug("REST request to get all Deliverabless where projectdeliverables is null");
            return deliverablesService.findAllWhereProjectdeliverablesIsNull();
        }
        log.debug("REST request to get all Deliverables");
        return deliverablesService.findAll();
    }

    /**
     * {@code GET  /deliverables/:id} : get the "id" deliverables.
     *
     * @param id the id of the deliverables to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deliverables, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Deliverables> getDeliverables(@PathVariable("id") Long id) {
        log.debug("REST request to get Deliverables : {}", id);
        Optional<Deliverables> deliverables = deliverablesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deliverables);
    }

    /**
     * {@code DELETE  /deliverables/:id} : delete the "id" deliverables.
     *
     * @param id the id of the deliverables to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeliverables(@PathVariable("id") Long id) {
        log.debug("REST request to delete Deliverables : {}", id);
        deliverablesService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
