package com.cvicse.service.impl;

import com.cvicse.domain.Wbssubmanage;
import com.cvicse.repository.WbssubmanageRepository;
import com.cvicse.service.WbssubmanageService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Wbssubmanage}.
 */
@Service
@Transactional
public class WbssubmanageServiceImpl implements WbssubmanageService {

    private final Logger log = LoggerFactory.getLogger(WbssubmanageServiceImpl.class);

    private final WbssubmanageRepository wbssubmanageRepository;

    public WbssubmanageServiceImpl(WbssubmanageRepository wbssubmanageRepository) {
        this.wbssubmanageRepository = wbssubmanageRepository;
    }

    @Override
    public Wbssubmanage save(Wbssubmanage wbssubmanage) {
        log.debug("Request to save Wbssubmanage : {}", wbssubmanage);
        return wbssubmanageRepository.save(wbssubmanage);
    }

    @Override
    public Wbssubmanage update(Wbssubmanage wbssubmanage) {
        log.debug("Request to update Wbssubmanage : {}", wbssubmanage);
        return wbssubmanageRepository.save(wbssubmanage);
    }

    @Override
    public Optional<Wbssubmanage> partialUpdate(Wbssubmanage wbssubmanage) {
        log.debug("Request to partially update Wbssubmanage : {}", wbssubmanage);

        return wbssubmanageRepository
            .findById(wbssubmanage.getId())
            .map(existingWbssubmanage -> {
                if (wbssubmanage.getPbssubname() != null) {
                    existingWbssubmanage.setPbssubname(wbssubmanage.getPbssubname());
                }
                if (wbssubmanage.getResponsiblename() != null) {
                    existingWbssubmanage.setResponsiblename(wbssubmanage.getResponsiblename());
                }
                if (wbssubmanage.getResponsibledepartment() != null) {
                    existingWbssubmanage.setResponsibledepartment(wbssubmanage.getResponsibledepartment());
                }
                if (wbssubmanage.getRelevantdepartment() != null) {
                    existingWbssubmanage.setRelevantdepartment(wbssubmanage.getRelevantdepartment());
                }
                if (wbssubmanage.getType() != null) {
                    existingWbssubmanage.setType(wbssubmanage.getType());
                }
                if (wbssubmanage.getStarttime() != null) {
                    existingWbssubmanage.setStarttime(wbssubmanage.getStarttime());
                }
                if (wbssubmanage.getEndtime() != null) {
                    existingWbssubmanage.setEndtime(wbssubmanage.getEndtime());
                }
                if (wbssubmanage.getSecretlevel() != null) {
                    existingWbssubmanage.setSecretlevel(wbssubmanage.getSecretlevel());
                }
                if (wbssubmanage.getAuditStatus() != null) {
                    existingWbssubmanage.setAuditStatus(wbssubmanage.getAuditStatus());
                }

                return existingWbssubmanage;
            })
            .map(wbssubmanageRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Wbssubmanage> findAll() {
        log.debug("Request to get all Wbssubmanages");
        return wbssubmanageRepository.findAll();
    }

    /**
     *  Get all the wbssubmanages where Wbsmanage is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Wbssubmanage> findAllWhereWbsmanageIsNull() {
        log.debug("Request to get all wbssubmanages where Wbsmanage is null");
        return StreamSupport.stream(wbssubmanageRepository.findAll().spliterator(), false)
            .filter(wbssubmanage -> wbssubmanage.getWbsmanage() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Wbssubmanage> findOne(String id) {
        log.debug("Request to get Wbssubmanage : {}", id);
        return wbssubmanageRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Wbssubmanage : {}", id);
        wbssubmanageRepository.deleteById(id);
    }
}
