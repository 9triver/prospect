package com.cvicse.web.rest;

import com.cvicse.domain.QualitymanagementWbs;
import com.cvicse.repository.QualitymanagementWbsRepository;
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
 * REST controller for managing {@link com.cvicse.domain.QualitymanagementWbs}.
 */
@RestController
@RequestMapping("/api/qualitymanagement-wbs")
@Transactional
public class QualitymanagementWbsResource {

    private final Logger log = LoggerFactory.getLogger(QualitymanagementWbsResource.class);

    private static final String ENTITY_NAME = "qualitymanagementWbs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QualitymanagementWbsRepository qualitymanagementWbsRepository;

    public QualitymanagementWbsResource(QualitymanagementWbsRepository qualitymanagementWbsRepository) {
        this.qualitymanagementWbsRepository = qualitymanagementWbsRepository;
    }

    /**
     * {@code POST  /qualitymanagement-wbs} : Create a new qualitymanagementWbs.
     *
     * @param qualitymanagementWbs the qualitymanagementWbs to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qualitymanagementWbs, or with status {@code 400 (Bad Request)} if the qualitymanagementWbs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<QualitymanagementWbs> createQualitymanagementWbs(@RequestBody QualitymanagementWbs qualitymanagementWbs)
        throws URISyntaxException {
        log.debug("REST request to save QualitymanagementWbs : {}", qualitymanagementWbs);
        if (qualitymanagementWbs.getId() != null) {
            throw new BadRequestAlertException("A new qualitymanagementWbs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        qualitymanagementWbs = qualitymanagementWbsRepository.save(qualitymanagementWbs);
        return ResponseEntity.created(new URI("/api/qualitymanagement-wbs/" + qualitymanagementWbs.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, qualitymanagementWbs.getId()))
            .body(qualitymanagementWbs);
    }

    /**
     * {@code PUT  /qualitymanagement-wbs/:id} : Updates an existing qualitymanagementWbs.
     *
     * @param id the id of the qualitymanagementWbs to save.
     * @param qualitymanagementWbs the qualitymanagementWbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualitymanagementWbs,
     * or with status {@code 400 (Bad Request)} if the qualitymanagementWbs is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qualitymanagementWbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<QualitymanagementWbs> updateQualitymanagementWbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody QualitymanagementWbs qualitymanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to update QualitymanagementWbs : {}, {}", id, qualitymanagementWbs);
        if (qualitymanagementWbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualitymanagementWbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualitymanagementWbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        qualitymanagementWbs = qualitymanagementWbsRepository.save(qualitymanagementWbs);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualitymanagementWbs.getId()))
            .body(qualitymanagementWbs);
    }

    /**
     * {@code PATCH  /qualitymanagement-wbs/:id} : Partial updates given fields of an existing qualitymanagementWbs, field will ignore if it is null
     *
     * @param id the id of the qualitymanagementWbs to save.
     * @param qualitymanagementWbs the qualitymanagementWbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualitymanagementWbs,
     * or with status {@code 400 (Bad Request)} if the qualitymanagementWbs is not valid,
     * or with status {@code 404 (Not Found)} if the qualitymanagementWbs is not found,
     * or with status {@code 500 (Internal Server Error)} if the qualitymanagementWbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<QualitymanagementWbs> partialUpdateQualitymanagementWbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody QualitymanagementWbs qualitymanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to partial update QualitymanagementWbs partially : {}, {}", id, qualitymanagementWbs);
        if (qualitymanagementWbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualitymanagementWbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualitymanagementWbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QualitymanagementWbs> result = qualitymanagementWbsRepository
            .findById(qualitymanagementWbs.getId())
            .map(existingQualitymanagementWbs -> {
                if (qualitymanagementWbs.getWbsspare1() != null) {
                    existingQualitymanagementWbs.setWbsspare1(qualitymanagementWbs.getWbsspare1());
                }
                if (qualitymanagementWbs.getWbsspare2() != null) {
                    existingQualitymanagementWbs.setWbsspare2(qualitymanagementWbs.getWbsspare2());
                }
                if (qualitymanagementWbs.getWbsspare3() != null) {
                    existingQualitymanagementWbs.setWbsspare3(qualitymanagementWbs.getWbsspare3());
                }
                if (qualitymanagementWbs.getWbsspare4() != null) {
                    existingQualitymanagementWbs.setWbsspare4(qualitymanagementWbs.getWbsspare4());
                }
                if (qualitymanagementWbs.getWbsspare5() != null) {
                    existingQualitymanagementWbs.setWbsspare5(qualitymanagementWbs.getWbsspare5());
                }

                return existingQualitymanagementWbs;
            })
            .map(qualitymanagementWbsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualitymanagementWbs.getId())
        );
    }

    /**
     * {@code GET  /qualitymanagement-wbs} : get all the qualitymanagementWbs.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qualitymanagementWbs in body.
     */
    @GetMapping("")
    public List<QualitymanagementWbs> getAllQualitymanagementWbs(@RequestParam(name = "filter", required = false) String filter) {
        if ("qualitymanagement-is-null".equals(filter)) {
            log.debug("REST request to get all QualitymanagementWbss where qualitymanagement is null");
            return StreamSupport.stream(qualitymanagementWbsRepository.findAll().spliterator(), false)
                .filter(qualitymanagementWbs -> qualitymanagementWbs.getQualitymanagement() == null)
                .toList();
        }
        log.debug("REST request to get all QualitymanagementWbs");
        return qualitymanagementWbsRepository.findAll();
    }

    /**
     * {@code GET  /qualitymanagement-wbs/:id} : get the "id" qualitymanagementWbs.
     *
     * @param id the id of the qualitymanagementWbs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qualitymanagementWbs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<QualitymanagementWbs> getQualitymanagementWbs(@PathVariable("id") String id) {
        log.debug("REST request to get QualitymanagementWbs : {}", id);
        Optional<QualitymanagementWbs> qualitymanagementWbs = qualitymanagementWbsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(qualitymanagementWbs);
    }

    /**
     * {@code DELETE  /qualitymanagement-wbs/:id} : delete the "id" qualitymanagementWbs.
     *
     * @param id the id of the qualitymanagementWbs to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQualitymanagementWbs(@PathVariable("id") String id) {
        log.debug("REST request to delete QualitymanagementWbs : {}", id);
        qualitymanagementWbsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
