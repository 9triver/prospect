package com.cvicse.web.rest;

import com.cvicse.domain.OutsourcingPurchaseExecute;
import com.cvicse.repository.OutsourcingPurchaseExecuteRepository;
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
 * REST controller for managing {@link com.cvicse.domain.OutsourcingPurchaseExecute}.
 */
@RestController
@RequestMapping("/api/outsourcing-purchase-executes")
@Transactional
public class OutsourcingPurchaseExecuteResource {

    private final Logger log = LoggerFactory.getLogger(OutsourcingPurchaseExecuteResource.class);

    private static final String ENTITY_NAME = "outsourcingPurchaseExecute";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OutsourcingPurchaseExecuteRepository outsourcingPurchaseExecuteRepository;

    public OutsourcingPurchaseExecuteResource(OutsourcingPurchaseExecuteRepository outsourcingPurchaseExecuteRepository) {
        this.outsourcingPurchaseExecuteRepository = outsourcingPurchaseExecuteRepository;
    }

    /**
     * {@code POST  /outsourcing-purchase-executes} : Create a new outsourcingPurchaseExecute.
     *
     * @param outsourcingPurchaseExecute the outsourcingPurchaseExecute to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new outsourcingPurchaseExecute, or with status {@code 400 (Bad Request)} if the outsourcingPurchaseExecute has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OutsourcingPurchaseExecute> createOutsourcingPurchaseExecute(
        @RequestBody OutsourcingPurchaseExecute outsourcingPurchaseExecute
    ) throws URISyntaxException {
        log.debug("REST request to save OutsourcingPurchaseExecute : {}", outsourcingPurchaseExecute);
        if (outsourcingPurchaseExecute.getId() != null) {
            throw new BadRequestAlertException("A new outsourcingPurchaseExecute cannot already have an ID", ENTITY_NAME, "idexists");
        }
        outsourcingPurchaseExecute = outsourcingPurchaseExecuteRepository.save(outsourcingPurchaseExecute);
        return ResponseEntity.created(new URI("/api/outsourcing-purchase-executes/" + outsourcingPurchaseExecute.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, outsourcingPurchaseExecute.getId()))
            .body(outsourcingPurchaseExecute);
    }

    /**
     * {@code PUT  /outsourcing-purchase-executes/:id} : Updates an existing outsourcingPurchaseExecute.
     *
     * @param id the id of the outsourcingPurchaseExecute to save.
     * @param outsourcingPurchaseExecute the outsourcingPurchaseExecute to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outsourcingPurchaseExecute,
     * or with status {@code 400 (Bad Request)} if the outsourcingPurchaseExecute is not valid,
     * or with status {@code 500 (Internal Server Error)} if the outsourcingPurchaseExecute couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OutsourcingPurchaseExecute> updateOutsourcingPurchaseExecute(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody OutsourcingPurchaseExecute outsourcingPurchaseExecute
    ) throws URISyntaxException {
        log.debug("REST request to update OutsourcingPurchaseExecute : {}, {}", id, outsourcingPurchaseExecute);
        if (outsourcingPurchaseExecute.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outsourcingPurchaseExecute.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!outsourcingPurchaseExecuteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        outsourcingPurchaseExecute = outsourcingPurchaseExecuteRepository.save(outsourcingPurchaseExecute);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, outsourcingPurchaseExecute.getId()))
            .body(outsourcingPurchaseExecute);
    }

    /**
     * {@code PATCH  /outsourcing-purchase-executes/:id} : Partial updates given fields of an existing outsourcingPurchaseExecute, field will ignore if it is null
     *
     * @param id the id of the outsourcingPurchaseExecute to save.
     * @param outsourcingPurchaseExecute the outsourcingPurchaseExecute to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outsourcingPurchaseExecute,
     * or with status {@code 400 (Bad Request)} if the outsourcingPurchaseExecute is not valid,
     * or with status {@code 404 (Not Found)} if the outsourcingPurchaseExecute is not found,
     * or with status {@code 500 (Internal Server Error)} if the outsourcingPurchaseExecute couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OutsourcingPurchaseExecute> partialUpdateOutsourcingPurchaseExecute(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody OutsourcingPurchaseExecute outsourcingPurchaseExecute
    ) throws URISyntaxException {
        log.debug("REST request to partial update OutsourcingPurchaseExecute partially : {}, {}", id, outsourcingPurchaseExecute);
        if (outsourcingPurchaseExecute.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outsourcingPurchaseExecute.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!outsourcingPurchaseExecuteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OutsourcingPurchaseExecute> result = outsourcingPurchaseExecuteRepository
            .findById(outsourcingPurchaseExecute.getId())
            .map(existingOutsourcingPurchaseExecute -> {
                if (outsourcingPurchaseExecute.getMatarialname() != null) {
                    existingOutsourcingPurchaseExecute.setMatarialname(outsourcingPurchaseExecute.getMatarialname());
                }
                if (outsourcingPurchaseExecute.getPurchasingmethod() != null) {
                    existingOutsourcingPurchaseExecute.setPurchasingmethod(outsourcingPurchaseExecute.getPurchasingmethod());
                }
                if (outsourcingPurchaseExecute.getBudgit() != null) {
                    existingOutsourcingPurchaseExecute.setBudgit(outsourcingPurchaseExecute.getBudgit());
                }
                if (outsourcingPurchaseExecute.getNeedtime() != null) {
                    existingOutsourcingPurchaseExecute.setNeedtime(outsourcingPurchaseExecute.getNeedtime());
                }
                if (outsourcingPurchaseExecute.getPlanusetime() != null) {
                    existingOutsourcingPurchaseExecute.setPlanusetime(outsourcingPurchaseExecute.getPlanusetime());
                }
                if (outsourcingPurchaseExecute.getSupplierid() != null) {
                    existingOutsourcingPurchaseExecute.setSupplierid(outsourcingPurchaseExecute.getSupplierid());
                }
                if (outsourcingPurchaseExecute.getPrice() != null) {
                    existingOutsourcingPurchaseExecute.setPrice(outsourcingPurchaseExecute.getPrice());
                }
                if (outsourcingPurchaseExecute.getSecretlevel() != null) {
                    existingOutsourcingPurchaseExecute.setSecretlevel(outsourcingPurchaseExecute.getSecretlevel());
                }

                return existingOutsourcingPurchaseExecute;
            })
            .map(outsourcingPurchaseExecuteRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, outsourcingPurchaseExecute.getId())
        );
    }

    /**
     * {@code GET  /outsourcing-purchase-executes} : get all the outsourcingPurchaseExecutes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of outsourcingPurchaseExecutes in body.
     */
    @GetMapping("")
    public List<OutsourcingPurchaseExecute> getAllOutsourcingPurchaseExecutes() {
        log.debug("REST request to get all OutsourcingPurchaseExecutes");
        return outsourcingPurchaseExecuteRepository.findAll();
    }

    /**
     * {@code GET  /outsourcing-purchase-executes/:id} : get the "id" outsourcingPurchaseExecute.
     *
     * @param id the id of the outsourcingPurchaseExecute to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the outsourcingPurchaseExecute, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OutsourcingPurchaseExecute> getOutsourcingPurchaseExecute(@PathVariable("id") String id) {
        log.debug("REST request to get OutsourcingPurchaseExecute : {}", id);
        Optional<OutsourcingPurchaseExecute> outsourcingPurchaseExecute = outsourcingPurchaseExecuteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(outsourcingPurchaseExecute);
    }

    /**
     * {@code DELETE  /outsourcing-purchase-executes/:id} : delete the "id" outsourcingPurchaseExecute.
     *
     * @param id the id of the outsourcingPurchaseExecute to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOutsourcingPurchaseExecute(@PathVariable("id") String id) {
        log.debug("REST request to delete OutsourcingPurchaseExecute : {}", id);
        outsourcingPurchaseExecuteRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
