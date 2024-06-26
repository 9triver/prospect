package com.cvicse.service.impl;

import com.cvicse.domain.ProjectSecrecy;
import com.cvicse.repository.ProjectSecrecyRepository;
import com.cvicse.service.ProjectSecrecyService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.ProjectSecrecy}.
 */
@Service
@Transactional
public class ProjectSecrecyServiceImpl implements ProjectSecrecyService {

    private final Logger log = LoggerFactory.getLogger(ProjectSecrecyServiceImpl.class);

    private final ProjectSecrecyRepository projectSecrecyRepository;

    public ProjectSecrecyServiceImpl(ProjectSecrecyRepository projectSecrecyRepository) {
        this.projectSecrecyRepository = projectSecrecyRepository;
    }

    @Override
    public ProjectSecrecy save(ProjectSecrecy projectSecrecy) {
        log.debug("Request to save ProjectSecrecy : {}", projectSecrecy);
        return projectSecrecyRepository.save(projectSecrecy);
    }

    @Override
    public ProjectSecrecy update(ProjectSecrecy projectSecrecy) {
        log.debug("Request to update ProjectSecrecy : {}", projectSecrecy);
        return projectSecrecyRepository.save(projectSecrecy);
    }

    @Override
    public Optional<ProjectSecrecy> partialUpdate(ProjectSecrecy projectSecrecy) {
        log.debug("Request to partially update ProjectSecrecy : {}", projectSecrecy);

        return projectSecrecyRepository
            .findById(projectSecrecy.getId())
            .map(existingProjectSecrecy -> {
                if (projectSecrecy.getProjectname() != null) {
                    existingProjectSecrecy.setProjectname(projectSecrecy.getProjectname());
                }
                if (projectSecrecy.getDescription() != null) {
                    existingProjectSecrecy.setDescription(projectSecrecy.getDescription());
                }
                if (projectSecrecy.getCreatetime() != null) {
                    existingProjectSecrecy.setCreatetime(projectSecrecy.getCreatetime());
                }
                if (projectSecrecy.getAuditStatus() != null) {
                    existingProjectSecrecy.setAuditStatus(projectSecrecy.getAuditStatus());
                }

                return existingProjectSecrecy;
            })
            .map(projectSecrecyRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectSecrecy> findAll() {
        log.debug("Request to get all ProjectSecrecies");
        return projectSecrecyRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectSecrecy> findOne(Long id) {
        log.debug("Request to get ProjectSecrecy : {}", id);
        return projectSecrecyRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProjectSecrecy : {}", id);
        projectSecrecyRepository.deleteById(id);
    }
}
