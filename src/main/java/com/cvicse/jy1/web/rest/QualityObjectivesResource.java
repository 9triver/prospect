package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.QualityObjectives;
import com.cvicse.jy1.repository.QualityObjectivesRepository;
import com.cvicse.jy1.service.QualityObjectivesService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.QualityObjectives}.
 */
@RestController
@RequestMapping("/api/quality-objectives")
public class QualityObjectivesResource {

    private static final Logger log = LoggerFactory.getLogger(QualityObjectivesResource.class);

    private static final String ENTITY_NAME = "qualityObjectives";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QualityObjectivesService qualityObjectivesService;

    private final QualityObjectivesRepository qualityObjectivesRepository;

    public QualityObjectivesResource(
        QualityObjectivesService qualityObjectivesService,
        QualityObjectivesRepository qualityObjectivesRepository
    ) {
        this.qualityObjectivesService = qualityObjectivesService;
        this.qualityObjectivesRepository = qualityObjectivesRepository;
    }

    /**
     * {@code POST  /quality-objectives} : Create a new qualityObjectives.
     *
     * @param qualityObjectives the qualityObjectives to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qualityObjectives, or with status {@code 400 (Bad Request)} if the qualityObjectives has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<QualityObjectives> createQualityObjectives(@RequestBody QualityObjectives qualityObjectives)
        throws URISyntaxException {
        log.debug("REST request to save QualityObjectives : {}", qualityObjectives);
        if (qualityObjectives.getId() != null) {
            throw new BadRequestAlertException("A new qualityObjectives cannot already have an ID", ENTITY_NAME, "idexists");
        }
        qualityObjectives = qualityObjectivesService.save(qualityObjectives);
        return ResponseEntity.created(new URI("/api/quality-objectives/" + qualityObjectives.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, qualityObjectives.getId()))
            .body(qualityObjectives);
    }

    /**
     * {@code PUT  /quality-objectives/:id} : Updates an existing qualityObjectives.
     *
     * @param id the id of the qualityObjectives to save.
     * @param qualityObjectives the qualityObjectives to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualityObjectives,
     * or with status {@code 400 (Bad Request)} if the qualityObjectives is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qualityObjectives couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<QualityObjectives> updateQualityObjectives(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody QualityObjectives qualityObjectives
    ) throws URISyntaxException {
        log.debug("REST request to update QualityObjectives : {}, {}", id, qualityObjectives);
        if (qualityObjectives.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualityObjectives.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualityObjectivesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        qualityObjectives = qualityObjectivesService.update(qualityObjectives);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualityObjectives.getId()))
            .body(qualityObjectives);
    }

    /**
     * {@code PATCH  /quality-objectives/:id} : Partial updates given fields of an existing qualityObjectives, field will ignore if it is null
     *
     * @param id the id of the qualityObjectives to save.
     * @param qualityObjectives the qualityObjectives to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualityObjectives,
     * or with status {@code 400 (Bad Request)} if the qualityObjectives is not valid,
     * or with status {@code 404 (Not Found)} if the qualityObjectives is not found,
     * or with status {@code 500 (Internal Server Error)} if the qualityObjectives couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<QualityObjectives> partialUpdateQualityObjectives(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody QualityObjectives qualityObjectives
    ) throws URISyntaxException {
        log.debug("REST request to partial update QualityObjectives partially : {}, {}", id, qualityObjectives);
        if (qualityObjectives.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualityObjectives.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualityObjectivesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QualityObjectives> result = qualityObjectivesService.partialUpdate(qualityObjectives);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualityObjectives.getId())
        );
    }

    /**
     * {@code GET  /quality-objectives} : get all the qualityObjectives.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qualityObjectives in body.
     */
    @GetMapping("")
    public List<QualityObjectives> getAllQualityObjectives(
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get all QualityObjectives");
        return qualityObjectivesService.findAll();
    }

    /**
     * {@code GET  /quality-objectives/:id} : get the "id" qualityObjectives.
     *
     * @param id the id of the qualityObjectives to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qualityObjectives, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<QualityObjectives> getQualityObjectives(@PathVariable("id") String id) {
        log.debug("REST request to get QualityObjectives : {}", id);
        Optional<QualityObjectives> qualityObjectives = qualityObjectivesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qualityObjectives);
    }

    /**
     * {@code DELETE  /quality-objectives/:id} : delete the "id" qualityObjectives.
     *
     * @param id the id of the qualityObjectives to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQualityObjectives(@PathVariable("id") String id) {
        log.debug("REST request to delete QualityObjectives : {}", id);
        qualityObjectivesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
