package com.cvicse.service.impl;

import com.cvicse.domain.ApprovalAgent;
import com.cvicse.repository.ApprovalAgentRepository;
import com.cvicse.service.ApprovalAgentService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.ApprovalAgent}.
 */
@Service
@Transactional
public class ApprovalAgentServiceImpl implements ApprovalAgentService {

    private final Logger log = LoggerFactory.getLogger(ApprovalAgentServiceImpl.class);

    private final ApprovalAgentRepository approvalAgentRepository;

    public ApprovalAgentServiceImpl(ApprovalAgentRepository approvalAgentRepository) {
        this.approvalAgentRepository = approvalAgentRepository;
    }

    @Override
    public ApprovalAgent save(ApprovalAgent approvalAgent) {
        log.debug("Request to save ApprovalAgent : {}", approvalAgent);
        return approvalAgentRepository.save(approvalAgent);
    }

    @Override
    public ApprovalAgent update(ApprovalAgent approvalAgent) {
        log.debug("Request to update ApprovalAgent : {}", approvalAgent);
        return approvalAgentRepository.save(approvalAgent);
    }

    @Override
    public Optional<ApprovalAgent> partialUpdate(ApprovalAgent approvalAgent) {
        log.debug("Request to partially update ApprovalAgent : {}", approvalAgent);

        return approvalAgentRepository
            .findById(approvalAgent.getId())
            .map(existingApprovalAgent -> {
                if (approvalAgent.getAgentid() != null) {
                    existingApprovalAgent.setAgentid(approvalAgent.getAgentid());
                }
                if (approvalAgent.getAgentname() != null) {
                    existingApprovalAgent.setAgentname(approvalAgent.getAgentname());
                }
                if (approvalAgent.getAgentstarttime() != null) {
                    existingApprovalAgent.setAgentstarttime(approvalAgent.getAgentstarttime());
                }
                if (approvalAgent.getAutocanceltime() != null) {
                    existingApprovalAgent.setAutocanceltime(approvalAgent.getAutocanceltime());
                }
                if (approvalAgent.getAgentdepartment() != null) {
                    existingApprovalAgent.setAgentdepartment(approvalAgent.getAgentdepartment());
                }
                if (approvalAgent.getOriginalapprovalname() != null) {
                    existingApprovalAgent.setOriginalapprovalname(approvalAgent.getOriginalapprovalname());
                }
                if (approvalAgent.getOriginaldepartment() != null) {
                    existingApprovalAgent.setOriginaldepartment(approvalAgent.getOriginaldepartment());
                }
                if (approvalAgent.getSecrecylevel() != null) {
                    existingApprovalAgent.setSecrecylevel(approvalAgent.getSecrecylevel());
                }

                return existingApprovalAgent;
            })
            .map(approvalAgentRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApprovalAgent> findAll() {
        log.debug("Request to get all ApprovalAgents");
        return approvalAgentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ApprovalAgent> findOne(String id) {
        log.debug("Request to get ApprovalAgent : {}", id);
        return approvalAgentRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ApprovalAgent : {}", id);
        approvalAgentRepository.deleteById(id);
    }
}
