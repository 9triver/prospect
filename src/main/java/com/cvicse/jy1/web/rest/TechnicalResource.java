package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.Technical;
import com.cvicse.jy1.repository.TechnicalRepository;
import com.cvicse.jy1.service.TechnicalService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.Technical}.
 */
@RestController
@RequestMapping("/api/technicals")
public class TechnicalResource {

    private static final Logger log = LoggerFactory.getLogger(TechnicalResource.class);

    private static final String ENTITY_NAME = "technical";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TechnicalService technicalService;

    private final TechnicalRepository technicalRepository;

    public TechnicalResource(TechnicalService technicalService, TechnicalRepository technicalRepository) {
        this.technicalService = technicalService;
        this.technicalRepository = technicalRepository;
    }

    /**
     * {@code POST  /technicals} : Create a new technical.
     *
     * @param technical the technical to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new technical, or with status {@code 400 (Bad Request)} if the technical has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Technical> createTechnical(@RequestBody Technical technical) throws URISyntaxException {
        log.debug("REST request to save Technical : {}", technical);
        if (technical.getId() != null) {
            throw new BadRequestAlertException("A new technical cannot already have an ID", ENTITY_NAME, "idexists");
        }
        technical = technicalService.save(technical);
        return ResponseEntity.created(new URI("/api/technicals/" + technical.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, technical.getId()))
            .body(technical);
    }

    /**
     * {@code PUT  /technicals/:id} : Updates an existing technical.
     *
     * @param id the id of the technical to save.
     * @param technical the technical to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated technical,
     * or with status {@code 400 (Bad Request)} if the technical is not valid,
     * or with status {@code 500 (Internal Server Error)} if the technical couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Technical> updateTechnical(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Technical technical
    ) throws URISyntaxException {
        log.debug("REST request to update Technical : {}, {}", id, technical);
        if (technical.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, technical.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!technicalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        technical = technicalService.update(technical);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, technical.getId()))
            .body(technical);
    }

    /**
     * {@code PATCH  /technicals/:id} : Partial updates given fields of an existing technical, field will ignore if it is null
     *
     * @param id the id of the technical to save.
     * @param technical the technical to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated technical,
     * or with status {@code 400 (Bad Request)} if the technical is not valid,
     * or with status {@code 404 (Not Found)} if the technical is not found,
     * or with status {@code 500 (Internal Server Error)} if the technical couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Technical> partialUpdateTechnical(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Technical technical
    ) throws URISyntaxException {
        log.debug("REST request to partial update Technical partially : {}, {}", id, technical);
        if (technical.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, technical.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!technicalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Technical> result = technicalService.partialUpdate(technical);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, technical.getId())
        );
    }

    /**
     * {@code GET  /technicals} : get all the technicals.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of technicals in body.
     */
    @GetMapping("")
    public List<Technical> getAllTechnicals(@RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload) {
        log.debug("REST request to get all Technicals");
        return technicalService.findAll();
    }

    /**
     * {@code GET  /technicals/:id} : get the "id" technical.
     *
     * @param id the id of the technical to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the technical, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Technical> getTechnical(@PathVariable("id") String id) {
        log.debug("REST request to get Technical : {}", id);
        Optional<Technical> technical = technicalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(technical);
    }

    /**
     * {@code DELETE  /technicals/:id} : delete the "id" technical.
     *
     * @param id the id of the technical to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnical(@PathVariable("id") String id) {
        log.debug("REST request to delete Technical : {}", id);
        technicalService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
