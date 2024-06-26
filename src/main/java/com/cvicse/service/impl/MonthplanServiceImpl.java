package com.cvicse.service.impl;

import com.cvicse.domain.Monthplan;
import com.cvicse.repository.MonthplanRepository;
import com.cvicse.service.MonthplanService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Monthplan}.
 */
@Service
@Transactional
public class MonthplanServiceImpl implements MonthplanService {

    private final Logger log = LoggerFactory.getLogger(MonthplanServiceImpl.class);

    private final MonthplanRepository monthplanRepository;

    public MonthplanServiceImpl(MonthplanRepository monthplanRepository) {
        this.monthplanRepository = monthplanRepository;
    }

    @Override
    public Monthplan save(Monthplan monthplan) {
        log.debug("Request to save Monthplan : {}", monthplan);
        return monthplanRepository.save(monthplan);
    }

    @Override
    public Monthplan update(Monthplan monthplan) {
        log.debug("Request to update Monthplan : {}", monthplan);
        return monthplanRepository.save(monthplan);
    }

    @Override
    public Optional<Monthplan> partialUpdate(Monthplan monthplan) {
        log.debug("Request to partially update Monthplan : {}", monthplan);

        return monthplanRepository
            .findById(monthplan.getId())
            .map(existingMonthplan -> {
                if (monthplan.getMonthplanname() != null) {
                    existingMonthplan.setMonthplanname(monthplan.getMonthplanname());
                }
                if (monthplan.getMonth() != null) {
                    existingMonthplan.setMonth(monthplan.getMonth());
                }
                if (monthplan.getSecretlevel() != null) {
                    existingMonthplan.setSecretlevel(monthplan.getSecretlevel());
                }
                if (monthplan.getCreatorname() != null) {
                    existingMonthplan.setCreatorname(monthplan.getCreatorname());
                }
                if (monthplan.getStatus() != null) {
                    existingMonthplan.setStatus(monthplan.getStatus());
                }
                if (monthplan.getAuditStatus() != null) {
                    existingMonthplan.setAuditStatus(monthplan.getAuditStatus());
                }

                return existingMonthplan;
            })
            .map(monthplanRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Monthplan> findAll() {
        log.debug("Request to get all Monthplans");
        return monthplanRepository.findAll();
    }

    /**
     *  Get all the monthplans where Cycleplan is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Monthplan> findAllWhereCycleplanIsNull() {
        log.debug("Request to get all monthplans where Cycleplan is null");
        return StreamSupport.stream(monthplanRepository.findAll().spliterator(), false)
            .filter(monthplan -> monthplan.getCycleplan() == null)
            .toList();
    }

    /**
     *  Get all the monthplans where Annualplan is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Monthplan> findAllWhereAnnualplanIsNull() {
        log.debug("Request to get all monthplans where Annualplan is null");
        return StreamSupport.stream(monthplanRepository.findAll().spliterator(), false)
            .filter(monthplan -> monthplan.getAnnualplan() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Monthplan> findOne(String id) {
        log.debug("Request to get Monthplan : {}", id);
        return monthplanRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Monthplan : {}", id);
        monthplanRepository.deleteById(id);
    }
}
