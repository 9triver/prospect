package com.cvicse.service.impl;

import com.cvicse.domain.ResourcemanagementWbs;
import com.cvicse.repository.ResourcemanagementWbsRepository;
import com.cvicse.service.ResourcemanagementWbsService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.ResourcemanagementWbs}.
 */
@Service
@Transactional
public class ResourcemanagementWbsServiceImpl implements ResourcemanagementWbsService {

    private final Logger log = LoggerFactory.getLogger(ResourcemanagementWbsServiceImpl.class);

    private final ResourcemanagementWbsRepository resourcemanagementWbsRepository;

    public ResourcemanagementWbsServiceImpl(ResourcemanagementWbsRepository resourcemanagementWbsRepository) {
        this.resourcemanagementWbsRepository = resourcemanagementWbsRepository;
    }

    @Override
    public ResourcemanagementWbs save(ResourcemanagementWbs resourcemanagementWbs) {
        log.debug("Request to save ResourcemanagementWbs : {}", resourcemanagementWbs);
        return resourcemanagementWbsRepository.save(resourcemanagementWbs);
    }

    @Override
    public ResourcemanagementWbs update(ResourcemanagementWbs resourcemanagementWbs) {
        log.debug("Request to update ResourcemanagementWbs : {}", resourcemanagementWbs);
        return resourcemanagementWbsRepository.save(resourcemanagementWbs);
    }

    @Override
    public Optional<ResourcemanagementWbs> partialUpdate(ResourcemanagementWbs resourcemanagementWbs) {
        log.debug("Request to partially update ResourcemanagementWbs : {}", resourcemanagementWbs);

        return resourcemanagementWbsRepository
            .findById(resourcemanagementWbs.getId())
            .map(existingResourcemanagementWbs -> {
                if (resourcemanagementWbs.getWbsspare1() != null) {
                    existingResourcemanagementWbs.setWbsspare1(resourcemanagementWbs.getWbsspare1());
                }
                if (resourcemanagementWbs.getWbsspare2() != null) {
                    existingResourcemanagementWbs.setWbsspare2(resourcemanagementWbs.getWbsspare2());
                }
                if (resourcemanagementWbs.getWbsspare3() != null) {
                    existingResourcemanagementWbs.setWbsspare3(resourcemanagementWbs.getWbsspare3());
                }
                if (resourcemanagementWbs.getWbsspare4() != null) {
                    existingResourcemanagementWbs.setWbsspare4(resourcemanagementWbs.getWbsspare4());
                }
                if (resourcemanagementWbs.getWbsspare5() != null) {
                    existingResourcemanagementWbs.setWbsspare5(resourcemanagementWbs.getWbsspare5());
                }

                return existingResourcemanagementWbs;
            })
            .map(resourcemanagementWbsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResourcemanagementWbs> findAll() {
        log.debug("Request to get all ResourcemanagementWbs");
        return resourcemanagementWbsRepository.findAll();
    }

    /**
     *  Get all the resourcemanagementWbs where Resourcemanagement is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ResourcemanagementWbs> findAllWhereResourcemanagementIsNull() {
        log.debug("Request to get all resourcemanagementWbs where Resourcemanagement is null");
        return StreamSupport.stream(resourcemanagementWbsRepository.findAll().spliterator(), false)
            .filter(resourcemanagementWbs -> resourcemanagementWbs.getResourcemanagement() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ResourcemanagementWbs> findOne(String id) {
        log.debug("Request to get ResourcemanagementWbs : {}", id);
        return resourcemanagementWbsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ResourcemanagementWbs : {}", id);
        resourcemanagementWbsRepository.deleteById(id);
    }
}
