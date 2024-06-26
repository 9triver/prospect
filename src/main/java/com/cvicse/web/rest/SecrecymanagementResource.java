package com.cvicse.web.rest;

import com.cvicse.domain.Secrecymanagement;
import com.cvicse.repository.SecrecymanagementRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Secrecymanagement}.
 */
@RestController
@RequestMapping("/api/secrecymanagements")
@Transactional
public class SecrecymanagementResource {

    private final Logger log = LoggerFactory.getLogger(SecrecymanagementResource.class);

    private static final String ENTITY_NAME = "secrecymanagement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SecrecymanagementRepository secrecymanagementRepository;

    public SecrecymanagementResource(SecrecymanagementRepository secrecymanagementRepository) {
        this.secrecymanagementRepository = secrecymanagementRepository;
    }

    /**
     * {@code POST  /secrecymanagements} : Create a new secrecymanagement.
     *
     * @param secrecymanagement the secrecymanagement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new secrecymanagement, or with status {@code 400 (Bad Request)} if the secrecymanagement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Secrecymanagement> createSecrecymanagement(@RequestBody Secrecymanagement secrecymanagement)
        throws URISyntaxException {
        log.debug("REST request to save Secrecymanagement : {}", secrecymanagement);
        if (secrecymanagement.getId() != null) {
            throw new BadRequestAlertException("A new secrecymanagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        secrecymanagement = secrecymanagementRepository.save(secrecymanagement);
        return ResponseEntity.created(new URI("/api/secrecymanagements/" + secrecymanagement.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, secrecymanagement.getId()))
            .body(secrecymanagement);
    }

    /**
     * {@code PUT  /secrecymanagements/:id} : Updates an existing secrecymanagement.
     *
     * @param id the id of the secrecymanagement to save.
     * @param secrecymanagement the secrecymanagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated secrecymanagement,
     * or with status {@code 400 (Bad Request)} if the secrecymanagement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the secrecymanagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Secrecymanagement> updateSecrecymanagement(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Secrecymanagement secrecymanagement
    ) throws URISyntaxException {
        log.debug("REST request to update Secrecymanagement : {}, {}", id, secrecymanagement);
        if (secrecymanagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, secrecymanagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!secrecymanagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        secrecymanagement = secrecymanagementRepository.save(secrecymanagement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, secrecymanagement.getId()))
            .body(secrecymanagement);
    }

    /**
     * {@code PATCH  /secrecymanagements/:id} : Partial updates given fields of an existing secrecymanagement, field will ignore if it is null
     *
     * @param id the id of the secrecymanagement to save.
     * @param secrecymanagement the secrecymanagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated secrecymanagement,
     * or with status {@code 400 (Bad Request)} if the secrecymanagement is not valid,
     * or with status {@code 404 (Not Found)} if the secrecymanagement is not found,
     * or with status {@code 500 (Internal Server Error)} if the secrecymanagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Secrecymanagement> partialUpdateSecrecymanagement(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Secrecymanagement secrecymanagement
    ) throws URISyntaxException {
        log.debug("REST request to partial update Secrecymanagement partially : {}, {}", id, secrecymanagement);
        if (secrecymanagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, secrecymanagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!secrecymanagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Secrecymanagement> result = secrecymanagementRepository
            .findById(secrecymanagement.getId())
            .map(existingSecrecymanagement -> {
                if (secrecymanagement.getName() != null) {
                    existingSecrecymanagement.setName(secrecymanagement.getName());
                }
                if (secrecymanagement.getDescription() != null) {
                    existingSecrecymanagement.setDescription(secrecymanagement.getDescription());
                }
                if (secrecymanagement.getStarttime() != null) {
                    existingSecrecymanagement.setStarttime(secrecymanagement.getStarttime());
                }
                if (secrecymanagement.getEndtime() != null) {
                    existingSecrecymanagement.setEndtime(secrecymanagement.getEndtime());
                }

                return existingSecrecymanagement;
            })
            .map(secrecymanagementRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, secrecymanagement.getId())
        );
    }

    /**
     * {@code GET  /secrecymanagements} : get all the secrecymanagements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of secrecymanagements in body.
     */
    @GetMapping("")
    public List<Secrecymanagement> getAllSecrecymanagements() {
        log.debug("REST request to get all Secrecymanagements");
        return secrecymanagementRepository.findAll();
    }

    /**
     * {@code GET  /secrecymanagements/:id} : get the "id" secrecymanagement.
     *
     * @param id the id of the secrecymanagement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the secrecymanagement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Secrecymanagement> getSecrecymanagement(@PathVariable("id") String id) {
        log.debug("REST request to get Secrecymanagement : {}", id);
        Optional<Secrecymanagement> secrecymanagement = secrecymanagementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(secrecymanagement);
    }

    /**
     * {@code DELETE  /secrecymanagements/:id} : delete the "id" secrecymanagement.
     *
     * @param id the id of the secrecymanagement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSecrecymanagement(@PathVariable("id") String id) {
        log.debug("REST request to delete Secrecymanagement : {}", id);
        secrecymanagementRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
