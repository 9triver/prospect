package com.cvicse.web.rest;

import com.cvicse.domain.ApprovalAgent;
import com.cvicse.repository.ApprovalAgentRepository;
import com.cvicse.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link com.cvicse.domain.ApprovalAgent}.
 */
@RestController
@RequestMapping("/api/approval-agents")
@Transactional
public class ApprovalAgentResource {

    private final Logger log = LoggerFactory.getLogger(ApprovalAgentResource.class);

    private static final String ENTITY_NAME = "approvalAgent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApprovalAgentRepository approvalAgentRepository;

    public ApprovalAgentResource(ApprovalAgentRepository approvalAgentRepository) {
        this.approvalAgentRepository = approvalAgentRepository;
    }

    /**
     * {@code POST  /approval-agents} : Create a new approvalAgent.
     *
     * @param approvalAgent the approvalAgent to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new approvalAgent, or with status {@code 400 (Bad Request)} if the approvalAgent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ApprovalAgent> createApprovalAgent(@RequestBody ApprovalAgent approvalAgent) throws URISyntaxException {
        log.debug("REST request to save ApprovalAgent : {}", approvalAgent);
        if (approvalAgent.getId() != null) {
            throw new BadRequestAlertException("A new approvalAgent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        approvalAgent = approvalAgentRepository.save(approvalAgent);
        return ResponseEntity.created(new URI("/api/approval-agents/" + approvalAgent.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, approvalAgent.getId()))
            .body(approvalAgent);
    }

    /**
     * {@code PUT  /approval-agents/:id} : Updates an existing approvalAgent.
     *
     * @param id the id of the approvalAgent to save.
     * @param approvalAgent the approvalAgent to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated approvalAgent,
     * or with status {@code 400 (Bad Request)} if the approvalAgent is not valid,
     * or with status {@code 500 (Internal Server Error)} if the approvalAgent couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApprovalAgent> updateApprovalAgent(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ApprovalAgent approvalAgent
    ) throws URISyntaxException {
        log.debug("REST request to update ApprovalAgent : {}, {}", id, approvalAgent);
        if (approvalAgent.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, approvalAgent.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!approvalAgentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        approvalAgent = approvalAgentRepository.save(approvalAgent);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, approvalAgent.getId()))
            .body(approvalAgent);
    }

    /**
     * {@code PATCH  /approval-agents/:id} : Partial updates given fields of an existing approvalAgent, field will ignore if it is null
     *
     * @param id the id of the approvalAgent to save.
     * @param approvalAgent the approvalAgent to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated approvalAgent,
     * or with status {@code 400 (Bad Request)} if the approvalAgent is not valid,
     * or with status {@code 404 (Not Found)} if the approvalAgent is not found,
     * or with status {@code 500 (Internal Server Error)} if the approvalAgent couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ApprovalAgent> partialUpdateApprovalAgent(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ApprovalAgent approvalAgent
    ) throws URISyntaxException {
        log.debug("REST request to partial update ApprovalAgent partially : {}, {}", id, approvalAgent);
        if (approvalAgent.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, approvalAgent.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!approvalAgentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ApprovalAgent> result = approvalAgentRepository
            .findById(approvalAgent.getId())
            .map(existingApprovalAgent -> {
                if (approvalAgent.getAgentid() != null) {
                    existingApprovalAgent.setAgentid(approvalAgent.getAgentid());
                }
                if (approvalAgent.getAgentname() != null) {
                    existingApprovalAgent.setAgentname(approvalAgent.getAgentname());
                }
                if (approvalAgent.getAgentstarttime() != null) {
                    existingApprovalAgent.setAgentstarttime(approvalAgent.getAgentstarttime());
                }
                if (approvalAgent.getAutocanceltime() != null) {
                    existingApprovalAgent.setAutocanceltime(approvalAgent.getAutocanceltime());
                }
                if (approvalAgent.getAgentdepartment() != null) {
                    existingApprovalAgent.setAgentdepartment(approvalAgent.getAgentdepartment());
                }
                if (approvalAgent.getOriginalapprovalname() != null) {
                    existingApprovalAgent.setOriginalapprovalname(approvalAgent.getOriginalapprovalname());
                }
                if (approvalAgent.getOriginaldepartment() != null) {
                    existingApprovalAgent.setOriginaldepartment(approvalAgent.getOriginaldepartment());
                }
                if (approvalAgent.getSecrecylevel() != null) {
                    existingApprovalAgent.setSecrecylevel(approvalAgent.getSecrecylevel());
                }

                return existingApprovalAgent;
            })
            .map(approvalAgentRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, approvalAgent.getId())
        );
    }

    /**
     * {@code GET  /approval-agents} : get all the approvalAgents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of approvalAgents in body.
     */
    @GetMapping("")
    public List<ApprovalAgent> getAllApprovalAgents() {
        log.debug("REST request to get all ApprovalAgents");
        return approvalAgentRepository.findAll();
    }

    /**
     * {@code GET  /approval-agents/:id} : get the "id" approvalAgent.
     *
     * @param id the id of the approvalAgent to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the approvalAgent, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApprovalAgent> getApprovalAgent(@PathVariable("id") String id) {
        log.debug("REST request to get ApprovalAgent : {}", id);
        Optional<ApprovalAgent> approvalAgent = approvalAgentRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(approvalAgent);
    }

    /**
     * {@code DELETE  /approval-agents/:id} : delete the "id" approvalAgent.
     *
     * @param id the id of the approvalAgent to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApprovalAgent(@PathVariable("id") String id) {
        log.debug("REST request to delete ApprovalAgent : {}", id);
        approvalAgentRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
