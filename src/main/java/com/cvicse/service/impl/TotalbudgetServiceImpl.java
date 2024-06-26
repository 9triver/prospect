package com.cvicse.service.impl;

import com.cvicse.domain.Totalbudget;
import com.cvicse.repository.TotalbudgetRepository;
import com.cvicse.service.TotalbudgetService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Totalbudget}.
 */
@Service
@Transactional
public class TotalbudgetServiceImpl implements TotalbudgetService {

    private final Logger log = LoggerFactory.getLogger(TotalbudgetServiceImpl.class);

    private final TotalbudgetRepository totalbudgetRepository;

    public TotalbudgetServiceImpl(TotalbudgetRepository totalbudgetRepository) {
        this.totalbudgetRepository = totalbudgetRepository;
    }

    @Override
    public Totalbudget save(Totalbudget totalbudget) {
        log.debug("Request to save Totalbudget : {}", totalbudget);
        return totalbudgetRepository.save(totalbudget);
    }

    @Override
    public Totalbudget update(Totalbudget totalbudget) {
        log.debug("Request to update Totalbudget : {}", totalbudget);
        return totalbudgetRepository.save(totalbudget);
    }

    @Override
    public Optional<Totalbudget> partialUpdate(Totalbudget totalbudget) {
        log.debug("Request to partially update Totalbudget : {}", totalbudget);

        return totalbudgetRepository
            .findById(totalbudget.getId())
            .map(existingTotalbudget -> {
                if (totalbudget.getValuationsubjects() != null) {
                    existingTotalbudget.setValuationsubjects(totalbudget.getValuationsubjects());
                }
                if (totalbudget.getBudget() != null) {
                    existingTotalbudget.setBudget(totalbudget.getBudget());
                }
                if (totalbudget.getPercentage() != null) {
                    existingTotalbudget.setPercentage(totalbudget.getPercentage());
                }
                if (totalbudget.getRemarks() != null) {
                    existingTotalbudget.setRemarks(totalbudget.getRemarks());
                }

                return existingTotalbudget;
            })
            .map(totalbudgetRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Totalbudget> findAll() {
        log.debug("Request to get all Totalbudgets");
        return totalbudgetRepository.findAll();
    }

    /**
     *  Get all the totalbudgets where Comprehensivecontrol is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Totalbudget> findAllWhereComprehensivecontrolIsNull() {
        log.debug("Request to get all totalbudgets where Comprehensivecontrol is null");
        return StreamSupport.stream(totalbudgetRepository.findAll().spliterator(), false)
            .filter(totalbudget -> totalbudget.getComprehensivecontrol() == null)
            .toList();
    }

    /**
     *  Get all the totalbudgets where Auditedbudget is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Totalbudget> findAllWhereAuditedbudgetIsNull() {
        log.debug("Request to get all totalbudgets where Auditedbudget is null");
        return StreamSupport.stream(totalbudgetRepository.findAll().spliterator(), false)
            .filter(totalbudget -> totalbudget.getAuditedbudget() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Totalbudget> findOne(String id) {
        log.debug("Request to get Totalbudget : {}", id);
        return totalbudgetRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Totalbudget : {}", id);
        totalbudgetRepository.deleteById(id);
    }
}
