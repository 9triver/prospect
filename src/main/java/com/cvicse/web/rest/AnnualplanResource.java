package com.cvicse.web.rest;

import com.cvicse.domain.Annualplan;
import com.cvicse.repository.AnnualplanRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Annualplan}.
 */
@RestController
@RequestMapping("/api/annualplans")
@Transactional
public class AnnualplanResource {

    private final Logger log = LoggerFactory.getLogger(AnnualplanResource.class);

    private static final String ENTITY_NAME = "annualplan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnnualplanRepository annualplanRepository;

    public AnnualplanResource(AnnualplanRepository annualplanRepository) {
        this.annualplanRepository = annualplanRepository;
    }

    /**
     * {@code POST  /annualplans} : Create a new annualplan.
     *
     * @param annualplan the annualplan to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new annualplan, or with status {@code 400 (Bad Request)} if the annualplan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Annualplan> createAnnualplan(@Valid @RequestBody Annualplan annualplan) throws URISyntaxException {
        log.debug("REST request to save Annualplan : {}", annualplan);
        if (annualplan.getId() != null) {
            throw new BadRequestAlertException("A new annualplan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        annualplan = annualplanRepository.save(annualplan);
        return ResponseEntity.created(new URI("/api/annualplans/" + annualplan.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, annualplan.getId().toString()))
            .body(annualplan);
    }

    /**
     * {@code PUT  /annualplans/:id} : Updates an existing annualplan.
     *
     * @param id the id of the annualplan to save.
     * @param annualplan the annualplan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated annualplan,
     * or with status {@code 400 (Bad Request)} if the annualplan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the annualplan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Annualplan> updateAnnualplan(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Annualplan annualplan
    ) throws URISyntaxException {
        log.debug("REST request to update Annualplan : {}, {}", id, annualplan);
        if (annualplan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, annualplan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!annualplanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        annualplan = annualplanRepository.save(annualplan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, annualplan.getId().toString()))
            .body(annualplan);
    }

    /**
     * {@code PATCH  /annualplans/:id} : Partial updates given fields of an existing annualplan, field will ignore if it is null
     *
     * @param id the id of the annualplan to save.
     * @param annualplan the annualplan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated annualplan,
     * or with status {@code 400 (Bad Request)} if the annualplan is not valid,
     * or with status {@code 404 (Not Found)} if the annualplan is not found,
     * or with status {@code 500 (Internal Server Error)} if the annualplan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Annualplan> partialUpdateAnnualplan(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Annualplan annualplan
    ) throws URISyntaxException {
        log.debug("REST request to partial update Annualplan partially : {}, {}", id, annualplan);
        if (annualplan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, annualplan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!annualplanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Annualplan> result = annualplanRepository
            .findById(annualplan.getId())
            .map(existingAnnualplan -> {
                if (annualplan.getAnnualplanid() != null) {
                    existingAnnualplan.setAnnualplanid(annualplan.getAnnualplanid());
                }
                if (annualplan.getAnnualplanname() != null) {
                    existingAnnualplan.setAnnualplanname(annualplan.getAnnualplanname());
                }
                if (annualplan.getYear() != null) {
                    existingAnnualplan.setYear(annualplan.getYear());
                }
                if (annualplan.getSecretlevel() != null) {
                    existingAnnualplan.setSecretlevel(annualplan.getSecretlevel());
                }
                if (annualplan.getCreatorname() != null) {
                    existingAnnualplan.setCreatorname(annualplan.getCreatorname());
                }
                if (annualplan.getStatus() != null) {
                    existingAnnualplan.setStatus(annualplan.getStatus());
                }
                if (annualplan.getAuditStatus() != null) {
                    existingAnnualplan.setAuditStatus(annualplan.getAuditStatus());
                }

                return existingAnnualplan;
            })
            .map(annualplanRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, annualplan.getId().toString())
        );
    }

    /**
     * {@code GET  /annualplans} : get all the annualplans.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of annualplans in body.
     */
    @GetMapping("")
    public List<Annualplan> getAllAnnualplans(@RequestParam(name = "filter", required = false) String filter) {
        if ("cycleplan-is-null".equals(filter)) {
            log.debug("REST request to get all Annualplans where cycleplan is null");
            return StreamSupport.stream(annualplanRepository.findAll().spliterator(), false)
                .filter(annualplan -> annualplan.getCycleplan() == null)
                .toList();
        }
        log.debug("REST request to get all Annualplans");
        return annualplanRepository.findAll();
    }

    /**
     * {@code GET  /annualplans/:id} : get the "id" annualplan.
     *
     * @param id the id of the annualplan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the annualplan, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Annualplan> getAnnualplan(@PathVariable("id") Long id) {
        log.debug("REST request to get Annualplan : {}", id);
        Optional<Annualplan> annualplan = annualplanRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(annualplan);
    }

    /**
     * {@code DELETE  /annualplans/:id} : delete the "id" annualplan.
     *
     * @param id the id of the annualplan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnualplan(@PathVariable("id") Long id) {
        log.debug("REST request to delete Annualplan : {}", id);
        annualplanRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
