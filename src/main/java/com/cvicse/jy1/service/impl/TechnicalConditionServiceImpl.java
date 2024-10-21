package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.TechnicalCondition;
import com.cvicse.jy1.repository.TechnicalConditionRepository;
import com.cvicse.jy1.service.TechnicalConditionService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.TechnicalCondition}.
 */
@Service
@Transactional
public class TechnicalConditionServiceImpl implements TechnicalConditionService {

    private static final Logger log = LoggerFactory.getLogger(TechnicalConditionServiceImpl.class);

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
                if (technicalCondition.getWorkbagid() != null) {
                    existingTechnicalCondition.setWorkbagid(technicalCondition.getWorkbagid());
                }
                if (technicalCondition.getBelongwbsid() != null) {
                    existingTechnicalCondition.setBelongwbsid(technicalCondition.getBelongwbsid());
                }
                if (technicalCondition.getOutsourcingcontractid() != null) {
                    existingTechnicalCondition.setOutsourcingcontractid(technicalCondition.getOutsourcingcontractid());
                }
                if (technicalCondition.getTechnicalid() != null) {
                    existingTechnicalCondition.setTechnicalid(technicalCondition.getTechnicalid());
                }
                if (technicalCondition.getTechnicalname() != null) {
                    existingTechnicalCondition.setTechnicalname(technicalCondition.getTechnicalname());
                }
                if (technicalCondition.getChangedfilename() != null) {
                    existingTechnicalCondition.setChangedfilename(technicalCondition.getChangedfilename());
                }
                if (technicalCondition.getApplicant() != null) {
                    existingTechnicalCondition.setApplicant(technicalCondition.getApplicant());
                }
                if (technicalCondition.getApplicationdate() != null) {
                    existingTechnicalCondition.setApplicationdate(technicalCondition.getApplicationdate());
                }
                if (technicalCondition.getChangedreason() != null) {
                    existingTechnicalCondition.setChangedreason(technicalCondition.getChangedreason());
                }
                if (technicalCondition.getChangedbefore() != null) {
                    existingTechnicalCondition.setChangedbefore(technicalCondition.getChangedbefore());
                }
                if (technicalCondition.getChangedafter() != null) {
                    existingTechnicalCondition.setChangedafter(technicalCondition.getChangedafter());
                }
                if (technicalCondition.getDistributionrange() != null) {
                    existingTechnicalCondition.setDistributionrange(technicalCondition.getDistributionrange());
                }
                if (technicalCondition.getRemarks() != null) {
                    existingTechnicalCondition.setRemarks(technicalCondition.getRemarks());
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
    public Optional<TechnicalCondition> findOne(Integer id) {
        log.debug("Request to get TechnicalCondition : {}", id);
        return technicalConditionRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete TechnicalCondition : {}", id);
        technicalConditionRepository.deleteById(id);
    }
}
