package com.cvicse.service.impl;

import com.cvicse.domain.Integratedmanagement;
import com.cvicse.repository.IntegratedmanagementRepository;
import com.cvicse.service.IntegratedmanagementService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Integratedmanagement}.
 */
@Service
@Transactional
public class IntegratedmanagementServiceImpl implements IntegratedmanagementService {

    private final Logger log = LoggerFactory.getLogger(IntegratedmanagementServiceImpl.class);

    private final IntegratedmanagementRepository integratedmanagementRepository;

    public IntegratedmanagementServiceImpl(IntegratedmanagementRepository integratedmanagementRepository) {
        this.integratedmanagementRepository = integratedmanagementRepository;
    }

    @Override
    public Integratedmanagement save(Integratedmanagement integratedmanagement) {
        log.debug("Request to save Integratedmanagement : {}", integratedmanagement);
        return integratedmanagementRepository.save(integratedmanagement);
    }

    @Override
    public Integratedmanagement update(Integratedmanagement integratedmanagement) {
        log.debug("Request to update Integratedmanagement : {}", integratedmanagement);
        return integratedmanagementRepository.save(integratedmanagement);
    }

    @Override
    public Optional<Integratedmanagement> partialUpdate(Integratedmanagement integratedmanagement) {
        log.debug("Request to partially update Integratedmanagement : {}", integratedmanagement);

        return integratedmanagementRepository
            .findById(integratedmanagement.getId())
            .map(existingIntegratedmanagement -> {
                if (integratedmanagement.getName() != null) {
                    existingIntegratedmanagement.setName(integratedmanagement.getName());
                }
                if (integratedmanagement.getDescription() != null) {
                    existingIntegratedmanagement.setDescription(integratedmanagement.getDescription());
                }

                return existingIntegratedmanagement;
            })
            .map(integratedmanagementRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Integratedmanagement> findAll() {
        log.debug("Request to get all Integratedmanagements");
        return integratedmanagementRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Integratedmanagement> findOne(Long id) {
        log.debug("Request to get Integratedmanagement : {}", id);
        return integratedmanagementRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Integratedmanagement : {}", id);
        integratedmanagementRepository.deleteById(id);
    }
}
