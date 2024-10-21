package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.CommunicationDictionary;
import com.cvicse.jy1.repository.CommunicationDictionaryRepository;
import com.cvicse.jy1.service.CommunicationDictionaryService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.CommunicationDictionary}.
 */
@RestController
@RequestMapping("/api/communication-dictionaries")
public class CommunicationDictionaryResource {

    private static final Logger log = LoggerFactory.getLogger(CommunicationDictionaryResource.class);

    private static final String ENTITY_NAME = "communicationDictionary";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommunicationDictionaryService communicationDictionaryService;

    private final CommunicationDictionaryRepository communicationDictionaryRepository;

    public CommunicationDictionaryResource(
        CommunicationDictionaryService communicationDictionaryService,
        CommunicationDictionaryRepository communicationDictionaryRepository
    ) {
        this.communicationDictionaryService = communicationDictionaryService;
        this.communicationDictionaryRepository = communicationDictionaryRepository;
    }

    /**
     * {@code POST  /communication-dictionaries} : Create a new communicationDictionary.
     *
     * @param communicationDictionary the communicationDictionary to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new communicationDictionary, or with status {@code 400 (Bad Request)} if the communicationDictionary has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CommunicationDictionary> createCommunicationDictionary(
        @RequestBody CommunicationDictionary communicationDictionary
    ) throws URISyntaxException {
        log.debug("REST request to save CommunicationDictionary : {}", communicationDictionary);
        if (communicationDictionary.getId() != null) {
            throw new BadRequestAlertException("A new communicationDictionary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        communicationDictionary = communicationDictionaryService.save(communicationDictionary);
        return ResponseEntity.created(new URI("/api/communication-dictionaries/" + communicationDictionary.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, communicationDictionary.getId().toString()))
            .body(communicationDictionary);
    }

    /**
     * {@code PUT  /communication-dictionaries/:id} : Updates an existing communicationDictionary.
     *
     * @param id the id of the communicationDictionary to save.
     * @param communicationDictionary the communicationDictionary to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated communicationDictionary,
     * or with status {@code 400 (Bad Request)} if the communicationDictionary is not valid,
     * or with status {@code 500 (Internal Server Error)} if the communicationDictionary couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CommunicationDictionary> updateCommunicationDictionary(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody CommunicationDictionary communicationDictionary
    ) throws URISyntaxException {
        log.debug("REST request to update CommunicationDictionary : {}, {}", id, communicationDictionary);
        if (communicationDictionary.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, communicationDictionary.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!communicationDictionaryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        communicationDictionary = communicationDictionaryService.update(communicationDictionary);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, communicationDictionary.getId().toString()))
            .body(communicationDictionary);
    }

    /**
     * {@code PATCH  /communication-dictionaries/:id} : Partial updates given fields of an existing communicationDictionary, field will ignore if it is null
     *
     * @param id the id of the communicationDictionary to save.
     * @param communicationDictionary the communicationDictionary to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated communicationDictionary,
     * or with status {@code 400 (Bad Request)} if the communicationDictionary is not valid,
     * or with status {@code 404 (Not Found)} if the communicationDictionary is not found,
     * or with status {@code 500 (Internal Server Error)} if the communicationDictionary couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CommunicationDictionary> partialUpdateCommunicationDictionary(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody CommunicationDictionary communicationDictionary
    ) throws URISyntaxException {
        log.debug("REST request to partial update CommunicationDictionary partially : {}, {}", id, communicationDictionary);
        if (communicationDictionary.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, communicationDictionary.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!communicationDictionaryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CommunicationDictionary> result = communicationDictionaryService.partialUpdate(communicationDictionary);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, communicationDictionary.getId().toString())
        );
    }

    /**
     * {@code GET  /communication-dictionaries} : get all the communicationDictionaries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of communicationDictionaries in body.
     */
    @GetMapping("")
    public List<CommunicationDictionary> getAllCommunicationDictionaries() {
        log.debug("REST request to get all CommunicationDictionaries");
        return communicationDictionaryService.findAll();
    }

    /**
     * {@code GET  /communication-dictionaries/:id} : get the "id" communicationDictionary.
     *
     * @param id the id of the communicationDictionary to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the communicationDictionary, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CommunicationDictionary> getCommunicationDictionary(@PathVariable("id") Integer id) {
        log.debug("REST request to get CommunicationDictionary : {}", id);
        Optional<CommunicationDictionary> communicationDictionary = communicationDictionaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(communicationDictionary);
    }

    /**
     * {@code DELETE  /communication-dictionaries/:id} : delete the "id" communicationDictionary.
     *
     * @param id the id of the communicationDictionary to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommunicationDictionary(@PathVariable("id") Integer id) {
        log.debug("REST request to delete CommunicationDictionary : {}", id);
        communicationDictionaryService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
