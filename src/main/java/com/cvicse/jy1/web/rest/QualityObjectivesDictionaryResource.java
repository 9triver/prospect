package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.QualityObjectivesDictionary;
import com.cvicse.jy1.repository.QualityObjectivesDictionaryRepository;
import com.cvicse.jy1.service.QualityObjectivesDictionaryService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.QualityObjectivesDictionary}.
 */
@RestController
@RequestMapping("/api/quality-objectives-dictionaries")
public class QualityObjectivesDictionaryResource {

    private static final Logger log = LoggerFactory.getLogger(QualityObjectivesDictionaryResource.class);

    private static final String ENTITY_NAME = "qualityObjectivesDictionary";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QualityObjectivesDictionaryService qualityObjectivesDictionaryService;

    private final QualityObjectivesDictionaryRepository qualityObjectivesDictionaryRepository;

    public QualityObjectivesDictionaryResource(
        QualityObjectivesDictionaryService qualityObjectivesDictionaryService,
        QualityObjectivesDictionaryRepository qualityObjectivesDictionaryRepository
    ) {
        this.qualityObjectivesDictionaryService = qualityObjectivesDictionaryService;
        this.qualityObjectivesDictionaryRepository = qualityObjectivesDictionaryRepository;
    }

    /**
     * {@code POST  /quality-objectives-dictionaries} : Create a new qualityObjectivesDictionary.
     *
     * @param qualityObjectivesDictionary the qualityObjectivesDictionary to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qualityObjectivesDictionary, or with status {@code 400 (Bad Request)} if the qualityObjectivesDictionary has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<QualityObjectivesDictionary> createQualityObjectivesDictionary(
        @Valid @RequestBody QualityObjectivesDictionary qualityObjectivesDictionary
    ) throws URISyntaxException {
        log.debug("REST request to save QualityObjectivesDictionary : {}", qualityObjectivesDictionary);
        if (qualityObjectivesDictionary.getId() != null) {
            throw new BadRequestAlertException("A new qualityObjectivesDictionary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        qualityObjectivesDictionary = qualityObjectivesDictionaryService.save(qualityObjectivesDictionary);
        return ResponseEntity.created(new URI("/api/quality-objectives-dictionaries/" + qualityObjectivesDictionary.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, qualityObjectivesDictionary.getId().toString())
            )
            .body(qualityObjectivesDictionary);
    }

    /**
     * {@code PUT  /quality-objectives-dictionaries/:id} : Updates an existing qualityObjectivesDictionary.
     *
     * @param id the id of the qualityObjectivesDictionary to save.
     * @param qualityObjectivesDictionary the qualityObjectivesDictionary to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualityObjectivesDictionary,
     * or with status {@code 400 (Bad Request)} if the qualityObjectivesDictionary is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qualityObjectivesDictionary couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<QualityObjectivesDictionary> updateQualityObjectivesDictionary(
        @PathVariable(value = "id", required = false) final Integer id,
        @Valid @RequestBody QualityObjectivesDictionary qualityObjectivesDictionary
    ) throws URISyntaxException {
        log.debug("REST request to update QualityObjectivesDictionary : {}, {}", id, qualityObjectivesDictionary);
        if (qualityObjectivesDictionary.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualityObjectivesDictionary.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualityObjectivesDictionaryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        qualityObjectivesDictionary = qualityObjectivesDictionaryService.update(qualityObjectivesDictionary);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualityObjectivesDictionary.getId().toString()))
            .body(qualityObjectivesDictionary);
    }

    /**
     * {@code PATCH  /quality-objectives-dictionaries/:id} : Partial updates given fields of an existing qualityObjectivesDictionary, field will ignore if it is null
     *
     * @param id the id of the qualityObjectivesDictionary to save.
     * @param qualityObjectivesDictionary the qualityObjectivesDictionary to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualityObjectivesDictionary,
     * or with status {@code 400 (Bad Request)} if the qualityObjectivesDictionary is not valid,
     * or with status {@code 404 (Not Found)} if the qualityObjectivesDictionary is not found,
     * or with status {@code 500 (Internal Server Error)} if the qualityObjectivesDictionary couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<QualityObjectivesDictionary> partialUpdateQualityObjectivesDictionary(
        @PathVariable(value = "id", required = false) final Integer id,
        @NotNull @RequestBody QualityObjectivesDictionary qualityObjectivesDictionary
    ) throws URISyntaxException {
        log.debug("REST request to partial update QualityObjectivesDictionary partially : {}, {}", id, qualityObjectivesDictionary);
        if (qualityObjectivesDictionary.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualityObjectivesDictionary.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualityObjectivesDictionaryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QualityObjectivesDictionary> result = qualityObjectivesDictionaryService.partialUpdate(qualityObjectivesDictionary);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualityObjectivesDictionary.getId().toString())
        );
    }

    /**
     * {@code GET  /quality-objectives-dictionaries} : get all the qualityObjectivesDictionaries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qualityObjectivesDictionaries in body.
     */
    @GetMapping("")
    public List<QualityObjectivesDictionary> getAllQualityObjectivesDictionaries() {
        log.debug("REST request to get all QualityObjectivesDictionaries");
        return qualityObjectivesDictionaryService.findAll();
    }

    /**
     * {@code GET  /quality-objectives-dictionaries/:id} : get the "id" qualityObjectivesDictionary.
     *
     * @param id the id of the qualityObjectivesDictionary to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qualityObjectivesDictionary, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<QualityObjectivesDictionary> getQualityObjectivesDictionary(@PathVariable("id") Integer id) {
        log.debug("REST request to get QualityObjectivesDictionary : {}", id);
        Optional<QualityObjectivesDictionary> qualityObjectivesDictionary = qualityObjectivesDictionaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qualityObjectivesDictionary);
    }

    /**
     * {@code DELETE  /quality-objectives-dictionaries/:id} : delete the "id" qualityObjectivesDictionary.
     *
     * @param id the id of the qualityObjectivesDictionary to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQualityObjectivesDictionary(@PathVariable("id") Integer id) {
        log.debug("REST request to delete QualityObjectivesDictionary : {}", id);
        qualityObjectivesDictionaryService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
