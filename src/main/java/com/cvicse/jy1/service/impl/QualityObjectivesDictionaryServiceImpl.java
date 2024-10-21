package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.QualityObjectivesDictionary;
import com.cvicse.jy1.repository.QualityObjectivesDictionaryRepository;
import com.cvicse.jy1.service.QualityObjectivesDictionaryService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.QualityObjectivesDictionary}.
 */
@Service
@Transactional
public class QualityObjectivesDictionaryServiceImpl implements QualityObjectivesDictionaryService {

    private static final Logger log = LoggerFactory.getLogger(QualityObjectivesDictionaryServiceImpl.class);

    private final QualityObjectivesDictionaryRepository qualityObjectivesDictionaryRepository;

    public QualityObjectivesDictionaryServiceImpl(QualityObjectivesDictionaryRepository qualityObjectivesDictionaryRepository) {
        this.qualityObjectivesDictionaryRepository = qualityObjectivesDictionaryRepository;
    }

    @Override
    public QualityObjectivesDictionary save(QualityObjectivesDictionary qualityObjectivesDictionary) {
        log.debug("Request to save QualityObjectivesDictionary : {}", qualityObjectivesDictionary);
        return qualityObjectivesDictionaryRepository.save(qualityObjectivesDictionary);
    }

    @Override
    public QualityObjectivesDictionary update(QualityObjectivesDictionary qualityObjectivesDictionary) {
        log.debug("Request to update QualityObjectivesDictionary : {}", qualityObjectivesDictionary);
        return qualityObjectivesDictionaryRepository.save(qualityObjectivesDictionary);
    }

    @Override
    public Optional<QualityObjectivesDictionary> partialUpdate(QualityObjectivesDictionary qualityObjectivesDictionary) {
        log.debug("Request to partially update QualityObjectivesDictionary : {}", qualityObjectivesDictionary);

        return qualityObjectivesDictionaryRepository
            .findById(qualityObjectivesDictionary.getId())
            .map(existingQualityObjectivesDictionary -> {
                if (qualityObjectivesDictionary.getObjectiveslevel() != null) {
                    existingQualityObjectivesDictionary.setObjectiveslevel(qualityObjectivesDictionary.getObjectiveslevel());
                }
                if (qualityObjectivesDictionary.getObjectivestype() != null) {
                    existingQualityObjectivesDictionary.setObjectivestype(qualityObjectivesDictionary.getObjectivestype());
                }
                if (qualityObjectivesDictionary.getObjectivesname() != null) {
                    existingQualityObjectivesDictionary.setObjectivesname(qualityObjectivesDictionary.getObjectivesname());
                }
                if (qualityObjectivesDictionary.getObjectivescontent() != null) {
                    existingQualityObjectivesDictionary.setObjectivescontent(qualityObjectivesDictionary.getObjectivescontent());
                }
                if (qualityObjectivesDictionary.getCalculationmethod() != null) {
                    existingQualityObjectivesDictionary.setCalculationmethod(qualityObjectivesDictionary.getCalculationmethod());
                }
                if (qualityObjectivesDictionary.getFrequency() != null) {
                    existingQualityObjectivesDictionary.setFrequency(qualityObjectivesDictionary.getFrequency());
                }
                if (qualityObjectivesDictionary.getEvaluationcriteria() != null) {
                    existingQualityObjectivesDictionary.setEvaluationcriteria(qualityObjectivesDictionary.getEvaluationcriteria());
                }

                return existingQualityObjectivesDictionary;
            })
            .map(qualityObjectivesDictionaryRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QualityObjectivesDictionary> findAll() {
        log.debug("Request to get all QualityObjectivesDictionaries");
        return qualityObjectivesDictionaryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QualityObjectivesDictionary> findOne(Integer id) {
        log.debug("Request to get QualityObjectivesDictionary : {}", id);
        return qualityObjectivesDictionaryRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete QualityObjectivesDictionary : {}", id);
        qualityObjectivesDictionaryRepository.deleteById(id);
    }
}
