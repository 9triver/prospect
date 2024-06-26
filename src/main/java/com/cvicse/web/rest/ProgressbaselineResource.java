package com.cvicse.web.rest;

import com.cvicse.domain.Progressbaseline;
import com.cvicse.repository.ProgressbaselineRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Progressbaseline}.
 */
@RestController
@RequestMapping("/api/progressbaselines")
@Transactional
public class ProgressbaselineResource {

    private final Logger log = LoggerFactory.getLogger(ProgressbaselineResource.class);

    private static final String ENTITY_NAME = "progressbaseline";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProgressbaselineRepository progressbaselineRepository;

    public ProgressbaselineResource(ProgressbaselineRepository progressbaselineRepository) {
        this.progressbaselineRepository = progressbaselineRepository;
    }

    /**
     * {@code POST  /progressbaselines} : Create a new progressbaseline.
     *
     * @param progressbaseline the progressbaseline to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new progressbaseline, or with status {@code 400 (Bad Request)} if the progressbaseline has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Progressbaseline> createProgressbaseline(@RequestBody Progressbaseline progressbaseline)
        throws URISyntaxException {
        log.debug("REST request to save Progressbaseline : {}", progressbaseline);
        if (progressbaseline.getId() != null) {
            throw new BadRequestAlertException("A new progressbaseline cannot already have an ID", ENTITY_NAME, "idexists");
        }
        progressbaseline = progressbaselineRepository.save(progressbaseline);
        return ResponseEntity.created(new URI("/api/progressbaselines/" + progressbaseline.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, progressbaseline.getId()))
            .body(progressbaseline);
    }

    /**
     * {@code PUT  /progressbaselines/:id} : Updates an existing progressbaseline.
     *
     * @param id the id of the progressbaseline to save.
     * @param progressbaseline the progressbaseline to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated progressbaseline,
     * or with status {@code 400 (Bad Request)} if the progressbaseline is not valid,
     * or with status {@code 500 (Internal Server Error)} if the progressbaseline couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Progressbaseline> updateProgressbaseline(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Progressbaseline progressbaseline
    ) throws URISyntaxException {
        log.debug("REST request to update Progressbaseline : {}, {}", id, progressbaseline);
        if (progressbaseline.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, progressbaseline.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!progressbaselineRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        progressbaseline = progressbaselineRepository.save(progressbaseline);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, progressbaseline.getId()))
            .body(progressbaseline);
    }

    /**
     * {@code PATCH  /progressbaselines/:id} : Partial updates given fields of an existing progressbaseline, field will ignore if it is null
     *
     * @param id the id of the progressbaseline to save.
     * @param progressbaseline the progressbaseline to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated progressbaseline,
     * or with status {@code 400 (Bad Request)} if the progressbaseline is not valid,
     * or with status {@code 404 (Not Found)} if the progressbaseline is not found,
     * or with status {@code 500 (Internal Server Error)} if the progressbaseline couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Progressbaseline> partialUpdateProgressbaseline(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Progressbaseline progressbaseline
    ) throws URISyntaxException {
        log.debug("REST request to partial update Progressbaseline partially : {}, {}", id, progressbaseline);
        if (progressbaseline.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, progressbaseline.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!progressbaselineRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Progressbaseline> result = progressbaselineRepository
            .findById(progressbaseline.getId())
            .map(existingProgressbaseline -> {
                if (progressbaseline.getSecretlevel() != null) {
                    existingProgressbaseline.setSecretlevel(progressbaseline.getSecretlevel());
                }
                if (progressbaseline.getRequestdeportment() != null) {
                    existingProgressbaseline.setRequestdeportment(progressbaseline.getRequestdeportment());
                }
                if (progressbaseline.getChargetype() != null) {
                    existingProgressbaseline.setChargetype(progressbaseline.getChargetype());
                }
                if (progressbaseline.getChargecontent() != null) {
                    existingProgressbaseline.setChargecontent(progressbaseline.getChargecontent());
                }
                if (progressbaseline.getStatus() != null) {
                    existingProgressbaseline.setStatus(progressbaseline.getStatus());
                }
                if (progressbaseline.getVersion() != null) {
                    existingProgressbaseline.setVersion(progressbaseline.getVersion());
                }
                if (progressbaseline.getRemark() != null) {
                    existingProgressbaseline.setRemark(progressbaseline.getRemark());
                }

                return existingProgressbaseline;
            })
            .map(progressbaselineRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, progressbaseline.getId())
        );
    }

    /**
     * {@code GET  /progressbaselines} : get all the progressbaselines.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of progressbaselines in body.
     */
    @GetMapping("")
    public List<Progressbaseline> getAllProgressbaselines() {
        log.debug("REST request to get all Progressbaselines");
        return progressbaselineRepository.findAll();
    }

    /**
     * {@code GET  /progressbaselines/:id} : get the "id" progressbaseline.
     *
     * @param id the id of the progressbaseline to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the progressbaseline, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Progressbaseline> getProgressbaseline(@PathVariable("id") String id) {
        log.debug("REST request to get Progressbaseline : {}", id);
        Optional<Progressbaseline> progressbaseline = progressbaselineRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(progressbaseline);
    }

    /**
     * {@code DELETE  /progressbaselines/:id} : delete the "id" progressbaseline.
     *
     * @param id the id of the progressbaseline to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgressbaseline(@PathVariable("id") String id) {
        log.debug("REST request to delete Progressbaseline : {}", id);
        progressbaselineRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
