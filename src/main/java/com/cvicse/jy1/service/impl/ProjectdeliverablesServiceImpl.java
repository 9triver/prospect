package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.Projectdeliverables;
import com.cvicse.jy1.repository.ProjectdeliverablesRepository;
import com.cvicse.jy1.service.ProjectdeliverablesService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.Projectdeliverables}.
 */
@Service
@Transactional
public class ProjectdeliverablesServiceImpl implements ProjectdeliverablesService {

    private static final Logger log = LoggerFactory.getLogger(ProjectdeliverablesServiceImpl.class);

    private final ProjectdeliverablesRepository projectdeliverablesRepository;

    public ProjectdeliverablesServiceImpl(ProjectdeliverablesRepository projectdeliverablesRepository) {
        this.projectdeliverablesRepository = projectdeliverablesRepository;
    }

    @Override
    public Projectdeliverables save(Projectdeliverables projectdeliverables) {
        log.debug("Request to save Projectdeliverables : {}", projectdeliverables);
        return projectdeliverablesRepository.save(projectdeliverables);
    }

    @Override
    public Projectdeliverables update(Projectdeliverables projectdeliverables) {
        log.debug("Request to update Projectdeliverables : {}", projectdeliverables);
        return projectdeliverablesRepository.save(projectdeliverables);
    }

    @Override
    public Optional<Projectdeliverables> partialUpdate(Projectdeliverables projectdeliverables) {
        log.debug("Request to partially update Projectdeliverables : {}", projectdeliverables);

        return projectdeliverablesRepository
            .findById(projectdeliverables.getId())
            .map(existingProjectdeliverables -> {
                if (projectdeliverables.getParentcode() != null) {
                    existingProjectdeliverables.setParentcode(projectdeliverables.getParentcode());
                }
                if (projectdeliverables.getIsSubmit() != null) {
                    existingProjectdeliverables.setIsSubmit(projectdeliverables.getIsSubmit());
                }
                if (projectdeliverables.getStatus() != null) {
                    existingProjectdeliverables.setStatus(projectdeliverables.getStatus());
                }

                return existingProjectdeliverables;
            })
            .map(projectdeliverablesRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Projectdeliverables> findAll() {
        log.debug("Request to get all Projectdeliverables");
        return projectdeliverablesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Projectdeliverables> findOne(Integer id) {
        log.debug("Request to get Projectdeliverables : {}", id);
        return projectdeliverablesRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete Projectdeliverables : {}", id);
        projectdeliverablesRepository.deleteById(id);
    }
}
