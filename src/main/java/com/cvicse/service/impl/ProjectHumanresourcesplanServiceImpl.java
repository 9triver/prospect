package com.cvicse.service.impl;

import com.cvicse.domain.ProjectHumanresourcesplan;
import com.cvicse.repository.ProjectHumanresourcesplanRepository;
import com.cvicse.service.ProjectHumanresourcesplanService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.ProjectHumanresourcesplan}.
 */
@Service
@Transactional
public class ProjectHumanresourcesplanServiceImpl implements ProjectHumanresourcesplanService {

    private final Logger log = LoggerFactory.getLogger(ProjectHumanresourcesplanServiceImpl.class);

    private final ProjectHumanresourcesplanRepository projectHumanresourcesplanRepository;

    public ProjectHumanresourcesplanServiceImpl(ProjectHumanresourcesplanRepository projectHumanresourcesplanRepository) {
        this.projectHumanresourcesplanRepository = projectHumanresourcesplanRepository;
    }

    @Override
    public ProjectHumanresourcesplan save(ProjectHumanresourcesplan projectHumanresourcesplan) {
        log.debug("Request to save ProjectHumanresourcesplan : {}", projectHumanresourcesplan);
        return projectHumanresourcesplanRepository.save(projectHumanresourcesplan);
    }

    @Override
    public ProjectHumanresourcesplan update(ProjectHumanresourcesplan projectHumanresourcesplan) {
        log.debug("Request to update ProjectHumanresourcesplan : {}", projectHumanresourcesplan);
        return projectHumanresourcesplanRepository.save(projectHumanresourcesplan);
    }

    @Override
    public Optional<ProjectHumanresourcesplan> partialUpdate(ProjectHumanresourcesplan projectHumanresourcesplan) {
        log.debug("Request to partially update ProjectHumanresourcesplan : {}", projectHumanresourcesplan);

        return projectHumanresourcesplanRepository
            .findById(projectHumanresourcesplan.getId())
            .map(existingProjectHumanresourcesplan -> {
                if (projectHumanresourcesplan.getProjectname() != null) {
                    existingProjectHumanresourcesplan.setProjectname(projectHumanresourcesplan.getProjectname());
                }
                if (projectHumanresourcesplan.getClientname() != null) {
                    existingProjectHumanresourcesplan.setClientname(projectHumanresourcesplan.getClientname());
                }
                if (projectHumanresourcesplan.getPlandate() != null) {
                    existingProjectHumanresourcesplan.setPlandate(projectHumanresourcesplan.getPlandate());
                }
                if (projectHumanresourcesplan.getSecretlevel() != null) {
                    existingProjectHumanresourcesplan.setSecretlevel(projectHumanresourcesplan.getSecretlevel());
                }
                if (projectHumanresourcesplan.getAuditStatus() != null) {
                    existingProjectHumanresourcesplan.setAuditStatus(projectHumanresourcesplan.getAuditStatus());
                }

                return existingProjectHumanresourcesplan;
            })
            .map(projectHumanresourcesplanRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectHumanresourcesplan> findAll() {
        log.debug("Request to get all ProjectHumanresourcesplans");
        return projectHumanresourcesplanRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectHumanresourcesplan> findOne(String id) {
        log.debug("Request to get ProjectHumanresourcesplan : {}", id);
        return projectHumanresourcesplanRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ProjectHumanresourcesplan : {}", id);
        projectHumanresourcesplanRepository.deleteById(id);
    }
}
