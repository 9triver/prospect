package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.QualityPlan;
import com.cvicse.jy1.repository.QualityPlanRepository;
import com.cvicse.jy1.service.QualityPlanService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.QualityPlan}.
 */
@Service
@Transactional
public class QualityPlanServiceImpl implements QualityPlanService {

    private static final Logger log = LoggerFactory.getLogger(QualityPlanServiceImpl.class);

    private final QualityPlanRepository qualityPlanRepository;

    public QualityPlanServiceImpl(QualityPlanRepository qualityPlanRepository) {
        this.qualityPlanRepository = qualityPlanRepository;
    }

    @Override
    public QualityPlan save(QualityPlan qualityPlan) {
        log.debug("Request to save QualityPlan : {}", qualityPlan);
        return qualityPlanRepository.save(qualityPlan);
    }

    @Override
    public QualityPlan update(QualityPlan qualityPlan) {
        log.debug("Request to update QualityPlan : {}", qualityPlan);
        return qualityPlanRepository.save(qualityPlan);
    }

    @Override
    public Optional<QualityPlan> partialUpdate(QualityPlan qualityPlan) {
        log.debug("Request to partially update QualityPlan : {}", qualityPlan);

        return qualityPlanRepository
            .findById(qualityPlan.getId())
            .map(existingQualityPlan -> {
                if (qualityPlan.getName() != null) {
                    existingQualityPlan.setName(qualityPlan.getName());
                }
                if (qualityPlan.getQualitytype() != null) {
                    existingQualityPlan.setQualitytype(qualityPlan.getQualitytype());
                }
                if (qualityPlan.getSecretlevel() != null) {
                    existingQualityPlan.setSecretlevel(qualityPlan.getSecretlevel());
                }
                if (qualityPlan.getWbsid() != null) {
                    existingQualityPlan.setWbsid(qualityPlan.getWbsid());
                }
                if (qualityPlan.getWorkbagid() != null) {
                    existingQualityPlan.setWorkbagid(qualityPlan.getWorkbagid());
                }
                if (qualityPlan.getFileversion() != null) {
                    existingQualityPlan.setFileversion(qualityPlan.getFileversion());
                }
                if (qualityPlan.getAuthor() != null) {
                    existingQualityPlan.setAuthor(qualityPlan.getAuthor());
                }
                if (qualityPlan.getAttachment() != null) {
                    existingQualityPlan.setAttachment(qualityPlan.getAttachment());
                }

                return existingQualityPlan;
            })
            .map(qualityPlanRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QualityPlan> findAll() {
        log.debug("Request to get all QualityPlans");
        return qualityPlanRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QualityPlan> findOne(String id) {
        log.debug("Request to get QualityPlan : {}", id);
        return qualityPlanRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete QualityPlan : {}", id);
        qualityPlanRepository.deleteById(id);
    }
}
