package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.Archives;
import com.cvicse.jy1.repository.ArchivesRepository;
import com.cvicse.jy1.service.ArchivesService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.Archives}.
 */
@RestController
@RequestMapping("/api/archives")
public class ArchivesResource {

    private static final Logger log = LoggerFactory.getLogger(ArchivesResource.class);

    private static final String ENTITY_NAME = "archives";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ArchivesService archivesService;

    private final ArchivesRepository archivesRepository;

    public ArchivesResource(ArchivesService archivesService, ArchivesRepository archivesRepository) {
        this.archivesService = archivesService;
        this.archivesRepository = archivesRepository;
    }

    /**
     * {@code POST  /archives} : Create a new archives.
     *
     * @param archives the archives to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new archives, or with status {@code 400 (Bad Request)} if the archives has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Archives> createArchives(@RequestBody Archives archives) throws URISyntaxException {
        log.debug("REST request to save Archives : {}", archives);
        if (archives.getId() != null) {
            throw new BadRequestAlertException("A new archives cannot already have an ID", ENTITY_NAME, "idexists");
        }
        archives = archivesService.save(archives);
        return ResponseEntity.created(new URI("/api/archives/" + archives.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, archives.getId()))
            .body(archives);
    }

    /**
     * {@code PUT  /archives/:id} : Updates an existing archives.
     *
     * @param id the id of the archives to save.
     * @param archives the archives to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated archives,
     * or with status {@code 400 (Bad Request)} if the archives is not valid,
     * or with status {@code 500 (Internal Server Error)} if the archives couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Archives> updateArchives(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Archives archives
    ) throws URISyntaxException {
        log.debug("REST request to update Archives : {}, {}", id, archives);
        if (archives.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, archives.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!archivesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        archives = archivesService.update(archives);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, archives.getId()))
            .body(archives);
    }

    /**
     * {@code PATCH  /archives/:id} : Partial updates given fields of an existing archives, field will ignore if it is null
     *
     * @param id the id of the archives to save.
     * @param archives the archives to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated archives,
     * or with status {@code 400 (Bad Request)} if the archives is not valid,
     * or with status {@code 404 (Not Found)} if the archives is not found,
     * or with status {@code 500 (Internal Server Error)} if the archives couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Archives> partialUpdateArchives(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Archives archives
    ) throws URISyntaxException {
        log.debug("REST request to partial update Archives partially : {}, {}", id, archives);
        if (archives.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, archives.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!archivesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Archives> result = archivesService.partialUpdate(archives);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, archives.getId())
        );
    }

    /**
     * {@code GET  /archives} : get all the archives.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of archives in body.
     */
    @GetMapping("")
    public List<Archives> getAllArchives() {
        log.debug("REST request to get all Archives");
        return archivesService.findAll();
    }

    /**
     * {@code GET  /archives/:id} : get the "id" archives.
     *
     * @param id the id of the archives to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the archives, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Archives> getArchives(@PathVariable("id") String id) {
        log.debug("REST request to get Archives : {}", id);
        Optional<Archives> archives = archivesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(archives);
    }

    /**
     * {@code DELETE  /archives/:id} : delete the "id" archives.
     *
     * @param id the id of the archives to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArchives(@PathVariable("id") String id) {
        log.debug("REST request to delete Archives : {}", id);
        archivesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
