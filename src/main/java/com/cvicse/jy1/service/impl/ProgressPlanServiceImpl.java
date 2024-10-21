package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.ProgressPlan;
import com.cvicse.jy1.repository.ProgressPlanRepository;
import com.cvicse.jy1.service.ProgressPlanService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.ProgressPlan}.
 */
@Service
@Transactional
public class ProgressPlanServiceImpl implements ProgressPlanService {

    private static final Logger log = LoggerFactory.getLogger(ProgressPlanServiceImpl.class);

    private final ProgressPlanRepository progressPlanRepository;

    public ProgressPlanServiceImpl(ProgressPlanRepository progressPlanRepository) {
        this.progressPlanRepository = progressPlanRepository;
    }

    @Override
    public ProgressPlan save(ProgressPlan progressPlan) {
        log.debug("Request to save ProgressPlan : {}", progressPlan);
        return progressPlanRepository.save(progressPlan);
    }

    @Override
    public ProgressPlan update(ProgressPlan progressPlan) {
        log.debug("Request to update ProgressPlan : {}", progressPlan);
        return progressPlanRepository.save(progressPlan);
    }

    @Override
    public Optional<ProgressPlan> partialUpdate(ProgressPlan progressPlan) {
        log.debug("Request to partially update ProgressPlan : {}", progressPlan);

        return progressPlanRepository
            .findById(progressPlan.getId())
            .map(existingProgressPlan -> {
                if (progressPlan.getPlanname() != null) {
                    existingProgressPlan.setPlanname(progressPlan.getPlanname());
                }
                if (progressPlan.getBelongproject() != null) {
                    existingProgressPlan.setBelongproject(progressPlan.getBelongproject());
                }
                if (progressPlan.getBelongplanid() != null) {
                    existingProgressPlan.setBelongplanid(progressPlan.getBelongplanid());
                }
                if (progressPlan.getSecretlevel() != null) {
                    existingProgressPlan.setSecretlevel(progressPlan.getSecretlevel());
                }
                if (progressPlan.getPlantype() != null) {
                    existingProgressPlan.setPlantype(progressPlan.getPlantype());
                }
                if (progressPlan.getPlanlevel() != null) {
                    existingProgressPlan.setPlanlevel(progressPlan.getPlanlevel());
                }
                if (progressPlan.getPlanstage() != null) {
                    existingProgressPlan.setPlanstage(progressPlan.getPlanstage());
                }
                if (progressPlan.getReadytime() != null) {
                    existingProgressPlan.setReadytime(progressPlan.getReadytime());
                }
                if (progressPlan.getDescription() != null) {
                    existingProgressPlan.setDescription(progressPlan.getDescription());
                }
                if (progressPlan.getDeliverables() != null) {
                    existingProgressPlan.setDeliverables(progressPlan.getDeliverables());
                }
                if (progressPlan.getPlanobjectives() != null) {
                    existingProgressPlan.setPlanobjectives(progressPlan.getPlanobjectives());
                }
                if (progressPlan.getPreplan() != null) {
                    existingProgressPlan.setPreplan(progressPlan.getPreplan());
                }
                if (progressPlan.getStarttime() != null) {
                    existingProgressPlan.setStarttime(progressPlan.getStarttime());
                }
                if (progressPlan.getEndtime() != null) {
                    existingProgressPlan.setEndtime(progressPlan.getEndtime());
                }
                if (progressPlan.getActualstarttime() != null) {
                    existingProgressPlan.setActualstarttime(progressPlan.getActualstarttime());
                }
                if (progressPlan.getActualendtime() != null) {
                    existingProgressPlan.setActualendtime(progressPlan.getActualendtime());
                }
                if (progressPlan.getProgress() != null) {
                    existingProgressPlan.setProgress(progressPlan.getProgress());
                }
                if (progressPlan.getProgresstype() != null) {
                    existingProgressPlan.setProgresstype(progressPlan.getProgresstype());
                }
                if (progressPlan.getIskey() != null) {
                    existingProgressPlan.setIskey(progressPlan.getIskey());
                }
                if (progressPlan.getStatus() != null) {
                    existingProgressPlan.setStatus(progressPlan.getStatus());
                }
                if (progressPlan.getAuditStatus() != null) {
                    existingProgressPlan.setAuditStatus(progressPlan.getAuditStatus());
                }
                if (progressPlan.getReturns() != null) {
                    existingProgressPlan.setReturns(progressPlan.getReturns());
                }
                if (progressPlan.getRemark() != null) {
                    existingProgressPlan.setRemark(progressPlan.getRemark());
                }

                return existingProgressPlan;
            })
            .map(progressPlanRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgressPlan> findAll() {
        log.debug("Request to get all ProgressPlans");
        return progressPlanRepository.findAll();
    }

    public Page<ProgressPlan> findAllWithEagerRelationships(Pageable pageable) {
        return progressPlanRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgressPlan> findAllWithEagerRelationships() {
        log.debug("Request to get all ProgressPlans with eager relationships");
        return progressPlanRepository.findAllWithEagerRelationships();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProgressPlan> findOne(String id) {
        log.debug("Request to get ProgressPlan : {}", id);
        return progressPlanRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ProgressPlan : {}", id);
        progressPlanRepository.deleteById(id);
    }
}
