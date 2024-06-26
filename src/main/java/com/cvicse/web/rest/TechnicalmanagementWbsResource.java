package com.cvicse.web.rest;

import com.cvicse.domain.TechnicalmanagementWbs;
import com.cvicse.repository.TechnicalmanagementWbsRepository;
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
 * REST controller for managing {@link com.cvicse.domain.TechnicalmanagementWbs}.
 */
@RestController
@RequestMapping("/api/technicalmanagement-wbs")
@Transactional
public class TechnicalmanagementWbsResource {

    private final Logger log = LoggerFactory.getLogger(TechnicalmanagementWbsResource.class);

    private static final String ENTITY_NAME = "technicalmanagementWbs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TechnicalmanagementWbsRepository technicalmanagementWbsRepository;

    public TechnicalmanagementWbsResource(TechnicalmanagementWbsRepository technicalmanagementWbsRepository) {
        this.technicalmanagementWbsRepository = technicalmanagementWbsRepository;
    }

    /**
     * {@code POST  /technicalmanagement-wbs} : Create a new technicalmanagementWbs.
     *
     * @param technicalmanagementWbs the technicalmanagementWbs to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new technicalmanagementWbs, or with status {@code 400 (Bad Request)} if the technicalmanagementWbs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TechnicalmanagementWbs> createTechnicalmanagementWbs(@RequestBody TechnicalmanagementWbs technicalmanagementWbs)
        throws URISyntaxException {
        log.debug("REST request to save TechnicalmanagementWbs : {}", technicalmanagementWbs);
        if (technicalmanagementWbs.getId() != null) {
            throw new BadRequestAlertException("A new technicalmanagementWbs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        technicalmanagementWbs = technicalmanagementWbsRepository.save(technicalmanagementWbs);
        return ResponseEntity.created(new URI("/api/technicalmanagement-wbs/" + technicalmanagementWbs.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, technicalmanagementWbs.getId()))
            .body(technicalmanagementWbs);
    }

    /**
     * {@code PUT  /technicalmanagement-wbs/:id} : Updates an existing technicalmanagementWbs.
     *
     * @param id the id of the technicalmanagementWbs to save.
     * @param technicalmanagementWbs the technicalmanagementWbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated technicalmanagementWbs,
     * or with status {@code 400 (Bad Request)} if the technicalmanagementWbs is not valid,
     * or with status {@code 500 (Internal Server Error)} if the technicalmanagementWbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TechnicalmanagementWbs> updateTechnicalmanagementWbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody TechnicalmanagementWbs technicalmanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to update TechnicalmanagementWbs : {}, {}", id, technicalmanagementWbs);
        if (technicalmanagementWbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, technicalmanagementWbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!technicalmanagementWbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        technicalmanagementWbs = technicalmanagementWbsRepository.save(technicalmanagementWbs);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, technicalmanagementWbs.getId()))
            .body(technicalmanagementWbs);
    }

    /**
     * {@code PATCH  /technicalmanagement-wbs/:id} : Partial updates given fields of an existing technicalmanagementWbs, field will ignore if it is null
     *
     * @param id the id of the technicalmanagementWbs to save.
     * @param technicalmanagementWbs the technicalmanagementWbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated technicalmanagementWbs,
     * or with status {@code 400 (Bad Request)} if the technicalmanagementWbs is not valid,
     * or with status {@code 404 (Not Found)} if the technicalmanagementWbs is not found,
     * or with status {@code 500 (Internal Server Error)} if the technicalmanagementWbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TechnicalmanagementWbs> partialUpdateTechnicalmanagementWbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody TechnicalmanagementWbs technicalmanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to partial update TechnicalmanagementWbs partially : {}, {}", id, technicalmanagementWbs);
        if (technicalmanagementWbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, technicalmanagementWbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!technicalmanagementWbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TechnicalmanagementWbs> result = technicalmanagementWbsRepository
            .findById(technicalmanagementWbs.getId())
            .map(existingTechnicalmanagementWbs -> {
                if (technicalmanagementWbs.getWbsspare1() != null) {
                    existingTechnicalmanagementWbs.setWbsspare1(technicalmanagementWbs.getWbsspare1());
                }
                if (technicalmanagementWbs.getWbsspare2() != null) {
                    existingTechnicalmanagementWbs.setWbsspare2(technicalmanagementWbs.getWbsspare2());
                }
                if (technicalmanagementWbs.getWbsspare3() != null) {
                    existingTechnicalmanagementWbs.setWbsspare3(technicalmanagementWbs.getWbsspare3());
                }
                if (technicalmanagementWbs.getWbsspare4() != null) {
                    existingTechnicalmanagementWbs.setWbsspare4(technicalmanagementWbs.getWbsspare4());
                }
                if (technicalmanagementWbs.getWbsspare5() != null) {
                    existingTechnicalmanagementWbs.setWbsspare5(technicalmanagementWbs.getWbsspare5());
                }

                return existingTechnicalmanagementWbs;
            })
            .map(technicalmanagementWbsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, technicalmanagementWbs.getId())
        );
    }

    /**
     * {@code GET  /technicalmanagement-wbs} : get all the technicalmanagementWbs.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of technicalmanagementWbs in body.
     */
    @GetMapping("")
    public List<TechnicalmanagementWbs> getAllTechnicalmanagementWbs(@RequestParam(name = "filter", required = false) String filter) {
        if ("technicalmanagement-is-null".equals(filter)) {
            log.debug("REST request to get all TechnicalmanagementWbss where technicalmanagement is null");
            return StreamSupport.stream(technicalmanagementWbsRepository.findAll().spliterator(), false)
                .filter(technicalmanagementWbs -> technicalmanagementWbs.getTechnicalmanagement() == null)
                .toList();
        }
        log.debug("REST request to get all TechnicalmanagementWbs");
        return technicalmanagementWbsRepository.findAll();
    }

    /**
     * {@code GET  /technicalmanagement-wbs/:id} : get the "id" technicalmanagementWbs.
     *
     * @param id the id of the technicalmanagementWbs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the technicalmanagementWbs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TechnicalmanagementWbs> getTechnicalmanagementWbs(@PathVariable("id") String id) {
        log.debug("REST request to get TechnicalmanagementWbs : {}", id);
        Optional<TechnicalmanagementWbs> technicalmanagementWbs = technicalmanagementWbsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(technicalmanagementWbs);
    }

    /**
     * {@code DELETE  /technicalmanagement-wbs/:id} : delete the "id" technicalmanagementWbs.
     *
     * @param id the id of the technicalmanagementWbs to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnicalmanagementWbs(@PathVariable("id") String id) {
        log.debug("REST request to delete TechnicalmanagementWbs : {}", id);
        technicalmanagementWbsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
