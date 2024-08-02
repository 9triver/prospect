package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.QualityReturns;
import com.cvicse.jy1.repository.QualityReturnsRepository;
import com.cvicse.jy1.service.QualityReturnsService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.QualityReturns}.
 */
@Service
@Transactional
public class QualityReturnsServiceImpl implements QualityReturnsService {

    private static final Logger log = LoggerFactory.getLogger(QualityReturnsServiceImpl.class);

    private final QualityReturnsRepository qualityReturnsRepository;

    public QualityReturnsServiceImpl(QualityReturnsRepository qualityReturnsRepository) {
        this.qualityReturnsRepository = qualityReturnsRepository;
    }

    @Override
    public QualityReturns save(QualityReturns qualityReturns) {
        log.debug("Request to save QualityReturns : {}", qualityReturns);
        return qualityReturnsRepository.save(qualityReturns);
    }

    @Override
    public QualityReturns update(QualityReturns qualityReturns) {
        log.debug("Request to update QualityReturns : {}", qualityReturns);
        return qualityReturnsRepository.save(qualityReturns);
    }

    @Override
    public Optional<QualityReturns> partialUpdate(QualityReturns qualityReturns) {
        log.debug("Request to partially update QualityReturns : {}", qualityReturns);

        return qualityReturnsRepository
            .findById(qualityReturns.getId())
            .map(existingQualityReturns -> {
                if (qualityReturns.getName() != null) {
                    existingQualityReturns.setName(qualityReturns.getName());
                }
                if (qualityReturns.getObjectives() != null) {
                    existingQualityReturns.setObjectives(qualityReturns.getObjectives());
                }
                if (qualityReturns.getQualitytype() != null) {
                    existingQualityReturns.setQualitytype(qualityReturns.getQualitytype());
                }
                if (qualityReturns.getSecretlevel() != null) {
                    existingQualityReturns.setSecretlevel(qualityReturns.getSecretlevel());
                }
                if (qualityReturns.getTarget() != null) {
                    existingQualityReturns.setTarget(qualityReturns.getTarget());
                }
                if (qualityReturns.getStatisticalmethod() != null) {
                    existingQualityReturns.setStatisticalmethod(qualityReturns.getStatisticalmethod());
                }
                if (qualityReturns.getStatisticalfrequency() != null) {
                    existingQualityReturns.setStatisticalfrequency(qualityReturns.getStatisticalfrequency());
                }
                if (qualityReturns.getIstarget() != null) {
                    existingQualityReturns.setIstarget(qualityReturns.getIstarget());
                }
                if (qualityReturns.getProgress() != null) {
                    existingQualityReturns.setProgress(qualityReturns.getProgress());
                }
                if (qualityReturns.getDescription() != null) {
                    existingQualityReturns.setDescription(qualityReturns.getDescription());
                }
                if (qualityReturns.getProblems() != null) {
                    existingQualityReturns.setProblems(qualityReturns.getProblems());
                }
                if (qualityReturns.getImprovementmeasures() != null) {
                    existingQualityReturns.setImprovementmeasures(qualityReturns.getImprovementmeasures());
                }
                if (qualityReturns.getReturntime() != null) {
                    existingQualityReturns.setReturntime(qualityReturns.getReturntime());
                }
                if (qualityReturns.getCreatetime() != null) {
                    existingQualityReturns.setCreatetime(qualityReturns.getCreatetime());
                }
                if (qualityReturns.getAuditStatus() != null) {
                    existingQualityReturns.setAuditStatus(qualityReturns.getAuditStatus());
                }

                return existingQualityReturns;
            })
            .map(qualityReturnsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QualityReturns> findAll() {
        log.debug("Request to get all QualityReturns");
        return qualityReturnsRepository.findAll();
    }

    public Page<QualityReturns> findAllWithEagerRelationships(Pageable pageable) {
        return qualityReturnsRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QualityReturns> findOne(String id) {
        log.debug("Request to get QualityReturns : {}", id);
        return qualityReturnsRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete QualityReturns : {}", id);
        qualityReturnsRepository.deleteById(id);
    }
}
