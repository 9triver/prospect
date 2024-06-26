package com.cvicse.service.impl;

import com.cvicse.domain.ProgressmanagementWbs;
import com.cvicse.repository.ProgressmanagementWbsRepository;
import com.cvicse.service.ProgressmanagementWbsService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.ProgressmanagementWbs}.
 */
@Service
@Transactional
public class ProgressmanagementWbsServiceImpl implements ProgressmanagementWbsService {

    private final Logger log = LoggerFactory.getLogger(ProgressmanagementWbsServiceImpl.class);

    private final ProgressmanagementWbsRepository progressmanagementWbsRepository;

    public ProgressmanagementWbsServiceImpl(ProgressmanagementWbsRepository progressmanagementWbsRepository) {
        this.progressmanagementWbsRepository = progressmanagementWbsRepository;
    }

    @Override
    public ProgressmanagementWbs save(ProgressmanagementWbs progressmanagementWbs) {
        log.debug("Request to save ProgressmanagementWbs : {}", progressmanagementWbs);
        return progressmanagementWbsRepository.save(progressmanagementWbs);
    }

    @Override
    public ProgressmanagementWbs update(ProgressmanagementWbs progressmanagementWbs) {
        log.debug("Request to update ProgressmanagementWbs : {}", progressmanagementWbs);
        return progressmanagementWbsRepository.save(progressmanagementWbs);
    }

    @Override
    public Optional<ProgressmanagementWbs> partialUpdate(ProgressmanagementWbs progressmanagementWbs) {
        log.debug("Request to partially update ProgressmanagementWbs : {}", progressmanagementWbs);

        return progressmanagementWbsRepository
            .findById(progressmanagementWbs.getId())
            .map(existingProgressmanagementWbs -> {
                if (progressmanagementWbs.getWbsspare1() != null) {
                    existingProgressmanagementWbs.setWbsspare1(progressmanagementWbs.getWbsspare1());
                }
                if (progressmanagementWbs.getWbsspare2() != null) {
                    existingProgressmanagementWbs.setWbsspare2(progressmanagementWbs.getWbsspare2());
                }
                if (progressmanagementWbs.getWbsspare3() != null) {
                    existingProgressmanagementWbs.setWbsspare3(progressmanagementWbs.getWbsspare3());
                }
                if (progressmanagementWbs.getWbsspare4() != null) {
                    existingProgressmanagementWbs.setWbsspare4(progressmanagementWbs.getWbsspare4());
                }
                if (progressmanagementWbs.getWbsspare5() != null) {
                    existingProgressmanagementWbs.setWbsspare5(progressmanagementWbs.getWbsspare5());
                }

                return existingProgressmanagementWbs;
            })
            .map(progressmanagementWbsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgressmanagementWbs> findAll() {
        log.debug("Request to get all ProgressmanagementWbs");
        return progressmanagementWbsRepository.findAll();
    }

    /**
     *  Get all the progressmanagementWbs where Progressmanagement is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProgressmanagementWbs> findAllWhereProgressmanagementIsNull() {
        log.debug("Request to get all progressmanagementWbs where Progressmanagement is null");
        return StreamSupport.stream(progressmanagementWbsRepository.findAll().spliterator(), false)
            .filter(progressmanagementWbs -> progressmanagementWbs.getProgressmanagement() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProgressmanagementWbs> findOne(String id) {
        log.debug("Request to get ProgressmanagementWbs : {}", id);
        return progressmanagementWbsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ProgressmanagementWbs : {}", id);
        progressmanagementWbsRepository.deleteById(id);
    }
}
