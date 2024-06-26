package com.cvicse.web.rest;

import com.cvicse.domain.OutsourcingPurchasePlan;
import com.cvicse.repository.OutsourcingPurchasePlanRepository;
import com.cvicse.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.cvicse.domain.OutsourcingPurchasePlan}.
 */
@RestController
@RequestMapping("/api/outsourcing-purchase-plans")
@Transactional
public class OutsourcingPurchasePlanResource {

    private final Logger log = LoggerFactory.getLogger(OutsourcingPurchasePlanResource.class);

    private static final String ENTITY_NAME = "outsourcingPurchasePlan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OutsourcingPurchasePlanRepository outsourcingPurchasePlanRepository;

    public OutsourcingPurchasePlanResource(OutsourcingPurchasePlanRepository outsourcingPurchasePlanRepository) {
        this.outsourcingPurchasePlanRepository = outsourcingPurchasePlanRepository;
    }

    /**
     * {@code POST  /outsourcing-purchase-plans} : Create a new outsourcingPurchasePlan.
     *
     * @param outsourcingPurchasePlan the outsourcingPurchasePlan to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new outsourcingPurchasePlan, or with status {@code 400 (Bad Request)} if the outsourcingPurchasePlan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OutsourcingPurchasePlan> createOutsourcingPurchasePlan(
        @RequestBody OutsourcingPurchasePlan outsourcingPurchasePlan
    ) throws URISyntaxException {
        log.debug("REST request to save OutsourcingPurchasePlan : {}", outsourcingPurchasePlan);
        if (outsourcingPurchasePlan.getId() != null) {
            throw new BadRequestAlertException("A new outsourcingPurchasePlan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        outsourcingPurchasePlan = outsourcingPurchasePlanRepository.save(outsourcingPurchasePlan);
        return ResponseEntity.created(new URI("/api/outsourcing-purchase-plans/" + outsourcingPurchasePlan.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, outsourcingPurchasePlan.getId()))
            .body(outsourcingPurchasePlan);
    }

    /**
     * {@code PUT  /outsourcing-purchase-plans/:id} : Updates an existing outsourcingPurchasePlan.
     *
     * @param id the id of the outsourcingPurchasePlan to save.
     * @param outsourcingPurchasePlan the outsourcingPurchasePlan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outsourcingPurchasePlan,
     * or with status {@code 400 (Bad Request)} if the outsourcingPurchasePlan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the outsourcingPurchasePlan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OutsourcingPurchasePlan> updateOutsourcingPurchasePlan(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody OutsourcingPurchasePlan outsourcingPurchasePlan
    ) throws URISyntaxException {
        log.debug("REST request to update OutsourcingPurchasePlan : {}, {}", id, outsourcingPurchasePlan);
        if (outsourcingPurchasePlan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outsourcingPurchasePlan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!outsourcingPurchasePlanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        outsourcingPurchasePlan = outsourcingPurchasePlanRepository.save(outsourcingPurchasePlan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, outsourcingPurchasePlan.getId()))
            .body(outsourcingPurchasePlan);
    }

    /**
     * {@code PATCH  /outsourcing-purchase-plans/:id} : Partial updates given fields of an existing outsourcingPurchasePlan, field will ignore if it is null
     *
     * @param id the id of the outsourcingPurchasePlan to save.
     * @param outsourcingPurchasePlan the outsourcingPurchasePlan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outsourcingPurchasePlan,
     * or with status {@code 400 (Bad Request)} if the outsourcingPurchasePlan is not valid,
     * or with status {@code 404 (Not Found)} if the outsourcingPurchasePlan is not found,
     * or with status {@code 500 (Internal Server Error)} if the outsourcingPurchasePlan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OutsourcingPurchasePlan> partialUpdateOutsourcingPurchasePlan(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody OutsourcingPurchasePlan outsourcingPurchasePlan
    ) throws URISyntaxException {
        log.debug("REST request to partial update OutsourcingPurchasePlan partially : {}, {}", id, outsourcingPurchasePlan);
        if (outsourcingPurchasePlan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outsourcingPurchasePlan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!outsourcingPurchasePlanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OutsourcingPurchasePlan> result = outsourcingPurchasePlanRepository
            .findById(outsourcingPurchasePlan.getId())
            .map(existingOutsourcingPurchasePlan -> {
                if (outsourcingPurchasePlan.getMatarialname() != null) {
                    existingOutsourcingPurchasePlan.setMatarialname(outsourcingPurchasePlan.getMatarialname());
                }
                if (outsourcingPurchasePlan.getPurchasingmethod() != null) {
                    existingOutsourcingPurchasePlan.setPurchasingmethod(outsourcingPurchasePlan.getPurchasingmethod());
                }
                if (outsourcingPurchasePlan.getBudgit() != null) {
                    existingOutsourcingPurchasePlan.setBudgit(outsourcingPurchasePlan.getBudgit());
                }
                if (outsourcingPurchasePlan.getNeedtime() != null) {
                    existingOutsourcingPurchasePlan.setNeedtime(outsourcingPurchasePlan.getNeedtime());
                }
                if (outsourcingPurchasePlan.getPlanusetime() != null) {
                    existingOutsourcingPurchasePlan.setPlanusetime(outsourcingPurchasePlan.getPlanusetime());
                }
                if (outsourcingPurchasePlan.getSupplierid() != null) {
                    existingOutsourcingPurchasePlan.setSupplierid(outsourcingPurchasePlan.getSupplierid());
                }
                if (outsourcingPurchasePlan.getPrice() != null) {
                    existingOutsourcingPurchasePlan.setPrice(outsourcingPurchasePlan.getPrice());
                }
                if (outsourcingPurchasePlan.getSecretlevel() != null) {
                    existingOutsourcingPurchasePlan.setSecretlevel(outsourcingPurchasePlan.getSecretlevel());
                }
                if (outsourcingPurchasePlan.getAuditStatus() != null) {
                    existingOutsourcingPurchasePlan.setAuditStatus(outsourcingPurchasePlan.getAuditStatus());
                }

                return existingOutsourcingPurchasePlan;
            })
            .map(outsourcingPurchasePlanRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, outsourcingPurchasePlan.getId())
        );
    }

    /**
     * {@code GET  /outsourcing-purchase-plans} : get all the outsourcingPurchasePlans.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of outsourcingPurchasePlans in body.
     */
    @GetMapping("")
    public List<OutsourcingPurchasePlan> getAllOutsourcingPurchasePlans(@RequestParam(name = "filter", required = false) String filter) {
        if ("outsourcingpurchaseexecute-is-null".equals(filter)) {
            log.debug("REST request to get all OutsourcingPurchasePlans where outsourcingPurchaseExecute is null");
            return StreamSupport.stream(outsourcingPurchasePlanRepository.findAll().spliterator(), false)
                .filter(outsourcingPurchasePlan -> outsourcingPurchasePlan.getOutsourcingPurchaseExecute() == null)
                .toList();
        }
        log.debug("REST request to get all OutsourcingPurchasePlans");
        return outsourcingPurchasePlanRepository.findAll();
    }

    /**
     * {@code GET  /outsourcing-purchase-plans/:id} : get the "id" outsourcingPurchasePlan.
     *
     * @param id the id of the outsourcingPurchasePlan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the outsourcingPurchasePlan, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OutsourcingPurchasePlan> getOutsourcingPurchasePlan(@PathVariable("id") String id) {
        log.debug("REST request to get OutsourcingPurchasePlan : {}", id);
        Optional<OutsourcingPurchasePlan> outsourcingPurchasePlan = outsourcingPurchasePlanRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(outsourcingPurchasePlan);
    }

    /**
     * {@code DELETE  /outsourcing-purchase-plans/:id} : delete the "id" outsourcingPurchasePlan.
     *
     * @param id the id of the outsourcingPurchasePlan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOutsourcingPurchasePlan(@PathVariable("id") String id) {
        log.debug("REST request to delete OutsourcingPurchasePlan : {}", id);
        outsourcingPurchasePlanRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
