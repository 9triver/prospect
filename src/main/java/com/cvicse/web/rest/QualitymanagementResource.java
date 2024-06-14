package com.cvicse.web.rest;

import com.cvicse.domain.Qualitymanagement;
import com.cvicse.repository.QualitymanagementRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Qualitymanagement}.
 */
@RestController
@RequestMapping("/api/qualitymanagements")
@Transactional
public class QualitymanagementResource {

    private final Logger log = LoggerFactory.getLogger(QualitymanagementResource.class);

    private static final String ENTITY_NAME = "qualitymanagement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QualitymanagementRepository qualitymanagementRepository;

    public QualitymanagementResource(QualitymanagementRepository qualitymanagementRepository) {
        this.qualitymanagementRepository = qualitymanagementRepository;
    }

    /**
     * {@code POST  /qualitymanagements} : Create a new qualitymanagement.
     *
     * @param qualitymanagement the qualitymanagement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qualitymanagement, or with status {@code 400 (Bad Request)} if the qualitymanagement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Qualitymanagement> createQualitymanagement(@Valid @RequestBody Qualitymanagement qualitymanagement)
        throws URISyntaxException {
        log.debug("REST request to save Qualitymanagement : {}", qualitymanagement);
        if (qualitymanagement.getId() != null) {
            throw new BadRequestAlertException("A new qualitymanagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        qualitymanagement = qualitymanagementRepository.save(qualitymanagement);
        return ResponseEntity.created(new URI("/api/qualitymanagements/" + qualitymanagement.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, qualitymanagement.getId().toString()))
            .body(qualitymanagement);
    }

    /**
     * {@code PUT  /qualitymanagements/:id} : Updates an existing qualitymanagement.
     *
     * @param id the id of the qualitymanagement to save.
     * @param qualitymanagement the qualitymanagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualitymanagement,
     * or with status {@code 400 (Bad Request)} if the qualitymanagement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qualitymanagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Qualitymanagement> updateQualitymanagement(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Qualitymanagement qualitymanagement
    ) throws URISyntaxException {
        log.debug("REST request to update Qualitymanagement : {}, {}", id, qualitymanagement);
        if (qualitymanagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualitymanagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualitymanagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        qualitymanagement = qualitymanagementRepository.save(qualitymanagement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualitymanagement.getId().toString()))
            .body(qualitymanagement);
    }

    /**
     * {@code PATCH  /qualitymanagements/:id} : Partial updates given fields of an existing qualitymanagement, field will ignore if it is null
     *
     * @param id the id of the qualitymanagement to save.
     * @param qualitymanagement the qualitymanagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualitymanagement,
     * or with status {@code 400 (Bad Request)} if the qualitymanagement is not valid,
     * or with status {@code 404 (Not Found)} if the qualitymanagement is not found,
     * or with status {@code 500 (Internal Server Error)} if the qualitymanagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Qualitymanagement> partialUpdateQualitymanagement(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Qualitymanagement qualitymanagement
    ) throws URISyntaxException {
        log.debug("REST request to partial update Qualitymanagement partially : {}, {}", id, qualitymanagement);
        if (qualitymanagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualitymanagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualitymanagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Qualitymanagement> result = qualitymanagementRepository
            .findById(qualitymanagement.getId())
            .map(existingQualitymanagement -> {
                if (qualitymanagement.getQualityid() != null) {
                    existingQualitymanagement.setQualityid(qualitymanagement.getQualityid());
                }
                if (qualitymanagement.getCreatetime() != null) {
                    existingQualitymanagement.setCreatetime(qualitymanagement.getCreatetime());
                }
                if (qualitymanagement.getCreatorname() != null) {
                    existingQualitymanagement.setCreatorname(qualitymanagement.getCreatorname());
                }
                if (qualitymanagement.getSecretlevel() != null) {
                    existingQualitymanagement.setSecretlevel(qualitymanagement.getSecretlevel());
                }
                if (qualitymanagement.getAuditStatus() != null) {
                    existingQualitymanagement.setAuditStatus(qualitymanagement.getAuditStatus());
                }

                return existingQualitymanagement;
            })
            .map(qualitymanagementRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualitymanagement.getId().toString())
        );
    }

    /**
     * {@code GET  /qualitymanagements} : get all the qualitymanagements.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qualitymanagements in body.
     */
    @GetMapping("")
    public List<Qualitymanagement> getAllQualitymanagements(@RequestParam(name = "filter", required = false) String filter) {
        if ("project-is-null".equals(filter)) {
            log.debug("REST request to get all Qualitymanagements where project is null");
            return StreamSupport.stream(qualitymanagementRepository.findAll().spliterator(), false)
                .filter(qualitymanagement -> qualitymanagement.getProject() == null)
                .toList();
        }
        log.debug("REST request to get all Qualitymanagements");
        return qualitymanagementRepository.findAll();
    }

    /**
     * {@code GET  /qualitymanagements/:id} : get the "id" qualitymanagement.
     *
     * @param id the id of the qualitymanagement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qualitymanagement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Qualitymanagement> getQualitymanagement(@PathVariable("id") Long id) {
        log.debug("REST request to get Qualitymanagement : {}", id);
        Optional<Qualitymanagement> qualitymanagement = qualitymanagementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(qualitymanagement);
    }

    /**
     * {@code DELETE  /qualitymanagements/:id} : delete the "id" qualitymanagement.
     *
     * @param id the id of the qualitymanagement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQualitymanagement(@PathVariable("id") Long id) {
        log.debug("REST request to delete Qualitymanagement : {}", id);
        qualitymanagementRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
