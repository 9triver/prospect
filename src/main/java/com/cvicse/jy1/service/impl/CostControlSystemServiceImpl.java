package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.CostControlSystem;
import com.cvicse.jy1.repository.CostControlSystemRepository;
import com.cvicse.jy1.service.CostControlSystemService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.CostControlSystem}.
 */
@Service
@Transactional
public class CostControlSystemServiceImpl implements CostControlSystemService {

    private static final Logger log = LoggerFactory.getLogger(CostControlSystemServiceImpl.class);

    private final CostControlSystemRepository costControlSystemRepository;

    public CostControlSystemServiceImpl(CostControlSystemRepository costControlSystemRepository) {
        this.costControlSystemRepository = costControlSystemRepository;
    }

    @Override
    public CostControlSystem save(CostControlSystem costControlSystem) {
        log.debug("Request to save CostControlSystem : {}", costControlSystem);
        return costControlSystemRepository.save(costControlSystem);
    }

    @Override
    public CostControlSystem update(CostControlSystem costControlSystem) {
        log.debug("Request to update CostControlSystem : {}", costControlSystem);
        return costControlSystemRepository.save(costControlSystem);
    }

    @Override
    public Optional<CostControlSystem> partialUpdate(CostControlSystem costControlSystem) {
        log.debug("Request to partially update CostControlSystem : {}", costControlSystem);

        return costControlSystemRepository
            .findById(costControlSystem.getId())
            .map(existingCostControlSystem -> {
                if (costControlSystem.getType() != null) {
                    existingCostControlSystem.setType(costControlSystem.getType());
                }
                if (costControlSystem.getSubject() != null) {
                    existingCostControlSystem.setSubject(costControlSystem.getSubject());
                }
                if (costControlSystem.getImplementedamount() != null) {
                    existingCostControlSystem.setImplementedamount(costControlSystem.getImplementedamount());
                }
                if (costControlSystem.getApprovedamount() != null) {
                    existingCostControlSystem.setApprovedamount(costControlSystem.getApprovedamount());
                }
                if (costControlSystem.getPendingimplementationamount() != null) {
                    existingCostControlSystem.setPendingimplementationamount(costControlSystem.getPendingimplementationamount());
                }
                if (costControlSystem.getContractpaymentamount() != null) {
                    existingCostControlSystem.setContractpaymentamount(costControlSystem.getContractpaymentamount());
                }
                if (costControlSystem.getManagementregistrationnumber() != null) {
                    existingCostControlSystem.setManagementregistrationnumber(costControlSystem.getManagementregistrationnumber());
                }
                if (costControlSystem.getFinancialregistrationnumber() != null) {
                    existingCostControlSystem.setFinancialregistrationnumber(costControlSystem.getFinancialregistrationnumber());
                }
                if (costControlSystem.getContractbudgetamount() != null) {
                    existingCostControlSystem.setContractbudgetamount(costControlSystem.getContractbudgetamount());
                }
                if (costControlSystem.getContractsigningamount() != null) {
                    existingCostControlSystem.setContractsigningamount(costControlSystem.getContractsigningamount());
                }
                if (costControlSystem.getContractsettlementamount() != null) {
                    existingCostControlSystem.setContractsettlementamount(costControlSystem.getContractsettlementamount());
                }
                if (costControlSystem.getUnforeseeableamount() != null) {
                    existingCostControlSystem.setUnforeseeableamount(costControlSystem.getUnforeseeableamount());
                }
                if (costControlSystem.getInvoicepaymentamount() != null) {
                    existingCostControlSystem.setInvoicepaymentamount(costControlSystem.getInvoicepaymentamount());
                }
                if (costControlSystem.getLoanpaymentamount() != null) {
                    existingCostControlSystem.setLoanpaymentamount(costControlSystem.getLoanpaymentamount());
                }
                if (costControlSystem.getAccountoutstandingamount() != null) {
                    existingCostControlSystem.setAccountoutstandingamount(costControlSystem.getAccountoutstandingamount());
                }
                if (costControlSystem.getPendingpaymentamount() != null) {
                    existingCostControlSystem.setPendingpaymentamount(costControlSystem.getPendingpaymentamount());
                }
                if (costControlSystem.getPendinginvoiceamount() != null) {
                    existingCostControlSystem.setPendinginvoiceamount(costControlSystem.getPendinginvoiceamount());
                }

                return existingCostControlSystem;
            })
            .map(costControlSystemRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostControlSystem> findAll() {
        log.debug("Request to get all CostControlSystems");
        return costControlSystemRepository.findAll();
    }

    public Page<CostControlSystem> findAllWithEagerRelationships(Pageable pageable) {
        return costControlSystemRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CostControlSystem> findOne(String id) {
        log.debug("Request to get CostControlSystem : {}", id);
        return costControlSystemRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete CostControlSystem : {}", id);
        costControlSystemRepository.deleteById(id);
    }
}
