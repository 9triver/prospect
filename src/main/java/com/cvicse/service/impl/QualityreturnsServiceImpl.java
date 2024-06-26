package com.cvicse.service.impl;

import com.cvicse.domain.Qualityreturns;
import com.cvicse.repository.QualityreturnsRepository;
import com.cvicse.service.QualityreturnsService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Qualityreturns}.
 */
@Service
@Transactional
public class QualityreturnsServiceImpl implements QualityreturnsService {

    private final Logger log = LoggerFactory.getLogger(QualityreturnsServiceImpl.class);

    private final QualityreturnsRepository qualityreturnsRepository;

    public QualityreturnsServiceImpl(QualityreturnsRepository qualityreturnsRepository) {
        this.qualityreturnsRepository = qualityreturnsRepository;
    }

    @Override
    public Qualityreturns save(Qualityreturns qualityreturns) {
        log.debug("Request to save Qualityreturns : {}", qualityreturns);
        return qualityreturnsRepository.save(qualityreturns);
    }

    @Override
    public Qualityreturns update(Qualityreturns qualityreturns) {
        log.debug("Request to update Qualityreturns : {}", qualityreturns);
        return qualityreturnsRepository.save(qualityreturns);
    }

    @Override
    public Optional<Qualityreturns> partialUpdate(Qualityreturns qualityreturns) {
        log.debug("Request to partially update Qualityreturns : {}", qualityreturns);

        return qualityreturnsRepository
            .findById(qualityreturns.getId())
            .map(existingQualityreturns -> {
                if (qualityreturns.getQualityreturnsname() != null) {
                    existingQualityreturns.setQualityreturnsname(qualityreturns.getQualityreturnsname());
                }
                if (qualityreturns.getStarttime() != null) {
                    existingQualityreturns.setStarttime(qualityreturns.getStarttime());
                }
                if (qualityreturns.getEndtime() != null) {
                    existingQualityreturns.setEndtime(qualityreturns.getEndtime());
                }
                if (qualityreturns.getQualitytype() != null) {
                    existingQualityreturns.setQualitytype(qualityreturns.getQualitytype());
                }
                if (qualityreturns.getReturnstime() != null) {
                    existingQualityreturns.setReturnstime(qualityreturns.getReturnstime());
                }
                if (qualityreturns.getReturnsstatus() != null) {
                    existingQualityreturns.setReturnsstatus(qualityreturns.getReturnsstatus());
                }

                return existingQualityreturns;
            })
            .map(qualityreturnsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Qualityreturns> findAll() {
        log.debug("Request to get all Qualityreturns");
        return qualityreturnsRepository.findAll();
    }

    /**
     *  Get all the qualityreturns where Qualityobjectives is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Qualityreturns> findAllWhereQualityobjectivesIsNull() {
        log.debug("Request to get all qualityreturns where Qualityobjectives is null");
        return StreamSupport.stream(qualityreturnsRepository.findAll().spliterator(), false)
            .filter(qualityreturns -> qualityreturns.getQualityobjectives() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Qualityreturns> findOne(String id) {
        log.debug("Request to get Qualityreturns : {}", id);
        return qualityreturnsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Qualityreturns : {}", id);
        qualityreturnsRepository.deleteById(id);
    }
}
