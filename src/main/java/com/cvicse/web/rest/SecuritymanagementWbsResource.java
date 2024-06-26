package com.cvicse.web.rest;

import com.cvicse.domain.SecuritymanagementWbs;
import com.cvicse.repository.SecuritymanagementWbsRepository;
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
 * REST controller for managing {@link com.cvicse.domain.SecuritymanagementWbs}.
 */
@RestController
@RequestMapping("/api/securitymanagement-wbs")
@Transactional
public class SecuritymanagementWbsResource {

    private final Logger log = LoggerFactory.getLogger(SecuritymanagementWbsResource.class);

    private static final String ENTITY_NAME = "securitymanagementWbs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SecuritymanagementWbsRepository securitymanagementWbsRepository;

    public SecuritymanagementWbsResource(SecuritymanagementWbsRepository securitymanagementWbsRepository) {
        this.securitymanagementWbsRepository = securitymanagementWbsRepository;
    }

    /**
     * {@code POST  /securitymanagement-wbs} : Create a new securitymanagementWbs.
     *
     * @param securitymanagementWbs the securitymanagementWbs to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new securitymanagementWbs, or with status {@code 400 (Bad Request)} if the securitymanagementWbs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<SecuritymanagementWbs> createSecuritymanagementWbs(@RequestBody SecuritymanagementWbs securitymanagementWbs)
        throws URISyntaxException {
        log.debug("REST request to save SecuritymanagementWbs : {}", securitymanagementWbs);
        if (securitymanagementWbs.getId() != null) {
            throw new BadRequestAlertException("A new securitymanagementWbs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        securitymanagementWbs = securitymanagementWbsRepository.save(securitymanagementWbs);
        return ResponseEntity.created(new URI("/api/securitymanagement-wbs/" + securitymanagementWbs.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, securitymanagementWbs.getId()))
            .body(securitymanagementWbs);
    }

    /**
     * {@code PUT  /securitymanagement-wbs/:id} : Updates an existing securitymanagementWbs.
     *
     * @param id the id of the securitymanagementWbs to save.
     * @param securitymanagementWbs the securitymanagementWbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated securitymanagementWbs,
     * or with status {@code 400 (Bad Request)} if the securitymanagementWbs is not valid,
     * or with status {@code 500 (Internal Server Error)} if the securitymanagementWbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SecuritymanagementWbs> updateSecuritymanagementWbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody SecuritymanagementWbs securitymanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to update SecuritymanagementWbs : {}, {}", id, securitymanagementWbs);
        if (securitymanagementWbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, securitymanagementWbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!securitymanagementWbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        securitymanagementWbs = securitymanagementWbsRepository.save(securitymanagementWbs);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, securitymanagementWbs.getId()))
            .body(securitymanagementWbs);
    }

    /**
     * {@code PATCH  /securitymanagement-wbs/:id} : Partial updates given fields of an existing securitymanagementWbs, field will ignore if it is null
     *
     * @param id the id of the securitymanagementWbs to save.
     * @param securitymanagementWbs the securitymanagementWbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated securitymanagementWbs,
     * or with status {@code 400 (Bad Request)} if the securitymanagementWbs is not valid,
     * or with status {@code 404 (Not Found)} if the securitymanagementWbs is not found,
     * or with status {@code 500 (Internal Server Error)} if the securitymanagementWbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SecuritymanagementWbs> partialUpdateSecuritymanagementWbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody SecuritymanagementWbs securitymanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to partial update SecuritymanagementWbs partially : {}, {}", id, securitymanagementWbs);
        if (securitymanagementWbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, securitymanagementWbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!securitymanagementWbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SecuritymanagementWbs> result = securitymanagementWbsRepository
            .findById(securitymanagementWbs.getId())
            .map(existingSecuritymanagementWbs -> {
                if (securitymanagementWbs.getWbsspare1() != null) {
                    existingSecuritymanagementWbs.setWbsspare1(securitymanagementWbs.getWbsspare1());
                }
                if (securitymanagementWbs.getWbsspare2() != null) {
                    existingSecuritymanagementWbs.setWbsspare2(securitymanagementWbs.getWbsspare2());
                }
                if (securitymanagementWbs.getWbsspare3() != null) {
                    existingSecuritymanagementWbs.setWbsspare3(securitymanagementWbs.getWbsspare3());
                }
                if (securitymanagementWbs.getWbsspare4() != null) {
                    existingSecuritymanagementWbs.setWbsspare4(securitymanagementWbs.getWbsspare4());
                }
                if (securitymanagementWbs.getWbsspare5() != null) {
                    existingSecuritymanagementWbs.setWbsspare5(securitymanagementWbs.getWbsspare5());
                }

                return existingSecuritymanagementWbs;
            })
            .map(securitymanagementWbsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, securitymanagementWbs.getId())
        );
    }

    /**
     * {@code GET  /securitymanagement-wbs} : get all the securitymanagementWbs.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of securitymanagementWbs in body.
     */
    @GetMapping("")
    public List<SecuritymanagementWbs> getAllSecuritymanagementWbs(@RequestParam(name = "filter", required = false) String filter) {
        if ("securitymanagement-is-null".equals(filter)) {
            log.debug("REST request to get all SecuritymanagementWbss where securitymanagement is null");
            return StreamSupport.stream(securitymanagementWbsRepository.findAll().spliterator(), false)
                .filter(securitymanagementWbs -> securitymanagementWbs.getSecuritymanagement() == null)
                .toList();
        }
        log.debug("REST request to get all SecuritymanagementWbs");
        return securitymanagementWbsRepository.findAll();
    }

    /**
     * {@code GET  /securitymanagement-wbs/:id} : get the "id" securitymanagementWbs.
     *
     * @param id the id of the securitymanagementWbs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the securitymanagementWbs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SecuritymanagementWbs> getSecuritymanagementWbs(@PathVariable("id") String id) {
        log.debug("REST request to get SecuritymanagementWbs : {}", id);
        Optional<SecuritymanagementWbs> securitymanagementWbs = securitymanagementWbsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(securitymanagementWbs);
    }

    /**
     * {@code DELETE  /securitymanagement-wbs/:id} : delete the "id" securitymanagementWbs.
     *
     * @param id the id of the securitymanagementWbs to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSecuritymanagementWbs(@PathVariable("id") String id) {
        log.debug("REST request to delete SecuritymanagementWbs : {}", id);
        securitymanagementWbsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
