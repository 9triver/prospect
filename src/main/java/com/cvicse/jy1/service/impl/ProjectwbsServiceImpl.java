package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.Projectwbs;
import com.cvicse.jy1.repository.ProjectwbsRepository;
import com.cvicse.jy1.service.ProjectwbsService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.Projectwbs}.
 */
@Service
@Transactional
public class ProjectwbsServiceImpl implements ProjectwbsService {

    private static final Logger log = LoggerFactory.getLogger(ProjectwbsServiceImpl.class);

    private final ProjectwbsRepository projectwbsRepository;

    public ProjectwbsServiceImpl(ProjectwbsRepository projectwbsRepository) {
        this.projectwbsRepository = projectwbsRepository;
    }

    @Override
    public Projectwbs save(Projectwbs projectwbs) {
        log.debug("Request to save Projectwbs : {}", projectwbs);
        return projectwbsRepository.save(projectwbs);
    }

    @Override
    public Projectwbs update(Projectwbs projectwbs) {
        log.debug("Request to update Projectwbs : {}", projectwbs);
        return projectwbsRepository.save(projectwbs);
    }

    @Override
    public Optional<Projectwbs> partialUpdate(Projectwbs projectwbs) {
        log.debug("Request to partially update Projectwbs : {}", projectwbs);

        return projectwbsRepository
            .findById(projectwbs.getId())
            .map(existingProjectwbs -> {
                if (projectwbs.getWbsname() != null) {
                    existingProjectwbs.setWbsname(projectwbs.getWbsname());
                }
                if (projectwbs.getParentwbsid() != null) {
                    existingProjectwbs.setParentwbsid(projectwbs.getParentwbsid());
                }
                if (projectwbs.getPbsid() != null) {
                    existingProjectwbs.setPbsid(projectwbs.getPbsid());
                }
                if (projectwbs.getDescription() != null) {
                    existingProjectwbs.setDescription(projectwbs.getDescription());
                }
                if (projectwbs.getBelongfront() != null) {
                    existingProjectwbs.setBelongfront(projectwbs.getBelongfront());
                }
                if (projectwbs.getStarttime() != null) {
                    existingProjectwbs.setStarttime(projectwbs.getStarttime());
                }
                if (projectwbs.getEndtime() != null) {
                    existingProjectwbs.setEndtime(projectwbs.getEndtime());
                }
                if (projectwbs.getProgress() != null) {
                    existingProjectwbs.setProgress(projectwbs.getProgress());
                }
                if (projectwbs.getType() != null) {
                    existingProjectwbs.setType(projectwbs.getType());
                }
                if (projectwbs.getPriorty() != null) {
                    existingProjectwbs.setPriorty(projectwbs.getPriorty());
                }
                if (projectwbs.getSecretlevel() != null) {
                    existingProjectwbs.setSecretlevel(projectwbs.getSecretlevel());
                }
                if (projectwbs.getDeliverables() != null) {
                    existingProjectwbs.setDeliverables(projectwbs.getDeliverables());
                }
                if (projectwbs.getStatus() != null) {
                    existingProjectwbs.setStatus(projectwbs.getStatus());
                }
                if (projectwbs.getAuditStatus() != null) {
                    existingProjectwbs.setAuditStatus(projectwbs.getAuditStatus());
                }
                if (projectwbs.getWorkbag() != null) {
                    existingProjectwbs.setWorkbag(projectwbs.getWorkbag());
                }

                return existingProjectwbs;
            })
            .map(projectwbsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Projectwbs> findAll() {
        log.debug("Request to get all Projectwbs");
        return projectwbsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Projectwbs> findOne(String id) {
        log.debug("Request to get Projectwbs : {}", id);
        return projectwbsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Projectwbs : {}", id);
        projectwbsRepository.deleteById(id);
    }
}
