package com.cvicse.web.rest;

import com.cvicse.domain.UnQualityAudit;
import com.cvicse.repository.UnQualityAuditRepository;
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
 * REST controller for managing {@link com.cvicse.domain.UnQualityAudit}.
 */
@RestController
@RequestMapping("/api/un-quality-audits")
@Transactional
public class UnQualityAuditResource {

    private final Logger log = LoggerFactory.getLogger(UnQualityAuditResource.class);

    private static final String ENTITY_NAME = "unQualityAudit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UnQualityAuditRepository unQualityAuditRepository;

    public UnQualityAuditResource(UnQualityAuditRepository unQualityAuditRepository) {
        this.unQualityAuditRepository = unQualityAuditRepository;
    }

    /**
     * {@code POST  /un-quality-audits} : Create a new unQualityAudit.
     *
     * @param unQualityAudit the unQualityAudit to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new unQualityAudit, or with status {@code 400 (Bad Request)} if the unQualityAudit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<UnQualityAudit> createUnQualityAudit(@RequestBody UnQualityAudit unQualityAudit) throws URISyntaxException {
        log.debug("REST request to save UnQualityAudit : {}", unQualityAudit);
        if (unQualityAudit.getId() != null) {
            throw new BadRequestAlertException("A new unQualityAudit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        unQualityAudit = unQualityAuditRepository.save(unQualityAudit);
        return ResponseEntity.created(new URI("/api/un-quality-audits/" + unQualityAudit.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, unQualityAudit.getId()))
            .body(unQualityAudit);
    }

    /**
     * {@code PUT  /un-quality-audits/:id} : Updates an existing unQualityAudit.
     *
     * @param id the id of the unQualityAudit to save.
     * @param unQualityAudit the unQualityAudit to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated unQualityAudit,
     * or with status {@code 400 (Bad Request)} if the unQualityAudit is not valid,
     * or with status {@code 500 (Internal Server Error)} if the unQualityAudit couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UnQualityAudit> updateUnQualityAudit(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody UnQualityAudit unQualityAudit
    ) throws URISyntaxException {
        log.debug("REST request to update UnQualityAudit : {}, {}", id, unQualityAudit);
        if (unQualityAudit.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, unQualityAudit.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!unQualityAuditRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        unQualityAudit = unQualityAuditRepository.save(unQualityAudit);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, unQualityAudit.getId()))
            .body(unQualityAudit);
    }

    /**
     * {@code PATCH  /un-quality-audits/:id} : Partial updates given fields of an existing unQualityAudit, field will ignore if it is null
     *
     * @param id the id of the unQualityAudit to save.
     * @param unQualityAudit the unQualityAudit to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated unQualityAudit,
     * or with status {@code 400 (Bad Request)} if the unQualityAudit is not valid,
     * or with status {@code 404 (Not Found)} if the unQualityAudit is not found,
     * or with status {@code 500 (Internal Server Error)} if the unQualityAudit couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UnQualityAudit> partialUpdateUnQualityAudit(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody UnQualityAudit unQualityAudit
    ) throws URISyntaxException {
        log.debug("REST request to partial update UnQualityAudit partially : {}, {}", id, unQualityAudit);
        if (unQualityAudit.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, unQualityAudit.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!unQualityAuditRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UnQualityAudit> result = unQualityAuditRepository
            .findById(unQualityAudit.getId())
            .map(existingUnQualityAudit -> {
                if (unQualityAudit.getUnqualityname() != null) {
                    existingUnQualityAudit.setUnqualityname(unQualityAudit.getUnqualityname());
                }
                if (unQualityAudit.getUnqualitytype() != null) {
                    existingUnQualityAudit.setUnqualitytype(unQualityAudit.getUnqualitytype());
                }
                if (unQualityAudit.getBelongunitid() != null) {
                    existingUnQualityAudit.setBelongunitid(unQualityAudit.getBelongunitid());
                }
                if (unQualityAudit.getBelongunitname() != null) {
                    existingUnQualityAudit.setBelongunitname(unQualityAudit.getBelongunitname());
                }
                if (unQualityAudit.getAuditteam() != null) {
                    existingUnQualityAudit.setAuditteam(unQualityAudit.getAuditteam());
                }
                if (unQualityAudit.getAuditperson() != null) {
                    existingUnQualityAudit.setAuditperson(unQualityAudit.getAuditperson());
                }
                if (unQualityAudit.getUnqualitynum() != null) {
                    existingUnQualityAudit.setUnqualitynum(unQualityAudit.getUnqualitynum());
                }
                if (unQualityAudit.getCreatorname() != null) {
                    existingUnQualityAudit.setCreatorname(unQualityAudit.getCreatorname());
                }
                if (unQualityAudit.getAuditStatus() != null) {
                    existingUnQualityAudit.setAuditStatus(unQualityAudit.getAuditStatus());
                }

                return existingUnQualityAudit;
            })
            .map(unQualityAuditRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, unQualityAudit.getId())
        );
    }

    /**
     * {@code GET  /un-quality-audits} : get all the unQualityAudits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of unQualityAudits in body.
     */
    @GetMapping("")
    public List<UnQualityAudit> getAllUnQualityAudits() {
        log.debug("REST request to get all UnQualityAudits");
        return unQualityAuditRepository.findAll();
    }

    /**
     * {@code GET  /un-quality-audits/:id} : get the "id" unQualityAudit.
     *
     * @param id the id of the unQualityAudit to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the unQualityAudit, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UnQualityAudit> getUnQualityAudit(@PathVariable("id") String id) {
        log.debug("REST request to get UnQualityAudit : {}", id);
        Optional<UnQualityAudit> unQualityAudit = unQualityAuditRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(unQualityAudit);
    }

    /**
     * {@code DELETE  /un-quality-audits/:id} : delete the "id" unQualityAudit.
     *
     * @param id the id of the unQualityAudit to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnQualityAudit(@PathVariable("id") String id) {
        log.debug("REST request to delete UnQualityAudit : {}", id);
        unQualityAuditRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
