package com.cvicse.web.rest;

import com.cvicse.domain.Riskmanagement;
import com.cvicse.repository.RiskmanagementRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Riskmanagement}.
 */
@RestController
@RequestMapping("/api/riskmanagements")
@Transactional
public class RiskmanagementResource {

    private final Logger log = LoggerFactory.getLogger(RiskmanagementResource.class);

    private static final String ENTITY_NAME = "riskmanagement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RiskmanagementRepository riskmanagementRepository;

    public RiskmanagementResource(RiskmanagementRepository riskmanagementRepository) {
        this.riskmanagementRepository = riskmanagementRepository;
    }

    /**
     * {@code POST  /riskmanagements} : Create a new riskmanagement.
     *
     * @param riskmanagement the riskmanagement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new riskmanagement, or with status {@code 400 (Bad Request)} if the riskmanagement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Riskmanagement> createRiskmanagement(@RequestBody Riskmanagement riskmanagement) throws URISyntaxException {
        log.debug("REST request to save Riskmanagement : {}", riskmanagement);
        if (riskmanagement.getId() != null) {
            throw new BadRequestAlertException("A new riskmanagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        riskmanagement = riskmanagementRepository.save(riskmanagement);
        return ResponseEntity.created(new URI("/api/riskmanagements/" + riskmanagement.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, riskmanagement.getId()))
            .body(riskmanagement);
    }

    /**
     * {@code PUT  /riskmanagements/:id} : Updates an existing riskmanagement.
     *
     * @param id the id of the riskmanagement to save.
     * @param riskmanagement the riskmanagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskmanagement,
     * or with status {@code 400 (Bad Request)} if the riskmanagement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the riskmanagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Riskmanagement> updateRiskmanagement(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Riskmanagement riskmanagement
    ) throws URISyntaxException {
        log.debug("REST request to update Riskmanagement : {}, {}", id, riskmanagement);
        if (riskmanagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, riskmanagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!riskmanagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        riskmanagement = riskmanagementRepository.save(riskmanagement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskmanagement.getId()))
            .body(riskmanagement);
    }

    /**
     * {@code PATCH  /riskmanagements/:id} : Partial updates given fields of an existing riskmanagement, field will ignore if it is null
     *
     * @param id the id of the riskmanagement to save.
     * @param riskmanagement the riskmanagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskmanagement,
     * or with status {@code 400 (Bad Request)} if the riskmanagement is not valid,
     * or with status {@code 404 (Not Found)} if the riskmanagement is not found,
     * or with status {@code 500 (Internal Server Error)} if the riskmanagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Riskmanagement> partialUpdateRiskmanagement(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Riskmanagement riskmanagement
    ) throws URISyntaxException {
        log.debug("REST request to partial update Riskmanagement partially : {}, {}", id, riskmanagement);
        if (riskmanagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, riskmanagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!riskmanagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Riskmanagement> result = riskmanagementRepository
            .findById(riskmanagement.getId())
            .map(existingRiskmanagement -> {
                if (riskmanagement.getName() != null) {
                    existingRiskmanagement.setName(riskmanagement.getName());
                }
                if (riskmanagement.getDescription() != null) {
                    existingRiskmanagement.setDescription(riskmanagement.getDescription());
                }
                if (riskmanagement.getStarttime() != null) {
                    existingRiskmanagement.setStarttime(riskmanagement.getStarttime());
                }
                if (riskmanagement.getEndtime() != null) {
                    existingRiskmanagement.setEndtime(riskmanagement.getEndtime());
                }

                return existingRiskmanagement;
            })
            .map(riskmanagementRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskmanagement.getId())
        );
    }

    /**
     * {@code GET  /riskmanagements} : get all the riskmanagements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of riskmanagements in body.
     */
    @GetMapping("")
    public List<Riskmanagement> getAllRiskmanagements() {
        log.debug("REST request to get all Riskmanagements");
        return riskmanagementRepository.findAll();
    }

    /**
     * {@code GET  /riskmanagements/:id} : get the "id" riskmanagement.
     *
     * @param id the id of the riskmanagement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the riskmanagement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Riskmanagement> getRiskmanagement(@PathVariable("id") String id) {
        log.debug("REST request to get Riskmanagement : {}", id);
        Optional<Riskmanagement> riskmanagement = riskmanagementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(riskmanagement);
    }

    /**
     * {@code DELETE  /riskmanagements/:id} : delete the "id" riskmanagement.
     *
     * @param id the id of the riskmanagement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRiskmanagement(@PathVariable("id") String id) {
        log.debug("REST request to delete Riskmanagement : {}", id);
        riskmanagementRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
