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
                if (contract.getContractname() != null) {
                    existingContract.setContractname(contract.getContractname());
                }
                if (contract.getYear() != null) {
                    existingContract.setYear(contract.getYear());
                }
                if (contract.getStarttime() != null) {
                    existingContract.setStarttime(contract.getStarttime());
                }
                if (contract.getEndtime() != null) {
                    existingContract.setEndtime(contract.getEndtime());
                }
                if (contract.getContractbudgetcost() != null) {
                    existingContract.setContractbudgetcost(contract.getContractbudgetcost());
                }
                if (contract.getSecretlevel() != null) {
                    existingContract.setSecretlevel(contract.getSecretlevel());
                }
                if (contract.getStatus() != null) {
                    existingContract.setStatus(contract.getStatus());
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
    public Optional<Contract> findOne(String id) {
        log.debug("Request to get Contract : {}", id);
        return contractRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Contract : {}", id);
        contractRepository.deleteById(id);
    }
}
