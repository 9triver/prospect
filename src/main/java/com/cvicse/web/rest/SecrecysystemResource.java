package com.cvicse.web.rest;

import com.cvicse.domain.Secrecysystem;
import com.cvicse.repository.SecrecysystemRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Secrecysystem}.
 */
@RestController
@RequestMapping("/api/secrecysystems")
@Transactional
public class SecrecysystemResource {

    private final Logger log = LoggerFactory.getLogger(SecrecysystemResource.class);

    private static final String ENTITY_NAME = "secrecysystem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SecrecysystemRepository secrecysystemRepository;

    public SecrecysystemResource(SecrecysystemRepository secrecysystemRepository) {
        this.secrecysystemRepository = secrecysystemRepository;
    }

    /**
     * {@code POST  /secrecysystems} : Create a new secrecysystem.
     *
     * @param secrecysystem the secrecysystem to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new secrecysystem, or with status {@code 400 (Bad Request)} if the secrecysystem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Secrecysystem> createSecrecysystem(@RequestBody Secrecysystem secrecysystem) throws URISyntaxException {
        log.debug("REST request to save Secrecysystem : {}", secrecysystem);
        if (secrecysystem.getId() != null) {
            throw new BadRequestAlertException("A new secrecysystem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        secrecysystem = secrecysystemRepository.save(secrecysystem);
        return ResponseEntity.created(new URI("/api/secrecysystems/" + secrecysystem.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, secrecysystem.getId()))
            .body(secrecysystem);
    }

    /**
     * {@code PUT  /secrecysystems/:id} : Updates an existing secrecysystem.
     *
     * @param id the id of the secrecysystem to save.
     * @param secrecysystem the secrecysystem to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated secrecysystem,
     * or with status {@code 400 (Bad Request)} if the secrecysystem is not valid,
     * or with status {@code 500 (Internal Server Error)} if the secrecysystem couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Secrecysystem> updateSecrecysystem(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Secrecysystem secrecysystem
    ) throws URISyntaxException {
        log.debug("REST request to update Secrecysystem : {}, {}", id, secrecysystem);
        if (secrecysystem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, secrecysystem.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!secrecysystemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        secrecysystem = secrecysystemRepository.save(secrecysystem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, secrecysystem.getId()))
            .body(secrecysystem);
    }

    /**
     * {@code PATCH  /secrecysystems/:id} : Partial updates given fields of an existing secrecysystem, field will ignore if it is null
     *
     * @param id the id of the secrecysystem to save.
     * @param secrecysystem the secrecysystem to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated secrecysystem,
     * or with status {@code 400 (Bad Request)} if the secrecysystem is not valid,
     * or with status {@code 404 (Not Found)} if the secrecysystem is not found,
     * or with status {@code 500 (Internal Server Error)} if the secrecysystem couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Secrecysystem> partialUpdateSecrecysystem(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Secrecysystem secrecysystem
    ) throws URISyntaxException {
        log.debug("REST request to partial update Secrecysystem partially : {}, {}", id, secrecysystem);
        if (secrecysystem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, secrecysystem.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!secrecysystemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Secrecysystem> result = secrecysystemRepository
            .findById(secrecysystem.getId())
            .map(existingSecrecysystem -> {
                if (secrecysystem.getPublishedby() != null) {
                    existingSecrecysystem.setPublishedby(secrecysystem.getPublishedby());
                }
                if (secrecysystem.getDocumentname() != null) {
                    existingSecrecysystem.setDocumentname(secrecysystem.getDocumentname());
                }
                if (secrecysystem.getDocumenttype() != null) {
                    existingSecrecysystem.setDocumenttype(secrecysystem.getDocumenttype());
                }
                if (secrecysystem.getDocumentsize() != null) {
                    existingSecrecysystem.setDocumentsize(secrecysystem.getDocumentsize());
                }
                if (secrecysystem.getSecretlevel() != null) {
                    existingSecrecysystem.setSecretlevel(secrecysystem.getSecretlevel());
                }
                if (secrecysystem.getAuditStatus() != null) {
                    existingSecrecysystem.setAuditStatus(secrecysystem.getAuditStatus());
                }

                return existingSecrecysystem;
            })
            .map(secrecysystemRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, secrecysystem.getId())
        );
    }

    /**
     * {@code GET  /secrecysystems} : get all the secrecysystems.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of secrecysystems in body.
     */
    @GetMapping("")
    public List<Secrecysystem> getAllSecrecysystems(@RequestParam(name = "filter", required = false) String filter) {
        if ("projectsecrecy-is-null".equals(filter)) {
            log.debug("REST request to get all Secrecysystems where projectSecrecy is null");
            return StreamSupport.stream(secrecysystemRepository.findAll().spliterator(), false)
                .filter(secrecysystem -> secrecysystem.getProjectSecrecy() == null)
                .toList();
        }
        log.debug("REST request to get all Secrecysystems");
        return secrecysystemRepository.findAll();
    }

    /**
     * {@code GET  /secrecysystems/:id} : get the "id" secrecysystem.
     *
     * @param id the id of the secrecysystem to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the secrecysystem, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Secrecysystem> getSecrecysystem(@PathVariable("id") String id) {
        log.debug("REST request to get Secrecysystem : {}", id);
        Optional<Secrecysystem> secrecysystem = secrecysystemRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(secrecysystem);
    }

    /**
     * {@code DELETE  /secrecysystems/:id} : delete the "id" secrecysystem.
     *
     * @param id the id of the secrecysystem to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSecrecysystem(@PathVariable("id") String id) {
        log.debug("REST request to delete Secrecysystem : {}", id);
        secrecysystemRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
