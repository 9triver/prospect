package com.cvicse.service.impl;

import com.cvicse.domain.Projectcharge;
import com.cvicse.repository.ProjectchargeRepository;
import com.cvicse.service.ProjectchargeService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Projectcharge}.
 */
@Service
@Transactional
public class ProjectchargeServiceImpl implements ProjectchargeService {

    private final Logger log = LoggerFactory.getLogger(ProjectchargeServiceImpl.class);

    private final ProjectchargeRepository projectchargeRepository;

    public ProjectchargeServiceImpl(ProjectchargeRepository projectchargeRepository) {
        this.projectchargeRepository = projectchargeRepository;
    }

    @Override
    public Projectcharge save(Projectcharge projectcharge) {
        log.debug("Request to save Projectcharge : {}", projectcharge);
        return projectchargeRepository.save(projectcharge);
    }

    @Override
    public Projectcharge update(Projectcharge projectcharge) {
        log.debug("Request to update Projectcharge : {}", projectcharge);
        return projectchargeRepository.save(projectcharge);
    }

    @Override
    public Optional<Projectcharge> partialUpdate(Projectcharge projectcharge) {
        log.debug("Request to partially update Projectcharge : {}", projectcharge);

        return projectchargeRepository
            .findById(projectcharge.getId())
            .map(existingProjectcharge -> {
                if (projectcharge.getProjectname() != null) {
                    existingProjectcharge.setProjectname(projectcharge.getProjectname());
                }
                if (projectcharge.getFormid() != null) {
                    existingProjectcharge.setFormid(projectcharge.getFormid());
                }
                if (projectcharge.getStarttime() != null) {
                    existingProjectcharge.setStarttime(projectcharge.getStarttime());
                }
                if (projectcharge.getEndtime() != null) {
                    existingProjectcharge.setEndtime(projectcharge.getEndtime());
                }
                if (projectcharge.getSecretlevel() != null) {
                    existingProjectcharge.setSecretlevel(projectcharge.getSecretlevel());
                }
                if (projectcharge.getRequestdeportment() != null) {
                    existingProjectcharge.setRequestdeportment(projectcharge.getRequestdeportment());
                }
                if (projectcharge.getChargetype() != null) {
                    existingProjectcharge.setChargetype(projectcharge.getChargetype());
                }
                if (projectcharge.getChargecontent() != null) {
                    existingProjectcharge.setChargecontent(projectcharge.getChargecontent());
                }

                return existingProjectcharge;
            })
            .map(projectchargeRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Projectcharge> findAll() {
        log.debug("Request to get all Projectcharges");
        return projectchargeRepository.findAll();
    }

    /**
     *  Get all the projectcharges where Cycleplan is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Projectcharge> findAllWhereCycleplanIsNull() {
        log.debug("Request to get all projectcharges where Cycleplan is null");
        return StreamSupport.stream(projectchargeRepository.findAll().spliterator(), false)
            .filter(projectcharge -> projectcharge.getCycleplan() == null)
            .toList();
    }

    /**
     *  Get all the projectcharges where Annualplan is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Projectcharge> findAllWhereAnnualplanIsNull() {
        log.debug("Request to get all projectcharges where Annualplan is null");
        return StreamSupport.stream(projectchargeRepository.findAll().spliterator(), false)
            .filter(projectcharge -> projectcharge.getAnnualplan() == null)
            .toList();
    }

    /**
     *  Get all the projectcharges where Monthplan is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Projectcharge> findAllWhereMonthplanIsNull() {
        log.debug("Request to get all projectcharges where Monthplan is null");
        return StreamSupport.stream(projectchargeRepository.findAll().spliterator(), false)
            .filter(projectcharge -> projectcharge.getMonthplan() == null)
            .toList();
    }

    /**
     *  Get all the projectcharges where Pbsbaseline is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Projectcharge> findAllWherePbsbaselineIsNull() {
        log.debug("Request to get all projectcharges where Pbsbaseline is null");
        return StreamSupport.stream(projectchargeRepository.findAll().spliterator(), false)
            .filter(projectcharge -> projectcharge.getPbsbaseline() == null)
            .toList();
    }

    /**
     *  Get all the projectcharges where Wbsbaseline is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Projectcharge> findAllWhereWbsbaselineIsNull() {
        log.debug("Request to get all projectcharges where Wbsbaseline is null");
        return StreamSupport.stream(projectchargeRepository.findAll().spliterator(), false)
            .filter(projectcharge -> projectcharge.getWbsbaseline() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Projectcharge> findOne(Long id) {
        log.debug("Request to get Projectcharge : {}", id);
        return projectchargeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Projectcharge : {}", id);
        projectchargeRepository.deleteById(id);
    }
}
