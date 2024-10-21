package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.CustomerSatisfaction;
import com.cvicse.jy1.repository.CustomerSatisfactionRepository;
import com.cvicse.jy1.service.CustomerSatisfactionService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.CustomerSatisfaction}.
 */
@RestController
@RequestMapping("/api/customer-satisfactions")
public class CustomerSatisfactionResource {

    private static final Logger log = LoggerFactory.getLogger(CustomerSatisfactionResource.class);

    private static final String ENTITY_NAME = "customerSatisfaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomerSatisfactionService customerSatisfactionService;

    private final CustomerSatisfactionRepository customerSatisfactionRepository;

    public CustomerSatisfactionResource(
        CustomerSatisfactionService customerSatisfactionService,
        CustomerSatisfactionRepository customerSatisfactionRepository
    ) {
        this.customerSatisfactionService = customerSatisfactionService;
        this.customerSatisfactionRepository = customerSatisfactionRepository;
    }

    /**
     * {@code POST  /customer-satisfactions} : Create a new customerSatisfaction.
     *
     * @param customerSatisfaction the customerSatisfaction to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customerSatisfaction, or with status {@code 400 (Bad Request)} if the customerSatisfaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CustomerSatisfaction> createCustomerSatisfaction(@RequestBody CustomerSatisfaction customerSatisfaction)
        throws URISyntaxException {
        log.debug("REST request to save CustomerSatisfaction : {}", customerSatisfaction);
        if (customerSatisfaction.getId() != null) {
            throw new BadRequestAlertException("A new customerSatisfaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        customerSatisfaction = customerSatisfactionService.save(customerSatisfaction);
        return ResponseEntity.created(new URI("/api/customer-satisfactions/" + customerSatisfaction.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, customerSatisfaction.getId().toString()))
            .body(customerSatisfaction);
    }

    /**
     * {@code PUT  /customer-satisfactions/:id} : Updates an existing customerSatisfaction.
     *
     * @param id the id of the customerSatisfaction to save.
     * @param customerSatisfaction the customerSatisfaction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerSatisfaction,
     * or with status {@code 400 (Bad Request)} if the customerSatisfaction is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customerSatisfaction couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomerSatisfaction> updateCustomerSatisfaction(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody CustomerSatisfaction customerSatisfaction
    ) throws URISyntaxException {
        log.debug("REST request to update CustomerSatisfaction : {}, {}", id, customerSatisfaction);
        if (customerSatisfaction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customerSatisfaction.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customerSatisfactionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        customerSatisfaction = customerSatisfactionService.update(customerSatisfaction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customerSatisfaction.getId().toString()))
            .body(customerSatisfaction);
    }

    /**
     * {@code PATCH  /customer-satisfactions/:id} : Partial updates given fields of an existing customerSatisfaction, field will ignore if it is null
     *
     * @param id the id of the customerSatisfaction to save.
     * @param customerSatisfaction the customerSatisfaction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerSatisfaction,
     * or with status {@code 400 (Bad Request)} if the customerSatisfaction is not valid,
     * or with status {@code 404 (Not Found)} if the customerSatisfaction is not found,
     * or with status {@code 500 (Internal Server Error)} if the customerSatisfaction couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CustomerSatisfaction> partialUpdateCustomerSatisfaction(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody CustomerSatisfaction customerSatisfaction
    ) throws URISyntaxException {
        log.debug("REST request to partial update CustomerSatisfaction partially : {}, {}", id, customerSatisfaction);
        if (customerSatisfaction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customerSatisfaction.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customerSatisfactionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CustomerSatisfaction> result = customerSatisfactionService.partialUpdate(customerSatisfaction);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customerSatisfaction.getId().toString())
        );
    }

    /**
     * {@code GET  /customer-satisfactions} : get all the customerSatisfactions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customerSatisfactions in body.
     */
    @GetMapping("")
    public List<CustomerSatisfaction> getAllCustomerSatisfactions() {
        log.debug("REST request to get all CustomerSatisfactions");
        return customerSatisfactionService.findAll();
    }

    /**
     * {@code GET  /customer-satisfactions/:id} : get the "id" customerSatisfaction.
     *
     * @param id the id of the customerSatisfaction to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customerSatisfaction, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerSatisfaction> getCustomerSatisfaction(@PathVariable("id") Integer id) {
        log.debug("REST request to get CustomerSatisfaction : {}", id);
        Optional<CustomerSatisfaction> customerSatisfaction = customerSatisfactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerSatisfaction);
    }

    /**
     * {@code DELETE  /customer-satisfactions/:id} : delete the "id" customerSatisfaction.
     *
     * @param id the id of the customerSatisfaction to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerSatisfaction(@PathVariable("id") Integer id) {
        log.debug("REST request to delete CustomerSatisfaction : {}", id);
        customerSatisfactionService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
