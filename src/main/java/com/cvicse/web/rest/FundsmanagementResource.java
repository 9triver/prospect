package com.cvicse.web.rest;

import com.cvicse.domain.Fundsmanagement;
import com.cvicse.repository.FundsmanagementRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Fundsmanagement}.
 */
@RestController
@RequestMapping("/api/fundsmanagements")
@Transactional
public class FundsmanagementResource {

    private final Logger log = LoggerFactory.getLogger(FundsmanagementResource.class);

    private static final String ENTITY_NAME = "fundsmanagement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FundsmanagementRepository fundsmanagementRepository;

    public FundsmanagementResource(FundsmanagementRepository fundsmanagementRepository) {
        this.fundsmanagementRepository = fundsmanagementRepository;
    }

    /**
     * {@code POST  /fundsmanagements} : Create a new fundsmanagement.
     *
     * @param fundsmanagement the fundsmanagement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fundsmanagement, or with status {@code 400 (Bad Request)} if the fundsmanagement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Fundsmanagement> createFundsmanagement(@Valid @RequestBody Fundsmanagement fundsmanagement)
        throws URISyntaxException {
        log.debug("REST request to save Fundsmanagement : {}", fundsmanagement);
        if (fundsmanagement.getId() != null) {
            throw new BadRequestAlertException("A new fundsmanagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fundsmanagement = fundsmanagementRepository.save(fundsmanagement);
        return ResponseEntity.created(new URI("/api/fundsmanagements/" + fundsmanagement.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, fundsmanagement.getId().toString()))
            .body(fundsmanagement);
    }

    /**
     * {@code PUT  /fundsmanagements/:id} : Updates an existing fundsmanagement.
     *
     * @param id the id of the fundsmanagement to save.
     * @param fundsmanagement the fundsmanagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fundsmanagement,
     * or with status {@code 400 (Bad Request)} if the fundsmanagement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fundsmanagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Fundsmanagement> updateFundsmanagement(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Fundsmanagement fundsmanagement
    ) throws URISyntaxException {
        log.debug("REST request to update Fundsmanagement : {}, {}", id, fundsmanagement);
        if (fundsmanagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fundsmanagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fundsmanagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        fundsmanagement = fundsmanagementRepository.save(fundsmanagement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fundsmanagement.getId().toString()))
            .body(fundsmanagement);
    }

    /**
     * {@code PATCH  /fundsmanagements/:id} : Partial updates given fields of an existing fundsmanagement, field will ignore if it is null
     *
     * @param id the id of the fundsmanagement to save.
     * @param fundsmanagement the fundsmanagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fundsmanagement,
     * or with status {@code 400 (Bad Request)} if the fundsmanagement is not valid,
     * or with status {@code 404 (Not Found)} if the fundsmanagement is not found,
     * or with status {@code 500 (Internal Server Error)} if the fundsmanagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Fundsmanagement> partialUpdateFundsmanagement(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Fundsmanagement fundsmanagement
    ) throws URISyntaxException {
        log.debug("REST request to partial update Fundsmanagement partially : {}, {}", id, fundsmanagement);
        if (fundsmanagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fundsmanagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fundsmanagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Fundsmanagement> result = fundsmanagementRepository
            .findById(fundsmanagement.getId())
            .map(existingFundsmanagement -> {
                if (fundsmanagement.getFundsid() != null) {
                    existingFundsmanagement.setFundsid(fundsmanagement.getFundsid());
                }
                if (fundsmanagement.getCreatetime() != null) {
                    existingFundsmanagement.setCreatetime(fundsmanagement.getCreatetime());
                }
                if (fundsmanagement.getCreatorname() != null) {
                    existingFundsmanagement.setCreatorname(fundsmanagement.getCreatorname());
                }
                if (fundsmanagement.getSecretlevel() != null) {
                    existingFundsmanagement.setSecretlevel(fundsmanagement.getSecretlevel());
                }
                if (fundsmanagement.getYear() != null) {
                    existingFundsmanagement.setYear(fundsmanagement.getYear());
                }
                if (fundsmanagement.getBudgit() != null) {
                    existingFundsmanagement.setBudgit(fundsmanagement.getBudgit());
                }
                if (fundsmanagement.getDapartmentid() != null) {
                    existingFundsmanagement.setDapartmentid(fundsmanagement.getDapartmentid());
                }
                if (fundsmanagement.getDraftapproval() != null) {
                    existingFundsmanagement.setDraftapproval(fundsmanagement.getDraftapproval());
                }
                if (fundsmanagement.getTotalbudgetid() != null) {
                    existingFundsmanagement.setTotalbudgetid(fundsmanagement.getTotalbudgetid());
                }
                if (fundsmanagement.getUnitbudgetid() != null) {
                    existingFundsmanagement.setUnitbudgetid(fundsmanagement.getUnitbudgetid());
                }
                if (fundsmanagement.getDocumentid() != null) {
                    existingFundsmanagement.setDocumentid(fundsmanagement.getDocumentid());
                }
                if (fundsmanagement.getMaintainerid() != null) {
                    existingFundsmanagement.setMaintainerid(fundsmanagement.getMaintainerid());
                }
                if (fundsmanagement.getAuditStatus() != null) {
                    existingFundsmanagement.setAuditStatus(fundsmanagement.getAuditStatus());
                }

                return existingFundsmanagement;
            })
            .map(fundsmanagementRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fundsmanagement.getId().toString())
        );
    }

    /**
     * {@code GET  /fundsmanagements} : get all the fundsmanagements.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fundsmanagements in body.
     */
    @GetMapping("")
    public List<Fundsmanagement> getAllFundsmanagements(@RequestParam(name = "filter", required = false) String filter) {
        if ("project-is-null".equals(filter)) {
            log.debug("REST request to get all Fundsmanagements where project is null");
            return StreamSupport.stream(fundsmanagementRepository.findAll().spliterator(), false)
                .filter(fundsmanagement -> fundsmanagement.getProject() == null)
                .toList();
        }

        if ("comprehensivecontrol-is-null".equals(filter)) {
            log.debug("REST request to get all Fundsmanagements where comprehensivecontrol is null");
            return StreamSupport.stream(fundsmanagementRepository.findAll().spliterator(), false)
                .filter(fundsmanagement -> fundsmanagement.getComprehensivecontrol() == null)
                .toList();
        }

        if ("fundsavailability-is-null".equals(filter)) {
            log.debug("REST request to get all Fundsmanagements where fundsavailability is null");
            return StreamSupport.stream(fundsmanagementRepository.findAll().spliterator(), false)
                .filter(fundsmanagement -> fundsmanagement.getFundsavailability() == null)
                .toList();
        }
        log.debug("REST request to get all Fundsmanagements");
        return fundsmanagementRepository.findAll();
    }

    /**
     * {@code GET  /fundsmanagements/:id} : get the "id" fundsmanagement.
     *
     * @param id the id of the fundsmanagement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fundsmanagement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Fundsmanagement> getFundsmanagement(@PathVariable("id") Long id) {
        log.debug("REST request to get Fundsmanagement : {}", id);
        Optional<Fundsmanagement> fundsmanagement = fundsmanagementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fundsmanagement);
    }

    /**
     * {@code DELETE  /fundsmanagements/:id} : delete the "id" fundsmanagement.
     *
     * @param id the id of the fundsmanagement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFundsmanagement(@PathVariable("id") Long id) {
        log.debug("REST request to delete Fundsmanagement : {}", id);
        fundsmanagementRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
