package com.cvicse.web.rest;

import com.cvicse.domain.AnnualSecurityPlan;
import com.cvicse.repository.AnnualSecurityPlanRepository;
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
 * REST controller for managing {@link com.cvicse.domain.AnnualSecurityPlan}.
 */
@RestController
@RequestMapping("/api/annual-security-plans")
@Transactional
public class AnnualSecurityPlanResource {

    private final Logger log = LoggerFactory.getLogger(AnnualSecurityPlanResource.class);

    private static final String ENTITY_NAME = "annualSecurityPlan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnnualSecurityPlanRepository annualSecurityPlanRepository;

    public AnnualSecurityPlanResource(AnnualSecurityPlanRepository annualSecurityPlanRepository) {
        this.annualSecurityPlanRepository = annualSecurityPlanRepository;
    }

    /**
     * {@code POST  /annual-security-plans} : Create a new annualSecurityPlan.
     *
     * @param annualSecurityPlan the annualSecurityPlan to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new annualSecurityPlan, or with status {@code 400 (Bad Request)} if the annualSecurityPlan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<AnnualSecurityPlan> createAnnualSecurityPlan(@RequestBody AnnualSecurityPlan annualSecurityPlan)
        throws URISyntaxException {
        log.debug("REST request to save AnnualSecurityPlan : {}", annualSecurityPlan);
        if (annualSecurityPlan.getId() != null) {
            throw new BadRequestAlertException("A new annualSecurityPlan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        annualSecurityPlan = annualSecurityPlanRepository.save(annualSecurityPlan);
        return ResponseEntity.created(new URI("/api/annual-security-plans/" + annualSecurityPlan.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, annualSecurityPlan.getId()))
            .body(annualSecurityPlan);
    }

    /**
     * {@code PUT  /annual-security-plans/:id} : Updates an existing annualSecurityPlan.
     *
     * @param id the id of the annualSecurityPlan to save.
     * @param annualSecurityPlan the annualSecurityPlan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated annualSecurityPlan,
     * or with status {@code 400 (Bad Request)} if the annualSecurityPlan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the annualSecurityPlan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AnnualSecurityPlan> updateAnnualSecurityPlan(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody AnnualSecurityPlan annualSecurityPlan
    ) throws URISyntaxException {
        log.debug("REST request to update AnnualSecurityPlan : {}, {}", id, annualSecurityPlan);
        if (annualSecurityPlan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, annualSecurityPlan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!annualSecurityPlanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        annualSecurityPlan = annualSecurityPlanRepository.save(annualSecurityPlan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, annualSecurityPlan.getId()))
            .body(annualSecurityPlan);
    }

    /**
     * {@code PATCH  /annual-security-plans/:id} : Partial updates given fields of an existing annualSecurityPlan, field will ignore if it is null
     *
     * @param id the id of the annualSecurityPlan to save.
     * @param annualSecurityPlan the annualSecurityPlan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated annualSecurityPlan,
     * or with status {@code 400 (Bad Request)} if the annualSecurityPlan is not valid,
     * or with status {@code 404 (Not Found)} if the annualSecurityPlan is not found,
     * or with status {@code 500 (Internal Server Error)} if the annualSecurityPlan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AnnualSecurityPlan> partialUpdateAnnualSecurityPlan(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody AnnualSecurityPlan annualSecurityPlan
    ) throws URISyntaxException {
        log.debug("REST request to partial update AnnualSecurityPlan partially : {}, {}", id, annualSecurityPlan);
        if (annualSecurityPlan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, annualSecurityPlan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!annualSecurityPlanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AnnualSecurityPlan> result = annualSecurityPlanRepository
            .findById(annualSecurityPlan.getId())
            .map(existingAnnualSecurityPlan -> {
                if (annualSecurityPlan.getSecurityplanname() != null) {
                    existingAnnualSecurityPlan.setSecurityplanname(annualSecurityPlan.getSecurityplanname());
                }
                if (annualSecurityPlan.getReleasetime() != null) {
                    existingAnnualSecurityPlan.setReleasetime(annualSecurityPlan.getReleasetime());
                }
                if (annualSecurityPlan.getCreatetime() != null) {
                    existingAnnualSecurityPlan.setCreatetime(annualSecurityPlan.getCreatetime());
                }
                if (annualSecurityPlan.getCreatorname() != null) {
                    existingAnnualSecurityPlan.setCreatorname(annualSecurityPlan.getCreatorname());
                }
                if (annualSecurityPlan.getAuditStatus() != null) {
                    existingAnnualSecurityPlan.setAuditStatus(annualSecurityPlan.getAuditStatus());
                }
                if (annualSecurityPlan.getVersion() != null) {
                    existingAnnualSecurityPlan.setVersion(annualSecurityPlan.getVersion());
                }

                return existingAnnualSecurityPlan;
            })
            .map(annualSecurityPlanRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, annualSecurityPlan.getId())
        );
    }

    /**
     * {@code GET  /annual-security-plans} : get all the annualSecurityPlans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of annualSecurityPlans in body.
     */
    @GetMapping("")
    public List<AnnualSecurityPlan> getAllAnnualSecurityPlans() {
        log.debug("REST request to get all AnnualSecurityPlans");
        return annualSecurityPlanRepository.findAll();
    }

    /**
     * {@code GET  /annual-security-plans/:id} : get the "id" annualSecurityPlan.
     *
     * @param id the id of the annualSecurityPlan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the annualSecurityPlan, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AnnualSecurityPlan> getAnnualSecurityPlan(@PathVariable("id") String id) {
        log.debug("REST request to get AnnualSecurityPlan : {}", id);
        Optional<AnnualSecurityPlan> annualSecurityPlan = annualSecurityPlanRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(annualSecurityPlan);
    }

    /**
     * {@code DELETE  /annual-security-plans/:id} : delete the "id" annualSecurityPlan.
     *
     * @param id the id of the annualSecurityPlan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnualSecurityPlan(@PathVariable("id") String id) {
        log.debug("REST request to delete AnnualSecurityPlan : {}", id);
        annualSecurityPlanRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
