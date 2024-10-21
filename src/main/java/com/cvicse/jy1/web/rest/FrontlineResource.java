package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.Frontline;
import com.cvicse.jy1.repository.FrontlineRepository;
import com.cvicse.jy1.service.FrontlineService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.Frontline}.
 */
@RestController
@RequestMapping("/api/frontlines")
public class FrontlineResource {

    private static final Logger log = LoggerFactory.getLogger(FrontlineResource.class);

    private static final String ENTITY_NAME = "frontline";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FrontlineService frontlineService;

    private final FrontlineRepository frontlineRepository;

    public FrontlineResource(FrontlineService frontlineService, FrontlineRepository frontlineRepository) {
        this.frontlineService = frontlineService;
        this.frontlineRepository = frontlineRepository;
    }

    /**
     * {@code POST  /frontlines} : Create a new frontline.
     *
     * @param frontline the frontline to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new frontline, or with status {@code 400 (Bad Request)} if the frontline has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Frontline> createFrontline(@RequestBody Frontline frontline) throws URISyntaxException {
        log.debug("REST request to save Frontline : {}", frontline);
        if (frontline.getId() != null) {
            throw new BadRequestAlertException("A new frontline cannot already have an ID", ENTITY_NAME, "idexists");
        }
        frontline = frontlineService.save(frontline);
        return ResponseEntity.created(new URI("/api/frontlines/" + frontline.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, frontline.getId()))
            .body(frontline);
    }

    /**
     * {@code PUT  /frontlines/:id} : Updates an existing frontline.
     *
     * @param id the id of the frontline to save.
     * @param frontline the frontline to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated frontline,
     * or with status {@code 400 (Bad Request)} if the frontline is not valid,
     * or with status {@code 500 (Internal Server Error)} if the frontline couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Frontline> updateFrontline(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Frontline frontline
    ) throws URISyntaxException {
        log.debug("REST request to update Frontline : {}, {}", id, frontline);
        if (frontline.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, frontline.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!frontlineRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        frontline = frontlineService.update(frontline);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, frontline.getId()))
            .body(frontline);
    }

    /**
     * {@code PATCH  /frontlines/:id} : Partial updates given fields of an existing frontline, field will ignore if it is null
     *
     * @param id the id of the frontline to save.
     * @param frontline the frontline to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated frontline,
     * or with status {@code 400 (Bad Request)} if the frontline is not valid,
     * or with status {@code 404 (Not Found)} if the frontline is not found,
     * or with status {@code 500 (Internal Server Error)} if the frontline couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Frontline> partialUpdateFrontline(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Frontline frontline
    ) throws URISyntaxException {
        log.debug("REST request to partial update Frontline partially : {}, {}", id, frontline);
        if (frontline.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, frontline.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!frontlineRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Frontline> result = frontlineService.partialUpdate(frontline);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, frontline.getId())
        );
    }

    /**
     * {@code GET  /frontlines} : get all the frontlines.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of frontlines in body.
     */
    @GetMapping("")
    public List<Frontline> getAllFrontlines() {
        log.debug("REST request to get all Frontlines");
        return frontlineService.findAll();
    }

    /**
     * {@code GET  /frontlines/:id} : get the "id" frontline.
     *
     * @param id the id of the frontline to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the frontline, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Frontline> getFrontline(@PathVariable("id") String id) {
        log.debug("REST request to get Frontline : {}", id);
        Optional<Frontline> frontline = frontlineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(frontline);
    }

    /**
     * {@code DELETE  /frontlines/:id} : delete the "id" frontline.
     *
     * @param id the id of the frontline to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFrontline(@PathVariable("id") String id) {
        log.debug("REST request to delete Frontline : {}", id);
        frontlineService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
