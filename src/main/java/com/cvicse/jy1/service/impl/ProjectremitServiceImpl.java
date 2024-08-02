package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.Projectremit;
import com.cvicse.jy1.repository.ProjectremitRepository;
import com.cvicse.jy1.service.ProjectremitService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.Projectremit}.
 */
@Service
@Transactional
public class ProjectremitServiceImpl implements ProjectremitService {

    private static final Logger log = LoggerFactory.getLogger(ProjectremitServiceImpl.class);

    private final ProjectremitRepository projectremitRepository;

    public ProjectremitServiceImpl(ProjectremitRepository projectremitRepository) {
        this.projectremitRepository = projectremitRepository;
    }

    @Override
    public Projectremit save(Projectremit projectremit) {
        log.debug("Request to save Projectremit : {}", projectremit);
        return projectremitRepository.save(projectremit);
    }

    @Override
    public Projectremit update(Projectremit projectremit) {
        log.debug("Request to update Projectremit : {}", projectremit);
        return projectremitRepository.save(projectremit);
    }

    @Override
    public Optional<Projectremit> partialUpdate(Projectremit projectremit) {
        log.debug("Request to partially update Projectremit : {}", projectremit);

        return projectremitRepository
            .findById(projectremit.getId())
            .map(existingProjectremit -> {
                if (projectremit.getRemit() != null) {
                    existingProjectremit.setRemit(projectremit.getRemit());
                }
                if (projectremit.getOutdeportment() != null) {
                    existingProjectremit.setOutdeportment(projectremit.getOutdeportment());
                }
                if (projectremit.getIndeportment() != null) {
                    existingProjectremit.setIndeportment(projectremit.getIndeportment());
                }
                if (projectremit.getProjectname() != null) {
                    existingProjectremit.setProjectname(projectremit.getProjectname());
                }
                if (projectremit.getDeportment() != null) {
                    existingProjectremit.setDeportment(projectremit.getDeportment());
                }
                if (projectremit.getProjectleader() != null) {
                    existingProjectremit.setProjectleader(projectremit.getProjectleader());
                }
                if (projectremit.getSecretlevel() != null) {
                    existingProjectremit.setSecretlevel(projectremit.getSecretlevel());
                }
                if (projectremit.getAuditStatus() != null) {
                    existingProjectremit.setAuditStatus(projectremit.getAuditStatus());
                }

                return existingProjectremit;
            })
            .map(projectremitRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Projectremit> findAll() {
        log.debug("Request to get all Projectremits");
        return projectremitRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Projectremit> findOne(String id) {
        log.debug("Request to get Projectremit : {}", id);
        return projectremitRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Projectremit : {}", id);
        projectremitRepository.deleteById(id);
    }
}
