package com.cvicse.web.rest;

import com.cvicse.domain.Fundsavailability;
import com.cvicse.repository.FundsavailabilityRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Fundsavailability}.
 */
@RestController
@RequestMapping("/api/fundsavailabilities")
@Transactional
public class FundsavailabilityResource {

    private final Logger log = LoggerFactory.getLogger(FundsavailabilityResource.class);

    private static final String ENTITY_NAME = "fundsavailability";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FundsavailabilityRepository fundsavailabilityRepository;

    public FundsavailabilityResource(FundsavailabilityRepository fundsavailabilityRepository) {
        this.fundsavailabilityRepository = fundsavailabilityRepository;
    }

    /**
     * {@code POST  /fundsavailabilities} : Create a new fundsavailability.
     *
     * @param fundsavailability the fundsavailability to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fundsavailability, or with status {@code 400 (Bad Request)} if the fundsavailability has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Fundsavailability> createFundsavailability(@RequestBody Fundsavailability fundsavailability)
        throws URISyntaxException {
        log.debug("REST request to save Fundsavailability : {}", fundsavailability);
        if (fundsavailability.getId() != null) {
            throw new BadRequestAlertException("A new fundsavailability cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fundsavailability = fundsavailabilityRepository.save(fundsavailability);
        return ResponseEntity.created(new URI("/api/fundsavailabilities/" + fundsavailability.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, fundsavailability.getId()))
            .body(fundsavailability);
    }

    /**
     * {@code PUT  /fundsavailabilities/:id} : Updates an existing fundsavailability.
     *
     * @param id the id of the fundsavailability to save.
     * @param fundsavailability the fundsavailability to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fundsavailability,
     * or with status {@code 400 (Bad Request)} if the fundsavailability is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fundsavailability couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Fundsavailability> updateFundsavailability(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Fundsavailability fundsavailability
    ) throws URISyntaxException {
        log.debug("REST request to update Fundsavailability : {}, {}", id, fundsavailability);
        if (fundsavailability.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fundsavailability.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fundsavailabilityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        fundsavailability = fundsavailabilityRepository.save(fundsavailability);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fundsavailability.getId()))
            .body(fundsavailability);
    }

    /**
     * {@code PATCH  /fundsavailabilities/:id} : Partial updates given fields of an existing fundsavailability, field will ignore if it is null
     *
     * @param id the id of the fundsavailability to save.
     * @param fundsavailability the fundsavailability to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fundsavailability,
     * or with status {@code 400 (Bad Request)} if the fundsavailability is not valid,
     * or with status {@code 404 (Not Found)} if the fundsavailability is not found,
     * or with status {@code 500 (Internal Server Error)} if the fundsavailability couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Fundsavailability> partialUpdateFundsavailability(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Fundsavailability fundsavailability
    ) throws URISyntaxException {
        log.debug("REST request to partial update Fundsavailability partially : {}, {}", id, fundsavailability);
        if (fundsavailability.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fundsavailability.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fundsavailabilityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Fundsavailability> result = fundsavailabilityRepository
            .findById(fundsavailability.getId())
            .map(existingFundsavailability -> {
                if (fundsavailability.getFundsid() != null) {
                    existingFundsavailability.setFundsid(fundsavailability.getFundsid());
                }
                if (fundsavailability.getYear() != null) {
                    existingFundsavailability.setYear(fundsavailability.getYear());
                }
                if (fundsavailability.getBudgit() != null) {
                    existingFundsavailability.setBudgit(fundsavailability.getBudgit());
                }
                if (fundsavailability.getFunding() != null) {
                    existingFundsavailability.setFunding(fundsavailability.getFunding());
                }

                return existingFundsavailability;
            })
            .map(fundsavailabilityRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fundsavailability.getId())
        );
    }

    /**
     * {@code GET  /fundsavailabilities} : get all the fundsavailabilities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fundsavailabilities in body.
     */
    @GetMapping("")
    public List<Fundsavailability> getAllFundsavailabilities() {
        log.debug("REST request to get all Fundsavailabilities");
        return fundsavailabilityRepository.findAll();
    }

    /**
     * {@code GET  /fundsavailabilities/:id} : get the "id" fundsavailability.
     *
     * @param id the id of the fundsavailability to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fundsavailability, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Fundsavailability> getFundsavailability(@PathVariable("id") String id) {
        log.debug("REST request to get Fundsavailability : {}", id);
        Optional<Fundsavailability> fundsavailability = fundsavailabilityRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fundsavailability);
    }

    /**
     * {@code DELETE  /fundsavailabilities/:id} : delete the "id" fundsavailability.
     *
     * @param id the id of the fundsavailability to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFundsavailability(@PathVariable("id") String id) {
        log.debug("REST request to delete Fundsavailability : {}", id);
        fundsavailabilityRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
