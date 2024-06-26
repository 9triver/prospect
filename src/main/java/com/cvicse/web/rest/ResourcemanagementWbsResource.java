package com.cvicse.web.rest;

import com.cvicse.domain.ResourcemanagementWbs;
import com.cvicse.repository.ResourcemanagementWbsRepository;
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
 * REST controller for managing {@link com.cvicse.domain.ResourcemanagementWbs}.
 */
@RestController
@RequestMapping("/api/resourcemanagement-wbs")
@Transactional
public class ResourcemanagementWbsResource {

    private final Logger log = LoggerFactory.getLogger(ResourcemanagementWbsResource.class);

    private static final String ENTITY_NAME = "resourcemanagementWbs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResourcemanagementWbsRepository resourcemanagementWbsRepository;

    public ResourcemanagementWbsResource(ResourcemanagementWbsRepository resourcemanagementWbsRepository) {
        this.resourcemanagementWbsRepository = resourcemanagementWbsRepository;
    }

    /**
     * {@code POST  /resourcemanagement-wbs} : Create a new resourcemanagementWbs.
     *
     * @param resourcemanagementWbs the resourcemanagementWbs to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resourcemanagementWbs, or with status {@code 400 (Bad Request)} if the resourcemanagementWbs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ResourcemanagementWbs> createResourcemanagementWbs(@RequestBody ResourcemanagementWbs resourcemanagementWbs)
        throws URISyntaxException {
        log.debug("REST request to save ResourcemanagementWbs : {}", resourcemanagementWbs);
        if (resourcemanagementWbs.getId() != null) {
            throw new BadRequestAlertException("A new resourcemanagementWbs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        resourcemanagementWbs = resourcemanagementWbsRepository.save(resourcemanagementWbs);
        return ResponseEntity.created(new URI("/api/resourcemanagement-wbs/" + resourcemanagementWbs.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, resourcemanagementWbs.getId()))
            .body(resourcemanagementWbs);
    }

    /**
     * {@code PUT  /resourcemanagement-wbs/:id} : Updates an existing resourcemanagementWbs.
     *
     * @param id the id of the resourcemanagementWbs to save.
     * @param resourcemanagementWbs the resourcemanagementWbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resourcemanagementWbs,
     * or with status {@code 400 (Bad Request)} if the resourcemanagementWbs is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resourcemanagementWbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResourcemanagementWbs> updateResourcemanagementWbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ResourcemanagementWbs resourcemanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to update ResourcemanagementWbs : {}, {}", id, resourcemanagementWbs);
        if (resourcemanagementWbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resourcemanagementWbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resourcemanagementWbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        resourcemanagementWbs = resourcemanagementWbsRepository.save(resourcemanagementWbs);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resourcemanagementWbs.getId()))
            .body(resourcemanagementWbs);
    }

    /**
     * {@code PATCH  /resourcemanagement-wbs/:id} : Partial updates given fields of an existing resourcemanagementWbs, field will ignore if it is null
     *
     * @param id the id of the resourcemanagementWbs to save.
     * @param resourcemanagementWbs the resourcemanagementWbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resourcemanagementWbs,
     * or with status {@code 400 (Bad Request)} if the resourcemanagementWbs is not valid,
     * or with status {@code 404 (Not Found)} if the resourcemanagementWbs is not found,
     * or with status {@code 500 (Internal Server Error)} if the resourcemanagementWbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ResourcemanagementWbs> partialUpdateResourcemanagementWbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ResourcemanagementWbs resourcemanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to partial update ResourcemanagementWbs partially : {}, {}", id, resourcemanagementWbs);
        if (resourcemanagementWbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resourcemanagementWbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resourcemanagementWbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ResourcemanagementWbs> result = resourcemanagementWbsRepository
            .findById(resourcemanagementWbs.getId())
            .map(existingResourcemanagementWbs -> {
                if (resourcemanagementWbs.getWbsspare1() != null) {
                    existingResourcemanagementWbs.setWbsspare1(resourcemanagementWbs.getWbsspare1());
                }
                if (resourcemanagementWbs.getWbsspare2() != null) {
                    existingResourcemanagementWbs.setWbsspare2(resourcemanagementWbs.getWbsspare2());
                }
                if (resourcemanagementWbs.getWbsspare3() != null) {
                    existingResourcemanagementWbs.setWbsspare3(resourcemanagementWbs.getWbsspare3());
                }
                if (resourcemanagementWbs.getWbsspare4() != null) {
                    existingResourcemanagementWbs.setWbsspare4(resourcemanagementWbs.getWbsspare4());
                }
                if (resourcemanagementWbs.getWbsspare5() != null) {
                    existingResourcemanagementWbs.setWbsspare5(resourcemanagementWbs.getWbsspare5());
                }

                return existingResourcemanagementWbs;
            })
            .map(resourcemanagementWbsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resourcemanagementWbs.getId())
        );
    }

    /**
     * {@code GET  /resourcemanagement-wbs} : get all the resourcemanagementWbs.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resourcemanagementWbs in body.
     */
    @GetMapping("")
    public List<ResourcemanagementWbs> getAllResourcemanagementWbs(@RequestParam(name = "filter", required = false) String filter) {
        if ("resourcemanagement-is-null".equals(filter)) {
            log.debug("REST request to get all ResourcemanagementWbss where resourcemanagement is null");
            return StreamSupport.stream(resourcemanagementWbsRepository.findAll().spliterator(), false)
                .filter(resourcemanagementWbs -> resourcemanagementWbs.getResourcemanagement() == null)
                .toList();
        }
        log.debug("REST request to get all ResourcemanagementWbs");
        return resourcemanagementWbsRepository.findAll();
    }

    /**
     * {@code GET  /resourcemanagement-wbs/:id} : get the "id" resourcemanagementWbs.
     *
     * @param id the id of the resourcemanagementWbs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resourcemanagementWbs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResourcemanagementWbs> getResourcemanagementWbs(@PathVariable("id") String id) {
        log.debug("REST request to get ResourcemanagementWbs : {}", id);
        Optional<ResourcemanagementWbs> resourcemanagementWbs = resourcemanagementWbsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(resourcemanagementWbs);
    }

    /**
     * {@code DELETE  /resourcemanagement-wbs/:id} : delete the "id" resourcemanagementWbs.
     *
     * @param id the id of the resourcemanagementWbs to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResourcemanagementWbs(@PathVariable("id") String id) {
        log.debug("REST request to delete ResourcemanagementWbs : {}", id);
        resourcemanagementWbsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
