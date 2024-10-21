package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.RiskReturn;
import com.cvicse.jy1.repository.RiskReturnRepository;
import com.cvicse.jy1.service.RiskReturnService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.RiskReturn}.
 */
@Service
@Transactional
public class RiskReturnServiceImpl implements RiskReturnService {

    private static final Logger log = LoggerFactory.getLogger(RiskReturnServiceImpl.class);

    private final RiskReturnRepository riskReturnRepository;

    public RiskReturnServiceImpl(RiskReturnRepository riskReturnRepository) {
        this.riskReturnRepository = riskReturnRepository;
    }

    @Override
    public RiskReturn save(RiskReturn riskReturn) {
        log.debug("Request to save RiskReturn : {}", riskReturn);
        return riskReturnRepository.save(riskReturn);
    }

    @Override
    public RiskReturn update(RiskReturn riskReturn) {
        log.debug("Request to update RiskReturn : {}", riskReturn);
        return riskReturnRepository.save(riskReturn);
    }

    @Override
    public Optional<RiskReturn> partialUpdate(RiskReturn riskReturn) {
        log.debug("Request to partially update RiskReturn : {}", riskReturn);

        return riskReturnRepository
            .findById(riskReturn.getId())
            .map(existingRiskReturn -> {
                if (riskReturn.getBelongriskid() != null) {
                    existingRiskReturn.setBelongriskid(riskReturn.getBelongriskid());
                }
                if (riskReturn.getStatus() != null) {
                    existingRiskReturn.setStatus(riskReturn.getStatus());
                }
                if (riskReturn.getClosestatus() != null) {
                    existingRiskReturn.setClosestatus(riskReturn.getClosestatus());
                }
                if (riskReturn.getEvidencefile() != null) {
                    existingRiskReturn.setEvidencefile(riskReturn.getEvidencefile());
                }

                return existingRiskReturn;
            })
            .map(riskReturnRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RiskReturn> findAll() {
        log.debug("Request to get all RiskReturns");
        return riskReturnRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RiskReturn> findOne(Integer id) {
        log.debug("Request to get RiskReturn : {}", id);
        return riskReturnRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete RiskReturn : {}", id);
        riskReturnRepository.deleteById(id);
    }
}
