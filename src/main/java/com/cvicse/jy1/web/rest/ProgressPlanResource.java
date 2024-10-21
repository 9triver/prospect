package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.ProgressPlan;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.PlanLevel;
import com.cvicse.jy1.domain.enumeration.Planstatus;
import com.cvicse.jy1.domain.enumeration.Progressstatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.cvicse.jy1.repository.ProgressPlanRepository;
import com.cvicse.jy1.service.ProgressPlanService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.ProgressPlan}.
 */
@RestController
@RequestMapping("/api/progress-plans")
public class ProgressPlanResource {

    private static final Logger log = LoggerFactory.getLogger(ProgressPlanResource.class);

    private static final String ENTITY_NAME = "progressPlan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProgressPlanService progressPlanService;

    private final ProgressPlanRepository progressPlanRepository;

    public ProgressPlanResource(ProgressPlanService progressPlanService, ProgressPlanRepository progressPlanRepository) {
        this.progressPlanService = progressPlanService;
        this.progressPlanRepository = progressPlanRepository;
    }

    /**
     * {@code POST  /progress-plans} : Create a new progressPlan.
     *
     * @param progressPlan the progressPlan to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new progressPlan, or with status {@code 400 (Bad Request)} if the progressPlan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ProgressPlan> createProgressPlan(@RequestBody ProgressPlan progressPlan) throws URISyntaxException {
        log.debug("REST request to save ProgressPlan : {}", progressPlan);
        if (progressPlan.getId() != null) {
            throw new BadRequestAlertException("A new progressPlan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        progressPlan = progressPlanService.save(progressPlan);
        return ResponseEntity.created(new URI("/api/progress-plans/" + progressPlan.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, progressPlan.getId()))
            .body(progressPlan);
    }

    /**
     * {@code PUT  /progress-plans/:id} : Updates an existing progressPlan.
     *
     * @param id the id of the progressPlan to save.
     * @param progressPlan the progressPlan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated progressPlan,
     * or with status {@code 400 (Bad Request)} if the progressPlan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the progressPlan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProgressPlan> updateProgressPlan(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ProgressPlan progressPlan
    ) throws URISyntaxException {
        log.debug("REST request to update ProgressPlan : {}, {}", id, progressPlan);
        if (progressPlan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, progressPlan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!progressPlanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        progressPlan = progressPlanService.update(progressPlan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, progressPlan.getId()))
            .body(progressPlan);
    }

    /**
     * {@code PATCH  /progress-plans/:id} : Partial updates given fields of an existing progressPlan, field will ignore if it is null
     *
     * @param id the id of the progressPlan to save.
     * @param progressPlan the progressPlan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated progressPlan,
     * or with status {@code 400 (Bad Request)} if the progressPlan is not valid,
     * or with status {@code 404 (Not Found)} if the progressPlan is not found,
     * or with status {@code 500 (Internal Server Error)} if the progressPlan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProgressPlan> partialUpdateProgressPlan(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ProgressPlan progressPlan
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProgressPlan partially : {}, {}", id, progressPlan);
        if (progressPlan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, progressPlan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!progressPlanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProgressPlan> result = progressPlanService.partialUpdate(progressPlan);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, progressPlan.getId())
        );
    }

    /**
     * {@code GET  /progress-plans} : get all the progressPlans.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of progressPlans in body.
     */
    @GetMapping("")
    public List<ProgressPlan> getAllProgressPlans(
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get all ProgressPlans");
        return progressPlanService.findAll();
    }

    /**
     * {@code GET  /progress-plans/:id} : get the "id" progressPlan.
     *
     * @param id the id of the progressPlan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the progressPlan, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProgressPlan> getProgressPlan(@PathVariable("id") String id) {
        log.debug("REST request to get ProgressPlan : {}", id);
        Optional<ProgressPlan> progressPlan = progressPlanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(progressPlan);
    }

    /**
     * {@code DELETE  /progress-plans/:id} : delete the "id" progressPlan.
     *
     * @param id the id of the progressPlan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgressPlan(@PathVariable("id") String id) {
        log.debug("REST request to delete ProgressPlan : {}", id);
        progressPlanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }


    /**
     * 条件查询
     */
    //{"id":"","planname":"年","belongplanid":"","secretlevel":"","starttime":"","endtime":"","plantype":null,"description":"","progress":null,"status":"","auditStatus":""}
    @PostMapping(value = "/query2" )
    public List<ProgressPlan> getAllProgressPlanByQuery2(@RequestBody ProgressPlan progressPlan) {
        System.err.println("！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
        log.debug("REST request to get all ProgressPlan");
        // 提取查询参数
        String id = progressPlan.getId();
        String planname = progressPlan.getPlanname();
        String belongplanid = progressPlan.getBelongplanid();
        String starttime = (progressPlan.getStarttime() != null) ? progressPlan.getStarttime().toString() : null;
        String endtime = (progressPlan.getEndtime() != null) ? progressPlan.getEndtime().toString() : null;
        String description = progressPlan.getDescription();
        Integer plantype = progressPlan.getPlantype();
        Integer progress = progressPlan.getProgress();
        Integer iskey = progressPlan.getIskey();
        Secretlevel secretlevel = progressPlan.getSecretlevel(); // 获取 Secretlevel 枚举值
        PlanLevel planlevel = progressPlan.getPlanlevel();
        Progressstatus progresstype = progressPlan.getProgresstype();
        Planstatus status = progressPlan.getStatus();
        AuditStatus auditStatus = progressPlan.getAuditStatus();

        // System.err.println("！！！！！！！！！！！！查询条件：planname="+planname+",belongplanid="+belongplanid+"，secretlevel="+secretlevel+"！！！！！！！！！！！！");
        // 调用 repository 方法
        return progressPlanRepository.findAllWithToOneRelationship2(
            id, planname, belongplanid, secretlevel, starttime, endtime,planlevel, iskey,
            plantype, description, progress, progresstype, status, auditStatus
        );
    }

    @PostMapping(value = "/query" )
    public List<ProgressPlan> getAllProgressPlanByQuery(@RequestBody ProgressPlan progressPlan) {
        System.err.println("！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
        log.debug("REST request to get all ProgressPlan");
        // 提取查询参数
        String id = progressPlan.getId();
        String planname = progressPlan.getPlanname();
        Integer plantype = progressPlan.getPlantype();
        Integer progress = progressPlan.getProgress();
        Secretlevel secretlevel = progressPlan.getSecretlevel(); // 获取 Secretlevel 枚举值
        Planstatus status = progressPlan.getStatus();
        AuditStatus auditStatus = progressPlan.getAuditStatus();

        System.err.println("！！！！！！！！！！！！查询条件：planname="+planname+"，secretlevel="+secretlevel+"！！！！！！！！！！！！");
        // 调用 repository 方法
        return progressPlanRepository.findAllWithToOneRelationship(
            id, planname, secretlevel, progress, plantype, status, auditStatus
        );
    }
}
