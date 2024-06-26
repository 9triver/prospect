package com.cvicse.service.impl;

import com.cvicse.domain.Safetycheck;
import com.cvicse.repository.SafetycheckRepository;
import com.cvicse.service.SafetycheckService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Safetycheck}.
 */
@Service
@Transactional
public class SafetycheckServiceImpl implements SafetycheckService {

    private final Logger log = LoggerFactory.getLogger(SafetycheckServiceImpl.class);

    private final SafetycheckRepository safetycheckRepository;

    public SafetycheckServiceImpl(SafetycheckRepository safetycheckRepository) {
        this.safetycheckRepository = safetycheckRepository;
    }

    @Override
    public Safetycheck save(Safetycheck safetycheck) {
        log.debug("Request to save Safetycheck : {}", safetycheck);
        return safetycheckRepository.save(safetycheck);
    }

    @Override
    public Safetycheck update(Safetycheck safetycheck) {
        log.debug("Request to update Safetycheck : {}", safetycheck);
        return safetycheckRepository.save(safetycheck);
    }

    @Override
    public Optional<Safetycheck> partialUpdate(Safetycheck safetycheck) {
        log.debug("Request to partially update Safetycheck : {}", safetycheck);

        return safetycheckRepository
            .findById(safetycheck.getId())
            .map(existingSafetycheck -> {
                if (safetycheck.getSafetycheckname() != null) {
                    existingSafetycheck.setSafetycheckname(safetycheck.getSafetycheckname());
                }
                if (safetycheck.getChecksource() != null) {
                    existingSafetycheck.setChecksource(safetycheck.getChecksource());
                }
                if (safetycheck.getChecktime() != null) {
                    existingSafetycheck.setChecktime(safetycheck.getChecktime());
                }
                if (safetycheck.getEffectivetime() != null) {
                    existingSafetycheck.setEffectivetime(safetycheck.getEffectivetime());
                }
                if (safetycheck.getOperatinglocation() != null) {
                    existingSafetycheck.setOperatinglocation(safetycheck.getOperatinglocation());
                }
                if (safetycheck.getDeprotment() != null) {
                    existingSafetycheck.setDeprotment(safetycheck.getDeprotment());
                }
                if (safetycheck.getPhonenumber() != null) {
                    existingSafetycheck.setPhonenumber(safetycheck.getPhonenumber());
                }
                if (safetycheck.getRisklevel() != null) {
                    existingSafetycheck.setRisklevel(safetycheck.getRisklevel());
                }
                if (safetycheck.getAuditStatus() != null) {
                    existingSafetycheck.setAuditStatus(safetycheck.getAuditStatus());
                }

                return existingSafetycheck;
            })
            .map(safetycheckRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Safetycheck> findAll() {
        log.debug("Request to get all Safetychecks");
        return safetycheckRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Safetycheck> findOne(String id) {
        log.debug("Request to get Safetycheck : {}", id);
        return safetycheckRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Safetycheck : {}", id);
        safetycheckRepository.deleteById(id);
    }
}
