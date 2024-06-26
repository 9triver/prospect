package com.cvicse.web.rest;

import com.cvicse.domain.IntegratedmanagementWbs;
import com.cvicse.repository.IntegratedmanagementWbsRepository;
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
 * REST controller for managing {@link com.cvicse.domain.IntegratedmanagementWbs}.
 */
@RestController
@RequestMapping("/api/integratedmanagement-wbs")
@Transactional
public class IntegratedmanagementWbsResource {

    private final Logger log = LoggerFactory.getLogger(IntegratedmanagementWbsResource.class);

    private static final String ENTITY_NAME = "integratedmanagementWbs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IntegratedmanagementWbsRepository integratedmanagementWbsRepository;

    public IntegratedmanagementWbsResource(IntegratedmanagementWbsRepository integratedmanagementWbsRepository) {
        this.integratedmanagementWbsRepository = integratedmanagementWbsRepository;
    }

    /**
     * {@code POST  /integratedmanagement-wbs} : Create a new integratedmanagementWbs.
     *
     * @param integratedmanagementWbs the integratedmanagementWbs to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new integratedmanagementWbs, or with status {@code 400 (Bad Request)} if the integratedmanagementWbs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<IntegratedmanagementWbs> createIntegratedmanagementWbs(
        @RequestBody IntegratedmanagementWbs integratedmanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to save IntegratedmanagementWbs : {}", integratedmanagementWbs);
        if (integratedmanagementWbs.getId() != null) {
            throw new BadRequestAlertException("A new integratedmanagementWbs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        integratedmanagementWbs = integratedmanagementWbsRepository.save(integratedmanagementWbs);
        return ResponseEntity.created(new URI("/api/integratedmanagement-wbs/" + integratedmanagementWbs.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, integratedmanagementWbs.getId()))
            .body(integratedmanagementWbs);
    }

    /**
     * {@code PUT  /integratedmanagement-wbs/:id} : Updates an existing integratedmanagementWbs.
     *
     * @param id the id of the integratedmanagementWbs to save.
     * @param integratedmanagementWbs the integratedmanagementWbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated integratedmanagementWbs,
     * or with status {@code 400 (Bad Request)} if the integratedmanagementWbs is not valid,
     * or with status {@code 500 (Internal Server Error)} if the integratedmanagementWbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<IntegratedmanagementWbs> updateIntegratedmanagementWbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody IntegratedmanagementWbs integratedmanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to update IntegratedmanagementWbs : {}, {}", id, integratedmanagementWbs);
        if (integratedmanagementWbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, integratedmanagementWbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!integratedmanagementWbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        integratedmanagementWbs = integratedmanagementWbsRepository.save(integratedmanagementWbs);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, integratedmanagementWbs.getId()))
            .body(integratedmanagementWbs);
    }

    /**
     * {@code PATCH  /integratedmanagement-wbs/:id} : Partial updates given fields of an existing integratedmanagementWbs, field will ignore if it is null
     *
     * @param id the id of the integratedmanagementWbs to save.
     * @param integratedmanagementWbs the integratedmanagementWbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated integratedmanagementWbs,
     * or with status {@code 400 (Bad Request)} if the integratedmanagementWbs is not valid,
     * or with status {@code 404 (Not Found)} if the integratedmanagementWbs is not found,
     * or with status {@code 500 (Internal Server Error)} if the integratedmanagementWbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<IntegratedmanagementWbs> partialUpdateIntegratedmanagementWbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody IntegratedmanagementWbs integratedmanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to partial update IntegratedmanagementWbs partially : {}, {}", id, integratedmanagementWbs);
        if (integratedmanagementWbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, integratedmanagementWbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!integratedmanagementWbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<IntegratedmanagementWbs> result = integratedmanagementWbsRepository
            .findById(integratedmanagementWbs.getId())
            .map(existingIntegratedmanagementWbs -> {
                if (integratedmanagementWbs.getWbsspare1() != null) {
                    existingIntegratedmanagementWbs.setWbsspare1(integratedmanagementWbs.getWbsspare1());
                }
                if (integratedmanagementWbs.getWbsspare2() != null) {
                    existingIntegratedmanagementWbs.setWbsspare2(integratedmanagementWbs.getWbsspare2());
                }
                if (integratedmanagementWbs.getWbsspare3() != null) {
                    existingIntegratedmanagementWbs.setWbsspare3(integratedmanagementWbs.getWbsspare3());
                }
                if (integratedmanagementWbs.getWbsspare4() != null) {
                    existingIntegratedmanagementWbs.setWbsspare4(integratedmanagementWbs.getWbsspare4());
                }
                if (integratedmanagementWbs.getWbsspare5() != null) {
                    existingIntegratedmanagementWbs.setWbsspare5(integratedmanagementWbs.getWbsspare5());
                }

                return existingIntegratedmanagementWbs;
            })
            .map(integratedmanagementWbsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, integratedmanagementWbs.getId())
        );
    }

    /**
     * {@code GET  /integratedmanagement-wbs} : get all the integratedmanagementWbs.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of integratedmanagementWbs in body.
     */
    @GetMapping("")
    public List<IntegratedmanagementWbs> getAllIntegratedmanagementWbs(@RequestParam(name = "filter", required = false) String filter) {
        if ("integratedmanagement-is-null".equals(filter)) {
            log.debug("REST request to get all IntegratedmanagementWbss where integratedmanagement is null");
            return StreamSupport.stream(integratedmanagementWbsRepository.findAll().spliterator(), false)
                .filter(integratedmanagementWbs -> integratedmanagementWbs.getIntegratedmanagement() == null)
                .toList();
        }
        log.debug("REST request to get all IntegratedmanagementWbs");
        return integratedmanagementWbsRepository.findAll();
    }

    /**
     * {@code GET  /integratedmanagement-wbs/:id} : get the "id" integratedmanagementWbs.
     *
     * @param id the id of the integratedmanagementWbs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the integratedmanagementWbs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<IntegratedmanagementWbs> getIntegratedmanagementWbs(@PathVariable("id") String id) {
        log.debug("REST request to get IntegratedmanagementWbs : {}", id);
        Optional<IntegratedmanagementWbs> integratedmanagementWbs = integratedmanagementWbsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(integratedmanagementWbs);
    }

    /**
     * {@code DELETE  /integratedmanagement-wbs/:id} : delete the "id" integratedmanagementWbs.
     *
     * @param id the id of the integratedmanagementWbs to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIntegratedmanagementWbs(@PathVariable("id") String id) {
        log.debug("REST request to delete IntegratedmanagementWbs : {}", id);
        integratedmanagementWbsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
