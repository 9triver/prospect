package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.DeviationPermitApplication;
import com.cvicse.jy1.repository.DeviationPermitApplicationRepository;
import com.cvicse.jy1.service.DeviationPermitApplicationService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.DeviationPermitApplication}.
 */
@RestController
@RequestMapping("/api/deviation-permit-applications")
public class DeviationPermitApplicationResource {

    private static final Logger log = LoggerFactory.getLogger(DeviationPermitApplicationResource.class);

    private static final String ENTITY_NAME = "deviationPermitApplication";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeviationPermitApplicationService deviationPermitApplicationService;

    private final DeviationPermitApplicationRepository deviationPermitApplicationRepository;

    public DeviationPermitApplicationResource(
        DeviationPermitApplicationService deviationPermitApplicationService,
        DeviationPermitApplicationRepository deviationPermitApplicationRepository
    ) {
        this.deviationPermitApplicationService = deviationPermitApplicationService;
        this.deviationPermitApplicationRepository = deviationPermitApplicationRepository;
    }

    /**
     * {@code POST  /deviation-permit-applications} : Create a new deviationPermitApplication.
     *
     * @param deviationPermitApplication the deviationPermitApplication to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deviationPermitApplication, or with status {@code 400 (Bad Request)} if the deviationPermitApplication has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DeviationPermitApplication> createDeviationPermitApplication(
        @RequestBody DeviationPermitApplication deviationPermitApplication
    ) throws URISyntaxException {
        log.debug("REST request to save DeviationPermitApplication : {}", deviationPermitApplication);
        if (deviationPermitApplication.getId() != null) {
            throw new BadRequestAlertException("A new deviationPermitApplication cannot already have an ID", ENTITY_NAME, "idexists");
        }
        deviationPermitApplication = deviationPermitApplicationService.save(deviationPermitApplication);
        return ResponseEntity.created(new URI("/api/deviation-permit-applications/" + deviationPermitApplication.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, deviationPermitApplication.getId().toString())
            )
            .body(deviationPermitApplication);
    }

    /**
     * {@code PUT  /deviation-permit-applications/:id} : Updates an existing deviationPermitApplication.
     *
     * @param id the id of the deviationPermitApplication to save.
     * @param deviationPermitApplication the deviationPermitApplication to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deviationPermitApplication,
     * or with status {@code 400 (Bad Request)} if the deviationPermitApplication is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deviationPermitApplication couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DeviationPermitApplication> updateDeviationPermitApplication(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody DeviationPermitApplication deviationPermitApplication
    ) throws URISyntaxException {
        log.debug("REST request to update DeviationPermitApplication : {}, {}", id, deviationPermitApplication);
        if (deviationPermitApplication.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, deviationPermitApplication.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deviationPermitApplicationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        deviationPermitApplication = deviationPermitApplicationService.update(deviationPermitApplication);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deviationPermitApplication.getId().toString()))
            .body(deviationPermitApplication);
    }

    /**
     * {@code PATCH  /deviation-permit-applications/:id} : Partial updates given fields of an existing deviationPermitApplication, field will ignore if it is null
     *
     * @param id the id of the deviationPermitApplication to save.
     * @param deviationPermitApplication the deviationPermitApplication to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deviationPermitApplication,
     * or with status {@code 400 (Bad Request)} if the deviationPermitApplication is not valid,
     * or with status {@code 404 (Not Found)} if the deviationPermitApplication is not found,
     * or with status {@code 500 (Internal Server Error)} if the deviationPermitApplication couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DeviationPermitApplication> partialUpdateDeviationPermitApplication(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody DeviationPermitApplication deviationPermitApplication
    ) throws URISyntaxException {
        log.debug("REST request to partial update DeviationPermitApplication partially : {}, {}", id, deviationPermitApplication);
        if (deviationPermitApplication.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, deviationPermitApplication.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deviationPermitApplicationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DeviationPermitApplication> result = deviationPermitApplicationService.partialUpdate(deviationPermitApplication);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deviationPermitApplication.getId().toString())
        );
    }

    /**
     * {@code GET  /deviation-permit-applications} : get all the deviationPermitApplications.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deviationPermitApplications in body.
     */
    @GetMapping("")
    public List<DeviationPermitApplication> getAllDeviationPermitApplications() {
        log.debug("REST request to get all DeviationPermitApplications");
        return deviationPermitApplicationService.findAll();
    }

    /**
     * {@code GET  /deviation-permit-applications/:id} : get the "id" deviationPermitApplication.
     *
     * @param id the id of the deviationPermitApplication to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deviationPermitApplication, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DeviationPermitApplication> getDeviationPermitApplication(@PathVariable("id") Integer id) {
        log.debug("REST request to get DeviationPermitApplication : {}", id);
        Optional<DeviationPermitApplication> deviationPermitApplication = deviationPermitApplicationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deviationPermitApplication);
    }

    /**
     * {@code DELETE  /deviation-permit-applications/:id} : delete the "id" deviationPermitApplication.
     *
     * @param id the id of the deviationPermitApplication to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeviationPermitApplication(@PathVariable("id") Integer id) {
        log.debug("REST request to delete DeviationPermitApplication : {}", id);
        deviationPermitApplicationService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
