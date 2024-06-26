package com.cvicse.web.rest;

import com.cvicse.domain.Qualityobjectives;
import com.cvicse.repository.QualityobjectivesRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Qualityobjectives}.
 */
@RestController
@RequestMapping("/api/qualityobjectives")
@Transactional
public class QualityobjectivesResource {

    private final Logger log = LoggerFactory.getLogger(QualityobjectivesResource.class);

    private static final String ENTITY_NAME = "qualityobjectives";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QualityobjectivesRepository qualityobjectivesRepository;

    public QualityobjectivesResource(QualityobjectivesRepository qualityobjectivesRepository) {
        this.qualityobjectivesRepository = qualityobjectivesRepository;
    }

    /**
     * {@code POST  /qualityobjectives} : Create a new qualityobjectives.
     *
     * @param qualityobjectives the qualityobjectives to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qualityobjectives, or with status {@code 400 (Bad Request)} if the qualityobjectives has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Qualityobjectives> createQualityobjectives(@RequestBody Qualityobjectives qualityobjectives)
        throws URISyntaxException {
        log.debug("REST request to save Qualityobjectives : {}", qualityobjectives);
        if (qualityobjectives.getId() != null) {
            throw new BadRequestAlertException("A new qualityobjectives cannot already have an ID", ENTITY_NAME, "idexists");
        }
        qualityobjectives = qualityobjectivesRepository.save(qualityobjectives);
        return ResponseEntity.created(new URI("/api/qualityobjectives/" + qualityobjectives.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, qualityobjectives.getId()))
            .body(qualityobjectives);
    }

    /**
     * {@code PUT  /qualityobjectives/:id} : Updates an existing qualityobjectives.
     *
     * @param id the id of the qualityobjectives to save.
     * @param qualityobjectives the qualityobjectives to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualityobjectives,
     * or with status {@code 400 (Bad Request)} if the qualityobjectives is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qualityobjectives couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Qualityobjectives> updateQualityobjectives(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Qualityobjectives qualityobjectives
    ) throws URISyntaxException {
        log.debug("REST request to update Qualityobjectives : {}, {}", id, qualityobjectives);
        if (qualityobjectives.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualityobjectives.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualityobjectivesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        qualityobjectives = qualityobjectivesRepository.save(qualityobjectives);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualityobjectives.getId()))
            .body(qualityobjectives);
    }

    /**
     * {@code PATCH  /qualityobjectives/:id} : Partial updates given fields of an existing qualityobjectives, field will ignore if it is null
     *
     * @param id the id of the qualityobjectives to save.
     * @param qualityobjectives the qualityobjectives to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualityobjectives,
     * or with status {@code 400 (Bad Request)} if the qualityobjectives is not valid,
     * or with status {@code 404 (Not Found)} if the qualityobjectives is not found,
     * or with status {@code 500 (Internal Server Error)} if the qualityobjectives couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Qualityobjectives> partialUpdateQualityobjectives(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Qualityobjectives qualityobjectives
    ) throws URISyntaxException {
        log.debug("REST request to partial update Qualityobjectives partially : {}, {}", id, qualityobjectives);
        if (qualityobjectives.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualityobjectives.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualityobjectivesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Qualityobjectives> result = qualityobjectivesRepository
            .findById(qualityobjectives.getId())
            .map(existingQualityobjectives -> {
                if (qualityobjectives.getQualityobjectivesname() != null) {
                    existingQualityobjectives.setQualityobjectivesname(qualityobjectives.getQualityobjectivesname());
                }
                if (qualityobjectives.getYear() != null) {
                    existingQualityobjectives.setYear(qualityobjectives.getYear());
                }
                if (qualityobjectives.getCreatetime() != null) {
                    existingQualityobjectives.setCreatetime(qualityobjectives.getCreatetime());
                }
                if (qualityobjectives.getCreatorname() != null) {
                    existingQualityobjectives.setCreatorname(qualityobjectives.getCreatorname());
                }
                if (qualityobjectives.getSecretlevel() != null) {
                    existingQualityobjectives.setSecretlevel(qualityobjectives.getSecretlevel());
                }
                if (qualityobjectives.getAuditStatus() != null) {
                    existingQualityobjectives.setAuditStatus(qualityobjectives.getAuditStatus());
                }

                return existingQualityobjectives;
            })
            .map(qualityobjectivesRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualityobjectives.getId())
        );
    }

    /**
     * {@code GET  /qualityobjectives} : get all the qualityobjectives.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qualityobjectives in body.
     */
    @GetMapping("")
    public List<Qualityobjectives> getAllQualityobjectives() {
        log.debug("REST request to get all Qualityobjectives");
        return qualityobjectivesRepository.findAll();
    }

    /**
     * {@code GET  /qualityobjectives/:id} : get the "id" qualityobjectives.
     *
     * @param id the id of the qualityobjectives to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qualityobjectives, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Qualityobjectives> getQualityobjectives(@PathVariable("id") String id) {
        log.debug("REST request to get Qualityobjectives : {}", id);
        Optional<Qualityobjectives> qualityobjectives = qualityobjectivesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(qualityobjectives);
    }

    /**
     * {@code DELETE  /qualityobjectives/:id} : delete the "id" qualityobjectives.
     *
     * @param id the id of the qualityobjectives to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQualityobjectives(@PathVariable("id") String id) {
        log.debug("REST request to delete Qualityobjectives : {}", id);
        qualityobjectivesRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
