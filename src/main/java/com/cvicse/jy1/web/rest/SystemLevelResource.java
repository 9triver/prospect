package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.SystemLevel;
import com.cvicse.jy1.repository.SystemLevelRepository;
import com.cvicse.jy1.service.SystemLevelService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.SystemLevel}.
 */
@RestController
@RequestMapping("/api/system-levels")
public class SystemLevelResource {

    private static final Logger log = LoggerFactory.getLogger(SystemLevelResource.class);

    private static final String ENTITY_NAME = "systemLevel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SystemLevelService systemLevelService;

    private final SystemLevelRepository systemLevelRepository;

    public SystemLevelResource(SystemLevelService systemLevelService, SystemLevelRepository systemLevelRepository) {
        this.systemLevelService = systemLevelService;
        this.systemLevelRepository = systemLevelRepository;
    }

    /**
     * {@code POST  /system-levels} : Create a new systemLevel.
     *
     * @param systemLevel the systemLevel to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new systemLevel, or with status {@code 400 (Bad Request)} if the systemLevel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<SystemLevel> createSystemLevel(@RequestBody SystemLevel systemLevel) throws URISyntaxException {
        log.debug("REST request to save SystemLevel : {}", systemLevel);
        if (systemLevel.getId() != null) {
            throw new BadRequestAlertException("A new systemLevel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        systemLevel = systemLevelService.save(systemLevel);
        return ResponseEntity.created(new URI("/api/system-levels/" + systemLevel.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, systemLevel.getId().toString()))
            .body(systemLevel);
    }

    /**
     * {@code PUT  /system-levels/:id} : Updates an existing systemLevel.
     *
     * @param id the id of the systemLevel to save.
     * @param systemLevel the systemLevel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated systemLevel,
     * or with status {@code 400 (Bad Request)} if the systemLevel is not valid,
     * or with status {@code 500 (Internal Server Error)} if the systemLevel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SystemLevel> updateSystemLevel(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody SystemLevel systemLevel
    ) throws URISyntaxException {
        log.debug("REST request to update SystemLevel : {}, {}", id, systemLevel);
        if (systemLevel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, systemLevel.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!systemLevelRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        systemLevel = systemLevelService.update(systemLevel);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, systemLevel.getId().toString()))
            .body(systemLevel);
    }

    /**
     * {@code PATCH  /system-levels/:id} : Partial updates given fields of an existing systemLevel, field will ignore if it is null
     *
     * @param id the id of the systemLevel to save.
     * @param systemLevel the systemLevel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated systemLevel,
     * or with status {@code 400 (Bad Request)} if the systemLevel is not valid,
     * or with status {@code 404 (Not Found)} if the systemLevel is not found,
     * or with status {@code 500 (Internal Server Error)} if the systemLevel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SystemLevel> partialUpdateSystemLevel(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody SystemLevel systemLevel
    ) throws URISyntaxException {
        log.debug("REST request to partial update SystemLevel partially : {}, {}", id, systemLevel);
        if (systemLevel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, systemLevel.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!systemLevelRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SystemLevel> result = systemLevelService.partialUpdate(systemLevel);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, systemLevel.getId().toString())
        );
    }

    /**
     * {@code GET  /system-levels} : get all the systemLevels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of systemLevels in body.
     */
    @GetMapping("")
    public List<SystemLevel> getAllSystemLevels() {
        log.debug("REST request to get all SystemLevels");
        return systemLevelService.findAll();
    }

    /**
     * {@code GET  /system-levels/:id} : get the "id" systemLevel.
     *
     * @param id the id of the systemLevel to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the systemLevel, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SystemLevel> getSystemLevel(@PathVariable("id") Integer id) {
        log.debug("REST request to get SystemLevel : {}", id);
        Optional<SystemLevel> systemLevel = systemLevelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(systemLevel);
    }

    /**
     * {@code DELETE  /system-levels/:id} : delete the "id" systemLevel.
     *
     * @param id the id of the systemLevel to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSystemLevel(@PathVariable("id") Integer id) {
        log.debug("REST request to delete SystemLevel : {}", id);
        systemLevelService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
