package com.cvicse.web.rest;

import com.cvicse.domain.OutsourcingmPurchaseExecute;
import com.cvicse.repository.OutsourcingmPurchaseExecuteRepository;
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
 * REST controller for managing {@link com.cvicse.domain.OutsourcingmPurchaseExecute}.
 */
@RestController
@RequestMapping("/api/outsourcingm-purchase-executes")
@Transactional
public class OutsourcingmPurchaseExecuteResource {

    private final Logger log = LoggerFactory.getLogger(OutsourcingmPurchaseExecuteResource.class);

    private static final String ENTITY_NAME = "outsourcingmPurchaseExecute";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OutsourcingmPurchaseExecuteRepository outsourcingmPurchaseExecuteRepository;

    public OutsourcingmPurchaseExecuteResource(OutsourcingmPurchaseExecuteRepository outsourcingmPurchaseExecuteRepository) {
        this.outsourcingmPurchaseExecuteRepository = outsourcingmPurchaseExecuteRepository;
    }

    /**
     * {@code POST  /outsourcingm-purchase-executes} : Create a new outsourcingmPurchaseExecute.
     *
     * @param outsourcingmPurchaseExecute the outsourcingmPurchaseExecute to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new outsourcingmPurchaseExecute, or with status {@code 400 (Bad Request)} if the outsourcingmPurchaseExecute has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OutsourcingmPurchaseExecute> createOutsourcingmPurchaseExecute(
        @Valid @RequestBody OutsourcingmPurchaseExecute outsourcingmPurchaseExecute
    ) throws URISyntaxException {
        log.debug("REST request to save OutsourcingmPurchaseExecute : {}", outsourcingmPurchaseExecute);
        if (outsourcingmPurchaseExecute.getId() != null) {
            throw new BadRequestAlertException("A new outsourcingmPurchaseExecute cannot already have an ID", ENTITY_NAME, "idexists");
        }
        outsourcingmPurchaseExecute = outsourcingmPurchaseExecuteRepository.save(outsourcingmPurchaseExecute);
        return ResponseEntity.created(new URI("/api/outsourcingm-purchase-executes/" + outsourcingmPurchaseExecute.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, outsourcingmPurchaseExecute.getId().toString())
            )
            .body(outsourcingmPurchaseExecute);
    }

    /**
     * {@code PUT  /outsourcingm-purchase-executes/:id} : Updates an existing outsourcingmPurchaseExecute.
     *
     * @param id the id of the outsourcingmPurchaseExecute to save.
     * @param outsourcingmPurchaseExecute the outsourcingmPurchaseExecute to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outsourcingmPurchaseExecute,
     * or with status {@code 400 (Bad Request)} if the outsourcingmPurchaseExecute is not valid,
     * or with status {@code 500 (Internal Server Error)} if the outsourcingmPurchaseExecute couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OutsourcingmPurchaseExecute> updateOutsourcingmPurchaseExecute(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody OutsourcingmPurchaseExecute outsourcingmPurchaseExecute
    ) throws URISyntaxException {
        log.debug("REST request to update OutsourcingmPurchaseExecute : {}, {}", id, outsourcingmPurchaseExecute);
        if (outsourcingmPurchaseExecute.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outsourcingmPurchaseExecute.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!outsourcingmPurchaseExecuteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        outsourcingmPurchaseExecute = outsourcingmPurchaseExecuteRepository.save(outsourcingmPurchaseExecute);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, outsourcingmPurchaseExecute.getId().toString()))
            .body(outsourcingmPurchaseExecute);
    }

    /**
     * {@code PATCH  /outsourcingm-purchase-executes/:id} : Partial updates given fields of an existing outsourcingmPurchaseExecute, field will ignore if it is null
     *
     * @param id the id of the outsourcingmPurchaseExecute to save.
     * @param outsourcingmPurchaseExecute the outsourcingmPurchaseExecute to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outsourcingmPurchaseExecute,
     * or with status {@code 400 (Bad Request)} if the outsourcingmPurchaseExecute is not valid,
     * or with status {@code 404 (Not Found)} if the outsourcingmPurchaseExecute is not found,
     * or with status {@code 500 (Internal Server Error)} if the outsourcingmPurchaseExecute couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OutsourcingmPurchaseExecute> partialUpdateOutsourcingmPurchaseExecute(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody OutsourcingmPurchaseExecute outsourcingmPurchaseExecute
    ) throws URISyntaxException {
        log.debug("REST request to partial update OutsourcingmPurchaseExecute partially : {}, {}", id, outsourcingmPurchaseExecute);
        if (outsourcingmPurchaseExecute.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outsourcingmPurchaseExecute.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!outsourcingmPurchaseExecuteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OutsourcingmPurchaseExecute> result = outsourcingmPurchaseExecuteRepository
            .findById(outsourcingmPurchaseExecute.getId())
            .map(existingOutsourcingmPurchaseExecute -> {
                if (outsourcingmPurchaseExecute.getOutsourcingexecuteid() != null) {
                    existingOutsourcingmPurchaseExecute.setOutsourcingexecuteid(outsourcingmPurchaseExecute.getOutsourcingexecuteid());
                }
                if (outsourcingmPurchaseExecute.getMatarialname() != null) {
                    existingOutsourcingmPurchaseExecute.setMatarialname(outsourcingmPurchaseExecute.getMatarialname());
                }
                if (outsourcingmPurchaseExecute.getPurchasingmethod() != null) {
                    existingOutsourcingmPurchaseExecute.setPurchasingmethod(outsourcingmPurchaseExecute.getPurchasingmethod());
                }
                if (outsourcingmPurchaseExecute.getBudgit() != null) {
                    existingOutsourcingmPurchaseExecute.setBudgit(outsourcingmPurchaseExecute.getBudgit());
                }
                if (outsourcingmPurchaseExecute.getNeedtime() != null) {
                    existingOutsourcingmPurchaseExecute.setNeedtime(outsourcingmPurchaseExecute.getNeedtime());
                }
                if (outsourcingmPurchaseExecute.getPlanusetime() != null) {
                    existingOutsourcingmPurchaseExecute.setPlanusetime(outsourcingmPurchaseExecute.getPlanusetime());
                }
                if (outsourcingmPurchaseExecute.getSupplierid() != null) {
                    existingOutsourcingmPurchaseExecute.setSupplierid(outsourcingmPurchaseExecute.getSupplierid());
                }
                if (outsourcingmPurchaseExecute.getPrice() != null) {
                    existingOutsourcingmPurchaseExecute.setPrice(outsourcingmPurchaseExecute.getPrice());
                }
                if (outsourcingmPurchaseExecute.getSecretlevel() != null) {
                    existingOutsourcingmPurchaseExecute.setSecretlevel(outsourcingmPurchaseExecute.getSecretlevel());
                }

                return existingOutsourcingmPurchaseExecute;
            })
            .map(outsourcingmPurchaseExecuteRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, outsourcingmPurchaseExecute.getId().toString())
        );
    }

    /**
     * {@code GET  /outsourcingm-purchase-executes} : get all the outsourcingmPurchaseExecutes.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of outsourcingmPurchaseExecutes in body.
     */
    @GetMapping("")
    public List<OutsourcingmPurchaseExecute> getAllOutsourcingmPurchaseExecutes(
        @RequestParam(name = "filter", required = false) String filter
    ) {
        if ("project-is-null".equals(filter)) {
            log.debug("REST request to get all OutsourcingmPurchaseExecutes where project is null");
            return StreamSupport.stream(outsourcingmPurchaseExecuteRepository.findAll().spliterator(), false)
                .filter(outsourcingmPurchaseExecute -> outsourcingmPurchaseExecute.getProject() == null)
                .toList();
        }
        log.debug("REST request to get all OutsourcingmPurchaseExecutes");
        return outsourcingmPurchaseExecuteRepository.findAll();
    }

    /**
     * {@code GET  /outsourcingm-purchase-executes/:id} : get the "id" outsourcingmPurchaseExecute.
     *
     * @param id the id of the outsourcingmPurchaseExecute to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the outsourcingmPurchaseExecute, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OutsourcingmPurchaseExecute> getOutsourcingmPurchaseExecute(@PathVariable("id") Long id) {
        log.debug("REST request to get OutsourcingmPurchaseExecute : {}", id);
        Optional<OutsourcingmPurchaseExecute> outsourcingmPurchaseExecute = outsourcingmPurchaseExecuteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(outsourcingmPurchaseExecute);
    }

    /**
     * {@code DELETE  /outsourcingm-purchase-executes/:id} : delete the "id" outsourcingmPurchaseExecute.
     *
     * @param id the id of the outsourcingmPurchaseExecute to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOutsourcingmPurchaseExecute(@PathVariable("id") Long id) {
        log.debug("REST request to delete OutsourcingmPurchaseExecute : {}", id);
        outsourcingmPurchaseExecuteRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
