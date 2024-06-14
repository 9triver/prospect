package com.cvicse.web.rest;

import com.cvicse.domain.Project;
import com.cvicse.repository.ProjectRepository;
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
 * REST controller for managing {@link com.cvicse.domain.Project}.
 */
@RestController
@RequestMapping("/api/projects")
@Transactional
public class ProjectResource {

    private final Logger log = LoggerFactory.getLogger(ProjectResource.class);

    private static final String ENTITY_NAME = "project";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectRepository projectRepository;

    public ProjectResource(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * {@code POST  /projects} : Create a new project.
     *
     * @param project the project to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new project, or with status {@code 400 (Bad Request)} if the project has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Project> createProject(@Valid @RequestBody Project project) throws URISyntaxException {
        log.debug("REST request to save Project : {}", project);
        if (project.getId() != null) {
            throw new BadRequestAlertException("A new project cannot already have an ID", ENTITY_NAME, "idexists");
        }
        project = projectRepository.save(project);
        return ResponseEntity.created(new URI("/api/projects/" + project.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, project.getId().toString()))
            .body(project);
    }

    /**
     * {@code PUT  /projects/:id} : Updates an existing project.
     *
     * @param id the id of the project to save.
     * @param project the project to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated project,
     * or with status {@code 400 (Bad Request)} if the project is not valid,
     * or with status {@code 500 (Internal Server Error)} if the project couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Project project
    ) throws URISyntaxException {
        log.debug("REST request to update Project : {}, {}", id, project);
        if (project.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, project.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        project = projectRepository.save(project);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, project.getId().toString()))
            .body(project);
    }

    /**
     * {@code PATCH  /projects/:id} : Partial updates given fields of an existing project, field will ignore if it is null
     *
     * @param id the id of the project to save.
     * @param project the project to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated project,
     * or with status {@code 400 (Bad Request)} if the project is not valid,
     * or with status {@code 404 (Not Found)} if the project is not found,
     * or with status {@code 500 (Internal Server Error)} if the project couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Project> partialUpdateProject(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Project project
    ) throws URISyntaxException {
        log.debug("REST request to partial update Project partially : {}, {}", id, project);
        if (project.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, project.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Project> result = projectRepository
            .findById(project.getId())
            .map(existingProject -> {
                if (project.getProjectid() != null) {
                    existingProject.setProjectid(project.getProjectid());
                }
                if (project.getProjectname() != null) {
                    existingProject.setProjectname(project.getProjectname());
                }
                if (project.getDescription() != null) {
                    existingProject.setDescription(project.getDescription());
                }
                if (project.getNumber() != null) {
                    existingProject.setNumber(project.getNumber());
                }
                if (project.getProjecttype() != null) {
                    existingProject.setProjecttype(project.getProjecttype());
                }
                if (project.getResponsiblename() != null) {
                    existingProject.setResponsiblename(project.getResponsiblename());
                }
                if (project.getDuedate() != null) {
                    existingProject.setDuedate(project.getDuedate());
                }
                if (project.getPriorty() != null) {
                    existingProject.setPriorty(project.getPriorty());
                }
                if (project.getProgressid() != null) {
                    existingProject.setProgressid(project.getProgressid());
                }
                if (project.getReturnsid() != null) {
                    existingProject.setReturnsid(project.getReturnsid());
                }
                if (project.getQualityid() != null) {
                    existingProject.setQualityid(project.getQualityid());
                }
                if (project.getFundsid() != null) {
                    existingProject.setFundsid(project.getFundsid());
                }
                if (project.getStatus() != null) {
                    existingProject.setStatus(project.getStatus());
                }
                if (project.getAuditStatus() != null) {
                    existingProject.setAuditStatus(project.getAuditStatus());
                }

                return existingProject;
            })
            .map(projectRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, project.getId().toString())
        );
    }

    /**
     * {@code GET  /projects} : get all the projects.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projects in body.
     */
    @GetMapping("")
    public List<Project> getAllProjects(@RequestParam(name = "filter", required = false) String filter) {
        if ("comprehensivecontrol-is-null".equals(filter)) {
            log.debug("REST request to get all Projects where comprehensivecontrol is null");
            return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
                .filter(project -> project.getComprehensivecontrol() == null)
                .toList();
        }

        if ("wbsmanage-is-null".equals(filter)) {
            log.debug("REST request to get all Projects where wbsmanage is null");
            return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
                .filter(project -> project.getWbsmanage() == null)
                .toList();
        }

        if ("outsourcingmpurchaseplan-is-null".equals(filter)) {
            log.debug("REST request to get all Projects where outsourcingmPurchasePlan is null");
            return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
                .filter(project -> project.getOutsourcingmPurchasePlan() == null)
                .toList();
        }

        if ("humanresources-is-null".equals(filter)) {
            log.debug("REST request to get all Projects where humanresources is null");
            return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
                .filter(project -> project.getHumanresources() == null)
                .toList();
        }

        if ("annualsecurityplan-is-null".equals(filter)) {
            log.debug("REST request to get all Projects where annualSecurityPlan is null");
            return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
                .filter(project -> project.getAnnualSecurityPlan() == null)
                .toList();
        }

        if ("managementcapacityevaluation-is-null".equals(filter)) {
            log.debug("REST request to get all Projects where managementCapacityEvaluation is null");
            return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
                .filter(project -> project.getManagementCapacityEvaluation() == null)
                .toList();
        }
        log.debug("REST request to get all Projects");
        return projectRepository.findAll();
    }

    /**
     * {@code GET  /projects/:id} : get the "id" project.
     *
     * @param id the id of the project to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the project, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable("id") Long id) {
        log.debug("REST request to get Project : {}", id);
        Optional<Project> project = projectRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(project);
    }

    /**
     * {@code DELETE  /projects/:id} : delete the "id" project.
     *
     * @param id the id of the project to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable("id") Long id) {
        log.debug("REST request to delete Project : {}", id);
        projectRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
