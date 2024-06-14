package com.cvicse.web.rest;

import com.cvicse.domain.OutsourcingmPurchasePlan;
import com.cvicse.repository.OutsourcingmPurchasePlanRepository;
import com.cvicse.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.cvicse.domain.OutsourcingmPurchasePlan}.
 */
@RestController
@RequestMapping("/api/outsourcingm-purchase-plans")
@Transactional
public class OutsourcingmPurchasePlanResource {

    private final Logger log = LoggerFactory.getLogger(OutsourcingmPurchasePlanResource.class);

    private static final String ENTITY_NAME = "outsourcingmPurchasePlan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OutsourcingmPurchasePlanRepository outsourcingmPurchasePlanRepository;

    public OutsourcingmPurchasePlanResource(OutsourcingmPurchasePlanRepository outsourcingmPurchasePlanRepository) {
        this.outsourcingmPurchasePlanRepository = outsourcingmPurchasePlanRepository;
    }

    /**
     * {@code POST  /outsourcingm-purchase-plans} : Create a new outsourcingmPurchasePlan.
     *
     * @param outsourcingmPurchasePlan the outsourcingmPurchasePlan to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new outsourcingmPurchasePlan, or with status {@code 400 (Bad Request)} if the outsourcingmPurchasePlan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OutsourcingmPurchasePlan> createOutsourcingmPurchasePlan(
        @Valid @RequestBody OutsourcingmPurchasePlan outsourcingmPurchasePlan
    ) throws URISyntaxException {
        log.debug("REST request to save OutsourcingmPurchasePlan : {}", outsourcingmPurchasePlan);
        if (outsourcingmPurchasePlan.getId() != null) {
            throw new BadRequestAlertException("A new outsourcingmPurchasePlan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        outsourcingmPurchasePlan = outsourcingmPurchasePlanRepository.save(outsourcingmPurchasePlan);
        return ResponseEntity.created(new URI("/api/outsourcingm-purchase-plans/" + outsourcingmPurchasePlan.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, outsourcingmPurchasePlan.getId().toString()))
            .body(outsourcingmPurchasePlan);
    }

    /**
     * {@code PUT  /outsourcingm-purchase-plans/:id} : Updates an existing outsourcingmPurchasePlan.
     *
     * @param id the id of the outsourcingmPurchasePlan to save.
     * @param outsourcingmPurchasePlan the outsourcingmPurchasePlan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outsourcingmPurchasePlan,
     * or with status {@code 400 (Bad Request)} if the outsourcingmPurchasePlan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the outsourcingmPurchasePlan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OutsourcingmPurchasePlan> updateOutsourcingmPurchasePlan(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody OutsourcingmPurchasePlan outsourcingmPurchasePlan
    ) throws URISyntaxException {
        log.debug("REST request to update OutsourcingmPurchasePlan : {}, {}", id, outsourcingmPurchasePlan);
        if (outsourcingmPurchasePlan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outsourcingmPurchasePlan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!outsourcingmPurchasePlanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        outsourcingmPurchasePlan = outsourcingmPurchasePlanRepository.save(outsourcingmPurchasePlan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, outsourcingmPurchasePlan.getId().toString()))
            .body(outsourcingmPurchasePlan);
    }

    /**
     * {@code PATCH  /outsourcingm-purchase-plans/:id} : Partial updates given fields of an existing outsourcingmPurchasePlan, field will ignore if it is null
     *
     * @param id the id of the outsourcingmPurchasePlan to save.
     * @param outsourcingmPurchasePlan the outsourcingmPurchasePlan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outsourcingmPurchasePlan,
     * or with status {@code 400 (Bad Request)} if the outsourcingmPurchasePlan is not valid,
     * or with status {@code 404 (Not Found)} if the outsourcingmPurchasePlan is not found,
     * or with status {@code 500 (Internal Server Error)} if the outsourcingmPurchasePlan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OutsourcingmPurchasePlan> partialUpdateOutsourcingmPurchasePlan(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody OutsourcingmPurchasePlan outsourcingmPurchasePlan
    ) throws URISyntaxException {
        log.debug("REST request to partial update OutsourcingmPurchasePlan partially : {}, {}", id, outsourcingmPurchasePlan);
        if (outsourcingmPurchasePlan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outsourcingmPurchasePlan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!outsourcingmPurchasePlanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OutsourcingmPurchasePlan> result = outsourcingmPurchasePlanRepository
            .findById(outsourcingmPurchasePlan.getId())
            .map(existingOutsourcingmPurchasePlan -> {
                if (outsourcingmPurchasePlan.getOutsourcingplanid() != null) {
                    existingOutsourcingmPurchasePlan.setOutsourcingplanid(outsourcingmPurchasePlan.getOutsourcingplanid());
                }
                if (outsourcingmPurchasePlan.getMatarialname() != null) {
                    existingOutsourcingmPurchasePlan.setMatarialname(outsourcingmPurchasePlan.getMatarialname());
                }
                if (outsourcingmPurchasePlan.getPurchasingmethod() != null) {
                    existingOutsourcingmPurchasePlan.setPurchasingmethod(outsourcingmPurchasePlan.getPurchasingmethod());
                }
                if (outsourcingmPurchasePlan.getBudgit() != null) {
                    existingOutsourcingmPurchasePlan.setBudgit(outsourcingmPurchasePlan.getBudgit());
                }
                if (outsourcingmPurchasePlan.getNeedtime() != null) {
                    existingOutsourcingmPurchasePlan.setNeedtime(outsourcingmPurchasePlan.getNeedtime());
                }
                if (outsourcingmPurchasePlan.getPlanusetime() != null) {
                    existingOutsourcingmPurchasePlan.setPlanusetime(outsourcingmPurchasePlan.getPlanusetime());
                }
                if (outsourcingmPurchasePlan.getSupplierid() != null) {
                    existingOutsourcingmPurchasePlan.setSupplierid(outsourcingmPurchasePlan.getSupplierid());
                }
                if (outsourcingmPurchasePlan.getPrice() != null) {
                    existingOutsourcingmPurchasePlan.setPrice(outsourcingmPurchasePlan.getPrice());
                }
                if (outsourcingmPurchasePlan.getSecretlevel() != null) {
                    existingOutsourcingmPurchasePlan.setSecretlevel(outsourcingmPurchasePlan.getSecretlevel());
                }
                if (outsourcingmPurchasePlan.getAuditStatus() != null) {
                    existingOutsourcingmPurchasePlan.setAuditStatus(outsourcingmPurchasePlan.getAuditStatus());
                }

                return existingOutsourcingmPurchasePlan;
            })
            .map(outsourcingmPurchasePlanRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, outsourcingmPurchasePlan.getId().toString())
        );
    }

    /**
     * {@code GET  /outsourcingm-purchase-plans} : get all the outsourcingmPurchasePlans.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of outsourcingmPurchasePlans in body.
     */
    @GetMapping("")
    public List<OutsourcingmPurchasePlan> getAllOutsourcingmPurchasePlans(@RequestParam(name = "filter", required = false) String filter) {
        if ("outsourcingmpurchaseexecute-is-null".equals(filter)) {
            log.debug("REST request to get all OutsourcingmPurchasePlans where outsourcingmPurchaseExecute is null");
            return StreamSupport.stream(outsourcingmPurchasePlanRepository.findAll().spliterator(), false)
                .filter(outsourcingmPurchasePlan -> outsourcingmPurchasePlan.getOutsourcingmPurchaseExecute() == null)
                .toList();
        }
        log.debug("REST request to get all OutsourcingmPurchasePlans");
        return outsourcingmPurchasePlanRepository.findAll();
    }

    /**
     * {@code GET  /outsourcingm-purchase-plans/:id} : get the "id" outsourcingmPurchasePlan.
     *
     * @param id the id of the outsourcingmPurchasePlan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the outsourcingmPurchasePlan, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OutsourcingmPurchasePlan> getOutsourcingmPurchasePlan(@PathVariable("id") Long id) {
        log.debug("REST request to get OutsourcingmPurchasePlan : {}", id);
        Optional<OutsourcingmPurchasePlan> outsourcingmPurchasePlan = outsourcingmPurchasePlanRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(outsourcingmPurchasePlan);
    }

    /**
     * {@code DELETE  /outsourcingm-purchase-plans/:id} : delete the "id" outsourcingmPurchasePlan.
     *
     * @param id the id of the outsourcingmPurchasePlan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOutsourcingmPurchasePlan(@PathVariable("id") Long id) {
        log.debug("REST request to delete OutsourcingmPurchasePlan : {}", id);
        outsourcingmPurchasePlanRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
