package com.cvicse.web.rest;

import com.cvicse.domain.SecrecymanagementWbs;
import com.cvicse.repository.SecrecymanagementWbsRepository;
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
 * REST controller for managing {@link com.cvicse.domain.SecrecymanagementWbs}.
 */
@RestController
@RequestMapping("/api/secrecymanagement-wbs")
@Transactional
public class SecrecymanagementWbsResource {

    private final Logger log = LoggerFactory.getLogger(SecrecymanagementWbsResource.class);

    private static final String ENTITY_NAME = "secrecymanagementWbs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SecrecymanagementWbsRepository secrecymanagementWbsRepository;

    public SecrecymanagementWbsResource(SecrecymanagementWbsRepository secrecymanagementWbsRepository) {
        this.secrecymanagementWbsRepository = secrecymanagementWbsRepository;
    }

    /**
     * {@code POST  /secrecymanagement-wbs} : Create a new secrecymanagementWbs.
     *
     * @param secrecymanagementWbs the secrecymanagementWbs to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new secrecymanagementWbs, or with status {@code 400 (Bad Request)} if the secrecymanagementWbs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<SecrecymanagementWbs> createSecrecymanagementWbs(@RequestBody SecrecymanagementWbs secrecymanagementWbs)
        throws URISyntaxException {
        log.debug("REST request to save SecrecymanagementWbs : {}", secrecymanagementWbs);
        if (secrecymanagementWbs.getId() != null) {
            throw new BadRequestAlertException("A new secrecymanagementWbs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        secrecymanagementWbs = secrecymanagementWbsRepository.save(secrecymanagementWbs);
        return ResponseEntity.created(new URI("/api/secrecymanagement-wbs/" + secrecymanagementWbs.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, secrecymanagementWbs.getId()))
            .body(secrecymanagementWbs);
    }

    /**
     * {@code PUT  /secrecymanagement-wbs/:id} : Updates an existing secrecymanagementWbs.
     *
     * @param id the id of the secrecymanagementWbs to save.
     * @param secrecymanagementWbs the secrecymanagementWbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated secrecymanagementWbs,
     * or with status {@code 400 (Bad Request)} if the secrecymanagementWbs is not valid,
     * or with status {@code 500 (Internal Server Error)} if the secrecymanagementWbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SecrecymanagementWbs> updateSecrecymanagementWbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody SecrecymanagementWbs secrecymanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to update SecrecymanagementWbs : {}, {}", id, secrecymanagementWbs);
        if (secrecymanagementWbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, secrecymanagementWbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!secrecymanagementWbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        secrecymanagementWbs = secrecymanagementWbsRepository.save(secrecymanagementWbs);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, secrecymanagementWbs.getId()))
            .body(secrecymanagementWbs);
    }

    /**
     * {@code PATCH  /secrecymanagement-wbs/:id} : Partial updates given fields of an existing secrecymanagementWbs, field will ignore if it is null
     *
     * @param id the id of the secrecymanagementWbs to save.
     * @param secrecymanagementWbs the secrecymanagementWbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated secrecymanagementWbs,
     * or with status {@code 400 (Bad Request)} if the secrecymanagementWbs is not valid,
     * or with status {@code 404 (Not Found)} if the secrecymanagementWbs is not found,
     * or with status {@code 500 (Internal Server Error)} if the secrecymanagementWbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SecrecymanagementWbs> partialUpdateSecrecymanagementWbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody SecrecymanagementWbs secrecymanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to partial update SecrecymanagementWbs partially : {}, {}", id, secrecymanagementWbs);
        if (secrecymanagementWbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, secrecymanagementWbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!secrecymanagementWbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SecrecymanagementWbs> result = secrecymanagementWbsRepository
            .findById(secrecymanagementWbs.getId())
            .map(existingSecrecymanagementWbs -> {
                if (secrecymanagementWbs.getWbsspare1() != null) {
                    existingSecrecymanagementWbs.setWbsspare1(secrecymanagementWbs.getWbsspare1());
                }
                if (secrecymanagementWbs.getWbsspare2() != null) {
                    existingSecrecymanagementWbs.setWbsspare2(secrecymanagementWbs.getWbsspare2());
                }
                if (secrecymanagementWbs.getWbsspare3() != null) {
                    existingSecrecymanagementWbs.setWbsspare3(secrecymanagementWbs.getWbsspare3());
                }
                if (secrecymanagementWbs.getWbsspare4() != null) {
                    existingSecrecymanagementWbs.setWbsspare4(secrecymanagementWbs.getWbsspare4());
                }
                if (secrecymanagementWbs.getWbsspare5() != null) {
                    existingSecrecymanagementWbs.setWbsspare5(secrecymanagementWbs.getWbsspare5());
                }

                return existingSecrecymanagementWbs;
            })
            .map(secrecymanagementWbsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, secrecymanagementWbs.getId())
        );
    }

    /**
     * {@code GET  /secrecymanagement-wbs} : get all the secrecymanagementWbs.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of secrecymanagementWbs in body.
     */
    @GetMapping("")
    public List<SecrecymanagementWbs> getAllSecrecymanagementWbs(@RequestParam(name = "filter", required = false) String filter) {
        if ("secrecymanagement-is-null".equals(filter)) {
            log.debug("REST request to get all SecrecymanagementWbss where secrecymanagement is null");
            return StreamSupport.stream(secrecymanagementWbsRepository.findAll().spliterator(), false)
                .filter(secrecymanagementWbs -> secrecymanagementWbs.getSecrecymanagement() == null)
                .toList();
        }
        log.debug("REST request to get all SecrecymanagementWbs");
        return secrecymanagementWbsRepository.findAll();
    }

    /**
     * {@code GET  /secrecymanagement-wbs/:id} : get the "id" secrecymanagementWbs.
     *
     * @param id the id of the secrecymanagementWbs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the secrecymanagementWbs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SecrecymanagementWbs> getSecrecymanagementWbs(@PathVariable("id") String id) {
        log.debug("REST request to get SecrecymanagementWbs : {}", id);
        Optional<SecrecymanagementWbs> secrecymanagementWbs = secrecymanagementWbsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(secrecymanagementWbs);
    }

    /**
     * {@code DELETE  /secrecymanagement-wbs/:id} : delete the "id" secrecymanagementWbs.
     *
     * @param id the id of the secrecymanagementWbs to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSecrecymanagementWbs(@PathVariable("id") String id) {
        log.debug("REST request to delete SecrecymanagementWbs : {}", id);
        secrecymanagementWbsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
