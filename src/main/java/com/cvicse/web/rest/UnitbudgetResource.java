package com.cvicse.web.rest;

import com.cvicse.domain.Unitbudget;
import com.cvicse.repository.UnitbudgetRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Unitbudget}.
 */
@RestController
@RequestMapping("/api/unitbudgets")
@Transactional
public class UnitbudgetResource {

    private final Logger log = LoggerFactory.getLogger(UnitbudgetResource.class);

    private static final String ENTITY_NAME = "unitbudget";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UnitbudgetRepository unitbudgetRepository;

    public UnitbudgetResource(UnitbudgetRepository unitbudgetRepository) {
        this.unitbudgetRepository = unitbudgetRepository;
    }

    /**
     * {@code POST  /unitbudgets} : Create a new unitbudget.
     *
     * @param unitbudget the unitbudget to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new unitbudget, or with status {@code 400 (Bad Request)} if the unitbudget has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Unitbudget> createUnitbudget(@RequestBody Unitbudget unitbudget) throws URISyntaxException {
        log.debug("REST request to save Unitbudget : {}", unitbudget);
        if (unitbudget.getId() != null) {
            throw new BadRequestAlertException("A new unitbudget cannot already have an ID", ENTITY_NAME, "idexists");
        }
        unitbudget = unitbudgetRepository.save(unitbudget);
        return ResponseEntity.created(new URI("/api/unitbudgets/" + unitbudget.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, unitbudget.getId().toString()))
            .body(unitbudget);
    }

    /**
     * {@code PUT  /unitbudgets/:id} : Updates an existing unitbudget.
     *
     * @param id the id of the unitbudget to save.
     * @param unitbudget the unitbudget to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated unitbudget,
     * or with status {@code 400 (Bad Request)} if the unitbudget is not valid,
     * or with status {@code 500 (Internal Server Error)} if the unitbudget couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Unitbudget> updateUnitbudget(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Unitbudget unitbudget
    ) throws URISyntaxException {
        log.debug("REST request to update Unitbudget : {}, {}", id, unitbudget);
        if (unitbudget.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, unitbudget.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!unitbudgetRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        unitbudget = unitbudgetRepository.save(unitbudget);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, unitbudget.getId().toString()))
            .body(unitbudget);
    }

    /**
     * {@code PATCH  /unitbudgets/:id} : Partial updates given fields of an existing unitbudget, field will ignore if it is null
     *
     * @param id the id of the unitbudget to save.
     * @param unitbudget the unitbudget to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated unitbudget,
     * or with status {@code 400 (Bad Request)} if the unitbudget is not valid,
     * or with status {@code 404 (Not Found)} if the unitbudget is not found,
     * or with status {@code 500 (Internal Server Error)} if the unitbudget couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Unitbudget> partialUpdateUnitbudget(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Unitbudget unitbudget
    ) throws URISyntaxException {
        log.debug("REST request to partial update Unitbudget partially : {}, {}", id, unitbudget);
        if (unitbudget.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, unitbudget.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!unitbudgetRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Unitbudget> result = unitbudgetRepository
            .findById(unitbudget.getId())
            .map(existingUnitbudget -> {
                if (unitbudget.getUnitbudgetid() != null) {
                    existingUnitbudget.setUnitbudgetid(unitbudget.getUnitbudgetid());
                }
                if (unitbudget.getSubprojectname() != null) {
                    existingUnitbudget.setSubprojectname(unitbudget.getSubprojectname());
                }
                if (unitbudget.getUnitbudgername() != null) {
                    existingUnitbudget.setUnitbudgername(unitbudget.getUnitbudgername());
                }
                if (unitbudget.getBillingunit() != null) {
                    existingUnitbudget.setBillingunit(unitbudget.getBillingunit());
                }
                if (unitbudget.getNumber() != null) {
                    existingUnitbudget.setNumber(unitbudget.getNumber());
                }
                if (unitbudget.getTotalbudget() != null) {
                    existingUnitbudget.setTotalbudget(unitbudget.getTotalbudget());
                }
                if (unitbudget.getMaintainerbudget() != null) {
                    existingUnitbudget.setMaintainerbudget(unitbudget.getMaintainerbudget());
                }
                if (unitbudget.getOutsourcingbudget() != null) {
                    existingUnitbudget.setOutsourcingbudget(unitbudget.getOutsourcingbudget());
                }
                if (unitbudget.getEarmarkedbudget() != null) {
                    existingUnitbudget.setEarmarkedbudget(unitbudget.getEarmarkedbudget());
                }
                if (unitbudget.getTestbudget() != null) {
                    existingUnitbudget.setTestbudget(unitbudget.getTestbudget());
                }

                return existingUnitbudget;
            })
            .map(unitbudgetRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, unitbudget.getId().toString())
        );
    }

    /**
     * {@code GET  /unitbudgets} : get all the unitbudgets.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of unitbudgets in body.
     */
    @GetMapping("")
    public List<Unitbudget> getAllUnitbudgets(@RequestParam(name = "filter", required = false) String filter) {
        if ("comprehensivecontrol-is-null".equals(filter)) {
            log.debug("REST request to get all Unitbudgets where comprehensivecontrol is null");
            return StreamSupport.stream(unitbudgetRepository.findAll().spliterator(), false)
                .filter(unitbudget -> unitbudget.getComprehensivecontrol() == null)
                .toList();
        }

        if ("fundsmanagement-is-null".equals(filter)) {
            log.debug("REST request to get all Unitbudgets where fundsmanagement is null");
            return StreamSupport.stream(unitbudgetRepository.findAll().spliterator(), false)
                .filter(unitbudget -> unitbudget.getFundsmanagement() == null)
                .toList();
        }
        log.debug("REST request to get all Unitbudgets");
        return unitbudgetRepository.findAll();
    }

    /**
     * {@code GET  /unitbudgets/:id} : get the "id" unitbudget.
     *
     * @param id the id of the unitbudget to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the unitbudget, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Unitbudget> getUnitbudget(@PathVariable("id") Long id) {
        log.debug("REST request to get Unitbudget : {}", id);
        Optional<Unitbudget> unitbudget = unitbudgetRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(unitbudget);
    }

    /**
     * {@code DELETE  /unitbudgets/:id} : delete the "id" unitbudget.
     *
     * @param id the id of the unitbudget to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnitbudget(@PathVariable("id") Long id) {
        log.debug("REST request to delete Unitbudget : {}", id);
        unitbudgetRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
