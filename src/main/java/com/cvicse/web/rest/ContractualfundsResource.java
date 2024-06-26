package com.cvicse.web.rest;

import com.cvicse.domain.Contractualfunds;
import com.cvicse.repository.ContractualfundsRepository;
import com.cvicse.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.cvicse.domain.Contractualfunds}.
 */
@RestController
@RequestMapping("/api/contractualfunds")
@Transactional
public class ContractualfundsResource {

    private final Logger log = LoggerFactory.getLogger(ContractualfundsResource.class);

    private static final String ENTITY_NAME = "contractualfunds";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContractualfundsRepository contractualfundsRepository;

    public ContractualfundsResource(ContractualfundsRepository contractualfundsRepository) {
        this.contractualfundsRepository = contractualfundsRepository;
    }

    /**
     * {@code POST  /contractualfunds} : Create a new contractualfunds.
     *
     * @param contractualfunds the contractualfunds to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contractualfunds, or with status {@code 400 (Bad Request)} if the contractualfunds has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Contractualfunds> createContractualfunds(@RequestBody Contractualfunds contractualfunds)
        throws URISyntaxException {
        log.debug("REST request to save Contractualfunds : {}", contractualfunds);
        if (contractualfunds.getId() != null) {
            throw new BadRequestAlertException("A new contractualfunds cannot already have an ID", ENTITY_NAME, "idexists");
        }
        contractualfunds = contractualfundsRepository.save(contractualfunds);
        return ResponseEntity.created(new URI("/api/contractualfunds/" + contractualfunds.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, contractualfunds.getId()))
            .body(contractualfunds);
    }

    /**
     * {@code PUT  /contractualfunds/:id} : Updates an existing contractualfunds.
     *
     * @param id the id of the contractualfunds to save.
     * @param contractualfunds the contractualfunds to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractualfunds,
     * or with status {@code 400 (Bad Request)} if the contractualfunds is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contractualfunds couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Contractualfunds> updateContractualfunds(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Contractualfunds contractualfunds
    ) throws URISyntaxException {
        log.debug("REST request to update Contractualfunds : {}, {}", id, contractualfunds);
        if (contractualfunds.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractualfunds.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractualfundsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        contractualfunds = contractualfundsRepository.save(contractualfunds);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contractualfunds.getId()))
            .body(contractualfunds);
    }

    /**
     * {@code PATCH  /contractualfunds/:id} : Partial updates given fields of an existing contractualfunds, field will ignore if it is null
     *
     * @param id the id of the contractualfunds to save.
     * @param contractualfunds the contractualfunds to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractualfunds,
     * or with status {@code 400 (Bad Request)} if the contractualfunds is not valid,
     * or with status {@code 404 (Not Found)} if the contractualfunds is not found,
     * or with status {@code 500 (Internal Server Error)} if the contractualfunds couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Contractualfunds> partialUpdateContractualfunds(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Contractualfunds contractualfunds
    ) throws URISyntaxException {
        log.debug("REST request to partial update Contractualfunds partially : {}, {}", id, contractualfunds);
        if (contractualfunds.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractualfunds.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractualfundsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Contractualfunds> result = contractualfundsRepository
            .findById(contractualfunds.getId())
            .map(existingContractualfunds -> {
                if (contractualfunds.getDepartment() != null) {
                    existingContractualfunds.setDepartment(contractualfunds.getDepartment());
                }
                if (contractualfunds.getYear() != null) {
                    existingContractualfunds.setYear(contractualfunds.getYear());
                }
                if (contractualfunds.getStarttime() != null) {
                    existingContractualfunds.setStarttime(contractualfunds.getStarttime());
                }
                if (contractualfunds.getEndtime() != null) {
                    existingContractualfunds.setEndtime(contractualfunds.getEndtime());
                }
                if (contractualfunds.getStatus() != null) {
                    existingContractualfunds.setStatus(contractualfunds.getStatus());
                }
                if (contractualfunds.getSecretlevel() != null) {
                    existingContractualfunds.setSecretlevel(contractualfunds.getSecretlevel());
                }
                if (contractualfunds.getForeigncurrency() != null) {
                    existingContractualfunds.setForeigncurrency(contractualfunds.getForeigncurrency());
                }
                if (contractualfunds.getTotalbudget() != null) {
                    existingContractualfunds.setTotalbudget(contractualfunds.getTotalbudget());
                }
                if (contractualfunds.getFundsinplace() != null) {
                    existingContractualfunds.setFundsinplace(contractualfunds.getFundsinplace());
                }
                if (contractualfunds.getResponsibleunitname() != null) {
                    existingContractualfunds.setResponsibleunitname(contractualfunds.getResponsibleunitname());
                }
                if (contractualfunds.getAudittime() != null) {
                    existingContractualfunds.setAudittime(contractualfunds.getAudittime());
                }
                if (contractualfunds.getAccountbank() != null) {
                    existingContractualfunds.setAccountbank(contractualfunds.getAccountbank());
                }

                return existingContractualfunds;
            })
            .map(contractualfundsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contractualfunds.getId())
        );
    }

    /**
     * {@code GET  /contractualfunds} : get all the contractualfunds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contractualfunds in body.
     */
    @GetMapping("")
    public List<Contractualfunds> getAllContractualfunds() {
        log.debug("REST request to get all Contractualfunds");
        return contractualfundsRepository.findAll();
    }

    /**
     * {@code GET  /contractualfunds/:id} : get the "id" contractualfunds.
     *
     * @param id the id of the contractualfunds to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contractualfunds, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Contractualfunds> getContractualfunds(@PathVariable("id") String id) {
        log.debug("REST request to get Contractualfunds : {}", id);
        Optional<Contractualfunds> contractualfunds = contractualfundsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(contractualfunds);
    }

    /**
     * {@code DELETE  /contractualfunds/:id} : delete the "id" contractualfunds.
     *
     * @param id the id of the contractualfunds to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContractualfunds(@PathVariable("id") String id) {
        log.debug("REST request to delete Contractualfunds : {}", id);
        contractualfundsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
