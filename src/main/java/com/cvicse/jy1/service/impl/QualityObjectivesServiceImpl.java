package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.QualityObjectives;
import com.cvicse.jy1.repository.QualityObjectivesRepository;
import com.cvicse.jy1.service.QualityObjectivesService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                if (qualityObjectives.getObjectives() != null) {
                    existingQualityObjectives.setObjectives(qualityObjectives.getObjectives());
                }
                if (qualityObjectives.getQualitytype() != null) {
                    existingQualityObjectives.setQualitytype(qualityObjectives.getQualitytype());
                }
                if (qualityObjectives.getSecretlevel() != null) {
                    existingQualityObjectives.setSecretlevel(qualityObjectives.getSecretlevel());
                }
                if (qualityObjectives.getTarget() != null) {
                    existingQualityObjectives.setTarget(qualityObjectives.getTarget());
                }
                if (qualityObjectives.getStatisticalmethod() != null) {
                    existingQualityObjectives.setStatisticalmethod(qualityObjectives.getStatisticalmethod());
                }
                if (qualityObjectives.getStatisticalfrequency() != null) {
                    existingQualityObjectives.setStatisticalfrequency(qualityObjectives.getStatisticalfrequency());
                }
                if (qualityObjectives.getIstarget() != null) {
                    existingQualityObjectives.setIstarget(qualityObjectives.getIstarget());
                }
                if (qualityObjectives.getProgress() != null) {
                    existingQualityObjectives.setProgress(qualityObjectives.getProgress());
                }
                if (qualityObjectives.getDescription() != null) {
                    existingQualityObjectives.setDescription(qualityObjectives.getDescription());
                }
                if (qualityObjectives.getProblems() != null) {
                    existingQualityObjectives.setProblems(qualityObjectives.getProblems());
                }
                if (qualityObjectives.getImprovementmeasures() != null) {
                    existingQualityObjectives.setImprovementmeasures(qualityObjectives.getImprovementmeasures());
                }
                if (qualityObjectives.getReturntime() != null) {
                    existingQualityObjectives.setReturntime(qualityObjectives.getReturntime());
                }
                if (qualityObjectives.getCreatetime() != null) {
                    existingQualityObjectives.setCreatetime(qualityObjectives.getCreatetime());
                }
                if (qualityObjectives.getAuditStatus() != null) {
                    existingQualityObjectives.setAuditStatus(qualityObjectives.getAuditStatus());
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

    public Page<QualityObjectives> findAllWithEagerRelationships(Pageable pageable) {
        return qualityObjectivesRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QualityObjectives> findOne(String id) {
        log.debug("Request to get QualityObjectives : {}", id);
        return qualityObjectivesRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete QualityObjectives : {}", id);
        qualityObjectivesRepository.deleteById(id);
    }
}
