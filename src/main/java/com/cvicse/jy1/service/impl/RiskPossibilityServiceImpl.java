package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.RiskPossibility;
import com.cvicse.jy1.repository.RiskPossibilityRepository;
import com.cvicse.jy1.service.RiskPossibilityService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.RiskPossibility}.
 */
@Service
@Transactional
public class RiskPossibilityServiceImpl implements RiskPossibilityService {

    private static final Logger log = LoggerFactory.getLogger(RiskPossibilityServiceImpl.class);

    private final RiskPossibilityRepository riskPossibilityRepository;

    public RiskPossibilityServiceImpl(RiskPossibilityRepository riskPossibilityRepository) {
        this.riskPossibilityRepository = riskPossibilityRepository;
    }

    @Override
    public RiskPossibility save(RiskPossibility riskPossibility) {
        log.debug("Request to save RiskPossibility : {}", riskPossibility);
        return riskPossibilityRepository.save(riskPossibility);
    }

    @Override
    public RiskPossibility update(RiskPossibility riskPossibility) {
        log.debug("Request to update RiskPossibility : {}", riskPossibility);
        return riskPossibilityRepository.save(riskPossibility);
    }

    @Override
    public Optional<RiskPossibility> partialUpdate(RiskPossibility riskPossibility) {
        log.debug("Request to partially update RiskPossibility : {}", riskPossibility);

        return riskPossibilityRepository
            .findById(riskPossibility.getId())
            .map(existingRiskPossibility -> {
                if (riskPossibility.getName() != null) {
                    existingRiskPossibility.setName(riskPossibility.getName());
                }

                return existingRiskPossibility;
            })
            .map(riskPossibilityRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RiskPossibility> findAll() {
        log.debug("Request to get all RiskPossibilities");
        return riskPossibilityRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RiskPossibility> findOne(Integer id) {
        log.debug("Request to get RiskPossibility : {}", id);
        return riskPossibilityRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete RiskPossibility : {}", id);
        riskPossibilityRepository.deleteById(id);
    }
}
