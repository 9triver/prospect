package com.cvicse.web.rest;

import com.cvicse.domain.FundsmanagementWbs;
import com.cvicse.repository.FundsmanagementWbsRepository;
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
 * REST controller for managing {@link com.cvicse.domain.FundsmanagementWbs}.
 */
@RestController
@RequestMapping("/api/fundsmanagement-wbs")
@Transactional
public class FundsmanagementWbsResource {

    private final Logger log = LoggerFactory.getLogger(FundsmanagementWbsResource.class);

    private static final String ENTITY_NAME = "fundsmanagementWbs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FundsmanagementWbsRepository fundsmanagementWbsRepository;

    public FundsmanagementWbsResource(FundsmanagementWbsRepository fundsmanagementWbsRepository) {
        this.fundsmanagementWbsRepository = fundsmanagementWbsRepository;
    }

    /**
     * {@code POST  /fundsmanagement-wbs} : Create a new fundsmanagementWbs.
     *
     * @param fundsmanagementWbs the fundsmanagementWbs to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fundsmanagementWbs, or with status {@code 400 (Bad Request)} if the fundsmanagementWbs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FundsmanagementWbs> createFundsmanagementWbs(@RequestBody FundsmanagementWbs fundsmanagementWbs)
        throws URISyntaxException {
        log.debug("REST request to save FundsmanagementWbs : {}", fundsmanagementWbs);
        if (fundsmanagementWbs.getId() != null) {
            throw new BadRequestAlertException("A new fundsmanagementWbs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fundsmanagementWbs = fundsmanagementWbsRepository.save(fundsmanagementWbs);
        return ResponseEntity.created(new URI("/api/fundsmanagement-wbs/" + fundsmanagementWbs.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, fundsmanagementWbs.getId()))
            .body(fundsmanagementWbs);
    }

    /**
     * {@code PUT  /fundsmanagement-wbs/:id} : Updates an existing fundsmanagementWbs.
     *
     * @param id the id of the fundsmanagementWbs to save.
     * @param fundsmanagementWbs the fundsmanagementWbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fundsmanagementWbs,
     * or with status {@code 400 (Bad Request)} if the fundsmanagementWbs is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fundsmanagementWbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FundsmanagementWbs> updateFundsmanagementWbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody FundsmanagementWbs fundsmanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to update FundsmanagementWbs : {}, {}", id, fundsmanagementWbs);
        if (fundsmanagementWbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fundsmanagementWbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fundsmanagementWbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        fundsmanagementWbs = fundsmanagementWbsRepository.save(fundsmanagementWbs);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fundsmanagementWbs.getId()))
            .body(fundsmanagementWbs);
    }

    /**
     * {@code PATCH  /fundsmanagement-wbs/:id} : Partial updates given fields of an existing fundsmanagementWbs, field will ignore if it is null
     *
     * @param id the id of the fundsmanagementWbs to save.
     * @param fundsmanagementWbs the fundsmanagementWbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fundsmanagementWbs,
     * or with status {@code 400 (Bad Request)} if the fundsmanagementWbs is not valid,
     * or with status {@code 404 (Not Found)} if the fundsmanagementWbs is not found,
     * or with status {@code 500 (Internal Server Error)} if the fundsmanagementWbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FundsmanagementWbs> partialUpdateFundsmanagementWbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody FundsmanagementWbs fundsmanagementWbs
    ) throws URISyntaxException {
        log.debug("REST request to partial update FundsmanagementWbs partially : {}, {}", id, fundsmanagementWbs);
        if (fundsmanagementWbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fundsmanagementWbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fundsmanagementWbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FundsmanagementWbs> result = fundsmanagementWbsRepository
            .findById(fundsmanagementWbs.getId())
            .map(existingFundsmanagementWbs -> {
                if (fundsmanagementWbs.getWbsspare1() != null) {
                    existingFundsmanagementWbs.setWbsspare1(fundsmanagementWbs.getWbsspare1());
                }
                if (fundsmanagementWbs.getWbsspare2() != null) {
                    existingFundsmanagementWbs.setWbsspare2(fundsmanagementWbs.getWbsspare2());
                }
                if (fundsmanagementWbs.getWbsspare3() != null) {
                    existingFundsmanagementWbs.setWbsspare3(fundsmanagementWbs.getWbsspare3());
                }
                if (fundsmanagementWbs.getWbsspare4() != null) {
                    existingFundsmanagementWbs.setWbsspare4(fundsmanagementWbs.getWbsspare4());
                }
                if (fundsmanagementWbs.getWbsspare5() != null) {
                    existingFundsmanagementWbs.setWbsspare5(fundsmanagementWbs.getWbsspare5());
                }

                return existingFundsmanagementWbs;
            })
            .map(fundsmanagementWbsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fundsmanagementWbs.getId())
        );
    }

    /**
     * {@code GET  /fundsmanagement-wbs} : get all the fundsmanagementWbs.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fundsmanagementWbs in body.
     */
    @GetMapping("")
    public List<FundsmanagementWbs> getAllFundsmanagementWbs(@RequestParam(name = "filter", required = false) String filter) {
        if ("fundsmanagement-is-null".equals(filter)) {
            log.debug("REST request to get all FundsmanagementWbss where fundsmanagement is null");
            return StreamSupport.stream(fundsmanagementWbsRepository.findAll().spliterator(), false)
                .filter(fundsmanagementWbs -> fundsmanagementWbs.getFundsmanagement() == null)
                .toList();
        }
        log.debug("REST request to get all FundsmanagementWbs");
        return fundsmanagementWbsRepository.findAll();
    }

    /**
     * {@code GET  /fundsmanagement-wbs/:id} : get the "id" fundsmanagementWbs.
     *
     * @param id the id of the fundsmanagementWbs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fundsmanagementWbs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FundsmanagementWbs> getFundsmanagementWbs(@PathVariable("id") String id) {
        log.debug("REST request to get FundsmanagementWbs : {}", id);
        Optional<FundsmanagementWbs> fundsmanagementWbs = fundsmanagementWbsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fundsmanagementWbs);
    }

    /**
     * {@code DELETE  /fundsmanagement-wbs/:id} : delete the "id" fundsmanagementWbs.
     *
     * @param id the id of the fundsmanagementWbs to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFundsmanagementWbs(@PathVariable("id") String id) {
        log.debug("REST request to delete FundsmanagementWbs : {}", id);
        fundsmanagementWbsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
