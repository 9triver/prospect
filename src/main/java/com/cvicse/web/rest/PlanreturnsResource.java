package com.cvicse.web.rest;

import com.cvicse.domain.Planreturns;
import com.cvicse.repository.PlanreturnsRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Planreturns}.
 */
@RestController
@RequestMapping("/api/planreturns")
@Transactional
public class PlanreturnsResource {

    private final Logger log = LoggerFactory.getLogger(PlanreturnsResource.class);

    private static final String ENTITY_NAME = "planreturns";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlanreturnsRepository planreturnsRepository;

    public PlanreturnsResource(PlanreturnsRepository planreturnsRepository) {
        this.planreturnsRepository = planreturnsRepository;
    }

    /**
     * {@code POST  /planreturns} : Create a new planreturns.
     *
     * @param planreturns the planreturns to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new planreturns, or with status {@code 400 (Bad Request)} if the planreturns has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Planreturns> createPlanreturns(@RequestBody Planreturns planreturns) throws URISyntaxException {
        log.debug("REST request to save Planreturns : {}", planreturns);
        if (planreturns.getId() != null) {
            throw new BadRequestAlertException("A new planreturns cannot already have an ID", ENTITY_NAME, "idexists");
        }
        planreturns = planreturnsRepository.save(planreturns);
        return ResponseEntity.created(new URI("/api/planreturns/" + planreturns.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, planreturns.getId()))
            .body(planreturns);
    }

    /**
     * {@code PUT  /planreturns/:id} : Updates an existing planreturns.
     *
     * @param id the id of the planreturns to save.
     * @param planreturns the planreturns to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planreturns,
     * or with status {@code 400 (Bad Request)} if the planreturns is not valid,
     * or with status {@code 500 (Internal Server Error)} if the planreturns couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Planreturns> updatePlanreturns(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Planreturns planreturns
    ) throws URISyntaxException {
        log.debug("REST request to update Planreturns : {}, {}", id, planreturns);
        if (planreturns.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, planreturns.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!planreturnsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        planreturns = planreturnsRepository.save(planreturns);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, planreturns.getId()))
            .body(planreturns);
    }

    /**
     * {@code PATCH  /planreturns/:id} : Partial updates given fields of an existing planreturns, field will ignore if it is null
     *
     * @param id the id of the planreturns to save.
     * @param planreturns the planreturns to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planreturns,
     * or with status {@code 400 (Bad Request)} if the planreturns is not valid,
     * or with status {@code 404 (Not Found)} if the planreturns is not found,
     * or with status {@code 500 (Internal Server Error)} if the planreturns couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Planreturns> partialUpdatePlanreturns(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Planreturns planreturns
    ) throws URISyntaxException {
        log.debug("REST request to partial update Planreturns partially : {}, {}", id, planreturns);
        if (planreturns.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, planreturns.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!planreturnsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Planreturns> result = planreturnsRepository
            .findById(planreturns.getId())
            .map(existingPlanreturns -> {
                if (planreturns.getPlanreturnsname() != null) {
                    existingPlanreturns.setPlanreturnsname(planreturns.getPlanreturnsname());
                }
                if (planreturns.getStarttime() != null) {
                    existingPlanreturns.setStarttime(planreturns.getStarttime());
                }
                if (planreturns.getEndtime() != null) {
                    existingPlanreturns.setEndtime(planreturns.getEndtime());
                }
                if (planreturns.getPlantype() != null) {
                    existingPlanreturns.setPlantype(planreturns.getPlantype());
                }
                if (planreturns.getReturnstime() != null) {
                    existingPlanreturns.setReturnstime(planreturns.getReturnstime());
                }
                if (planreturns.getReturnsstatus() != null) {
                    existingPlanreturns.setReturnsstatus(planreturns.getReturnsstatus());
                }

                return existingPlanreturns;
            })
            .map(planreturnsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, planreturns.getId())
        );
    }

    /**
     * {@code GET  /planreturns} : get all the planreturns.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of planreturns in body.
     */
    @GetMapping("")
    public List<Planreturns> getAllPlanreturns(@RequestParam(name = "filter", required = false) String filter) {
        if ("planexecute-is-null".equals(filter)) {
            log.debug("REST request to get all Planreturnss where planexecute is null");
            return StreamSupport.stream(planreturnsRepository.findAll().spliterator(), false)
                .filter(planreturns -> planreturns.getPlanexecute() == null)
                .toList();
        }

        if ("progressplan-is-null".equals(filter)) {
            log.debug("REST request to get all Planreturnss where progressplan is null");
            return StreamSupport.stream(planreturnsRepository.findAll().spliterator(), false)
                .filter(planreturns -> planreturns.getProgressplan() == null)
                .toList();
        }
        log.debug("REST request to get all Planreturns");
        return planreturnsRepository.findAll();
    }

    /**
     * {@code GET  /planreturns/:id} : get the "id" planreturns.
     *
     * @param id the id of the planreturns to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the planreturns, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Planreturns> getPlanreturns(@PathVariable("id") String id) {
        log.debug("REST request to get Planreturns : {}", id);
        Optional<Planreturns> planreturns = planreturnsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(planreturns);
    }

    /**
     * {@code DELETE  /planreturns/:id} : delete the "id" planreturns.
     *
     * @param id the id of the planreturns to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanreturns(@PathVariable("id") String id) {
        log.debug("REST request to delete Planreturns : {}", id);
        planreturnsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
