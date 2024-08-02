package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.PlanReturns;
import com.cvicse.jy1.repository.PlanReturnsRepository;
import com.cvicse.jy1.service.PlanReturnsService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.PlanReturns}.
 */
@Service
@Transactional
public class PlanReturnsServiceImpl implements PlanReturnsService {

    private static final Logger log = LoggerFactory.getLogger(PlanReturnsServiceImpl.class);

    private final PlanReturnsRepository planReturnsRepository;

    public PlanReturnsServiceImpl(PlanReturnsRepository planReturnsRepository) {
        this.planReturnsRepository = planReturnsRepository;
    }

    @Override
    public PlanReturns save(PlanReturns planReturns) {
        log.debug("Request to save PlanReturns : {}", planReturns);
        return planReturnsRepository.save(planReturns);
    }

    @Override
    public PlanReturns update(PlanReturns planReturns) {
        log.debug("Request to update PlanReturns : {}", planReturns);
        return planReturnsRepository.save(planReturns);
    }

    @Override
    public Optional<PlanReturns> partialUpdate(PlanReturns planReturns) {
        log.debug("Request to partially update PlanReturns : {}", planReturns);

        return planReturnsRepository
            .findById(planReturns.getId())
            .map(existingPlanReturns -> {
                if (planReturns.getPlanreturnsname() != null) {
                    existingPlanReturns.setPlanreturnsname(planReturns.getPlanreturnsname());
                }
                if (planReturns.getPlantype() != null) {
                    existingPlanReturns.setPlantype(planReturns.getPlantype());
                }
                if (planReturns.getPlanlevel() != null) {
                    existingPlanReturns.setPlanlevel(planReturns.getPlanlevel());
                }
                if (planReturns.getDescription() != null) {
                    existingPlanReturns.setDescription(planReturns.getDescription());
                }
                if (planReturns.getActualstarttime() != null) {
                    existingPlanReturns.setActualstarttime(planReturns.getActualstarttime());
                }
                if (planReturns.getActualendtime() != null) {
                    existingPlanReturns.setActualendtime(planReturns.getActualendtime());
                }
                if (planReturns.getDeliverables() != null) {
                    existingPlanReturns.setDeliverables(planReturns.getDeliverables());
                }
                if (planReturns.getProgress() != null) {
                    existingPlanReturns.setProgress(planReturns.getProgress());
                }
                if (planReturns.getStatus() != null) {
                    existingPlanReturns.setStatus(planReturns.getStatus());
                }
                if (planReturns.getImpactanalysis() != null) {
                    existingPlanReturns.setImpactanalysis(planReturns.getImpactanalysis());
                }
                if (planReturns.getReturnstime() != null) {
                    existingPlanReturns.setReturnstime(planReturns.getReturnstime());
                }
                if (planReturns.getRejectionreason() != null) {
                    existingPlanReturns.setRejectionreason(planReturns.getRejectionreason());
                }
                if (planReturns.getReturnsstatus() != null) {
                    existingPlanReturns.setReturnsstatus(planReturns.getReturnsstatus());
                }

                return existingPlanReturns;
            })
            .map(planReturnsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlanReturns> findAll() {
        log.debug("Request to get all PlanReturns");
        return planReturnsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PlanReturns> findOne(String id) {
        log.debug("Request to get PlanReturns : {}", id);
        return planReturnsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete PlanReturns : {}", id);
        planReturnsRepository.deleteById(id);
    }
}
