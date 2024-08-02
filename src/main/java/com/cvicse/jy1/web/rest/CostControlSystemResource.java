package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.CostControlSystem;
import com.cvicse.jy1.repository.CostControlSystemRepository;
import com.cvicse.jy1.service.CostControlSystemService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.CostControlSystem}.
 */
@RestController
@RequestMapping("/api/cost-control-systems")
public class CostControlSystemResource {

    private static final Logger log = LoggerFactory.getLogger(CostControlSystemResource.class);

    private static final String ENTITY_NAME = "costControlSystem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CostControlSystemService costControlSystemService;

    private final CostControlSystemRepository costControlSystemRepository;

    public CostControlSystemResource(
        CostControlSystemService costControlSystemService,
        CostControlSystemRepository costControlSystemRepository
    ) {
        this.costControlSystemService = costControlSystemService;
        this.costControlSystemRepository = costControlSystemRepository;
    }

    /**
     * {@code POST  /cost-control-systems} : Create a new costControlSystem.
     *
     * @param costControlSystem the costControlSystem to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new costControlSystem, or with status {@code 400 (Bad Request)} if the costControlSystem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CostControlSystem> createCostControlSystem(@RequestBody CostControlSystem costControlSystem)
        throws URISyntaxException {
        log.debug("REST request to save CostControlSystem : {}", costControlSystem);
        if (costControlSystem.getId() != null) {
            throw new BadRequestAlertException("A new costControlSystem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        costControlSystem = costControlSystemService.save(costControlSystem);
        return ResponseEntity.created(new URI("/api/cost-control-systems/" + costControlSystem.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, costControlSystem.getId()))
            .body(costControlSystem);
    }

    /**
     * {@code PUT  /cost-control-systems/:id} : Updates an existing costControlSystem.
     *
     * @param id the id of the costControlSystem to save.
     * @param costControlSystem the costControlSystem to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated costControlSystem,
     * or with status {@code 400 (Bad Request)} if the costControlSystem is not valid,
     * or with status {@code 500 (Internal Server Error)} if the costControlSystem couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CostControlSystem> updateCostControlSystem(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody CostControlSystem costControlSystem
    ) throws URISyntaxException {
        log.debug("REST request to update CostControlSystem : {}, {}", id, costControlSystem);
        if (costControlSystem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, costControlSystem.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!costControlSystemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        costControlSystem = costControlSystemService.update(costControlSystem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, costControlSystem.getId()))
            .body(costControlSystem);
    }

    /**
     * {@code PATCH  /cost-control-systems/:id} : Partial updates given fields of an existing costControlSystem, field will ignore if it is null
     *
     * @param id the id of the costControlSystem to save.
     * @param costControlSystem the costControlSystem to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated costControlSystem,
     * or with status {@code 400 (Bad Request)} if the costControlSystem is not valid,
     * or with status {@code 404 (Not Found)} if the costControlSystem is not found,
     * or with status {@code 500 (Internal Server Error)} if the costControlSystem couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CostControlSystem> partialUpdateCostControlSystem(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody CostControlSystem costControlSystem
    ) throws URISyntaxException {
        log.debug("REST request to partial update CostControlSystem partially : {}, {}", id, costControlSystem);
        if (costControlSystem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, costControlSystem.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!costControlSystemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CostControlSystem> result = costControlSystemService.partialUpdate(costControlSystem);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, costControlSystem.getId())
        );
    }

    /**
     * {@code GET  /cost-control-systems} : get all the costControlSystems.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of costControlSystems in body.
     */
    @GetMapping("")
    public List<CostControlSystem> getAllCostControlSystems(
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get all CostControlSystems");
        return costControlSystemService.findAll();
    }

    /**
     * {@code GET  /cost-control-systems/:id} : get the "id" costControlSystem.
     *
     * @param id the id of the costControlSystem to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the costControlSystem, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CostControlSystem> getCostControlSystem(@PathVariable("id") String id) {
        log.debug("REST request to get CostControlSystem : {}", id);
        Optional<CostControlSystem> costControlSystem = costControlSystemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(costControlSystem);
    }

    /**
     * {@code DELETE  /cost-control-systems/:id} : delete the "id" costControlSystem.
     *
     * @param id the id of the costControlSystem to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCostControlSystem(@PathVariable("id") String id) {
        log.debug("REST request to delete CostControlSystem : {}", id);
        costControlSystemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
