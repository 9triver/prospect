package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.HrManagement;
import com.cvicse.jy1.repository.HrManagementRepository;
import com.cvicse.jy1.service.HrManagementService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.HrManagement}.
 */
@RestController
@RequestMapping("/api/hr-managements")
public class HrManagementResource {

    private static final Logger log = LoggerFactory.getLogger(HrManagementResource.class);

    private static final String ENTITY_NAME = "hrManagement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HrManagementService hrManagementService;

    private final HrManagementRepository hrManagementRepository;

    public HrManagementResource(HrManagementService hrManagementService, HrManagementRepository hrManagementRepository) {
        this.hrManagementService = hrManagementService;
        this.hrManagementRepository = hrManagementRepository;
    }

    /**
     * {@code POST  /hr-managements} : Create a new hrManagement.
     *
     * @param hrManagement the hrManagement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hrManagement, or with status {@code 400 (Bad Request)} if the hrManagement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<HrManagement> createHrManagement(@RequestBody HrManagement hrManagement) throws URISyntaxException {
        log.debug("REST request to save HrManagement : {}", hrManagement);
        if (hrManagement.getId() != null) {
            throw new BadRequestAlertException("A new hrManagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        hrManagement = hrManagementService.save(hrManagement);
        return ResponseEntity.created(new URI("/api/hr-managements/" + hrManagement.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, hrManagement.getId().toString()))
            .body(hrManagement);
    }

    /**
     * {@code PUT  /hr-managements/:id} : Updates an existing hrManagement.
     *
     * @param id the id of the hrManagement to save.
     * @param hrManagement the hrManagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hrManagement,
     * or with status {@code 400 (Bad Request)} if the hrManagement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hrManagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<HrManagement> updateHrManagement(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody HrManagement hrManagement
    ) throws URISyntaxException {
        log.debug("REST request to update HrManagement : {}, {}", id, hrManagement);
        if (hrManagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hrManagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hrManagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        hrManagement = hrManagementService.update(hrManagement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, hrManagement.getId().toString()))
            .body(hrManagement);
    }

    /**
     * {@code PATCH  /hr-managements/:id} : Partial updates given fields of an existing hrManagement, field will ignore if it is null
     *
     * @param id the id of the hrManagement to save.
     * @param hrManagement the hrManagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hrManagement,
     * or with status {@code 400 (Bad Request)} if the hrManagement is not valid,
     * or with status {@code 404 (Not Found)} if the hrManagement is not found,
     * or with status {@code 500 (Internal Server Error)} if the hrManagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HrManagement> partialUpdateHrManagement(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody HrManagement hrManagement
    ) throws URISyntaxException {
        log.debug("REST request to partial update HrManagement partially : {}, {}", id, hrManagement);
        if (hrManagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hrManagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hrManagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HrManagement> result = hrManagementService.partialUpdate(hrManagement);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, hrManagement.getId().toString())
        );
    }

    /**
     * {@code GET  /hr-managements} : get all the hrManagements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hrManagements in body.
     */
    @GetMapping("")
    public List<HrManagement> getAllHrManagements() {
        log.debug("REST request to get all HrManagements");
        return hrManagementService.findAll();
    }

    /**
     * {@code GET  /hr-managements/:id} : get the "id" hrManagement.
     *
     * @param id the id of the hrManagement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hrManagement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<HrManagement> getHrManagement(@PathVariable("id") Integer id) {
        log.debug("REST request to get HrManagement : {}", id);
        Optional<HrManagement> hrManagement = hrManagementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hrManagement);
    }

    /**
     * {@code DELETE  /hr-managements/:id} : delete the "id" hrManagement.
     *
     * @param id the id of the hrManagement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHrManagement(@PathVariable("id") Integer id) {
        log.debug("REST request to delete HrManagement : {}", id);
        hrManagementService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
