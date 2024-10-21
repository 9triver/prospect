package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.RegularInspection;
import com.cvicse.jy1.repository.RegularInspectionRepository;
import com.cvicse.jy1.service.RegularInspectionService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.RegularInspection}.
 */
@Service
@Transactional
public class RegularInspectionServiceImpl implements RegularInspectionService {

    private static final Logger log = LoggerFactory.getLogger(RegularInspectionServiceImpl.class);

    private final RegularInspectionRepository regularInspectionRepository;

    public RegularInspectionServiceImpl(RegularInspectionRepository regularInspectionRepository) {
        this.regularInspectionRepository = regularInspectionRepository;
    }

    @Override
    public RegularInspection save(RegularInspection regularInspection) {
        log.debug("Request to save RegularInspection : {}", regularInspection);
        return regularInspectionRepository.save(regularInspection);
    }

    @Override
    public RegularInspection update(RegularInspection regularInspection) {
        log.debug("Request to update RegularInspection : {}", regularInspection);
        return regularInspectionRepository.save(regularInspection);
    }

    @Override
    public Optional<RegularInspection> partialUpdate(RegularInspection regularInspection) {
        log.debug("Request to partially update RegularInspection : {}", regularInspection);

        return regularInspectionRepository
            .findById(regularInspection.getId())
            .map(existingRegularInspection -> {
                if (regularInspection.getName() != null) {
                    existingRegularInspection.setName(regularInspection.getName());
                }
                if (regularInspection.getWorkbagid() != null) {
                    existingRegularInspection.setWorkbagid(regularInspection.getWorkbagid());
                }
                if (regularInspection.getWorkbagname() != null) {
                    existingRegularInspection.setWorkbagname(regularInspection.getWorkbagname());
                }
                if (regularInspection.getType() != null) {
                    existingRegularInspection.setType(regularInspection.getType());
                }
                if (regularInspection.getSecretlevel() != null) {
                    existingRegularInspection.setSecretlevel(regularInspection.getSecretlevel());
                }
                if (regularInspection.getStandard() != null) {
                    existingRegularInspection.setStandard(regularInspection.getStandard());
                }
                if (regularInspection.getMeasurementmethod() != null) {
                    existingRegularInspection.setMeasurementmethod(regularInspection.getMeasurementmethod());
                }
                if (regularInspection.getCheckresult() != null) {
                    existingRegularInspection.setCheckresult(regularInspection.getCheckresult());
                }
                if (regularInspection.getChecktarget() != null) {
                    existingRegularInspection.setChecktarget(regularInspection.getChecktarget());
                }
                if (regularInspection.getChecktime() != null) {
                    existingRegularInspection.setChecktime(regularInspection.getChecktime());
                }
                if (regularInspection.getCheckcompletion() != null) {
                    existingRegularInspection.setCheckcompletion(regularInspection.getCheckcompletion());
                }
                if (regularInspection.getCheckstatus() != null) {
                    existingRegularInspection.setCheckstatus(regularInspection.getCheckstatus());
                }
                if (regularInspection.getAuditStatus() != null) {
                    existingRegularInspection.setAuditStatus(regularInspection.getAuditStatus());
                }

                return existingRegularInspection;
            })
            .map(regularInspectionRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegularInspection> findAll() {
        log.debug("Request to get all RegularInspections");
        return regularInspectionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RegularInspection> findOne(Integer id) {
        log.debug("Request to get RegularInspection : {}", id);
        return regularInspectionRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete RegularInspection : {}", id);
        regularInspectionRepository.deleteById(id);
    }
}
