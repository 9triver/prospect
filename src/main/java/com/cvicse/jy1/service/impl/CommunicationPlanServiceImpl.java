package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.CommunicationPlan;
import com.cvicse.jy1.repository.CommunicationPlanRepository;
import com.cvicse.jy1.service.CommunicationPlanService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.CommunicationPlan}.
 */
@Service
@Transactional
public class CommunicationPlanServiceImpl implements CommunicationPlanService {

    private static final Logger log = LoggerFactory.getLogger(CommunicationPlanServiceImpl.class);

    private final CommunicationPlanRepository communicationPlanRepository;

    public CommunicationPlanServiceImpl(CommunicationPlanRepository communicationPlanRepository) {
        this.communicationPlanRepository = communicationPlanRepository;
    }

    @Override
    public CommunicationPlan save(CommunicationPlan communicationPlan) {
        log.debug("Request to save CommunicationPlan : {}", communicationPlan);
        return communicationPlanRepository.save(communicationPlan);
    }

    @Override
    public CommunicationPlan update(CommunicationPlan communicationPlan) {
        log.debug("Request to update CommunicationPlan : {}", communicationPlan);
        return communicationPlanRepository.save(communicationPlan);
    }

    @Override
    public Optional<CommunicationPlan> partialUpdate(CommunicationPlan communicationPlan) {
        log.debug("Request to partially update CommunicationPlan : {}", communicationPlan);

        return communicationPlanRepository
            .findById(communicationPlan.getId())
            .map(existingCommunicationPlan -> {
                if (communicationPlan.getWbsid() != null) {
                    existingCommunicationPlan.setWbsid(communicationPlan.getWbsid());
                }
                if (communicationPlan.getCommunicationtopic() != null) {
                    existingCommunicationPlan.setCommunicationtopic(communicationPlan.getCommunicationtopic());
                }
                if (communicationPlan.getCommunicationtime() != null) {
                    existingCommunicationPlan.setCommunicationtime(communicationPlan.getCommunicationtime());
                }
                if (communicationPlan.getWorktarget() != null) {
                    existingCommunicationPlan.setWorktarget(communicationPlan.getWorktarget());
                }
                if (communicationPlan.getWorkcontent() != null) {
                    existingCommunicationPlan.setWorkcontent(communicationPlan.getWorkcontent());
                }
                if (communicationPlan.getRemarks() != null) {
                    existingCommunicationPlan.setRemarks(communicationPlan.getRemarks());
                }
                if (communicationPlan.getAuditStatus() != null) {
                    existingCommunicationPlan.setAuditStatus(communicationPlan.getAuditStatus());
                }

                return existingCommunicationPlan;
            })
            .map(communicationPlanRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommunicationPlan> findAll() {
        log.debug("Request to get all CommunicationPlans");
        return communicationPlanRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CommunicationPlan> findOne(Integer id) {
        log.debug("Request to get CommunicationPlan : {}", id);
        return communicationPlanRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete CommunicationPlan : {}", id);
        communicationPlanRepository.deleteById(id);
    }
}
