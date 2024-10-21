package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.PaymentApplication;
import com.cvicse.jy1.repository.PaymentApplicationRepository;
import com.cvicse.jy1.service.PaymentApplicationService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.PaymentApplication}.
 */
@Service
@Transactional
public class PaymentApplicationServiceImpl implements PaymentApplicationService {

    private static final Logger log = LoggerFactory.getLogger(PaymentApplicationServiceImpl.class);

    private final PaymentApplicationRepository paymentApplicationRepository;

    public PaymentApplicationServiceImpl(PaymentApplicationRepository paymentApplicationRepository) {
        this.paymentApplicationRepository = paymentApplicationRepository;
    }

    @Override
    public PaymentApplication save(PaymentApplication paymentApplication) {
        log.debug("Request to save PaymentApplication : {}", paymentApplication);
        return paymentApplicationRepository.save(paymentApplication);
    }

    @Override
    public PaymentApplication update(PaymentApplication paymentApplication) {
        log.debug("Request to update PaymentApplication : {}", paymentApplication);
        return paymentApplicationRepository.save(paymentApplication);
    }

    @Override
    public Optional<PaymentApplication> partialUpdate(PaymentApplication paymentApplication) {
        log.debug("Request to partially update PaymentApplication : {}", paymentApplication);

        return paymentApplicationRepository
            .findById(paymentApplication.getId())
            .map(existingPaymentApplication -> {
                if (paymentApplication.getWorkbagid() != null) {
                    existingPaymentApplication.setWorkbagid(paymentApplication.getWorkbagid());
                }
                if (paymentApplication.getContractcode() != null) {
                    existingPaymentApplication.setContractcode(paymentApplication.getContractcode());
                }
                if (paymentApplication.getPlanpaymentnode() != null) {
                    existingPaymentApplication.setPlanpaymentnode(paymentApplication.getPlanpaymentnode());
                }
                if (paymentApplication.getPlanpaymentamount() != null) {
                    existingPaymentApplication.setPlanpaymentamount(paymentApplication.getPlanpaymentamount());
                }

                return existingPaymentApplication;
            })
            .map(paymentApplicationRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentApplication> findAll() {
        log.debug("Request to get all PaymentApplications");
        return paymentApplicationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PaymentApplication> findOne(Integer id) {
        log.debug("Request to get PaymentApplication : {}", id);
        return paymentApplicationRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete PaymentApplication : {}", id);
        paymentApplicationRepository.deleteById(id);
    }
}
