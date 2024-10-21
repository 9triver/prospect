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
                if (qualityReturns.getQualityplanid() != null) {
                    existingQualityReturns.setQualityplanid(qualityReturns.getQualityplanid());
                }
                if (qualityReturns.getQualityobjectivesid() != null) {
                    existingQualityReturns.setQualityobjectivesid(qualityReturns.getQualityobjectivesid());
                }
                if (qualityReturns.getName() != null) {
                    existingQualityReturns.setName(qualityReturns.getName());
                }
                if (qualityReturns.getDepartment() != null) {
                    existingQualityReturns.setDepartment(qualityReturns.getDepartment());
                }
                if (qualityReturns.getResponsibleid() != null) {
                    existingQualityReturns.setResponsibleid(qualityReturns.getResponsibleid());
                }
                if (qualityReturns.getWbsid() != null) {
                    existingQualityReturns.setWbsid(qualityReturns.getWbsid());
                }
                if (qualityReturns.getWorkbagid() != null) {
                    existingQualityReturns.setWorkbagid(qualityReturns.getWorkbagid());
                }
                if (qualityReturns.getObjectiveslevel() != null) {
                    existingQualityReturns.setObjectiveslevel(qualityReturns.getObjectiveslevel());
                }
                if (qualityReturns.getObjectives() != null) {
                    existingQualityReturns.setObjectives(qualityReturns.getObjectives());
                }
                if (qualityReturns.getObjectivesvalue() != null) {
                    existingQualityReturns.setObjectivesvalue(qualityReturns.getObjectivesvalue());
                }
                if (qualityReturns.getCalculationmethod() != null) {
                    existingQualityReturns.setCalculationmethod(qualityReturns.getCalculationmethod());
                }
                if (qualityReturns.getFrequency() != null) {
                    existingQualityReturns.setFrequency(qualityReturns.getFrequency());
                }
                if (qualityReturns.getIsobjectivesvalue() != null) {
                    existingQualityReturns.setIsobjectivesvalue(qualityReturns.getIsobjectivesvalue());
                }
                if (qualityReturns.getPercentage() != null) {
                    existingQualityReturns.setPercentage(qualityReturns.getPercentage());
                }
                if (qualityReturns.getObjectivescompletion() != null) {
                    existingQualityReturns.setObjectivescompletion(qualityReturns.getObjectivescompletion());
                }
                if (qualityReturns.getProblem() != null) {
                    existingQualityReturns.setProblem(qualityReturns.getProblem());
                }
                if (qualityReturns.getTakeaction() != null) {
                    existingQualityReturns.setTakeaction(qualityReturns.getTakeaction());
                }
                if (qualityReturns.getContinuousimprovement() != null) {
                    existingQualityReturns.setContinuousimprovement(qualityReturns.getContinuousimprovement());
                }
                if (qualityReturns.getWorkevidence() != null) {
                    existingQualityReturns.setWorkevidence(qualityReturns.getWorkevidence());
                }
                if (qualityReturns.getReturntime() != null) {
                    existingQualityReturns.setReturntime(qualityReturns.getReturntime());
                }
                if (qualityReturns.getStatus() != null) {
                    existingQualityReturns.setStatus(qualityReturns.getStatus());
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
    public Optional<QualityReturns> findOne(Integer id) {
        log.debug("Request to get QualityReturns : {}", id);
        return qualityReturnsRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete QualityReturns : {}", id);
        qualityReturnsRepository.deleteById(id);
    }
}
