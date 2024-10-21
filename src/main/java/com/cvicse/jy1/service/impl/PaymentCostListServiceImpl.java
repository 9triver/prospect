package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.PaymentCostList;
import com.cvicse.jy1.repository.PaymentCostListRepository;
import com.cvicse.jy1.service.PaymentCostListService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.PaymentCostList}.
 */
@Service
@Transactional
public class PaymentCostListServiceImpl implements PaymentCostListService {

    private static final Logger log = LoggerFactory.getLogger(PaymentCostListServiceImpl.class);

    private final PaymentCostListRepository paymentCostListRepository;

    public PaymentCostListServiceImpl(PaymentCostListRepository paymentCostListRepository) {
        this.paymentCostListRepository = paymentCostListRepository;
    }

    @Override
    public PaymentCostList save(PaymentCostList paymentCostList) {
        log.debug("Request to save PaymentCostList : {}", paymentCostList);
        return paymentCostListRepository.save(paymentCostList);
    }

    @Override
    public PaymentCostList update(PaymentCostList paymentCostList) {
        log.debug("Request to update PaymentCostList : {}", paymentCostList);
        return paymentCostListRepository.save(paymentCostList);
    }

    @Override
    public Optional<PaymentCostList> partialUpdate(PaymentCostList paymentCostList) {
        log.debug("Request to partially update PaymentCostList : {}", paymentCostList);

        return paymentCostListRepository
            .findById(paymentCostList.getId())
            .map(existingPaymentCostList -> {
                if (paymentCostList.getWbsid() != null) {
                    existingPaymentCostList.setWbsid(paymentCostList.getWbsid());
                }
                if (paymentCostList.getWbsname() != null) {
                    existingPaymentCostList.setWbsname(paymentCostList.getWbsname());
                }
                if (paymentCostList.getParentwbsid() != null) {
                    existingPaymentCostList.setParentwbsid(paymentCostList.getParentwbsid());
                }
                if (paymentCostList.getUnit() != null) {
                    existingPaymentCostList.setUnit(paymentCostList.getUnit());
                }
                if (paymentCostList.getUnitprice() != null) {
                    existingPaymentCostList.setUnitprice(paymentCostList.getUnitprice());
                }
                if (paymentCostList.getNumber() != null) {
                    existingPaymentCostList.setNumber(paymentCostList.getNumber());
                }
                if (paymentCostList.getInvoicepaymentamount() != null) {
                    existingPaymentCostList.setInvoicepaymentamount(paymentCostList.getInvoicepaymentamount());
                }
                if (paymentCostList.getBorrowingpaymentamount() != null) {
                    existingPaymentCostList.setBorrowingpaymentamount(paymentCostList.getBorrowingpaymentamount());
                }
                if (paymentCostList.getAccountingamount() != null) {
                    existingPaymentCostList.setAccountingamount(paymentCostList.getAccountingamount());
                }

                return existingPaymentCostList;
            })
            .map(paymentCostListRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentCostList> findAll() {
        log.debug("Request to get all PaymentCostLists");
        return paymentCostListRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PaymentCostList> findOne(Integer id) {
        log.debug("Request to get PaymentCostList : {}", id);
        return paymentCostListRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete PaymentCostList : {}", id);
        paymentCostListRepository.deleteById(id);
    }
}
