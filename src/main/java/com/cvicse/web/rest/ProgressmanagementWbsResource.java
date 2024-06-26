package com.cvicse.web.rest;

import com.cvicse.domain.ProgressmanagementWbs;
import com.cvicse.repository.ProgressmanagementWbsRepository;
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
 * REST controller for managing {@link com.cvicse.domain.ProgressmanagementWbs}.
 */
@RestController
@RequestMapping("/api/progressmanagement-wbs")
@Transactional
public class ProgressmanagementWbsResource {

    private final Logger log = LoggerFactory.getLogger(ProgressmanagementWbsResource.class);

    private static final String ENTITY_NAME = "progressmanagementWbs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProgressmanagementWbsRepository progressmanagementWbsRepository;

    public ProgressmanagementWbsResource(ProgressmanagementWbsRepository progressmanagementWbsRepository) {
        this.progressmanagementWbsRepository = progressmanagementWbsRepository;
    }

    /**
     * {@code POST  /progressmanagement-wbs} : Create a new progressmanagementWbs.
     *
     * @param progressmanagementWbs the progressmanagementWbs to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new progressmanagementWbs, or with status {@code 400 (Bad Request)} if the progressmanagementWbs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ProgressmanagementWbs> createProgressmanagementWbs(@RequestBody ProgressmanagementWbs progressmanagementWbs)
        throws URISyntaxException {
        log.debug("REST request to save ProgressmanagementWbs : {}", progressmanagementWbs);
        if (progressmanagementWbs.getId() != null) {
            throw new BadRequestAlertException("A new progressmanagementWbs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        progressmanagementWbs = progressmanagementWbsRepository.save(progressmanagementWbs);
        return ResponseEntity.created(new URI("/api/progressmanagement-wbs/" + progressmanagementWbs.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, progressmanagementWbs.getId()))
            .body(progressmanagementWbs);
    }

    /**
     * {@code PUT  /progressmanagement-wbs/:id} : Updates an existing progressmanagementWbs.
     *
     * @param id the id of the progressmanagementWbs to save.
     * @param progressmanagementWbs the progressmanagementWbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated progressmanagementWbs,
     * or with status {@code 400 (Bad Request)} if the progressmanagementWbs is not valid,
     * or with status {@code 500 (Internal Server Error)} if the progressmanagementWbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProgressmanagementWbs> updateProgressmanagementWbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ProgressmanagementWbs progressmanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to update ProgressmanagementWbs : {}, {}", id, progressmanagementWbs);
        if (progressmanagementWbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, progressmanagementWbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!progressmanagementWbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        progressmanagementWbs = progressmanagementWbsRepository.save(progressmanagementWbs);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, progressmanagementWbs.getId()))
            .body(progressmanagementWbs);
    }

    /**
     * {@code PATCH  /progressmanagement-wbs/:id} : Partial updates given fields of an existing progressmanagementWbs, field will ignore if it is null
     *
     * @param id the id of the progressmanagementWbs to save.
     * @param progressmanagementWbs the progressmanagementWbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated progressmanagementWbs,
     * or with status {@code 400 (Bad Request)} if the progressmanagementWbs is not valid,
     * or with status {@code 404 (Not Found)} if the progressmanagementWbs is not found,
     * or with status {@code 500 (Internal Server Error)} if the progressmanagementWbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProgressmanagementWbs> partialUpdateProgressmanagementWbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ProgressmanagementWbs progressmanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProgressmanagementWbs partially : {}, {}", id, progressmanagementWbs);
        if (progressmanagementWbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, progressmanagementWbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!progressmanagementWbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProgressmanagementWbs> result = progressmanagementWbsRepository
            .findById(progressmanagementWbs.getId())
            .map(existingProgressmanagementWbs -> {
                if (progressmanagementWbs.getWbsspare1() != null) {
                    existingProgressmanagementWbs.setWbsspare1(progressmanagementWbs.getWbsspare1());
                }
                if (progressmanagementWbs.getWbsspare2() != null) {
                    existingProgressmanagementWbs.setWbsspare2(progressmanagementWbs.getWbsspare2());
                }
                if (progressmanagementWbs.getWbsspare3() != null) {
                    existingProgressmanagementWbs.setWbsspare3(progressmanagementWbs.getWbsspare3());
                }
                if (progressmanagementWbs.getWbsspare4() != null) {
                    existingProgressmanagementWbs.setWbsspare4(progressmanagementWbs.getWbsspare4());
                }
                if (progressmanagementWbs.getWbsspare5() != null) {
                    existingProgressmanagementWbs.setWbsspare5(progressmanagementWbs.getWbsspare5());
                }

                return existingProgressmanagementWbs;
            })
            .map(progressmanagementWbsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, progressmanagementWbs.getId())
        );
    }

    /**
     * {@code GET  /progressmanagement-wbs} : get all the progressmanagementWbs.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of progressmanagementWbs in body.
     */
    @GetMapping("")
    public List<ProgressmanagementWbs> getAllProgressmanagementWbs(@RequestParam(name = "filter", required = false) String filter) {
        if ("progressmanagement-is-null".equals(filter)) {
            log.debug("REST request to get all ProgressmanagementWbss where progressmanagement is null");
            return StreamSupport.stream(progressmanagementWbsRepository.findAll().spliterator(), false)
                .filter(progressmanagementWbs -> progressmanagementWbs.getProgressmanagement() == null)
                .toList();
        }
        log.debug("REST request to get all ProgressmanagementWbs");
        return progressmanagementWbsRepository.findAll();
    }

    /**
     * {@code GET  /progressmanagement-wbs/:id} : get the "id" progressmanagementWbs.
     *
     * @param id the id of the progressmanagementWbs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the progressmanagementWbs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProgressmanagementWbs> getProgressmanagementWbs(@PathVariable("id") String id) {
        log.debug("REST request to get ProgressmanagementWbs : {}", id);
        Optional<ProgressmanagementWbs> progressmanagementWbs = progressmanagementWbsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(progressmanagementWbs);
    }

    /**
     * {@code DELETE  /progressmanagement-wbs/:id} : delete the "id" progressmanagementWbs.
     *
     * @param id the id of the progressmanagementWbs to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgressmanagementWbs(@PathVariable("id") String id) {
        log.debug("REST request to delete ProgressmanagementWbs : {}", id);
        progressmanagementWbsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
