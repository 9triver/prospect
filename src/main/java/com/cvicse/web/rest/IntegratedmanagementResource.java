package com.cvicse.web.rest;

import com.cvicse.domain.Integratedmanagement;
import com.cvicse.repository.IntegratedmanagementRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Integratedmanagement}.
 */
@RestController
@RequestMapping("/api/integratedmanagements")
@Transactional
public class IntegratedmanagementResource {

    private final Logger log = LoggerFactory.getLogger(IntegratedmanagementResource.class);

    private static final String ENTITY_NAME = "integratedmanagement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IntegratedmanagementRepository integratedmanagementRepository;

    public IntegratedmanagementResource(IntegratedmanagementRepository integratedmanagementRepository) {
        this.integratedmanagementRepository = integratedmanagementRepository;
    }

    /**
     * {@code POST  /integratedmanagements} : Create a new integratedmanagement.
     *
     * @param integratedmanagement the integratedmanagement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new integratedmanagement, or with status {@code 400 (Bad Request)} if the integratedmanagement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Integratedmanagement> createIntegratedmanagement(@RequestBody Integratedmanagement integratedmanagement)
        throws URISyntaxException {
        log.debug("REST request to save Integratedmanagement : {}", integratedmanagement);
        if (integratedmanagement.getId() != null) {
            throw new BadRequestAlertException("A new integratedmanagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        integratedmanagement = integratedmanagementRepository.save(integratedmanagement);
        return ResponseEntity.created(new URI("/api/integratedmanagements/" + integratedmanagement.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, integratedmanagement.getId().toString()))
            .body(integratedmanagement);
    }

    /**
     * {@code PUT  /integratedmanagements/:id} : Updates an existing integratedmanagement.
     *
     * @param id the id of the integratedmanagement to save.
     * @param integratedmanagement the integratedmanagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated integratedmanagement,
     * or with status {@code 400 (Bad Request)} if the integratedmanagement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the integratedmanagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Integratedmanagement> updateIntegratedmanagement(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Integratedmanagement integratedmanagement
    ) throws URISyntaxException {
        log.debug("REST request to update Integratedmanagement : {}, {}", id, integratedmanagement);
        if (integratedmanagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, integratedmanagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!integratedmanagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        integratedmanagement = integratedmanagementRepository.save(integratedmanagement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, integratedmanagement.getId().toString()))
            .body(integratedmanagement);
    }

    /**
     * {@code PATCH  /integratedmanagements/:id} : Partial updates given fields of an existing integratedmanagement, field will ignore if it is null
     *
     * @param id the id of the integratedmanagement to save.
     * @param integratedmanagement the integratedmanagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated integratedmanagement,
     * or with status {@code 400 (Bad Request)} if the integratedmanagement is not valid,
     * or with status {@code 404 (Not Found)} if the integratedmanagement is not found,
     * or with status {@code 500 (Internal Server Error)} if the integratedmanagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Integratedmanagement> partialUpdateIntegratedmanagement(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Integratedmanagement integratedmanagement
    ) throws URISyntaxException {
        log.debug("REST request to partial update Integratedmanagement partially : {}, {}", id, integratedmanagement);
        if (integratedmanagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, integratedmanagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!integratedmanagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Integratedmanagement> result = integratedmanagementRepository
            .findById(integratedmanagement.getId())
            .map(existingIntegratedmanagement -> {
                if (integratedmanagement.getName() != null) {
                    existingIntegratedmanagement.setName(integratedmanagement.getName());
                }
                if (integratedmanagement.getDescription() != null) {
                    existingIntegratedmanagement.setDescription(integratedmanagement.getDescription());
                }

                return existingIntegratedmanagement;
            })
            .map(integratedmanagementRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, integratedmanagement.getId().toString())
        );
    }

    /**
     * {@code GET  /integratedmanagements} : get all the integratedmanagements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of integratedmanagements in body.
     */
    @GetMapping("")
    public List<Integratedmanagement> getAllIntegratedmanagements() {
        log.debug("REST request to get all Integratedmanagements");
        return integratedmanagementRepository.findAll();
    }

    /**
     * {@code GET  /integratedmanagements/:id} : get the "id" integratedmanagement.
     *
     * @param id the id of the integratedmanagement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the integratedmanagement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Integratedmanagement> getIntegratedmanagement(@PathVariable("id") Long id) {
        log.debug("REST request to get Integratedmanagement : {}", id);
        Optional<Integratedmanagement> integratedmanagement = integratedmanagementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(integratedmanagement);
    }

    /**
     * {@code DELETE  /integratedmanagements/:id} : delete the "id" integratedmanagement.
     *
     * @param id the id of the integratedmanagement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIntegratedmanagement(@PathVariable("id") Long id) {
        log.debug("REST request to delete Integratedmanagement : {}", id);
        integratedmanagementRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
