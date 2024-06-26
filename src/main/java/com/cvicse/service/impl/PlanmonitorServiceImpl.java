package com.cvicse.service.impl;

import com.cvicse.domain.Planmonitor;
import com.cvicse.repository.PlanmonitorRepository;
import com.cvicse.service.PlanmonitorService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Planmonitor}.
 */
@Service
@Transactional
public class PlanmonitorServiceImpl implements PlanmonitorService {

    private final Logger log = LoggerFactory.getLogger(PlanmonitorServiceImpl.class);

    private final PlanmonitorRepository planmonitorRepository;

    public PlanmonitorServiceImpl(PlanmonitorRepository planmonitorRepository) {
        this.planmonitorRepository = planmonitorRepository;
    }

    @Override
    public Planmonitor save(Planmonitor planmonitor) {
        log.debug("Request to save Planmonitor : {}", planmonitor);
        return planmonitorRepository.save(planmonitor);
    }

    @Override
    public Planmonitor update(Planmonitor planmonitor) {
        log.debug("Request to update Planmonitor : {}", planmonitor);
        return planmonitorRepository.save(planmonitor);
    }

    @Override
    public Optional<Planmonitor> partialUpdate(Planmonitor planmonitor) {
        log.debug("Request to partially update Planmonitor : {}", planmonitor);

        return planmonitorRepository
            .findById(planmonitor.getId())
            .map(existingPlanmonitor -> {
                if (planmonitor.getMonth() != null) {
                    existingPlanmonitor.setMonth(planmonitor.getMonth());
                }
                if (planmonitor.getType() != null) {
                    existingPlanmonitor.setType(planmonitor.getType());
                }
                if (planmonitor.getYear() != null) {
                    existingPlanmonitor.setYear(planmonitor.getYear());
                }
                if (planmonitor.getSecretlevel() != null) {
                    existingPlanmonitor.setSecretlevel(planmonitor.getSecretlevel());
                }
                if (planmonitor.getStatus() != null) {
                    existingPlanmonitor.setStatus(planmonitor.getStatus());
                }

                return existingPlanmonitor;
            })
            .map(planmonitorRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Planmonitor> findAll() {
        log.debug("Request to get all Planmonitors");
        return planmonitorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Planmonitor> findOne(String id) {
        log.debug("Request to get Planmonitor : {}", id);
        return planmonitorRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Planmonitor : {}", id);
        planmonitorRepository.deleteById(id);
    }
}
