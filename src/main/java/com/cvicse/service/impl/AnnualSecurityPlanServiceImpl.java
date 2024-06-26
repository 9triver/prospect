package com.cvicse.service.impl;

import com.cvicse.domain.AnnualSecurityPlan;
import com.cvicse.repository.AnnualSecurityPlanRepository;
import com.cvicse.service.AnnualSecurityPlanService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.AnnualSecurityPlan}.
 */
@Service
@Transactional
public class AnnualSecurityPlanServiceImpl implements AnnualSecurityPlanService {

    private final Logger log = LoggerFactory.getLogger(AnnualSecurityPlanServiceImpl.class);

    private final AnnualSecurityPlanRepository annualSecurityPlanRepository;

    public AnnualSecurityPlanServiceImpl(AnnualSecurityPlanRepository annualSecurityPlanRepository) {
        this.annualSecurityPlanRepository = annualSecurityPlanRepository;
    }

    @Override
    public AnnualSecurityPlan save(AnnualSecurityPlan annualSecurityPlan) {
        log.debug("Request to save AnnualSecurityPlan : {}", annualSecurityPlan);
        return annualSecurityPlanRepository.save(annualSecurityPlan);
    }

    @Override
    public AnnualSecurityPlan update(AnnualSecurityPlan annualSecurityPlan) {
        log.debug("Request to update AnnualSecurityPlan : {}", annualSecurityPlan);
        return annualSecurityPlanRepository.save(annualSecurityPlan);
    }

    @Override
    public Optional<AnnualSecurityPlan> partialUpdate(AnnualSecurityPlan annualSecurityPlan) {
        log.debug("Request to partially update AnnualSecurityPlan : {}", annualSecurityPlan);

        return annualSecurityPlanRepository
            .findById(annualSecurityPlan.getId())
            .map(existingAnnualSecurityPlan -> {
                if (annualSecurityPlan.getSecurityplanname() != null) {
                    existingAnnualSecurityPlan.setSecurityplanname(annualSecurityPlan.getSecurityplanname());
                }
                if (annualSecurityPlan.getReleasetime() != null) {
                    existingAnnualSecurityPlan.setReleasetime(annualSecurityPlan.getReleasetime());
                }
                if (annualSecurityPlan.getCreatetime() != null) {
                    existingAnnualSecurityPlan.setCreatetime(annualSecurityPlan.getCreatetime());
                }
                if (annualSecurityPlan.getCreatorname() != null) {
                    existingAnnualSecurityPlan.setCreatorname(annualSecurityPlan.getCreatorname());
                }
                if (annualSecurityPlan.getAuditStatus() != null) {
                    existingAnnualSecurityPlan.setAuditStatus(annualSecurityPlan.getAuditStatus());
                }
                if (annualSecurityPlan.getVersion() != null) {
                    existingAnnualSecurityPlan.setVersion(annualSecurityPlan.getVersion());
                }

                return existingAnnualSecurityPlan;
            })
            .map(annualSecurityPlanRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnnualSecurityPlan> findAll() {
        log.debug("Request to get all AnnualSecurityPlans");
        return annualSecurityPlanRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AnnualSecurityPlan> findOne(String id) {
        log.debug("Request to get AnnualSecurityPlan : {}", id);
        return annualSecurityPlanRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete AnnualSecurityPlan : {}", id);
        annualSecurityPlanRepository.deleteById(id);
    }
}
