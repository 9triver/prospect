package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.Projectpbs;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.ProjectStatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.cvicse.jy1.repository.ProjectpbsRepository;
import com.cvicse.jy1.service.ProjectpbsService;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.Projectpbs}.
 */
@RestController
@RequestMapping("/api/projectpbs")
public class ProjectpbsResource {

    private static final Logger log = LoggerFactory.getLogger(ProjectpbsResource.class);

    private static final String ENTITY_NAME = "projectpbs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectpbsService projectpbsService;

    private final ProjectpbsRepository projectpbsRepository;

    public ProjectpbsResource(ProjectpbsService projectpbsService, ProjectpbsRepository projectpbsRepository) {
        this.projectpbsService = projectpbsService;
        this.projectpbsRepository = projectpbsRepository;
    }

    /**
     * {@code POST  /projectpbs} : Create a new projectpbs.
     *
     * @param projectpbs the projectpbs to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectpbs, or with status {@code 400 (Bad Request)} if the projectpbs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Projectpbs> createProjectpbs(@RequestBody Projectpbs projectpbs) throws URISyntaxException {
        log.debug("REST request to save Projectpbs : {}", projectpbs);
        if (projectpbs.getId() != null) {
            throw new BadRequestAlertException("A new projectpbs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        projectpbs = projectpbsService.save(projectpbs);
        return ResponseEntity.created(new URI("/api/projectpbs/" + projectpbs.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, projectpbs.getId()))
            .body(projectpbs);
    }

    /**
     * {@code PUT  /projectpbs/:id} : Updates an existing projectpbs.
     *
     * @param id the id of the projectpbs to save.
     * @param projectpbs the projectpbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectpbs,
     * or with status {@code 400 (Bad Request)} if the projectpbs is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectpbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Projectpbs> updateProjectpbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Projectpbs projectpbs
    ) throws URISyntaxException {
        log.debug("REST request to update Projectpbs : {}, {}", id, projectpbs);
        if (projectpbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectpbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectpbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        projectpbs = projectpbsService.update(projectpbs);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectpbs.getId()))
            .body(projectpbs);
    }

    /**
     * {@code PATCH  /projectpbs/:id} : Partial updates given fields of an existing projectpbs, field will ignore if it is null
     *
     * @param id the id of the projectpbs to save.
     * @param projectpbs the projectpbs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectpbs,
     * or with status {@code 400 (Bad Request)} if the projectpbs is not valid,
     * or with status {@code 404 (Not Found)} if the projectpbs is not found,
     * or with status {@code 500 (Internal Server Error)} if the projectpbs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Projectpbs> partialUpdateProjectpbs(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Projectpbs projectpbs
    ) throws URISyntaxException {
        log.debug("REST request to partial update Projectpbs partially : {}, {}", id, projectpbs);
        if (projectpbs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectpbs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectpbsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Projectpbs> result = projectpbsService.partialUpdate(projectpbs);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectpbs.getId())
        );
    }

    /**
     * {@code GET  /projectpbs} : get all the projectpbs.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectpbs in body.
     */
    @GetMapping("")
    public List<Projectpbs> getAllProjectpbs(
        @RequestParam(name = "filter", required = false) String filter,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        if ("projectwbs-is-null".equals(filter)) {
            log.debug("REST request to get all Projectpbss where projectwbs is null");
            return projectpbsService.findAllWhereProjectwbsIsNull();
        }
        log.debug("REST request to get all Projectpbs");
        return projectpbsService.findAll();
    }

    /**
     * {@code GET  /projectpbs/:id} : get the "id" projectpbs.
     *
     * @param id the id of the projectpbs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectpbs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Projectpbs> getProjectpbs(@PathVariable("id") String id) {
        log.debug("REST request to get Projectpbs : {}", id);
        Optional<Projectpbs> projectpbs = projectpbsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projectpbs);
    }

    /**
     * {@code DELETE  /projectpbs/:id} : delete the "id" projectpbs.
     *
     * @param id the id of the projectpbs to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectpbs(@PathVariable("id") String id) {
        log.debug("REST request to delete Projectpbs : {}", id);
        projectpbsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }


    /**
     * 条件查询
     **/

    // @GetMapping(value = "/query", consumes = { "application/json", "application/merge-patch+json" })
    // public List<Projectpbs> getAllProjectpbsByQuery(@RequestBody Projectpbs projectpbs)  {
    //     log.debug("REST request to get all Projectpbs");
    //     System.err.println("!!!!!!!!!!!!!!!!!!!------"+projectpbs);
    //     return projectpbsRepository.findAllWithToOneRelationship(projectpbs);
    // }
    @PostMapping(value = "/query" )
    public List<Projectpbs> getAllProjectpbsByQuery(@RequestBody Projectpbs projectpbs) {
        log.debug("REST request to get all Projectpbs");
        // 提取查询参数
        String id = projectpbs.getId();
        String pbsname = projectpbs.getPbsname();
        String parentpbsid = projectpbs.getParentpbsid();
        String starttime = (projectpbs.getStarttime() != null) ? projectpbs.getStarttime().toString() : null;
        String endtime = (projectpbs.getEndtime() != null) ? projectpbs.getEndtime().toString() : null;
        String productlevel = projectpbs.getProductlevel();
        Integer iskey = projectpbs.getIskey();
        Integer isimportant = projectpbs.getIsimportant();
        String description = projectpbs.getDescription();
        Integer progress = projectpbs.getProgress();
        Integer type = projectpbs.getType();
        Integer priorty = projectpbs.getPriorty();
        // String technicaldirectorname = projectpbs.getTechnicaldirectorname();
        Secretlevel secretlevel = projectpbs.getSecretlevel(); // 获取 Secretlevel 枚举值
        ProjectStatus status = projectpbs.getStatus();
        AuditStatus auditStatus = projectpbs.getAuditStatus();
        // String secretlevelStr = (projectpbs.getSecretlevel() != null && !projectpbs.getSecretlevel().toString().isEmpty())
        //                         ? projectpbs.getSecretlevel().name()
        //                         : null;
        // String statuslStr = (projectpbs.getStatus() != null && !projectpbs.getStatus().toString().isEmpty())
        //                         ? projectpbs.getStatus().name() 
        //                         : null;
        // String auditStatuslStr = (projectpbs.getAuditStatus() != null && !projectpbs.getAuditStatus().toString().isEmpty())
        //                         ? projectpbs.getAuditStatus().name() 
        //                         : null;

        System.err.println("！！！！！！！！！！！！查询条件：pbsname="+pbsname+",parentpbsid="+parentpbsid+"，secretlevel="+secretlevel+"！！！！！！！！！！！！");
        // 调用 repository 方法
        return projectpbsRepository.findAllWithToOneRelationship(
            id, pbsname, parentpbsid, secretlevel, starttime, endtime, productlevel,
            iskey, isimportant, description, progress, type, priorty, status, auditStatus
        );
    }
}
