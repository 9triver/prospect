package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.Projectpbs;
import com.cvicse.jy1.config.ApplicationProperties;
import com.cvicse.jy1.domain.Documentmenu;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.cvicse.jy1.domain.enumeration.ProjectStatus;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.repository.ProjectpbsRepository;
import com.cvicse.jy1.repository.DocumentmenuRepository;
import com.cvicse.jy1.service.ProjectpbsService;
import com.cvicse.jy1.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.nio.file.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
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
    private final DocumentmenuRepository documentmenuRepository;

    private final Path fileStorageLocation;


    public ProjectpbsResource(ProjectpbsService projectpbsService, ProjectpbsRepository projectpbsRepository, ApplicationProperties properties, DocumentmenuRepository documentmenuRepository) {
        this.projectpbsService = projectpbsService;
        this.projectpbsRepository = projectpbsRepository;
        this.documentmenuRepository = documentmenuRepository;

        String uploadDir = properties.getFile().getUploadDir();
        if (uploadDir == null || uploadDir.isEmpty()) {
            throw new IllegalArgumentException("Upload directory is not configured properly.");
        }
        this.fileStorageLocation = Paths.get(uploadDir);
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
        // if (projectpbs.getId() != null) {
        //     throw new BadRequestAlertException("A new projectpbs cannot already have an ID", ENTITY_NAME, "idexists");
        // }
        //保存PBS
        projectpbs = projectpbsService.save(projectpbs);

        //保存文件目录
        String menuid = String.valueOf(projectpbs.getParentpbsid());
        if (menuid == null || menuid.equals("")) {
            menuid = null;
        }
        String belongtype = "PBS";
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!menuid="+menuid+",belongtype="+belongtype);
        List<Documentmenu> documentmenulist = documentmenuRepository.findbyMenuid(menuid, belongtype);
        System.out.println("查询条数："+documentmenulist.size()+"，查询结果："+documentmenulist);
        String fileurlfirst = "";
        Integer level = 0;
        if(documentmenulist!=null && documentmenulist.size()>0){
            fileurlfirst = documentmenulist.get(0).getFileurl();
            if(fileurlfirst==null || fileurlfirst.equals("")){
                fileurlfirst = documentmenulist.get(0).getMenuname();
                level = 1;
            }
        }else{
            Documentmenu documentmenu = new Documentmenu();
            documentmenu.setMenuid("pbs");
            documentmenu.setBelongtype("PBS");
            documentmenu.setMenuname("PBS管理");
            documentmenu.setParentmenuid("");
            documentmenu.setCreatetime(LocalDate.now());
            documentmenu.setType(1);
            System.out.println("777777777"+this.fileStorageLocation.toString());
            documentmenu.setFileurl(this.fileStorageLocation.toString());
            System.out.println("保存文件目录参数："+documentmenu);
            documentmenuRepository.save(documentmenu); 
            fileurlfirst = this.fileStorageLocation.toString();
            level = 2;
        }
        String fileurl = fileurlfirst+"/"+projectpbs.getPbsname();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!文件目录fileurl="+fileurl);
        Documentmenu documentmenu = new Documentmenu();
        documentmenu.setMenuid(projectpbs.getId());
        documentmenu.setBelongtype("PBS");
        documentmenu.setMenuname(projectpbs.getPbsname());
        if(level == 2){
            documentmenu.setParentmenuid("pbs");
        }else{
            documentmenu.setParentmenuid(projectpbs.getParentpbsid());
        }
        documentmenu.setCreatetime(LocalDate.now());
        documentmenu.setType(1);
        documentmenu.setFileurl(fileurl);
        System.out.println("保存文件目录参数："+documentmenu);
        documentmenuRepository.save(documentmenu); 
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!保存文件目录完成");

        //返回
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
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectpbs in body.
     */
    @GetMapping("")
    public List<Projectpbs> getAllProjectpbs(@RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload) {
        log.debug("REST request to get all Projectpbs");
        return projectpbsService.findAll();
    }

    /**
     * {@code GET  /projectpbs} : get projectpbs through query criteria.条件查询
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectpbs in body.
     */
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
        Integer productlevel = projectpbs.getProductlevel();
        Integer ifkey = projectpbs.getIfkey();
        Integer ifimporttant = projectpbs.getIfimporttant();
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
            ifkey, ifimporttant, description, progress, type, priorty, status, auditStatus
        );
    }
    // @GetMapping("/query")
    // public List<Projectpbs> getAllProjectpbsByQuery(@RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload)  {
    //     log.debug("REST request to get all Projectpbs");
    //     return projectpbsRepository.findAllWithToOneRelationship("勇敢"); 
    // }

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
}
