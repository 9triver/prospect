package com.cvicse.service.impl;

import com.cvicse.domain.Comprehensivecontrol;
import com.cvicse.repository.ComprehensivecontrolRepository;
import com.cvicse.service.ComprehensivecontrolService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Comprehensivecontrol}.
 */
@Service
@Transactional
public class ComprehensivecontrolServiceImpl implements ComprehensivecontrolService {

    private final Logger log = LoggerFactory.getLogger(ComprehensivecontrolServiceImpl.class);

    private final ComprehensivecontrolRepository comprehensivecontrolRepository;

    public ComprehensivecontrolServiceImpl(ComprehensivecontrolRepository comprehensivecontrolRepository) {
        this.comprehensivecontrolRepository = comprehensivecontrolRepository;
    }

    @Override
    public Comprehensivecontrol save(Comprehensivecontrol comprehensivecontrol) {
        log.debug("Request to save Comprehensivecontrol : {}", comprehensivecontrol);
        return comprehensivecontrolRepository.save(comprehensivecontrol);
    }

    @Override
    public Comprehensivecontrol update(Comprehensivecontrol comprehensivecontrol) {
        log.debug("Request to update Comprehensivecontrol : {}", comprehensivecontrol);
        return comprehensivecontrolRepository.save(comprehensivecontrol);
    }

    @Override
    public Optional<Comprehensivecontrol> partialUpdate(Comprehensivecontrol comprehensivecontrol) {
        log.debug("Request to partially update Comprehensivecontrol : {}", comprehensivecontrol);

        return comprehensivecontrolRepository
            .findById(comprehensivecontrol.getId())
            .map(existingComprehensivecontrol -> {
                if (comprehensivecontrol.getDescription() != null) {
                    existingComprehensivecontrol.setDescription(comprehensivecontrol.getDescription());
                }
                if (comprehensivecontrol.getNumber() != null) {
                    existingComprehensivecontrol.setNumber(comprehensivecontrol.getNumber());
                }
                if (comprehensivecontrol.getType() != null) {
                    existingComprehensivecontrol.setType(comprehensivecontrol.getType());
                }
                if (comprehensivecontrol.getStarttime() != null) {
                    existingComprehensivecontrol.setStarttime(comprehensivecontrol.getStarttime());
                }
                if (comprehensivecontrol.getEndtime() != null) {
                    existingComprehensivecontrol.setEndtime(comprehensivecontrol.getEndtime());
                }
                if (comprehensivecontrol.getActualstarttime() != null) {
                    existingComprehensivecontrol.setActualstarttime(comprehensivecontrol.getActualstarttime());
                }
                if (comprehensivecontrol.getActualendtime() != null) {
                    existingComprehensivecontrol.setActualendtime(comprehensivecontrol.getActualendtime());
                }
                if (comprehensivecontrol.getResult() != null) {
                    existingComprehensivecontrol.setResult(comprehensivecontrol.getResult());
                }
                if (comprehensivecontrol.getAcceptancetype() != null) {
                    existingComprehensivecontrol.setAcceptancetype(comprehensivecontrol.getAcceptancetype());
                }
                if (comprehensivecontrol.getStatus() != null) {
                    existingComprehensivecontrol.setStatus(comprehensivecontrol.getStatus());
                }
                if (comprehensivecontrol.getAuditStatus() != null) {
                    existingComprehensivecontrol.setAuditStatus(comprehensivecontrol.getAuditStatus());
                }
                if (comprehensivecontrol.getResponsiblename() != null) {
                    existingComprehensivecontrol.setResponsiblename(comprehensivecontrol.getResponsiblename());
                }

                return existingComprehensivecontrol;
            })
            .map(comprehensivecontrolRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comprehensivecontrol> findAll() {
        log.debug("Request to get all Comprehensivecontrols");
        return comprehensivecontrolRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comprehensivecontrol> findOne(String id) {
        log.debug("Request to get Comprehensivecontrol : {}", id);
        return comprehensivecontrolRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Comprehensivecontrol : {}", id);
        comprehensivecontrolRepository.deleteById(id);
    }
}
