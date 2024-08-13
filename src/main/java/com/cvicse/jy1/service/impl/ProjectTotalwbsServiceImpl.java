package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.ProjectTotalwbs;
import com.cvicse.jy1.repository.ProjectTotalwbsRepository;
import com.cvicse.jy1.service.ProjectTotalwbsService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.ProjectTotalwbs}.
 */
@Service
@Transactional
public class ProjectTotalwbsServiceImpl implements ProjectTotalwbsService {

    private static final Logger log = LoggerFactory.getLogger(ProjectTotalwbsServiceImpl.class);

    private final ProjectTotalwbsRepository projectTotalwbsRepository;

    public ProjectTotalwbsServiceImpl(ProjectTotalwbsRepository projectTotalwbsRepository) {
        this.projectTotalwbsRepository = projectTotalwbsRepository;
    }

    @Override
    public ProjectTotalwbs save(ProjectTotalwbs projectTotalwbs) {
        log.debug("Request to save ProjectTotalwbs : {}", projectTotalwbs);
        return projectTotalwbsRepository.save(projectTotalwbs);
    }

    @Override
    public ProjectTotalwbs update(ProjectTotalwbs projectTotalwbs) {
        log.debug("Request to update ProjectTotalwbs : {}", projectTotalwbs);
        return projectTotalwbsRepository.save(projectTotalwbs);
    }

    @Override
    public Optional<ProjectTotalwbs> partialUpdate(ProjectTotalwbs projectTotalwbs) {
        log.debug("Request to partially update ProjectTotalwbs : {}", projectTotalwbs);

        return projectTotalwbsRepository
            .findById(projectTotalwbs.getId())
            .map(existingProjectTotalwbs -> {
                if (projectTotalwbs.getWbsname() != null) {
                    existingProjectTotalwbs.setWbsname(projectTotalwbs.getWbsname());
                }
                if (projectTotalwbs.getParentwbsid() != null) {
                    existingProjectTotalwbs.setParentwbsid(projectTotalwbs.getParentwbsid());
                }
                if (projectTotalwbs.getPbsid() != null) {
                    existingProjectTotalwbs.setPbsid(projectTotalwbs.getPbsid());
                }
                if (projectTotalwbs.getDescription() != null) {
                    existingProjectTotalwbs.setDescription(projectTotalwbs.getDescription());
                }
                if (projectTotalwbs.getBelongfront() != null) {
                    existingProjectTotalwbs.setBelongfront(projectTotalwbs.getBelongfront());
                }
                if (projectTotalwbs.getStarttime() != null) {
                    existingProjectTotalwbs.setStarttime(projectTotalwbs.getStarttime());
                }
                if (projectTotalwbs.getEndtime() != null) {
                    existingProjectTotalwbs.setEndtime(projectTotalwbs.getEndtime());
                }
                if (projectTotalwbs.getProgress() != null) {
                    existingProjectTotalwbs.setProgress(projectTotalwbs.getProgress());
                }
                if (projectTotalwbs.getType() != null) {
                    existingProjectTotalwbs.setType(projectTotalwbs.getType());
                }
                if (projectTotalwbs.getPriorty() != null) {
                    existingProjectTotalwbs.setPriorty(projectTotalwbs.getPriorty());
                }
                if (projectTotalwbs.getSecretlevel() != null) {
                    existingProjectTotalwbs.setSecretlevel(projectTotalwbs.getSecretlevel());
                }
                if (projectTotalwbs.getDeliverables() != null) {
                    existingProjectTotalwbs.setDeliverables(projectTotalwbs.getDeliverables());
                }
                if (projectTotalwbs.getStatus() != null) {
                    existingProjectTotalwbs.setStatus(projectTotalwbs.getStatus());
                }
                if (projectTotalwbs.getAuditStatus() != null) {
                    existingProjectTotalwbs.setAuditStatus(projectTotalwbs.getAuditStatus());
                }
                if (projectTotalwbs.getWorkbag() != null) {
                    existingProjectTotalwbs.setWorkbag(projectTotalwbs.getWorkbag());
                }

                return existingProjectTotalwbs;
            })
            .map(projectTotalwbsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectTotalwbs> findAll() {
        log.debug("Request to get all ProjectTotalwbs");
        return projectTotalwbsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectTotalwbs> findOne(String id) {
        log.debug("Request to get ProjectTotalwbs : {}", id);
        return projectTotalwbsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ProjectTotalwbs : {}", id);
        projectTotalwbsRepository.deleteById(id);
    }
}
