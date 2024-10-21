package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.ContractPayment;
import com.cvicse.jy1.repository.ContractPaymentRepository;
import com.cvicse.jy1.service.ContractPaymentService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.ContractPayment}.
 */
@RestController
@RequestMapping("/api/contract-payments")
public class ContractPaymentResource {

    private static final Logger log = LoggerFactory.getLogger(ContractPaymentResource.class);

    private static final String ENTITY_NAME = "contractPayment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContractPaymentService contractPaymentService;

    private final ContractPaymentRepository contractPaymentRepository;

    public ContractPaymentResource(ContractPaymentService contractPaymentService, ContractPaymentRepository contractPaymentRepository) {
        this.contractPaymentService = contractPaymentService;
        this.contractPaymentRepository = contractPaymentRepository;
    }

    /**
     * {@code POST  /contract-payments} : Create a new contractPayment.
     *
     * @param contractPayment the contractPayment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contractPayment, or with status {@code 400 (Bad Request)} if the contractPayment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ContractPayment> createContractPayment(@Valid @RequestBody ContractPayment contractPayment)
        throws URISyntaxException {
        log.debug("REST request to save ContractPayment : {}", contractPayment);
        if (contractPayment.getId() != null) {
            throw new BadRequestAlertException("A new contractPayment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        contractPayment = contractPaymentService.save(contractPayment);
        return ResponseEntity.created(new URI("/api/contract-payments/" + contractPayment.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, contractPayment.getId().toString()))
            .body(contractPayment);
    }

    /**
     * {@code PUT  /contract-payments/:id} : Updates an existing contractPayment.
     *
     * @param id the id of the contractPayment to save.
     * @param contractPayment the contractPayment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractPayment,
     * or with status {@code 400 (Bad Request)} if the contractPayment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contractPayment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContractPayment> updateContractPayment(
        @PathVariable(value = "id", required = false) final Integer id,
        @Valid @RequestBody ContractPayment contractPayment
    ) throws URISyntaxException {
        log.debug("REST request to update ContractPayment : {}, {}", id, contractPayment);
        if (contractPayment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractPayment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractPaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        contractPayment = contractPaymentService.update(contractPayment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contractPayment.getId().toString()))
            .body(contractPayment);
    }

    /**
     * {@code PATCH  /contract-payments/:id} : Partial updates given fields of an existing contractPayment, field will ignore if it is null
     *
     * @param id the id of the contractPayment to save.
     * @param contractPayment the contractPayment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractPayment,
     * or with status {@code 400 (Bad Request)} if the contractPayment is not valid,
     * or with status {@code 404 (Not Found)} if the contractPayment is not found,
     * or with status {@code 500 (Internal Server Error)} if the contractPayment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ContractPayment> partialUpdateContractPayment(
        @PathVariable(value = "id", required = false) final Integer id,
        @NotNull @RequestBody ContractPayment contractPayment
    ) throws URISyntaxException {
        log.debug("REST request to partial update ContractPayment partially : {}, {}", id, contractPayment);
        if (contractPayment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractPayment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractPaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ContractPayment> result = contractPaymentService.partialUpdate(contractPayment);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contractPayment.getId().toString())
        );
    }

    /**
     * {@code GET  /contract-payments} : get all the contractPayments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contractPayments in body.
     */
    @GetMapping("")
    public List<ContractPayment> getAllContractPayments() {
        log.debug("REST request to get all ContractPayments");
        return contractPaymentService.findAll();
    }

    /**
     * {@code GET  /contract-payments/:id} : get the "id" contractPayment.
     *
     * @param id the id of the contractPayment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contractPayment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContractPayment> getContractPayment(@PathVariable("id") Integer id) {
        log.debug("REST request to get ContractPayment : {}", id);
        Optional<ContractPayment> contractPayment = contractPaymentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contractPayment);
    }

    /**
     * {@code DELETE  /contract-payments/:id} : delete the "id" contractPayment.
     *
     * @param id the id of the contractPayment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContractPayment(@PathVariable("id") Integer id) {
        log.debug("REST request to delete ContractPayment : {}", id);
        contractPaymentService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
