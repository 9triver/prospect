package com.cvicse.service.impl;

import com.cvicse.domain.Riskmanagement;
import com.cvicse.repository.RiskmanagementRepository;
import com.cvicse.service.RiskmanagementService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Riskmanagement}.
 */
@Service
@Transactional
public class RiskmanagementServiceImpl implements RiskmanagementService {

    private final Logger log = LoggerFactory.getLogger(RiskmanagementServiceImpl.class);

    private final RiskmanagementRepository riskmanagementRepository;

    public RiskmanagementServiceImpl(RiskmanagementRepository riskmanagementRepository) {
        this.riskmanagementRepository = riskmanagementRepository;
    }

    @Override
    public Riskmanagement save(Riskmanagement riskmanagement) {
        log.debug("Request to save Riskmanagement : {}", riskmanagement);
        return riskmanagementRepository.save(riskmanagement);
    }

    @Override
    public Riskmanagement update(Riskmanagement riskmanagement) {
        log.debug("Request to update Riskmanagement : {}", riskmanagement);
        return riskmanagementRepository.save(riskmanagement);
    }

    @Override
    public Optional<Riskmanagement> partialUpdate(Riskmanagement riskmanagement) {
        log.debug("Request to partially update Riskmanagement : {}", riskmanagement);

        return riskmanagementRepository
            .findById(riskmanagement.getId())
            .map(existingRiskmanagement -> {
                if (riskmanagement.getName() != null) {
                    existingRiskmanagement.setName(riskmanagement.getName());
                }
                if (riskmanagement.getDescription() != null) {
                    existingRiskmanagement.setDescription(riskmanagement.getDescription());
                }
                if (riskmanagement.getStarttime() != null) {
                    existingRiskmanagement.setStarttime(riskmanagement.getStarttime());
                }
                if (riskmanagement.getEndtime() != null) {
                    existingRiskmanagement.setEndtime(riskmanagement.getEndtime());
                }

                return existingRiskmanagement;
            })
            .map(riskmanagementRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Riskmanagement> findAll() {
        log.debug("Request to get all Riskmanagements");
        return riskmanagementRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Riskmanagement> findOne(String id) {
        log.debug("Request to get Riskmanagement : {}", id);
        return riskmanagementRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Riskmanagement : {}", id);
        riskmanagementRepository.deleteById(id);
    }
}
