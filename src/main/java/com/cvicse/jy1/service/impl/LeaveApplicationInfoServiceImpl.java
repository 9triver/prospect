package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.LeaveApplicationInfo;
import com.cvicse.jy1.repository.LeaveApplicationInfoRepository;
import com.cvicse.jy1.service.LeaveApplicationInfoService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.LeaveApplicationInfo}.
 */
@Service
@Transactional
public class LeaveApplicationInfoServiceImpl implements LeaveApplicationInfoService {

    private static final Logger log = LoggerFactory.getLogger(LeaveApplicationInfoServiceImpl.class);

    private final LeaveApplicationInfoRepository leaveApplicationInfoRepository;

    public LeaveApplicationInfoServiceImpl(LeaveApplicationInfoRepository leaveApplicationInfoRepository) {
        this.leaveApplicationInfoRepository = leaveApplicationInfoRepository;
    }

    @Override
    public LeaveApplicationInfo save(LeaveApplicationInfo leaveApplicationInfo) {
        log.debug("Request to save LeaveApplicationInfo : {}", leaveApplicationInfo);
        return leaveApplicationInfoRepository.save(leaveApplicationInfo);
    }

    @Override
    public LeaveApplicationInfo update(LeaveApplicationInfo leaveApplicationInfo) {
        log.debug("Request to update LeaveApplicationInfo : {}", leaveApplicationInfo);
        return leaveApplicationInfoRepository.save(leaveApplicationInfo);
    }

    @Override
    public Optional<LeaveApplicationInfo> partialUpdate(LeaveApplicationInfo leaveApplicationInfo) {
        log.debug("Request to partially update LeaveApplicationInfo : {}", leaveApplicationInfo);

        return leaveApplicationInfoRepository
            .findById(leaveApplicationInfo.getId())
            .map(existingLeaveApplicationInfo -> {
                if (leaveApplicationInfo.getStartDate() != null) {
                    existingLeaveApplicationInfo.setStartDate(leaveApplicationInfo.getStartDate());
                }
                if (leaveApplicationInfo.getEndDate() != null) {
                    existingLeaveApplicationInfo.setEndDate(leaveApplicationInfo.getEndDate());
                }
                if (leaveApplicationInfo.getLeaveType() != null) {
                    existingLeaveApplicationInfo.setLeaveType(leaveApplicationInfo.getLeaveType());
                }
                if (leaveApplicationInfo.getReason() != null) {
                    existingLeaveApplicationInfo.setReason(leaveApplicationInfo.getReason());
                }
                if (leaveApplicationInfo.getStatus() != null) {
                    existingLeaveApplicationInfo.setStatus(leaveApplicationInfo.getStatus());
                }

                return existingLeaveApplicationInfo;
            })
            .map(leaveApplicationInfoRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LeaveApplicationInfo> findAll() {
        log.debug("Request to get all LeaveApplicationInfos");
        return leaveApplicationInfoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LeaveApplicationInfo> findOne(Integer id) {
        log.debug("Request to get LeaveApplicationInfo : {}", id);
        return leaveApplicationInfoRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete LeaveApplicationInfo : {}", id);
        leaveApplicationInfoRepository.deleteById(id);
    }
}
