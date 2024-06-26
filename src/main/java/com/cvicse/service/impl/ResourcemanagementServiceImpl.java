package com.cvicse.service.impl;

import com.cvicse.domain.Resourcemanagement;
import com.cvicse.repository.ResourcemanagementRepository;
import com.cvicse.service.ResourcemanagementService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Resourcemanagement}.
 */
@Service
@Transactional
public class ResourcemanagementServiceImpl implements ResourcemanagementService {

    private final Logger log = LoggerFactory.getLogger(ResourcemanagementServiceImpl.class);

    private final ResourcemanagementRepository resourcemanagementRepository;

    public ResourcemanagementServiceImpl(ResourcemanagementRepository resourcemanagementRepository) {
        this.resourcemanagementRepository = resourcemanagementRepository;
    }

    @Override
    public Resourcemanagement save(Resourcemanagement resourcemanagement) {
        log.debug("Request to save Resourcemanagement : {}", resourcemanagement);
        return resourcemanagementRepository.save(resourcemanagement);
    }

    @Override
    public Resourcemanagement update(Resourcemanagement resourcemanagement) {
        log.debug("Request to update Resourcemanagement : {}", resourcemanagement);
        return resourcemanagementRepository.save(resourcemanagement);
    }

    @Override
    public Optional<Resourcemanagement> partialUpdate(Resourcemanagement resourcemanagement) {
        log.debug("Request to partially update Resourcemanagement : {}", resourcemanagement);

        return resourcemanagementRepository
            .findById(resourcemanagement.getId())
            .map(existingResourcemanagement -> {
                if (resourcemanagement.getName() != null) {
                    existingResourcemanagement.setName(resourcemanagement.getName());
                }
                if (resourcemanagement.getDescription() != null) {
                    existingResourcemanagement.setDescription(resourcemanagement.getDescription());
                }
                if (resourcemanagement.getStarttime() != null) {
                    existingResourcemanagement.setStarttime(resourcemanagement.getStarttime());
                }
                if (resourcemanagement.getEndtime() != null) {
                    existingResourcemanagement.setEndtime(resourcemanagement.getEndtime());
                }

                return existingResourcemanagement;
            })
            .map(resourcemanagementRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Resourcemanagement> findAll() {
        log.debug("Request to get all Resourcemanagements");
        return resourcemanagementRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Resourcemanagement> findOne(String id) {
        log.debug("Request to get Resourcemanagement : {}", id);
        return resourcemanagementRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Resourcemanagement : {}", id);
        resourcemanagementRepository.deleteById(id);
    }
}
