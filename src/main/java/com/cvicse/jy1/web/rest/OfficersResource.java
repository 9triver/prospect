package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.Officers;
import com.cvicse.jy1.repository.OfficersRepository;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.cvicse.jy1.domain.Officers}.
 */
@RestController
@RequestMapping("/api/officers")
@Transactional
public class OfficersResource {

    private static final Logger log = LoggerFactory.getLogger(OfficersResource.class);

    private static final String ENTITY_NAME = "officers";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OfficersRepository officersRepository;

    public OfficersResource(OfficersRepository officersRepository) {
        this.officersRepository = officersRepository;
    }

    /**
     * {@code POST  /officers} : Create a new officers.
     *
     * @param officers the officers to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new officers, or with status {@code 400 (Bad Request)} if the officers has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Officers> createOfficers(@RequestBody Officers officers) throws URISyntaxException {
        log.debug("REST request to save Officers : {}", officers);
        if (officers.getId() != null) {
            throw new BadRequestAlertException("A new officers cannot already have an ID", ENTITY_NAME, "idexists");
        }
        officers = officersRepository.save(officers);
        return ResponseEntity.created(new URI("/api/officers/" + officers.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, officers.getId()))
            .body(officers);
    }

    /**
     * {@code PUT  /officers/:id} : Updates an existing officers.
     *
     * @param id the id of the officers to save.
     * @param officers the officers to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated officers,
     * or with status {@code 400 (Bad Request)} if the officers is not valid,
     * or with status {@code 500 (Internal Server Error)} if the officers couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Officers> updateOfficers(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Officers officers
    ) throws URISyntaxException {
        log.debug("REST request to update Officers : {}, {}", id, officers);
        if (officers.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, officers.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!officersRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        officers = officersRepository.save(officers);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, officers.getId()))
            .body(officers);
    }

    /**
     * {@code PATCH  /officers/:id} : Partial updates given fields of an existing officers, field will ignore if it is null
     *
     * @param id the id of the officers to save.
     * @param officers the officers to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated officers,
     * or with status {@code 400 (Bad Request)} if the officers is not valid,
     * or with status {@code 404 (Not Found)} if the officers is not found,
     * or with status {@code 500 (Internal Server Error)} if the officers couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Officers> partialUpdateOfficers(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Officers officers
    ) throws URISyntaxException {
        log.debug("REST request to partial update Officers partially : {}, {}", id, officers);
        if (officers.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, officers.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!officersRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Officers> result = officersRepository
            .findById(officers.getId())
            .map(existingOfficers -> {
                if (officers.getOfficersname() != null) {
                    existingOfficers.setOfficersname(officers.getOfficersname());
                }
                if (officers.getPassword() != null) {
                    existingOfficers.setPassword(officers.getPassword());
                }
                if (officers.getEmail() != null) {
                    existingOfficers.setEmail(officers.getEmail());
                }
                if (officers.getPhone() != null) {
                    existingOfficers.setPhone(officers.getPhone());
                }

                return existingOfficers;
            })
            .map(officersRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, officers.getId())
        );
    }

    /**
     * {@code GET  /officers} : get all the officers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of officers in body.
     */
    @GetMapping("")
    public List<Officers> getAllOfficers() {
        log.debug("REST request to get all Officers");
        return officersRepository.findAll();
    }

    /**
     * {@code GET  /officers/:id} : get the "id" officers.
     *
     * @param id the id of the officers to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the officers, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Officers> getOfficers(@PathVariable("id") String id) {
        log.debug("REST request to get Officers : {}", id);
        Optional<Officers> officers = officersRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(officers);
    }

    /**
     * {@code DELETE  /officers/:id} : delete the "id" officers.
     *
     * @param id the id of the officers to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOfficers(@PathVariable("id") String id) {
        log.debug("REST request to delete Officers : {}", id);
        officersRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
