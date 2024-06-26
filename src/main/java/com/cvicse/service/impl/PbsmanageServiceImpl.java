package com.cvicse.service.impl;

import com.cvicse.domain.Pbsmanage;
import com.cvicse.repository.PbsmanageRepository;
import com.cvicse.service.PbsmanageService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Pbsmanage}.
 */
@Service
@Transactional
public class PbsmanageServiceImpl implements PbsmanageService {

    private final Logger log = LoggerFactory.getLogger(PbsmanageServiceImpl.class);

    private final PbsmanageRepository pbsmanageRepository;

    public PbsmanageServiceImpl(PbsmanageRepository pbsmanageRepository) {
        this.pbsmanageRepository = pbsmanageRepository;
    }

    @Override
    public Pbsmanage save(Pbsmanage pbsmanage) {
        log.debug("Request to save Pbsmanage : {}", pbsmanage);
        return pbsmanageRepository.save(pbsmanage);
    }

    @Override
    public Pbsmanage update(Pbsmanage pbsmanage) {
        log.debug("Request to update Pbsmanage : {}", pbsmanage);
        return pbsmanageRepository.save(pbsmanage);
    }

    @Override
    public Optional<Pbsmanage> partialUpdate(Pbsmanage pbsmanage) {
        log.debug("Request to partially update Pbsmanage : {}", pbsmanage);

        return pbsmanageRepository
            .findById(pbsmanage.getId())
            .map(existingPbsmanage -> {
                if (pbsmanage.getPbsname() != null) {
                    existingPbsmanage.setPbsname(pbsmanage.getPbsname());
                }
                if (pbsmanage.getNumber() != null) {
                    existingPbsmanage.setNumber(pbsmanage.getNumber());
                }
                if (pbsmanage.getType() != null) {
                    existingPbsmanage.setType(pbsmanage.getType());
                }
                if (pbsmanage.getStarttime() != null) {
                    existingPbsmanage.setStarttime(pbsmanage.getStarttime());
                }
                if (pbsmanage.getEndtime() != null) {
                    existingPbsmanage.setEndtime(pbsmanage.getEndtime());
                }
                if (pbsmanage.getAdministratorid() != null) {
                    existingPbsmanage.setAdministratorid(pbsmanage.getAdministratorid());
                }
                if (pbsmanage.getAdministratorname() != null) {
                    existingPbsmanage.setAdministratorname(pbsmanage.getAdministratorname());
                }
                if (pbsmanage.getResponsiblename() != null) {
                    existingPbsmanage.setResponsiblename(pbsmanage.getResponsiblename());
                }
                if (pbsmanage.getDepartment() != null) {
                    existingPbsmanage.setDepartment(pbsmanage.getDepartment());
                }
                if (pbsmanage.getSecretlevel() != null) {
                    existingPbsmanage.setSecretlevel(pbsmanage.getSecretlevel());
                }
                if (pbsmanage.getAuditStatus() != null) {
                    existingPbsmanage.setAuditStatus(pbsmanage.getAuditStatus());
                }
                if (pbsmanage.getAuditUserid() != null) {
                    existingPbsmanage.setAuditUserid(pbsmanage.getAuditUserid());
                }

                return existingPbsmanage;
            })
            .map(pbsmanageRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pbsmanage> findAll() {
        log.debug("Request to get all Pbsmanages");
        return pbsmanageRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pbsmanage> findOne(String id) {
        log.debug("Request to get Pbsmanage : {}", id);
        return pbsmanageRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Pbsmanage : {}", id);
        pbsmanageRepository.deleteById(id);
    }
}
