package com.cvicse.web.rest;

import com.cvicse.domain.Wbssubmanage;
import com.cvicse.repository.WbssubmanageRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Wbssubmanage}.
 */
@RestController
@RequestMapping("/api/wbssubmanages")
@Transactional
public class WbssubmanageResource {

    private final Logger log = LoggerFactory.getLogger(WbssubmanageResource.class);

    private static final String ENTITY_NAME = "wbssubmanage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WbssubmanageRepository wbssubmanageRepository;

    public WbssubmanageResource(WbssubmanageRepository wbssubmanageRepository) {
        this.wbssubmanageRepository = wbssubmanageRepository;
    }

    /**
     * {@code POST  /wbssubmanages} : Create a new wbssubmanage.
     *
     * @param wbssubmanage the wbssubmanage to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new wbssubmanage, or with status {@code 400 (Bad Request)} if the wbssubmanage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Wbssubmanage> createWbssubmanage(@RequestBody Wbssubmanage wbssubmanage) throws URISyntaxException {
        log.debug("REST request to save Wbssubmanage : {}", wbssubmanage);
        if (wbssubmanage.getId() != null) {
            throw new BadRequestAlertException("A new wbssubmanage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        wbssubmanage = wbssubmanageRepository.save(wbssubmanage);
        return ResponseEntity.created(new URI("/api/wbssubmanages/" + wbssubmanage.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, wbssubmanage.getId().toString()))
            .body(wbssubmanage);
    }

    /**
     * {@code PUT  /wbssubmanages/:id} : Updates an existing wbssubmanage.
     *
     * @param id the id of the wbssubmanage to save.
     * @param wbssubmanage the wbssubmanage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wbssubmanage,
     * or with status {@code 400 (Bad Request)} if the wbssubmanage is not valid,
     * or with status {@code 500 (Internal Server Error)} if the wbssubmanage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Wbssubmanage> updateWbssubmanage(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Wbssubmanage wbssubmanage
    ) throws URISyntaxException {
        log.debug("REST request to update Wbssubmanage : {}, {}", id, wbssubmanage);
        if (wbssubmanage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, wbssubmanage.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!wbssubmanageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        wbssubmanage = wbssubmanageRepository.save(wbssubmanage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, wbssubmanage.getId().toString()))
            .body(wbssubmanage);
    }

    /**
     * {@code PATCH  /wbssubmanages/:id} : Partial updates given fields of an existing wbssubmanage, field will ignore if it is null
     *
     * @param id the id of the wbssubmanage to save.
     * @param wbssubmanage the wbssubmanage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wbssubmanage,
     * or with status {@code 400 (Bad Request)} if the wbssubmanage is not valid,
     * or with status {@code 404 (Not Found)} if the wbssubmanage is not found,
     * or with status {@code 500 (Internal Server Error)} if the wbssubmanage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Wbssubmanage> partialUpdateWbssubmanage(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Wbssubmanage wbssubmanage
    ) throws URISyntaxException {
        log.debug("REST request to partial update Wbssubmanage partially : {}, {}", id, wbssubmanage);
        if (wbssubmanage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, wbssubmanage.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!wbssubmanageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Wbssubmanage> result = wbssubmanageRepository
            .findById(wbssubmanage.getId())
            .map(existingWbssubmanage -> {
                if (wbssubmanage.getPbssubid() != null) {
                    existingWbssubmanage.setPbssubid(wbssubmanage.getPbssubid());
                }
                if (wbssubmanage.getPbssubname() != null) {
                    existingWbssubmanage.setPbssubname(wbssubmanage.getPbssubname());
                }
                if (wbssubmanage.getResponsiblename() != null) {
                    existingWbssubmanage.setResponsiblename(wbssubmanage.getResponsiblename());
                }
                if (wbssubmanage.getResponsibledepartment() != null) {
                    existingWbssubmanage.setResponsibledepartment(wbssubmanage.getResponsibledepartment());
                }
                if (wbssubmanage.getRelevantdepartment() != null) {
                    existingWbssubmanage.setRelevantdepartment(wbssubmanage.getRelevantdepartment());
                }
                if (wbssubmanage.getType() != null) {
                    existingWbssubmanage.setType(wbssubmanage.getType());
                }
                if (wbssubmanage.getStarttime() != null) {
                    existingWbssubmanage.setStarttime(wbssubmanage.getStarttime());
                }
                if (wbssubmanage.getEndtime() != null) {
                    existingWbssubmanage.setEndtime(wbssubmanage.getEndtime());
                }
                if (wbssubmanage.getSecretlevel() != null) {
                    existingWbssubmanage.setSecretlevel(wbssubmanage.getSecretlevel());
                }
                if (wbssubmanage.getAuditStatus() != null) {
                    existingWbssubmanage.setAuditStatus(wbssubmanage.getAuditStatus());
                }

                return existingWbssubmanage;
            })
            .map(wbssubmanageRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, wbssubmanage.getId().toString())
        );
    }

    /**
     * {@code GET  /wbssubmanages} : get all the wbssubmanages.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of wbssubmanages in body.
     */
    @GetMapping("")
    public List<Wbssubmanage> getAllWbssubmanages(@RequestParam(name = "filter", required = false) String filter) {
        if ("wbsmanage-is-null".equals(filter)) {
            log.debug("REST request to get all Wbssubmanages where wbsmanage is null");
            return StreamSupport.stream(wbssubmanageRepository.findAll().spliterator(), false)
                .filter(wbssubmanage -> wbssubmanage.getWbsmanage() == null)
                .toList();
        }
        log.debug("REST request to get all Wbssubmanages");
        return wbssubmanageRepository.findAll();
    }

    /**
     * {@code GET  /wbssubmanages/:id} : get the "id" wbssubmanage.
     *
     * @param id the id of the wbssubmanage to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wbssubmanage, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Wbssubmanage> getWbssubmanage(@PathVariable("id") Long id) {
        log.debug("REST request to get Wbssubmanage : {}", id);
        Optional<Wbssubmanage> wbssubmanage = wbssubmanageRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(wbssubmanage);
    }

    /**
     * {@code DELETE  /wbssubmanages/:id} : delete the "id" wbssubmanage.
     *
     * @param id the id of the wbssubmanage to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWbssubmanage(@PathVariable("id") Long id) {
        log.debug("REST request to delete Wbssubmanage : {}", id);
        wbssubmanageRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
