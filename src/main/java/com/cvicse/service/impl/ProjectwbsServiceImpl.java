package com.cvicse.service.impl;

import com.cvicse.domain.Projectwbs;
import com.cvicse.repository.ProjectwbsRepository;
import com.cvicse.service.ProjectwbsService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Projectwbs}.
 */
@Service
@Transactional
public class ProjectwbsServiceImpl implements ProjectwbsService {

    private final Logger log = LoggerFactory.getLogger(ProjectwbsServiceImpl.class);

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
                if (projectwbs.getProjectwbsname() != null) {
                    existingProjectwbs.setProjectwbsname(projectwbs.getProjectwbsname());
                }
                if (projectwbs.getWbsspare1() != null) {
                    existingProjectwbs.setWbsspare1(projectwbs.getWbsspare1());
                }
                if (projectwbs.getWbsspare2() != null) {
                    existingProjectwbs.setWbsspare2(projectwbs.getWbsspare2());
                }
                if (projectwbs.getWbsspare3() != null) {
                    existingProjectwbs.setWbsspare3(projectwbs.getWbsspare3());
                }
                if (projectwbs.getWbsspare4() != null) {
                    existingProjectwbs.setWbsspare4(projectwbs.getWbsspare4());
                }
                if (projectwbs.getWbsspare5() != null) {
                    existingProjectwbs.setWbsspare5(projectwbs.getWbsspare5());
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

    /**
     *  Get all the projectwbs where Project is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Projectwbs> findAllWhereProjectIsNull() {
        log.debug("Request to get all projectwbs where Project is null");
        return StreamSupport.stream(projectwbsRepository.findAll().spliterator(), false)
            .filter(projectwbs -> projectwbs.getProject() == null)
            .toList();
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
