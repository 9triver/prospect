package com.cvicse.service.impl;

import com.cvicse.domain.QualitymanagementWbs;
import com.cvicse.repository.QualitymanagementWbsRepository;
import com.cvicse.service.QualitymanagementWbsService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.QualitymanagementWbs}.
 */
@Service
@Transactional
public class QualitymanagementWbsServiceImpl implements QualitymanagementWbsService {

    private final Logger log = LoggerFactory.getLogger(QualitymanagementWbsServiceImpl.class);

    private final QualitymanagementWbsRepository qualitymanagementWbsRepository;

    public QualitymanagementWbsServiceImpl(QualitymanagementWbsRepository qualitymanagementWbsRepository) {
        this.qualitymanagementWbsRepository = qualitymanagementWbsRepository;
    }

    @Override
    public QualitymanagementWbs save(QualitymanagementWbs qualitymanagementWbs) {
        log.debug("Request to save QualitymanagementWbs : {}", qualitymanagementWbs);
        return qualitymanagementWbsRepository.save(qualitymanagementWbs);
    }

    @Override
    public QualitymanagementWbs update(QualitymanagementWbs qualitymanagementWbs) {
        log.debug("Request to update QualitymanagementWbs : {}", qualitymanagementWbs);
        return qualitymanagementWbsRepository.save(qualitymanagementWbs);
    }

    @Override
    public Optional<QualitymanagementWbs> partialUpdate(QualitymanagementWbs qualitymanagementWbs) {
        log.debug("Request to partially update QualitymanagementWbs : {}", qualitymanagementWbs);

        return qualitymanagementWbsRepository
            .findById(qualitymanagementWbs.getId())
            .map(existingQualitymanagementWbs -> {
                if (qualitymanagementWbs.getWbsspare1() != null) {
                    existingQualitymanagementWbs.setWbsspare1(qualitymanagementWbs.getWbsspare1());
                }
                if (qualitymanagementWbs.getWbsspare2() != null) {
                    existingQualitymanagementWbs.setWbsspare2(qualitymanagementWbs.getWbsspare2());
                }
                if (qualitymanagementWbs.getWbsspare3() != null) {
                    existingQualitymanagementWbs.setWbsspare3(qualitymanagementWbs.getWbsspare3());
                }
                if (qualitymanagementWbs.getWbsspare4() != null) {
                    existingQualitymanagementWbs.setWbsspare4(qualitymanagementWbs.getWbsspare4());
                }
                if (qualitymanagementWbs.getWbsspare5() != null) {
                    existingQualitymanagementWbs.setWbsspare5(qualitymanagementWbs.getWbsspare5());
                }

                return existingQualitymanagementWbs;
            })
            .map(qualitymanagementWbsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QualitymanagementWbs> findAll() {
        log.debug("Request to get all QualitymanagementWbs");
        return qualitymanagementWbsRepository.findAll();
    }

    /**
     *  Get all the qualitymanagementWbs where Qualitymanagement is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<QualitymanagementWbs> findAllWhereQualitymanagementIsNull() {
        log.debug("Request to get all qualitymanagementWbs where Qualitymanagement is null");
        return StreamSupport.stream(qualitymanagementWbsRepository.findAll().spliterator(), false)
            .filter(qualitymanagementWbs -> qualitymanagementWbs.getQualitymanagement() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QualitymanagementWbs> findOne(String id) {
        log.debug("Request to get QualitymanagementWbs : {}", id);
        return qualitymanagementWbsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete QualitymanagementWbs : {}", id);
        qualitymanagementWbsRepository.deleteById(id);
    }
}
