package com.cvicse.service.impl;

import com.cvicse.domain.Progressmanagement;
import com.cvicse.repository.ProgressmanagementRepository;
import com.cvicse.service.ProgressmanagementService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Progressmanagement}.
 */
@Service
@Transactional
public class ProgressmanagementServiceImpl implements ProgressmanagementService {

    private final Logger log = LoggerFactory.getLogger(ProgressmanagementServiceImpl.class);

    private final ProgressmanagementRepository progressmanagementRepository;

    public ProgressmanagementServiceImpl(ProgressmanagementRepository progressmanagementRepository) {
        this.progressmanagementRepository = progressmanagementRepository;
    }

    @Override
    public Progressmanagement save(Progressmanagement progressmanagement) {
        log.debug("Request to save Progressmanagement : {}", progressmanagement);
        return progressmanagementRepository.save(progressmanagement);
    }

    @Override
    public Progressmanagement update(Progressmanagement progressmanagement) {
        log.debug("Request to update Progressmanagement : {}", progressmanagement);
        return progressmanagementRepository.save(progressmanagement);
    }

    @Override
    public Optional<Progressmanagement> partialUpdate(Progressmanagement progressmanagement) {
        log.debug("Request to partially update Progressmanagement : {}", progressmanagement);

        return progressmanagementRepository
            .findById(progressmanagement.getId())
            .map(existingProgressmanagement -> {
                if (progressmanagement.getName() != null) {
                    existingProgressmanagement.setName(progressmanagement.getName());
                }
                if (progressmanagement.getDescription() != null) {
                    existingProgressmanagement.setDescription(progressmanagement.getDescription());
                }
                if (progressmanagement.getStarttime() != null) {
                    existingProgressmanagement.setStarttime(progressmanagement.getStarttime());
                }
                if (progressmanagement.getEndtime() != null) {
                    existingProgressmanagement.setEndtime(progressmanagement.getEndtime());
                }

                return existingProgressmanagement;
            })
            .map(progressmanagementRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Progressmanagement> findAll() {
        log.debug("Request to get all Progressmanagements");
        return progressmanagementRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Progressmanagement> findOne(String id) {
        log.debug("Request to get Progressmanagement : {}", id);
        return progressmanagementRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Progressmanagement : {}", id);
        progressmanagementRepository.deleteById(id);
    }
}
