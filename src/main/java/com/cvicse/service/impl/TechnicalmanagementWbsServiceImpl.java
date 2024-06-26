package com.cvicse.service.impl;

import com.cvicse.domain.TechnicalmanagementWbs;
import com.cvicse.repository.TechnicalmanagementWbsRepository;
import com.cvicse.service.TechnicalmanagementWbsService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.TechnicalmanagementWbs}.
 */
@Service
@Transactional
public class TechnicalmanagementWbsServiceImpl implements TechnicalmanagementWbsService {

    private final Logger log = LoggerFactory.getLogger(TechnicalmanagementWbsServiceImpl.class);

    private final TechnicalmanagementWbsRepository technicalmanagementWbsRepository;

    public TechnicalmanagementWbsServiceImpl(TechnicalmanagementWbsRepository technicalmanagementWbsRepository) {
        this.technicalmanagementWbsRepository = technicalmanagementWbsRepository;
    }

    @Override
    public TechnicalmanagementWbs save(TechnicalmanagementWbs technicalmanagementWbs) {
        log.debug("Request to save TechnicalmanagementWbs : {}", technicalmanagementWbs);
        return technicalmanagementWbsRepository.save(technicalmanagementWbs);
    }

    @Override
    public TechnicalmanagementWbs update(TechnicalmanagementWbs technicalmanagementWbs) {
        log.debug("Request to update TechnicalmanagementWbs : {}", technicalmanagementWbs);
        return technicalmanagementWbsRepository.save(technicalmanagementWbs);
    }

    @Override
    public Optional<TechnicalmanagementWbs> partialUpdate(TechnicalmanagementWbs technicalmanagementWbs) {
        log.debug("Request to partially update TechnicalmanagementWbs : {}", technicalmanagementWbs);

        return technicalmanagementWbsRepository
            .findById(technicalmanagementWbs.getId())
            .map(existingTechnicalmanagementWbs -> {
                if (technicalmanagementWbs.getWbsspare1() != null) {
                    existingTechnicalmanagementWbs.setWbsspare1(technicalmanagementWbs.getWbsspare1());
                }
                if (technicalmanagementWbs.getWbsspare2() != null) {
                    existingTechnicalmanagementWbs.setWbsspare2(technicalmanagementWbs.getWbsspare2());
                }
                if (technicalmanagementWbs.getWbsspare3() != null) {
                    existingTechnicalmanagementWbs.setWbsspare3(technicalmanagementWbs.getWbsspare3());
                }
                if (technicalmanagementWbs.getWbsspare4() != null) {
                    existingTechnicalmanagementWbs.setWbsspare4(technicalmanagementWbs.getWbsspare4());
                }
                if (technicalmanagementWbs.getWbsspare5() != null) {
                    existingTechnicalmanagementWbs.setWbsspare5(technicalmanagementWbs.getWbsspare5());
                }

                return existingTechnicalmanagementWbs;
            })
            .map(technicalmanagementWbsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TechnicalmanagementWbs> findAll() {
        log.debug("Request to get all TechnicalmanagementWbs");
        return technicalmanagementWbsRepository.findAll();
    }

    /**
     *  Get all the technicalmanagementWbs where Technicalmanagement is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TechnicalmanagementWbs> findAllWhereTechnicalmanagementIsNull() {
        log.debug("Request to get all technicalmanagementWbs where Technicalmanagement is null");
        return StreamSupport.stream(technicalmanagementWbsRepository.findAll().spliterator(), false)
            .filter(technicalmanagementWbs -> technicalmanagementWbs.getTechnicalmanagement() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TechnicalmanagementWbs> findOne(String id) {
        log.debug("Request to get TechnicalmanagementWbs : {}", id);
        return technicalmanagementWbsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete TechnicalmanagementWbs : {}", id);
        technicalmanagementWbsRepository.deleteById(id);
    }
}
