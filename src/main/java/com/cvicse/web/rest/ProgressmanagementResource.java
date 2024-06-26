package com.cvicse.web.rest;

import com.cvicse.domain.Progressmanagement;
import com.cvicse.repository.ProgressmanagementRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Progressmanagement}.
 */
@RestController
@RequestMapping("/api/progressmanagements")
@Transactional
public class ProgressmanagementResource {

    private final Logger log = LoggerFactory.getLogger(ProgressmanagementResource.class);

    private static final String ENTITY_NAME = "progressmanagement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProgressmanagementRepository progressmanagementRepository;

    public ProgressmanagementResource(ProgressmanagementRepository progressmanagementRepository) {
        this.progressmanagementRepository = progressmanagementRepository;
    }

    /**
     * {@code POST  /progressmanagements} : Create a new progressmanagement.
     *
     * @param progressmanagement the progressmanagement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new progressmanagement, or with status {@code 400 (Bad Request)} if the progressmanagement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Progressmanagement> createProgressmanagement(@RequestBody Progressmanagement progressmanagement)
        throws URISyntaxException {
        log.debug("REST request to save Progressmanagement : {}", progressmanagement);
        if (progressmanagement.getId() != null) {
            throw new BadRequestAlertException("A new progressmanagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        progressmanagement = progressmanagementRepository.save(progressmanagement);
        return ResponseEntity.created(new URI("/api/progressmanagements/" + progressmanagement.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, progressmanagement.getId()))
            .body(progressmanagement);
    }

    /**
     * {@code PUT  /progressmanagements/:id} : Updates an existing progressmanagement.
     *
     * @param id the id of the progressmanagement to save.
     * @param progressmanagement the progressmanagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated progressmanagement,
     * or with status {@code 400 (Bad Request)} if the progressmanagement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the progressmanagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Progressmanagement> updateProgressmanagement(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Progressmanagement progressmanagement
    ) throws URISyntaxException {
        log.debug("REST request to update Progressmanagement : {}, {}", id, progressmanagement);
        if (progressmanagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, progressmanagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!progressmanagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        progressmanagement = progressmanagementRepository.save(progressmanagement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, progressmanagement.getId()))
            .body(progressmanagement);
    }

    /**
     * {@code PATCH  /progressmanagements/:id} : Partial updates given fields of an existing progressmanagement, field will ignore if it is null
     *
     * @param id the id of the progressmanagement to save.
     * @param progressmanagement the progressmanagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated progressmanagement,
     * or with status {@code 400 (Bad Request)} if the progressmanagement is not valid,
     * or with status {@code 404 (Not Found)} if the progressmanagement is not found,
     * or with status {@code 500 (Internal Server Error)} if the progressmanagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Progressmanagement> partialUpdateProgressmanagement(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Progressmanagement progressmanagement
    ) throws URISyntaxException {
        log.debug("REST request to partial update Progressmanagement partially : {}, {}", id, progressmanagement);
        if (progressmanagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, progressmanagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!progressmanagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Progressmanagement> result = progressmanagementRepository
            .findById(progressmanagement.getId())
            .map(existingProgressmanagement -> {
                if (progressmanagement.getName() != null) {
                    existingProgressmanagement.setName(progressmanagement.getName());
                }
                if (progressmanagement.getDescription() != null) {
                    existingProgressmanagement.setDescription(progressmanagement.getDescription());
                }
                if (progressmanagement.getStarttime() != null) {
                    existingProgressmanagement.setStarttime(progressmanagement.getStarttime());
                }
                if (progressmanagement.getEndtime() != null) {
                    existingProgressmanagement.setEndtime(progressmanagement.getEndtime());
                }

                return existingProgressmanagement;
            })
            .map(progressmanagementRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, progressmanagement.getId())
        );
    }

    /**
     * {@code GET  /progressmanagements} : get all the progressmanagements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of progressmanagements in body.
     */
    @GetMapping("")
    public List<Progressmanagement> getAllProgressmanagements() {
        log.debug("REST request to get all Progressmanagements");
        return progressmanagementRepository.findAll();
    }

    /**
     * {@code GET  /progressmanagements/:id} : get the "id" progressmanagement.
     *
     * @param id the id of the progressmanagement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the progressmanagement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Progressmanagement> getProgressmanagement(@PathVariable("id") String id) {
        log.debug("REST request to get Progressmanagement : {}", id);
        Optional<Progressmanagement> progressmanagement = progressmanagementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(progressmanagement);
    }

    /**
     * {@code DELETE  /progressmanagements/:id} : delete the "id" progressmanagement.
     *
     * @param id the id of the progressmanagement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgressmanagement(@PathVariable("id") String id) {
        log.debug("REST request to delete Progressmanagement : {}", id);
        progressmanagementRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
