package com.cvicse.service.impl;

import com.cvicse.domain.Secrecymanagement;
import com.cvicse.repository.SecrecymanagementRepository;
import com.cvicse.service.SecrecymanagementService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Secrecymanagement}.
 */
@Service
@Transactional
public class SecrecymanagementServiceImpl implements SecrecymanagementService {

    private final Logger log = LoggerFactory.getLogger(SecrecymanagementServiceImpl.class);

    private final SecrecymanagementRepository secrecymanagementRepository;

    public SecrecymanagementServiceImpl(SecrecymanagementRepository secrecymanagementRepository) {
        this.secrecymanagementRepository = secrecymanagementRepository;
    }

    @Override
    public Secrecymanagement save(Secrecymanagement secrecymanagement) {
        log.debug("Request to save Secrecymanagement : {}", secrecymanagement);
        return secrecymanagementRepository.save(secrecymanagement);
    }

    @Override
    public Secrecymanagement update(Secrecymanagement secrecymanagement) {
        log.debug("Request to update Secrecymanagement : {}", secrecymanagement);
        return secrecymanagementRepository.save(secrecymanagement);
    }

    @Override
    public Optional<Secrecymanagement> partialUpdate(Secrecymanagement secrecymanagement) {
        log.debug("Request to partially update Secrecymanagement : {}", secrecymanagement);

        return secrecymanagementRepository
            .findById(secrecymanagement.getId())
            .map(existingSecrecymanagement -> {
                if (secrecymanagement.getName() != null) {
                    existingSecrecymanagement.setName(secrecymanagement.getName());
                }
                if (secrecymanagement.getDescription() != null) {
                    existingSecrecymanagement.setDescription(secrecymanagement.getDescription());
                }
                if (secrecymanagement.getStarttime() != null) {
                    existingSecrecymanagement.setStarttime(secrecymanagement.getStarttime());
                }
                if (secrecymanagement.getEndtime() != null) {
                    existingSecrecymanagement.setEndtime(secrecymanagement.getEndtime());
                }

                return existingSecrecymanagement;
            })
            .map(secrecymanagementRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Secrecymanagement> findAll() {
        log.debug("Request to get all Secrecymanagements");
        return secrecymanagementRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Secrecymanagement> findOne(String id) {
        log.debug("Request to get Secrecymanagement : {}", id);
        return secrecymanagementRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Secrecymanagement : {}", id);
        secrecymanagementRepository.deleteById(id);
    }
}
