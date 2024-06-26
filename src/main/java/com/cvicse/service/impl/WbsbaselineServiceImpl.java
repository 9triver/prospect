package com.cvicse.service.impl;

import com.cvicse.domain.Wbsbaseline;
import com.cvicse.repository.WbsbaselineRepository;
import com.cvicse.service.WbsbaselineService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Wbsbaseline}.
 */
@Service
@Transactional
public class WbsbaselineServiceImpl implements WbsbaselineService {

    private final Logger log = LoggerFactory.getLogger(WbsbaselineServiceImpl.class);

    private final WbsbaselineRepository wbsbaselineRepository;

    public WbsbaselineServiceImpl(WbsbaselineRepository wbsbaselineRepository) {
        this.wbsbaselineRepository = wbsbaselineRepository;
    }

    @Override
    public Wbsbaseline save(Wbsbaseline wbsbaseline) {
        log.debug("Request to save Wbsbaseline : {}", wbsbaseline);
        return wbsbaselineRepository.save(wbsbaseline);
    }

    @Override
    public Wbsbaseline update(Wbsbaseline wbsbaseline) {
        log.debug("Request to update Wbsbaseline : {}", wbsbaseline);
        return wbsbaselineRepository.save(wbsbaseline);
    }

    @Override
    public Optional<Wbsbaseline> partialUpdate(Wbsbaseline wbsbaseline) {
        log.debug("Request to partially update Wbsbaseline : {}", wbsbaseline);

        return wbsbaselineRepository
            .findById(wbsbaseline.getId())
            .map(existingWbsbaseline -> {
                if (wbsbaseline.getSecretlevel() != null) {
                    existingWbsbaseline.setSecretlevel(wbsbaseline.getSecretlevel());
                }
                if (wbsbaseline.getRequestdeportment() != null) {
                    existingWbsbaseline.setRequestdeportment(wbsbaseline.getRequestdeportment());
                }
                if (wbsbaseline.getChargetype() != null) {
                    existingWbsbaseline.setChargetype(wbsbaseline.getChargetype());
                }
                if (wbsbaseline.getChargecontent() != null) {
                    existingWbsbaseline.setChargecontent(wbsbaseline.getChargecontent());
                }
                if (wbsbaseline.getStatus() != null) {
                    existingWbsbaseline.setStatus(wbsbaseline.getStatus());
                }
                if (wbsbaseline.getVersion() != null) {
                    existingWbsbaseline.setVersion(wbsbaseline.getVersion());
                }
                if (wbsbaseline.getRemark() != null) {
                    existingWbsbaseline.setRemark(wbsbaseline.getRemark());
                }

                return existingWbsbaseline;
            })
            .map(wbsbaselineRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Wbsbaseline> findAll() {
        log.debug("Request to get all Wbsbaselines");
        return wbsbaselineRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Wbsbaseline> findOne(String id) {
        log.debug("Request to get Wbsbaseline : {}", id);
        return wbsbaselineRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Wbsbaseline : {}", id);
        wbsbaselineRepository.deleteById(id);
    }
}
