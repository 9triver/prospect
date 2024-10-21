package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.ContractPayment;
import com.cvicse.jy1.repository.ContractPaymentRepository;
import com.cvicse.jy1.service.ContractPaymentService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.ContractPayment}.
 */
@Service
@Transactional
public class ContractPaymentServiceImpl implements ContractPaymentService {

    private static final Logger log = LoggerFactory.getLogger(ContractPaymentServiceImpl.class);

    private final ContractPaymentRepository contractPaymentRepository;

    public ContractPaymentServiceImpl(ContractPaymentRepository contractPaymentRepository) {
        this.contractPaymentRepository = contractPaymentRepository;
    }

    @Override
    public ContractPayment save(ContractPayment contractPayment) {
        log.debug("Request to save ContractPayment : {}", contractPayment);
        return contractPaymentRepository.save(contractPayment);
    }

    @Override
    public ContractPayment update(ContractPayment contractPayment) {
        log.debug("Request to update ContractPayment : {}", contractPayment);
        return contractPaymentRepository.save(contractPayment);
    }

    @Override
    public Optional<ContractPayment> partialUpdate(ContractPayment contractPayment) {
        log.debug("Request to partially update ContractPayment : {}", contractPayment);

        return contractPaymentRepository
            .findById(contractPayment.getId())
            .map(existingContractPayment -> {
                if (contractPayment.getWorkbagid() != null) {
                    existingContractPayment.setWorkbagid(contractPayment.getWorkbagid());
                }
                if (contractPayment.getWorkbagname() != null) {
                    existingContractPayment.setWorkbagname(contractPayment.getWorkbagname());
                }
                if (contractPayment.getContractcode() != null) {
                    existingContractPayment.setContractcode(contractPayment.getContractcode());
                }
                if (contractPayment.getContractname() != null) {
                    existingContractPayment.setContractname(contractPayment.getContractname());
                }
                if (contractPayment.getPlanpaymentnode() != null) {
                    existingContractPayment.setPlanpaymentnode(contractPayment.getPlanpaymentnode());
                }
                if (contractPayment.getPlanpaymentamount() != null) {
                    existingContractPayment.setPlanpaymentamount(contractPayment.getPlanpaymentamount());
                }
                if (contractPayment.getActualpaymentamount() != null) {
                    existingContractPayment.setActualpaymentamount(contractPayment.getActualpaymentamount());
                }
                if (contractPayment.getPaymenttype() != null) {
                    existingContractPayment.setPaymenttype(contractPayment.getPaymenttype());
                }
                if (contractPayment.getFinancialvoucherid() != null) {
                    existingContractPayment.setFinancialvoucherid(contractPayment.getFinancialvoucherid());
                }

                return existingContractPayment;
            })
            .map(contractPaymentRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContractPayment> findAll() {
        log.debug("Request to get all ContractPayments");
        return contractPaymentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ContractPayment> findOne(Integer id) {
        log.debug("Request to get ContractPayment : {}", id);
        return contractPaymentRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete ContractPayment : {}", id);
        contractPaymentRepository.deleteById(id);
    }
}
