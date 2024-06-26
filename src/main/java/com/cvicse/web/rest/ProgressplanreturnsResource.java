package com.cvicse.web.rest;

import com.cvicse.domain.Progressplanreturns;
import com.cvicse.repository.ProgressplanreturnsRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Progressplanreturns}.
 */
@RestController
@RequestMapping("/api/progressplanreturns")
@Transactional
public class ProgressplanreturnsResource {

    private final Logger log = LoggerFactory.getLogger(ProgressplanreturnsResource.class);

    private static final String ENTITY_NAME = "progressplanreturns";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProgressplanreturnsRepository progressplanreturnsRepository;

    public ProgressplanreturnsResource(ProgressplanreturnsRepository progressplanreturnsRepository) {
        this.progressplanreturnsRepository = progressplanreturnsRepository;
    }

    /**
     * {@code POST  /progressplanreturns} : Create a new progressplanreturns.
     *
     * @param progressplanreturns the progressplanreturns to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new progressplanreturns, or with status {@code 400 (Bad Request)} if the progressplanreturns has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Progressplanreturns> createProgressplanreturns(@RequestBody Progressplanreturns progressplanreturns)
        throws URISyntaxException {
        log.debug("REST request to save Progressplanreturns : {}", progressplanreturns);
        if (progressplanreturns.getId() != null) {
            throw new BadRequestAlertException("A new progressplanreturns cannot already have an ID", ENTITY_NAME, "idexists");
        }
        progressplanreturns = progressplanreturnsRepository.save(progressplanreturns);
        return ResponseEntity.created(new URI("/api/progressplanreturns/" + progressplanreturns.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, progressplanreturns.getId()))
            .body(progressplanreturns);
    }

    /**
     * {@code PUT  /progressplanreturns/:id} : Updates an existing progressplanreturns.
     *
     * @param id the id of the progressplanreturns to save.
     * @param progressplanreturns the progressplanreturns to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated progressplanreturns,
     * or with status {@code 400 (Bad Request)} if the progressplanreturns is not valid,
     * or with status {@code 500 (Internal Server Error)} if the progressplanreturns couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Progressplanreturns> updateProgressplanreturns(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Progressplanreturns progressplanreturns
    ) throws URISyntaxException {
        log.debug("REST request to update Progressplanreturns : {}, {}", id, progressplanreturns);
        if (progressplanreturns.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, progressplanreturns.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!progressplanreturnsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        progressplanreturns = progressplanreturnsRepository.save(progressplanreturns);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, progressplanreturns.getId()))
            .body(progressplanreturns);
    }

    /**
     * {@code PATCH  /progressplanreturns/:id} : Partial updates given fields of an existing progressplanreturns, field will ignore if it is null
     *
     * @param id the id of the progressplanreturns to save.
     * @param progressplanreturns the progressplanreturns to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated progressplanreturns,
     * or with status {@code 400 (Bad Request)} if the progressplanreturns is not valid,
     * or with status {@code 404 (Not Found)} if the progressplanreturns is not found,
     * or with status {@code 500 (Internal Server Error)} if the progressplanreturns couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Progressplanreturns> partialUpdateProgressplanreturns(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Progressplanreturns progressplanreturns
    ) throws URISyntaxException {
        log.debug("REST request to partial update Progressplanreturns partially : {}, {}", id, progressplanreturns);
        if (progressplanreturns.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, progressplanreturns.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!progressplanreturnsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Progressplanreturns> result = progressplanreturnsRepository
            .findById(progressplanreturns.getId())
            .map(existingProgressplanreturns -> {
                if (progressplanreturns.getPlanstarttime() != null) {
                    existingProgressplanreturns.setPlanstarttime(progressplanreturns.getPlanstarttime());
                }
                if (progressplanreturns.getPlanendtime() != null) {
                    existingProgressplanreturns.setPlanendtime(progressplanreturns.getPlanendtime());
                }
                if (progressplanreturns.getReturnstime() != null) {
                    existingProgressplanreturns.setReturnstime(progressplanreturns.getReturnstime());
                }

                return existingProgressplanreturns;
            })
            .map(progressplanreturnsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, progressplanreturns.getId())
        );
    }

    /**
     * {@code GET  /progressplanreturns} : get all the progressplanreturns.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of progressplanreturns in body.
     */
    @GetMapping("")
    public List<Progressplanreturns> getAllProgressplanreturns() {
        log.debug("REST request to get all Progressplanreturns");
        return progressplanreturnsRepository.findAll();
    }

    /**
     * {@code GET  /progressplanreturns/:id} : get the "id" progressplanreturns.
     *
     * @param id the id of the progressplanreturns to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the progressplanreturns, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Progressplanreturns> getProgressplanreturns(@PathVariable("id") String id) {
        log.debug("REST request to get Progressplanreturns : {}", id);
        Optional<Progressplanreturns> progressplanreturns = progressplanreturnsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(progressplanreturns);
    }

    /**
     * {@code DELETE  /progressplanreturns/:id} : delete the "id" progressplanreturns.
     *
     * @param id the id of the progressplanreturns to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgressplanreturns(@PathVariable("id") String id) {
        log.debug("REST request to delete Progressplanreturns : {}", id);
        progressplanreturnsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
