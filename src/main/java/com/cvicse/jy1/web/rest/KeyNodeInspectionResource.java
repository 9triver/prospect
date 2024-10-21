package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.KeyNodeInspection;
import com.cvicse.jy1.repository.KeyNodeInspectionRepository;
import com.cvicse.jy1.service.KeyNodeInspectionService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.KeyNodeInspection}.
 */
@RestController
@RequestMapping("/api/key-node-inspections")
public class KeyNodeInspectionResource {

    private static final Logger log = LoggerFactory.getLogger(KeyNodeInspectionResource.class);

    private static final String ENTITY_NAME = "keyNodeInspection";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KeyNodeInspectionService keyNodeInspectionService;

    private final KeyNodeInspectionRepository keyNodeInspectionRepository;

    public KeyNodeInspectionResource(
        KeyNodeInspectionService keyNodeInspectionService,
        KeyNodeInspectionRepository keyNodeInspectionRepository
    ) {
        this.keyNodeInspectionService = keyNodeInspectionService;
        this.keyNodeInspectionRepository = keyNodeInspectionRepository;
    }

    /**
     * {@code POST  /key-node-inspections} : Create a new keyNodeInspection.
     *
     * @param keyNodeInspection the keyNodeInspection to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new keyNodeInspection, or with status {@code 400 (Bad Request)} if the keyNodeInspection has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<KeyNodeInspection> createKeyNodeInspection(@Valid @RequestBody KeyNodeInspection keyNodeInspection)
        throws URISyntaxException {
        log.debug("REST request to save KeyNodeInspection : {}", keyNodeInspection);
        if (keyNodeInspection.getId() != null) {
            throw new BadRequestAlertException("A new keyNodeInspection cannot already have an ID", ENTITY_NAME, "idexists");
        }
        keyNodeInspection = keyNodeInspectionService.save(keyNodeInspection);
        return ResponseEntity.created(new URI("/api/key-node-inspections/" + keyNodeInspection.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, keyNodeInspection.getId().toString()))
            .body(keyNodeInspection);
    }

    /**
     * {@code PUT  /key-node-inspections/:id} : Updates an existing keyNodeInspection.
     *
     * @param id the id of the keyNodeInspection to save.
     * @param keyNodeInspection the keyNodeInspection to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated keyNodeInspection,
     * or with status {@code 400 (Bad Request)} if the keyNodeInspection is not valid,
     * or with status {@code 500 (Internal Server Error)} if the keyNodeInspection couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<KeyNodeInspection> updateKeyNodeInspection(
        @PathVariable(value = "id", required = false) final Integer id,
        @Valid @RequestBody KeyNodeInspection keyNodeInspection
    ) throws URISyntaxException {
        log.debug("REST request to update KeyNodeInspection : {}, {}", id, keyNodeInspection);
        if (keyNodeInspection.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, keyNodeInspection.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!keyNodeInspectionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        keyNodeInspection = keyNodeInspectionService.update(keyNodeInspection);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, keyNodeInspection.getId().toString()))
            .body(keyNodeInspection);
    }

    /**
     * {@code PATCH  /key-node-inspections/:id} : Partial updates given fields of an existing keyNodeInspection, field will ignore if it is null
     *
     * @param id the id of the keyNodeInspection to save.
     * @param keyNodeInspection the keyNodeInspection to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated keyNodeInspection,
     * or with status {@code 400 (Bad Request)} if the keyNodeInspection is not valid,
     * or with status {@code 404 (Not Found)} if the keyNodeInspection is not found,
     * or with status {@code 500 (Internal Server Error)} if the keyNodeInspection couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<KeyNodeInspection> partialUpdateKeyNodeInspection(
        @PathVariable(value = "id", required = false) final Integer id,
        @NotNull @RequestBody KeyNodeInspection keyNodeInspection
    ) throws URISyntaxException {
        log.debug("REST request to partial update KeyNodeInspection partially : {}, {}", id, keyNodeInspection);
        if (keyNodeInspection.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, keyNodeInspection.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!keyNodeInspectionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<KeyNodeInspection> result = keyNodeInspectionService.partialUpdate(keyNodeInspection);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, keyNodeInspection.getId().toString())
        );
    }

    /**
     * {@code GET  /key-node-inspections} : get all the keyNodeInspections.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of keyNodeInspections in body.
     */
    @GetMapping("")
    public List<KeyNodeInspection> getAllKeyNodeInspections() {
        log.debug("REST request to get all KeyNodeInspections");
        return keyNodeInspectionService.findAll();
    }

    /**
     * {@code GET  /key-node-inspections/:id} : get the "id" keyNodeInspection.
     *
     * @param id the id of the keyNodeInspection to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the keyNodeInspection, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<KeyNodeInspection> getKeyNodeInspection(@PathVariable("id") Integer id) {
        log.debug("REST request to get KeyNodeInspection : {}", id);
        Optional<KeyNodeInspection> keyNodeInspection = keyNodeInspectionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(keyNodeInspection);
    }

    /**
     * {@code DELETE  /key-node-inspections/:id} : delete the "id" keyNodeInspection.
     *
     * @param id the id of the keyNodeInspection to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKeyNodeInspection(@PathVariable("id") Integer id) {
        log.debug("REST request to delete KeyNodeInspection : {}", id);
        keyNodeInspectionService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
