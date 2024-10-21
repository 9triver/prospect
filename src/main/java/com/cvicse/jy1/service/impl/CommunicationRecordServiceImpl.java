package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.CommunicationRecord;
import com.cvicse.jy1.repository.CommunicationRecordRepository;
import com.cvicse.jy1.service.CommunicationRecordService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.CommunicationRecord}.
 */
@Service
@Transactional
public class CommunicationRecordServiceImpl implements CommunicationRecordService {

    private static final Logger log = LoggerFactory.getLogger(CommunicationRecordServiceImpl.class);

    private final CommunicationRecordRepository communicationRecordRepository;

    public CommunicationRecordServiceImpl(CommunicationRecordRepository communicationRecordRepository) {
        this.communicationRecordRepository = communicationRecordRepository;
    }

    @Override
    public CommunicationRecord save(CommunicationRecord communicationRecord) {
        log.debug("Request to save CommunicationRecord : {}", communicationRecord);
        return communicationRecordRepository.save(communicationRecord);
    }

    @Override
    public CommunicationRecord update(CommunicationRecord communicationRecord) {
        log.debug("Request to update CommunicationRecord : {}", communicationRecord);
        return communicationRecordRepository.save(communicationRecord);
    }

    @Override
    public Optional<CommunicationRecord> partialUpdate(CommunicationRecord communicationRecord) {
        log.debug("Request to partially update CommunicationRecord : {}", communicationRecord);

        return communicationRecordRepository
            .findById(communicationRecord.getId())
            .map(existingCommunicationRecord -> {
                if (communicationRecord.getWbsid() != null) {
                    existingCommunicationRecord.setWbsid(communicationRecord.getWbsid());
                }
                if (communicationRecord.getWbsname() != null) {
                    existingCommunicationRecord.setWbsname(communicationRecord.getWbsname());
                }
                if (communicationRecord.getWorkbagid() != null) {
                    existingCommunicationRecord.setWorkbagid(communicationRecord.getWorkbagid());
                }
                if (communicationRecord.getAssociationmeetingname() != null) {
                    existingCommunicationRecord.setAssociationmeetingname(communicationRecord.getAssociationmeetingname());
                }
                if (communicationRecord.getCommunicationtime() != null) {
                    existingCommunicationRecord.setCommunicationtime(communicationRecord.getCommunicationtime());
                }
                if (communicationRecord.getCommunicationlocation() != null) {
                    existingCommunicationRecord.setCommunicationlocation(communicationRecord.getCommunicationlocation());
                }
                if (communicationRecord.getCommunicationcontent() != null) {
                    existingCommunicationRecord.setCommunicationcontent(communicationRecord.getCommunicationcontent());
                }
                if (communicationRecord.getAuditorid() != null) {
                    existingCommunicationRecord.setAuditorid(communicationRecord.getAuditorid());
                }
                if (communicationRecord.getAuditorname() != null) {
                    existingCommunicationRecord.setAuditorname(communicationRecord.getAuditorname());
                }
                if (communicationRecord.getRemarks() != null) {
                    existingCommunicationRecord.setRemarks(communicationRecord.getRemarks());
                }

                return existingCommunicationRecord;
            })
            .map(communicationRecordRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommunicationRecord> findAll() {
        log.debug("Request to get all CommunicationRecords");
        return communicationRecordRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CommunicationRecord> findOne(Integer id) {
        log.debug("Request to get CommunicationRecord : {}", id);
        return communicationRecordRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete CommunicationRecord : {}", id);
        communicationRecordRepository.deleteById(id);
    }
}
