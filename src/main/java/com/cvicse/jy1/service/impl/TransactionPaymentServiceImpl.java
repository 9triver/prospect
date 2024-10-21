package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.TransactionPayment;
import com.cvicse.jy1.repository.TransactionPaymentRepository;
import com.cvicse.jy1.service.TransactionPaymentService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.TransactionPayment}.
 */
@Service
@Transactional
public class TransactionPaymentServiceImpl implements TransactionPaymentService {

    private static final Logger log = LoggerFactory.getLogger(TransactionPaymentServiceImpl.class);

    private final TransactionPaymentRepository transactionPaymentRepository;

    public TransactionPaymentServiceImpl(TransactionPaymentRepository transactionPaymentRepository) {
        this.transactionPaymentRepository = transactionPaymentRepository;
    }

    @Override
    public TransactionPayment save(TransactionPayment transactionPayment) {
        log.debug("Request to save TransactionPayment : {}", transactionPayment);
        return transactionPaymentRepository.save(transactionPayment);
    }

    @Override
    public TransactionPayment update(TransactionPayment transactionPayment) {
        log.debug("Request to update TransactionPayment : {}", transactionPayment);
        return transactionPaymentRepository.save(transactionPayment);
    }

    @Override
    public Optional<TransactionPayment> partialUpdate(TransactionPayment transactionPayment) {
        log.debug("Request to partially update TransactionPayment : {}", transactionPayment);

        return transactionPaymentRepository
            .findById(transactionPayment.getId())
            .map(existingTransactionPayment -> {
                if (transactionPayment.getPlanpaymentnode() != null) {
                    existingTransactionPayment.setPlanpaymentnode(transactionPayment.getPlanpaymentnode());
                }
                if (transactionPayment.getPlanpaymentamount() != null) {
                    existingTransactionPayment.setPlanpaymentamount(transactionPayment.getPlanpaymentamount());
                }
                if (transactionPayment.getActualpaymentamount() != null) {
                    existingTransactionPayment.setActualpaymentamount(transactionPayment.getActualpaymentamount());
                }
                if (transactionPayment.getPaymenttype() != null) {
                    existingTransactionPayment.setPaymenttype(transactionPayment.getPaymenttype());
                }
                if (transactionPayment.getFinancialvoucherid() != null) {
                    existingTransactionPayment.setFinancialvoucherid(transactionPayment.getFinancialvoucherid());
                }

                return existingTransactionPayment;
            })
            .map(transactionPaymentRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionPayment> findAll() {
        log.debug("Request to get all TransactionPayments");
        return transactionPaymentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TransactionPayment> findOne(Integer id) {
        log.debug("Request to get TransactionPayment : {}", id);
        return transactionPaymentRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete TransactionPayment : {}", id);
        transactionPaymentRepository.deleteById(id);
    }
}
