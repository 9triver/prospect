package com.cvicse.service.impl;

import com.cvicse.domain.OutsourcingPurchasePlan;
import com.cvicse.repository.OutsourcingPurchasePlanRepository;
import com.cvicse.service.OutsourcingPurchasePlanService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.OutsourcingPurchasePlan}.
 */
@Service
@Transactional
public class OutsourcingPurchasePlanServiceImpl implements OutsourcingPurchasePlanService {

    private final Logger log = LoggerFactory.getLogger(OutsourcingPurchasePlanServiceImpl.class);

    private final OutsourcingPurchasePlanRepository outsourcingPurchasePlanRepository;

    public OutsourcingPurchasePlanServiceImpl(OutsourcingPurchasePlanRepository outsourcingPurchasePlanRepository) {
        this.outsourcingPurchasePlanRepository = outsourcingPurchasePlanRepository;
    }

    @Override
    public OutsourcingPurchasePlan save(OutsourcingPurchasePlan outsourcingPurchasePlan) {
        log.debug("Request to save OutsourcingPurchasePlan : {}", outsourcingPurchasePlan);
        return outsourcingPurchasePlanRepository.save(outsourcingPurchasePlan);
    }

    @Override
    public OutsourcingPurchasePlan update(OutsourcingPurchasePlan outsourcingPurchasePlan) {
        log.debug("Request to update OutsourcingPurchasePlan : {}", outsourcingPurchasePlan);
        return outsourcingPurchasePlanRepository.save(outsourcingPurchasePlan);
    }

    @Override
    public Optional<OutsourcingPurchasePlan> partialUpdate(OutsourcingPurchasePlan outsourcingPurchasePlan) {
        log.debug("Request to partially update OutsourcingPurchasePlan : {}", outsourcingPurchasePlan);

        return outsourcingPurchasePlanRepository
            .findById(outsourcingPurchasePlan.getId())
            .map(existingOutsourcingPurchasePlan -> {
                if (outsourcingPurchasePlan.getMatarialname() != null) {
                    existingOutsourcingPurchasePlan.setMatarialname(outsourcingPurchasePlan.getMatarialname());
                }
                if (outsourcingPurchasePlan.getPurchasingmethod() != null) {
                    existingOutsourcingPurchasePlan.setPurchasingmethod(outsourcingPurchasePlan.getPurchasingmethod());
                }
                if (outsourcingPurchasePlan.getBudgit() != null) {
                    existingOutsourcingPurchasePlan.setBudgit(outsourcingPurchasePlan.getBudgit());
                }
                if (outsourcingPurchasePlan.getNeedtime() != null) {
                    existingOutsourcingPurchasePlan.setNeedtime(outsourcingPurchasePlan.getNeedtime());
                }
                if (outsourcingPurchasePlan.getPlanusetime() != null) {
                    existingOutsourcingPurchasePlan.setPlanusetime(outsourcingPurchasePlan.getPlanusetime());
                }
                if (outsourcingPurchasePlan.getSupplierid() != null) {
                    existingOutsourcingPurchasePlan.setSupplierid(outsourcingPurchasePlan.getSupplierid());
                }
                if (outsourcingPurchasePlan.getPrice() != null) {
                    existingOutsourcingPurchasePlan.setPrice(outsourcingPurchasePlan.getPrice());
                }
                if (outsourcingPurchasePlan.getSecretlevel() != null) {
                    existingOutsourcingPurchasePlan.setSecretlevel(outsourcingPurchasePlan.getSecretlevel());
                }
                if (outsourcingPurchasePlan.getAuditStatus() != null) {
                    existingOutsourcingPurchasePlan.setAuditStatus(outsourcingPurchasePlan.getAuditStatus());
                }

                return existingOutsourcingPurchasePlan;
            })
            .map(outsourcingPurchasePlanRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OutsourcingPurchasePlan> findAll() {
        log.debug("Request to get all OutsourcingPurchasePlans");
        return outsourcingPurchasePlanRepository.findAll();
    }

    /**
     *  Get all the outsourcingPurchasePlans where OutsourcingPurchaseExecute is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OutsourcingPurchasePlan> findAllWhereOutsourcingPurchaseExecuteIsNull() {
        log.debug("Request to get all outsourcingPurchasePlans where OutsourcingPurchaseExecute is null");
        return StreamSupport.stream(outsourcingPurchasePlanRepository.findAll().spliterator(), false)
            .filter(outsourcingPurchasePlan -> outsourcingPurchasePlan.getOutsourcingPurchaseExecute() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OutsourcingPurchasePlan> findOne(String id) {
        log.debug("Request to get OutsourcingPurchasePlan : {}", id);
        return outsourcingPurchasePlanRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete OutsourcingPurchasePlan : {}", id);
        outsourcingPurchasePlanRepository.deleteById(id);
    }
}
