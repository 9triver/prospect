package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.Contract;
import com.cvicse.jy1.repository.ContractRepository;
import com.cvicse.jy1.service.ContractService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.Contract}.
 */
@Service
@Transactional
public class ContractServiceImpl implements ContractService {

    private static final Logger log = LoggerFactory.getLogger(ContractServiceImpl.class);

    private final ContractRepository contractRepository;

    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public Contract save(Contract contract) {
        log.debug("Request to save Contract : {}", contract);
        return contractRepository.save(contract);
    }

    @Override
    public Contract update(Contract contract) {
        log.debug("Request to update Contract : {}", contract);
        return contractRepository.save(contract);
    }

    @Override
    public Optional<Contract> partialUpdate(Contract contract) {
        log.debug("Request to partially update Contract : {}", contract);

        return contractRepository
            .findById(contract.getId())
            .map(existingContract -> {
                if (contract.getContractcode() != null) {
                    existingContract.setContractcode(contract.getContractcode());
                }
                if (contract.getContractname() != null) {
                    existingContract.setContractname(contract.getContractname());
                }
                if (contract.getProjectid() != null) {
                    existingContract.setProjectid(contract.getProjectid());
                }
                if (contract.getProjectname() != null) {
                    existingContract.setProjectname(contract.getProjectname());
                }
                if (contract.getContracttype() != null) {
                    existingContract.setContracttype(contract.getContracttype());
                }
                if (contract.getYear() != null) {
                    existingContract.setYear(contract.getYear());
                }
                if (contract.getAmount() != null) {
                    existingContract.setAmount(contract.getAmount());
                }
                if (contract.getStarttime() != null) {
                    existingContract.setStarttime(contract.getStarttime());
                }
                if (contract.getEndtime() != null) {
                    existingContract.setEndtime(contract.getEndtime());
                }
                if (contract.getSecretlevel() != null) {
                    existingContract.setSecretlevel(contract.getSecretlevel());
                }
                if (contract.getStatus() != null) {
                    existingContract.setStatus(contract.getStatus());
                }
                if (contract.getBudgetamount() != null) {
                    existingContract.setBudgetamount(contract.getBudgetamount());
                }
                if (contract.getEstimatedamount() != null) {
                    existingContract.setEstimatedamount(contract.getEstimatedamount());
                }
                if (contract.getImplementedamount() != null) {
                    existingContract.setImplementedamount(contract.getImplementedamount());
                }
                if (contract.getDifference() != null) {
                    existingContract.setDifference(contract.getDifference());
                }

                return existingContract;
            })
            .map(contractRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contract> findAll() {
        log.debug("Request to get all Contracts");
        return contractRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Contract> findOne(Integer id) {
        log.debug("Request to get Contract : {}", id);
        return contractRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete Contract : {}", id);
        contractRepository.deleteById(id);
    }
}
