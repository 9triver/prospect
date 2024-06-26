package com.cvicse.web.rest;

import com.cvicse.domain.Auditedbudget;
import com.cvicse.repository.AuditedbudgetRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Auditedbudget}.
 */
@RestController
@RequestMapping("/api/auditedbudgets")
@Transactional
public class AuditedbudgetResource {

    private final Logger log = LoggerFactory.getLogger(AuditedbudgetResource.class);

    private static final String ENTITY_NAME = "auditedbudget";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AuditedbudgetRepository auditedbudgetRepository;

    public AuditedbudgetResource(AuditedbudgetRepository auditedbudgetRepository) {
        this.auditedbudgetRepository = auditedbudgetRepository;
    }

    /**
     * {@code POST  /auditedbudgets} : Create a new auditedbudget.
     *
     * @param auditedbudget the auditedbudget to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new auditedbudget, or with status {@code 400 (Bad Request)} if the auditedbudget has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Auditedbudget> createAuditedbudget(@RequestBody Auditedbudget auditedbudget) throws URISyntaxException {
        log.debug("REST request to save Auditedbudget : {}", auditedbudget);
        if (auditedbudget.getId() != null) {
            throw new BadRequestAlertException("A new auditedbudget cannot already have an ID", ENTITY_NAME, "idexists");
        }
        auditedbudget = auditedbudgetRepository.save(auditedbudget);
        return ResponseEntity.created(new URI("/api/auditedbudgets/" + auditedbudget.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, auditedbudget.getId()))
            .body(auditedbudget);
    }

    /**
     * {@code PUT  /auditedbudgets/:id} : Updates an existing auditedbudget.
     *
     * @param id the id of the auditedbudget to save.
     * @param auditedbudget the auditedbudget to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated auditedbudget,
     * or with status {@code 400 (Bad Request)} if the auditedbudget is not valid,
     * or with status {@code 500 (Internal Server Error)} if the auditedbudget couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Auditedbudget> updateAuditedbudget(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Auditedbudget auditedbudget
    ) throws URISyntaxException {
        log.debug("REST request to update Auditedbudget : {}, {}", id, auditedbudget);
        if (auditedbudget.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, auditedbudget.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!auditedbudgetRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        auditedbudget = auditedbudgetRepository.save(auditedbudget);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, auditedbudget.getId()))
            .body(auditedbudget);
    }

    /**
     * {@code PATCH  /auditedbudgets/:id} : Partial updates given fields of an existing auditedbudget, field will ignore if it is null
     *
     * @param id the id of the auditedbudget to save.
     * @param auditedbudget the auditedbudget to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated auditedbudget,
     * or with status {@code 400 (Bad Request)} if the auditedbudget is not valid,
     * or with status {@code 404 (Not Found)} if the auditedbudget is not found,
     * or with status {@code 500 (Internal Server Error)} if the auditedbudget couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Auditedbudget> partialUpdateAuditedbudget(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Auditedbudget auditedbudget
    ) throws URISyntaxException {
        log.debug("REST request to partial update Auditedbudget partially : {}, {}", id, auditedbudget);
        if (auditedbudget.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, auditedbudget.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!auditedbudgetRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Auditedbudget> result = auditedbudgetRepository
            .findById(auditedbudget.getId())
            .map(existingAuditedbudget -> {
                if (auditedbudget.getCreatetime() != null) {
                    existingAuditedbudget.setCreatetime(auditedbudget.getCreatetime());
                }
                if (auditedbudget.getCreatorname() != null) {
                    existingAuditedbudget.setCreatorname(auditedbudget.getCreatorname());
                }
                if (auditedbudget.getSecretlevel() != null) {
                    existingAuditedbudget.setSecretlevel(auditedbudget.getSecretlevel());
                }
                if (auditedbudget.getYear() != null) {
                    existingAuditedbudget.setYear(auditedbudget.getYear());
                }
                if (auditedbudget.getBudgit() != null) {
                    existingAuditedbudget.setBudgit(auditedbudget.getBudgit());
                }
                if (auditedbudget.getDapartmentid() != null) {
                    existingAuditedbudget.setDapartmentid(auditedbudget.getDapartmentid());
                }
                if (auditedbudget.getDraftapproval() != null) {
                    existingAuditedbudget.setDraftapproval(auditedbudget.getDraftapproval());
                }
                if (auditedbudget.getTotalbudgetid() != null) {
                    existingAuditedbudget.setTotalbudgetid(auditedbudget.getTotalbudgetid());
                }
                if (auditedbudget.getUnitbudgetid() != null) {
                    existingAuditedbudget.setUnitbudgetid(auditedbudget.getUnitbudgetid());
                }
                if (auditedbudget.getDocumentid() != null) {
                    existingAuditedbudget.setDocumentid(auditedbudget.getDocumentid());
                }
                if (auditedbudget.getMaintainerid() != null) {
                    existingAuditedbudget.setMaintainerid(auditedbudget.getMaintainerid());
                }
                if (auditedbudget.getAuditStatus() != null) {
                    existingAuditedbudget.setAuditStatus(auditedbudget.getAuditStatus());
                }

                return existingAuditedbudget;
            })
            .map(auditedbudgetRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, auditedbudget.getId())
        );
    }

    /**
     * {@code GET  /auditedbudgets} : get all the auditedbudgets.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of auditedbudgets in body.
     */
    @GetMapping("")
    public List<Auditedbudget> getAllAuditedbudgets(@RequestParam(name = "filter", required = false) String filter) {
        if ("fundsavailability-is-null".equals(filter)) {
            log.debug("REST request to get all Auditedbudgets where fundsavailability is null");
            return StreamSupport.stream(auditedbudgetRepository.findAll().spliterator(), false)
                .filter(auditedbudget -> auditedbudget.getFundsavailability() == null)
                .toList();
        }
        log.debug("REST request to get all Auditedbudgets");
        return auditedbudgetRepository.findAll();
    }

    /**
     * {@code GET  /auditedbudgets/:id} : get the "id" auditedbudget.
     *
     * @param id the id of the auditedbudget to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the auditedbudget, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Auditedbudget> getAuditedbudget(@PathVariable("id") String id) {
        log.debug("REST request to get Auditedbudget : {}", id);
        Optional<Auditedbudget> auditedbudget = auditedbudgetRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(auditedbudget);
    }

    /**
     * {@code DELETE  /auditedbudgets/:id} : delete the "id" auditedbudget.
     *
     * @param id the id of the auditedbudget to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuditedbudget(@PathVariable("id") String id) {
        log.debug("REST request to delete Auditedbudget : {}", id);
        auditedbudgetRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
