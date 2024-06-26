package com.cvicse.web.rest;

import com.cvicse.domain.Securitymanagement;
import com.cvicse.repository.SecuritymanagementRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Securitymanagement}.
 */
@RestController
@RequestMapping("/api/securitymanagements")
@Transactional
public class SecuritymanagementResource {

    private final Logger log = LoggerFactory.getLogger(SecuritymanagementResource.class);

    private static final String ENTITY_NAME = "securitymanagement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SecuritymanagementRepository securitymanagementRepository;

    public SecuritymanagementResource(SecuritymanagementRepository securitymanagementRepository) {
        this.securitymanagementRepository = securitymanagementRepository;
    }

    /**
     * {@code POST  /securitymanagements} : Create a new securitymanagement.
     *
     * @param securitymanagement the securitymanagement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new securitymanagement, or with status {@code 400 (Bad Request)} if the securitymanagement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Securitymanagement> createSecuritymanagement(@RequestBody Securitymanagement securitymanagement)
        throws URISyntaxException {
        log.debug("REST request to save Securitymanagement : {}", securitymanagement);
        if (securitymanagement.getId() != null) {
            throw new BadRequestAlertException("A new securitymanagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        securitymanagement = securitymanagementRepository.save(securitymanagement);
        return ResponseEntity.created(new URI("/api/securitymanagements/" + securitymanagement.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, securitymanagement.getId()))
            .body(securitymanagement);
    }

    /**
     * {@code PUT  /securitymanagements/:id} : Updates an existing securitymanagement.
     *
     * @param id the id of the securitymanagement to save.
     * @param securitymanagement the securitymanagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated securitymanagement,
     * or with status {@code 400 (Bad Request)} if the securitymanagement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the securitymanagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Securitymanagement> updateSecuritymanagement(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Securitymanagement securitymanagement
    ) throws URISyntaxException {
        log.debug("REST request to update Securitymanagement : {}, {}", id, securitymanagement);
        if (securitymanagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, securitymanagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!securitymanagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        securitymanagement = securitymanagementRepository.save(securitymanagement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, securitymanagement.getId()))
            .body(securitymanagement);
    }

    /**
     * {@code PATCH  /securitymanagements/:id} : Partial updates given fields of an existing securitymanagement, field will ignore if it is null
     *
     * @param id the id of the securitymanagement to save.
     * @param securitymanagement the securitymanagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated securitymanagement,
     * or with status {@code 400 (Bad Request)} if the securitymanagement is not valid,
     * or with status {@code 404 (Not Found)} if the securitymanagement is not found,
     * or with status {@code 500 (Internal Server Error)} if the securitymanagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Securitymanagement> partialUpdateSecuritymanagement(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Securitymanagement securitymanagement
    ) throws URISyntaxException {
        log.debug("REST request to partial update Securitymanagement partially : {}, {}", id, securitymanagement);
        if (securitymanagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, securitymanagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!securitymanagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Securitymanagement> result = securitymanagementRepository
            .findById(securitymanagement.getId())
            .map(existingSecuritymanagement -> {
                if (securitymanagement.getName() != null) {
                    existingSecuritymanagement.setName(securitymanagement.getName());
                }
                if (securitymanagement.getDescription() != null) {
                    existingSecuritymanagement.setDescription(securitymanagement.getDescription());
                }
                if (securitymanagement.getStarttime() != null) {
                    existingSecuritymanagement.setStarttime(securitymanagement.getStarttime());
                }
                if (securitymanagement.getEndtime() != null) {
                    existingSecuritymanagement.setEndtime(securitymanagement.getEndtime());
                }

                return existingSecuritymanagement;
            })
            .map(securitymanagementRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, securitymanagement.getId())
        );
    }

    /**
     * {@code GET  /securitymanagements} : get all the securitymanagements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of securitymanagements in body.
     */
    @GetMapping("")
    public List<Securitymanagement> getAllSecuritymanagements() {
        log.debug("REST request to get all Securitymanagements");
        return securitymanagementRepository.findAll();
    }

    /**
     * {@code GET  /securitymanagements/:id} : get the "id" securitymanagement.
     *
     * @param id the id of the securitymanagement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the securitymanagement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Securitymanagement> getSecuritymanagement(@PathVariable("id") String id) {
        log.debug("REST request to get Securitymanagement : {}", id);
        Optional<Securitymanagement> securitymanagement = securitymanagementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(securitymanagement);
    }

    /**
     * {@code DELETE  /securitymanagements/:id} : delete the "id" securitymanagement.
     *
     * @param id the id of the securitymanagement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSecuritymanagement(@PathVariable("id") String id) {
        log.debug("REST request to delete Securitymanagement : {}", id);
        securitymanagementRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
