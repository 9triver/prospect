package com.cvicse.service.impl;

import com.cvicse.domain.EvaluationCriteria;
import com.cvicse.repository.EvaluationCriteriaRepository;
import com.cvicse.service.EvaluationCriteriaService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.EvaluationCriteria}.
 */
@Service
@Transactional
public class EvaluationCriteriaServiceImpl implements EvaluationCriteriaService {

    private final Logger log = LoggerFactory.getLogger(EvaluationCriteriaServiceImpl.class);

    private final EvaluationCriteriaRepository evaluationCriteriaRepository;

    public EvaluationCriteriaServiceImpl(EvaluationCriteriaRepository evaluationCriteriaRepository) {
        this.evaluationCriteriaRepository = evaluationCriteriaRepository;
    }

    @Override
    public EvaluationCriteria save(EvaluationCriteria evaluationCriteria) {
        log.debug("Request to save EvaluationCriteria : {}", evaluationCriteria);
        return evaluationCriteriaRepository.save(evaluationCriteria);
    }

    @Override
    public EvaluationCriteria update(EvaluationCriteria evaluationCriteria) {
        log.debug("Request to update EvaluationCriteria : {}", evaluationCriteria);
        return evaluationCriteriaRepository.save(evaluationCriteria);
    }

    @Override
    public Optional<EvaluationCriteria> partialUpdate(EvaluationCriteria evaluationCriteria) {
        log.debug("Request to partially update EvaluationCriteria : {}", evaluationCriteria);

        return evaluationCriteriaRepository
            .findById(evaluationCriteria.getId())
            .map(existingEvaluationCriteria -> {
                if (evaluationCriteria.getStandardtype() != null) {
                    existingEvaluationCriteria.setStandardtype(evaluationCriteria.getStandardtype());
                }
                if (evaluationCriteria.getStandardname() != null) {
                    existingEvaluationCriteria.setStandardname(evaluationCriteria.getStandardname());
                }
                if (evaluationCriteria.getMark() != null) {
                    existingEvaluationCriteria.setMark(evaluationCriteria.getMark());
                }

                return existingEvaluationCriteria;
            })
            .map(evaluationCriteriaRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EvaluationCriteria> findAll() {
        log.debug("Request to get all EvaluationCriteria");
        return evaluationCriteriaRepository.findAll();
    }

    /**
     *  Get all the evaluationCriteria where ManagementCapacityEvaluation is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EvaluationCriteria> findAllWhereManagementCapacityEvaluationIsNull() {
        log.debug("Request to get all evaluationCriteria where ManagementCapacityEvaluation is null");
        return StreamSupport.stream(evaluationCriteriaRepository.findAll().spliterator(), false)
            .filter(evaluationCriteria -> evaluationCriteria.getManagementCapacityEvaluation() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EvaluationCriteria> findOne(String id) {
        log.debug("Request to get EvaluationCriteria : {}", id);
        return evaluationCriteriaRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete EvaluationCriteria : {}", id);
        evaluationCriteriaRepository.deleteById(id);
    }
}
