package com.cvicse.web.rest;

import com.cvicse.domain.Totalbudget;
import com.cvicse.repository.TotalbudgetRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Totalbudget}.
 */
@RestController
@RequestMapping("/api/totalbudgets")
@Transactional
public class TotalbudgetResource {

    private final Logger log = LoggerFactory.getLogger(TotalbudgetResource.class);

    private static final String ENTITY_NAME = "totalbudget";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TotalbudgetRepository totalbudgetRepository;

    public TotalbudgetResource(TotalbudgetRepository totalbudgetRepository) {
        this.totalbudgetRepository = totalbudgetRepository;
    }

    /**
     * {@code POST  /totalbudgets} : Create a new totalbudget.
     *
     * @param totalbudget the totalbudget to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new totalbudget, or with status {@code 400 (Bad Request)} if the totalbudget has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Totalbudget> createTotalbudget(@RequestBody Totalbudget totalbudget) throws URISyntaxException {
        log.debug("REST request to save Totalbudget : {}", totalbudget);
        if (totalbudget.getId() != null) {
            throw new BadRequestAlertException("A new totalbudget cannot already have an ID", ENTITY_NAME, "idexists");
        }
        totalbudget = totalbudgetRepository.save(totalbudget);
        return ResponseEntity.created(new URI("/api/totalbudgets/" + totalbudget.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, totalbudget.getId()))
            .body(totalbudget);
    }

    /**
     * {@code PUT  /totalbudgets/:id} : Updates an existing totalbudget.
     *
     * @param id the id of the totalbudget to save.
     * @param totalbudget the totalbudget to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated totalbudget,
     * or with status {@code 400 (Bad Request)} if the totalbudget is not valid,
     * or with status {@code 500 (Internal Server Error)} if the totalbudget couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Totalbudget> updateTotalbudget(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Totalbudget totalbudget
    ) throws URISyntaxException {
        log.debug("REST request to update Totalbudget : {}, {}", id, totalbudget);
        if (totalbudget.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, totalbudget.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!totalbudgetRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        totalbudget = totalbudgetRepository.save(totalbudget);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, totalbudget.getId()))
            .body(totalbudget);
    }

    /**
     * {@code PATCH  /totalbudgets/:id} : Partial updates given fields of an existing totalbudget, field will ignore if it is null
     *
     * @param id the id of the totalbudget to save.
     * @param totalbudget the totalbudget to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated totalbudget,
     * or with status {@code 400 (Bad Request)} if the totalbudget is not valid,
     * or with status {@code 404 (Not Found)} if the totalbudget is not found,
     * or with status {@code 500 (Internal Server Error)} if the totalbudget couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Totalbudget> partialUpdateTotalbudget(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Totalbudget totalbudget
    ) throws URISyntaxException {
        log.debug("REST request to partial update Totalbudget partially : {}, {}", id, totalbudget);
        if (totalbudget.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, totalbudget.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!totalbudgetRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Totalbudget> result = totalbudgetRepository
            .findById(totalbudget.getId())
            .map(existingTotalbudget -> {
                if (totalbudget.getValuationsubjects() != null) {
                    existingTotalbudget.setValuationsubjects(totalbudget.getValuationsubjects());
                }
                if (totalbudget.getBudget() != null) {
                    existingTotalbudget.setBudget(totalbudget.getBudget());
                }
                if (totalbudget.getPercentage() != null) {
                    existingTotalbudget.setPercentage(totalbudget.getPercentage());
                }
                if (totalbudget.getRemarks() != null) {
                    existingTotalbudget.setRemarks(totalbudget.getRemarks());
                }

                return existingTotalbudget;
            })
            .map(totalbudgetRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, totalbudget.getId())
        );
    }

    /**
     * {@code GET  /totalbudgets} : get all the totalbudgets.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of totalbudgets in body.
     */
    @GetMapping("")
    public List<Totalbudget> getAllTotalbudgets(@RequestParam(name = "filter", required = false) String filter) {
        if ("comprehensivecontrol-is-null".equals(filter)) {
            log.debug("REST request to get all Totalbudgets where comprehensivecontrol is null");
            return StreamSupport.stream(totalbudgetRepository.findAll().spliterator(), false)
                .filter(totalbudget -> totalbudget.getComprehensivecontrol() == null)
                .toList();
        }

        if ("auditedbudget-is-null".equals(filter)) {
            log.debug("REST request to get all Totalbudgets where auditedbudget is null");
            return StreamSupport.stream(totalbudgetRepository.findAll().spliterator(), false)
                .filter(totalbudget -> totalbudget.getAuditedbudget() == null)
                .toList();
        }
        log.debug("REST request to get all Totalbudgets");
        return totalbudgetRepository.findAll();
    }

    /**
     * {@code GET  /totalbudgets/:id} : get the "id" totalbudget.
     *
     * @param id the id of the totalbudget to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the totalbudget, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Totalbudget> getTotalbudget(@PathVariable("id") String id) {
        log.debug("REST request to get Totalbudget : {}", id);
        Optional<Totalbudget> totalbudget = totalbudgetRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(totalbudget);
    }

    /**
     * {@code DELETE  /totalbudgets/:id} : delete the "id" totalbudget.
     *
     * @param id the id of the totalbudget to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTotalbudget(@PathVariable("id") String id) {
        log.debug("REST request to delete Totalbudget : {}", id);
        totalbudgetRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
