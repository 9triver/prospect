package com.cvicse.service.impl;

import com.cvicse.domain.Securitymanagement;
import com.cvicse.repository.SecuritymanagementRepository;
import com.cvicse.service.SecuritymanagementService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Securitymanagement}.
 */
@Service
@Transactional
public class SecuritymanagementServiceImpl implements SecuritymanagementService {

    private final Logger log = LoggerFactory.getLogger(SecuritymanagementServiceImpl.class);

    private final SecuritymanagementRepository securitymanagementRepository;

    public SecuritymanagementServiceImpl(SecuritymanagementRepository securitymanagementRepository) {
        this.securitymanagementRepository = securitymanagementRepository;
    }

    @Override
    public Securitymanagement save(Securitymanagement securitymanagement) {
        log.debug("Request to save Securitymanagement : {}", securitymanagement);
        return securitymanagementRepository.save(securitymanagement);
    }

    @Override
    public Securitymanagement update(Securitymanagement securitymanagement) {
        log.debug("Request to update Securitymanagement : {}", securitymanagement);
        return securitymanagementRepository.save(securitymanagement);
    }

    @Override
    public Optional<Securitymanagement> partialUpdate(Securitymanagement securitymanagement) {
        log.debug("Request to partially update Securitymanagement : {}", securitymanagement);

        return securitymanagementRepository
            .findById(securitymanagement.getId())
            .map(existingSecuritymanagement -> {
                if (securitymanagement.getName() != null) {
                    existingSecuritymanagement.setName(securitymanagement.getName());
                }
                if (securitymanagement.getDescription() != null) {
                    existingSecuritymanagement.setDescription(securitymanagement.getDescription());
                }
                if (securitymanagement.getStarttime() != null) {
                    existingSecuritymanagement.setStarttime(securitymanagement.getStarttime());
                }
                if (securitymanagement.getEndtime() != null) {
                    existingSecuritymanagement.setEndtime(securitymanagement.getEndtime());
                }

                return existingSecuritymanagement;
            })
            .map(securitymanagementRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Securitymanagement> findAll() {
        log.debug("Request to get all Securitymanagements");
        return securitymanagementRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Securitymanagement> findOne(String id) {
        log.debug("Request to get Securitymanagement : {}", id);
        return securitymanagementRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Securitymanagement : {}", id);
        securitymanagementRepository.deleteById(id);
    }
}
