package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.SporadicPurchasePayment;
import com.cvicse.jy1.repository.SporadicPurchasePaymentRepository;
import com.cvicse.jy1.service.SporadicPurchasePaymentService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.SporadicPurchasePayment}.
 */
@Service
@Transactional
public class SporadicPurchasePaymentServiceImpl implements SporadicPurchasePaymentService {

    private static final Logger log = LoggerFactory.getLogger(SporadicPurchasePaymentServiceImpl.class);

    private final SporadicPurchasePaymentRepository sporadicPurchasePaymentRepository;

    public SporadicPurchasePaymentServiceImpl(SporadicPurchasePaymentRepository sporadicPurchasePaymentRepository) {
        this.sporadicPurchasePaymentRepository = sporadicPurchasePaymentRepository;
    }

    @Override
    public SporadicPurchasePayment save(SporadicPurchasePayment sporadicPurchasePayment) {
        log.debug("Request to save SporadicPurchasePayment : {}", sporadicPurchasePayment);
        return sporadicPurchasePaymentRepository.save(sporadicPurchasePayment);
    }

    @Override
    public SporadicPurchasePayment update(SporadicPurchasePayment sporadicPurchasePayment) {
        log.debug("Request to update SporadicPurchasePayment : {}", sporadicPurchasePayment);
        return sporadicPurchasePaymentRepository.save(sporadicPurchasePayment);
    }

    @Override
    public Optional<SporadicPurchasePayment> partialUpdate(SporadicPurchasePayment sporadicPurchasePayment) {
        log.debug("Request to partially update SporadicPurchasePayment : {}", sporadicPurchasePayment);

        return sporadicPurchasePaymentRepository
            .findById(sporadicPurchasePayment.getId())
            .map(existingSporadicPurchasePayment -> {
                if (sporadicPurchasePayment.getPlanpaymentnode() != null) {
                    existingSporadicPurchasePayment.setPlanpaymentnode(sporadicPurchasePayment.getPlanpaymentnode());
                }
                if (sporadicPurchasePayment.getPlanpaymentamount() != null) {
                    existingSporadicPurchasePayment.setPlanpaymentamount(sporadicPurchasePayment.getPlanpaymentamount());
                }
                if (sporadicPurchasePayment.getActualpaymentamount() != null) {
                    existingSporadicPurchasePayment.setActualpaymentamount(sporadicPurchasePayment.getActualpaymentamount());
                }
                if (sporadicPurchasePayment.getPaymenttype() != null) {
                    existingSporadicPurchasePayment.setPaymenttype(sporadicPurchasePayment.getPaymenttype());
                }
                if (sporadicPurchasePayment.getFinancialvoucherid() != null) {
                    existingSporadicPurchasePayment.setFinancialvoucherid(sporadicPurchasePayment.getFinancialvoucherid());
                }

                return existingSporadicPurchasePayment;
            })
            .map(sporadicPurchasePaymentRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SporadicPurchasePayment> findAll() {
        log.debug("Request to get all SporadicPurchasePayments");
        return sporadicPurchasePaymentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SporadicPurchasePayment> findOne(Integer id) {
        log.debug("Request to get SporadicPurchasePayment : {}", id);
        return sporadicPurchasePaymentRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete SporadicPurchasePayment : {}", id);
        sporadicPurchasePaymentRepository.deleteById(id);
    }
}
