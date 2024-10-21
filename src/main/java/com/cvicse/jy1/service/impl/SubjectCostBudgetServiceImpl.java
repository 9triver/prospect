package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.SubjectCostBudget;
import com.cvicse.jy1.repository.SubjectCostBudgetRepository;
import com.cvicse.jy1.service.SubjectCostBudgetService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.SubjectCostBudget}.
 */
@Service
@Transactional
public class SubjectCostBudgetServiceImpl implements SubjectCostBudgetService {

    private static final Logger log = LoggerFactory.getLogger(SubjectCostBudgetServiceImpl.class);

    private final SubjectCostBudgetRepository subjectCostBudgetRepository;

    public SubjectCostBudgetServiceImpl(SubjectCostBudgetRepository subjectCostBudgetRepository) {
        this.subjectCostBudgetRepository = subjectCostBudgetRepository;
    }

    @Override
    public SubjectCostBudget save(SubjectCostBudget subjectCostBudget) {
        log.debug("Request to save SubjectCostBudget : {}", subjectCostBudget);
        return subjectCostBudgetRepository.save(subjectCostBudget);
    }

    @Override
    public SubjectCostBudget update(SubjectCostBudget subjectCostBudget) {
        log.debug("Request to update SubjectCostBudget : {}", subjectCostBudget);
        return subjectCostBudgetRepository.save(subjectCostBudget);
    }

    @Override
    public Optional<SubjectCostBudget> partialUpdate(SubjectCostBudget subjectCostBudget) {
        log.debug("Request to partially update SubjectCostBudget : {}", subjectCostBudget);

        return subjectCostBudgetRepository
            .findById(subjectCostBudget.getId())
            .map(existingSubjectCostBudget -> {
                if (subjectCostBudget.getContractid() != null) {
                    existingSubjectCostBudget.setContractid(subjectCostBudget.getContractid());
                }
                if (subjectCostBudget.getSubjectid() != null) {
                    existingSubjectCostBudget.setSubjectid(subjectCostBudget.getSubjectid());
                }
                if (subjectCostBudget.getSubjectname() != null) {
                    existingSubjectCostBudget.setSubjectname(subjectCostBudget.getSubjectname());
                }
                if (subjectCostBudget.getBudgetamount() != null) {
                    existingSubjectCostBudget.setBudgetamount(subjectCostBudget.getBudgetamount());
                }
                if (subjectCostBudget.getEstimatedamount() != null) {
                    existingSubjectCostBudget.setEstimatedamount(subjectCostBudget.getEstimatedamount());
                }
                if (subjectCostBudget.getImplementedamount() != null) {
                    existingSubjectCostBudget.setImplementedamount(subjectCostBudget.getImplementedamount());
                }
                if (subjectCostBudget.getDifference() != null) {
                    existingSubjectCostBudget.setDifference(subjectCostBudget.getDifference());
                }
                if (subjectCostBudget.getPercentage() != null) {
                    existingSubjectCostBudget.setPercentage(subjectCostBudget.getPercentage());
                }

                return existingSubjectCostBudget;
            })
            .map(subjectCostBudgetRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectCostBudget> findAll() {
        log.debug("Request to get all SubjectCostBudgets");
        return subjectCostBudgetRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SubjectCostBudget> findOne(Long id) {
        log.debug("Request to get SubjectCostBudget : {}", id);
        return subjectCostBudgetRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SubjectCostBudget : {}", id);
        subjectCostBudgetRepository.deleteById(id);
    }
}
