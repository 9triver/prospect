package com.cvicse.service.impl;

import com.cvicse.domain.Qualityobjectives;
import com.cvicse.repository.QualityobjectivesRepository;
import com.cvicse.service.QualityobjectivesService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Qualityobjectives}.
 */
@Service
@Transactional
public class QualityobjectivesServiceImpl implements QualityobjectivesService {

    private final Logger log = LoggerFactory.getLogger(QualityobjectivesServiceImpl.class);

    private final QualityobjectivesRepository qualityobjectivesRepository;

    public QualityobjectivesServiceImpl(QualityobjectivesRepository qualityobjectivesRepository) {
        this.qualityobjectivesRepository = qualityobjectivesRepository;
    }

    @Override
    public Qualityobjectives save(Qualityobjectives qualityobjectives) {
        log.debug("Request to save Qualityobjectives : {}", qualityobjectives);
        return qualityobjectivesRepository.save(qualityobjectives);
    }

    @Override
    public Qualityobjectives update(Qualityobjectives qualityobjectives) {
        log.debug("Request to update Qualityobjectives : {}", qualityobjectives);
        return qualityobjectivesRepository.save(qualityobjectives);
    }

    @Override
    public Optional<Qualityobjectives> partialUpdate(Qualityobjectives qualityobjectives) {
        log.debug("Request to partially update Qualityobjectives : {}", qualityobjectives);

        return qualityobjectivesRepository
            .findById(qualityobjectives.getId())
            .map(existingQualityobjectives -> {
                if (qualityobjectives.getQualityobjectivesname() != null) {
                    existingQualityobjectives.setQualityobjectivesname(qualityobjectives.getQualityobjectivesname());
                }
                if (qualityobjectives.getYear() != null) {
                    existingQualityobjectives.setYear(qualityobjectives.getYear());
                }
                if (qualityobjectives.getCreatetime() != null) {
                    existingQualityobjectives.setCreatetime(qualityobjectives.getCreatetime());
                }
                if (qualityobjectives.getCreatorname() != null) {
                    existingQualityobjectives.setCreatorname(qualityobjectives.getCreatorname());
                }
                if (qualityobjectives.getSecretlevel() != null) {
                    existingQualityobjectives.setSecretlevel(qualityobjectives.getSecretlevel());
                }
                if (qualityobjectives.getAuditStatus() != null) {
                    existingQualityobjectives.setAuditStatus(qualityobjectives.getAuditStatus());
                }

                return existingQualityobjectives;
            })
            .map(qualityobjectivesRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Qualityobjectives> findAll() {
        log.debug("Request to get all Qualityobjectives");
        return qualityobjectivesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Qualityobjectives> findOne(String id) {
        log.debug("Request to get Qualityobjectives : {}", id);
        return qualityobjectivesRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Qualityobjectives : {}", id);
        qualityobjectivesRepository.deleteById(id);
    }
}
