package com.cvicse.service.impl;

import com.cvicse.domain.Contractualfunds;
import com.cvicse.repository.ContractualfundsRepository;
import com.cvicse.service.ContractualfundsService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Contractualfunds}.
 */
@Service
@Transactional
public class ContractualfundsServiceImpl implements ContractualfundsService {

    private final Logger log = LoggerFactory.getLogger(ContractualfundsServiceImpl.class);

    private final ContractualfundsRepository contractualfundsRepository;

    public ContractualfundsServiceImpl(ContractualfundsRepository contractualfundsRepository) {
        this.contractualfundsRepository = contractualfundsRepository;
    }

    @Override
    public Contractualfunds save(Contractualfunds contractualfunds) {
        log.debug("Request to save Contractualfunds : {}", contractualfunds);
        return contractualfundsRepository.save(contractualfunds);
    }

    @Override
    public Contractualfunds update(Contractualfunds contractualfunds) {
        log.debug("Request to update Contractualfunds : {}", contractualfunds);
        return contractualfundsRepository.save(contractualfunds);
    }

    @Override
    public Optional<Contractualfunds> partialUpdate(Contractualfunds contractualfunds) {
        log.debug("Request to partially update Contractualfunds : {}", contractualfunds);

        return contractualfundsRepository
            .findById(contractualfunds.getId())
            .map(existingContractualfunds -> {
                if (contractualfunds.getDepartment() != null) {
                    existingContractualfunds.setDepartment(contractualfunds.getDepartment());
                }
                if (contractualfunds.getYear() != null) {
                    existingContractualfunds.setYear(contractualfunds.getYear());
                }
                if (contractualfunds.getStarttime() != null) {
                    existingContractualfunds.setStarttime(contractualfunds.getStarttime());
                }
                if (contractualfunds.getEndtime() != null) {
                    existingContractualfunds.setEndtime(contractualfunds.getEndtime());
                }
                if (contractualfunds.getStatus() != null) {
                    existingContractualfunds.setStatus(contractualfunds.getStatus());
                }
                if (contractualfunds.getSecretlevel() != null) {
                    existingContractualfunds.setSecretlevel(contractualfunds.getSecretlevel());
                }
                if (contractualfunds.getForeigncurrency() != null) {
                    existingContractualfunds.setForeigncurrency(contractualfunds.getForeigncurrency());
                }
                if (contractualfunds.getTotalbudget() != null) {
                    existingContractualfunds.setTotalbudget(contractualfunds.getTotalbudget());
                }
                if (contractualfunds.getFundsinplace() != null) {
                    existingContractualfunds.setFundsinplace(contractualfunds.getFundsinplace());
                }
                if (contractualfunds.getResponsibleunitname() != null) {
                    existingContractualfunds.setResponsibleunitname(contractualfunds.getResponsibleunitname());
                }
                if (contractualfunds.getAudittime() != null) {
                    existingContractualfunds.setAudittime(contractualfunds.getAudittime());
                }
                if (contractualfunds.getAccountbank() != null) {
                    existingContractualfunds.setAccountbank(contractualfunds.getAccountbank());
                }

                return existingContractualfunds;
            })
            .map(contractualfundsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contractualfunds> findAll() {
        log.debug("Request to get all Contractualfunds");
        return contractualfundsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Contractualfunds> findOne(String id) {
        log.debug("Request to get Contractualfunds : {}", id);
        return contractualfundsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Contractualfunds : {}", id);
        contractualfundsRepository.deleteById(id);
    }
}
