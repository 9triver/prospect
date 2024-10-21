package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.PaymentCostList;
import com.cvicse.jy1.repository.PaymentCostListRepository;
import com.cvicse.jy1.service.PaymentCostListService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.PaymentCostList}.
 */
@RestController
@RequestMapping("/api/payment-cost-lists")
public class PaymentCostListResource {

    private static final Logger log = LoggerFactory.getLogger(PaymentCostListResource.class);

    private static final String ENTITY_NAME = "paymentCostList";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymentCostListService paymentCostListService;

    private final PaymentCostListRepository paymentCostListRepository;

    public PaymentCostListResource(PaymentCostListService paymentCostListService, PaymentCostListRepository paymentCostListRepository) {
        this.paymentCostListService = paymentCostListService;
        this.paymentCostListRepository = paymentCostListRepository;
    }

    /**
     * {@code POST  /payment-cost-lists} : Create a new paymentCostList.
     *
     * @param paymentCostList the paymentCostList to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentCostList, or with status {@code 400 (Bad Request)} if the paymentCostList has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<PaymentCostList> createPaymentCostList(@Valid @RequestBody PaymentCostList paymentCostList)
        throws URISyntaxException {
        log.debug("REST request to save PaymentCostList : {}", paymentCostList);
        if (paymentCostList.getId() != null) {
            throw new BadRequestAlertException("A new paymentCostList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        paymentCostList = paymentCostListService.save(paymentCostList);
        return ResponseEntity.created(new URI("/api/payment-cost-lists/" + paymentCostList.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, paymentCostList.getId().toString()))
            .body(paymentCostList);
    }

    /**
     * {@code PUT  /payment-cost-lists/:id} : Updates an existing paymentCostList.
     *
     * @param id the id of the paymentCostList to save.
     * @param paymentCostList the paymentCostList to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentCostList,
     * or with status {@code 400 (Bad Request)} if the paymentCostList is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymentCostList couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PaymentCostList> updatePaymentCostList(
        @PathVariable(value = "id", required = false) final Integer id,
        @Valid @RequestBody PaymentCostList paymentCostList
    ) throws URISyntaxException {
        log.debug("REST request to update PaymentCostList : {}, {}", id, paymentCostList);
        if (paymentCostList.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paymentCostList.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paymentCostListRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        paymentCostList = paymentCostListService.update(paymentCostList);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paymentCostList.getId().toString()))
            .body(paymentCostList);
    }

    /**
     * {@code PATCH  /payment-cost-lists/:id} : Partial updates given fields of an existing paymentCostList, field will ignore if it is null
     *
     * @param id the id of the paymentCostList to save.
     * @param paymentCostList the paymentCostList to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentCostList,
     * or with status {@code 400 (Bad Request)} if the paymentCostList is not valid,
     * or with status {@code 404 (Not Found)} if the paymentCostList is not found,
     * or with status {@code 500 (Internal Server Error)} if the paymentCostList couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PaymentCostList> partialUpdatePaymentCostList(
        @PathVariable(value = "id", required = false) final Integer id,
        @NotNull @RequestBody PaymentCostList paymentCostList
    ) throws URISyntaxException {
        log.debug("REST request to partial update PaymentCostList partially : {}, {}", id, paymentCostList);
        if (paymentCostList.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paymentCostList.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paymentCostListRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PaymentCostList> result = paymentCostListService.partialUpdate(paymentCostList);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paymentCostList.getId().toString())
        );
    }

    /**
     * {@code GET  /payment-cost-lists} : get all the paymentCostLists.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentCostLists in body.
     */
    @GetMapping("")
    public List<PaymentCostList> getAllPaymentCostLists() {
        log.debug("REST request to get all PaymentCostLists");
        return paymentCostListService.findAll();
    }

    /**
     * {@code GET  /payment-cost-lists/:id} : get the "id" paymentCostList.
     *
     * @param id the id of the paymentCostList to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentCostList, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PaymentCostList> getPaymentCostList(@PathVariable("id") Integer id) {
        log.debug("REST request to get PaymentCostList : {}", id);
        Optional<PaymentCostList> paymentCostList = paymentCostListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paymentCostList);
    }

    /**
     * {@code DELETE  /payment-cost-lists/:id} : delete the "id" paymentCostList.
     *
     * @param id the id of the paymentCostList to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentCostList(@PathVariable("id") Integer id) {
        log.debug("REST request to delete PaymentCostList : {}", id);
        paymentCostListService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
