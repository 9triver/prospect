package com.cvicse.service.impl;

import com.cvicse.domain.Progressbaseline;
import com.cvicse.repository.ProgressbaselineRepository;
import com.cvicse.service.ProgressbaselineService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Progressbaseline}.
 */
@Service
@Transactional
public class ProgressbaselineServiceImpl implements ProgressbaselineService {

    private final Logger log = LoggerFactory.getLogger(ProgressbaselineServiceImpl.class);

    private final ProgressbaselineRepository progressbaselineRepository;

    public ProgressbaselineServiceImpl(ProgressbaselineRepository progressbaselineRepository) {
        this.progressbaselineRepository = progressbaselineRepository;
    }

    @Override
    public Progressbaseline save(Progressbaseline progressbaseline) {
        log.debug("Request to save Progressbaseline : {}", progressbaseline);
        return progressbaselineRepository.save(progressbaseline);
    }

    @Override
    public Progressbaseline update(Progressbaseline progressbaseline) {
        log.debug("Request to update Progressbaseline : {}", progressbaseline);
        return progressbaselineRepository.save(progressbaseline);
    }

    @Override
    public Optional<Progressbaseline> partialUpdate(Progressbaseline progressbaseline) {
        log.debug("Request to partially update Progressbaseline : {}", progressbaseline);

        return progressbaselineRepository
            .findById(progressbaseline.getId())
            .map(existingProgressbaseline -> {
                if (progressbaseline.getSecretlevel() != null) {
                    existingProgressbaseline.setSecretlevel(progressbaseline.getSecretlevel());
                }
                if (progressbaseline.getRequestdeportment() != null) {
                    existingProgressbaseline.setRequestdeportment(progressbaseline.getRequestdeportment());
                }
                if (progressbaseline.getChargetype() != null) {
                    existingProgressbaseline.setChargetype(progressbaseline.getChargetype());
                }
                if (progressbaseline.getChargecontent() != null) {
                    existingProgressbaseline.setChargecontent(progressbaseline.getChargecontent());
                }
                if (progressbaseline.getStatus() != null) {
                    existingProgressbaseline.setStatus(progressbaseline.getStatus());
                }
                if (progressbaseline.getVersion() != null) {
                    existingProgressbaseline.setVersion(progressbaseline.getVersion());
                }
                if (progressbaseline.getRemark() != null) {
                    existingProgressbaseline.setRemark(progressbaseline.getRemark());
                }

                return existingProgressbaseline;
            })
            .map(progressbaselineRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Progressbaseline> findAll() {
        log.debug("Request to get all Progressbaselines");
        return progressbaselineRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Progressbaseline> findOne(String id) {
        log.debug("Request to get Progressbaseline : {}", id);
        return progressbaselineRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Progressbaseline : {}", id);
        progressbaselineRepository.deleteById(id);
    }
}
