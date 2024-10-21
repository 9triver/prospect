package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.ProjectBudget;
import com.cvicse.jy1.repository.ProjectBudgetRepository;
import com.cvicse.jy1.service.ProjectBudgetService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.ProjectBudget}.
 */
@Service
@Transactional
public class ProjectBudgetServiceImpl implements ProjectBudgetService {

    private static final Logger log = LoggerFactory.getLogger(ProjectBudgetServiceImpl.class);

    private final ProjectBudgetRepository projectBudgetRepository;

    public ProjectBudgetServiceImpl(ProjectBudgetRepository projectBudgetRepository) {
        this.projectBudgetRepository = projectBudgetRepository;
    }

    @Override
    public ProjectBudget save(ProjectBudget projectBudget) {
        log.debug("Request to save ProjectBudget : {}", projectBudget);
        return projectBudgetRepository.save(projectBudget);
    }

    @Override
    public ProjectBudget update(ProjectBudget projectBudget) {
        log.debug("Request to update ProjectBudget : {}", projectBudget);
        return projectBudgetRepository.save(projectBudget);
    }

    @Override
    public Optional<ProjectBudget> partialUpdate(ProjectBudget projectBudget) {
        log.debug("Request to partially update ProjectBudget : {}", projectBudget);

        return projectBudgetRepository
            .findById(projectBudget.getId())
            .map(existingProjectBudget -> {
                if (projectBudget.getWbsid() != null) {
                    existingProjectBudget.setWbsid(projectBudget.getWbsid());
                }
                if (projectBudget.getWbsname() != null) {
                    existingProjectBudget.setWbsname(projectBudget.getWbsname());
                }
                if (projectBudget.getParentwbsid() != null) {
                    existingProjectBudget.setParentwbsid(projectBudget.getParentwbsid());
                }
                if (projectBudget.getSubjectid() != null) {
                    existingProjectBudget.setSubjectid(projectBudget.getSubjectid());
                }
                if (projectBudget.getSubjectname() != null) {
                    existingProjectBudget.setSubjectname(projectBudget.getSubjectname());
                }
                if (projectBudget.getContractid() != null) {
                    existingProjectBudget.setContractid(projectBudget.getContractid());
                }
                if (projectBudget.getContractname() != null) {
                    existingProjectBudget.setContractname(projectBudget.getContractname());
                }
                if (projectBudget.getYear() != null) {
                    existingProjectBudget.setYear(projectBudget.getYear());
                }
                if (projectBudget.getAuxiliaryitem() != null) {
                    existingProjectBudget.setAuxiliaryitem(projectBudget.getAuxiliaryitem());
                }
                if (projectBudget.getUnit() != null) {
                    existingProjectBudget.setUnit(projectBudget.getUnit());
                }
                if (projectBudget.getNumber() != null) {
                    existingProjectBudget.setNumber(projectBudget.getNumber());
                }
                if (projectBudget.getUnitprice() != null) {
                    existingProjectBudget.setUnitprice(projectBudget.getUnitprice());
                }
                if (projectBudget.getBudgetamount() != null) {
                    existingProjectBudget.setBudgetamount(projectBudget.getBudgetamount());
                }
                if (projectBudget.getEstimatedamount() != null) {
                    existingProjectBudget.setEstimatedamount(projectBudget.getEstimatedamount());
                }
                if (projectBudget.getImplementedamount() != null) {
                    existingProjectBudget.setImplementedamount(projectBudget.getImplementedamount());
                }
                if (projectBudget.getDifference() != null) {
                    existingProjectBudget.setDifference(projectBudget.getDifference());
                }
                if (projectBudget.getRemark() != null) {
                    existingProjectBudget.setRemark(projectBudget.getRemark());
                }

                return existingProjectBudget;
            })
            .map(projectBudgetRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectBudget> findAll() {
        log.debug("Request to get all ProjectBudgets");
        return projectBudgetRepository.findAll();
    }

    public Page<ProjectBudget> findAllWithEagerRelationships(Pageable pageable) {
        return projectBudgetRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectBudget> findOne(Long id) {
        log.debug("Request to get ProjectBudget : {}", id);
        return projectBudgetRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProjectBudget : {}", id);
        projectBudgetRepository.deleteById(id);
    }
}
