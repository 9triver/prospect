package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.SharePayment;
import com.cvicse.jy1.repository.SharePaymentRepository;
import com.cvicse.jy1.service.SharePaymentService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.SharePayment}.
 */
@RestController
@RequestMapping("/api/share-payments")
public class SharePaymentResource {

    private static final Logger log = LoggerFactory.getLogger(SharePaymentResource.class);

    private static final String ENTITY_NAME = "sharePayment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SharePaymentService sharePaymentService;

    private final SharePaymentRepository sharePaymentRepository;

    public SharePaymentResource(SharePaymentService sharePaymentService, SharePaymentRepository sharePaymentRepository) {
        this.sharePaymentService = sharePaymentService;
        this.sharePaymentRepository = sharePaymentRepository;
    }

    /**
     * {@code POST  /share-payments} : Create a new sharePayment.
     *
     * @param sharePayment the sharePayment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sharePayment, or with status {@code 400 (Bad Request)} if the sharePayment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<SharePayment> createSharePayment(@Valid @RequestBody SharePayment sharePayment) throws URISyntaxException {
        log.debug("REST request to save SharePayment : {}", sharePayment);
        if (sharePayment.getId() != null) {
            throw new BadRequestAlertException("A new sharePayment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        sharePayment = sharePaymentService.save(sharePayment);
        return ResponseEntity.created(new URI("/api/share-payments/" + sharePayment.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, sharePayment.getId().toString()))
            .body(sharePayment);
    }

    /**
     * {@code PUT  /share-payments/:id} : Updates an existing sharePayment.
     *
     * @param id the id of the sharePayment to save.
     * @param sharePayment the sharePayment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sharePayment,
     * or with status {@code 400 (Bad Request)} if the sharePayment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sharePayment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SharePayment> updateSharePayment(
        @PathVariable(value = "id", required = false) final Integer id,
        @Valid @RequestBody SharePayment sharePayment
    ) throws URISyntaxException {
        log.debug("REST request to update SharePayment : {}, {}", id, sharePayment);
        if (sharePayment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sharePayment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sharePaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        sharePayment = sharePaymentService.update(sharePayment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sharePayment.getId().toString()))
            .body(sharePayment);
    }

    /**
     * {@code PATCH  /share-payments/:id} : Partial updates given fields of an existing sharePayment, field will ignore if it is null
     *
     * @param id the id of the sharePayment to save.
     * @param sharePayment the sharePayment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sharePayment,
     * or with status {@code 400 (Bad Request)} if the sharePayment is not valid,
     * or with status {@code 404 (Not Found)} if the sharePayment is not found,
     * or with status {@code 500 (Internal Server Error)} if the sharePayment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SharePayment> partialUpdateSharePayment(
        @PathVariable(value = "id", required = false) final Integer id,
        @NotNull @RequestBody SharePayment sharePayment
    ) throws URISyntaxException {
        log.debug("REST request to partial update SharePayment partially : {}, {}", id, sharePayment);
        if (sharePayment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sharePayment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sharePaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SharePayment> result = sharePaymentService.partialUpdate(sharePayment);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sharePayment.getId().toString())
        );
    }

    /**
     * {@code GET  /share-payments} : get all the sharePayments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sharePayments in body.
     */
    @GetMapping("")
    public List<SharePayment> getAllSharePayments() {
        log.debug("REST request to get all SharePayments");
        return sharePaymentService.findAll();
    }

    /**
     * {@code GET  /share-payments/:id} : get the "id" sharePayment.
     *
     * @param id the id of the sharePayment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sharePayment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SharePayment> getSharePayment(@PathVariable("id") Integer id) {
        log.debug("REST request to get SharePayment : {}", id);
        Optional<SharePayment> sharePayment = sharePaymentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sharePayment);
    }

    /**
     * {@code DELETE  /share-payments/:id} : delete the "id" sharePayment.
     *
     * @param id the id of the sharePayment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSharePayment(@PathVariable("id") Integer id) {
        log.debug("REST request to delete SharePayment : {}", id);
        sharePaymentService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
