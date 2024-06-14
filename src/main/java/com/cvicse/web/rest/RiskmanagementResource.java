package com.cvicse.web.rest;

import com.cvicse.domain.Riskmanagement;
import com.cvicse.repository.RiskmanagementRepository;
import com.cvicse.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
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
    public ResponseEntity<Riskmanagement> createRiskmanagement(@Valid @RequestBody Riskmanagement riskmanagement)
        throws URISyntaxException {
        log.debug("REST request to save Riskmanagement : {}", riskmanagement);
        if (riskmanagement.getId() != null) {
            throw new BadRequestAlertException("A new riskmanagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        riskmanagement = riskmanagementRepository.save(riskmanagement);
        return ResponseEntity.created(new URI("/api/riskmanagements/" + riskmanagement.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, riskmanagement.getId().toString()))
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
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Riskmanagement riskmanagement
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
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskmanagement.getId().toString()))
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
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Riskmanagement riskmanagement
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
                if (riskmanagement.getRiskid() != null) {
                    existingRiskmanagement.setRiskid(riskmanagement.getRiskid());
                }
                if (riskmanagement.getProjectname() != null) {
                    existingRiskmanagement.setProjectname(riskmanagement.getProjectname());
                }
                if (riskmanagement.getYear() != null) {
                    existingRiskmanagement.setYear(riskmanagement.getYear());
                }
                if (riskmanagement.getNodename() != null) {
                    existingRiskmanagement.setNodename(riskmanagement.getNodename());
                }
                if (riskmanagement.getRisktype() != null) {
                    existingRiskmanagement.setRisktype(riskmanagement.getRisktype());
                }
                if (riskmanagement.getDecumentid() != null) {
                    existingRiskmanagement.setDecumentid(riskmanagement.getDecumentid());
                }
                if (riskmanagement.getVersion() != null) {
                    existingRiskmanagement.setVersion(riskmanagement.getVersion());
                }
                if (riskmanagement.getUsetime() != null) {
                    existingRiskmanagement.setUsetime(riskmanagement.getUsetime());
                }
                if (riskmanagement.getSystemlevel() != null) {
                    existingRiskmanagement.setSystemlevel(riskmanagement.getSystemlevel());
                }
                if (riskmanagement.getRisklevel() != null) {
                    existingRiskmanagement.setRisklevel(riskmanagement.getRisklevel());
                }
                if (riskmanagement.getLimitationtime() != null) {
                    existingRiskmanagement.setLimitationtime(riskmanagement.getLimitationtime());
                }
                if (riskmanagement.getClosetype() != null) {
                    existingRiskmanagement.setClosetype(riskmanagement.getClosetype());
                }

                return existingRiskmanagement;
            })
            .map(riskmanagementRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskmanagement.getId().toString())
        );
    }

    /**
     * {@code GET  /riskmanagements} : get all the riskmanagements.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of riskmanagements in body.
     */
    @GetMapping("")
    public List<Riskmanagement> getAllRiskmanagements(@RequestParam(name = "filter", required = false) String filter) {
        if ("project-is-null".equals(filter)) {
            log.debug("REST request to get all Riskmanagements where project is null");
            return StreamSupport.stream(riskmanagementRepository.findAll().spliterator(), false)
                .filter(riskmanagement -> riskmanagement.getProject() == null)
                .toList();
        }

        if ("riskreport-is-null".equals(filter)) {
            log.debug("REST request to get all Riskmanagements where riskreport is null");
            return StreamSupport.stream(riskmanagementRepository.findAll().spliterator(), false)
                .filter(riskmanagement -> riskmanagement.getRiskreport() == null)
                .toList();
        }
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
    public ResponseEntity<Riskmanagement> getRiskmanagement(@PathVariable("id") Long id) {
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
    public ResponseEntity<Void> deleteRiskmanagement(@PathVariable("id") Long id) {
        log.debug("REST request to delete Riskmanagement : {}", id);
        riskmanagementRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
