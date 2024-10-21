package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.CommunicationRecord;
import com.cvicse.jy1.repository.CommunicationRecordRepository;
import com.cvicse.jy1.service.CommunicationRecordService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.CommunicationRecord}.
 */
@RestController
@RequestMapping("/api/communication-records")
public class CommunicationRecordResource {

    private static final Logger log = LoggerFactory.getLogger(CommunicationRecordResource.class);

    private static final String ENTITY_NAME = "communicationRecord";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommunicationRecordService communicationRecordService;

    private final CommunicationRecordRepository communicationRecordRepository;

    public CommunicationRecordResource(
        CommunicationRecordService communicationRecordService,
        CommunicationRecordRepository communicationRecordRepository
    ) {
        this.communicationRecordService = communicationRecordService;
        this.communicationRecordRepository = communicationRecordRepository;
    }

    /**
     * {@code POST  /communication-records} : Create a new communicationRecord.
     *
     * @param communicationRecord the communicationRecord to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new communicationRecord, or with status {@code 400 (Bad Request)} if the communicationRecord has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CommunicationRecord> createCommunicationRecord(@RequestBody CommunicationRecord communicationRecord)
        throws URISyntaxException {
        log.debug("REST request to save CommunicationRecord : {}", communicationRecord);
        if (communicationRecord.getId() != null) {
            throw new BadRequestAlertException("A new communicationRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        communicationRecord = communicationRecordService.save(communicationRecord);
        return ResponseEntity.created(new URI("/api/communication-records/" + communicationRecord.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, communicationRecord.getId().toString()))
            .body(communicationRecord);
    }

    /**
     * {@code PUT  /communication-records/:id} : Updates an existing communicationRecord.
     *
     * @param id the id of the communicationRecord to save.
     * @param communicationRecord the communicationRecord to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated communicationRecord,
     * or with status {@code 400 (Bad Request)} if the communicationRecord is not valid,
     * or with status {@code 500 (Internal Server Error)} if the communicationRecord couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CommunicationRecord> updateCommunicationRecord(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody CommunicationRecord communicationRecord
    ) throws URISyntaxException {
        log.debug("REST request to update CommunicationRecord : {}, {}", id, communicationRecord);
        if (communicationRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, communicationRecord.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!communicationRecordRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        communicationRecord = communicationRecordService.update(communicationRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, communicationRecord.getId().toString()))
            .body(communicationRecord);
    }

    /**
     * {@code PATCH  /communication-records/:id} : Partial updates given fields of an existing communicationRecord, field will ignore if it is null
     *
     * @param id the id of the communicationRecord to save.
     * @param communicationRecord the communicationRecord to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated communicationRecord,
     * or with status {@code 400 (Bad Request)} if the communicationRecord is not valid,
     * or with status {@code 404 (Not Found)} if the communicationRecord is not found,
     * or with status {@code 500 (Internal Server Error)} if the communicationRecord couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CommunicationRecord> partialUpdateCommunicationRecord(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody CommunicationRecord communicationRecord
    ) throws URISyntaxException {
        log.debug("REST request to partial update CommunicationRecord partially : {}, {}", id, communicationRecord);
        if (communicationRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, communicationRecord.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!communicationRecordRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CommunicationRecord> result = communicationRecordService.partialUpdate(communicationRecord);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, communicationRecord.getId().toString())
        );
    }

    /**
     * {@code GET  /communication-records} : get all the communicationRecords.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of communicationRecords in body.
     */
    @GetMapping("")
    public List<CommunicationRecord> getAllCommunicationRecords() {
        log.debug("REST request to get all CommunicationRecords");
        return communicationRecordService.findAll();
    }

    /**
     * {@code GET  /communication-records/:id} : get the "id" communicationRecord.
     *
     * @param id the id of the communicationRecord to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the communicationRecord, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CommunicationRecord> getCommunicationRecord(@PathVariable("id") Integer id) {
        log.debug("REST request to get CommunicationRecord : {}", id);
        Optional<CommunicationRecord> communicationRecord = communicationRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(communicationRecord);
    }

    /**
     * {@code DELETE  /communication-records/:id} : delete the "id" communicationRecord.
     *
     * @param id the id of the communicationRecord to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommunicationRecord(@PathVariable("id") Integer id) {
        log.debug("REST request to delete CommunicationRecord : {}", id);
        communicationRecordService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
