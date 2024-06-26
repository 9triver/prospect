package com.cvicse.web.rest;

import com.cvicse.domain.Outsourcingmanagement;
import com.cvicse.repository.OutsourcingmanagementRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Outsourcingmanagement}.
 */
@RestController
@RequestMapping("/api/outsourcingmanagements")
@Transactional
public class OutsourcingmanagementResource {

    private final Logger log = LoggerFactory.getLogger(OutsourcingmanagementResource.class);

    private static final String ENTITY_NAME = "outsourcingmanagement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OutsourcingmanagementRepository outsourcingmanagementRepository;

    public OutsourcingmanagementResource(OutsourcingmanagementRepository outsourcingmanagementRepository) {
        this.outsourcingmanagementRepository = outsourcingmanagementRepository;
    }

    /**
     * {@code POST  /outsourcingmanagements} : Create a new outsourcingmanagement.
     *
     * @param outsourcingmanagement the outsourcingmanagement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new outsourcingmanagement, or with status {@code 400 (Bad Request)} if the outsourcingmanagement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Outsourcingmanagement> createOutsourcingmanagement(@RequestBody Outsourcingmanagement outsourcingmanagement)
        throws URISyntaxException {
        log.debug("REST request to save Outsourcingmanagement : {}", outsourcingmanagement);
        if (outsourcingmanagement.getId() != null) {
            throw new BadRequestAlertException("A new outsourcingmanagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        outsourcingmanagement = outsourcingmanagementRepository.save(outsourcingmanagement);
        return ResponseEntity.created(new URI("/api/outsourcingmanagements/" + outsourcingmanagement.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, outsourcingmanagement.getId()))
            .body(outsourcingmanagement);
    }

    /**
     * {@code PUT  /outsourcingmanagements/:id} : Updates an existing outsourcingmanagement.
     *
     * @param id the id of the outsourcingmanagement to save.
     * @param outsourcingmanagement the outsourcingmanagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outsourcingmanagement,
     * or with status {@code 400 (Bad Request)} if the outsourcingmanagement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the outsourcingmanagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Outsourcingmanagement> updateOutsourcingmanagement(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Outsourcingmanagement outsourcingmanagement
    ) throws URISyntaxException {
        log.debug("REST request to update Outsourcingmanagement : {}, {}", id, outsourcingmanagement);
        if (outsourcingmanagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outsourcingmanagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!outsourcingmanagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        outsourcingmanagement = outsourcingmanagementRepository.save(outsourcingmanagement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, outsourcingmanagement.getId()))
            .body(outsourcingmanagement);
    }

    /**
     * {@code PATCH  /outsourcingmanagements/:id} : Partial updates given fields of an existing outsourcingmanagement, field will ignore if it is null
     *
     * @param id the id of the outsourcingmanagement to save.
     * @param outsourcingmanagement the outsourcingmanagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outsourcingmanagement,
     * or with status {@code 400 (Bad Request)} if the outsourcingmanagement is not valid,
     * or with status {@code 404 (Not Found)} if the outsourcingmanagement is not found,
     * or with status {@code 500 (Internal Server Error)} if the outsourcingmanagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Outsourcingmanagement> partialUpdateOutsourcingmanagement(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Outsourcingmanagement outsourcingmanagement
    ) throws URISyntaxException {
        log.debug("REST request to partial update Outsourcingmanagement partially : {}, {}", id, outsourcingmanagement);
        if (outsourcingmanagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outsourcingmanagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!outsourcingmanagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Outsourcingmanagement> result = outsourcingmanagementRepository
            .findById(outsourcingmanagement.getId())
            .map(existingOutsourcingmanagement -> {
                if (outsourcingmanagement.getName() != null) {
                    existingOutsourcingmanagement.setName(outsourcingmanagement.getName());
                }
                if (outsourcingmanagement.getDescription() != null) {
                    existingOutsourcingmanagement.setDescription(outsourcingmanagement.getDescription());
                }
                if (outsourcingmanagement.getStarttime() != null) {
                    existingOutsourcingmanagement.setStarttime(outsourcingmanagement.getStarttime());
                }
                if (outsourcingmanagement.getEndtime() != null) {
                    existingOutsourcingmanagement.setEndtime(outsourcingmanagement.getEndtime());
                }

                return existingOutsourcingmanagement;
            })
            .map(outsourcingmanagementRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, outsourcingmanagement.getId())
        );
    }

    /**
     * {@code GET  /outsourcingmanagements} : get all the outsourcingmanagements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of outsourcingmanagements in body.
     */
    @GetMapping("")
    public List<Outsourcingmanagement> getAllOutsourcingmanagements() {
        log.debug("REST request to get all Outsourcingmanagements");
        return outsourcingmanagementRepository.findAll();
    }

    /**
     * {@code GET  /outsourcingmanagements/:id} : get the "id" outsourcingmanagement.
     *
     * @param id the id of the outsourcingmanagement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the outsourcingmanagement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Outsourcingmanagement> getOutsourcingmanagement(@PathVariable("id") String id) {
        log.debug("REST request to get Outsourcingmanagement : {}", id);
        Optional<Outsourcingmanagement> outsourcingmanagement = outsourcingmanagementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(outsourcingmanagement);
    }

    /**
     * {@code DELETE  /outsourcingmanagements/:id} : delete the "id" outsourcingmanagement.
     *
     * @param id the id of the outsourcingmanagement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOutsourcingmanagement(@PathVariable("id") String id) {
        log.debug("REST request to delete Outsourcingmanagement : {}", id);
        outsourcingmanagementRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
