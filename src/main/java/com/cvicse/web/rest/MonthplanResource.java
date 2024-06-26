package com.cvicse.web.rest;

import com.cvicse.domain.Monthplan;
import com.cvicse.repository.MonthplanRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Monthplan}.
 */
@RestController
@RequestMapping("/api/monthplans")
@Transactional
public class MonthplanResource {

    private final Logger log = LoggerFactory.getLogger(MonthplanResource.class);

    private static final String ENTITY_NAME = "monthplan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MonthplanRepository monthplanRepository;

    public MonthplanResource(MonthplanRepository monthplanRepository) {
        this.monthplanRepository = monthplanRepository;
    }

    /**
     * {@code POST  /monthplans} : Create a new monthplan.
     *
     * @param monthplan the monthplan to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new monthplan, or with status {@code 400 (Bad Request)} if the monthplan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Monthplan> createMonthplan(@RequestBody Monthplan monthplan) throws URISyntaxException {
        log.debug("REST request to save Monthplan : {}", monthplan);
        if (monthplan.getId() != null) {
            throw new BadRequestAlertException("A new monthplan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        monthplan = monthplanRepository.save(monthplan);
        return ResponseEntity.created(new URI("/api/monthplans/" + monthplan.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, monthplan.getId()))
            .body(monthplan);
    }

    /**
     * {@code PUT  /monthplans/:id} : Updates an existing monthplan.
     *
     * @param id the id of the monthplan to save.
     * @param monthplan the monthplan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated monthplan,
     * or with status {@code 400 (Bad Request)} if the monthplan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the monthplan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Monthplan> updateMonthplan(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Monthplan monthplan
    ) throws URISyntaxException {
        log.debug("REST request to update Monthplan : {}, {}", id, monthplan);
        if (monthplan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, monthplan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!monthplanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        monthplan = monthplanRepository.save(monthplan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, monthplan.getId()))
            .body(monthplan);
    }

    /**
     * {@code PATCH  /monthplans/:id} : Partial updates given fields of an existing monthplan, field will ignore if it is null
     *
     * @param id the id of the monthplan to save.
     * @param monthplan the monthplan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated monthplan,
     * or with status {@code 400 (Bad Request)} if the monthplan is not valid,
     * or with status {@code 404 (Not Found)} if the monthplan is not found,
     * or with status {@code 500 (Internal Server Error)} if the monthplan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Monthplan> partialUpdateMonthplan(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Monthplan monthplan
    ) throws URISyntaxException {
        log.debug("REST request to partial update Monthplan partially : {}, {}", id, monthplan);
        if (monthplan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, monthplan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!monthplanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Monthplan> result = monthplanRepository
            .findById(monthplan.getId())
            .map(existingMonthplan -> {
                if (monthplan.getMonthplanname() != null) {
                    existingMonthplan.setMonthplanname(monthplan.getMonthplanname());
                }
                if (monthplan.getMonth() != null) {
                    existingMonthplan.setMonth(monthplan.getMonth());
                }
                if (monthplan.getSecretlevel() != null) {
                    existingMonthplan.setSecretlevel(monthplan.getSecretlevel());
                }
                if (monthplan.getCreatorname() != null) {
                    existingMonthplan.setCreatorname(monthplan.getCreatorname());
                }
                if (monthplan.getStatus() != null) {
                    existingMonthplan.setStatus(monthplan.getStatus());
                }
                if (monthplan.getAuditStatus() != null) {
                    existingMonthplan.setAuditStatus(monthplan.getAuditStatus());
                }

                return existingMonthplan;
            })
            .map(monthplanRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, monthplan.getId())
        );
    }

    /**
     * {@code GET  /monthplans} : get all the monthplans.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of monthplans in body.
     */
    @GetMapping("")
    public List<Monthplan> getAllMonthplans(@RequestParam(name = "filter", required = false) String filter) {
        if ("cycleplan-is-null".equals(filter)) {
            log.debug("REST request to get all Monthplans where cycleplan is null");
            return StreamSupport.stream(monthplanRepository.findAll().spliterator(), false)
                .filter(monthplan -> monthplan.getCycleplan() == null)
                .toList();
        }

        if ("annualplan-is-null".equals(filter)) {
            log.debug("REST request to get all Monthplans where annualplan is null");
            return StreamSupport.stream(monthplanRepository.findAll().spliterator(), false)
                .filter(monthplan -> monthplan.getAnnualplan() == null)
                .toList();
        }
        log.debug("REST request to get all Monthplans");
        return monthplanRepository.findAll();
    }

    /**
     * {@code GET  /monthplans/:id} : get the "id" monthplan.
     *
     * @param id the id of the monthplan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the monthplan, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Monthplan> getMonthplan(@PathVariable("id") String id) {
        log.debug("REST request to get Monthplan : {}", id);
        Optional<Monthplan> monthplan = monthplanRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(monthplan);
    }

    /**
     * {@code DELETE  /monthplans/:id} : delete the "id" monthplan.
     *
     * @param id the id of the monthplan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonthplan(@PathVariable("id") String id) {
        log.debug("REST request to delete Monthplan : {}", id);
        monthplanRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
