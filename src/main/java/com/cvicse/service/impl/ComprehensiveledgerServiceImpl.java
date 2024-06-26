package com.cvicse.service.impl;

import com.cvicse.domain.Comprehensiveledger;
import com.cvicse.repository.ComprehensiveledgerRepository;
import com.cvicse.service.ComprehensiveledgerService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Comprehensiveledger}.
 */
@Service
@Transactional
public class ComprehensiveledgerServiceImpl implements ComprehensiveledgerService {

    private final Logger log = LoggerFactory.getLogger(ComprehensiveledgerServiceImpl.class);

    private final ComprehensiveledgerRepository comprehensiveledgerRepository;

    public ComprehensiveledgerServiceImpl(ComprehensiveledgerRepository comprehensiveledgerRepository) {
        this.comprehensiveledgerRepository = comprehensiveledgerRepository;
    }

    @Override
    public Comprehensiveledger save(Comprehensiveledger comprehensiveledger) {
        log.debug("Request to save Comprehensiveledger : {}", comprehensiveledger);
        return comprehensiveledgerRepository.save(comprehensiveledger);
    }

    @Override
    public Comprehensiveledger update(Comprehensiveledger comprehensiveledger) {
        log.debug("Request to update Comprehensiveledger : {}", comprehensiveledger);
        return comprehensiveledgerRepository.save(comprehensiveledger);
    }

    @Override
    public Optional<Comprehensiveledger> partialUpdate(Comprehensiveledger comprehensiveledger) {
        log.debug("Request to partially update Comprehensiveledger : {}", comprehensiveledger);

        return comprehensiveledgerRepository
            .findById(comprehensiveledger.getId())
            .map(existingComprehensiveledger -> {
                if (comprehensiveledger.getFundsname() != null) {
                    existingComprehensiveledger.setFundsname(comprehensiveledger.getFundsname());
                }
                if (comprehensiveledger.getWbsname() != null) {
                    existingComprehensiveledger.setWbsname(comprehensiveledger.getWbsname());
                }
                if (comprehensiveledger.getUnitname() != null) {
                    existingComprehensiveledger.setUnitname(comprehensiveledger.getUnitname());
                }
                if (comprehensiveledger.getBudgetsection() != null) {
                    existingComprehensiveledger.setBudgetsection(comprehensiveledger.getBudgetsection());
                }
                if (comprehensiveledger.getPurpose() != null) {
                    existingComprehensiveledger.setPurpose(comprehensiveledger.getPurpose());
                }
                if (comprehensiveledger.getUnit() != null) {
                    existingComprehensiveledger.setUnit(comprehensiveledger.getUnit());
                }
                if (comprehensiveledger.getNumber() != null) {
                    existingComprehensiveledger.setNumber(comprehensiveledger.getNumber());
                }
                if (comprehensiveledger.getUnitprice() != null) {
                    existingComprehensiveledger.setUnitprice(comprehensiveledger.getUnitprice());
                }
                if (comprehensiveledger.getForeignexchange() != null) {
                    existingComprehensiveledger.setForeignexchange(comprehensiveledger.getForeignexchange());
                }

                return existingComprehensiveledger;
            })
            .map(comprehensiveledgerRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comprehensiveledger> findAll() {
        log.debug("Request to get all Comprehensiveledgers");
        return comprehensiveledgerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comprehensiveledger> findOne(String id) {
        log.debug("Request to get Comprehensiveledger : {}", id);
        return comprehensiveledgerRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Comprehensiveledger : {}", id);
        comprehensiveledgerRepository.deleteById(id);
    }
}
