package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.DeliveryContent;
import com.cvicse.jy1.repository.DeliveryContentRepository;
import com.cvicse.jy1.service.DeliveryContentService;
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
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.cvicse.jy1.domain.DeliveryContent}.
 */
@RestController
@RequestMapping("/api/delivery-contents")
public class DeliveryContentResource {

    private static final Logger log = LoggerFactory.getLogger(DeliveryContentResource.class);

    private static final String ENTITY_NAME = "deliveryContent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeliveryContentService deliveryContentService;

    private final DeliveryContentRepository deliveryContentRepository;

    public DeliveryContentResource(DeliveryContentService deliveryContentService, DeliveryContentRepository deliveryContentRepository) {
        this.deliveryContentService = deliveryContentService;
        this.deliveryContentRepository = deliveryContentRepository;
    }

    /**
     * {@code POST  /delivery-contents} : Create a new deliveryContent.
     *
     * @param deliveryContent the deliveryContent to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deliveryContent, or with status {@code 400 (Bad Request)} if the deliveryContent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DeliveryContent> createDeliveryContent(@Valid @RequestBody DeliveryContent deliveryContent)
        throws URISyntaxException {
        log.debug("REST request to save DeliveryContent : {}", deliveryContent);
        if (deliveryContent.getId() != null) {
            throw new BadRequestAlertException("A new deliveryContent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        deliveryContent = deliveryContentService.save(deliveryContent);
        return ResponseEntity.created(new URI("/api/delivery-contents/" + deliveryContent.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, deliveryContent.getId().toString()))
            .body(deliveryContent);
    }

    /**
     * {@code PUT  /delivery-contents/:id} : Updates an existing deliveryContent.
     *
     * @param id the id of the deliveryContent to save.
     * @param deliveryContent the deliveryContent to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryContent,
     * or with status {@code 400 (Bad Request)} if the deliveryContent is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deliveryContent couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DeliveryContent> updateDeliveryContent(
        @PathVariable(value = "id", required = false) final Integer id,
        @Valid @RequestBody DeliveryContent deliveryContent
    ) throws URISyntaxException {
        log.debug("REST request to update DeliveryContent : {}, {}", id, deliveryContent);
        if (deliveryContent.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, deliveryContent.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deliveryContentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        deliveryContent = deliveryContentService.update(deliveryContent);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deliveryContent.getId().toString()))
            .body(deliveryContent);
    }

    /**
     * {@code PATCH  /delivery-contents/:id} : Partial updates given fields of an existing deliveryContent, field will ignore if it is null
     *
     * @param id the id of the deliveryContent to save.
     * @param deliveryContent the deliveryContent to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryContent,
     * or with status {@code 400 (Bad Request)} if the deliveryContent is not valid,
     * or with status {@code 404 (Not Found)} if the deliveryContent is not found,
     * or with status {@code 500 (Internal Server Error)} if the deliveryContent couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DeliveryContent> partialUpdateDeliveryContent(
        @PathVariable(value = "id", required = false) final Integer id,
        @NotNull @RequestBody DeliveryContent deliveryContent
    ) throws URISyntaxException {
        log.debug("REST request to partial update DeliveryContent partially : {}, {}", id, deliveryContent);
        if (deliveryContent.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, deliveryContent.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deliveryContentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DeliveryContent> result = deliveryContentService.partialUpdate(deliveryContent);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deliveryContent.getId().toString())
        );
    }

    /**
     * {@code GET  /delivery-contents} : get all the deliveryContents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deliveryContents in body.
     */
    @GetMapping("")
    public List<DeliveryContent> getAllDeliveryContents() {
        log.debug("REST request to get all DeliveryContents");
        return deliveryContentService.findAll();
    }

    /**
     * {@code GET  /delivery-contents/:id} : get the "id" deliveryContent.
     *
     * @param id the id of the deliveryContent to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deliveryContent, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryContent> getDeliveryContent(@PathVariable("id") Integer id) {
        log.debug("REST request to get DeliveryContent : {}", id);
        Optional<DeliveryContent> deliveryContent = deliveryContentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deliveryContent);
    }

    /**
     * {@code DELETE  /delivery-contents/:id} : delete the "id" deliveryContent.
     *
     * @param id the id of the deliveryContent to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeliveryContent(@PathVariable("id") Integer id) {
        log.debug("REST request to delete DeliveryContent : {}", id);
        deliveryContentService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
