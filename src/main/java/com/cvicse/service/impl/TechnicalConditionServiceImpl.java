package com.cvicse.service.impl;

import com.cvicse.domain.TechnicalCondition;
import com.cvicse.repository.TechnicalConditionRepository;
import com.cvicse.service.TechnicalConditionService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.TechnicalCondition}.
 */
@Service
@Transactional
public class TechnicalConditionServiceImpl implements TechnicalConditionService {

    private final Logger log = LoggerFactory.getLogger(TechnicalConditionServiceImpl.class);

    private final TechnicalConditionRepository technicalConditionRepository;

    public TechnicalConditionServiceImpl(TechnicalConditionRepository technicalConditionRepository) {
        this.technicalConditionRepository = technicalConditionRepository;
    }

    @Override
    public TechnicalCondition save(TechnicalCondition technicalCondition) {
        log.debug("Request to save TechnicalCondition : {}", technicalCondition);
        return technicalConditionRepository.save(technicalCondition);
    }

    @Override
    public TechnicalCondition update(TechnicalCondition technicalCondition) {
        log.debug("Request to update TechnicalCondition : {}", technicalCondition);
        return technicalConditionRepository.save(technicalCondition);
    }

    @Override
    public Optional<TechnicalCondition> partialUpdate(TechnicalCondition technicalCondition) {
        log.debug("Request to partially update TechnicalCondition : {}", technicalCondition);

        return technicalConditionRepository
            .findById(technicalCondition.getId())
            .map(existingTechnicalCondition -> {
                if (technicalCondition.getCaption() != null) {
                    existingTechnicalCondition.setCaption(technicalCondition.getCaption());
                }
                if (technicalCondition.getProjectname() != null) {
                    existingTechnicalCondition.setProjectname(technicalCondition.getProjectname());
                }
                if (technicalCondition.getDecumentid() != null) {
                    existingTechnicalCondition.setDecumentid(technicalCondition.getDecumentid());
                }
                if (technicalCondition.getClaimant() != null) {
                    existingTechnicalCondition.setClaimant(technicalCondition.getClaimant());
                }
                if (technicalCondition.getApplicant() != null) {
                    existingTechnicalCondition.setApplicant(technicalCondition.getApplicant());
                }
                if (technicalCondition.getApplicanttime() != null) {
                    existingTechnicalCondition.setApplicanttime(technicalCondition.getApplicanttime());
                }
                if (technicalCondition.getValidrange() != null) {
                    existingTechnicalCondition.setValidrange(technicalCondition.getValidrange());
                }
                if (technicalCondition.getCreatetime() != null) {
                    existingTechnicalCondition.setCreatetime(technicalCondition.getCreatetime());
                }
                if (technicalCondition.getAuditStatus() != null) {
                    existingTechnicalCondition.setAuditStatus(technicalCondition.getAuditStatus());
                }

                return existingTechnicalCondition;
            })
            .map(technicalConditionRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TechnicalCondition> findAll() {
        log.debug("Request to get all TechnicalConditions");
        return technicalConditionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TechnicalCondition> findOne(String id) {
        log.debug("Request to get TechnicalCondition : {}", id);
        return technicalConditionRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete TechnicalCondition : {}", id);
        technicalConditionRepository.deleteById(id);
    }
}
