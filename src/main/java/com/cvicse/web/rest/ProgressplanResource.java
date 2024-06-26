package com.cvicse.web.rest;

import com.cvicse.domain.Progressplan;
import com.cvicse.repository.ProgressplanRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Progressplan}.
 */
@RestController
@RequestMapping("/api/progressplans")
@Transactional
public class ProgressplanResource {

    private final Logger log = LoggerFactory.getLogger(ProgressplanResource.class);

    private static final String ENTITY_NAME = "progressplan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProgressplanRepository progressplanRepository;

    public ProgressplanResource(ProgressplanRepository progressplanRepository) {
        this.progressplanRepository = progressplanRepository;
    }

    /**
     * {@code POST  /progressplans} : Create a new progressplan.
     *
     * @param progressplan the progressplan to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new progressplan, or with status {@code 400 (Bad Request)} if the progressplan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Progressplan> createProgressplan(@RequestBody Progressplan progressplan) throws URISyntaxException {
        log.debug("REST request to save Progressplan : {}", progressplan);
        if (progressplan.getId() != null) {
            throw new BadRequestAlertException("A new progressplan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        progressplan = progressplanRepository.save(progressplan);
        return ResponseEntity.created(new URI("/api/progressplans/" + progressplan.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, progressplan.getId()))
            .body(progressplan);
    }

    /**
     * {@code PUT  /progressplans/:id} : Updates an existing progressplan.
     *
     * @param id the id of the progressplan to save.
     * @param progressplan the progressplan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated progressplan,
     * or with status {@code 400 (Bad Request)} if the progressplan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the progressplan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Progressplan> updateProgressplan(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Progressplan progressplan
    ) throws URISyntaxException {
        log.debug("REST request to update Progressplan : {}, {}", id, progressplan);
        if (progressplan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, progressplan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!progressplanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        progressplan = progressplanRepository.save(progressplan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, progressplan.getId()))
            .body(progressplan);
    }

    /**
     * {@code PATCH  /progressplans/:id} : Partial updates given fields of an existing progressplan, field will ignore if it is null
     *
     * @param id the id of the progressplan to save.
     * @param progressplan the progressplan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated progressplan,
     * or with status {@code 400 (Bad Request)} if the progressplan is not valid,
     * or with status {@code 404 (Not Found)} if the progressplan is not found,
     * or with status {@code 500 (Internal Server Error)} if the progressplan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Progressplan> partialUpdateProgressplan(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Progressplan progressplan
    ) throws URISyntaxException {
        log.debug("REST request to partial update Progressplan partially : {}, {}", id, progressplan);
        if (progressplan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, progressplan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!progressplanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Progressplan> result = progressplanRepository
            .findById(progressplan.getId())
            .map(existingProgressplan -> {
                if (progressplan.getProgressname() != null) {
                    existingProgressplan.setProgressname(progressplan.getProgressname());
                }
                if (progressplan.getProgresstype() != null) {
                    existingProgressplan.setProgresstype(progressplan.getProgresstype());
                }
                if (progressplan.getWorkfocus() != null) {
                    existingProgressplan.setWorkfocus(progressplan.getWorkfocus());
                }
                if (progressplan.getCreatetime() != null) {
                    existingProgressplan.setCreatetime(progressplan.getCreatetime());
                }
                if (progressplan.getCreatorname() != null) {
                    existingProgressplan.setCreatorname(progressplan.getCreatorname());
                }
                if (progressplan.getResponsiblename() != null) {
                    existingProgressplan.setResponsiblename(progressplan.getResponsiblename());
                }
                if (progressplan.getStatus() != null) {
                    existingProgressplan.setStatus(progressplan.getStatus());
                }
                if (progressplan.getAuditStatus() != null) {
                    existingProgressplan.setAuditStatus(progressplan.getAuditStatus());
                }

                return existingProgressplan;
            })
            .map(progressplanRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, progressplan.getId())
        );
    }

    /**
     * {@code GET  /progressplans} : get all the progressplans.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of progressplans in body.
     */
    @GetMapping("")
    public List<Progressplan> getAllProgressplans(@RequestParam(name = "filter", required = false) String filter) {
        if ("comprehensivecontrol-is-null".equals(filter)) {
            log.debug("REST request to get all Progressplans where comprehensivecontrol is null");
            return StreamSupport.stream(progressplanRepository.findAll().spliterator(), false)
                .filter(progressplan -> progressplan.getComprehensivecontrol() == null)
                .toList();
        }

        if ("progressplanreturns-is-null".equals(filter)) {
            log.debug("REST request to get all Progressplans where progressplanreturns is null");
            return StreamSupport.stream(progressplanRepository.findAll().spliterator(), false)
                .filter(progressplan -> progressplan.getProgressplanreturns() == null)
                .toList();
        }
        log.debug("REST request to get all Progressplans");
        return progressplanRepository.findAll();
    }

    /**
     * {@code GET  /progressplans/:id} : get the "id" progressplan.
     *
     * @param id the id of the progressplan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the progressplan, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Progressplan> getProgressplan(@PathVariable("id") String id) {
        log.debug("REST request to get Progressplan : {}", id);
        Optional<Progressplan> progressplan = progressplanRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(progressplan);
    }

    /**
     * {@code DELETE  /progressplans/:id} : delete the "id" progressplan.
     *
     * @param id the id of the progressplan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgressplan(@PathVariable("id") String id) {
        log.debug("REST request to delete Progressplan : {}", id);
        progressplanRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
