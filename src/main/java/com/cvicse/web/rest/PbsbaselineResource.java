package com.cvicse.web.rest;

import com.cvicse.domain.Pbsbaseline;
import com.cvicse.repository.PbsbaselineRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Pbsbaseline}.
 */
@RestController
@RequestMapping("/api/pbsbaselines")
@Transactional
public class PbsbaselineResource {

    private final Logger log = LoggerFactory.getLogger(PbsbaselineResource.class);

    private static final String ENTITY_NAME = "pbsbaseline";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PbsbaselineRepository pbsbaselineRepository;

    public PbsbaselineResource(PbsbaselineRepository pbsbaselineRepository) {
        this.pbsbaselineRepository = pbsbaselineRepository;
    }

    /**
     * {@code POST  /pbsbaselines} : Create a new pbsbaseline.
     *
     * @param pbsbaseline the pbsbaseline to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pbsbaseline, or with status {@code 400 (Bad Request)} if the pbsbaseline has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Pbsbaseline> createPbsbaseline(@RequestBody Pbsbaseline pbsbaseline) throws URISyntaxException {
        log.debug("REST request to save Pbsbaseline : {}", pbsbaseline);
        if (pbsbaseline.getId() != null) {
            throw new BadRequestAlertException("A new pbsbaseline cannot already have an ID", ENTITY_NAME, "idexists");
        }
        pbsbaseline = pbsbaselineRepository.save(pbsbaseline);
        return ResponseEntity.created(new URI("/api/pbsbaselines/" + pbsbaseline.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, pbsbaseline.getId()))
            .body(pbsbaseline);
    }

    /**
     * {@code PUT  /pbsbaselines/:id} : Updates an existing pbsbaseline.
     *
     * @param id the id of the pbsbaseline to save.
     * @param pbsbaseline the pbsbaseline to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pbsbaseline,
     * or with status {@code 400 (Bad Request)} if the pbsbaseline is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pbsbaseline couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Pbsbaseline> updatePbsbaseline(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Pbsbaseline pbsbaseline
    ) throws URISyntaxException {
        log.debug("REST request to update Pbsbaseline : {}, {}", id, pbsbaseline);
        if (pbsbaseline.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pbsbaseline.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pbsbaselineRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        pbsbaseline = pbsbaselineRepository.save(pbsbaseline);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pbsbaseline.getId()))
            .body(pbsbaseline);
    }

    /**
     * {@code PATCH  /pbsbaselines/:id} : Partial updates given fields of an existing pbsbaseline, field will ignore if it is null
     *
     * @param id the id of the pbsbaseline to save.
     * @param pbsbaseline the pbsbaseline to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pbsbaseline,
     * or with status {@code 400 (Bad Request)} if the pbsbaseline is not valid,
     * or with status {@code 404 (Not Found)} if the pbsbaseline is not found,
     * or with status {@code 500 (Internal Server Error)} if the pbsbaseline couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Pbsbaseline> partialUpdatePbsbaseline(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Pbsbaseline pbsbaseline
    ) throws URISyntaxException {
        log.debug("REST request to partial update Pbsbaseline partially : {}, {}", id, pbsbaseline);
        if (pbsbaseline.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pbsbaseline.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pbsbaselineRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Pbsbaseline> result = pbsbaselineRepository
            .findById(pbsbaseline.getId())
            .map(existingPbsbaseline -> {
                if (pbsbaseline.getSecretlevel() != null) {
                    existingPbsbaseline.setSecretlevel(pbsbaseline.getSecretlevel());
                }
                if (pbsbaseline.getRequestdeportment() != null) {
                    existingPbsbaseline.setRequestdeportment(pbsbaseline.getRequestdeportment());
                }
                if (pbsbaseline.getChargetype() != null) {
                    existingPbsbaseline.setChargetype(pbsbaseline.getChargetype());
                }
                if (pbsbaseline.getChargecontent() != null) {
                    existingPbsbaseline.setChargecontent(pbsbaseline.getChargecontent());
                }
                if (pbsbaseline.getStatus() != null) {
                    existingPbsbaseline.setStatus(pbsbaseline.getStatus());
                }
                if (pbsbaseline.getVersion() != null) {
                    existingPbsbaseline.setVersion(pbsbaseline.getVersion());
                }
                if (pbsbaseline.getRemark() != null) {
                    existingPbsbaseline.setRemark(pbsbaseline.getRemark());
                }

                return existingPbsbaseline;
            })
            .map(pbsbaselineRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pbsbaseline.getId())
        );
    }

    /**
     * {@code GET  /pbsbaselines} : get all the pbsbaselines.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pbsbaselines in body.
     */
    @GetMapping("")
    public List<Pbsbaseline> getAllPbsbaselines() {
        log.debug("REST request to get all Pbsbaselines");
        return pbsbaselineRepository.findAll();
    }

    /**
     * {@code GET  /pbsbaselines/:id} : get the "id" pbsbaseline.
     *
     * @param id the id of the pbsbaseline to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pbsbaseline, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pbsbaseline> getPbsbaseline(@PathVariable("id") String id) {
        log.debug("REST request to get Pbsbaseline : {}", id);
        Optional<Pbsbaseline> pbsbaseline = pbsbaselineRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pbsbaseline);
    }

    /**
     * {@code DELETE  /pbsbaselines/:id} : delete the "id" pbsbaseline.
     *
     * @param id the id of the pbsbaseline to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePbsbaseline(@PathVariable("id") String id) {
        log.debug("REST request to delete Pbsbaseline : {}", id);
        pbsbaselineRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
