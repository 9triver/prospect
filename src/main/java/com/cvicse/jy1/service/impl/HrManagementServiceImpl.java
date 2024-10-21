package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.HrManagement;
import com.cvicse.jy1.repository.HrManagementRepository;
import com.cvicse.jy1.service.HrManagementService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.HrManagement}.
 */
@Service
@Transactional
public class HrManagementServiceImpl implements HrManagementService {

    private static final Logger log = LoggerFactory.getLogger(HrManagementServiceImpl.class);

    private final HrManagementRepository hrManagementRepository;

    public HrManagementServiceImpl(HrManagementRepository hrManagementRepository) {
        this.hrManagementRepository = hrManagementRepository;
    }

    @Override
    public HrManagement save(HrManagement hrManagement) {
        log.debug("Request to save HrManagement : {}", hrManagement);
        return hrManagementRepository.save(hrManagement);
    }

    @Override
    public HrManagement update(HrManagement hrManagement) {
        log.debug("Request to update HrManagement : {}", hrManagement);
        return hrManagementRepository.save(hrManagement);
    }

    @Override
    public Optional<HrManagement> partialUpdate(HrManagement hrManagement) {
        log.debug("Request to partially update HrManagement : {}", hrManagement);

        return hrManagementRepository
            .findById(hrManagement.getId())
            .map(existingHrManagement -> {
                if (hrManagement.getOfficersid() != null) {
                    existingHrManagement.setOfficersid(hrManagement.getOfficersid());
                }
                if (hrManagement.getOfficersname() != null) {
                    existingHrManagement.setOfficersname(hrManagement.getOfficersname());
                }
                if (hrManagement.getProjectid() != null) {
                    existingHrManagement.setProjectid(hrManagement.getProjectid());
                }
                if (hrManagement.getProjectname() != null) {
                    existingHrManagement.setProjectname(hrManagement.getProjectname());
                }
                if (hrManagement.getProjectrole() != null) {
                    existingHrManagement.setProjectrole(hrManagement.getProjectrole());
                }
                if (hrManagement.getJobgrade() != null) {
                    existingHrManagement.setJobgrade(hrManagement.getJobgrade());
                }
                if (hrManagement.getDepartmentid() != null) {
                    existingHrManagement.setDepartmentid(hrManagement.getDepartmentid());
                }
                if (hrManagement.getDepartmentname() != null) {
                    existingHrManagement.setDepartmentname(hrManagement.getDepartmentname());
                }
                if (hrManagement.getFrontlineid() != null) {
                    existingHrManagement.setFrontlineid(hrManagement.getFrontlineid());
                }
                if (hrManagement.getFrontlinename() != null) {
                    existingHrManagement.setFrontlinename(hrManagement.getFrontlinename());
                }
                if (hrManagement.getJobduty() != null) {
                    existingHrManagement.setJobduty(hrManagement.getJobduty());
                }
                if (hrManagement.getAnnualworktime() != null) {
                    existingHrManagement.setAnnualworktime(hrManagement.getAnnualworktime());
                }
                if (hrManagement.getAnnualtasktarget() != null) {
                    existingHrManagement.setAnnualtasktarget(hrManagement.getAnnualtasktarget());
                }

                return existingHrManagement;
            })
            .map(hrManagementRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HrManagement> findAll() {
        log.debug("Request to get all HrManagements");
        return hrManagementRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HrManagement> findOne(Integer id) {
        log.debug("Request to get HrManagement : {}", id);
        return hrManagementRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete HrManagement : {}", id);
        hrManagementRepository.deleteById(id);
    }
}
