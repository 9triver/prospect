package com.cvicse.web.rest;

import com.cvicse.domain.Riskreport;
import com.cvicse.repository.RiskreportRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Riskreport}.
 */
@RestController
@RequestMapping("/api/riskreports")
@Transactional
public class RiskreportResource {

    private final Logger log = LoggerFactory.getLogger(RiskreportResource.class);

    private static final String ENTITY_NAME = "riskreport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RiskreportRepository riskreportRepository;

    public RiskreportResource(RiskreportRepository riskreportRepository) {
        this.riskreportRepository = riskreportRepository;
    }

    /**
     * {@code POST  /riskreports} : Create a new riskreport.
     *
     * @param riskreport the riskreport to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new riskreport, or with status {@code 400 (Bad Request)} if the riskreport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Riskreport> createRiskreport(@RequestBody Riskreport riskreport) throws URISyntaxException {
        log.debug("REST request to save Riskreport : {}", riskreport);
        if (riskreport.getId() != null) {
            throw new BadRequestAlertException("A new riskreport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        riskreport = riskreportRepository.save(riskreport);
        return ResponseEntity.created(new URI("/api/riskreports/" + riskreport.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, riskreport.getId()))
            .body(riskreport);
    }

    /**
     * {@code PUT  /riskreports/:id} : Updates an existing riskreport.
     *
     * @param id the id of the riskreport to save.
     * @param riskreport the riskreport to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskreport,
     * or with status {@code 400 (Bad Request)} if the riskreport is not valid,
     * or with status {@code 500 (Internal Server Error)} if the riskreport couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Riskreport> updateRiskreport(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Riskreport riskreport
    ) throws URISyntaxException {
        log.debug("REST request to update Riskreport : {}, {}", id, riskreport);
        if (riskreport.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, riskreport.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!riskreportRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        riskreport = riskreportRepository.save(riskreport);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskreport.getId()))
            .body(riskreport);
    }

    /**
     * {@code PATCH  /riskreports/:id} : Partial updates given fields of an existing riskreport, field will ignore if it is null
     *
     * @param id the id of the riskreport to save.
     * @param riskreport the riskreport to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskreport,
     * or with status {@code 400 (Bad Request)} if the riskreport is not valid,
     * or with status {@code 404 (Not Found)} if the riskreport is not found,
     * or with status {@code 500 (Internal Server Error)} if the riskreport couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Riskreport> partialUpdateRiskreport(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Riskreport riskreport
    ) throws URISyntaxException {
        log.debug("REST request to partial update Riskreport partially : {}, {}", id, riskreport);
        if (riskreport.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, riskreport.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!riskreportRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Riskreport> result = riskreportRepository
            .findById(riskreport.getId())
            .map(existingRiskreport -> {
                if (riskreport.getType() != null) {
                    existingRiskreport.setType(riskreport.getType());
                }
                if (riskreport.getRiskreportname() != null) {
                    existingRiskreport.setRiskreportname(riskreport.getRiskreportname());
                }
                if (riskreport.getReleasetime() != null) {
                    existingRiskreport.setReleasetime(riskreport.getReleasetime());
                }
                if (riskreport.getAuditStatus() != null) {
                    existingRiskreport.setAuditStatus(riskreport.getAuditStatus());
                }

                return existingRiskreport;
            })
            .map(riskreportRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskreport.getId())
        );
    }

    /**
     * {@code GET  /riskreports} : get all the riskreports.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of riskreports in body.
     */
    @GetMapping("")
    public List<Riskreport> getAllRiskreports() {
        log.debug("REST request to get all Riskreports");
        return riskreportRepository.findAll();
    }

    /**
     * {@code GET  /riskreports/:id} : get the "id" riskreport.
     *
     * @param id the id of the riskreport to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the riskreport, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Riskreport> getRiskreport(@PathVariable("id") String id) {
        log.debug("REST request to get Riskreport : {}", id);
        Optional<Riskreport> riskreport = riskreportRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(riskreport);
    }

    /**
     * {@code DELETE  /riskreports/:id} : delete the "id" riskreport.
     *
     * @param id the id of the riskreport to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRiskreport(@PathVariable("id") String id) {
        log.debug("REST request to delete Riskreport : {}", id);
        riskreportRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
