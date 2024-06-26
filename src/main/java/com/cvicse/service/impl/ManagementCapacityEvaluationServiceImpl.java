package com.cvicse.service.impl;

import com.cvicse.domain.ManagementCapacityEvaluation;
import com.cvicse.repository.ManagementCapacityEvaluationRepository;
import com.cvicse.service.ManagementCapacityEvaluationService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.ManagementCapacityEvaluation}.
 */
@Service
@Transactional
public class ManagementCapacityEvaluationServiceImpl implements ManagementCapacityEvaluationService {

    private final Logger log = LoggerFactory.getLogger(ManagementCapacityEvaluationServiceImpl.class);

    private final ManagementCapacityEvaluationRepository managementCapacityEvaluationRepository;

    public ManagementCapacityEvaluationServiceImpl(ManagementCapacityEvaluationRepository managementCapacityEvaluationRepository) {
        this.managementCapacityEvaluationRepository = managementCapacityEvaluationRepository;
    }

    @Override
    public ManagementCapacityEvaluation save(ManagementCapacityEvaluation managementCapacityEvaluation) {
        log.debug("Request to save ManagementCapacityEvaluation : {}", managementCapacityEvaluation);
        return managementCapacityEvaluationRepository.save(managementCapacityEvaluation);
    }

    @Override
    public ManagementCapacityEvaluation update(ManagementCapacityEvaluation managementCapacityEvaluation) {
        log.debug("Request to update ManagementCapacityEvaluation : {}", managementCapacityEvaluation);
        return managementCapacityEvaluationRepository.save(managementCapacityEvaluation);
    }

    @Override
    public Optional<ManagementCapacityEvaluation> partialUpdate(ManagementCapacityEvaluation managementCapacityEvaluation) {
        log.debug("Request to partially update ManagementCapacityEvaluation : {}", managementCapacityEvaluation);

        return managementCapacityEvaluationRepository
            .findById(managementCapacityEvaluation.getId())
            .map(existingManagementCapacityEvaluation -> {
                if (managementCapacityEvaluation.getYear() != null) {
                    existingManagementCapacityEvaluation.setYear(managementCapacityEvaluation.getYear());
                }
                if (managementCapacityEvaluation.getDeprotment() != null) {
                    existingManagementCapacityEvaluation.setDeprotment(managementCapacityEvaluation.getDeprotment());
                }
                if (managementCapacityEvaluation.getCreatetime() != null) {
                    existingManagementCapacityEvaluation.setCreatetime(managementCapacityEvaluation.getCreatetime());
                }
                if (managementCapacityEvaluation.getStatus() != null) {
                    existingManagementCapacityEvaluation.setStatus(managementCapacityEvaluation.getStatus());
                }
                if (managementCapacityEvaluation.getTotalmark() != null) {
                    existingManagementCapacityEvaluation.setTotalmark(managementCapacityEvaluation.getTotalmark());
                }
                if (managementCapacityEvaluation.getMark() != null) {
                    existingManagementCapacityEvaluation.setMark(managementCapacityEvaluation.getMark());
                }
                if (managementCapacityEvaluation.getRatingpersonname() != null) {
                    existingManagementCapacityEvaluation.setRatingpersonname(managementCapacityEvaluation.getRatingpersonname());
                }
                if (managementCapacityEvaluation.getRatingdepartment() != null) {
                    existingManagementCapacityEvaluation.setRatingdepartment(managementCapacityEvaluation.getRatingdepartment());
                }
                if (managementCapacityEvaluation.getRatingtimg() != null) {
                    existingManagementCapacityEvaluation.setRatingtimg(managementCapacityEvaluation.getRatingtimg());
                }

                return existingManagementCapacityEvaluation;
            })
            .map(managementCapacityEvaluationRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ManagementCapacityEvaluation> findAll() {
        log.debug("Request to get all ManagementCapacityEvaluations");
        return managementCapacityEvaluationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ManagementCapacityEvaluation> findOne(String id) {
        log.debug("Request to get ManagementCapacityEvaluation : {}", id);
        return managementCapacityEvaluationRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ManagementCapacityEvaluation : {}", id);
        managementCapacityEvaluationRepository.deleteById(id);
    }
}
