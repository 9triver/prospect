package com.cvicse.service.impl;

import com.cvicse.domain.Planstrategy;
import com.cvicse.repository.PlanstrategyRepository;
import com.cvicse.service.PlanstrategyService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Planstrategy}.
 */
@Service
@Transactional
public class PlanstrategyServiceImpl implements PlanstrategyService {

    private final Logger log = LoggerFactory.getLogger(PlanstrategyServiceImpl.class);

    private final PlanstrategyRepository planstrategyRepository;

    public PlanstrategyServiceImpl(PlanstrategyRepository planstrategyRepository) {
        this.planstrategyRepository = planstrategyRepository;
    }

    @Override
    public Planstrategy save(Planstrategy planstrategy) {
        log.debug("Request to save Planstrategy : {}", planstrategy);
        return planstrategyRepository.save(planstrategy);
    }

    @Override
    public Planstrategy update(Planstrategy planstrategy) {
        log.debug("Request to update Planstrategy : {}", planstrategy);
        return planstrategyRepository.save(planstrategy);
    }

    @Override
    public Optional<Planstrategy> partialUpdate(Planstrategy planstrategy) {
        log.debug("Request to partially update Planstrategy : {}", planstrategy);

        return planstrategyRepository
            .findById(planstrategy.getId())
            .map(existingPlanstrategy -> {
                if (planstrategy.getStrategyname() != null) {
                    existingPlanstrategy.setStrategyname(planstrategy.getStrategyname());
                }
                if (planstrategy.getNumber() != null) {
                    existingPlanstrategy.setNumber(planstrategy.getNumber());
                }
                if (planstrategy.getType() != null) {
                    existingPlanstrategy.setType(planstrategy.getType());
                }

                return existingPlanstrategy;
            })
            .map(planstrategyRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Planstrategy> findAll() {
        log.debug("Request to get all Planstrategies");
        return planstrategyRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Planstrategy> findOne(String id) {
        log.debug("Request to get Planstrategy : {}", id);
        return planstrategyRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Planstrategy : {}", id);
        planstrategyRepository.deleteById(id);
    }
}
