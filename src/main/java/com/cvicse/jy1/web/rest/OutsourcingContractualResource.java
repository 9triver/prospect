package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.OutsourcingContractual;
import com.cvicse.jy1.repository.OutsourcingContractualRepository;
import com.cvicse.jy1.service.OutsourcingContractualService;
import com.cvicse.jy1.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.cvicse.jy1.domain.OutsourcingContractual}.
 */
@RestController
@RequestMapping("/api/outsourcing-contractuals")
public class OutsourcingContractualResource {

    private static final Logger log = LoggerFactory.getLogger(OutsourcingContractualResource.class);

    private static final String ENTITY_NAME = "outsourcingContractual";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OutsourcingContractualService outsourcingContractualService;

    private final OutsourcingContractualRepository outsourcingContractualRepository;

    public OutsourcingContractualResource(
        OutsourcingContractualService outsourcingContractualService,
        OutsourcingContractualRepository outsourcingContractualRepository
    ) {
        this.outsourcingContractualService = outsourcingContractualService;
        this.outsourcingContractualRepository = outsourcingContractualRepository;
    }

    /**
     * {@code POST  /outsourcing-contractuals} : Create a new outsourcingContractual.
     *
     * @param outsourcingContractual the outsourcingContractual to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new outsourcingContractual, or with status {@code 400 (Bad Request)} if the outsourcingContractual has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OutsourcingContractual> createOutsourcingContractual(@RequestBody OutsourcingContractual outsourcingContractual)
        throws URISyntaxException {
        log.debug("REST request to save OutsourcingContractual : {}", outsourcingContractual);
        if (outsourcingContractual.getId() != null) {
            throw new BadRequestAlertException("A new outsourcingContractual cannot already have an ID", ENTITY_NAME, "idexists");
        }
        outsourcingContractual = outsourcingContractualService.save(outsourcingContractual);
        return ResponseEntity.created(new URI("/api/outsourcing-contractuals/" + outsourcingContractual.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, outsourcingContractual.getId()))
            .body(outsourcingContractual);
    }

    /**
     * {@code PUT  /outsourcing-contractuals/:id} : Updates an existing outsourcingContractual.
     *
     * @param id the id of the outsourcingContractual to save.
     * @param outsourcingContractual the outsourcingContractual to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outsourcingContractual,
     * or with status {@code 400 (Bad Request)} if the outsourcingContractual is not valid,
     * or with status {@code 500 (Internal Server Error)} if the outsourcingContractual couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OutsourcingContractual> updateOutsourcingContractual(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody OutsourcingContractual outsourcingContractual
    ) throws URISyntaxException {
        log.debug("REST request to update OutsourcingContractual : {}, {}", id, outsourcingContractual);
        if (outsourcingContractual.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outsourcingContractual.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!outsourcingContractualRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        outsourcingContractual = outsourcingContractualService.update(outsourcingContractual);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, outsourcingContractual.getId()))
            .body(outsourcingContractual);
    }

    /**
     * {@code PATCH  /outsourcing-contractuals/:id} : Partial updates given fields of an existing outsourcingContractual, field will ignore if it is null
     *
     * @param id the id of the outsourcingContractual to save.
     * @param outsourcingContractual the outsourcingContractual to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outsourcingContractual,
     * or with status {@code 400 (Bad Request)} if the outsourcingContractual is not valid,
     * or with status {@code 404 (Not Found)} if the outsourcingContractual is not found,
     * or with status {@code 500 (Internal Server Error)} if the outsourcingContractual couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OutsourcingContractual> partialUpdateOutsourcingContractual(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody OutsourcingContractual outsourcingContractual
    ) throws URISyntaxException {
        log.debug("REST request to partial update OutsourcingContractual partially : {}, {}", id, outsourcingContractual);
        if (outsourcingContractual.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outsourcingContractual.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!outsourcingContractualRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OutsourcingContractual> result = outsourcingContractualService.partialUpdate(outsourcingContractual);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, outsourcingContractual.getId())
        );
    }

    /**
     * {@code GET  /outsourcing-contractuals} : get all the outsourcingContractuals.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of outsourcingContractuals in body.
     */
    @GetMapping("")
    public List<OutsourcingContractual> getAllOutsourcingContractuals(
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get all OutsourcingContractuals");
        return outsourcingContractualService.findAll();
    }

    /**
     * {@code GET  /outsourcing-contractuals/:id} : get the "id" outsourcingContractual.
     *
     * @param id the id of the outsourcingContractual to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the outsourcingContractual, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OutsourcingContractual> getOutsourcingContractual(@PathVariable("id") String id) {
        log.debug("REST request to get OutsourcingContractual : {}", id);
        Optional<OutsourcingContractual> outsourcingContractual = outsourcingContractualService.findOne(id);
        return ResponseUtil.wrapOrNotFound(outsourcingContractual);
    }

    /**
     * {@code DELETE  /outsourcing-contractuals/:id} : delete the "id" outsourcingContractual.
     *
     * @param id the id of the outsourcingContractual to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOutsourcingContractual(@PathVariable("id") String id) {
        log.debug("REST request to delete OutsourcingContractual : {}", id);
        outsourcingContractualService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
