package com.cvicse.web.rest;

import com.cvicse.domain.Cycleplan;
import com.cvicse.repository.CycleplanRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Cycleplan}.
 */
@RestController
@RequestMapping("/api/cycleplans")
@Transactional
public class CycleplanResource {

    private final Logger log = LoggerFactory.getLogger(CycleplanResource.class);

    private static final String ENTITY_NAME = "cycleplan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CycleplanRepository cycleplanRepository;

    public CycleplanResource(CycleplanRepository cycleplanRepository) {
        this.cycleplanRepository = cycleplanRepository;
    }

    /**
     * {@code POST  /cycleplans} : Create a new cycleplan.
     *
     * @param cycleplan the cycleplan to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cycleplan, or with status {@code 400 (Bad Request)} if the cycleplan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Cycleplan> createCycleplan(@RequestBody Cycleplan cycleplan) throws URISyntaxException {
        log.debug("REST request to save Cycleplan : {}", cycleplan);
        if (cycleplan.getId() != null) {
            throw new BadRequestAlertException("A new cycleplan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        cycleplan = cycleplanRepository.save(cycleplan);
        return ResponseEntity.created(new URI("/api/cycleplans/" + cycleplan.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, cycleplan.getId()))
            .body(cycleplan);
    }

    /**
     * {@code PUT  /cycleplans/:id} : Updates an existing cycleplan.
     *
     * @param id the id of the cycleplan to save.
     * @param cycleplan the cycleplan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cycleplan,
     * or with status {@code 400 (Bad Request)} if the cycleplan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cycleplan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cycleplan> updateCycleplan(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Cycleplan cycleplan
    ) throws URISyntaxException {
        log.debug("REST request to update Cycleplan : {}, {}", id, cycleplan);
        if (cycleplan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cycleplan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cycleplanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        cycleplan = cycleplanRepository.save(cycleplan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cycleplan.getId()))
            .body(cycleplan);
    }

    /**
     * {@code PATCH  /cycleplans/:id} : Partial updates given fields of an existing cycleplan, field will ignore if it is null
     *
     * @param id the id of the cycleplan to save.
     * @param cycleplan the cycleplan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cycleplan,
     * or with status {@code 400 (Bad Request)} if the cycleplan is not valid,
     * or with status {@code 404 (Not Found)} if the cycleplan is not found,
     * or with status {@code 500 (Internal Server Error)} if the cycleplan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Cycleplan> partialUpdateCycleplan(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Cycleplan cycleplan
    ) throws URISyntaxException {
        log.debug("REST request to partial update Cycleplan partially : {}, {}", id, cycleplan);
        if (cycleplan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cycleplan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cycleplanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Cycleplan> result = cycleplanRepository
            .findById(cycleplan.getId())
            .map(existingCycleplan -> {
                if (cycleplan.getCycleplanname() != null) {
                    existingCycleplan.setCycleplanname(cycleplan.getCycleplanname());
                }
                if (cycleplan.getSecretlevel() != null) {
                    existingCycleplan.setSecretlevel(cycleplan.getSecretlevel());
                }
                if (cycleplan.getStarttime() != null) {
                    existingCycleplan.setStarttime(cycleplan.getStarttime());
                }
                if (cycleplan.getEndtime() != null) {
                    existingCycleplan.setEndtime(cycleplan.getEndtime());
                }
                if (cycleplan.getActualstarttime() != null) {
                    existingCycleplan.setActualstarttime(cycleplan.getActualstarttime());
                }
                if (cycleplan.getActualendtime() != null) {
                    existingCycleplan.setActualendtime(cycleplan.getActualendtime());
                }
                if (cycleplan.getResponsiblename() != null) {
                    existingCycleplan.setResponsiblename(cycleplan.getResponsiblename());
                }
                if (cycleplan.getStatus() != null) {
                    existingCycleplan.setStatus(cycleplan.getStatus());
                }
                if (cycleplan.getAuditStatus() != null) {
                    existingCycleplan.setAuditStatus(cycleplan.getAuditStatus());
                }

                return existingCycleplan;
            })
            .map(cycleplanRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cycleplan.getId())
        );
    }

    /**
     * {@code GET  /cycleplans} : get all the cycleplans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cycleplans in body.
     */
    @GetMapping("")
    public List<Cycleplan> getAllCycleplans() {
        log.debug("REST request to get all Cycleplans");
        return cycleplanRepository.findAll();
    }

    /**
     * {@code GET  /cycleplans/:id} : get the "id" cycleplan.
     *
     * @param id the id of the cycleplan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cycleplan, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cycleplan> getCycleplan(@PathVariable("id") String id) {
        log.debug("REST request to get Cycleplan : {}", id);
        Optional<Cycleplan> cycleplan = cycleplanRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cycleplan);
    }

    /**
     * {@code DELETE  /cycleplans/:id} : delete the "id" cycleplan.
     *
     * @param id the id of the cycleplan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCycleplan(@PathVariable("id") String id) {
        log.debug("REST request to delete Cycleplan : {}", id);
        cycleplanRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
