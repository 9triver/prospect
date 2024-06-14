package com.cvicse.web.rest;

import com.cvicse.domain.Wbsbaseline;
import com.cvicse.repository.WbsbaselineRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Wbsbaseline}.
 */
@RestController
@RequestMapping("/api/wbsbaselines")
@Transactional
public class WbsbaselineResource {

    private final Logger log = LoggerFactory.getLogger(WbsbaselineResource.class);

    private static final String ENTITY_NAME = "wbsbaseline";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WbsbaselineRepository wbsbaselineRepository;

    public WbsbaselineResource(WbsbaselineRepository wbsbaselineRepository) {
        this.wbsbaselineRepository = wbsbaselineRepository;
    }

    /**
     * {@code POST  /wbsbaselines} : Create a new wbsbaseline.
     *
     * @param wbsbaseline the wbsbaseline to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new wbsbaseline, or with status {@code 400 (Bad Request)} if the wbsbaseline has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Wbsbaseline> createWbsbaseline(@RequestBody Wbsbaseline wbsbaseline) throws URISyntaxException {
        log.debug("REST request to save Wbsbaseline : {}", wbsbaseline);
        if (wbsbaseline.getId() != null) {
            throw new BadRequestAlertException("A new wbsbaseline cannot already have an ID", ENTITY_NAME, "idexists");
        }
        wbsbaseline = wbsbaselineRepository.save(wbsbaseline);
        return ResponseEntity.created(new URI("/api/wbsbaselines/" + wbsbaseline.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, wbsbaseline.getId().toString()))
            .body(wbsbaseline);
    }

    /**
     * {@code PUT  /wbsbaselines/:id} : Updates an existing wbsbaseline.
     *
     * @param id the id of the wbsbaseline to save.
     * @param wbsbaseline the wbsbaseline to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wbsbaseline,
     * or with status {@code 400 (Bad Request)} if the wbsbaseline is not valid,
     * or with status {@code 500 (Internal Server Error)} if the wbsbaseline couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Wbsbaseline> updateWbsbaseline(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Wbsbaseline wbsbaseline
    ) throws URISyntaxException {
        log.debug("REST request to update Wbsbaseline : {}, {}", id, wbsbaseline);
        if (wbsbaseline.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, wbsbaseline.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!wbsbaselineRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        wbsbaseline = wbsbaselineRepository.save(wbsbaseline);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, wbsbaseline.getId().toString()))
            .body(wbsbaseline);
    }

    /**
     * {@code PATCH  /wbsbaselines/:id} : Partial updates given fields of an existing wbsbaseline, field will ignore if it is null
     *
     * @param id the id of the wbsbaseline to save.
     * @param wbsbaseline the wbsbaseline to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wbsbaseline,
     * or with status {@code 400 (Bad Request)} if the wbsbaseline is not valid,
     * or with status {@code 404 (Not Found)} if the wbsbaseline is not found,
     * or with status {@code 500 (Internal Server Error)} if the wbsbaseline couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Wbsbaseline> partialUpdateWbsbaseline(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Wbsbaseline wbsbaseline
    ) throws URISyntaxException {
        log.debug("REST request to partial update Wbsbaseline partially : {}, {}", id, wbsbaseline);
        if (wbsbaseline.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, wbsbaseline.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!wbsbaselineRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Wbsbaseline> result = wbsbaselineRepository
            .findById(wbsbaseline.getId())
            .map(existingWbsbaseline -> {
                if (wbsbaseline.getFormid() != null) {
                    existingWbsbaseline.setFormid(wbsbaseline.getFormid());
                }
                if (wbsbaseline.getSecretlevel() != null) {
                    existingWbsbaseline.setSecretlevel(wbsbaseline.getSecretlevel());
                }
                if (wbsbaseline.getRequestdeportment() != null) {
                    existingWbsbaseline.setRequestdeportment(wbsbaseline.getRequestdeportment());
                }
                if (wbsbaseline.getChargetype() != null) {
                    existingWbsbaseline.setChargetype(wbsbaseline.getChargetype());
                }
                if (wbsbaseline.getChargecontent() != null) {
                    existingWbsbaseline.setChargecontent(wbsbaseline.getChargecontent());
                }
                if (wbsbaseline.getStatus() != null) {
                    existingWbsbaseline.setStatus(wbsbaseline.getStatus());
                }
                if (wbsbaseline.getVersion() != null) {
                    existingWbsbaseline.setVersion(wbsbaseline.getVersion());
                }
                if (wbsbaseline.getRemark() != null) {
                    existingWbsbaseline.setRemark(wbsbaseline.getRemark());
                }

                return existingWbsbaseline;
            })
            .map(wbsbaselineRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, wbsbaseline.getId().toString())
        );
    }

    /**
     * {@code GET  /wbsbaselines} : get all the wbsbaselines.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of wbsbaselines in body.
     */
    @GetMapping("")
    public List<Wbsbaseline> getAllWbsbaselines() {
        log.debug("REST request to get all Wbsbaselines");
        return wbsbaselineRepository.findAll();
    }

    /**
     * {@code GET  /wbsbaselines/:id} : get the "id" wbsbaseline.
     *
     * @param id the id of the wbsbaseline to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wbsbaseline, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Wbsbaseline> getWbsbaseline(@PathVariable("id") Long id) {
        log.debug("REST request to get Wbsbaseline : {}", id);
        Optional<Wbsbaseline> wbsbaseline = wbsbaselineRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(wbsbaseline);
    }

    /**
     * {@code DELETE  /wbsbaselines/:id} : delete the "id" wbsbaseline.
     *
     * @param id the id of the wbsbaseline to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWbsbaseline(@PathVariable("id") Long id) {
        log.debug("REST request to delete Wbsbaseline : {}", id);
        wbsbaselineRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
