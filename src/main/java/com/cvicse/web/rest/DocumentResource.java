package com.cvicse.web.rest;

import com.cvicse.domain.Document;
import com.cvicse.repository.DocumentRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Document}.
 */
@RestController
@RequestMapping("/api/documents")
@Transactional
public class DocumentResource {

    private final Logger log = LoggerFactory.getLogger(DocumentResource.class);

    private static final String ENTITY_NAME = "document";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DocumentRepository documentRepository;

    public DocumentResource(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    /**
     * {@code POST  /documents} : Create a new document.
     *
     * @param document the document to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new document, or with status {@code 400 (Bad Request)} if the document has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Document> createDocument(@Valid @RequestBody Document document) throws URISyntaxException {
        log.debug("REST request to save Document : {}", document);
        if (document.getId() != null) {
            throw new BadRequestAlertException("A new document cannot already have an ID", ENTITY_NAME, "idexists");
        }
        document = documentRepository.save(document);
        return ResponseEntity.created(new URI("/api/documents/" + document.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, document.getId().toString()))
            .body(document);
    }

    /**
     * {@code PUT  /documents/:id} : Updates an existing document.
     *
     * @param id the id of the document to save.
     * @param document the document to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated document,
     * or with status {@code 400 (Bad Request)} if the document is not valid,
     * or with status {@code 500 (Internal Server Error)} if the document couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Document> updateDocument(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Document document
    ) throws URISyntaxException {
        log.debug("REST request to update Document : {}, {}", id, document);
        if (document.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, document.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!documentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        document = documentRepository.save(document);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, document.getId().toString()))
            .body(document);
    }

    /**
     * {@code PATCH  /documents/:id} : Partial updates given fields of an existing document, field will ignore if it is null
     *
     * @param id the id of the document to save.
     * @param document the document to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated document,
     * or with status {@code 400 (Bad Request)} if the document is not valid,
     * or with status {@code 404 (Not Found)} if the document is not found,
     * or with status {@code 500 (Internal Server Error)} if the document couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Document> partialUpdateDocument(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Document document
    ) throws URISyntaxException {
        log.debug("REST request to partial update Document partially : {}, {}", id, document);
        if (document.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, document.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!documentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Document> result = documentRepository
            .findById(document.getId())
            .map(existingDocument -> {
                if (document.getDocumentid() != null) {
                    existingDocument.setDocumentid(document.getDocumentid());
                }
                if (document.getDocumentname() != null) {
                    existingDocument.setDocumentname(document.getDocumentname());
                }
                if (document.getDocumenttype() != null) {
                    existingDocument.setDocumenttype(document.getDocumenttype());
                }
                if (document.getDocumentsize() != null) {
                    existingDocument.setDocumentsize(document.getDocumentsize());
                }
                if (document.getSecretlevel() != null) {
                    existingDocument.setSecretlevel(document.getSecretlevel());
                }
                if (document.getCreatetime() != null) {
                    existingDocument.setCreatetime(document.getCreatetime());
                }
                if (document.getCreatorname() != null) {
                    existingDocument.setCreatorname(document.getCreatorname());
                }

                return existingDocument;
            })
            .map(documentRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, document.getId().toString())
        );
    }

    /**
     * {@code GET  /documents} : get all the documents.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of documents in body.
     */
    @GetMapping("")
    public List<Document> getAllDocuments(@RequestParam(name = "filter", required = false) String filter) {
        if ("project-is-null".equals(filter)) {
            log.debug("REST request to get all Documents where project is null");
            return StreamSupport.stream(documentRepository.findAll().spliterator(), false)
                .filter(document -> document.getProject() == null)
                .toList();
        }

        if ("cycleplan-is-null".equals(filter)) {
            log.debug("REST request to get all Documents where cycleplan is null");
            return StreamSupport.stream(documentRepository.findAll().spliterator(), false)
                .filter(document -> document.getCycleplan() == null)
                .toList();
        }

        if ("annualplan-is-null".equals(filter)) {
            log.debug("REST request to get all Documents where annualplan is null");
            return StreamSupport.stream(documentRepository.findAll().spliterator(), false)
                .filter(document -> document.getAnnualplan() == null)
                .toList();
        }

        if ("monthplan-is-null".equals(filter)) {
            log.debug("REST request to get all Documents where monthplan is null");
            return StreamSupport.stream(documentRepository.findAll().spliterator(), false)
                .filter(document -> document.getMonthplan() == null)
                .toList();
        }

        if ("fundsmanagement-is-null".equals(filter)) {
            log.debug("REST request to get all Documents where fundsmanagement is null");
            return StreamSupport.stream(documentRepository.findAll().spliterator(), false)
                .filter(document -> document.getFundsmanagement() == null)
                .toList();
        }
        log.debug("REST request to get all Documents");
        return documentRepository.findAll();
    }

    /**
     * {@code GET  /documents/:id} : get the "id" document.
     *
     * @param id the id of the document to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the document, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocument(@PathVariable("id") Long id) {
        log.debug("REST request to get Document : {}", id);
        Optional<Document> document = documentRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(document);
    }

    /**
     * {@code DELETE  /documents/:id} : delete the "id" document.
     *
     * @param id the id of the document to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable("id") Long id) {
        log.debug("REST request to delete Document : {}", id);
        documentRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
