package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.LeaveApplicationInfo;
import com.cvicse.jy1.repository.LeaveApplicationInfoRepository;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.cvicse.jy1.domain.LeaveApplicationInfo}.
 */
@RestController
@RequestMapping("/api/leave-application-infos")
@Transactional
public class LeaveApplicationInfoResource {

    private static final Logger log = LoggerFactory.getLogger(LeaveApplicationInfoResource.class);

    private static final String ENTITY_NAME = "leaveApplicationInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LeaveApplicationInfoRepository leaveApplicationInfoRepository;

    public LeaveApplicationInfoResource(LeaveApplicationInfoRepository leaveApplicationInfoRepository) {
        this.leaveApplicationInfoRepository = leaveApplicationInfoRepository;
    }

    /**
     * {@code POST  /leave-application-infos} : Create a new leaveApplicationInfo.
     *
     * @param leaveApplicationInfo the leaveApplicationInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new leaveApplicationInfo, or with status {@code 400 (Bad Request)} if the leaveApplicationInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<LeaveApplicationInfo> createLeaveApplicationInfo(@RequestBody LeaveApplicationInfo leaveApplicationInfo)
        throws URISyntaxException {
        log.debug("REST request to save LeaveApplicationInfo : {}", leaveApplicationInfo);
        if (leaveApplicationInfo.getId() != null) {
            throw new BadRequestAlertException("A new leaveApplicationInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        leaveApplicationInfo = leaveApplicationInfoRepository.save(leaveApplicationInfo);
        return ResponseEntity.created(new URI("/api/leave-application-infos/" + leaveApplicationInfo.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, leaveApplicationInfo.getId()))
            .body(leaveApplicationInfo);
    }

    /**
     * {@code PUT  /leave-application-infos/:id} : Updates an existing leaveApplicationInfo.
     *
     * @param id the id of the leaveApplicationInfo to save.
     * @param leaveApplicationInfo the leaveApplicationInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated leaveApplicationInfo,
     * or with status {@code 400 (Bad Request)} if the leaveApplicationInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the leaveApplicationInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<LeaveApplicationInfo> updateLeaveApplicationInfo(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody LeaveApplicationInfo leaveApplicationInfo
    ) throws URISyntaxException {
        log.debug("REST request to update LeaveApplicationInfo : {}, {}", id, leaveApplicationInfo);
        if (leaveApplicationInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, leaveApplicationInfo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!leaveApplicationInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        leaveApplicationInfo = leaveApplicationInfoRepository.save(leaveApplicationInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, leaveApplicationInfo.getId()))
            .body(leaveApplicationInfo);
    }

    /**
     * {@code PATCH  /leave-application-infos/:id} : Partial updates given fields of an existing leaveApplicationInfo, field will ignore if it is null
     *
     * @param id the id of the leaveApplicationInfo to save.
     * @param leaveApplicationInfo the leaveApplicationInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated leaveApplicationInfo,
     * or with status {@code 400 (Bad Request)} if the leaveApplicationInfo is not valid,
     * or with status {@code 404 (Not Found)} if the leaveApplicationInfo is not found,
     * or with status {@code 500 (Internal Server Error)} if the leaveApplicationInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LeaveApplicationInfo> partialUpdateLeaveApplicationInfo(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody LeaveApplicationInfo leaveApplicationInfo
    ) throws URISyntaxException {
        log.debug("REST request to partial update LeaveApplicationInfo partially : {}, {}", id, leaveApplicationInfo);
        if (leaveApplicationInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, leaveApplicationInfo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!leaveApplicationInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LeaveApplicationInfo> result = leaveApplicationInfoRepository
            .findById(leaveApplicationInfo.getId())
            .map(existingLeaveApplicationInfo -> {
                if (leaveApplicationInfo.getStartDate() != null) {
                    existingLeaveApplicationInfo.setStartDate(leaveApplicationInfo.getStartDate());
                }
                if (leaveApplicationInfo.getEndDate() != null) {
                    existingLeaveApplicationInfo.setEndDate(leaveApplicationInfo.getEndDate());
                }
                if (leaveApplicationInfo.getLeaveType() != null) {
                    existingLeaveApplicationInfo.setLeaveType(leaveApplicationInfo.getLeaveType());
                }
                if (leaveApplicationInfo.getReason() != null) {
                    existingLeaveApplicationInfo.setReason(leaveApplicationInfo.getReason());
                }
                if (leaveApplicationInfo.getStatus() != null) {
                    existingLeaveApplicationInfo.setStatus(leaveApplicationInfo.getStatus());
                }

                return existingLeaveApplicationInfo;
            })
            .map(leaveApplicationInfoRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, leaveApplicationInfo.getId())
        );
    }

    /**
     * {@code GET  /leave-application-infos} : get all the leaveApplicationInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of leaveApplicationInfos in body.
     */
    @GetMapping("")
    public List<LeaveApplicationInfo> getAllLeaveApplicationInfos() {
        log.debug("REST request to get all LeaveApplicationInfos");
        return leaveApplicationInfoRepository.findAll();
    }

    /**
     * {@code GET  /leave-application-infos/:id} : get the "id" leaveApplicationInfo.
     *
     * @param id the id of the leaveApplicationInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the leaveApplicationInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LeaveApplicationInfo> getLeaveApplicationInfo(@PathVariable("id") String id) {
        log.debug("REST request to get LeaveApplicationInfo : {}", id);
        Optional<LeaveApplicationInfo> leaveApplicationInfo = leaveApplicationInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(leaveApplicationInfo);
    }

    /**
     * {@code DELETE  /leave-application-infos/:id} : delete the "id" leaveApplicationInfo.
     *
     * @param id the id of the leaveApplicationInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaveApplicationInfo(@PathVariable("id") String id) {
        log.debug("REST request to delete LeaveApplicationInfo : {}", id);
        leaveApplicationInfoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
