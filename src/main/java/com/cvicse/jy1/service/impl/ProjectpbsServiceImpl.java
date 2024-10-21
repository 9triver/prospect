package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.Projectpbs;
import com.cvicse.jy1.repository.ProjectpbsRepository;
import com.cvicse.jy1.service.ProjectpbsService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.Projectpbs}.
 */
@Service
@Transactional
public class ProjectpbsServiceImpl implements ProjectpbsService {

    private static final Logger log = LoggerFactory.getLogger(ProjectpbsServiceImpl.class);

    private final ProjectpbsRepository projectpbsRepository;

    public ProjectpbsServiceImpl(ProjectpbsRepository projectpbsRepository) {
        this.projectpbsRepository = projectpbsRepository;
    }

    @Override
    public Projectpbs save(Projectpbs projectpbs) {
        log.debug("Request to save Projectpbs : {}", projectpbs);
        return projectpbsRepository.save(projectpbs);
    }

    @Override
    public Projectpbs update(Projectpbs projectpbs) {
        log.debug("Request to update Projectpbs : {}", projectpbs);
        return projectpbsRepository.save(projectpbs);
    }

    @Override
    public Optional<Projectpbs> partialUpdate(Projectpbs projectpbs) {
        log.debug("Request to partially update Projectpbs : {}", projectpbs);

        return projectpbsRepository
            .findById(projectpbs.getId())
            .map(existingProjectpbs -> {
                if (projectpbs.getPbsname() != null) {
                    existingProjectpbs.setPbsname(projectpbs.getPbsname());
                }
                if (projectpbs.getParentpbsid() != null) {
                    existingProjectpbs.setParentpbsid(projectpbs.getParentpbsid());
                }
                if (projectpbs.getSecretlevel() != null) {
                    existingProjectpbs.setSecretlevel(projectpbs.getSecretlevel());
                }
                if (projectpbs.getStarttime() != null) {
                    existingProjectpbs.setStarttime(projectpbs.getStarttime());
                }
                if (projectpbs.getEndtime() != null) {
                    existingProjectpbs.setEndtime(projectpbs.getEndtime());
                }
                if (projectpbs.getProductlevel() != null) {
                    existingProjectpbs.setProductlevel(projectpbs.getProductlevel());
                }
                if (projectpbs.getIskey() != null) {
                    existingProjectpbs.setIskey(projectpbs.getIskey());
                }
                if (projectpbs.getIsimportant() != null) {
                    existingProjectpbs.setIsimportant(projectpbs.getIsimportant());
                }
                if (projectpbs.getDescription() != null) {
                    existingProjectpbs.setDescription(projectpbs.getDescription());
                }
                if (projectpbs.getProgress() != null) {
                    existingProjectpbs.setProgress(projectpbs.getProgress());
                }
                if (projectpbs.getType() != null) {
                    existingProjectpbs.setType(projectpbs.getType());
                }
                if (projectpbs.getPriorty() != null) {
                    existingProjectpbs.setPriorty(projectpbs.getPriorty());
                }
                if (projectpbs.getWbsid() != null) {
                    existingProjectpbs.setWbsid(projectpbs.getWbsid());
                }
                if (projectpbs.getStatus() != null) {
                    existingProjectpbs.setStatus(projectpbs.getStatus());
                }
                if (projectpbs.getAuditStatus() != null) {
                    existingProjectpbs.setAuditStatus(projectpbs.getAuditStatus());
                }

                return existingProjectpbs;
            })
            .map(projectpbsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Projectpbs> findAll() {
        log.debug("Request to get all Projectpbs");
        return projectpbsRepository.findAll();
    }

    public Page<Projectpbs> findAllWithEagerRelationships(Pageable pageable) {
        return projectpbsRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Projectpbs> findAllWithEagerRelationships() {
        log.debug("Request to get all Projectpbs with eager relationships");
        return projectpbsRepository.findAllWithEagerRelationships();
    }

    /**
     *  Get all the projectpbs where Projectwbs is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Projectpbs> findAllWhereProjectwbsIsNull() {
        log.debug("Request to get all projectpbs where Projectwbs is null");
        return StreamSupport.stream(projectpbsRepository.findAll().spliterator(), false)
            .filter(projectpbs -> projectpbs.getProjectwbs() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Projectpbs> findOne(String id) {
        log.debug("Request to get Projectpbs : {}", id);
        return projectpbsRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Projectpbs : {}", id);
        projectpbsRepository.deleteById(id);
    }
}
