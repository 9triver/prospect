package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.ContractCostBudget;
import com.cvicse.jy1.repository.ContractCostBudgetRepository;
import com.cvicse.jy1.service.ContractCostBudgetService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.ContractCostBudget}.
 */
@Service
@Transactional
public class ContractCostBudgetServiceImpl implements ContractCostBudgetService {

    private static final Logger log = LoggerFactory.getLogger(ContractCostBudgetServiceImpl.class);

    private final ContractCostBudgetRepository contractCostBudgetRepository;

    public ContractCostBudgetServiceImpl(ContractCostBudgetRepository contractCostBudgetRepository) {
        this.contractCostBudgetRepository = contractCostBudgetRepository;
    }

    @Override
    public ContractCostBudget save(ContractCostBudget contractCostBudget) {
        log.debug("Request to save ContractCostBudget : {}", contractCostBudget);
        return contractCostBudgetRepository.save(contractCostBudget);
    }

    @Override
    public ContractCostBudget update(ContractCostBudget contractCostBudget) {
        log.debug("Request to update ContractCostBudget : {}", contractCostBudget);
        return contractCostBudgetRepository.save(contractCostBudget);
    }

    @Override
    public Optional<ContractCostBudget> partialUpdate(ContractCostBudget contractCostBudget) {
        log.debug("Request to partially update ContractCostBudget : {}", contractCostBudget);

        return contractCostBudgetRepository
            .findById(contractCostBudget.getId())
            .map(existingContractCostBudget -> {
                if (contractCostBudget.getSubject() != null) {
                    existingContractCostBudget.setSubject(contractCostBudget.getSubject());
                }
                if (contractCostBudget.getAuxiliaryitem() != null) {
                    existingContractCostBudget.setAuxiliaryitem(contractCostBudget.getAuxiliaryitem());
                }
                if (contractCostBudget.getUnit() != null) {
                    existingContractCostBudget.setUnit(contractCostBudget.getUnit());
                }
                if (contractCostBudget.getNumber() != null) {
                    existingContractCostBudget.setNumber(contractCostBudget.getNumber());
                }
                if (contractCostBudget.getUnitprice() != null) {
                    existingContractCostBudget.setUnitprice(contractCostBudget.getUnitprice());
                }
                if (contractCostBudget.getTotalprice() != null) {
                    existingContractCostBudget.setTotalprice(contractCostBudget.getTotalprice());
                }

                return existingContractCostBudget;
            })
            .map(contractCostBudgetRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContractCostBudget> findAll() {
        log.debug("Request to get all ContractCostBudgets");
        return contractCostBudgetRepository.findAll();
    }

    public Page<ContractCostBudget> findAllWithEagerRelationships(Pageable pageable) {
        return contractCostBudgetRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ContractCostBudget> findOne(String id) {
        log.debug("Request to get ContractCostBudget : {}", id);
        return contractCostBudgetRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ContractCostBudget : {}", id);
        contractCostBudgetRepository.deleteById(id);
    }
}
