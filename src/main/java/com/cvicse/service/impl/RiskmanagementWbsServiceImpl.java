package com.cvicse.service.impl;

import com.cvicse.domain.RiskmanagementWbs;
import com.cvicse.repository.RiskmanagementWbsRepository;
import com.cvicse.service.RiskmanagementWbsService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.RiskmanagementWbs}.
 */
@Service
@Transactional
public class RiskmanagementWbsServiceImpl implements RiskmanagementWbsService {

    private final Logger log = LoggerFactory.getLogger(RiskmanagementWbsServiceImpl.class);

    private final RiskmanagementWbsRepository riskmanagementWbsRepository;

    public RiskmanagementWbsServiceImpl(RiskmanagementWbsRepository riskmanagementWbsRepository) {
        this.riskmanagementWbsRepository = riskmanagementWbsRepository;
    }

    @Override
    public RiskmanagementWbs save(RiskmanagementWbs riskmanagementWbs) {
        log.debug("Request to save RiskmanagementWbs : {}", riskmanagementWbs);
        return riskmanagementWbsRepository.save(riskmanagementWbs);
    }

    @Override
    public RiskmanagementWbs update(RiskmanagementWbs riskmanagementWbs) {
        log.debug("Request to update RiskmanagementWbs : {}", riskmanagementWbs);
        return riskmanagementWbsRepository.save(riskmanagementWbs);
    }

    @Override
    public Optional<RiskmanagementWbs> partialUpdate(RiskmanagementWbs riskmanagementWbs) {
        log.debug("Request to partially update RiskmanagementWbs : {}", riskmanagementWbs);

        return riskmanagementWbsRepository
            .findById(riskmanagementWbs.getId())
            .map(existingRiskmanagementWbs -> {
                if (riskmanagementWbs.getWbsspare1() != null) {
                    existingRiskmanagementWbs.setWbsspare1(riskmanagementWbs.getWbsspare1());
                }
                if (riskmanagementWbs.getWbsspare2() != null) {
                    existingRiskmanagementWbs.setWbsspare2(riskmanagementWbs.getWbsspare2());
                }
                if (riskmanagementWbs.getWbsspare3() != null) {
                    existingRiskmanagementWbs.setWbsspare3(riskmanagementWbs.getWbsspare3());
                }
                if (riskmanagementWbs.getWbsspare4() != null) {
                    existingRiskmanagementWbs.setWbsspare4(riskmanagementWbs.getWbsspare4());
                }
                if (riskmanagementWbs.getWbsspare5() != null) {
                    existingRiskmanagementWbs.setWbsspare5(riskmanagementWbs.getWbsspare5());
                }

                return existingRiskmanagementWbs;
            })
            .map(riskmanagementWbsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RiskmanagementWbs> findAll() {
        log.debug("Request to get all RiskmanagementWbs");
        return riskmanagementWbsRepository.findAll();
    }

    /**
     *  Get all the riskmanagementWbs where Riskmanagement is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RiskmanagementWbs> findAllWhereRiskmanagementIsNull() {
        log.debug("Request to get all riskmanagementWbs where Riskmanagement is null");
        return StreamSupport.stream(riskmanagementWbsRepository.findAll().spliterator(), false)
            .filter(riskmanagementWbs -> riskmanagementWbs.getRiskmanagement() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RiskmanagementWbs> findOne(String id) {
        log.debug("Request to get RiskmanagementWbs : {}", id);
        return riskmanagementWbsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete RiskmanagementWbs : {}", id);
        riskmanagementWbsRepository.deleteById(id);
    }
}
