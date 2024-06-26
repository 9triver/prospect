package com.cvicse.service.impl;

import com.cvicse.domain.Riskreport;
import com.cvicse.repository.RiskreportRepository;
import com.cvicse.service.RiskreportService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Riskreport}.
 */
@Service
@Transactional
public class RiskreportServiceImpl implements RiskreportService {

    private final Logger log = LoggerFactory.getLogger(RiskreportServiceImpl.class);

    private final RiskreportRepository riskreportRepository;

    public RiskreportServiceImpl(RiskreportRepository riskreportRepository) {
        this.riskreportRepository = riskreportRepository;
    }

    @Override
    public Riskreport save(Riskreport riskreport) {
        log.debug("Request to save Riskreport : {}", riskreport);
        return riskreportRepository.save(riskreport);
    }

    @Override
    public Riskreport update(Riskreport riskreport) {
        log.debug("Request to update Riskreport : {}", riskreport);
        return riskreportRepository.save(riskreport);
    }

    @Override
    public Optional<Riskreport> partialUpdate(Riskreport riskreport) {
        log.debug("Request to partially update Riskreport : {}", riskreport);

        return riskreportRepository
            .findById(riskreport.getId())
            .map(existingRiskreport -> {
                if (riskreport.getType() != null) {
                    existingRiskreport.setType(riskreport.getType());
                }
                if (riskreport.getRiskreportname() != null) {
                    existingRiskreport.setRiskreportname(riskreport.getRiskreportname());
                }
                if (riskreport.getReleasetime() != null) {
                    existingRiskreport.setReleasetime(riskreport.getReleasetime());
                }
                if (riskreport.getAuditStatus() != null) {
                    existingRiskreport.setAuditStatus(riskreport.getAuditStatus());
                }

                return existingRiskreport;
            })
            .map(riskreportRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Riskreport> findAll() {
        log.debug("Request to get all Riskreports");
        return riskreportRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Riskreport> findOne(String id) {
        log.debug("Request to get Riskreport : {}", id);
        return riskreportRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Riskreport : {}", id);
        riskreportRepository.deleteById(id);
    }
}
