package com.cvicse.web.rest;

import com.cvicse.domain.Comprehensiveledger;
import com.cvicse.repository.ComprehensiveledgerRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Comprehensiveledger}.
 */
@RestController
@RequestMapping("/api/comprehensiveledgers")
@Transactional
public class ComprehensiveledgerResource {

    private final Logger log = LoggerFactory.getLogger(ComprehensiveledgerResource.class);

    private static final String ENTITY_NAME = "comprehensiveledger";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ComprehensiveledgerRepository comprehensiveledgerRepository;

    public ComprehensiveledgerResource(ComprehensiveledgerRepository comprehensiveledgerRepository) {
        this.comprehensiveledgerRepository = comprehensiveledgerRepository;
    }

    /**
     * {@code POST  /comprehensiveledgers} : Create a new comprehensiveledger.
     *
     * @param comprehensiveledger the comprehensiveledger to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new comprehensiveledger, or with status {@code 400 (Bad Request)} if the comprehensiveledger has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Comprehensiveledger> createComprehensiveledger(@RequestBody Comprehensiveledger comprehensiveledger)
        throws URISyntaxException {
        log.debug("REST request to save Comprehensiveledger : {}", comprehensiveledger);
        if (comprehensiveledger.getId() != null) {
            throw new BadRequestAlertException("A new comprehensiveledger cannot already have an ID", ENTITY_NAME, "idexists");
        }
        comprehensiveledger = comprehensiveledgerRepository.save(comprehensiveledger);
        return ResponseEntity.created(new URI("/api/comprehensiveledgers/" + comprehensiveledger.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, comprehensiveledger.getId()))
            .body(comprehensiveledger);
    }

    /**
     * {@code PUT  /comprehensiveledgers/:id} : Updates an existing comprehensiveledger.
     *
     * @param id the id of the comprehensiveledger to save.
     * @param comprehensiveledger the comprehensiveledger to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated comprehensiveledger,
     * or with status {@code 400 (Bad Request)} if the comprehensiveledger is not valid,
     * or with status {@code 500 (Internal Server Error)} if the comprehensiveledger couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Comprehensiveledger> updateComprehensiveledger(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Comprehensiveledger comprehensiveledger
    ) throws URISyntaxException {
        log.debug("REST request to update Comprehensiveledger : {}, {}", id, comprehensiveledger);
        if (comprehensiveledger.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, comprehensiveledger.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!comprehensiveledgerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        comprehensiveledger = comprehensiveledgerRepository.save(comprehensiveledger);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, comprehensiveledger.getId()))
            .body(comprehensiveledger);
    }

    /**
     * {@code PATCH  /comprehensiveledgers/:id} : Partial updates given fields of an existing comprehensiveledger, field will ignore if it is null
     *
     * @param id the id of the comprehensiveledger to save.
     * @param comprehensiveledger the comprehensiveledger to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated comprehensiveledger,
     * or with status {@code 400 (Bad Request)} if the comprehensiveledger is not valid,
     * or with status {@code 404 (Not Found)} if the comprehensiveledger is not found,
     * or with status {@code 500 (Internal Server Error)} if the comprehensiveledger couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Comprehensiveledger> partialUpdateComprehensiveledger(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Comprehensiveledger comprehensiveledger
    ) throws URISyntaxException {
        log.debug("REST request to partial update Comprehensiveledger partially : {}, {}", id, comprehensiveledger);
        if (comprehensiveledger.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, comprehensiveledger.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!comprehensiveledgerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Comprehensiveledger> result = comprehensiveledgerRepository
            .findById(comprehensiveledger.getId())
            .map(existingComprehensiveledger -> {
                if (comprehensiveledger.getFundsname() != null) {
                    existingComprehensiveledger.setFundsname(comprehensiveledger.getFundsname());
                }
                if (comprehensiveledger.getWbsname() != null) {
                    existingComprehensiveledger.setWbsname(comprehensiveledger.getWbsname());
                }
                if (comprehensiveledger.getUnitname() != null) {
                    existingComprehensiveledger.setUnitname(comprehensiveledger.getUnitname());
                }
                if (comprehensiveledger.getBudgetsection() != null) {
                    existingComprehensiveledger.setBudgetsection(comprehensiveledger.getBudgetsection());
                }
                if (comprehensiveledger.getPurpose() != null) {
                    existingComprehensiveledger.setPurpose(comprehensiveledger.getPurpose());
                }
                if (comprehensiveledger.getUnit() != null) {
                    existingComprehensiveledger.setUnit(comprehensiveledger.getUnit());
                }
                if (comprehensiveledger.getNumber() != null) {
                    existingComprehensiveledger.setNumber(comprehensiveledger.getNumber());
                }
                if (comprehensiveledger.getUnitprice() != null) {
                    existingComprehensiveledger.setUnitprice(comprehensiveledger.getUnitprice());
                }
                if (comprehensiveledger.getForeignexchange() != null) {
                    existingComprehensiveledger.setForeignexchange(comprehensiveledger.getForeignexchange());
                }

                return existingComprehensiveledger;
            })
            .map(comprehensiveledgerRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, comprehensiveledger.getId())
        );
    }

    /**
     * {@code GET  /comprehensiveledgers} : get all the comprehensiveledgers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of comprehensiveledgers in body.
     */
    @GetMapping("")
    public List<Comprehensiveledger> getAllComprehensiveledgers() {
        log.debug("REST request to get all Comprehensiveledgers");
        return comprehensiveledgerRepository.findAll();
    }

    /**
     * {@code GET  /comprehensiveledgers/:id} : get the "id" comprehensiveledger.
     *
     * @param id the id of the comprehensiveledger to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the comprehensiveledger, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Comprehensiveledger> getComprehensiveledger(@PathVariable("id") String id) {
        log.debug("REST request to get Comprehensiveledger : {}", id);
        Optional<Comprehensiveledger> comprehensiveledger = comprehensiveledgerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(comprehensiveledger);
    }

    /**
     * {@code DELETE  /comprehensiveledgers/:id} : delete the "id" comprehensiveledger.
     *
     * @param id the id of the comprehensiveledger to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComprehensiveledger(@PathVariable("id") String id) {
        log.debug("REST request to delete Comprehensiveledger : {}", id);
        comprehensiveledgerRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
