package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.QualityObjectives;
import com.cvicse.jy1.repository.QualityObjectivesRepository;
import com.cvicse.jy1.service.QualityObjectivesService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.QualityObjectives}.
 */
@Service
@Transactional
public class QualityObjectivesServiceImpl implements QualityObjectivesService {

    private static final Logger log = LoggerFactory.getLogger(QualityObjectivesServiceImpl.class);

    private final QualityObjectivesRepository qualityObjectivesRepository;

    public QualityObjectivesServiceImpl(QualityObjectivesRepository qualityObjectivesRepository) {
        this.qualityObjectivesRepository = qualityObjectivesRepository;
    }

    @Override
    public QualityObjectives save(QualityObjectives qualityObjectives) {
        log.debug("Request to save QualityObjectives : {}", qualityObjectives);
        return qualityObjectivesRepository.save(qualityObjectives);
    }

    @Override
    public QualityObjectives update(QualityObjectives qualityObjectives) {
        log.debug("Request to update QualityObjectives : {}", qualityObjectives);
        return qualityObjectivesRepository.save(qualityObjectives);
    }

    @Override
    public Optional<QualityObjectives> partialUpdate(QualityObjectives qualityObjectives) {
        log.debug("Request to partially update QualityObjectives : {}", qualityObjectives);

        return qualityObjectivesRepository
            .findById(qualityObjectives.getId())
            .map(existingQualityObjectives -> {
                if (qualityObjectives.getName() != null) {
                    existingQualityObjectives.setName(qualityObjectives.getName());
                }
                if (qualityObjectives.getObjectiveslevel() != null) {
                    existingQualityObjectives.setObjectiveslevel(qualityObjectives.getObjectiveslevel());
                }
                if (qualityObjectives.getObjectives() != null) {
                    existingQualityObjectives.setObjectives(qualityObjectives.getObjectives());
                }
                if (qualityObjectives.getObjectivesvalue() != null) {
                    existingQualityObjectives.setObjectivesvalue(qualityObjectives.getObjectivesvalue());
                }
                if (qualityObjectives.getCalculationmethod() != null) {
                    existingQualityObjectives.setCalculationmethod(qualityObjectives.getCalculationmethod());
                }
                if (qualityObjectives.getFrequency() != null) {
                    existingQualityObjectives.setFrequency(qualityObjectives.getFrequency());
                }
                if (qualityObjectives.getTakeaction() != null) {
                    existingQualityObjectives.setTakeaction(qualityObjectives.getTakeaction());
                }
                if (qualityObjectives.getNeedresource() != null) {
                    existingQualityObjectives.setNeedresource(qualityObjectives.getNeedresource());
                }
                if (qualityObjectives.getStatus() != null) {
                    existingQualityObjectives.setStatus(qualityObjectives.getStatus());
                }

                return existingQualityObjectives;
            })
            .map(qualityObjectivesRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QualityObjectives> findAll() {
        log.debug("Request to get all QualityObjectives");
        return qualityObjectivesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QualityObjectives> findOne(Integer id) {
        log.debug("Request to get QualityObjectives : {}", id);
        return qualityObjectivesRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete QualityObjectives : {}", id);
        qualityObjectivesRepository.deleteById(id);
    }
}
