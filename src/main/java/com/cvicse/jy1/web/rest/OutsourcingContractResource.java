package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.OutsourcingContract;
import com.cvicse.jy1.repository.OutsourcingContractRepository;
import com.cvicse.jy1.service.OutsourcingContractService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.OutsourcingContract}.
 */
@RestController
@RequestMapping("/api/outsourcing-contracts")
public class OutsourcingContractResource {

    private static final Logger log = LoggerFactory.getLogger(OutsourcingContractResource.class);

    private static final String ENTITY_NAME = "outsourcingContract";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OutsourcingContractService outsourcingContractService;

    private final OutsourcingContractRepository outsourcingContractRepository;

    public OutsourcingContractResource(
        OutsourcingContractService outsourcingContractService,
        OutsourcingContractRepository outsourcingContractRepository
    ) {
        this.outsourcingContractService = outsourcingContractService;
        this.outsourcingContractRepository = outsourcingContractRepository;
    }

    /**
     * {@code POST  /outsourcing-contracts} : Create a new outsourcingContract.
     *
     * @param outsourcingContract the outsourcingContract to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new outsourcingContract, or with status {@code 400 (Bad Request)} if the outsourcingContract has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OutsourcingContract> createOutsourcingContract(@Valid @RequestBody OutsourcingContract outsourcingContract)
        throws URISyntaxException {
        log.debug("REST request to save OutsourcingContract : {}", outsourcingContract);
        if (outsourcingContract.getId() != null) {
            throw new BadRequestAlertException("A new outsourcingContract cannot already have an ID", ENTITY_NAME, "idexists");
        }
        outsourcingContract = outsourcingContractService.save(outsourcingContract);
        return ResponseEntity.created(new URI("/api/outsourcing-contracts/" + outsourcingContract.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, outsourcingContract.getId().toString()))
            .body(outsourcingContract);
    }

    /**
     * {@code PUT  /outsourcing-contracts/:id} : Updates an existing outsourcingContract.
     *
     * @param id the id of the outsourcingContract to save.
     * @param outsourcingContract the outsourcingContract to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outsourcingContract,
     * or with status {@code 400 (Bad Request)} if the outsourcingContract is not valid,
     * or with status {@code 500 (Internal Server Error)} if the outsourcingContract couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OutsourcingContract> updateOutsourcingContract(
        @PathVariable(value = "id", required = false) final Integer id,
        @Valid @RequestBody OutsourcingContract outsourcingContract
    ) throws URISyntaxException {
        log.debug("REST request to update OutsourcingContract : {}, {}", id, outsourcingContract);
        if (outsourcingContract.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outsourcingContract.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!outsourcingContractRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        outsourcingContract = outsourcingContractService.update(outsourcingContract);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, outsourcingContract.getId().toString()))
            .body(outsourcingContract);
    }

    /**
     * {@code PATCH  /outsourcing-contracts/:id} : Partial updates given fields of an existing outsourcingContract, field will ignore if it is null
     *
     * @param id the id of the outsourcingContract to save.
     * @param outsourcingContract the outsourcingContract to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outsourcingContract,
     * or with status {@code 400 (Bad Request)} if the outsourcingContract is not valid,
     * or with status {@code 404 (Not Found)} if the outsourcingContract is not found,
     * or with status {@code 500 (Internal Server Error)} if the outsourcingContract couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OutsourcingContract> partialUpdateOutsourcingContract(
        @PathVariable(value = "id", required = false) final Integer id,
        @NotNull @RequestBody OutsourcingContract outsourcingContract
    ) throws URISyntaxException {
        log.debug("REST request to partial update OutsourcingContract partially : {}, {}", id, outsourcingContract);
        if (outsourcingContract.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outsourcingContract.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!outsourcingContractRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OutsourcingContract> result = outsourcingContractService.partialUpdate(outsourcingContract);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, outsourcingContract.getId().toString())
        );
    }

    /**
     * {@code GET  /outsourcing-contracts} : get all the outsourcingContracts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of outsourcingContracts in body.
     */
    @GetMapping("")
    public List<OutsourcingContract> getAllOutsourcingContracts() {
        log.debug("REST request to get all OutsourcingContracts");
        return outsourcingContractService.findAll();
    }

    /**
     * {@code GET  /outsourcing-contracts/:id} : get the "id" outsourcingContract.
     *
     * @param id the id of the outsourcingContract to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the outsourcingContract, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OutsourcingContract> getOutsourcingContract(@PathVariable("id") Integer id) {
        log.debug("REST request to get OutsourcingContract : {}", id);
        Optional<OutsourcingContract> outsourcingContract = outsourcingContractService.findOne(id);
        return ResponseUtil.wrapOrNotFound(outsourcingContract);
    }

    /**
     * {@code DELETE  /outsourcing-contracts/:id} : delete the "id" outsourcingContract.
     *
     * @param id the id of the outsourcingContract to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOutsourcingContract(@PathVariable("id") Integer id) {
        log.debug("REST request to delete OutsourcingContract : {}", id);
        outsourcingContractService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
