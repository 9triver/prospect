package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.ProjectRisk;
import com.cvicse.jy1.repository.ProjectRiskRepository;
import com.cvicse.jy1.service.ProjectRiskService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
                if (projectRisk.getName() != null) {
                    existingProjectRisk.setName(projectRisk.getName());
                }
                if (projectRisk.getRiskcontent() != null) {
                    existingProjectRisk.setRiskcontent(projectRisk.getRiskcontent());
                }
                if (projectRisk.getIdentificationtime() != null) {
                    existingProjectRisk.setIdentificationtime(projectRisk.getIdentificationtime());
                }
                if (projectRisk.getRiskreason() != null) {
                    existingProjectRisk.setRiskreason(projectRisk.getRiskreason());
                }
                if (projectRisk.getImportantrange() != null) {
                    existingProjectRisk.setImportantrange(projectRisk.getImportantrange());
                }
                if (projectRisk.getMeasuresandtimelimit() != null) {
                    existingProjectRisk.setMeasuresandtimelimit(projectRisk.getMeasuresandtimelimit());
                }
                if (projectRisk.getConditions() != null) {
                    existingProjectRisk.setConditions(projectRisk.getConditions());
                }
                if (projectRisk.getClosedloopindicator() != null) {
                    existingProjectRisk.setClosedloopindicator(projectRisk.getClosedloopindicator());
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

    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectRisk> findOne(Integer id) {
        log.debug("Request to get ProjectRisk : {}", id);
        return projectRiskRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete ProjectRisk : {}", id);
        projectRiskRepository.deleteById(id);
    }
}
