package com.cvicse.web.rest;

import com.cvicse.domain.Resourcemanagement;
import com.cvicse.repository.ResourcemanagementRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Resourcemanagement}.
 */
@RestController
@RequestMapping("/api/resourcemanagements")
@Transactional
public class ResourcemanagementResource {

    private final Logger log = LoggerFactory.getLogger(ResourcemanagementResource.class);

    private static final String ENTITY_NAME = "resourcemanagement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResourcemanagementRepository resourcemanagementRepository;

    public ResourcemanagementResource(ResourcemanagementRepository resourcemanagementRepository) {
        this.resourcemanagementRepository = resourcemanagementRepository;
    }

    /**
     * {@code POST  /resourcemanagements} : Create a new resourcemanagement.
     *
     * @param resourcemanagement the resourcemanagement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resourcemanagement, or with status {@code 400 (Bad Request)} if the resourcemanagement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Resourcemanagement> createResourcemanagement(@RequestBody Resourcemanagement resourcemanagement)
        throws URISyntaxException {
        log.debug("REST request to save Resourcemanagement : {}", resourcemanagement);
        if (resourcemanagement.getId() != null) {
            throw new BadRequestAlertException("A new resourcemanagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        resourcemanagement = resourcemanagementRepository.save(resourcemanagement);
        return ResponseEntity.created(new URI("/api/resourcemanagements/" + resourcemanagement.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, resourcemanagement.getId()))
            .body(resourcemanagement);
    }

    /**
     * {@code PUT  /resourcemanagements/:id} : Updates an existing resourcemanagement.
     *
     * @param id the id of the resourcemanagement to save.
     * @param resourcemanagement the resourcemanagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resourcemanagement,
     * or with status {@code 400 (Bad Request)} if the resourcemanagement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resourcemanagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Resourcemanagement> updateResourcemanagement(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Resourcemanagement resourcemanagement
    ) throws URISyntaxException {
        log.debug("REST request to update Resourcemanagement : {}, {}", id, resourcemanagement);
        if (resourcemanagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resourcemanagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resourcemanagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        resourcemanagement = resourcemanagementRepository.save(resourcemanagement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resourcemanagement.getId()))
            .body(resourcemanagement);
    }

    /**
     * {@code PATCH  /resourcemanagements/:id} : Partial updates given fields of an existing resourcemanagement, field will ignore if it is null
     *
     * @param id the id of the resourcemanagement to save.
     * @param resourcemanagement the resourcemanagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resourcemanagement,
     * or with status {@code 400 (Bad Request)} if the resourcemanagement is not valid,
     * or with status {@code 404 (Not Found)} if the resourcemanagement is not found,
     * or with status {@code 500 (Internal Server Error)} if the resourcemanagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Resourcemanagement> partialUpdateResourcemanagement(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Resourcemanagement resourcemanagement
    ) throws URISyntaxException {
        log.debug("REST request to partial update Resourcemanagement partially : {}, {}", id, resourcemanagement);
        if (resourcemanagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resourcemanagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resourcemanagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Resourcemanagement> result = resourcemanagementRepository
            .findById(resourcemanagement.getId())
            .map(existingResourcemanagement -> {
                if (resourcemanagement.getName() != null) {
                    existingResourcemanagement.setName(resourcemanagement.getName());
                }
                if (resourcemanagement.getDescription() != null) {
                    existingResourcemanagement.setDescription(resourcemanagement.getDescription());
                }
                if (resourcemanagement.getStarttime() != null) {
                    existingResourcemanagement.setStarttime(resourcemanagement.getStarttime());
                }
                if (resourcemanagement.getEndtime() != null) {
                    existingResourcemanagement.setEndtime(resourcemanagement.getEndtime());
                }

                return existingResourcemanagement;
            })
            .map(resourcemanagementRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resourcemanagement.getId())
        );
    }

    /**
     * {@code GET  /resourcemanagements} : get all the resourcemanagements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resourcemanagements in body.
     */
    @GetMapping("")
    public List<Resourcemanagement> getAllResourcemanagements() {
        log.debug("REST request to get all Resourcemanagements");
        return resourcemanagementRepository.findAll();
    }

    /**
     * {@code GET  /resourcemanagements/:id} : get the "id" resourcemanagement.
     *
     * @param id the id of the resourcemanagement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resourcemanagement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Resourcemanagement> getResourcemanagement(@PathVariable("id") String id) {
        log.debug("REST request to get Resourcemanagement : {}", id);
        Optional<Resourcemanagement> resourcemanagement = resourcemanagementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(resourcemanagement);
    }

    /**
     * {@code DELETE  /resourcemanagements/:id} : delete the "id" resourcemanagement.
     *
     * @param id the id of the resourcemanagement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResourcemanagement(@PathVariable("id") String id) {
        log.debug("REST request to delete Resourcemanagement : {}", id);
        resourcemanagementRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
