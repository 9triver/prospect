package com.cvicse.web.rest;

import com.cvicse.domain.Pbsmanage;
import com.cvicse.repository.PbsmanageRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Pbsmanage}.
 */
@RestController
@RequestMapping("/api/pbsmanages")
@Transactional
public class PbsmanageResource {

    private final Logger log = LoggerFactory.getLogger(PbsmanageResource.class);

    private static final String ENTITY_NAME = "pbsmanage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PbsmanageRepository pbsmanageRepository;

    public PbsmanageResource(PbsmanageRepository pbsmanageRepository) {
        this.pbsmanageRepository = pbsmanageRepository;
    }

    /**
     * {@code POST  /pbsmanages} : Create a new pbsmanage.
     *
     * @param pbsmanage the pbsmanage to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pbsmanage, or with status {@code 400 (Bad Request)} if the pbsmanage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Pbsmanage> createPbsmanage(@RequestBody Pbsmanage pbsmanage) throws URISyntaxException {
        log.debug("REST request to save Pbsmanage : {}", pbsmanage);
        if (pbsmanage.getId() != null) {
            throw new BadRequestAlertException("A new pbsmanage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        pbsmanage = pbsmanageRepository.save(pbsmanage);
        return ResponseEntity.created(new URI("/api/pbsmanages/" + pbsmanage.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, pbsmanage.getId().toString()))
            .body(pbsmanage);
    }

    /**
     * {@code PUT  /pbsmanages/:id} : Updates an existing pbsmanage.
     *
     * @param id the id of the pbsmanage to save.
     * @param pbsmanage the pbsmanage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pbsmanage,
     * or with status {@code 400 (Bad Request)} if the pbsmanage is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pbsmanage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Pbsmanage> updatePbsmanage(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Pbsmanage pbsmanage
    ) throws URISyntaxException {
        log.debug("REST request to update Pbsmanage : {}, {}", id, pbsmanage);
        if (pbsmanage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pbsmanage.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pbsmanageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        pbsmanage = pbsmanageRepository.save(pbsmanage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pbsmanage.getId().toString()))
            .body(pbsmanage);
    }

    /**
     * {@code PATCH  /pbsmanages/:id} : Partial updates given fields of an existing pbsmanage, field will ignore if it is null
     *
     * @param id the id of the pbsmanage to save.
     * @param pbsmanage the pbsmanage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pbsmanage,
     * or with status {@code 400 (Bad Request)} if the pbsmanage is not valid,
     * or with status {@code 404 (Not Found)} if the pbsmanage is not found,
     * or with status {@code 500 (Internal Server Error)} if the pbsmanage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Pbsmanage> partialUpdatePbsmanage(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Pbsmanage pbsmanage
    ) throws URISyntaxException {
        log.debug("REST request to partial update Pbsmanage partially : {}, {}", id, pbsmanage);
        if (pbsmanage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pbsmanage.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pbsmanageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Pbsmanage> result = pbsmanageRepository
            .findById(pbsmanage.getId())
            .map(existingPbsmanage -> {
                if (pbsmanage.getPbsid() != null) {
                    existingPbsmanage.setPbsid(pbsmanage.getPbsid());
                }
                if (pbsmanage.getPbsname() != null) {
                    existingPbsmanage.setPbsname(pbsmanage.getPbsname());
                }
                if (pbsmanage.getNumber() != null) {
                    existingPbsmanage.setNumber(pbsmanage.getNumber());
                }
                if (pbsmanage.getType() != null) {
                    existingPbsmanage.setType(pbsmanage.getType());
                }
                if (pbsmanage.getAdministratorid() != null) {
                    existingPbsmanage.setAdministratorid(pbsmanage.getAdministratorid());
                }
                if (pbsmanage.getAdministratorname() != null) {
                    existingPbsmanage.setAdministratorname(pbsmanage.getAdministratorname());
                }
                if (pbsmanage.getResponsiblename() != null) {
                    existingPbsmanage.setResponsiblename(pbsmanage.getResponsiblename());
                }
                if (pbsmanage.getDepartment() != null) {
                    existingPbsmanage.setDepartment(pbsmanage.getDepartment());
                }
                if (pbsmanage.getSecretlevel() != null) {
                    existingPbsmanage.setSecretlevel(pbsmanage.getSecretlevel());
                }
                if (pbsmanage.getAuditStatus() != null) {
                    existingPbsmanage.setAuditStatus(pbsmanage.getAuditStatus());
                }
                if (pbsmanage.getAuditUserid() != null) {
                    existingPbsmanage.setAuditUserid(pbsmanage.getAuditUserid());
                }

                return existingPbsmanage;
            })
            .map(pbsmanageRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pbsmanage.getId().toString())
        );
    }

    /**
     * {@code GET  /pbsmanages} : get all the pbsmanages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pbsmanages in body.
     */
    @GetMapping("")
    public List<Pbsmanage> getAllPbsmanages() {
        log.debug("REST request to get all Pbsmanages");
        return pbsmanageRepository.findAll();
    }

    /**
     * {@code GET  /pbsmanages/:id} : get the "id" pbsmanage.
     *
     * @param id the id of the pbsmanage to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pbsmanage, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pbsmanage> getPbsmanage(@PathVariable("id") Long id) {
        log.debug("REST request to get Pbsmanage : {}", id);
        Optional<Pbsmanage> pbsmanage = pbsmanageRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pbsmanage);
    }

    /**
     * {@code DELETE  /pbsmanages/:id} : delete the "id" pbsmanage.
     *
     * @param id the id of the pbsmanage to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePbsmanage(@PathVariable("id") Long id) {
        log.debug("REST request to delete Pbsmanage : {}", id);
        pbsmanageRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
