package com.cvicse.web.rest;

import com.cvicse.domain.Technicalmanagement;
import com.cvicse.repository.TechnicalmanagementRepository;
import com.cvicse.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.cvicse.domain.Technicalmanagement}.
 */
@RestController
@RequestMapping("/api/technicalmanagements")
@Transactional
public class TechnicalmanagementResource {

    private final Logger log = LoggerFactory.getLogger(TechnicalmanagementResource.class);

    private static final String ENTITY_NAME = "technicalmanagement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TechnicalmanagementRepository technicalmanagementRepository;

    public TechnicalmanagementResource(TechnicalmanagementRepository technicalmanagementRepository) {
        this.technicalmanagementRepository = technicalmanagementRepository;
    }

    /**
     * {@code POST  /technicalmanagements} : Create a new technicalmanagement.
     *
     * @param technicalmanagement the technicalmanagement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new technicalmanagement, or with status {@code 400 (Bad Request)} if the technicalmanagement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Technicalmanagement> createTechnicalmanagement(@RequestBody Technicalmanagement technicalmanagement)
        throws URISyntaxException {
        log.debug("REST request to save Technicalmanagement : {}", technicalmanagement);
        if (technicalmanagement.getId() != null) {
            throw new BadRequestAlertException("A new technicalmanagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        technicalmanagement = technicalmanagementRepository.save(technicalmanagement);
        return ResponseEntity.created(new URI("/api/technicalmanagements/" + technicalmanagement.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, technicalmanagement.getId()))
            .body(technicalmanagement);
    }

    /**
     * {@code PUT  /technicalmanagements/:id} : Updates an existing technicalmanagement.
     *
     * @param id the id of the technicalmanagement to save.
     * @param technicalmanagement the technicalmanagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated technicalmanagement,
     * or with status {@code 400 (Bad Request)} if the technicalmanagement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the technicalmanagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Technicalmanagement> updateTechnicalmanagement(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Technicalmanagement technicalmanagement
    ) throws URISyntaxException {
        log.debug("REST request to update Technicalmanagement : {}, {}", id, technicalmanagement);
        if (technicalmanagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, technicalmanagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!technicalmanagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        technicalmanagement = technicalmanagementRepository.save(technicalmanagement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, technicalmanagement.getId()))
            .body(technicalmanagement);
    }

    /**
     * {@code PATCH  /technicalmanagements/:id} : Partial updates given fields of an existing technicalmanagement, field will ignore if it is null
     *
     * @param id the id of the technicalmanagement to save.
     * @param technicalmanagement the technicalmanagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated technicalmanagement,
     * or with status {@code 400 (Bad Request)} if the technicalmanagement is not valid,
     * or with status {@code 404 (Not Found)} if the technicalmanagement is not found,
     * or with status {@code 500 (Internal Server Error)} if the technicalmanagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Technicalmanagement> partialUpdateTechnicalmanagement(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Technicalmanagement technicalmanagement
    ) throws URISyntaxException {
        log.debug("REST request to partial update Technicalmanagement partially : {}, {}", id, technicalmanagement);
        if (technicalmanagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, technicalmanagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!technicalmanagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Technicalmanagement> result = technicalmanagementRepository
            .findById(technicalmanagement.getId())
            .map(existingTechnicalmanagement -> {
                if (technicalmanagement.getName() != null) {
                    existingTechnicalmanagement.setName(technicalmanagement.getName());
                }
                if (technicalmanagement.getDescription() != null) {
                    existingTechnicalmanagement.setDescription(technicalmanagement.getDescription());
                }
                if (technicalmanagement.getStarttime() != null) {
                    existingTechnicalmanagement.setStarttime(technicalmanagement.getStarttime());
                }
                if (technicalmanagement.getEndtime() != null) {
                    existingTechnicalmanagement.setEndtime(technicalmanagement.getEndtime());
                }

                return existingTechnicalmanagement;
            })
            .map(technicalmanagementRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, technicalmanagement.getId())
        );
    }

    /**
     * {@code GET  /technicalmanagements} : get all the technicalmanagements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of technicalmanagements in body.
     */
    @GetMapping("")
    public List<Technicalmanagement> getAllTechnicalmanagements() {
        log.debug("REST request to get all Technicalmanagements");
        return technicalmanagementRepository.findAll();
    }

    /**
     * {@code GET  /technicalmanagements/:id} : get the "id" technicalmanagement.
     *
     * @param id the id of the technicalmanagement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the technicalmanagement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Technicalmanagement> getTechnicalmanagement(@PathVariable("id") String id) {
        log.debug("REST request to get Technicalmanagement : {}", id);
        Optional<Technicalmanagement> technicalmanagement = technicalmanagementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(technicalmanagement);
    }

    /**
     * {@code DELETE  /technicalmanagements/:id} : delete the "id" technicalmanagement.
     *
     * @param id the id of the technicalmanagement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnicalmanagement(@PathVariable("id") String id) {
        log.debug("REST request to delete Technicalmanagement : {}", id);
        technicalmanagementRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
