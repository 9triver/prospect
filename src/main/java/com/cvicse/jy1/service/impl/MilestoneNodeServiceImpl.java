package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.MilestoneNode;
import com.cvicse.jy1.repository.MilestoneNodeRepository;
import com.cvicse.jy1.service.MilestoneNodeService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.MilestoneNode}.
 */
@Service
@Transactional
public class MilestoneNodeServiceImpl implements MilestoneNodeService {

    private static final Logger log = LoggerFactory.getLogger(MilestoneNodeServiceImpl.class);

    private final MilestoneNodeRepository milestoneNodeRepository;

    public MilestoneNodeServiceImpl(MilestoneNodeRepository milestoneNodeRepository) {
        this.milestoneNodeRepository = milestoneNodeRepository;
    }

    @Override
    public MilestoneNode save(MilestoneNode milestoneNode) {
        log.debug("Request to save MilestoneNode : {}", milestoneNode);
        return milestoneNodeRepository.save(milestoneNode);
    }

    @Override
    public MilestoneNode update(MilestoneNode milestoneNode) {
        log.debug("Request to update MilestoneNode : {}", milestoneNode);
        return milestoneNodeRepository.save(milestoneNode);
    }

    @Override
    public Optional<MilestoneNode> partialUpdate(MilestoneNode milestoneNode) {
        log.debug("Request to partially update MilestoneNode : {}", milestoneNode);

        return milestoneNodeRepository
            .findById(milestoneNode.getId())
            .map(existingMilestoneNode -> {
                if (milestoneNode.getName() != null) {
                    existingMilestoneNode.setName(milestoneNode.getName());
                }
                if (milestoneNode.getPlanpaymenttime() != null) {
                    existingMilestoneNode.setPlanpaymenttime(milestoneNode.getPlanpaymenttime());
                }
                if (milestoneNode.getPlanpaymentamount() != null) {
                    existingMilestoneNode.setPlanpaymentamount(milestoneNode.getPlanpaymentamount());
                }

                return existingMilestoneNode;
            })
            .map(milestoneNodeRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MilestoneNode> findAll() {
        log.debug("Request to get all MilestoneNodes");
        return milestoneNodeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MilestoneNode> findOne(Integer id) {
        log.debug("Request to get MilestoneNode : {}", id);
        return milestoneNodeRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete MilestoneNode : {}", id);
        milestoneNodeRepository.deleteById(id);
    }
}
