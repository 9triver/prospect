package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.RegularInspection;
import com.cvicse.jy1.repository.RegularInspectionRepository;
import com.cvicse.jy1.service.RegularInspectionService;
import com.cvicse.jy1.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.RegularInspection}.
 */
@RestController
@RequestMapping("/api/regular-inspections")
public class RegularInspectionResource {

    private static final Logger log = LoggerFactory.getLogger(RegularInspectionResource.class);

    private static final String ENTITY_NAME = "regularInspection";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RegularInspectionService regularInspectionService;

    private final RegularInspectionRepository regularInspectionRepository;

    public RegularInspectionResource(
        RegularInspectionService regularInspectionService,
        RegularInspectionRepository regularInspectionRepository
    ) {
        this.regularInspectionService = regularInspectionService;
        this.regularInspectionRepository = regularInspectionRepository;
    }

    /**
     * {@code POST  /regular-inspections} : Create a new regularInspection.
     *
     * @param regularInspection the regularInspection to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new regularInspection, or with status {@code 400 (Bad Request)} if the regularInspection has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<RegularInspection> createRegularInspection(@Valid @RequestBody RegularInspection regularInspection)
        throws URISyntaxException {
        log.debug("REST request to save RegularInspection : {}", regularInspection);
        if (regularInspection.getId() != null) {
            throw new BadRequestAlertException("A new regularInspection cannot already have an ID", ENTITY_NAME, "idexists");
        }
        regularInspection = regularInspectionService.save(regularInspection);
        return ResponseEntity.created(new URI("/api/regular-inspections/" + regularInspection.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, regularInspection.getId().toString()))
            .body(regularInspection);
    }

    /**
     * {@code PUT  /regular-inspections/:id} : Updates an existing regularInspection.
     *
     * @param id the id of the regularInspection to save.
     * @param regularInspection the regularInspection to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated regularInspection,
     * or with status {@code 400 (Bad Request)} if the regularInspection is not valid,
     * or with status {@code 500 (Internal Server Error)} if the regularInspection couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RegularInspection> updateRegularInspection(
        @PathVariable(value = "id", required = false) final Integer id,
        @Valid @RequestBody RegularInspection regularInspection
    ) throws URISyntaxException {
        log.debug("REST request to update RegularInspection : {}, {}", id, regularInspection);
        if (regularInspection.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, regularInspection.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!regularInspectionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        regularInspection = regularInspectionService.update(regularInspection);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, regularInspection.getId().toString()))
            .body(regularInspection);
    }

    /**
     * {@code PATCH  /regular-inspections/:id} : Partial updates given fields of an existing regularInspection, field will ignore if it is null
     *
     * @param id the id of the regularInspection to save.
     * @param regularInspection the regularInspection to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated regularInspection,
     * or with status {@code 400 (Bad Request)} if the regularInspection is not valid,
     * or with status {@code 404 (Not Found)} if the regularInspection is not found,
     * or with status {@code 500 (Internal Server Error)} if the regularInspection couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RegularInspection> partialUpdateRegularInspection(
        @PathVariable(value = "id", required = false) final Integer id,
        @NotNull @RequestBody RegularInspection regularInspection
    ) throws URISyntaxException {
        log.debug("REST request to partial update RegularInspection partially : {}, {}", id, regularInspection);
        if (regularInspection.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, regularInspection.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!regularInspectionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RegularInspection> result = regularInspectionService.partialUpdate(regularInspection);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, regularInspection.getId().toString())
        );
    }

    /**
     * {@code GET  /regular-inspections} : get all the regularInspections.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of regularInspections in body.
     */
    @GetMapping("")
    public List<RegularInspection> getAllRegularInspections() {
        log.debug("REST request to get all RegularInspections");
        return regularInspectionService.findAll();
    }

    /**
     * {@code GET  /regular-inspections/:id} : get the "id" regularInspection.
     *
     * @param id the id of the regularInspection to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the regularInspection, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RegularInspection> getRegularInspection(@PathVariable("id") Integer id) {
        log.debug("REST request to get RegularInspection : {}", id);
        Optional<RegularInspection> regularInspection = regularInspectionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(regularInspection);
    }

    /**
     * {@code DELETE  /regular-inspections/:id} : delete the "id" regularInspection.
     *
     * @param id the id of the regularInspection to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegularInspection(@PathVariable("id") Integer id) {
        log.debug("REST request to delete RegularInspection : {}", id);
        regularInspectionService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
