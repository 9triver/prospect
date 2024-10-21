package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.DeviationPermitApplication;
import com.cvicse.jy1.repository.DeviationPermitApplicationRepository;
import com.cvicse.jy1.service.DeviationPermitApplicationService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.DeviationPermitApplication}.
 */
@Service
@Transactional
public class DeviationPermitApplicationServiceImpl implements DeviationPermitApplicationService {

    private static final Logger log = LoggerFactory.getLogger(DeviationPermitApplicationServiceImpl.class);

    private final DeviationPermitApplicationRepository deviationPermitApplicationRepository;

    public DeviationPermitApplicationServiceImpl(DeviationPermitApplicationRepository deviationPermitApplicationRepository) {
        this.deviationPermitApplicationRepository = deviationPermitApplicationRepository;
    }

    @Override
    public DeviationPermitApplication save(DeviationPermitApplication deviationPermitApplication) {
        log.debug("Request to save DeviationPermitApplication : {}", deviationPermitApplication);
        return deviationPermitApplicationRepository.save(deviationPermitApplication);
    }

    @Override
    public DeviationPermitApplication update(DeviationPermitApplication deviationPermitApplication) {
        log.debug("Request to update DeviationPermitApplication : {}", deviationPermitApplication);
        return deviationPermitApplicationRepository.save(deviationPermitApplication);
    }

    @Override
    public Optional<DeviationPermitApplication> partialUpdate(DeviationPermitApplication deviationPermitApplication) {
        log.debug("Request to partially update DeviationPermitApplication : {}", deviationPermitApplication);

        return deviationPermitApplicationRepository
            .findById(deviationPermitApplication.getId())
            .map(existingDeviationPermitApplication -> {
                if (deviationPermitApplication.getWbsid() != null) {
                    existingDeviationPermitApplication.setWbsid(deviationPermitApplication.getWbsid());
                }
                if (deviationPermitApplication.getTechnicalfileid() != null) {
                    existingDeviationPermitApplication.setTechnicalfileid(deviationPermitApplication.getTechnicalfileid());
                }
                if (deviationPermitApplication.getApplicationunit() != null) {
                    existingDeviationPermitApplication.setApplicationunit(deviationPermitApplication.getApplicationunit());
                }
                if (deviationPermitApplication.getApplicant() != null) {
                    existingDeviationPermitApplication.setApplicant(deviationPermitApplication.getApplicant());
                }
                if (deviationPermitApplication.getApplicationdate() != null) {
                    existingDeviationPermitApplication.setApplicationdate(deviationPermitApplication.getApplicationdate());
                }
                if (deviationPermitApplication.getPermitcontent() != null) {
                    existingDeviationPermitApplication.setPermitcontent(deviationPermitApplication.getPermitcontent());
                }
                if (deviationPermitApplication.getPermitreason() != null) {
                    existingDeviationPermitApplication.setPermitreason(deviationPermitApplication.getPermitreason());
                }
                if (deviationPermitApplication.getProjectinfluence() != null) {
                    existingDeviationPermitApplication.setProjectinfluence(deviationPermitApplication.getProjectinfluence());
                }
                if (deviationPermitApplication.getContractinfluence() != null) {
                    existingDeviationPermitApplication.setContractinfluence(deviationPermitApplication.getContractinfluence());
                }
                if (deviationPermitApplication.getPermitrange() != null) {
                    existingDeviationPermitApplication.setPermitrange(deviationPermitApplication.getPermitrange());
                }
                if (deviationPermitApplication.getImplementationdate() != null) {
                    existingDeviationPermitApplication.setImplementationdate(deviationPermitApplication.getImplementationdate());
                }
                if (deviationPermitApplication.getRemarks() != null) {
                    existingDeviationPermitApplication.setRemarks(deviationPermitApplication.getRemarks());
                }
                if (deviationPermitApplication.getAuditStatus() != null) {
                    existingDeviationPermitApplication.setAuditStatus(deviationPermitApplication.getAuditStatus());
                }

                return existingDeviationPermitApplication;
            })
            .map(deviationPermitApplicationRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DeviationPermitApplication> findAll() {
        log.debug("Request to get all DeviationPermitApplications");
        return deviationPermitApplicationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DeviationPermitApplication> findOne(Integer id) {
        log.debug("Request to get DeviationPermitApplication : {}", id);
        return deviationPermitApplicationRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete DeviationPermitApplication : {}", id);
        deviationPermitApplicationRepository.deleteById(id);
    }
}
