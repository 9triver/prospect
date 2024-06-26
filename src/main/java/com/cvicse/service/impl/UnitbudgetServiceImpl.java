package com.cvicse.service.impl;

import com.cvicse.domain.Unitbudget;
import com.cvicse.repository.UnitbudgetRepository;
import com.cvicse.service.UnitbudgetService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Unitbudget}.
 */
@Service
@Transactional
public class UnitbudgetServiceImpl implements UnitbudgetService {

    private final Logger log = LoggerFactory.getLogger(UnitbudgetServiceImpl.class);

    private final UnitbudgetRepository unitbudgetRepository;

    public UnitbudgetServiceImpl(UnitbudgetRepository unitbudgetRepository) {
        this.unitbudgetRepository = unitbudgetRepository;
    }

    @Override
    public Unitbudget save(Unitbudget unitbudget) {
        log.debug("Request to save Unitbudget : {}", unitbudget);
        return unitbudgetRepository.save(unitbudget);
    }

    @Override
    public Unitbudget update(Unitbudget unitbudget) {
        log.debug("Request to update Unitbudget : {}", unitbudget);
        return unitbudgetRepository.save(unitbudget);
    }

    @Override
    public Optional<Unitbudget> partialUpdate(Unitbudget unitbudget) {
        log.debug("Request to partially update Unitbudget : {}", unitbudget);

        return unitbudgetRepository
            .findById(unitbudget.getId())
            .map(existingUnitbudget -> {
                if (unitbudget.getSubprojectname() != null) {
                    existingUnitbudget.setSubprojectname(unitbudget.getSubprojectname());
                }
                if (unitbudget.getUnitbudgername() != null) {
                    existingUnitbudget.setUnitbudgername(unitbudget.getUnitbudgername());
                }
                if (unitbudget.getBillingunit() != null) {
                    existingUnitbudget.setBillingunit(unitbudget.getBillingunit());
                }
                if (unitbudget.getNumber() != null) {
                    existingUnitbudget.setNumber(unitbudget.getNumber());
                }
                if (unitbudget.getTotalbudget() != null) {
                    existingUnitbudget.setTotalbudget(unitbudget.getTotalbudget());
                }
                if (unitbudget.getMaintainerbudget() != null) {
                    existingUnitbudget.setMaintainerbudget(unitbudget.getMaintainerbudget());
                }
                if (unitbudget.getOutsourcingbudget() != null) {
                    existingUnitbudget.setOutsourcingbudget(unitbudget.getOutsourcingbudget());
                }
                if (unitbudget.getEarmarkedbudget() != null) {
                    existingUnitbudget.setEarmarkedbudget(unitbudget.getEarmarkedbudget());
                }
                if (unitbudget.getTestbudget() != null) {
                    existingUnitbudget.setTestbudget(unitbudget.getTestbudget());
                }

                return existingUnitbudget;
            })
            .map(unitbudgetRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Unitbudget> findAll() {
        log.debug("Request to get all Unitbudgets");
        return unitbudgetRepository.findAll();
    }

    /**
     *  Get all the unitbudgets where Comprehensivecontrol is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Unitbudget> findAllWhereComprehensivecontrolIsNull() {
        log.debug("Request to get all unitbudgets where Comprehensivecontrol is null");
        return StreamSupport.stream(unitbudgetRepository.findAll().spliterator(), false)
            .filter(unitbudget -> unitbudget.getComprehensivecontrol() == null)
            .toList();
    }

    /**
     *  Get all the unitbudgets where Auditedbudget is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Unitbudget> findAllWhereAuditedbudgetIsNull() {
        log.debug("Request to get all unitbudgets where Auditedbudget is null");
        return StreamSupport.stream(unitbudgetRepository.findAll().spliterator(), false)
            .filter(unitbudget -> unitbudget.getAuditedbudget() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Unitbudget> findOne(String id) {
        log.debug("Request to get Unitbudget : {}", id);
        return unitbudgetRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Unitbudget : {}", id);
        unitbudgetRepository.deleteById(id);
    }
}
