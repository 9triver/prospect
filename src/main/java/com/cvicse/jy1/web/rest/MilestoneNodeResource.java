package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.MilestoneNode;
import com.cvicse.jy1.repository.MilestoneNodeRepository;
import com.cvicse.jy1.service.MilestoneNodeService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.MilestoneNode}.
 */
@RestController
@RequestMapping("/api/milestone-nodes")
public class MilestoneNodeResource {

    private static final Logger log = LoggerFactory.getLogger(MilestoneNodeResource.class);

    private static final String ENTITY_NAME = "milestoneNode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MilestoneNodeService milestoneNodeService;

    private final MilestoneNodeRepository milestoneNodeRepository;

    public MilestoneNodeResource(MilestoneNodeService milestoneNodeService, MilestoneNodeRepository milestoneNodeRepository) {
        this.milestoneNodeService = milestoneNodeService;
        this.milestoneNodeRepository = milestoneNodeRepository;
    }

    /**
     * {@code POST  /milestone-nodes} : Create a new milestoneNode.
     *
     * @param milestoneNode the milestoneNode to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new milestoneNode, or with status {@code 400 (Bad Request)} if the milestoneNode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<MilestoneNode> createMilestoneNode(@Valid @RequestBody MilestoneNode milestoneNode) throws URISyntaxException {
        log.debug("REST request to save MilestoneNode : {}", milestoneNode);
        if (milestoneNode.getId() != null) {
            throw new BadRequestAlertException("A new milestoneNode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        milestoneNode = milestoneNodeService.save(milestoneNode);
        return ResponseEntity.created(new URI("/api/milestone-nodes/" + milestoneNode.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, milestoneNode.getId().toString()))
            .body(milestoneNode);
    }

    /**
     * {@code PUT  /milestone-nodes/:id} : Updates an existing milestoneNode.
     *
     * @param id the id of the milestoneNode to save.
     * @param milestoneNode the milestoneNode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated milestoneNode,
     * or with status {@code 400 (Bad Request)} if the milestoneNode is not valid,
     * or with status {@code 500 (Internal Server Error)} if the milestoneNode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MilestoneNode> updateMilestoneNode(
        @PathVariable(value = "id", required = false) final Integer id,
        @Valid @RequestBody MilestoneNode milestoneNode
    ) throws URISyntaxException {
        log.debug("REST request to update MilestoneNode : {}, {}", id, milestoneNode);
        if (milestoneNode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, milestoneNode.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!milestoneNodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        milestoneNode = milestoneNodeService.update(milestoneNode);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, milestoneNode.getId().toString()))
            .body(milestoneNode);
    }

    /**
     * {@code PATCH  /milestone-nodes/:id} : Partial updates given fields of an existing milestoneNode, field will ignore if it is null
     *
     * @param id the id of the milestoneNode to save.
     * @param milestoneNode the milestoneNode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated milestoneNode,
     * or with status {@code 400 (Bad Request)} if the milestoneNode is not valid,
     * or with status {@code 404 (Not Found)} if the milestoneNode is not found,
     * or with status {@code 500 (Internal Server Error)} if the milestoneNode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MilestoneNode> partialUpdateMilestoneNode(
        @PathVariable(value = "id", required = false) final Integer id,
        @NotNull @RequestBody MilestoneNode milestoneNode
    ) throws URISyntaxException {
        log.debug("REST request to partial update MilestoneNode partially : {}, {}", id, milestoneNode);
        if (milestoneNode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, milestoneNode.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!milestoneNodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MilestoneNode> result = milestoneNodeService.partialUpdate(milestoneNode);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, milestoneNode.getId().toString())
        );
    }

    /**
     * {@code GET  /milestone-nodes} : get all the milestoneNodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of milestoneNodes in body.
     */
    @GetMapping("")
    public List<MilestoneNode> getAllMilestoneNodes() {
        log.debug("REST request to get all MilestoneNodes");
        return milestoneNodeService.findAll();
    }

    /**
     * {@code GET  /milestone-nodes/:id} : get the "id" milestoneNode.
     *
     * @param id the id of the milestoneNode to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the milestoneNode, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MilestoneNode> getMilestoneNode(@PathVariable("id") Integer id) {
        log.debug("REST request to get MilestoneNode : {}", id);
        Optional<MilestoneNode> milestoneNode = milestoneNodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(milestoneNode);
    }

    /**
     * {@code DELETE  /milestone-nodes/:id} : delete the "id" milestoneNode.
     *
     * @param id the id of the milestoneNode to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMilestoneNode(@PathVariable("id") Integer id) {
        log.debug("REST request to delete MilestoneNode : {}", id);
        milestoneNodeService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
