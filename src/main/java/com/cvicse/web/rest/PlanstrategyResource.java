package com.cvicse.web.rest;

import com.cvicse.domain.Planstrategy;
import com.cvicse.repository.PlanstrategyRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Planstrategy}.
 */
@RestController
@RequestMapping("/api/planstrategies")
@Transactional
public class PlanstrategyResource {

    private final Logger log = LoggerFactory.getLogger(PlanstrategyResource.class);

    private static final String ENTITY_NAME = "planstrategy";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlanstrategyRepository planstrategyRepository;

    public PlanstrategyResource(PlanstrategyRepository planstrategyRepository) {
        this.planstrategyRepository = planstrategyRepository;
    }

    /**
     * {@code POST  /planstrategies} : Create a new planstrategy.
     *
     * @param planstrategy the planstrategy to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new planstrategy, or with status {@code 400 (Bad Request)} if the planstrategy has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Planstrategy> createPlanstrategy(@RequestBody Planstrategy planstrategy) throws URISyntaxException {
        log.debug("REST request to save Planstrategy : {}", planstrategy);
        if (planstrategy.getId() != null) {
            throw new BadRequestAlertException("A new planstrategy cannot already have an ID", ENTITY_NAME, "idexists");
        }
        planstrategy = planstrategyRepository.save(planstrategy);
        return ResponseEntity.created(new URI("/api/planstrategies/" + planstrategy.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, planstrategy.getId()))
            .body(planstrategy);
    }

    /**
     * {@code PUT  /planstrategies/:id} : Updates an existing planstrategy.
     *
     * @param id the id of the planstrategy to save.
     * @param planstrategy the planstrategy to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planstrategy,
     * or with status {@code 400 (Bad Request)} if the planstrategy is not valid,
     * or with status {@code 500 (Internal Server Error)} if the planstrategy couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Planstrategy> updatePlanstrategy(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Planstrategy planstrategy
    ) throws URISyntaxException {
        log.debug("REST request to update Planstrategy : {}, {}", id, planstrategy);
        if (planstrategy.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, planstrategy.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!planstrategyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        planstrategy = planstrategyRepository.save(planstrategy);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, planstrategy.getId()))
            .body(planstrategy);
    }

    /**
     * {@code PATCH  /planstrategies/:id} : Partial updates given fields of an existing planstrategy, field will ignore if it is null
     *
     * @param id the id of the planstrategy to save.
     * @param planstrategy the planstrategy to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planstrategy,
     * or with status {@code 400 (Bad Request)} if the planstrategy is not valid,
     * or with status {@code 404 (Not Found)} if the planstrategy is not found,
     * or with status {@code 500 (Internal Server Error)} if the planstrategy couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Planstrategy> partialUpdatePlanstrategy(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Planstrategy planstrategy
    ) throws URISyntaxException {
        log.debug("REST request to partial update Planstrategy partially : {}, {}", id, planstrategy);
        if (planstrategy.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, planstrategy.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!planstrategyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Planstrategy> result = planstrategyRepository
            .findById(planstrategy.getId())
            .map(existingPlanstrategy -> {
                if (planstrategy.getStrategyname() != null) {
                    existingPlanstrategy.setStrategyname(planstrategy.getStrategyname());
                }
                if (planstrategy.getNumber() != null) {
                    existingPlanstrategy.setNumber(planstrategy.getNumber());
                }
                if (planstrategy.getType() != null) {
                    existingPlanstrategy.setType(planstrategy.getType());
                }

                return existingPlanstrategy;
            })
            .map(planstrategyRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, planstrategy.getId())
        );
    }

    /**
     * {@code GET  /planstrategies} : get all the planstrategies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of planstrategies in body.
     */
    @GetMapping("")
    public List<Planstrategy> getAllPlanstrategies() {
        log.debug("REST request to get all Planstrategies");
        return planstrategyRepository.findAll();
    }

    /**
     * {@code GET  /planstrategies/:id} : get the "id" planstrategy.
     *
     * @param id the id of the planstrategy to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the planstrategy, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Planstrategy> getPlanstrategy(@PathVariable("id") String id) {
        log.debug("REST request to get Planstrategy : {}", id);
        Optional<Planstrategy> planstrategy = planstrategyRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(planstrategy);
    }

    /**
     * {@code DELETE  /planstrategies/:id} : delete the "id" planstrategy.
     *
     * @param id the id of the planstrategy to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanstrategy(@PathVariable("id") String id) {
        log.debug("REST request to delete Planstrategy : {}", id);
        planstrategyRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
