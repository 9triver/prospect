package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.CommunicationFormDictionary;
import com.cvicse.jy1.repository.CommunicationFormDictionaryRepository;
import com.cvicse.jy1.service.CommunicationFormDictionaryService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.CommunicationFormDictionary}.
 */
@RestController
@RequestMapping("/api/communication-form-dictionaries")
public class CommunicationFormDictionaryResource {

    private static final Logger log = LoggerFactory.getLogger(CommunicationFormDictionaryResource.class);

    private static final String ENTITY_NAME = "communicationFormDictionary";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommunicationFormDictionaryService communicationFormDictionaryService;

    private final CommunicationFormDictionaryRepository communicationFormDictionaryRepository;

    public CommunicationFormDictionaryResource(
        CommunicationFormDictionaryService communicationFormDictionaryService,
        CommunicationFormDictionaryRepository communicationFormDictionaryRepository
    ) {
        this.communicationFormDictionaryService = communicationFormDictionaryService;
        this.communicationFormDictionaryRepository = communicationFormDictionaryRepository;
    }

    /**
     * {@code POST  /communication-form-dictionaries} : Create a new communicationFormDictionary.
     *
     * @param communicationFormDictionary the communicationFormDictionary to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new communicationFormDictionary, or with status {@code 400 (Bad Request)} if the communicationFormDictionary has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CommunicationFormDictionary> createCommunicationFormDictionary(
        @RequestBody CommunicationFormDictionary communicationFormDictionary
    ) throws URISyntaxException {
        log.debug("REST request to save CommunicationFormDictionary : {}", communicationFormDictionary);
        if (communicationFormDictionary.getId() != null) {
            throw new BadRequestAlertException("A new communicationFormDictionary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        communicationFormDictionary = communicationFormDictionaryService.save(communicationFormDictionary);
        return ResponseEntity.created(new URI("/api/communication-form-dictionaries/" + communicationFormDictionary.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, communicationFormDictionary.getId().toString())
            )
            .body(communicationFormDictionary);
    }

    /**
     * {@code PUT  /communication-form-dictionaries/:id} : Updates an existing communicationFormDictionary.
     *
     * @param id the id of the communicationFormDictionary to save.
     * @param communicationFormDictionary the communicationFormDictionary to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated communicationFormDictionary,
     * or with status {@code 400 (Bad Request)} if the communicationFormDictionary is not valid,
     * or with status {@code 500 (Internal Server Error)} if the communicationFormDictionary couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CommunicationFormDictionary> updateCommunicationFormDictionary(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody CommunicationFormDictionary communicationFormDictionary
    ) throws URISyntaxException {
        log.debug("REST request to update CommunicationFormDictionary : {}, {}", id, communicationFormDictionary);
        if (communicationFormDictionary.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, communicationFormDictionary.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!communicationFormDictionaryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        communicationFormDictionary = communicationFormDictionaryService.update(communicationFormDictionary);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, communicationFormDictionary.getId().toString()))
            .body(communicationFormDictionary);
    }

    /**
     * {@code PATCH  /communication-form-dictionaries/:id} : Partial updates given fields of an existing communicationFormDictionary, field will ignore if it is null
     *
     * @param id the id of the communicationFormDictionary to save.
     * @param communicationFormDictionary the communicationFormDictionary to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated communicationFormDictionary,
     * or with status {@code 400 (Bad Request)} if the communicationFormDictionary is not valid,
     * or with status {@code 404 (Not Found)} if the communicationFormDictionary is not found,
     * or with status {@code 500 (Internal Server Error)} if the communicationFormDictionary couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CommunicationFormDictionary> partialUpdateCommunicationFormDictionary(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody CommunicationFormDictionary communicationFormDictionary
    ) throws URISyntaxException {
        log.debug("REST request to partial update CommunicationFormDictionary partially : {}, {}", id, communicationFormDictionary);
        if (communicationFormDictionary.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, communicationFormDictionary.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!communicationFormDictionaryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CommunicationFormDictionary> result = communicationFormDictionaryService.partialUpdate(communicationFormDictionary);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, communicationFormDictionary.getId().toString())
        );
    }

    /**
     * {@code GET  /communication-form-dictionaries} : get all the communicationFormDictionaries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of communicationFormDictionaries in body.
     */
    @GetMapping("")
    public List<CommunicationFormDictionary> getAllCommunicationFormDictionaries() {
        log.debug("REST request to get all CommunicationFormDictionaries");
        return communicationFormDictionaryService.findAll();
    }

    /**
     * {@code GET  /communication-form-dictionaries/:id} : get the "id" communicationFormDictionary.
     *
     * @param id the id of the communicationFormDictionary to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the communicationFormDictionary, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CommunicationFormDictionary> getCommunicationFormDictionary(@PathVariable("id") Integer id) {
        log.debug("REST request to get CommunicationFormDictionary : {}", id);
        Optional<CommunicationFormDictionary> communicationFormDictionary = communicationFormDictionaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(communicationFormDictionary);
    }

    /**
     * {@code DELETE  /communication-form-dictionaries/:id} : delete the "id" communicationFormDictionary.
     *
     * @param id the id of the communicationFormDictionary to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommunicationFormDictionary(@PathVariable("id") Integer id) {
        log.debug("REST request to delete CommunicationFormDictionary : {}", id);
        communicationFormDictionaryService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
