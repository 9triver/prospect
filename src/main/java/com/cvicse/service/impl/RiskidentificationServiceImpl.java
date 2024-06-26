package com.cvicse.service.impl;

import com.cvicse.domain.Riskidentification;
import com.cvicse.repository.RiskidentificationRepository;
import com.cvicse.service.RiskidentificationService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Riskidentification}.
 */
@Service
@Transactional
public class RiskidentificationServiceImpl implements RiskidentificationService {

    private final Logger log = LoggerFactory.getLogger(RiskidentificationServiceImpl.class);

    private final RiskidentificationRepository riskidentificationRepository;

    public RiskidentificationServiceImpl(RiskidentificationRepository riskidentificationRepository) {
        this.riskidentificationRepository = riskidentificationRepository;
    }

    @Override
    public Riskidentification save(Riskidentification riskidentification) {
        log.debug("Request to save Riskidentification : {}", riskidentification);
        return riskidentificationRepository.save(riskidentification);
    }

    @Override
    public Riskidentification update(Riskidentification riskidentification) {
        log.debug("Request to update Riskidentification : {}", riskidentification);
        return riskidentificationRepository.save(riskidentification);
    }

    @Override
    public Optional<Riskidentification> partialUpdate(Riskidentification riskidentification) {
        log.debug("Request to partially update Riskidentification : {}", riskidentification);

        return riskidentificationRepository
            .findById(riskidentification.getId())
            .map(existingRiskidentification -> {
                if (riskidentification.getProjectname() != null) {
                    existingRiskidentification.setProjectname(riskidentification.getProjectname());
                }
                if (riskidentification.getYear() != null) {
                    existingRiskidentification.setYear(riskidentification.getYear());
                }
                if (riskidentification.getNodename() != null) {
                    existingRiskidentification.setNodename(riskidentification.getNodename());
                }
                if (riskidentification.getRisktype() != null) {
                    existingRiskidentification.setRisktype(riskidentification.getRisktype());
                }
                if (riskidentification.getDecumentid() != null) {
                    existingRiskidentification.setDecumentid(riskidentification.getDecumentid());
                }
                if (riskidentification.getVersion() != null) {
                    existingRiskidentification.setVersion(riskidentification.getVersion());
                }
                if (riskidentification.getUsetime() != null) {
                    existingRiskidentification.setUsetime(riskidentification.getUsetime());
                }
                if (riskidentification.getSystemlevel() != null) {
                    existingRiskidentification.setSystemlevel(riskidentification.getSystemlevel());
                }
                if (riskidentification.getRisklevel() != null) {
                    existingRiskidentification.setRisklevel(riskidentification.getRisklevel());
                }
                if (riskidentification.getLimitationtime() != null) {
                    existingRiskidentification.setLimitationtime(riskidentification.getLimitationtime());
                }
                if (riskidentification.getClosetype() != null) {
                    existingRiskidentification.setClosetype(riskidentification.getClosetype());
                }

                return existingRiskidentification;
            })
            .map(riskidentificationRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Riskidentification> findAll() {
        log.debug("Request to get all Riskidentifications");
        return riskidentificationRepository.findAll();
    }

    /**
     *  Get all the riskidentifications where Riskreport is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Riskidentification> findAllWhereRiskreportIsNull() {
        log.debug("Request to get all riskidentifications where Riskreport is null");
        return StreamSupport.stream(riskidentificationRepository.findAll().spliterator(), false)
            .filter(riskidentification -> riskidentification.getRiskreport() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Riskidentification> findOne(String id) {
        log.debug("Request to get Riskidentification : {}", id);
        return riskidentificationRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Riskidentification : {}", id);
        riskidentificationRepository.deleteById(id);
    }
}
