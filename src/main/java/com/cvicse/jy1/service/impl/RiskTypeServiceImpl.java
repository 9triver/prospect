package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.RiskType;
import com.cvicse.jy1.repository.RiskTypeRepository;
import com.cvicse.jy1.service.RiskTypeService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.RiskType}.
 */
@Service
@Transactional
public class RiskTypeServiceImpl implements RiskTypeService {

    private static final Logger log = LoggerFactory.getLogger(RiskTypeServiceImpl.class);

    private final RiskTypeRepository riskTypeRepository;

    public RiskTypeServiceImpl(RiskTypeRepository riskTypeRepository) {
        this.riskTypeRepository = riskTypeRepository;
    }

    @Override
    public RiskType save(RiskType riskType) {
        log.debug("Request to save RiskType : {}", riskType);
        return riskTypeRepository.save(riskType);
    }

    @Override
    public RiskType update(RiskType riskType) {
        log.debug("Request to update RiskType : {}", riskType);
        return riskTypeRepository.save(riskType);
    }

    @Override
    public Optional<RiskType> partialUpdate(RiskType riskType) {
        log.debug("Request to partially update RiskType : {}", riskType);

        return riskTypeRepository
            .findById(riskType.getId())
            .map(existingRiskType -> {
                if (riskType.getName() != null) {
                    existingRiskType.setName(riskType.getName());
                }

                return existingRiskType;
            })
            .map(riskTypeRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RiskType> findAll() {
        log.debug("Request to get all RiskTypes");
        return riskTypeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RiskType> findOne(Integer id) {
        log.debug("Request to get RiskType : {}", id);
        return riskTypeRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete RiskType : {}", id);
        riskTypeRepository.deleteById(id);
    }
}
