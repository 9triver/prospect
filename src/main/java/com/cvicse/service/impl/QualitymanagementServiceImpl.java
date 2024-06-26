package com.cvicse.service.impl;

import com.cvicse.domain.Qualitymanagement;
import com.cvicse.repository.QualitymanagementRepository;
import com.cvicse.service.QualitymanagementService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Qualitymanagement}.
 */
@Service
@Transactional
public class QualitymanagementServiceImpl implements QualitymanagementService {

    private final Logger log = LoggerFactory.getLogger(QualitymanagementServiceImpl.class);

    private final QualitymanagementRepository qualitymanagementRepository;

    public QualitymanagementServiceImpl(QualitymanagementRepository qualitymanagementRepository) {
        this.qualitymanagementRepository = qualitymanagementRepository;
    }

    @Override
    public Qualitymanagement save(Qualitymanagement qualitymanagement) {
        log.debug("Request to save Qualitymanagement : {}", qualitymanagement);
        return qualitymanagementRepository.save(qualitymanagement);
    }

    @Override
    public Qualitymanagement update(Qualitymanagement qualitymanagement) {
        log.debug("Request to update Qualitymanagement : {}", qualitymanagement);
        return qualitymanagementRepository.save(qualitymanagement);
    }

    @Override
    public Optional<Qualitymanagement> partialUpdate(Qualitymanagement qualitymanagement) {
        log.debug("Request to partially update Qualitymanagement : {}", qualitymanagement);

        return qualitymanagementRepository
            .findById(qualitymanagement.getId())
            .map(existingQualitymanagement -> {
                if (qualitymanagement.getName() != null) {
                    existingQualitymanagement.setName(qualitymanagement.getName());
                }
                if (qualitymanagement.getDescription() != null) {
                    existingQualitymanagement.setDescription(qualitymanagement.getDescription());
                }
                if (qualitymanagement.getStarttime() != null) {
                    existingQualitymanagement.setStarttime(qualitymanagement.getStarttime());
                }
                if (qualitymanagement.getEndtime() != null) {
                    existingQualitymanagement.setEndtime(qualitymanagement.getEndtime());
                }

                return existingQualitymanagement;
            })
            .map(qualitymanagementRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Qualitymanagement> findAll() {
        log.debug("Request to get all Qualitymanagements");
        return qualitymanagementRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Qualitymanagement> findOne(String id) {
        log.debug("Request to get Qualitymanagement : {}", id);
        return qualitymanagementRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Qualitymanagement : {}", id);
        qualitymanagementRepository.deleteById(id);
    }
}
