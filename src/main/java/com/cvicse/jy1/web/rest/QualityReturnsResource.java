package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.QualityReturns;
import com.cvicse.jy1.repository.QualityReturnsRepository;
import com.cvicse.jy1.service.QualityReturnsService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.QualityReturns}.
 */
@RestController
@RequestMapping("/api/quality-returns")
public class QualityReturnsResource {

    private static final Logger log = LoggerFactory.getLogger(QualityReturnsResource.class);

    private static final String ENTITY_NAME = "qualityReturns";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QualityReturnsService qualityReturnsService;

    private final QualityReturnsRepository qualityReturnsRepository;

    public QualityReturnsResource(QualityReturnsService qualityReturnsService, QualityReturnsRepository qualityReturnsRepository) {
        this.qualityReturnsService = qualityReturnsService;
        this.qualityReturnsRepository = qualityReturnsRepository;
    }

    /**
     * {@code POST  /quality-returns} : Create a new qualityReturns.
     *
     * @param qualityReturns the qualityReturns to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qualityReturns, or with status {@code 400 (Bad Request)} if the qualityReturns has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<QualityReturns> createQualityReturns(@Valid @RequestBody QualityReturns qualityReturns)
        throws URISyntaxException {
        log.debug("REST request to save QualityReturns : {}", qualityReturns);
        if (qualityReturns.getId() != null) {
            throw new BadRequestAlertException("A new qualityReturns cannot already have an ID", ENTITY_NAME, "idexists");
        }
        qualityReturns = qualityReturnsService.save(qualityReturns);
        return ResponseEntity.created(new URI("/api/quality-returns/" + qualityReturns.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, qualityReturns.getId().toString()))
            .body(qualityReturns);
    }

    /**
     * {@code PUT  /quality-returns/:id} : Updates an existing qualityReturns.
     *
     * @param id the id of the qualityReturns to save.
     * @param qualityReturns the qualityReturns to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualityReturns,
     * or with status {@code 400 (Bad Request)} if the qualityReturns is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qualityReturns couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<QualityReturns> updateQualityReturns(
        @PathVariable(value = "id", required = false) final Integer id,
        @Valid @RequestBody QualityReturns qualityReturns
    ) throws URISyntaxException {
        log.debug("REST request to update QualityReturns : {}, {}", id, qualityReturns);
        if (qualityReturns.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualityReturns.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualityReturnsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        qualityReturns = qualityReturnsService.update(qualityReturns);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualityReturns.getId().toString()))
            .body(qualityReturns);
    }

    /**
     * {@code PATCH  /quality-returns/:id} : Partial updates given fields of an existing qualityReturns, field will ignore if it is null
     *
     * @param id the id of the qualityReturns to save.
     * @param qualityReturns the qualityReturns to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualityReturns,
     * or with status {@code 400 (Bad Request)} if the qualityReturns is not valid,
     * or with status {@code 404 (Not Found)} if the qualityReturns is not found,
     * or with status {@code 500 (Internal Server Error)} if the qualityReturns couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<QualityReturns> partialUpdateQualityReturns(
        @PathVariable(value = "id", required = false) final Integer id,
        @NotNull @RequestBody QualityReturns qualityReturns
    ) throws URISyntaxException {
        log.debug("REST request to partial update QualityReturns partially : {}, {}", id, qualityReturns);
        if (qualityReturns.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualityReturns.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualityReturnsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QualityReturns> result = qualityReturnsService.partialUpdate(qualityReturns);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualityReturns.getId().toString())
        );
    }

    /**
     * {@code GET  /quality-returns} : get all the qualityReturns.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qualityReturns in body.
     */
    @GetMapping("")
    public List<QualityReturns> getAllQualityReturns(
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get all QualityReturns");
        return qualityReturnsService.findAll();
    }

    /**
     * {@code GET  /quality-returns/:id} : get the "id" qualityReturns.
     *
     * @param id the id of the qualityReturns to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qualityReturns, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<QualityReturns> getQualityReturns(@PathVariable("id") Integer id) {
        log.debug("REST request to get QualityReturns : {}", id);
        Optional<QualityReturns> qualityReturns = qualityReturnsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qualityReturns);
    }

    /**
     * {@code DELETE  /quality-returns/:id} : delete the "id" qualityReturns.
     *
     * @param id the id of the qualityReturns to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQualityReturns(@PathVariable("id") Integer id) {
        log.debug("REST request to delete QualityReturns : {}", id);
        qualityReturnsService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
