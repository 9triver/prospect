package com.cvicse.service.impl;

import com.cvicse.domain.Cycleplan;
import com.cvicse.repository.CycleplanRepository;
import com.cvicse.service.CycleplanService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Cycleplan}.
 */
@Service
@Transactional
public class CycleplanServiceImpl implements CycleplanService {

    private final Logger log = LoggerFactory.getLogger(CycleplanServiceImpl.class);

    private final CycleplanRepository cycleplanRepository;

    public CycleplanServiceImpl(CycleplanRepository cycleplanRepository) {
        this.cycleplanRepository = cycleplanRepository;
    }

    @Override
    public Cycleplan save(Cycleplan cycleplan) {
        log.debug("Request to save Cycleplan : {}", cycleplan);
        return cycleplanRepository.save(cycleplan);
    }

    @Override
    public Cycleplan update(Cycleplan cycleplan) {
        log.debug("Request to update Cycleplan : {}", cycleplan);
        return cycleplanRepository.save(cycleplan);
    }

    @Override
    public Optional<Cycleplan> partialUpdate(Cycleplan cycleplan) {
        log.debug("Request to partially update Cycleplan : {}", cycleplan);

        return cycleplanRepository
            .findById(cycleplan.getId())
            .map(existingCycleplan -> {
                if (cycleplan.getCycleplanname() != null) {
                    existingCycleplan.setCycleplanname(cycleplan.getCycleplanname());
                }
                if (cycleplan.getSecretlevel() != null) {
                    existingCycleplan.setSecretlevel(cycleplan.getSecretlevel());
                }
                if (cycleplan.getStarttime() != null) {
                    existingCycleplan.setStarttime(cycleplan.getStarttime());
                }
                if (cycleplan.getEndtime() != null) {
                    existingCycleplan.setEndtime(cycleplan.getEndtime());
                }
                if (cycleplan.getActualstarttime() != null) {
                    existingCycleplan.setActualstarttime(cycleplan.getActualstarttime());
                }
                if (cycleplan.getActualendtime() != null) {
                    existingCycleplan.setActualendtime(cycleplan.getActualendtime());
                }
                if (cycleplan.getResponsiblename() != null) {
                    existingCycleplan.setResponsiblename(cycleplan.getResponsiblename());
                }
                if (cycleplan.getStatus() != null) {
                    existingCycleplan.setStatus(cycleplan.getStatus());
                }
                if (cycleplan.getAuditStatus() != null) {
                    existingCycleplan.setAuditStatus(cycleplan.getAuditStatus());
                }

                return existingCycleplan;
            })
            .map(cycleplanRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cycleplan> findAll() {
        log.debug("Request to get all Cycleplans");
        return cycleplanRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cycleplan> findOne(String id) {
        log.debug("Request to get Cycleplan : {}", id);
        return cycleplanRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Cycleplan : {}", id);
        cycleplanRepository.deleteById(id);
    }
}
