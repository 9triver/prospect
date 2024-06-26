package com.cvicse.service.impl;

import com.cvicse.domain.Outsourcingmanagement;
import com.cvicse.repository.OutsourcingmanagementRepository;
import com.cvicse.service.OutsourcingmanagementService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Outsourcingmanagement}.
 */
@Service
@Transactional
public class OutsourcingmanagementServiceImpl implements OutsourcingmanagementService {

    private final Logger log = LoggerFactory.getLogger(OutsourcingmanagementServiceImpl.class);

    private final OutsourcingmanagementRepository outsourcingmanagementRepository;

    public OutsourcingmanagementServiceImpl(OutsourcingmanagementRepository outsourcingmanagementRepository) {
        this.outsourcingmanagementRepository = outsourcingmanagementRepository;
    }

    @Override
    public Outsourcingmanagement save(Outsourcingmanagement outsourcingmanagement) {
        log.debug("Request to save Outsourcingmanagement : {}", outsourcingmanagement);
        return outsourcingmanagementRepository.save(outsourcingmanagement);
    }

    @Override
    public Outsourcingmanagement update(Outsourcingmanagement outsourcingmanagement) {
        log.debug("Request to update Outsourcingmanagement : {}", outsourcingmanagement);
        return outsourcingmanagementRepository.save(outsourcingmanagement);
    }

    @Override
    public Optional<Outsourcingmanagement> partialUpdate(Outsourcingmanagement outsourcingmanagement) {
        log.debug("Request to partially update Outsourcingmanagement : {}", outsourcingmanagement);

        return outsourcingmanagementRepository
            .findById(outsourcingmanagement.getId())
            .map(existingOutsourcingmanagement -> {
                if (outsourcingmanagement.getName() != null) {
                    existingOutsourcingmanagement.setName(outsourcingmanagement.getName());
                }
                if (outsourcingmanagement.getDescription() != null) {
                    existingOutsourcingmanagement.setDescription(outsourcingmanagement.getDescription());
                }
                if (outsourcingmanagement.getStarttime() != null) {
                    existingOutsourcingmanagement.setStarttime(outsourcingmanagement.getStarttime());
                }
                if (outsourcingmanagement.getEndtime() != null) {
                    existingOutsourcingmanagement.setEndtime(outsourcingmanagement.getEndtime());
                }

                return existingOutsourcingmanagement;
            })
            .map(outsourcingmanagementRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Outsourcingmanagement> findAll() {
        log.debug("Request to get all Outsourcingmanagements");
        return outsourcingmanagementRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Outsourcingmanagement> findOne(String id) {
        log.debug("Request to get Outsourcingmanagement : {}", id);
        return outsourcingmanagementRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Outsourcingmanagement : {}", id);
        outsourcingmanagementRepository.deleteById(id);
    }
}
