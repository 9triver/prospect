package com.cvicse.web.rest;

import com.cvicse.domain.RiskmanagementWbs;
import com.cvicse.repository.RiskmanagementWbsRepository;
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
 * REST controller for managing {@link com.cvicse.domain.RiskmanagementWbs}.
 */
@RestController
@RequestMapping("/api/riskmanagement-wbs")
@Transactional
public class RiskmanagementWbsResource {

    private final Logger log = LoggerFactory.getLogger(RiskmanagementWbsResource.class);

    private static final String ENTITY_NAME = "riskmanagementWbs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RiskmanagementWbsRepository riskmanagementWbsRepository;

    public RiskmanagementWbsResource(RiskmanagementWbsRepository riskmanagementWbsRepository) {
        this.riskmanagementWbsRepository = riskmanagementWbsRepository;
    }

    /**
     * {@code POST  /riskmanagement-wbs} : Create a new riskmanagementWbs.
     *
     * @param riskmanagementWbs the riskmanagementWbs to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new riskmanagementWbs, or with status {@code 400 (Bad Request)} if the riskmanagementWbs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<RiskmanagementWbs> createRiskmanagementWbs(@RequestBody RiskmanagementWbs riskmanagementWbs)
        throws URISyntaxException {
        log.debug("REST request to save RiskmanagementWbs : {}", riskmanagementWbs);
        if (riskmanagementWbs.getId() != null) {
            throw new BadRequestAlertException("A new riskmanagementWbs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        riskmanagementWbs = riskmanagementWbsRepository.save(riskmanagementWbs);
        return ResponseEntity.created(new URI("/api/riskmanagement-wbs/" + riskmanagementWbs.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, riskmanagementWbs.getId()))
            .body(riskmanagementWbs);
    }

    /**
     * {@code PUT  /riskmanagement-wbs/:id} : Updates an existing riskmanagementWbs.
     *
     * @param id the id of the riskmanagementWbs to save.
     * @param riskmanagementWbs the riskmanagementWbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskmanagementWbs,
     * or with status {@code 400 (Bad Request)} if the riskmanagementWbs is not valid,
     * or with status {@code 500 (Internal Server Error)} if the riskmanagementWbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RiskmanagementWbs> updateRiskmanagementWbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody RiskmanagementWbs riskmanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to update RiskmanagementWbs : {}, {}", id, riskmanagementWbs);
        if (riskmanagementWbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, riskmanagementWbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!riskmanagementWbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        riskmanagementWbs = riskmanagementWbsRepository.save(riskmanagementWbs);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskmanagementWbs.getId()))
            .body(riskmanagementWbs);
    }

    /**
     * {@code PATCH  /riskmanagement-wbs/:id} : Partial updates given fields of an existing riskmanagementWbs, field will ignore if it is null
     *
     * @param id the id of the riskmanagementWbs to save.
     * @param riskmanagementWbs the riskmanagementWbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskmanagementWbs,
     * or with status {@code 400 (Bad Request)} if the riskmanagementWbs is not valid,
     * or with status {@code 404 (Not Found)} if the riskmanagementWbs is not found,
     * or with status {@code 500 (Internal Server Error)} if the riskmanagementWbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RiskmanagementWbs> partialUpdateRiskmanagementWbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody RiskmanagementWbs riskmanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to partial update RiskmanagementWbs partially : {}, {}", id, riskmanagementWbs);
        if (riskmanagementWbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, riskmanagementWbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!riskmanagementWbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RiskmanagementWbs> result = riskmanagementWbsRepository
            .findById(riskmanagementWbs.getId())
            .map(existingRiskmanagementWbs -> {
                if (riskmanagementWbs.getWbsspare1() != null) {
                    existingRiskmanagementWbs.setWbsspare1(riskmanagementWbs.getWbsspare1());
                }
                if (riskmanagementWbs.getWbsspare2() != null) {
                    existingRiskmanagementWbs.setWbsspare2(riskmanagementWbs.getWbsspare2());
                }
                if (riskmanagementWbs.getWbsspare3() != null) {
                    existingRiskmanagementWbs.setWbsspare3(riskmanagementWbs.getWbsspare3());
                }
                if (riskmanagementWbs.getWbsspare4() != null) {
                    existingRiskmanagementWbs.setWbsspare4(riskmanagementWbs.getWbsspare4());
                }
                if (riskmanagementWbs.getWbsspare5() != null) {
                    existingRiskmanagementWbs.setWbsspare5(riskmanagementWbs.getWbsspare5());
                }

                return existingRiskmanagementWbs;
            })
            .map(riskmanagementWbsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskmanagementWbs.getId())
        );
    }

    /**
     * {@code GET  /riskmanagement-wbs} : get all the riskmanagementWbs.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of riskmanagementWbs in body.
     */
    @GetMapping("")
    public List<RiskmanagementWbs> getAllRiskmanagementWbs(@RequestParam(name = "filter", required = false) String filter) {
        if ("riskmanagement-is-null".equals(filter)) {
            log.debug("REST request to get all RiskmanagementWbss where riskmanagement is null");
            return StreamSupport.stream(riskmanagementWbsRepository.findAll().spliterator(), false)
                .filter(riskmanagementWbs -> riskmanagementWbs.getRiskmanagement() == null)
                .toList();
        }
        log.debug("REST request to get all RiskmanagementWbs");
        return riskmanagementWbsRepository.findAll();
    }

    /**
     * {@code GET  /riskmanagement-wbs/:id} : get the "id" riskmanagementWbs.
     *
     * @param id the id of the riskmanagementWbs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the riskmanagementWbs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RiskmanagementWbs> getRiskmanagementWbs(@PathVariable("id") String id) {
        log.debug("REST request to get RiskmanagementWbs : {}", id);
        Optional<RiskmanagementWbs> riskmanagementWbs = riskmanagementWbsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(riskmanagementWbs);
    }

    /**
     * {@code DELETE  /riskmanagement-wbs/:id} : delete the "id" riskmanagementWbs.
     *
     * @param id the id of the riskmanagementWbs to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRiskmanagementWbs(@PathVariable("id") String id) {
        log.debug("REST request to delete RiskmanagementWbs : {}", id);
        riskmanagementWbsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
