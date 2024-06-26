package com.cvicse.web.rest;

import com.cvicse.domain.Riskidentification;
import com.cvicse.repository.RiskidentificationRepository;
import com.cvicse.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link com.cvicse.domain.Riskidentification}.
 */
@RestController
@RequestMapping("/api/riskidentifications")
@Transactional
public class RiskidentificationResource {

    private final Logger log = LoggerFactory.getLogger(RiskidentificationResource.class);

    private static final String ENTITY_NAME = "riskidentification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RiskidentificationRepository riskidentificationRepository;

    public RiskidentificationResource(RiskidentificationRepository riskidentificationRepository) {
        this.riskidentificationRepository = riskidentificationRepository;
    }

    /**
     * {@code POST  /riskidentifications} : Create a new riskidentification.
     *
     * @param riskidentification the riskidentification to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new riskidentification, or with status {@code 400 (Bad Request)} if the riskidentification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Riskidentification> createRiskidentification(@RequestBody Riskidentification riskidentification)
        throws URISyntaxException {
        log.debug("REST request to save Riskidentification : {}", riskidentification);
        if (riskidentification.getId() != null) {
            throw new BadRequestAlertException("A new riskidentification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        riskidentification = riskidentificationRepository.save(riskidentification);
        return ResponseEntity.created(new URI("/api/riskidentifications/" + riskidentification.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, riskidentification.getId()))
            .body(riskidentification);
    }

    /**
     * {@code PUT  /riskidentifications/:id} : Updates an existing riskidentification.
     *
     * @param id the id of the riskidentification to save.
     * @param riskidentification the riskidentification to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskidentification,
     * or with status {@code 400 (Bad Request)} if the riskidentification is not valid,
     * or with status {@code 500 (Internal Server Error)} if the riskidentification couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Riskidentification> updateRiskidentification(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Riskidentification riskidentification
    ) throws URISyntaxException {
        log.debug("REST request to update Riskidentification : {}, {}", id, riskidentification);
        if (riskidentification.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, riskidentification.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!riskidentificationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        riskidentification = riskidentificationRepository.save(riskidentification);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskidentification.getId()))
            .body(riskidentification);
    }

    /**
     * {@code PATCH  /riskidentifications/:id} : Partial updates given fields of an existing riskidentification, field will ignore if it is null
     *
     * @param id the id of the riskidentification to save.
     * @param riskidentification the riskidentification to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskidentification,
     * or with status {@code 400 (Bad Request)} if the riskidentification is not valid,
     * or with status {@code 404 (Not Found)} if the riskidentification is not found,
     * or with status {@code 500 (Internal Server Error)} if the riskidentification couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Riskidentification> partialUpdateRiskidentification(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Riskidentification riskidentification
    ) throws URISyntaxException {
        log.debug("REST request to partial update Riskidentification partially : {}, {}", id, riskidentification);
        if (riskidentification.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, riskidentification.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!riskidentificationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Riskidentification> result = riskidentificationRepository
            .findById(riskidentification.getId())
            .map(existingRiskidentification -> {
                if (riskidentification.getProjectname() != null) {
                    existingRiskidentification.setProjectname(riskidentification.getProjectname());
                }
                if (riskidentification.getYear() != null) {
                    existingRiskidentification.setYear(riskidentification.getYear());
                }
                if (riskidentification.getNodename() != null) {
                    existingRiskidentification.setNodename(riskidentification.getNodename());
                }
                if (riskidentification.getRisktype() != null) {
                    existingRiskidentification.setRisktype(riskidentification.getRisktype());
                }
                if (riskidentification.getDecumentid() != null) {
                    existingRiskidentification.setDecumentid(riskidentification.getDecumentid());
                }
                if (riskidentification.getVersion() != null) {
                    existingRiskidentification.setVersion(riskidentification.getVersion());
                }
                if (riskidentification.getUsetime() != null) {
                    existingRiskidentification.setUsetime(riskidentification.getUsetime());
                }
                if (riskidentification.getSystemlevel() != null) {
                    existingRiskidentification.setSystemlevel(riskidentification.getSystemlevel());
                }
                if (riskidentification.getRisklevel() != null) {
                    existingRiskidentification.setRisklevel(riskidentification.getRisklevel());
                }
                if (riskidentification.getLimitationtime() != null) {
                    existingRiskidentification.setLimitationtime(riskidentification.getLimitationtime());
                }
                if (riskidentification.getClosetype() != null) {
                    existingRiskidentification.setClosetype(riskidentification.getClosetype());
                }

                return existingRiskidentification;
            })
            .map(riskidentificationRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskidentification.getId())
        );
    }

    /**
     * {@code GET  /riskidentifications} : get all the riskidentifications.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of riskidentifications in body.
     */
    @GetMapping("")
    public List<Riskidentification> getAllRiskidentifications(@RequestParam(name = "filter", required = false) String filter) {
        if ("riskreport-is-null".equals(filter)) {
            log.debug("REST request to get all Riskidentifications where riskreport is null");
            return StreamSupport.stream(riskidentificationRepository.findAll().spliterator(), false)
                .filter(riskidentification -> riskidentification.getRiskreport() == null)
                .toList();
        }
        log.debug("REST request to get all Riskidentifications");
        return riskidentificationRepository.findAll();
    }

    /**
     * {@code GET  /riskidentifications/:id} : get the "id" riskidentification.
     *
     * @param id the id of the riskidentification to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the riskidentification, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Riskidentification> getRiskidentification(@PathVariable("id") String id) {
        log.debug("REST request to get Riskidentification : {}", id);
        Optional<Riskidentification> riskidentification = riskidentificationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(riskidentification);
    }

    /**
     * {@code DELETE  /riskidentifications/:id} : delete the "id" riskidentification.
     *
     * @param id the id of the riskidentification to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRiskidentification(@PathVariable("id") String id) {
        log.debug("REST request to delete Riskidentification : {}", id);
        riskidentificationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
