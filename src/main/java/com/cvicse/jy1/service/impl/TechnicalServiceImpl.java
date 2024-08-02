package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.Technical;
import com.cvicse.jy1.repository.TechnicalRepository;
import com.cvicse.jy1.service.TechnicalService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.Technical}.
 */
@Service
@Transactional
public class TechnicalServiceImpl implements TechnicalService {

    private static final Logger log = LoggerFactory.getLogger(TechnicalServiceImpl.class);

    private final TechnicalRepository technicalRepository;

    public TechnicalServiceImpl(TechnicalRepository technicalRepository) {
        this.technicalRepository = technicalRepository;
    }

    @Override
    public Technical save(Technical technical) {
        log.debug("Request to save Technical : {}", technical);
        return technicalRepository.save(technical);
    }

    @Override
    public Technical update(Technical technical) {
        log.debug("Request to update Technical : {}", technical);
        return technicalRepository.save(technical);
    }

    @Override
    public Optional<Technical> partialUpdate(Technical technical) {
        log.debug("Request to partially update Technical : {}", technical);

        return technicalRepository
            .findById(technical.getId())
            .map(existingTechnical -> {
                if (technical.getName() != null) {
                    existingTechnical.setName(technical.getName());
                }
                if (technical.getDescription() != null) {
                    existingTechnical.setDescription(technical.getDescription());
                }
                if (technical.getStarttime() != null) {
                    existingTechnical.setStarttime(technical.getStarttime());
                }
                if (technical.getEndtime() != null) {
                    existingTechnical.setEndtime(technical.getEndtime());
                }

                return existingTechnical;
            })
            .map(technicalRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Technical> findAll() {
        log.debug("Request to get all Technicals");
        return technicalRepository.findAll();
    }

    public Page<Technical> findAllWithEagerRelationships(Pageable pageable) {
        return technicalRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Technical> findOne(String id) {
        log.debug("Request to get Technical : {}", id);
        return technicalRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Technical : {}", id);
        technicalRepository.deleteById(id);
    }
}
