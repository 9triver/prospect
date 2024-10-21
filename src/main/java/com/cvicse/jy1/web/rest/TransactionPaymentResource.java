package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.TransactionPayment;
import com.cvicse.jy1.repository.TransactionPaymentRepository;
import com.cvicse.jy1.service.TransactionPaymentService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.TransactionPayment}.
 */
@RestController
@RequestMapping("/api/transaction-payments")
public class TransactionPaymentResource {

    private static final Logger log = LoggerFactory.getLogger(TransactionPaymentResource.class);

    private static final String ENTITY_NAME = "transactionPayment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TransactionPaymentService transactionPaymentService;

    private final TransactionPaymentRepository transactionPaymentRepository;

    public TransactionPaymentResource(
        TransactionPaymentService transactionPaymentService,
        TransactionPaymentRepository transactionPaymentRepository
    ) {
        this.transactionPaymentService = transactionPaymentService;
        this.transactionPaymentRepository = transactionPaymentRepository;
    }

    /**
     * {@code POST  /transaction-payments} : Create a new transactionPayment.
     *
     * @param transactionPayment the transactionPayment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transactionPayment, or with status {@code 400 (Bad Request)} if the transactionPayment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TransactionPayment> createTransactionPayment(@Valid @RequestBody TransactionPayment transactionPayment)
        throws URISyntaxException {
        log.debug("REST request to save TransactionPayment : {}", transactionPayment);
        if (transactionPayment.getId() != null) {
            throw new BadRequestAlertException("A new transactionPayment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        transactionPayment = transactionPaymentService.save(transactionPayment);
        return ResponseEntity.created(new URI("/api/transaction-payments/" + transactionPayment.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, transactionPayment.getId().toString()))
            .body(transactionPayment);
    }

    /**
     * {@code PUT  /transaction-payments/:id} : Updates an existing transactionPayment.
     *
     * @param id the id of the transactionPayment to save.
     * @param transactionPayment the transactionPayment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transactionPayment,
     * or with status {@code 400 (Bad Request)} if the transactionPayment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transactionPayment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TransactionPayment> updateTransactionPayment(
        @PathVariable(value = "id", required = false) final Integer id,
        @Valid @RequestBody TransactionPayment transactionPayment
    ) throws URISyntaxException {
        log.debug("REST request to update TransactionPayment : {}, {}", id, transactionPayment);
        if (transactionPayment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, transactionPayment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!transactionPaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        transactionPayment = transactionPaymentService.update(transactionPayment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, transactionPayment.getId().toString()))
            .body(transactionPayment);
    }

    /**
     * {@code PATCH  /transaction-payments/:id} : Partial updates given fields of an existing transactionPayment, field will ignore if it is null
     *
     * @param id the id of the transactionPayment to save.
     * @param transactionPayment the transactionPayment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transactionPayment,
     * or with status {@code 400 (Bad Request)} if the transactionPayment is not valid,
     * or with status {@code 404 (Not Found)} if the transactionPayment is not found,
     * or with status {@code 500 (Internal Server Error)} if the transactionPayment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TransactionPayment> partialUpdateTransactionPayment(
        @PathVariable(value = "id", required = false) final Integer id,
        @NotNull @RequestBody TransactionPayment transactionPayment
    ) throws URISyntaxException {
        log.debug("REST request to partial update TransactionPayment partially : {}, {}", id, transactionPayment);
        if (transactionPayment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, transactionPayment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!transactionPaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TransactionPayment> result = transactionPaymentService.partialUpdate(transactionPayment);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, transactionPayment.getId().toString())
        );
    }

    /**
     * {@code GET  /transaction-payments} : get all the transactionPayments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transactionPayments in body.
     */
    @GetMapping("")
    public List<TransactionPayment> getAllTransactionPayments() {
        log.debug("REST request to get all TransactionPayments");
        return transactionPaymentService.findAll();
    }

    /**
     * {@code GET  /transaction-payments/:id} : get the "id" transactionPayment.
     *
     * @param id the id of the transactionPayment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transactionPayment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TransactionPayment> getTransactionPayment(@PathVariable("id") Integer id) {
        log.debug("REST request to get TransactionPayment : {}", id);
        Optional<TransactionPayment> transactionPayment = transactionPaymentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transactionPayment);
    }

    /**
     * {@code DELETE  /transaction-payments/:id} : delete the "id" transactionPayment.
     *
     * @param id the id of the transactionPayment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactionPayment(@PathVariable("id") Integer id) {
        log.debug("REST request to delete TransactionPayment : {}", id);
        transactionPaymentService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
