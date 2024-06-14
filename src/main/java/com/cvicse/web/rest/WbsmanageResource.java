package com.cvicse.web.rest;

import com.cvicse.domain.Wbsmanage;
import com.cvicse.repository.WbsmanageRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Wbsmanage}.
 */
@RestController
@RequestMapping("/api/wbsmanages")
@Transactional
public class WbsmanageResource {

    private final Logger log = LoggerFactory.getLogger(WbsmanageResource.class);

    private static final String ENTITY_NAME = "wbsmanage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WbsmanageRepository wbsmanageRepository;

    public WbsmanageResource(WbsmanageRepository wbsmanageRepository) {
        this.wbsmanageRepository = wbsmanageRepository;
    }

    /**
     * {@code POST  /wbsmanages} : Create a new wbsmanage.
     *
     * @param wbsmanage the wbsmanage to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new wbsmanage, or with status {@code 400 (Bad Request)} if the wbsmanage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Wbsmanage> createWbsmanage(@RequestBody Wbsmanage wbsmanage) throws URISyntaxException {
        log.debug("REST request to save Wbsmanage : {}", wbsmanage);
        if (wbsmanage.getId() != null) {
            throw new BadRequestAlertException("A new wbsmanage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        wbsmanage = wbsmanageRepository.save(wbsmanage);
        return ResponseEntity.created(new URI("/api/wbsmanages/" + wbsmanage.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, wbsmanage.getId().toString()))
            .body(wbsmanage);
    }

    /**
     * {@code PUT  /wbsmanages/:id} : Updates an existing wbsmanage.
     *
     * @param id the id of the wbsmanage to save.
     * @param wbsmanage the wbsmanage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wbsmanage,
     * or with status {@code 400 (Bad Request)} if the wbsmanage is not valid,
     * or with status {@code 500 (Internal Server Error)} if the wbsmanage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Wbsmanage> updateWbsmanage(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Wbsmanage wbsmanage
    ) throws URISyntaxException {
        log.debug("REST request to update Wbsmanage : {}, {}", id, wbsmanage);
        if (wbsmanage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, wbsmanage.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!wbsmanageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        wbsmanage = wbsmanageRepository.save(wbsmanage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, wbsmanage.getId().toString()))
            .body(wbsmanage);
    }

    /**
     * {@code PATCH  /wbsmanages/:id} : Partial updates given fields of an existing wbsmanage, field will ignore if it is null
     *
     * @param id the id of the wbsmanage to save.
     * @param wbsmanage the wbsmanage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wbsmanage,
     * or with status {@code 400 (Bad Request)} if the wbsmanage is not valid,
     * or with status {@code 404 (Not Found)} if the wbsmanage is not found,
     * or with status {@code 500 (Internal Server Error)} if the wbsmanage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Wbsmanage> partialUpdateWbsmanage(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Wbsmanage wbsmanage
    ) throws URISyntaxException {
        log.debug("REST request to partial update Wbsmanage partially : {}, {}", id, wbsmanage);
        if (wbsmanage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, wbsmanage.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!wbsmanageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Wbsmanage> result = wbsmanageRepository
            .findById(wbsmanage.getId())
            .map(existingWbsmanage -> {
                if (wbsmanage.getWbsid() != null) {
                    existingWbsmanage.setWbsid(wbsmanage.getWbsid());
                }
                if (wbsmanage.getWbsname() != null) {
                    existingWbsmanage.setWbsname(wbsmanage.getWbsname());
                }
                if (wbsmanage.getDescription() != null) {
                    existingWbsmanage.setDescription(wbsmanage.getDescription());
                }
                if (wbsmanage.getResult() != null) {
                    existingWbsmanage.setResult(wbsmanage.getResult());
                }
                if (wbsmanage.getAdministratorname() != null) {
                    existingWbsmanage.setAdministratorname(wbsmanage.getAdministratorname());
                }
                if (wbsmanage.getResponsiblename() != null) {
                    existingWbsmanage.setResponsiblename(wbsmanage.getResponsiblename());
                }
                if (wbsmanage.getDepartment() != null) {
                    existingWbsmanage.setDepartment(wbsmanage.getDepartment());
                }
                if (wbsmanage.getSecretlevel() != null) {
                    existingWbsmanage.setSecretlevel(wbsmanage.getSecretlevel());
                }
                if (wbsmanage.getAuditStatus() != null) {
                    existingWbsmanage.setAuditStatus(wbsmanage.getAuditStatus());
                }

                return existingWbsmanage;
            })
            .map(wbsmanageRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, wbsmanage.getId().toString())
        );
    }

    /**
     * {@code GET  /wbsmanages} : get all the wbsmanages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of wbsmanages in body.
     */
    @GetMapping("")
    public List<Wbsmanage> getAllWbsmanages() {
        log.debug("REST request to get all Wbsmanages");
        return wbsmanageRepository.findAll();
    }

    /**
     * {@code GET  /wbsmanages/:id} : get the "id" wbsmanage.
     *
     * @param id the id of the wbsmanage to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wbsmanage, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Wbsmanage> getWbsmanage(@PathVariable("id") Long id) {
        log.debug("REST request to get Wbsmanage : {}", id);
        Optional<Wbsmanage> wbsmanage = wbsmanageRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(wbsmanage);
    }

    /**
     * {@code DELETE  /wbsmanages/:id} : delete the "id" wbsmanage.
     *
     * @param id the id of the wbsmanage to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWbsmanage(@PathVariable("id") Long id) {
        log.debug("REST request to delete Wbsmanage : {}", id);
        wbsmanageRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
