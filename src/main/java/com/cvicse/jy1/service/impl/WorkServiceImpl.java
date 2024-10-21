package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.Work;
import com.cvicse.jy1.repository.WorkRepository;
import com.cvicse.jy1.service.WorkService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.Work}.
 */
@Service
@Transactional
public class WorkServiceImpl implements WorkService {

    private static final Logger log = LoggerFactory.getLogger(WorkServiceImpl.class);

    private final WorkRepository workRepository;

    public WorkServiceImpl(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    @Override
    public Work save(Work work) {
        log.debug("Request to save Work : {}", work);
        return workRepository.save(work);
    }

    @Override
    public Work update(Work work) {
        log.debug("Request to update Work : {}", work);
        return workRepository.save(work);
    }

    @Override
    public Optional<Work> partialUpdate(Work work) {
        log.debug("Request to partially update Work : {}", work);

        return workRepository
            .findById(work.getId())
            .map(existingWork -> {
                if (work.getName() != null) {
                    existingWork.setName(work.getName());
                }
                if (work.getSecretlevel() != null) {
                    existingWork.setSecretlevel(work.getSecretlevel());
                }
                if (work.getDescription() != null) {
                    existingWork.setDescription(work.getDescription());
                }
                if (work.getWorkbagid() != null) {
                    existingWork.setWorkbagid(work.getWorkbagid());
                }
                if (work.getAuditStatus() != null) {
                    existingWork.setAuditStatus(work.getAuditStatus());
                }

                return existingWork;
            })
            .map(workRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Work> findAll() {
        log.debug("Request to get all Works");
        return workRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Work> findOne(String id) {
        log.debug("Request to get Work : {}", id);
        return workRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Work : {}", id);
        workRepository.deleteById(id);
    }
}
