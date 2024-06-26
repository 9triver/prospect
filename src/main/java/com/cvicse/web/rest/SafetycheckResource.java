package com.cvicse.web.rest;

import com.cvicse.domain.Safetycheck;
import com.cvicse.repository.SafetycheckRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Safetycheck}.
 */
@RestController
@RequestMapping("/api/safetychecks")
@Transactional
public class SafetycheckResource {

    private final Logger log = LoggerFactory.getLogger(SafetycheckResource.class);

    private static final String ENTITY_NAME = "safetycheck";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SafetycheckRepository safetycheckRepository;

    public SafetycheckResource(SafetycheckRepository safetycheckRepository) {
        this.safetycheckRepository = safetycheckRepository;
    }

    /**
     * {@code POST  /safetychecks} : Create a new safetycheck.
     *
     * @param safetycheck the safetycheck to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new safetycheck, or with status {@code 400 (Bad Request)} if the safetycheck has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Safetycheck> createSafetycheck(@RequestBody Safetycheck safetycheck) throws URISyntaxException {
        log.debug("REST request to save Safetycheck : {}", safetycheck);
        if (safetycheck.getId() != null) {
            throw new BadRequestAlertException("A new safetycheck cannot already have an ID", ENTITY_NAME, "idexists");
        }
        safetycheck = safetycheckRepository.save(safetycheck);
        return ResponseEntity.created(new URI("/api/safetychecks/" + safetycheck.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, safetycheck.getId()))
            .body(safetycheck);
    }

    /**
     * {@code PUT  /safetychecks/:id} : Updates an existing safetycheck.
     *
     * @param id the id of the safetycheck to save.
     * @param safetycheck the safetycheck to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated safetycheck,
     * or with status {@code 400 (Bad Request)} if the safetycheck is not valid,
     * or with status {@code 500 (Internal Server Error)} if the safetycheck couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Safetycheck> updateSafetycheck(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Safetycheck safetycheck
    ) throws URISyntaxException {
        log.debug("REST request to update Safetycheck : {}, {}", id, safetycheck);
        if (safetycheck.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, safetycheck.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!safetycheckRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        safetycheck = safetycheckRepository.save(safetycheck);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, safetycheck.getId()))
            .body(safetycheck);
    }

    /**
     * {@code PATCH  /safetychecks/:id} : Partial updates given fields of an existing safetycheck, field will ignore if it is null
     *
     * @param id the id of the safetycheck to save.
     * @param safetycheck the safetycheck to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated safetycheck,
     * or with status {@code 400 (Bad Request)} if the safetycheck is not valid,
     * or with status {@code 404 (Not Found)} if the safetycheck is not found,
     * or with status {@code 500 (Internal Server Error)} if the safetycheck couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Safetycheck> partialUpdateSafetycheck(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Safetycheck safetycheck
    ) throws URISyntaxException {
        log.debug("REST request to partial update Safetycheck partially : {}, {}", id, safetycheck);
        if (safetycheck.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, safetycheck.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!safetycheckRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Safetycheck> result = safetycheckRepository
            .findById(safetycheck.getId())
            .map(existingSafetycheck -> {
                if (safetycheck.getSafetycheckname() != null) {
                    existingSafetycheck.setSafetycheckname(safetycheck.getSafetycheckname());
                }
                if (safetycheck.getChecksource() != null) {
                    existingSafetycheck.setChecksource(safetycheck.getChecksource());
                }
                if (safetycheck.getChecktime() != null) {
                    existingSafetycheck.setChecktime(safetycheck.getChecktime());
                }
                if (safetycheck.getEffectivetime() != null) {
                    existingSafetycheck.setEffectivetime(safetycheck.getEffectivetime());
                }
                if (safetycheck.getOperatinglocation() != null) {
                    existingSafetycheck.setOperatinglocation(safetycheck.getOperatinglocation());
                }
                if (safetycheck.getDeprotment() != null) {
                    existingSafetycheck.setDeprotment(safetycheck.getDeprotment());
                }
                if (safetycheck.getPhonenumber() != null) {
                    existingSafetycheck.setPhonenumber(safetycheck.getPhonenumber());
                }
                if (safetycheck.getRisklevel() != null) {
                    existingSafetycheck.setRisklevel(safetycheck.getRisklevel());
                }
                if (safetycheck.getAuditStatus() != null) {
                    existingSafetycheck.setAuditStatus(safetycheck.getAuditStatus());
                }

                return existingSafetycheck;
            })
            .map(safetycheckRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, safetycheck.getId())
        );
    }

    /**
     * {@code GET  /safetychecks} : get all the safetychecks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of safetychecks in body.
     */
    @GetMapping("")
    public List<Safetycheck> getAllSafetychecks() {
        log.debug("REST request to get all Safetychecks");
        return safetycheckRepository.findAll();
    }

    /**
     * {@code GET  /safetychecks/:id} : get the "id" safetycheck.
     *
     * @param id the id of the safetycheck to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the safetycheck, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Safetycheck> getSafetycheck(@PathVariable("id") String id) {
        log.debug("REST request to get Safetycheck : {}", id);
        Optional<Safetycheck> safetycheck = safetycheckRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(safetycheck);
    }

    /**
     * {@code DELETE  /safetychecks/:id} : delete the "id" safetycheck.
     *
     * @param id the id of the safetycheck to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSafetycheck(@PathVariable("id") String id) {
        log.debug("REST request to delete Safetycheck : {}", id);
        safetycheckRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
