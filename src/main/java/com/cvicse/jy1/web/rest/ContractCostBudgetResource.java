package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.ContractCostBudget;
import com.cvicse.jy1.repository.ContractCostBudgetRepository;
import com.cvicse.jy1.service.ContractCostBudgetService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.ContractCostBudget}.
 */
@RestController
@RequestMapping("/api/contract-cost-budgets")
public class ContractCostBudgetResource {

    private static final Logger log = LoggerFactory.getLogger(ContractCostBudgetResource.class);

    private static final String ENTITY_NAME = "contractCostBudget";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContractCostBudgetService contractCostBudgetService;

    private final ContractCostBudgetRepository contractCostBudgetRepository;

    public ContractCostBudgetResource(
        ContractCostBudgetService contractCostBudgetService,
        ContractCostBudgetRepository contractCostBudgetRepository
    ) {
        this.contractCostBudgetService = contractCostBudgetService;
        this.contractCostBudgetRepository = contractCostBudgetRepository;
    }

    /**
     * {@code POST  /contract-cost-budgets} : Create a new contractCostBudget.
     *
     * @param contractCostBudget the contractCostBudget to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contractCostBudget, or with status {@code 400 (Bad Request)} if the contractCostBudget has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ContractCostBudget> createContractCostBudget(@RequestBody ContractCostBudget contractCostBudget)
        throws URISyntaxException {
        log.debug("REST request to save ContractCostBudget : {}", contractCostBudget);
        if (contractCostBudget.getId() != null) {
            throw new BadRequestAlertException("A new contractCostBudget cannot already have an ID", ENTITY_NAME, "idexists");
        }
        contractCostBudget = contractCostBudgetService.save(contractCostBudget);
        return ResponseEntity.created(new URI("/api/contract-cost-budgets/" + contractCostBudget.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, contractCostBudget.getId()))
            .body(contractCostBudget);
    }

    /**
     * {@code PUT  /contract-cost-budgets/:id} : Updates an existing contractCostBudget.
     *
     * @param id the id of the contractCostBudget to save.
     * @param contractCostBudget the contractCostBudget to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractCostBudget,
     * or with status {@code 400 (Bad Request)} if the contractCostBudget is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contractCostBudget couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContractCostBudget> updateContractCostBudget(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ContractCostBudget contractCostBudget
    ) throws URISyntaxException {
        log.debug("REST request to update ContractCostBudget : {}, {}", id, contractCostBudget);
        if (contractCostBudget.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractCostBudget.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractCostBudgetRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        contractCostBudget = contractCostBudgetService.update(contractCostBudget);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contractCostBudget.getId()))
            .body(contractCostBudget);
    }

    /**
     * {@code PATCH  /contract-cost-budgets/:id} : Partial updates given fields of an existing contractCostBudget, field will ignore if it is null
     *
     * @param id the id of the contractCostBudget to save.
     * @param contractCostBudget the contractCostBudget to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractCostBudget,
     * or with status {@code 400 (Bad Request)} if the contractCostBudget is not valid,
     * or with status {@code 404 (Not Found)} if the contractCostBudget is not found,
     * or with status {@code 500 (Internal Server Error)} if the contractCostBudget couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ContractCostBudget> partialUpdateContractCostBudget(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ContractCostBudget contractCostBudget
    ) throws URISyntaxException {
        log.debug("REST request to partial update ContractCostBudget partially : {}, {}", id, contractCostBudget);
        if (contractCostBudget.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractCostBudget.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractCostBudgetRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ContractCostBudget> result = contractCostBudgetService.partialUpdate(contractCostBudget);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contractCostBudget.getId())
        );
    }

    /**
     * {@code GET  /contract-cost-budgets} : get all the contractCostBudgets.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contractCostBudgets in body.
     */
    @GetMapping("")
    public List<ContractCostBudget> getAllContractCostBudgets(
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get all ContractCostBudgets");
        return contractCostBudgetService.findAll();
    }

    /**
     * {@code GET  /contract-cost-budgets/:id} : get the "id" contractCostBudget.
     *
     * @param id the id of the contractCostBudget to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contractCostBudget, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContractCostBudget> getContractCostBudget(@PathVariable("id") String id) {
        log.debug("REST request to get ContractCostBudget : {}", id);
        Optional<ContractCostBudget> contractCostBudget = contractCostBudgetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contractCostBudget);
    }

    /**
     * {@code DELETE  /contract-cost-budgets/:id} : delete the "id" contractCostBudget.
     *
     * @param id the id of the contractCostBudget to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContractCostBudget(@PathVariable("id") String id) {
        log.debug("REST request to delete ContractCostBudget : {}", id);
        contractCostBudgetService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
