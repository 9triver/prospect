package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.ProjectRisk;
import com.cvicse.jy1.repository.ProjectRiskRepository;
import com.cvicse.jy1.service.ProjectRiskService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.ProjectRisk}.
 */
@Service
@Transactional
public class ProjectRiskServiceImpl implements ProjectRiskService {

    private static final Logger log = LoggerFactory.getLogger(ProjectRiskServiceImpl.class);

    private final ProjectRiskRepository projectRiskRepository;

    public ProjectRiskServiceImpl(ProjectRiskRepository projectRiskRepository) {
        this.projectRiskRepository = projectRiskRepository;
    }

    @Override
    public ProjectRisk save(ProjectRisk projectRisk) {
        log.debug("Request to save ProjectRisk : {}", projectRisk);
        return projectRiskRepository.save(projectRisk);
    }

    @Override
    public ProjectRisk update(ProjectRisk projectRisk) {
        log.debug("Request to update ProjectRisk : {}", projectRisk);
        return projectRiskRepository.save(projectRisk);
    }

    @Override
    public Optional<ProjectRisk> partialUpdate(ProjectRisk projectRisk) {
        log.debug("Request to partially update ProjectRisk : {}", projectRisk);

        return projectRiskRepository
            .findById(projectRisk.getId())
            .map(existingProjectRisk -> {
                if (projectRisk.getYear() != null) {
                    existingProjectRisk.setYear(projectRisk.getYear());
                }
                if (projectRisk.getNodename() != null) {
                    existingProjectRisk.setNodename(projectRisk.getNodename());
                }
                if (projectRisk.getRisktype() != null) {
                    existingProjectRisk.setRisktype(projectRisk.getRisktype());
                }
                if (projectRisk.getDecumentid() != null) {
                    existingProjectRisk.setDecumentid(projectRisk.getDecumentid());
                }
                if (projectRisk.getVersion() != null) {
                    existingProjectRisk.setVersion(projectRisk.getVersion());
                }
                if (projectRisk.getUsetime() != null) {
                    existingProjectRisk.setUsetime(projectRisk.getUsetime());
                }
                if (projectRisk.getSystemlevel() != null) {
                    existingProjectRisk.setSystemlevel(projectRisk.getSystemlevel());
                }
                if (projectRisk.getRisklevel() != null) {
                    existingProjectRisk.setRisklevel(projectRisk.getRisklevel());
                }
                if (projectRisk.getLimitationtime() != null) {
                    existingProjectRisk.setLimitationtime(projectRisk.getLimitationtime());
                }
                if (projectRisk.getClosetype() != null) {
                    existingProjectRisk.setClosetype(projectRisk.getClosetype());
                }

                return existingProjectRisk;
            })
            .map(projectRiskRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectRisk> findAll() {
        log.debug("Request to get all ProjectRisks");
        return projectRiskRepository.findAll();
    }

    public Page<ProjectRisk> findAllWithEagerRelationships(Pageable pageable) {
        return projectRiskRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectRisk> findOne(String id) {
        log.debug("Request to get ProjectRisk : {}", id);
        return projectRiskRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ProjectRisk : {}", id);
        projectRiskRepository.deleteById(id);
    }
}
