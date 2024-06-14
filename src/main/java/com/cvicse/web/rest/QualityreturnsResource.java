package com.cvicse.web.rest;

import com.cvicse.domain.Qualityreturns;
import com.cvicse.repository.QualityreturnsRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Qualityreturns}.
 */
@RestController
@RequestMapping("/api/qualityreturns")
@Transactional
public class QualityreturnsResource {

    private final Logger log = LoggerFactory.getLogger(QualityreturnsResource.class);

    private static final String ENTITY_NAME = "qualityreturns";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QualityreturnsRepository qualityreturnsRepository;

    public QualityreturnsResource(QualityreturnsRepository qualityreturnsRepository) {
        this.qualityreturnsRepository = qualityreturnsRepository;
    }

    /**
     * {@code POST  /qualityreturns} : Create a new qualityreturns.
     *
     * @param qualityreturns the qualityreturns to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qualityreturns, or with status {@code 400 (Bad Request)} if the qualityreturns has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Qualityreturns> createQualityreturns(@Valid @RequestBody Qualityreturns qualityreturns)
        throws URISyntaxException {
        log.debug("REST request to save Qualityreturns : {}", qualityreturns);
        if (qualityreturns.getId() != null) {
            throw new BadRequestAlertException("A new qualityreturns cannot already have an ID", ENTITY_NAME, "idexists");
        }
        qualityreturns = qualityreturnsRepository.save(qualityreturns);
        return ResponseEntity.created(new URI("/api/qualityreturns/" + qualityreturns.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, qualityreturns.getId().toString()))
            .body(qualityreturns);
    }

    /**
     * {@code PUT  /qualityreturns/:id} : Updates an existing qualityreturns.
     *
     * @param id the id of the qualityreturns to save.
     * @param qualityreturns the qualityreturns to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualityreturns,
     * or with status {@code 400 (Bad Request)} if the qualityreturns is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qualityreturns couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Qualityreturns> updateQualityreturns(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Qualityreturns qualityreturns
    ) throws URISyntaxException {
        log.debug("REST request to update Qualityreturns : {}, {}", id, qualityreturns);
        if (qualityreturns.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualityreturns.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualityreturnsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        qualityreturns = qualityreturnsRepository.save(qualityreturns);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualityreturns.getId().toString()))
            .body(qualityreturns);
    }

    /**
     * {@code PATCH  /qualityreturns/:id} : Partial updates given fields of an existing qualityreturns, field will ignore if it is null
     *
     * @param id the id of the qualityreturns to save.
     * @param qualityreturns the qualityreturns to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualityreturns,
     * or with status {@code 400 (Bad Request)} if the qualityreturns is not valid,
     * or with status {@code 404 (Not Found)} if the qualityreturns is not found,
     * or with status {@code 500 (Internal Server Error)} if the qualityreturns couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Qualityreturns> partialUpdateQualityreturns(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Qualityreturns qualityreturns
    ) throws URISyntaxException {
        log.debug("REST request to partial update Qualityreturns partially : {}, {}", id, qualityreturns);
        if (qualityreturns.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualityreturns.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualityreturnsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Qualityreturns> result = qualityreturnsRepository
            .findById(qualityreturns.getId())
            .map(existingQualityreturns -> {
                if (qualityreturns.getQualityreturnsid() != null) {
                    existingQualityreturns.setQualityreturnsid(qualityreturns.getQualityreturnsid());
                }
                if (qualityreturns.getQualityreturnsname() != null) {
                    existingQualityreturns.setQualityreturnsname(qualityreturns.getQualityreturnsname());
                }
                if (qualityreturns.getStarttime() != null) {
                    existingQualityreturns.setStarttime(qualityreturns.getStarttime());
                }
                if (qualityreturns.getEndtime() != null) {
                    existingQualityreturns.setEndtime(qualityreturns.getEndtime());
                }
                if (qualityreturns.getQualitytype() != null) {
                    existingQualityreturns.setQualitytype(qualityreturns.getQualitytype());
                }
                if (qualityreturns.getReturnstime() != null) {
                    existingQualityreturns.setReturnstime(qualityreturns.getReturnstime());
                }
                if (qualityreturns.getReturnsstatus() != null) {
                    existingQualityreturns.setReturnsstatus(qualityreturns.getReturnsstatus());
                }

                return existingQualityreturns;
            })
            .map(qualityreturnsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualityreturns.getId().toString())
        );
    }

    /**
     * {@code GET  /qualityreturns} : get all the qualityreturns.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qualityreturns in body.
     */
    @GetMapping("")
    public List<Qualityreturns> getAllQualityreturns(@RequestParam(name = "filter", required = false) String filter) {
        if ("qualityobjectives-is-null".equals(filter)) {
            log.debug("REST request to get all Qualityreturnss where qualityobjectives is null");
            return StreamSupport.stream(qualityreturnsRepository.findAll().spliterator(), false)
                .filter(qualityreturns -> qualityreturns.getQualityobjectives() == null)
                .toList();
        }
        log.debug("REST request to get all Qualityreturns");
        return qualityreturnsRepository.findAll();
    }

    /**
     * {@code GET  /qualityreturns/:id} : get the "id" qualityreturns.
     *
     * @param id the id of the qualityreturns to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qualityreturns, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Qualityreturns> getQualityreturns(@PathVariable("id") Long id) {
        log.debug("REST request to get Qualityreturns : {}", id);
        Optional<Qualityreturns> qualityreturns = qualityreturnsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(qualityreturns);
    }

    /**
     * {@code DELETE  /qualityreturns/:id} : delete the "id" qualityreturns.
     *
     * @param id the id of the qualityreturns to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQualityreturns(@PathVariable("id") Long id) {
        log.debug("REST request to delete Qualityreturns : {}", id);
        qualityreturnsRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
