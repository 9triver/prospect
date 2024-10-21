package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.SporadicPurchasePayment;
import com.cvicse.jy1.repository.SporadicPurchasePaymentRepository;
import com.cvicse.jy1.service.SporadicPurchasePaymentService;
import com.cvicse.jy1.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.SporadicPurchasePayment}.
 */
@RestController
@RequestMapping("/api/sporadic-purchase-payments")
public class SporadicPurchasePaymentResource {

    private static final Logger log = LoggerFactory.getLogger(SporadicPurchasePaymentResource.class);

    private static final String ENTITY_NAME = "sporadicPurchasePayment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SporadicPurchasePaymentService sporadicPurchasePaymentService;

    private final SporadicPurchasePaymentRepository sporadicPurchasePaymentRepository;

    public SporadicPurchasePaymentResource(
        SporadicPurchasePaymentService sporadicPurchasePaymentService,
        SporadicPurchasePaymentRepository sporadicPurchasePaymentRepository
    ) {
        this.sporadicPurchasePaymentService = sporadicPurchasePaymentService;
        this.sporadicPurchasePaymentRepository = sporadicPurchasePaymentRepository;
    }

    /**
     * {@code POST  /sporadic-purchase-payments} : Create a new sporadicPurchasePayment.
     *
     * @param sporadicPurchasePayment the sporadicPurchasePayment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sporadicPurchasePayment, or with status {@code 400 (Bad Request)} if the sporadicPurchasePayment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<SporadicPurchasePayment> createSporadicPurchasePayment(
        @Valid @RequestBody SporadicPurchasePayment sporadicPurchasePayment
    ) throws URISyntaxException {
        log.debug("REST request to save SporadicPurchasePayment : {}", sporadicPurchasePayment);
        if (sporadicPurchasePayment.getId() != null) {
            throw new BadRequestAlertException("A new sporadicPurchasePayment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        sporadicPurchasePayment = sporadicPurchasePaymentService.save(sporadicPurchasePayment);
        return ResponseEntity.created(new URI("/api/sporadic-purchase-payments/" + sporadicPurchasePayment.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, sporadicPurchasePayment.getId().toString()))
            .body(sporadicPurchasePayment);
    }

    /**
     * {@code PUT  /sporadic-purchase-payments/:id} : Updates an existing sporadicPurchasePayment.
     *
     * @param id the id of the sporadicPurchasePayment to save.
     * @param sporadicPurchasePayment the sporadicPurchasePayment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sporadicPurchasePayment,
     * or with status {@code 400 (Bad Request)} if the sporadicPurchasePayment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sporadicPurchasePayment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SporadicPurchasePayment> updateSporadicPurchasePayment(
        @PathVariable(value = "id", required = false) final Integer id,
        @Valid @RequestBody SporadicPurchasePayment sporadicPurchasePayment
    ) throws URISyntaxException {
        log.debug("REST request to update SporadicPurchasePayment : {}, {}", id, sporadicPurchasePayment);
        if (sporadicPurchasePayment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sporadicPurchasePayment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sporadicPurchasePaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        sporadicPurchasePayment = sporadicPurchasePaymentService.update(sporadicPurchasePayment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sporadicPurchasePayment.getId().toString()))
            .body(sporadicPurchasePayment);
    }

    /**
     * {@code PATCH  /sporadic-purchase-payments/:id} : Partial updates given fields of an existing sporadicPurchasePayment, field will ignore if it is null
     *
     * @param id the id of the sporadicPurchasePayment to save.
     * @param sporadicPurchasePayment the sporadicPurchasePayment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sporadicPurchasePayment,
     * or with status {@code 400 (Bad Request)} if the sporadicPurchasePayment is not valid,
     * or with status {@code 404 (Not Found)} if the sporadicPurchasePayment is not found,
     * or with status {@code 500 (Internal Server Error)} if the sporadicPurchasePayment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SporadicPurchasePayment> partialUpdateSporadicPurchasePayment(
        @PathVariable(value = "id", required = false) final Integer id,
        @NotNull @RequestBody SporadicPurchasePayment sporadicPurchasePayment
    ) throws URISyntaxException {
        log.debug("REST request to partial update SporadicPurchasePayment partially : {}, {}", id, sporadicPurchasePayment);
        if (sporadicPurchasePayment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sporadicPurchasePayment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sporadicPurchasePaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SporadicPurchasePayment> result = sporadicPurchasePaymentService.partialUpdate(sporadicPurchasePayment);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sporadicPurchasePayment.getId().toString())
        );
    }

    /**
     * {@code GET  /sporadic-purchase-payments} : get all the sporadicPurchasePayments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sporadicPurchasePayments in body.
     */
    @GetMapping("")
    public List<SporadicPurchasePayment> getAllSporadicPurchasePayments() {
        log.debug("REST request to get all SporadicPurchasePayments");
        return sporadicPurchasePaymentService.findAll();
    }

    /**
     * {@code GET  /sporadic-purchase-payments/:id} : get the "id" sporadicPurchasePayment.
     *
     * @param id the id of the sporadicPurchasePayment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sporadicPurchasePayment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SporadicPurchasePayment> getSporadicPurchasePayment(@PathVariable("id") Integer id) {
        log.debug("REST request to get SporadicPurchasePayment : {}", id);
        Optional<SporadicPurchasePayment> sporadicPurchasePayment = sporadicPurchasePaymentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sporadicPurchasePayment);
    }

    /**
     * {@code DELETE  /sporadic-purchase-payments/:id} : delete the "id" sporadicPurchasePayment.
     *
     * @param id the id of the sporadicPurchasePayment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSporadicPurchasePayment(@PathVariable("id") Integer id) {
        log.debug("REST request to delete SporadicPurchasePayment : {}", id);
        sporadicPurchasePaymentService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
