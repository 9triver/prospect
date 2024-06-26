package com.cvicse.service.impl;

import com.cvicse.domain.OutsourcingPurchaseExecute;
import com.cvicse.repository.OutsourcingPurchaseExecuteRepository;
import com.cvicse.service.OutsourcingPurchaseExecuteService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.OutsourcingPurchaseExecute}.
 */
@Service
@Transactional
public class OutsourcingPurchaseExecuteServiceImpl implements OutsourcingPurchaseExecuteService {

    private final Logger log = LoggerFactory.getLogger(OutsourcingPurchaseExecuteServiceImpl.class);

    private final OutsourcingPurchaseExecuteRepository outsourcingPurchaseExecuteRepository;

    public OutsourcingPurchaseExecuteServiceImpl(OutsourcingPurchaseExecuteRepository outsourcingPurchaseExecuteRepository) {
        this.outsourcingPurchaseExecuteRepository = outsourcingPurchaseExecuteRepository;
    }

    @Override
    public OutsourcingPurchaseExecute save(OutsourcingPurchaseExecute outsourcingPurchaseExecute) {
        log.debug("Request to save OutsourcingPurchaseExecute : {}", outsourcingPurchaseExecute);
        return outsourcingPurchaseExecuteRepository.save(outsourcingPurchaseExecute);
    }

    @Override
    public OutsourcingPurchaseExecute update(OutsourcingPurchaseExecute outsourcingPurchaseExecute) {
        log.debug("Request to update OutsourcingPurchaseExecute : {}", outsourcingPurchaseExecute);
        return outsourcingPurchaseExecuteRepository.save(outsourcingPurchaseExecute);
    }

    @Override
    public Optional<OutsourcingPurchaseExecute> partialUpdate(OutsourcingPurchaseExecute outsourcingPurchaseExecute) {
        log.debug("Request to partially update OutsourcingPurchaseExecute : {}", outsourcingPurchaseExecute);

        return outsourcingPurchaseExecuteRepository
            .findById(outsourcingPurchaseExecute.getId())
            .map(existingOutsourcingPurchaseExecute -> {
                if (outsourcingPurchaseExecute.getMatarialname() != null) {
                    existingOutsourcingPurchaseExecute.setMatarialname(outsourcingPurchaseExecute.getMatarialname());
                }
                if (outsourcingPurchaseExecute.getPurchasingmethod() != null) {
                    existingOutsourcingPurchaseExecute.setPurchasingmethod(outsourcingPurchaseExecute.getPurchasingmethod());
                }
                if (outsourcingPurchaseExecute.getBudgit() != null) {
                    existingOutsourcingPurchaseExecute.setBudgit(outsourcingPurchaseExecute.getBudgit());
                }
                if (outsourcingPurchaseExecute.getNeedtime() != null) {
                    existingOutsourcingPurchaseExecute.setNeedtime(outsourcingPurchaseExecute.getNeedtime());
                }
                if (outsourcingPurchaseExecute.getPlanusetime() != null) {
                    existingOutsourcingPurchaseExecute.setPlanusetime(outsourcingPurchaseExecute.getPlanusetime());
                }
                if (outsourcingPurchaseExecute.getSupplierid() != null) {
                    existingOutsourcingPurchaseExecute.setSupplierid(outsourcingPurchaseExecute.getSupplierid());
                }
                if (outsourcingPurchaseExecute.getPrice() != null) {
                    existingOutsourcingPurchaseExecute.setPrice(outsourcingPurchaseExecute.getPrice());
                }
                if (outsourcingPurchaseExecute.getSecretlevel() != null) {
                    existingOutsourcingPurchaseExecute.setSecretlevel(outsourcingPurchaseExecute.getSecretlevel());
                }

                return existingOutsourcingPurchaseExecute;
            })
            .map(outsourcingPurchaseExecuteRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OutsourcingPurchaseExecute> findAll() {
        log.debug("Request to get all OutsourcingPurchaseExecutes");
        return outsourcingPurchaseExecuteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OutsourcingPurchaseExecute> findOne(String id) {
        log.debug("Request to get OutsourcingPurchaseExecute : {}", id);
        return outsourcingPurchaseExecuteRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete OutsourcingPurchaseExecute : {}", id);
        outsourcingPurchaseExecuteRepository.deleteById(id);
    }
}
