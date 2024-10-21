package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.Qualitytozero;
import com.cvicse.jy1.repository.QualitytozeroRepository;
import com.cvicse.jy1.service.QualitytozeroService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.Qualitytozero}.
 */
@RestController
@RequestMapping("/api/qualitytozeros")
public class QualitytozeroResource {

    private static final Logger log = LoggerFactory.getLogger(QualitytozeroResource.class);

    private static final String ENTITY_NAME = "qualitytozero";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QualitytozeroService qualitytozeroService;

    private final QualitytozeroRepository qualitytozeroRepository;

    public QualitytozeroResource(QualitytozeroService qualitytozeroService, QualitytozeroRepository qualitytozeroRepository) {
        this.qualitytozeroService = qualitytozeroService;
        this.qualitytozeroRepository = qualitytozeroRepository;
    }

    /**
     * {@code POST  /qualitytozeros} : Create a new qualitytozero.
     *
     * @param qualitytozero the qualitytozero to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qualitytozero, or with status {@code 400 (Bad Request)} if the qualitytozero has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Qualitytozero> createQualitytozero(@RequestBody Qualitytozero qualitytozero) throws URISyntaxException {
        log.debug("REST request to save Qualitytozero : {}", qualitytozero);
        if (qualitytozero.getId() != null) {
            throw new BadRequestAlertException("A new qualitytozero cannot already have an ID", ENTITY_NAME, "idexists");
        }
        qualitytozero = qualitytozeroService.save(qualitytozero);
        return ResponseEntity.created(new URI("/api/qualitytozeros/" + qualitytozero.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, qualitytozero.getId().toString()))
            .body(qualitytozero);
    }

    /**
     * {@code PUT  /qualitytozeros/:id} : Updates an existing qualitytozero.
     *
     * @param id the id of the qualitytozero to save.
     * @param qualitytozero the qualitytozero to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualitytozero,
     * or with status {@code 400 (Bad Request)} if the qualitytozero is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qualitytozero couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Qualitytozero> updateQualitytozero(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody Qualitytozero qualitytozero
    ) throws URISyntaxException {
        log.debug("REST request to update Qualitytozero : {}, {}", id, qualitytozero);
        if (qualitytozero.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualitytozero.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualitytozeroRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        qualitytozero = qualitytozeroService.update(qualitytozero);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualitytozero.getId().toString()))
            .body(qualitytozero);
    }

    /**
     * {@code PATCH  /qualitytozeros/:id} : Partial updates given fields of an existing qualitytozero, field will ignore if it is null
     *
     * @param id the id of the qualitytozero to save.
     * @param qualitytozero the qualitytozero to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualitytozero,
     * or with status {@code 400 (Bad Request)} if the qualitytozero is not valid,
     * or with status {@code 404 (Not Found)} if the qualitytozero is not found,
     * or with status {@code 500 (Internal Server Error)} if the qualitytozero couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Qualitytozero> partialUpdateQualitytozero(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody Qualitytozero qualitytozero
    ) throws URISyntaxException {
        log.debug("REST request to partial update Qualitytozero partially : {}, {}", id, qualitytozero);
        if (qualitytozero.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualitytozero.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualitytozeroRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Qualitytozero> result = qualitytozeroService.partialUpdate(qualitytozero);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualitytozero.getId().toString())
        );
    }

    /**
     * {@code GET  /qualitytozeros} : get all the qualitytozeros.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qualitytozeros in body.
     */
    @GetMapping("")
    public List<Qualitytozero> getAllQualitytozeros() {
        log.debug("REST request to get all Qualitytozeros");
        return qualitytozeroService.findAll();
    }

    /**
     * {@code GET  /qualitytozeros/:id} : get the "id" qualitytozero.
     *
     * @param id the id of the qualitytozero to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qualitytozero, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Qualitytozero> getQualitytozero(@PathVariable("id") Integer id) {
        log.debug("REST request to get Qualitytozero : {}", id);
        Optional<Qualitytozero> qualitytozero = qualitytozeroService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qualitytozero);
    }

    /**
     * {@code DELETE  /qualitytozeros/:id} : delete the "id" qualitytozero.
     *
     * @param id the id of the qualitytozero to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQualitytozero(@PathVariable("id") Integer id) {
        log.debug("REST request to delete Qualitytozero : {}", id);
        qualitytozeroService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
