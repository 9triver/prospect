package com.cvicse.web.rest;

import com.cvicse.domain.OutsourcingmanagementWbs;
import com.cvicse.repository.OutsourcingmanagementWbsRepository;
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
 * REST controller for managing {@link com.cvicse.domain.OutsourcingmanagementWbs}.
 */
@RestController
@RequestMapping("/api/outsourcingmanagement-wbs")
@Transactional
public class OutsourcingmanagementWbsResource {

    private final Logger log = LoggerFactory.getLogger(OutsourcingmanagementWbsResource.class);

    private static final String ENTITY_NAME = "outsourcingmanagementWbs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OutsourcingmanagementWbsRepository outsourcingmanagementWbsRepository;

    public OutsourcingmanagementWbsResource(OutsourcingmanagementWbsRepository outsourcingmanagementWbsRepository) {
        this.outsourcingmanagementWbsRepository = outsourcingmanagementWbsRepository;
    }

    /**
     * {@code POST  /outsourcingmanagement-wbs} : Create a new outsourcingmanagementWbs.
     *
     * @param outsourcingmanagementWbs the outsourcingmanagementWbs to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new outsourcingmanagementWbs, or with status {@code 400 (Bad Request)} if the outsourcingmanagementWbs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OutsourcingmanagementWbs> createOutsourcingmanagementWbs(
        @RequestBody OutsourcingmanagementWbs outsourcingmanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to save OutsourcingmanagementWbs : {}", outsourcingmanagementWbs);
        if (outsourcingmanagementWbs.getId() != null) {
            throw new BadRequestAlertException("A new outsourcingmanagementWbs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        outsourcingmanagementWbs = outsourcingmanagementWbsRepository.save(outsourcingmanagementWbs);
        return ResponseEntity.created(new URI("/api/outsourcingmanagement-wbs/" + outsourcingmanagementWbs.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, outsourcingmanagementWbs.getId()))
            .body(outsourcingmanagementWbs);
    }

    /**
     * {@code PUT  /outsourcingmanagement-wbs/:id} : Updates an existing outsourcingmanagementWbs.
     *
     * @param id the id of the outsourcingmanagementWbs to save.
     * @param outsourcingmanagementWbs the outsourcingmanagementWbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outsourcingmanagementWbs,
     * or with status {@code 400 (Bad Request)} if the outsourcingmanagementWbs is not valid,
     * or with status {@code 500 (Internal Server Error)} if the outsourcingmanagementWbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OutsourcingmanagementWbs> updateOutsourcingmanagementWbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody OutsourcingmanagementWbs outsourcingmanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to update OutsourcingmanagementWbs : {}, {}", id, outsourcingmanagementWbs);
        if (outsourcingmanagementWbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outsourcingmanagementWbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!outsourcingmanagementWbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        outsourcingmanagementWbs = outsourcingmanagementWbsRepository.save(outsourcingmanagementWbs);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, outsourcingmanagementWbs.getId()))
            .body(outsourcingmanagementWbs);
    }

    /**
     * {@code PATCH  /outsourcingmanagement-wbs/:id} : Partial updates given fields of an existing outsourcingmanagementWbs, field will ignore if it is null
     *
     * @param id the id of the outsourcingmanagementWbs to save.
     * @param outsourcingmanagementWbs the outsourcingmanagementWbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outsourcingmanagementWbs,
     * or with status {@code 400 (Bad Request)} if the outsourcingmanagementWbs is not valid,
     * or with status {@code 404 (Not Found)} if the outsourcingmanagementWbs is not found,
     * or with status {@code 500 (Internal Server Error)} if the outsourcingmanagementWbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OutsourcingmanagementWbs> partialUpdateOutsourcingmanagementWbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody OutsourcingmanagementWbs outsourcingmanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to partial update OutsourcingmanagementWbs partially : {}, {}", id, outsourcingmanagementWbs);
        if (outsourcingmanagementWbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outsourcingmanagementWbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!outsourcingmanagementWbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OutsourcingmanagementWbs> result = outsourcingmanagementWbsRepository
            .findById(outsourcingmanagementWbs.getId())
            .map(existingOutsourcingmanagementWbs -> {
                if (outsourcingmanagementWbs.getWbsspare1() != null) {
                    existingOutsourcingmanagementWbs.setWbsspare1(outsourcingmanagementWbs.getWbsspare1());
                }
                if (outsourcingmanagementWbs.getWbsspare2() != null) {
                    existingOutsourcingmanagementWbs.setWbsspare2(outsourcingmanagementWbs.getWbsspare2());
                }
                if (outsourcingmanagementWbs.getWbsspare3() != null) {
                    existingOutsourcingmanagementWbs.setWbsspare3(outsourcingmanagementWbs.getWbsspare3());
                }
                if (outsourcingmanagementWbs.getWbsspare4() != null) {
                    existingOutsourcingmanagementWbs.setWbsspare4(outsourcingmanagementWbs.getWbsspare4());
                }
                if (outsourcingmanagementWbs.getWbsspare5() != null) {
                    existingOutsourcingmanagementWbs.setWbsspare5(outsourcingmanagementWbs.getWbsspare5());
                }

                return existingOutsourcingmanagementWbs;
            })
            .map(outsourcingmanagementWbsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, outsourcingmanagementWbs.getId())
        );
    }

    /**
     * {@code GET  /outsourcingmanagement-wbs} : get all the outsourcingmanagementWbs.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of outsourcingmanagementWbs in body.
     */
    @GetMapping("")
    public List<OutsourcingmanagementWbs> getAllOutsourcingmanagementWbs(@RequestParam(name = "filter", required = false) String filter) {
        if ("outsourcingmanagement-is-null".equals(filter)) {
            log.debug("REST request to get all OutsourcingmanagementWbss where outsourcingmanagement is null");
            return StreamSupport.stream(outsourcingmanagementWbsRepository.findAll().spliterator(), false)
                .filter(outsourcingmanagementWbs -> outsourcingmanagementWbs.getOutsourcingmanagement() == null)
                .toList();
        }
        log.debug("REST request to get all OutsourcingmanagementWbs");
        return outsourcingmanagementWbsRepository.findAll();
    }

    /**
     * {@code GET  /outsourcingmanagement-wbs/:id} : get the "id" outsourcingmanagementWbs.
     *
     * @param id the id of the outsourcingmanagementWbs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the outsourcingmanagementWbs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OutsourcingmanagementWbs> getOutsourcingmanagementWbs(@PathVariable("id") String id) {
        log.debug("REST request to get OutsourcingmanagementWbs : {}", id);
        Optional<OutsourcingmanagementWbs> outsourcingmanagementWbs = outsourcingmanagementWbsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(outsourcingmanagementWbs);
    }

    /**
     * {@code DELETE  /outsourcingmanagement-wbs/:id} : delete the "id" outsourcingmanagementWbs.
     *
     * @param id the id of the outsourcingmanagementWbs to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOutsourcingmanagementWbs(@PathVariable("id") String id) {
        log.debug("REST request to delete OutsourcingmanagementWbs : {}", id);
        outsourcingmanagementWbsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
