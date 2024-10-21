package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.RiskLevel;
import com.cvicse.jy1.repository.RiskLevelRepository;
import com.cvicse.jy1.service.RiskLevelService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.RiskLevel}.
 */
@Service
@Transactional
public class RiskLevelServiceImpl implements RiskLevelService {

    private static final Logger log = LoggerFactory.getLogger(RiskLevelServiceImpl.class);

    private final RiskLevelRepository riskLevelRepository;

    public RiskLevelServiceImpl(RiskLevelRepository riskLevelRepository) {
        this.riskLevelRepository = riskLevelRepository;
    }

    @Override
    public RiskLevel save(RiskLevel riskLevel) {
        log.debug("Request to save RiskLevel : {}", riskLevel);
        return riskLevelRepository.save(riskLevel);
    }

    @Override
    public RiskLevel update(RiskLevel riskLevel) {
        log.debug("Request to update RiskLevel : {}", riskLevel);
        return riskLevelRepository.save(riskLevel);
    }

    @Override
    public Optional<RiskLevel> partialUpdate(RiskLevel riskLevel) {
        log.debug("Request to partially update RiskLevel : {}", riskLevel);

        return riskLevelRepository
            .findById(riskLevel.getId())
            .map(existingRiskLevel -> {
                if (riskLevel.getName() != null) {
                    existingRiskLevel.setName(riskLevel.getName());
                }

                return existingRiskLevel;
            })
            .map(riskLevelRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RiskLevel> findAll() {
        log.debug("Request to get all RiskLevels");
        return riskLevelRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RiskLevel> findOne(Integer id) {
        log.debug("Request to get RiskLevel : {}", id);
        return riskLevelRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete RiskLevel : {}", id);
        riskLevelRepository.deleteById(id);
    }
}
