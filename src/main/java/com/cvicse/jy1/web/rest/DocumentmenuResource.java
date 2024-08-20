package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.Documentmenu;
import com.cvicse.jy1.repository.DocumentmenuRepository;
import com.cvicse.jy1.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.Documentmenu}.
 */
@RestController
@RequestMapping("/api/documentmenus")
@Transactional
public class DocumentmenuResource {

    private static final Logger log = LoggerFactory.getLogger(DocumentmenuResource.class);

    private static final String ENTITY_NAME = "documentmenu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DocumentmenuRepository documentmenuRepository;

    public DocumentmenuResource(DocumentmenuRepository documentmenuRepository) {
        this.documentmenuRepository = documentmenuRepository;
    }

    /**
     * {@code POST  /documentmenus} : Create a new documentmenu.
     *
     * @param documentmenu the documentmenu to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new documentmenu, or with status {@code 400 (Bad Request)} if the documentmenu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Documentmenu> createDocumentmenu(@Valid @RequestBody Documentmenu documentmenu) throws URISyntaxException {
        log.debug("REST request to save Documentmenu : {}", documentmenu);
        if (documentmenu.getId() != null) {
            throw new BadRequestAlertException("A new documentmenu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        documentmenu = documentmenuRepository.save(documentmenu);
        return ResponseEntity.created(new URI("/api/documentmenus/" + documentmenu.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, documentmenu.getId().toString()))
            .body(documentmenu);
    }

    /**
     * {@code PUT  /documentmenus/:id} : Updates an existing documentmenu.
     *
     * @param id the id of the documentmenu to save.
     * @param documentmenu the documentmenu to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentmenu,
     * or with status {@code 400 (Bad Request)} if the documentmenu is not valid,
     * or with status {@code 500 (Internal Server Error)} if the documentmenu couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Documentmenu> updateDocumentmenu(
        @PathVariable(value = "id", required = false) final Integer id,
        @Valid @RequestBody Documentmenu documentmenu
    ) throws URISyntaxException {
        log.debug("REST request to update Documentmenu : {}, {}", id, documentmenu);
        if (documentmenu.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, documentmenu.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!documentmenuRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        documentmenu = documentmenuRepository.save(documentmenu);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, documentmenu.getId().toString()))
            .body(documentmenu);
    }

    /**
     * {@code PATCH  /documentmenus/:id} : Partial updates given fields of an existing documentmenu, field will ignore if it is null
     *
     * @param id the id of the documentmenu to save.
     * @param documentmenu the documentmenu to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentmenu,
     * or with status {@code 400 (Bad Request)} if the documentmenu is not valid,
     * or with status {@code 404 (Not Found)} if the documentmenu is not found,
     * or with status {@code 500 (Internal Server Error)} if the documentmenu couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Documentmenu> partialUpdateDocumentmenu(
        @PathVariable(value = "id", required = false) final Integer id,
        @NotNull @RequestBody Documentmenu documentmenu
    ) throws URISyntaxException {
        log.debug("REST request to partial update Documentmenu partially : {}, {}", id, documentmenu);
        if (documentmenu.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, documentmenu.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!documentmenuRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Documentmenu> result = documentmenuRepository
            .findById(documentmenu.getId())
            .map(existingDocumentmenu -> {
                if (documentmenu.getMenuid() != null) {
                    existingDocumentmenu.setMenuid(documentmenu.getMenuid());
                }
                if (documentmenu.getBelongtype() != null) {
                    existingDocumentmenu.setBelongtype(documentmenu.getBelongtype());
                }
                if (documentmenu.getMenuname() != null) {
                    existingDocumentmenu.setMenuname(documentmenu.getMenuname());
                }
                if (documentmenu.getParentmenuid() != null) {
                    existingDocumentmenu.setParentmenuid(documentmenu.getParentmenuid());
                }
                if (documentmenu.getCreatetime() != null) {
                    existingDocumentmenu.setCreatetime(documentmenu.getCreatetime());
                }
                if (documentmenu.getCreatorid() != null) {
                    existingDocumentmenu.setCreatorid(documentmenu.getCreatorid());
                }
                if (documentmenu.getCreatorname() != null) {
                    existingDocumentmenu.setCreatorname(documentmenu.getCreatorname());
                }
                if (documentmenu.getType() != null) {
                    existingDocumentmenu.setType(documentmenu.getType());
                }
                if (documentmenu.getFilenum() != null) {
                    existingDocumentmenu.setFilenum(documentmenu.getFilenum());
                }
                if (documentmenu.getDepartmentid() != null) {
                    existingDocumentmenu.setDepartmentid(documentmenu.getDepartmentid());
                }
                if (documentmenu.getDepartmentname() != null) {
                    existingDocumentmenu.setDepartmentname(documentmenu.getDepartmentname());
                }
                if (documentmenu.getSpare1() != null) {
                    existingDocumentmenu.setSpare1(documentmenu.getSpare1());
                }
                if (documentmenu.getSpare2() != null) {
                    existingDocumentmenu.setSpare2(documentmenu.getSpare2());
                }
                if (documentmenu.getSpare3() != null) {
                    existingDocumentmenu.setSpare3(documentmenu.getSpare3());
                }

                return existingDocumentmenu;
            })
            .map(documentmenuRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, documentmenu.getId().toString())
        );
    }

    /**
     * {@code GET  /documentmenus} : get all the documentmenus.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of documentmenus in body.
     */
    @GetMapping("")
    public List<Documentmenu> getAllDocumentmenus() {
        log.debug("REST request to get all Documentmenus");
        return documentmenuRepository.findAll();
    }

    /**
     * {@code GET  /documentmenus/:id} : get the "id" documentmenu.
     *
     * @param id the id of the documentmenu to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the documentmenu, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Documentmenu> getDocumentmenu(@PathVariable("id") Integer id) {
        log.debug("REST request to get Documentmenu : {}", id);
        Optional<Documentmenu> documentmenu = documentmenuRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(documentmenu);
    }

    /**
     * {@code DELETE  /documentmenus/:id} : delete the "id" documentmenu.
     *
     * @param id the id of the documentmenu to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocumentmenu(@PathVariable("id") Integer id) {
        log.debug("REST request to delete Documentmenu : {}", id);
        documentmenuRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
