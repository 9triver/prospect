package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.SharePayment;
import com.cvicse.jy1.repository.SharePaymentRepository;
import com.cvicse.jy1.service.SharePaymentService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.SharePayment}.
 */
@Service
@Transactional
public class SharePaymentServiceImpl implements SharePaymentService {

    private static final Logger log = LoggerFactory.getLogger(SharePaymentServiceImpl.class);

    private final SharePaymentRepository sharePaymentRepository;

    public SharePaymentServiceImpl(SharePaymentRepository sharePaymentRepository) {
        this.sharePaymentRepository = sharePaymentRepository;
    }

    @Override
    public SharePayment save(SharePayment sharePayment) {
        log.debug("Request to save SharePayment : {}", sharePayment);
        return sharePaymentRepository.save(sharePayment);
    }

    @Override
    public SharePayment update(SharePayment sharePayment) {
        log.debug("Request to update SharePayment : {}", sharePayment);
        return sharePaymentRepository.save(sharePayment);
    }

    @Override
    public Optional<SharePayment> partialUpdate(SharePayment sharePayment) {
        log.debug("Request to partially update SharePayment : {}", sharePayment);

        return sharePaymentRepository
            .findById(sharePayment.getId())
            .map(existingSharePayment -> {
                if (sharePayment.getPlanpaymentnode() != null) {
                    existingSharePayment.setPlanpaymentnode(sharePayment.getPlanpaymentnode());
                }
                if (sharePayment.getPlanpaymentamount() != null) {
                    existingSharePayment.setPlanpaymentamount(sharePayment.getPlanpaymentamount());
                }
                if (sharePayment.getActualpaymentamount() != null) {
                    existingSharePayment.setActualpaymentamount(sharePayment.getActualpaymentamount());
                }
                if (sharePayment.getPaymenttype() != null) {
                    existingSharePayment.setPaymenttype(sharePayment.getPaymenttype());
                }
                if (sharePayment.getFinancialvoucherid() != null) {
                    existingSharePayment.setFinancialvoucherid(sharePayment.getFinancialvoucherid());
                }

                return existingSharePayment;
            })
            .map(sharePaymentRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SharePayment> findAll() {
        log.debug("Request to get all SharePayments");
        return sharePaymentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SharePayment> findOne(Integer id) {
        log.debug("Request to get SharePayment : {}", id);
        return sharePaymentRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete SharePayment : {}", id);
        sharePaymentRepository.deleteById(id);
    }
}
