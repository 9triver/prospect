package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.Deliverables;
import com.cvicse.jy1.repository.DeliverablesRepository;
import com.cvicse.jy1.service.DeliverablesService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.Deliverables}.
 */
@Service
@Transactional
public class DeliverablesServiceImpl implements DeliverablesService {

    private static final Logger log = LoggerFactory.getLogger(DeliverablesServiceImpl.class);

    private final DeliverablesRepository deliverablesRepository;

    public DeliverablesServiceImpl(DeliverablesRepository deliverablesRepository) {
        this.deliverablesRepository = deliverablesRepository;
    }

    @Override
    public Deliverables save(Deliverables deliverables) {
        log.debug("Request to save Deliverables : {}", deliverables);
        return deliverablesRepository.save(deliverables);
    }

    @Override
    public Deliverables update(Deliverables deliverables) {
        log.debug("Request to update Deliverables : {}", deliverables);
        return deliverablesRepository.save(deliverables);
    }

    @Override
    public Optional<Deliverables> partialUpdate(Deliverables deliverables) {
        log.debug("Request to partially update Deliverables : {}", deliverables);

        return deliverablesRepository
            .findById(deliverables.getId())
            .map(existingDeliverables -> {
                if (deliverables.getCode() != null) {
                    existingDeliverables.setCode(deliverables.getCode());
                }
                if (deliverables.getName() != null) {
                    existingDeliverables.setName(deliverables.getName());
                }
                if (deliverables.getParentcode() != null) {
                    existingDeliverables.setParentcode(deliverables.getParentcode());
                }
                if (deliverables.getLevel() != null) {
                    existingDeliverables.setLevel(deliverables.getLevel());
                }
                if (deliverables.getStatus() != null) {
                    existingDeliverables.setStatus(deliverables.getStatus());
                }
                if (deliverables.getDescription() != null) {
                    existingDeliverables.setDescription(deliverables.getDescription());
                }

                return existingDeliverables;
            })
            .map(deliverablesRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Deliverables> findAll() {
        log.debug("Request to get all Deliverables");
        return deliverablesRepository.findAll();
    }

    /**
     *  Get all the deliverables where Projectdeliverables is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Deliverables> findAllWhereProjectdeliverablesIsNull() {
        log.debug("Request to get all deliverables where Projectdeliverables is null");
        return StreamSupport.stream(deliverablesRepository.findAll().spliterator(), false)
            .filter(deliverables -> deliverables.getProjectdeliverables() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Deliverables> findOne(Long id) {
        log.debug("Request to get Deliverables : {}", id);
        return deliverablesRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Deliverables : {}", id);
        deliverablesRepository.deleteById(id);
    }
}
