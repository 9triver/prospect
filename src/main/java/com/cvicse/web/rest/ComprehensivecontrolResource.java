package com.cvicse.web.rest;

import com.cvicse.domain.Comprehensivecontrol;
import com.cvicse.repository.ComprehensivecontrolRepository;
import com.cvicse.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.cvicse.domain.Comprehensivecontrol}.
 */
@RestController
@RequestMapping("/api/comprehensivecontrols")
@Transactional
public class ComprehensivecontrolResource {

    private final Logger log = LoggerFactory.getLogger(ComprehensivecontrolResource.class);

    private static final String ENTITY_NAME = "comprehensivecontrol";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ComprehensivecontrolRepository comprehensivecontrolRepository;

    public ComprehensivecontrolResource(ComprehensivecontrolRepository comprehensivecontrolRepository) {
        this.comprehensivecontrolRepository = comprehensivecontrolRepository;
    }

    /**
     * {@code POST  /comprehensivecontrols} : Create a new comprehensivecontrol.
     *
     * @param comprehensivecontrol the comprehensivecontrol to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new comprehensivecontrol, or with status {@code 400 (Bad Request)} if the comprehensivecontrol has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Comprehensivecontrol> createComprehensivecontrol(@Valid @RequestBody Comprehensivecontrol comprehensivecontrol)
        throws URISyntaxException {
        log.debug("REST request to save Comprehensivecontrol : {}", comprehensivecontrol);
        if (comprehensivecontrol.getId() != null) {
            throw new BadRequestAlertException("A new comprehensivecontrol cannot already have an ID", ENTITY_NAME, "idexists");
        }
        comprehensivecontrol = comprehensivecontrolRepository.save(comprehensivecontrol);
        return ResponseEntity.created(new URI("/api/comprehensivecontrols/" + comprehensivecontrol.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, comprehensivecontrol.getId().toString()))
            .body(comprehensivecontrol);
    }

    /**
     * {@code PUT  /comprehensivecontrols/:id} : Updates an existing comprehensivecontrol.
     *
     * @param id the id of the comprehensivecontrol to save.
     * @param comprehensivecontrol the comprehensivecontrol to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated comprehensivecontrol,
     * or with status {@code 400 (Bad Request)} if the comprehensivecontrol is not valid,
     * or with status {@code 500 (Internal Server Error)} if the comprehensivecontrol couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Comprehensivecontrol> updateComprehensivecontrol(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Comprehensivecontrol comprehensivecontrol
    ) throws URISyntaxException {
        log.debug("REST request to update Comprehensivecontrol : {}, {}", id, comprehensivecontrol);
        if (comprehensivecontrol.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, comprehensivecontrol.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!comprehensivecontrolRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        comprehensivecontrol = comprehensivecontrolRepository.save(comprehensivecontrol);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, comprehensivecontrol.getId().toString()))
            .body(comprehensivecontrol);
    }

    /**
     * {@code PATCH  /comprehensivecontrols/:id} : Partial updates given fields of an existing comprehensivecontrol, field will ignore if it is null
     *
     * @param id the id of the comprehensivecontrol to save.
     * @param comprehensivecontrol the comprehensivecontrol to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated comprehensivecontrol,
     * or with status {@code 400 (Bad Request)} if the comprehensivecontrol is not valid,
     * or with status {@code 404 (Not Found)} if the comprehensivecontrol is not found,
     * or with status {@code 500 (Internal Server Error)} if the comprehensivecontrol couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Comprehensivecontrol> partialUpdateComprehensivecontrol(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Comprehensivecontrol comprehensivecontrol
    ) throws URISyntaxException {
        log.debug("REST request to partial update Comprehensivecontrol partially : {}, {}", id, comprehensivecontrol);
        if (comprehensivecontrol.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, comprehensivecontrol.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!comprehensivecontrolRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Comprehensivecontrol> result = comprehensivecontrolRepository
            .findById(comprehensivecontrol.getId())
            .map(existingComprehensivecontrol -> {
                if (comprehensivecontrol.getControlid() != null) {
                    existingComprehensivecontrol.setControlid(comprehensivecontrol.getControlid());
                }
                if (comprehensivecontrol.getDescription() != null) {
                    existingComprehensivecontrol.setDescription(comprehensivecontrol.getDescription());
                }
                if (comprehensivecontrol.getNumber() != null) {
                    existingComprehensivecontrol.setNumber(comprehensivecontrol.getNumber());
                }
                if (comprehensivecontrol.getType() != null) {
                    existingComprehensivecontrol.setType(comprehensivecontrol.getType());
                }
                if (comprehensivecontrol.getStarttime() != null) {
                    existingComprehensivecontrol.setStarttime(comprehensivecontrol.getStarttime());
                }
                if (comprehensivecontrol.getEndtime() != null) {
                    existingComprehensivecontrol.setEndtime(comprehensivecontrol.getEndtime());
                }
                if (comprehensivecontrol.getActualstarttime() != null) {
                    existingComprehensivecontrol.setActualstarttime(comprehensivecontrol.getActualstarttime());
                }
                if (comprehensivecontrol.getActualendtime() != null) {
                    existingComprehensivecontrol.setActualendtime(comprehensivecontrol.getActualendtime());
                }
                if (comprehensivecontrol.getResult() != null) {
                    existingComprehensivecontrol.setResult(comprehensivecontrol.getResult());
                }
                if (comprehensivecontrol.getAcceptancetype() != null) {
                    existingComprehensivecontrol.setAcceptancetype(comprehensivecontrol.getAcceptancetype());
                }
                if (comprehensivecontrol.getStatus() != null) {
                    existingComprehensivecontrol.setStatus(comprehensivecontrol.getStatus());
                }
                if (comprehensivecontrol.getAuditStatus() != null) {
                    existingComprehensivecontrol.setAuditStatus(comprehensivecontrol.getAuditStatus());
                }
                if (comprehensivecontrol.getResponsiblename() != null) {
                    existingComprehensivecontrol.setResponsiblename(comprehensivecontrol.getResponsiblename());
                }

                return existingComprehensivecontrol;
            })
            .map(comprehensivecontrolRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, comprehensivecontrol.getId().toString())
        );
    }

    /**
     * {@code GET  /comprehensivecontrols} : get all the comprehensivecontrols.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of comprehensivecontrols in body.
     */
    @GetMapping("")
    public List<Comprehensivecontrol> getAllComprehensivecontrols() {
        log.debug("REST request to get all Comprehensivecontrols");
        return comprehensivecontrolRepository.findAll();
    }

    /**
     * {@code GET  /comprehensivecontrols/:id} : get the "id" comprehensivecontrol.
     *
     * @param id the id of the comprehensivecontrol to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the comprehensivecontrol, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Comprehensivecontrol> getComprehensivecontrol(@PathVariable("id") Long id) {
        log.debug("REST request to get Comprehensivecontrol : {}", id);
        Optional<Comprehensivecontrol> comprehensivecontrol = comprehensivecontrolRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(comprehensivecontrol);
    }

    /**
     * {@code DELETE  /comprehensivecontrols/:id} : delete the "id" comprehensivecontrol.
     *
     * @param id the id of the comprehensivecontrol to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComprehensivecontrol(@PathVariable("id") Long id) {
        log.debug("REST request to delete Comprehensivecontrol : {}", id);
        comprehensivecontrolRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
