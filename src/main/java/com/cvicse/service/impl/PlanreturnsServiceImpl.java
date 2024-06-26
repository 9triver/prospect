package com.cvicse.service.impl;

import com.cvicse.domain.Planreturns;
import com.cvicse.repository.PlanreturnsRepository;
import com.cvicse.service.PlanreturnsService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Planreturns}.
 */
@Service
@Transactional
public class PlanreturnsServiceImpl implements PlanreturnsService {

    private final Logger log = LoggerFactory.getLogger(PlanreturnsServiceImpl.class);

    private final PlanreturnsRepository planreturnsRepository;

    public PlanreturnsServiceImpl(PlanreturnsRepository planreturnsRepository) {
        this.planreturnsRepository = planreturnsRepository;
    }

    @Override
    public Planreturns save(Planreturns planreturns) {
        log.debug("Request to save Planreturns : {}", planreturns);
        return planreturnsRepository.save(planreturns);
    }

    @Override
    public Planreturns update(Planreturns planreturns) {
        log.debug("Request to update Planreturns : {}", planreturns);
        return planreturnsRepository.save(planreturns);
    }

    @Override
    public Optional<Planreturns> partialUpdate(Planreturns planreturns) {
        log.debug("Request to partially update Planreturns : {}", planreturns);

        return planreturnsRepository
            .findById(planreturns.getId())
            .map(existingPlanreturns -> {
                if (planreturns.getPlanreturnsname() != null) {
                    existingPlanreturns.setPlanreturnsname(planreturns.getPlanreturnsname());
                }
                if (planreturns.getStarttime() != null) {
                    existingPlanreturns.setStarttime(planreturns.getStarttime());
                }
                if (planreturns.getEndtime() != null) {
                    existingPlanreturns.setEndtime(planreturns.getEndtime());
                }
                if (planreturns.getPlantype() != null) {
                    existingPlanreturns.setPlantype(planreturns.getPlantype());
                }
                if (planreturns.getReturnstime() != null) {
                    existingPlanreturns.setReturnstime(planreturns.getReturnstime());
                }
                if (planreturns.getReturnsstatus() != null) {
                    existingPlanreturns.setReturnsstatus(planreturns.getReturnsstatus());
                }

                return existingPlanreturns;
            })
            .map(planreturnsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Planreturns> findAll() {
        log.debug("Request to get all Planreturns");
        return planreturnsRepository.findAll();
    }

    /**
     *  Get all the planreturns where Planexecute is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Planreturns> findAllWherePlanexecuteIsNull() {
        log.debug("Request to get all planreturns where Planexecute is null");
        return StreamSupport.stream(planreturnsRepository.findAll().spliterator(), false)
            .filter(planreturns -> planreturns.getPlanexecute() == null)
            .toList();
    }

    /**
     *  Get all the planreturns where Progressplan is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Planreturns> findAllWhereProgressplanIsNull() {
        log.debug("Request to get all planreturns where Progressplan is null");
        return StreamSupport.stream(planreturnsRepository.findAll().spliterator(), false)
            .filter(planreturns -> planreturns.getProgressplan() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Planreturns> findOne(String id) {
        log.debug("Request to get Planreturns : {}", id);
        return planreturnsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Planreturns : {}", id);
        planreturnsRepository.deleteById(id);
    }
}
