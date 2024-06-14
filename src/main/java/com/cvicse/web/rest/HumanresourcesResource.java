package com.cvicse.web.rest;

import com.cvicse.domain.Humanresources;
import com.cvicse.repository.HumanresourcesRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Humanresources}.
 */
@RestController
@RequestMapping("/api/humanresources")
@Transactional
public class HumanresourcesResource {

    private final Logger log = LoggerFactory.getLogger(HumanresourcesResource.class);

    private static final String ENTITY_NAME = "humanresources";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HumanresourcesRepository humanresourcesRepository;

    public HumanresourcesResource(HumanresourcesRepository humanresourcesRepository) {
        this.humanresourcesRepository = humanresourcesRepository;
    }

    /**
     * {@code POST  /humanresources} : Create a new humanresources.
     *
     * @param humanresources the humanresources to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new humanresources, or with status {@code 400 (Bad Request)} if the humanresources has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Humanresources> createHumanresources(@RequestBody Humanresources humanresources) throws URISyntaxException {
        log.debug("REST request to save Humanresources : {}", humanresources);
        if (humanresources.getId() != null) {
            throw new BadRequestAlertException("A new humanresources cannot already have an ID", ENTITY_NAME, "idexists");
        }
        humanresources = humanresourcesRepository.save(humanresources);
        return ResponseEntity.created(new URI("/api/humanresources/" + humanresources.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, humanresources.getId().toString()))
            .body(humanresources);
    }

    /**
     * {@code PUT  /humanresources/:id} : Updates an existing humanresources.
     *
     * @param id the id of the humanresources to save.
     * @param humanresources the humanresources to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated humanresources,
     * or with status {@code 400 (Bad Request)} if the humanresources is not valid,
     * or with status {@code 500 (Internal Server Error)} if the humanresources couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Humanresources> updateHumanresources(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Humanresources humanresources
    ) throws URISyntaxException {
        log.debug("REST request to update Humanresources : {}, {}", id, humanresources);
        if (humanresources.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, humanresources.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!humanresourcesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        humanresources = humanresourcesRepository.save(humanresources);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, humanresources.getId().toString()))
            .body(humanresources);
    }

    /**
     * {@code PATCH  /humanresources/:id} : Partial updates given fields of an existing humanresources, field will ignore if it is null
     *
     * @param id the id of the humanresources to save.
     * @param humanresources the humanresources to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated humanresources,
     * or with status {@code 400 (Bad Request)} if the humanresources is not valid,
     * or with status {@code 404 (Not Found)} if the humanresources is not found,
     * or with status {@code 500 (Internal Server Error)} if the humanresources couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Humanresources> partialUpdateHumanresources(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Humanresources humanresources
    ) throws URISyntaxException {
        log.debug("REST request to partial update Humanresources partially : {}, {}", id, humanresources);
        if (humanresources.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, humanresources.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!humanresourcesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Humanresources> result = humanresourcesRepository
            .findById(humanresources.getId())
            .map(existingHumanresources -> {
                if (humanresources.getHumanresourcesid() != null) {
                    existingHumanresources.setHumanresourcesid(humanresources.getHumanresourcesid());
                }
                if (humanresources.getName() != null) {
                    existingHumanresources.setName(humanresources.getName());
                }
                if (humanresources.getOutdeportment() != null) {
                    existingHumanresources.setOutdeportment(humanresources.getOutdeportment());
                }
                if (humanresources.getIndeportment() != null) {
                    existingHumanresources.setIndeportment(humanresources.getIndeportment());
                }
                if (humanresources.getAdjusttime() != null) {
                    existingHumanresources.setAdjusttime(humanresources.getAdjusttime());
                }
                if (humanresources.getProjectname() != null) {
                    existingHumanresources.setProjectname(humanresources.getProjectname());
                }
                if (humanresources.getDeportment() != null) {
                    existingHumanresources.setDeportment(humanresources.getDeportment());
                }
                if (humanresources.getProjectleader() != null) {
                    existingHumanresources.setProjectleader(humanresources.getProjectleader());
                }
                if (humanresources.getSecretlevel() != null) {
                    existingHumanresources.setSecretlevel(humanresources.getSecretlevel());
                }
                if (humanresources.getAuditStatus() != null) {
                    existingHumanresources.setAuditStatus(humanresources.getAuditStatus());
                }

                return existingHumanresources;
            })
            .map(humanresourcesRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, humanresources.getId().toString())
        );
    }

    /**
     * {@code GET  /humanresources} : get all the humanresources.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of humanresources in body.
     */
    @GetMapping("")
    public List<Humanresources> getAllHumanresources() {
        log.debug("REST request to get all Humanresources");
        return humanresourcesRepository.findAll();
    }

    /**
     * {@code GET  /humanresources/:id} : get the "id" humanresources.
     *
     * @param id the id of the humanresources to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the humanresources, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Humanresources> getHumanresources(@PathVariable("id") Long id) {
        log.debug("REST request to get Humanresources : {}", id);
        Optional<Humanresources> humanresources = humanresourcesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(humanresources);
    }

    /**
     * {@code DELETE  /humanresources/:id} : delete the "id" humanresources.
     *
     * @param id the id of the humanresources to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHumanresources(@PathVariable("id") Long id) {
        log.debug("REST request to delete Humanresources : {}", id);
        humanresourcesRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
