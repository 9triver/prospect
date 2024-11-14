package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.OtherPayment;
import com.cvicse.jy1.repository.OtherPaymentRepository;
import com.cvicse.jy1.service.OtherPaymentService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.OtherPayment}.
 */
@RestController
@RequestMapping("/api/other-payments")
public class OtherPaymentResource {

    private static final Logger log = LoggerFactory.getLogger(OtherPaymentResource.class);

    private static final String ENTITY_NAME = "otherPayment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OtherPaymentService otherPaymentService;

    private final OtherPaymentRepository otherPaymentRepository;

    public OtherPaymentResource(OtherPaymentService otherPaymentService, OtherPaymentRepository otherPaymentRepository) {
        this.otherPaymentService = otherPaymentService;
        this.otherPaymentRepository = otherPaymentRepository;
    }

    /**
     * {@code POST  /other-payments} : Create a new otherPayment.
     *
     * @param otherPayment the otherPayment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new otherPayment, or with status {@code 400 (Bad Request)} if the otherPayment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OtherPayment> createOtherPayment(@RequestBody OtherPayment otherPayment) throws URISyntaxException {
        log.debug("REST request to save OtherPayment : {}", otherPayment);
        if (otherPayment.getId() != null) {
            throw new BadRequestAlertException("A new otherPayment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        otherPayment = otherPaymentService.save(otherPayment);
        return ResponseEntity.created(new URI("/api/other-payments/" + otherPayment.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, otherPayment.getId().toString()))
            .body(otherPayment);
    }

    /**
     * {@code PUT  /other-payments/:id} : Updates an existing otherPayment.
     *
     * @param id the id of the otherPayment to save.
     * @param otherPayment the otherPayment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated otherPayment,
     * or with status {@code 400 (Bad Request)} if the otherPayment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the otherPayment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OtherPayment> updateOtherPayment(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody OtherPayment otherPayment
    ) throws URISyntaxException {
        log.debug("REST request to update OtherPayment : {}, {}", id, otherPayment);
        if (otherPayment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, otherPayment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!otherPaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        otherPayment = otherPaymentService.update(otherPayment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, otherPayment.getId().toString()))
            .body(otherPayment);
    }

    /**
     * {@code PATCH  /other-payments/:id} : Partial updates given fields of an existing otherPayment, field will ignore if it is null
     *
     * @param id the id of the otherPayment to save.
     * @param otherPayment the otherPayment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated otherPayment,
     * or with status {@code 400 (Bad Request)} if the otherPayment is not valid,
     * or with status {@code 404 (Not Found)} if the otherPayment is not found,
     * or with status {@code 500 (Internal Server Error)} if the otherPayment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OtherPayment> partialUpdateOtherPayment(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody OtherPayment otherPayment
    ) throws URISyntaxException {
        log.debug("REST request to partial update OtherPayment partially : {}, {}", id, otherPayment);
        if (otherPayment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, otherPayment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!otherPaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OtherPayment> result = otherPaymentService.partialUpdate(otherPayment);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, otherPayment.getId().toString())
        );
    }

    /**
     * {@code GET  /other-payments} : get all the otherPayments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of otherPayments in body.
     */
    @GetMapping("")
    public List<OtherPayment> getAllOtherPayments() {
        log.debug("REST request to get all OtherPayments");
        return otherPaymentService.findAll();
    }

    /**
     * {@code GET  /other-payments/:id} : get the "id" otherPayment.
     *
     * @param id the id of the otherPayment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the otherPayment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OtherPayment> getOtherPayment(@PathVariable("id") Integer id) {
        log.debug("REST request to get OtherPayment : {}", id);
        Optional<OtherPayment> otherPayment = otherPaymentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(otherPayment);
    }

    /**
     * {@code DELETE  /other-payments/:id} : delete the "id" otherPayment.
     *
     * @param id the id of the otherPayment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOtherPayment(@PathVariable("id") Integer id) {
        log.debug("REST request to delete OtherPayment : {}", id);
        otherPaymentService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
