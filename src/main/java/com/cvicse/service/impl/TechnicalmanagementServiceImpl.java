package com.cvicse.service.impl;

import com.cvicse.domain.Technicalmanagement;
import com.cvicse.repository.TechnicalmanagementRepository;
import com.cvicse.service.TechnicalmanagementService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Technicalmanagement}.
 */
@Service
@Transactional
public class TechnicalmanagementServiceImpl implements TechnicalmanagementService {

    private final Logger log = LoggerFactory.getLogger(TechnicalmanagementServiceImpl.class);

    private final TechnicalmanagementRepository technicalmanagementRepository;

    public TechnicalmanagementServiceImpl(TechnicalmanagementRepository technicalmanagementRepository) {
        this.technicalmanagementRepository = technicalmanagementRepository;
    }

    @Override
    public Technicalmanagement save(Technicalmanagement technicalmanagement) {
        log.debug("Request to save Technicalmanagement : {}", technicalmanagement);
        return technicalmanagementRepository.save(technicalmanagement);
    }

    @Override
    public Technicalmanagement update(Technicalmanagement technicalmanagement) {
        log.debug("Request to update Technicalmanagement : {}", technicalmanagement);
        return technicalmanagementRepository.save(technicalmanagement);
    }

    @Override
    public Optional<Technicalmanagement> partialUpdate(Technicalmanagement technicalmanagement) {
        log.debug("Request to partially update Technicalmanagement : {}", technicalmanagement);

        return technicalmanagementRepository
            .findById(technicalmanagement.getId())
            .map(existingTechnicalmanagement -> {
                if (technicalmanagement.getName() != null) {
                    existingTechnicalmanagement.setName(technicalmanagement.getName());
                }
                if (technicalmanagement.getDescription() != null) {
                    existingTechnicalmanagement.setDescription(technicalmanagement.getDescription());
                }
                if (technicalmanagement.getStarttime() != null) {
                    existingTechnicalmanagement.setStarttime(technicalmanagement.getStarttime());
                }
                if (technicalmanagement.getEndtime() != null) {
                    existingTechnicalmanagement.setEndtime(technicalmanagement.getEndtime());
                }

                return existingTechnicalmanagement;
            })
            .map(technicalmanagementRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Technicalmanagement> findAll() {
        log.debug("Request to get all Technicalmanagements");
        return technicalmanagementRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Technicalmanagement> findOne(String id) {
        log.debug("Request to get Technicalmanagement : {}", id);
        return technicalmanagementRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Technicalmanagement : {}", id);
        technicalmanagementRepository.deleteById(id);
    }
}
