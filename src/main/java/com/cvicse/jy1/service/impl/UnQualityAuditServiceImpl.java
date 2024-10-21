package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.UnQualityAudit;
import com.cvicse.jy1.repository.UnQualityAuditRepository;
import com.cvicse.jy1.service.UnQualityAuditService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.UnQualityAudit}.
 */
@Service
@Transactional
public class UnQualityAuditServiceImpl implements UnQualityAuditService {

    private static final Logger log = LoggerFactory.getLogger(UnQualityAuditServiceImpl.class);

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
                if (unQualityAudit.getWorkbagid() != null) {
                    existingUnQualityAudit.setWorkbagid(unQualityAudit.getWorkbagid());
                }
                if (unQualityAudit.getBelongwbsid() != null) {
                    existingUnQualityAudit.setBelongwbsid(unQualityAudit.getBelongwbsid());
                }
                if (unQualityAudit.getOutsourcingcontractid() != null) {
                    existingUnQualityAudit.setOutsourcingcontractid(unQualityAudit.getOutsourcingcontractid());
                }
                if (unQualityAudit.getUnqualityid() != null) {
                    existingUnQualityAudit.setUnqualityid(unQualityAudit.getUnqualityid());
                }
                if (unQualityAudit.getUnqualityname() != null) {
                    existingUnQualityAudit.setUnqualityname(unQualityAudit.getUnqualityname());
                }
                if (unQualityAudit.getUnqualityunit() != null) {
                    existingUnQualityAudit.setUnqualityunit(unQualityAudit.getUnqualityunit());
                }
                if (unQualityAudit.getUnqualitytrialgroup() != null) {
                    existingUnQualityAudit.setUnqualitytrialgroup(unQualityAudit.getUnqualitytrialgroup());
                }
                if (unQualityAudit.getInspector() != null) {
                    existingUnQualityAudit.setInspector(unQualityAudit.getInspector());
                }
                if (unQualityAudit.getUnqualitystage() != null) {
                    existingUnQualityAudit.setUnqualitystage(unQualityAudit.getUnqualitystage());
                }
                if (unQualityAudit.getUnqualitynumber() != null) {
                    existingUnQualityAudit.setUnqualitynumber(unQualityAudit.getUnqualitynumber());
                }
                if (unQualityAudit.getUnqualityintroduction() != null) {
                    existingUnQualityAudit.setUnqualityintroduction(unQualityAudit.getUnqualityintroduction());
                }
                if (unQualityAudit.getUnqualitycategory() != null) {
                    existingUnQualityAudit.setUnqualitycategory(unQualityAudit.getUnqualitycategory());
                }
                if (unQualityAudit.getHandlingopinion() != null) {
                    existingUnQualityAudit.setHandlingopinion(unQualityAudit.getHandlingopinion());
                }
                if (unQualityAudit.getApplicant() != null) {
                    existingUnQualityAudit.setApplicant(unQualityAudit.getApplicant());
                }
                if (unQualityAudit.getApplicationdate() != null) {
                    existingUnQualityAudit.setApplicationdate(unQualityAudit.getApplicationdate());
                }
                if (unQualityAudit.getAuditStatus() != null) {
                    existingUnQualityAudit.setAuditStatus(unQualityAudit.getAuditStatus());
                }
                if (unQualityAudit.getAttachment() != null) {
                    existingUnQualityAudit.setAttachment(unQualityAudit.getAttachment());
                }
                if (unQualityAudit.getDisposalmethod() != null) {
                    existingUnQualityAudit.setDisposalmethod(unQualityAudit.getDisposalmethod());
                }
                if (unQualityAudit.getCauseanalysis() != null) {
                    existingUnQualityAudit.setCauseanalysis(unQualityAudit.getCauseanalysis());
                }
                if (unQualityAudit.getCorrectivemeasures() != null) {
                    existingUnQualityAudit.setCorrectivemeasures(unQualityAudit.getCorrectivemeasures());
                }
                if (unQualityAudit.getRemarks() != null) {
                    existingUnQualityAudit.setRemarks(unQualityAudit.getRemarks());
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
    public Optional<UnQualityAudit> findOne(Integer id) {
        log.debug("Request to get UnQualityAudit : {}", id);
        return unQualityAuditRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete UnQualityAudit : {}", id);
        unQualityAuditRepository.deleteById(id);
    }
}
