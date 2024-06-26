package com.cvicse.service.impl;

import com.cvicse.domain.Project;
import com.cvicse.repository.ProjectRepository;
import com.cvicse.service.ProjectService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Project}.
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project save(Project project) {
        log.debug("Request to save Project : {}", project);
        return projectRepository.save(project);
    }

    @Override
    public Project update(Project project) {
        log.debug("Request to update Project : {}", project);
        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> partialUpdate(Project project) {
        log.debug("Request to partially update Project : {}", project);

        return projectRepository
            .findById(project.getId())
            .map(existingProject -> {
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
                if (project.getStatus() != null) {
                    existingProject.setStatus(project.getStatus());
                }
                if (project.getAuditStatus() != null) {
                    existingProject.setAuditStatus(project.getAuditStatus());
                }

                return existingProject;
            })
            .map(projectRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Project> findAll() {
        log.debug("Request to get all Projects");
        return projectRepository.findAll();
    }

    /**
     *  Get all the projects where Comprehensivecontrol is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Project> findAllWhereComprehensivecontrolIsNull() {
        log.debug("Request to get all projects where Comprehensivecontrol is null");
        return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
            .filter(project -> project.getComprehensivecontrol() == null)
            .toList();
    }

    /**
     *  Get all the projects where Wbsmanage is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Project> findAllWhereWbsmanageIsNull() {
        log.debug("Request to get all projects where Wbsmanage is null");
        return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
            .filter(project -> project.getWbsmanage() == null)
            .toList();
    }

    /**
     *  Get all the projects where OutsourcingPurchasePlan is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Project> findAllWhereOutsourcingPurchasePlanIsNull() {
        log.debug("Request to get all projects where OutsourcingPurchasePlan is null");
        return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
            .filter(project -> project.getOutsourcingPurchasePlan() == null)
            .toList();
    }

    /**
     *  Get all the projects where ProjectHumanresourcesplan is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Project> findAllWhereProjectHumanresourcesplanIsNull() {
        log.debug("Request to get all projects where ProjectHumanresourcesplan is null");
        return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
            .filter(project -> project.getProjectHumanresourcesplan() == null)
            .toList();
    }

    /**
     *  Get all the projects where Projectremit is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Project> findAllWhereProjectremitIsNull() {
        log.debug("Request to get all projects where Projectremit is null");
        return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
            .filter(project -> project.getProjectremit() == null)
            .toList();
    }

    /**
     *  Get all the projects where Humanresources is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Project> findAllWhereHumanresourcesIsNull() {
        log.debug("Request to get all projects where Humanresources is null");
        return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
            .filter(project -> project.getHumanresources() == null)
            .toList();
    }

    /**
     *  Get all the projects where AnnualSecurityPlan is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Project> findAllWhereAnnualSecurityPlanIsNull() {
        log.debug("Request to get all projects where AnnualSecurityPlan is null");
        return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
            .filter(project -> project.getAnnualSecurityPlan() == null)
            .toList();
    }

    /**
     *  Get all the projects where ManagementCapacityEvaluation is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Project> findAllWhereManagementCapacityEvaluationIsNull() {
        log.debug("Request to get all projects where ManagementCapacityEvaluation is null");
        return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
            .filter(project -> project.getManagementCapacityEvaluation() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Project> findOne(String id) {
        log.debug("Request to get Project : {}", id);
        return projectRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Project : {}", id);
        projectRepository.deleteById(id);
    }
}
