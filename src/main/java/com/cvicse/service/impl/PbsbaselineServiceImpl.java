package com.cvicse.service.impl;

import com.cvicse.domain.Pbsbaseline;
import com.cvicse.repository.PbsbaselineRepository;
import com.cvicse.service.PbsbaselineService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Pbsbaseline}.
 */
@Service
@Transactional
public class PbsbaselineServiceImpl implements PbsbaselineService {

    private final Logger log = LoggerFactory.getLogger(PbsbaselineServiceImpl.class);

    private final PbsbaselineRepository pbsbaselineRepository;

    public PbsbaselineServiceImpl(PbsbaselineRepository pbsbaselineRepository) {
        this.pbsbaselineRepository = pbsbaselineRepository;
    }

    @Override
    public Pbsbaseline save(Pbsbaseline pbsbaseline) {
        log.debug("Request to save Pbsbaseline : {}", pbsbaseline);
        return pbsbaselineRepository.save(pbsbaseline);
    }

    @Override
    public Pbsbaseline update(Pbsbaseline pbsbaseline) {
        log.debug("Request to update Pbsbaseline : {}", pbsbaseline);
        return pbsbaselineRepository.save(pbsbaseline);
    }

    @Override
    public Optional<Pbsbaseline> partialUpdate(Pbsbaseline pbsbaseline) {
        log.debug("Request to partially update Pbsbaseline : {}", pbsbaseline);

        return pbsbaselineRepository
            .findById(pbsbaseline.getId())
            .map(existingPbsbaseline -> {
                if (pbsbaseline.getSecretlevel() != null) {
                    existingPbsbaseline.setSecretlevel(pbsbaseline.getSecretlevel());
                }
                if (pbsbaseline.getRequestdeportment() != null) {
                    existingPbsbaseline.setRequestdeportment(pbsbaseline.getRequestdeportment());
                }
                if (pbsbaseline.getChargetype() != null) {
                    existingPbsbaseline.setChargetype(pbsbaseline.getChargetype());
                }
                if (pbsbaseline.getChargecontent() != null) {
                    existingPbsbaseline.setChargecontent(pbsbaseline.getChargecontent());
                }
                if (pbsbaseline.getStatus() != null) {
                    existingPbsbaseline.setStatus(pbsbaseline.getStatus());
                }
                if (pbsbaseline.getVersion() != null) {
                    existingPbsbaseline.setVersion(pbsbaseline.getVersion());
                }
                if (pbsbaseline.getRemark() != null) {
                    existingPbsbaseline.setRemark(pbsbaseline.getRemark());
                }

                return existingPbsbaseline;
            })
            .map(pbsbaselineRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pbsbaseline> findAll() {
        log.debug("Request to get all Pbsbaselines");
        return pbsbaselineRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pbsbaseline> findOne(String id) {
        log.debug("Request to get Pbsbaseline : {}", id);
        return pbsbaselineRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Pbsbaseline : {}", id);
        pbsbaselineRepository.deleteById(id);
    }
}
