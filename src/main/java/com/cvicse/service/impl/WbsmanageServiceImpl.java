package com.cvicse.service.impl;

import com.cvicse.domain.Wbsmanage;
import com.cvicse.repository.WbsmanageRepository;
import com.cvicse.service.WbsmanageService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Wbsmanage}.
 */
@Service
@Transactional
public class WbsmanageServiceImpl implements WbsmanageService {

    private final Logger log = LoggerFactory.getLogger(WbsmanageServiceImpl.class);

    private final WbsmanageRepository wbsmanageRepository;

    public WbsmanageServiceImpl(WbsmanageRepository wbsmanageRepository) {
        this.wbsmanageRepository = wbsmanageRepository;
    }

    @Override
    public Wbsmanage save(Wbsmanage wbsmanage) {
        log.debug("Request to save Wbsmanage : {}", wbsmanage);
        return wbsmanageRepository.save(wbsmanage);
    }

    @Override
    public Wbsmanage update(Wbsmanage wbsmanage) {
        log.debug("Request to update Wbsmanage : {}", wbsmanage);
        return wbsmanageRepository.save(wbsmanage);
    }

    @Override
    public Optional<Wbsmanage> partialUpdate(Wbsmanage wbsmanage) {
        log.debug("Request to partially update Wbsmanage : {}", wbsmanage);

        return wbsmanageRepository
            .findById(wbsmanage.getId())
            .map(existingWbsmanage -> {
                if (wbsmanage.getWbsname() != null) {
                    existingWbsmanage.setWbsname(wbsmanage.getWbsname());
                }
                if (wbsmanage.getDescription() != null) {
                    existingWbsmanage.setDescription(wbsmanage.getDescription());
                }
                if (wbsmanage.getResult() != null) {
                    existingWbsmanage.setResult(wbsmanage.getResult());
                }
                if (wbsmanage.getAdministratorname() != null) {
                    existingWbsmanage.setAdministratorname(wbsmanage.getAdministratorname());
                }
                if (wbsmanage.getResponsiblename() != null) {
                    existingWbsmanage.setResponsiblename(wbsmanage.getResponsiblename());
                }
                if (wbsmanage.getDepartment() != null) {
                    existingWbsmanage.setDepartment(wbsmanage.getDepartment());
                }
                if (wbsmanage.getSecretlevel() != null) {
                    existingWbsmanage.setSecretlevel(wbsmanage.getSecretlevel());
                }
                if (wbsmanage.getAuditStatus() != null) {
                    existingWbsmanage.setAuditStatus(wbsmanage.getAuditStatus());
                }

                return existingWbsmanage;
            })
            .map(wbsmanageRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Wbsmanage> findAll() {
        log.debug("Request to get all Wbsmanages");
        return wbsmanageRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Wbsmanage> findOne(String id) {
        log.debug("Request to get Wbsmanage : {}", id);
        return wbsmanageRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Wbsmanage : {}", id);
        wbsmanageRepository.deleteById(id);
    }
}
