package com.cvicse.web.rest;

import com.cvicse.domain.Pbssubmanage;
import com.cvicse.repository.PbssubmanageRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Pbssubmanage}.
 */
@RestController
@RequestMapping("/api/pbssubmanages")
@Transactional
public class PbssubmanageResource {

    private final Logger log = LoggerFactory.getLogger(PbssubmanageResource.class);

    private static final String ENTITY_NAME = "pbssubmanage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PbssubmanageRepository pbssubmanageRepository;

    public PbssubmanageResource(PbssubmanageRepository pbssubmanageRepository) {
        this.pbssubmanageRepository = pbssubmanageRepository;
    }

    /**
     * {@code POST  /pbssubmanages} : Create a new pbssubmanage.
     *
     * @param pbssubmanage the pbssubmanage to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pbssubmanage, or with status {@code 400 (Bad Request)} if the pbssubmanage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Pbssubmanage> createPbssubmanage(@RequestBody Pbssubmanage pbssubmanage) throws URISyntaxException {
        log.debug("REST request to save Pbssubmanage : {}", pbssubmanage);
        if (pbssubmanage.getId() != null) {
            throw new BadRequestAlertException("A new pbssubmanage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        pbssubmanage = pbssubmanageRepository.save(pbssubmanage);
        return ResponseEntity.created(new URI("/api/pbssubmanages/" + pbssubmanage.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, pbssubmanage.getId().toString()))
            .body(pbssubmanage);
    }

    /**
     * {@code PUT  /pbssubmanages/:id} : Updates an existing pbssubmanage.
     *
     * @param id the id of the pbssubmanage to save.
     * @param pbssubmanage the pbssubmanage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pbssubmanage,
     * or with status {@code 400 (Bad Request)} if the pbssubmanage is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pbssubmanage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Pbssubmanage> updatePbssubmanage(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Pbssubmanage pbssubmanage
    ) throws URISyntaxException {
        log.debug("REST request to update Pbssubmanage : {}, {}", id, pbssubmanage);
        if (pbssubmanage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pbssubmanage.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pbssubmanageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        pbssubmanage = pbssubmanageRepository.save(pbssubmanage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pbssubmanage.getId().toString()))
            .body(pbssubmanage);
    }

    /**
     * {@code PATCH  /pbssubmanages/:id} : Partial updates given fields of an existing pbssubmanage, field will ignore if it is null
     *
     * @param id the id of the pbssubmanage to save.
     * @param pbssubmanage the pbssubmanage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pbssubmanage,
     * or with status {@code 400 (Bad Request)} if the pbssubmanage is not valid,
     * or with status {@code 404 (Not Found)} if the pbssubmanage is not found,
     * or with status {@code 500 (Internal Server Error)} if the pbssubmanage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Pbssubmanage> partialUpdatePbssubmanage(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Pbssubmanage pbssubmanage
    ) throws URISyntaxException {
        log.debug("REST request to partial update Pbssubmanage partially : {}, {}", id, pbssubmanage);
        if (pbssubmanage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pbssubmanage.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pbssubmanageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Pbssubmanage> result = pbssubmanageRepository
            .findById(pbssubmanage.getId())
            .map(existingPbssubmanage -> {
                if (pbssubmanage.getPbssubid() != null) {
                    existingPbssubmanage.setPbssubid(pbssubmanage.getPbssubid());
                }
                if (pbssubmanage.getPbssubname() != null) {
                    existingPbssubmanage.setPbssubname(pbssubmanage.getPbssubname());
                }
                if (pbssubmanage.getResponsiblename() != null) {
                    existingPbssubmanage.setResponsiblename(pbssubmanage.getResponsiblename());
                }
                if (pbssubmanage.getResponsibledepartment() != null) {
                    existingPbssubmanage.setResponsibledepartment(pbssubmanage.getResponsibledepartment());
                }
                if (pbssubmanage.getRelevantdepartment() != null) {
                    existingPbssubmanage.setRelevantdepartment(pbssubmanage.getRelevantdepartment());
                }
                if (pbssubmanage.getType() != null) {
                    existingPbssubmanage.setType(pbssubmanage.getType());
                }
                if (pbssubmanage.getStarttime() != null) {
                    existingPbssubmanage.setStarttime(pbssubmanage.getStarttime());
                }
                if (pbssubmanage.getEndtime() != null) {
                    existingPbssubmanage.setEndtime(pbssubmanage.getEndtime());
                }
                if (pbssubmanage.getSecretlevel() != null) {
                    existingPbssubmanage.setSecretlevel(pbssubmanage.getSecretlevel());
                }
                if (pbssubmanage.getAuditStatus() != null) {
                    existingPbssubmanage.setAuditStatus(pbssubmanage.getAuditStatus());
                }

                return existingPbssubmanage;
            })
            .map(pbssubmanageRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pbssubmanage.getId().toString())
        );
    }

    /**
     * {@code GET  /pbssubmanages} : get all the pbssubmanages.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pbssubmanages in body.
     */
    @GetMapping("")
    public List<Pbssubmanage> getAllPbssubmanages(@RequestParam(name = "filter", required = false) String filter) {
        if ("pbsmanage-is-null".equals(filter)) {
            log.debug("REST request to get all Pbssubmanages where pbsmanage is null");
            return StreamSupport.stream(pbssubmanageRepository.findAll().spliterator(), false)
                .filter(pbssubmanage -> pbssubmanage.getPbsmanage() == null)
                .toList();
        }

        if ("wbsmanage-is-null".equals(filter)) {
            log.debug("REST request to get all Pbssubmanages where wbsmanage is null");
            return StreamSupport.stream(pbssubmanageRepository.findAll().spliterator(), false)
                .filter(pbssubmanage -> pbssubmanage.getWbsmanage() == null)
                .toList();
        }
        log.debug("REST request to get all Pbssubmanages");
        return pbssubmanageRepository.findAll();
    }

    /**
     * {@code GET  /pbssubmanages/:id} : get the "id" pbssubmanage.
     *
     * @param id the id of the pbssubmanage to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pbssubmanage, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pbssubmanage> getPbssubmanage(@PathVariable("id") Long id) {
        log.debug("REST request to get Pbssubmanage : {}", id);
        Optional<Pbssubmanage> pbssubmanage = pbssubmanageRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pbssubmanage);
    }

    /**
     * {@code DELETE  /pbssubmanages/:id} : delete the "id" pbssubmanage.
     *
     * @param id the id of the pbssubmanage to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePbssubmanage(@PathVariable("id") Long id) {
        log.debug("REST request to delete Pbssubmanage : {}", id);
        pbssubmanageRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
