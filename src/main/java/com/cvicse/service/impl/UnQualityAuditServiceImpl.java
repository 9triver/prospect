package com.cvicse.service.impl;

import com.cvicse.domain.UnQualityAudit;
import com.cvicse.repository.UnQualityAuditRepository;
import com.cvicse.service.UnQualityAuditService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.UnQualityAudit}.
 */
@Service
@Transactional
public class UnQualityAuditServiceImpl implements UnQualityAuditService {

    private final Logger log = LoggerFactory.getLogger(UnQualityAuditServiceImpl.class);

    private final UnQualityAuditRepository unQualityAuditRepository;

    public UnQualityAuditServiceImpl(UnQualityAuditRepository unQualityAuditRepository) {
        this.unQualityAuditRepository = unQualityAuditRepository;
    }

    @Override
    public UnQualityAudit save(UnQualityAudit unQualityAudit) {
        log.debug("Request to save UnQualityAudit : {}", unQualityAudit);
        return unQualityAuditRepository.save(unQualityAudit);
    }

    @Override
    public UnQualityAudit update(UnQualityAudit unQualityAudit) {
        log.debug("Request to update UnQualityAudit : {}", unQualityAudit);
        return unQualityAuditRepository.save(unQualityAudit);
    }

    @Override
    public Optional<UnQualityAudit> partialUpdate(UnQualityAudit unQualityAudit) {
        log.debug("Request to partially update UnQualityAudit : {}", unQualityAudit);

        return unQualityAuditRepository
            .findById(unQualityAudit.getId())
            .map(existingUnQualityAudit -> {
                if (unQualityAudit.getUnqualityname() != null) {
                    existingUnQualityAudit.setUnqualityname(unQualityAudit.getUnqualityname());
                }
                if (unQualityAudit.getUnqualitytype() != null) {
                    existingUnQualityAudit.setUnqualitytype(unQualityAudit.getUnqualitytype());
                }
                if (unQualityAudit.getBelongunitid() != null) {
                    existingUnQualityAudit.setBelongunitid(unQualityAudit.getBelongunitid());
                }
                if (unQualityAudit.getBelongunitname() != null) {
                    existingUnQualityAudit.setBelongunitname(unQualityAudit.getBelongunitname());
                }
                if (unQualityAudit.getAuditteam() != null) {
                    existingUnQualityAudit.setAuditteam(unQualityAudit.getAuditteam());
                }
                if (unQualityAudit.getAuditperson() != null) {
                    existingUnQualityAudit.setAuditperson(unQualityAudit.getAuditperson());
                }
                if (unQualityAudit.getUnqualitynum() != null) {
                    existingUnQualityAudit.setUnqualitynum(unQualityAudit.getUnqualitynum());
                }
                if (unQualityAudit.getCreatorname() != null) {
                    existingUnQualityAudit.setCreatorname(unQualityAudit.getCreatorname());
                }
                if (unQualityAudit.getAuditStatus() != null) {
                    existingUnQualityAudit.setAuditStatus(unQualityAudit.getAuditStatus());
                }

                return existingUnQualityAudit;
            })
            .map(unQualityAuditRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UnQualityAudit> findAll() {
        log.debug("Request to get all UnQualityAudits");
        return unQualityAuditRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UnQualityAudit> findOne(String id) {
        log.debug("Request to get UnQualityAudit : {}", id);
        return unQualityAuditRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete UnQualityAudit : {}", id);
        unQualityAuditRepository.deleteById(id);
    }
}
