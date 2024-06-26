package com.cvicse.service.impl;

import com.cvicse.domain.Auditedbudget;
import com.cvicse.repository.AuditedbudgetRepository;
import com.cvicse.service.AuditedbudgetService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Auditedbudget}.
 */
@Service
@Transactional
public class AuditedbudgetServiceImpl implements AuditedbudgetService {

    private final Logger log = LoggerFactory.getLogger(AuditedbudgetServiceImpl.class);

    private final AuditedbudgetRepository auditedbudgetRepository;

    public AuditedbudgetServiceImpl(AuditedbudgetRepository auditedbudgetRepository) {
        this.auditedbudgetRepository = auditedbudgetRepository;
    }

    @Override
    public Auditedbudget save(Auditedbudget auditedbudget) {
        log.debug("Request to save Auditedbudget : {}", auditedbudget);
        return auditedbudgetRepository.save(auditedbudget);
    }

    @Override
    public Auditedbudget update(Auditedbudget auditedbudget) {
        log.debug("Request to update Auditedbudget : {}", auditedbudget);
        return auditedbudgetRepository.save(auditedbudget);
    }

    @Override
    public Optional<Auditedbudget> partialUpdate(Auditedbudget auditedbudget) {
        log.debug("Request to partially update Auditedbudget : {}", auditedbudget);

        return auditedbudgetRepository
            .findById(auditedbudget.getId())
            .map(existingAuditedbudget -> {
                if (auditedbudget.getCreatetime() != null) {
                    existingAuditedbudget.setCreatetime(auditedbudget.getCreatetime());
                }
                if (auditedbudget.getCreatorname() != null) {
                    existingAuditedbudget.setCreatorname(auditedbudget.getCreatorname());
                }
                if (auditedbudget.getSecretlevel() != null) {
                    existingAuditedbudget.setSecretlevel(auditedbudget.getSecretlevel());
                }
                if (auditedbudget.getYear() != null) {
                    existingAuditedbudget.setYear(auditedbudget.getYear());
                }
                if (auditedbudget.getBudgit() != null) {
                    existingAuditedbudget.setBudgit(auditedbudget.getBudgit());
                }
                if (auditedbudget.getDapartmentid() != null) {
                    existingAuditedbudget.setDapartmentid(auditedbudget.getDapartmentid());
                }
                if (auditedbudget.getDraftapproval() != null) {
                    existingAuditedbudget.setDraftapproval(auditedbudget.getDraftapproval());
                }
                if (auditedbudget.getTotalbudgetid() != null) {
                    existingAuditedbudget.setTotalbudgetid(auditedbudget.getTotalbudgetid());
                }
                if (auditedbudget.getUnitbudgetid() != null) {
                    existingAuditedbudget.setUnitbudgetid(auditedbudget.getUnitbudgetid());
                }
                if (auditedbudget.getDocumentid() != null) {
                    existingAuditedbudget.setDocumentid(auditedbudget.getDocumentid());
                }
                if (auditedbudget.getMaintainerid() != null) {
                    existingAuditedbudget.setMaintainerid(auditedbudget.getMaintainerid());
                }
                if (auditedbudget.getAuditStatus() != null) {
                    existingAuditedbudget.setAuditStatus(auditedbudget.getAuditStatus());
                }

                return existingAuditedbudget;
            })
            .map(auditedbudgetRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Auditedbudget> findAll() {
        log.debug("Request to get all Auditedbudgets");
        return auditedbudgetRepository.findAll();
    }

    /**
     *  Get all the auditedbudgets where Fundsavailability is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Auditedbudget> findAllWhereFundsavailabilityIsNull() {
        log.debug("Request to get all auditedbudgets where Fundsavailability is null");
        return StreamSupport.stream(auditedbudgetRepository.findAll().spliterator(), false)
            .filter(auditedbudget -> auditedbudget.getFundsavailability() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Auditedbudget> findOne(String id) {
        log.debug("Request to get Auditedbudget : {}", id);
        return auditedbudgetRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Auditedbudget : {}", id);
        auditedbudgetRepository.deleteById(id);
    }
}
