package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.OutsourcingContractual;
import com.cvicse.jy1.repository.OutsourcingContractualRepository;
import com.cvicse.jy1.service.OutsourcingContractualService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.OutsourcingContractual}.
 */
@Service
@Transactional
public class OutsourcingContractualServiceImpl implements OutsourcingContractualService {

    private static final Logger log = LoggerFactory.getLogger(OutsourcingContractualServiceImpl.class);

    private final OutsourcingContractualRepository outsourcingContractualRepository;

    public OutsourcingContractualServiceImpl(OutsourcingContractualRepository outsourcingContractualRepository) {
        this.outsourcingContractualRepository = outsourcingContractualRepository;
    }

    @Override
    public OutsourcingContractual save(OutsourcingContractual outsourcingContractual) {
        log.debug("Request to save OutsourcingContractual : {}", outsourcingContractual);
        return outsourcingContractualRepository.save(outsourcingContractual);
    }

    @Override
    public OutsourcingContractual update(OutsourcingContractual outsourcingContractual) {
        log.debug("Request to update OutsourcingContractual : {}", outsourcingContractual);
        return outsourcingContractualRepository.save(outsourcingContractual);
    }

    @Override
    public Optional<OutsourcingContractual> partialUpdate(OutsourcingContractual outsourcingContractual) {
        log.debug("Request to partially update OutsourcingContractual : {}", outsourcingContractual);

        return outsourcingContractualRepository
            .findById(outsourcingContractual.getId())
            .map(existingOutsourcingContractual -> {
                if (outsourcingContractual.getDepartment() != null) {
                    existingOutsourcingContractual.setDepartment(outsourcingContractual.getDepartment());
                }
                if (outsourcingContractual.getYear() != null) {
                    existingOutsourcingContractual.setYear(outsourcingContractual.getYear());
                }
                if (outsourcingContractual.getStarttime() != null) {
                    existingOutsourcingContractual.setStarttime(outsourcingContractual.getStarttime());
                }
                if (outsourcingContractual.getEndtime() != null) {
                    existingOutsourcingContractual.setEndtime(outsourcingContractual.getEndtime());
                }
                if (outsourcingContractual.getStatus() != null) {
                    existingOutsourcingContractual.setStatus(outsourcingContractual.getStatus());
                }
                if (outsourcingContractual.getSecretlevel() != null) {
                    existingOutsourcingContractual.setSecretlevel(outsourcingContractual.getSecretlevel());
                }
                if (outsourcingContractual.getForeigncurrency() != null) {
                    existingOutsourcingContractual.setForeigncurrency(outsourcingContractual.getForeigncurrency());
                }
                if (outsourcingContractual.getTotalbudget() != null) {
                    existingOutsourcingContractual.setTotalbudget(outsourcingContractual.getTotalbudget());
                }
                if (outsourcingContractual.getFundsinplace() != null) {
                    existingOutsourcingContractual.setFundsinplace(outsourcingContractual.getFundsinplace());
                }
                if (outsourcingContractual.getResponsibleunitname() != null) {
                    existingOutsourcingContractual.setResponsibleunitname(outsourcingContractual.getResponsibleunitname());
                }
                if (outsourcingContractual.getAudittime() != null) {
                    existingOutsourcingContractual.setAudittime(outsourcingContractual.getAudittime());
                }
                if (outsourcingContractual.getAccountbank() != null) {
                    existingOutsourcingContractual.setAccountbank(outsourcingContractual.getAccountbank());
                }

                return existingOutsourcingContractual;
            })
            .map(outsourcingContractualRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OutsourcingContractual> findAll() {
        log.debug("Request to get all OutsourcingContractuals");
        return outsourcingContractualRepository.findAll();
    }

    public Page<OutsourcingContractual> findAllWithEagerRelationships(Pageable pageable) {
        return outsourcingContractualRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OutsourcingContractual> findOne(String id) {
        log.debug("Request to get OutsourcingContractual : {}", id);
        return outsourcingContractualRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete OutsourcingContractual : {}", id);
        outsourcingContractualRepository.deleteById(id);
    }
}
