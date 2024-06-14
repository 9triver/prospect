package com.cvicse.web.rest;

import com.cvicse.domain.Planexecute;
import com.cvicse.repository.PlanexecuteRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Planexecute}.
 */
@RestController
@RequestMapping("/api/planexecutes")
@Transactional
public class PlanexecuteResource {

    private final Logger log = LoggerFactory.getLogger(PlanexecuteResource.class);

    private static final String ENTITY_NAME = "planexecute";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlanexecuteRepository planexecuteRepository;

    public PlanexecuteResource(PlanexecuteRepository planexecuteRepository) {
        this.planexecuteRepository = planexecuteRepository;
    }

    /**
     * {@code POST  /planexecutes} : Create a new planexecute.
     *
     * @param planexecute the planexecute to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new planexecute, or with status {@code 400 (Bad Request)} if the planexecute has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Planexecute> createPlanexecute(@RequestBody Planexecute planexecute) throws URISyntaxException {
        log.debug("REST request to save Planexecute : {}", planexecute);
        if (planexecute.getId() != null) {
            throw new BadRequestAlertException("A new planexecute cannot already have an ID", ENTITY_NAME, "idexists");
        }
        planexecute = planexecuteRepository.save(planexecute);
        return ResponseEntity.created(new URI("/api/planexecutes/" + planexecute.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, planexecute.getId().toString()))
            .body(planexecute);
    }

    /**
     * {@code PUT  /planexecutes/:id} : Updates an existing planexecute.
     *
     * @param id the id of the planexecute to save.
     * @param planexecute the planexecute to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planexecute,
     * or with status {@code 400 (Bad Request)} if the planexecute is not valid,
     * or with status {@code 500 (Internal Server Error)} if the planexecute couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Planexecute> updatePlanexecute(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Planexecute planexecute
    ) throws URISyntaxException {
        log.debug("REST request to update Planexecute : {}, {}", id, planexecute);
        if (planexecute.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, planexecute.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!planexecuteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        planexecute = planexecuteRepository.save(planexecute);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, planexecute.getId().toString()))
            .body(planexecute);
    }

    /**
     * {@code PATCH  /planexecutes/:id} : Partial updates given fields of an existing planexecute, field will ignore if it is null
     *
     * @param id the id of the planexecute to save.
     * @param planexecute the planexecute to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planexecute,
     * or with status {@code 400 (Bad Request)} if the planexecute is not valid,
     * or with status {@code 404 (Not Found)} if the planexecute is not found,
     * or with status {@code 500 (Internal Server Error)} if the planexecute couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Planexecute> partialUpdatePlanexecute(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Planexecute planexecute
    ) throws URISyntaxException {
        log.debug("REST request to partial update Planexecute partially : {}, {}", id, planexecute);
        if (planexecute.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, planexecute.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!planexecuteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Planexecute> result = planexecuteRepository
            .findById(planexecute.getId())
            .map(existingPlanexecute -> {
                if (planexecute.getPlanname() != null) {
                    existingPlanexecute.setPlanname(planexecute.getPlanname());
                }
                if (planexecute.getPlanstarttime() != null) {
                    existingPlanexecute.setPlanstarttime(planexecute.getPlanstarttime());
                }
                if (planexecute.getPlanendtime() != null) {
                    existingPlanexecute.setPlanendtime(planexecute.getPlanendtime());
                }

                return existingPlanexecute;
            })
            .map(planexecuteRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, planexecute.getId().toString())
        );
    }

    /**
     * {@code GET  /planexecutes} : get all the planexecutes.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of planexecutes in body.
     */
    @GetMapping("")
    public List<Planexecute> getAllPlanexecutes(@RequestParam(name = "filter", required = false) String filter) {
        if ("monthplan-is-null".equals(filter)) {
            log.debug("REST request to get all Planexecutes where monthplan is null");
            return StreamSupport.stream(planexecuteRepository.findAll().spliterator(), false)
                .filter(planexecute -> planexecute.getMonthplan() == null)
                .toList();
        }
        log.debug("REST request to get all Planexecutes");
        return planexecuteRepository.findAll();
    }

    /**
     * {@code GET  /planexecutes/:id} : get the "id" planexecute.
     *
     * @param id the id of the planexecute to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the planexecute, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Planexecute> getPlanexecute(@PathVariable("id") Long id) {
        log.debug("REST request to get Planexecute : {}", id);
        Optional<Planexecute> planexecute = planexecuteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(planexecute);
    }

    /**
     * {@code DELETE  /planexecutes/:id} : delete the "id" planexecute.
     *
     * @param id the id of the planexecute to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanexecute(@PathVariable("id") Long id) {
        log.debug("REST request to delete Planexecute : {}", id);
        planexecuteRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
