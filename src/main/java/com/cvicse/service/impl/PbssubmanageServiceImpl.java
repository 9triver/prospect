package com.cvicse.service.impl;

import com.cvicse.domain.Pbssubmanage;
import com.cvicse.repository.PbssubmanageRepository;
import com.cvicse.service.PbssubmanageService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Pbssubmanage}.
 */
@Service
@Transactional
public class PbssubmanageServiceImpl implements PbssubmanageService {

    private final Logger log = LoggerFactory.getLogger(PbssubmanageServiceImpl.class);

    private final PbssubmanageRepository pbssubmanageRepository;

    public PbssubmanageServiceImpl(PbssubmanageRepository pbssubmanageRepository) {
        this.pbssubmanageRepository = pbssubmanageRepository;
    }

    @Override
    public Pbssubmanage save(Pbssubmanage pbssubmanage) {
        log.debug("Request to save Pbssubmanage : {}", pbssubmanage);
        return pbssubmanageRepository.save(pbssubmanage);
    }

    @Override
    public Pbssubmanage update(Pbssubmanage pbssubmanage) {
        log.debug("Request to update Pbssubmanage : {}", pbssubmanage);
        return pbssubmanageRepository.save(pbssubmanage);
    }

    @Override
    public Optional<Pbssubmanage> partialUpdate(Pbssubmanage pbssubmanage) {
        log.debug("Request to partially update Pbssubmanage : {}", pbssubmanage);

        return pbssubmanageRepository
            .findById(pbssubmanage.getId())
            .map(existingPbssubmanage -> {
                if (pbssubmanage.getPbssubname() != null) {
                    existingPbssubmanage.setPbssubname(pbssubmanage.getPbssubname());
                }
                if (pbssubmanage.getResponsiblename() != null) {
                    existingPbssubmanage.setResponsiblename(pbssubmanage.getResponsiblename());
                }
                if (pbssubmanage.getResponsibledepartment() != null) {
                    existingPbssubmanage.setResponsibledepartment(pbssubmanage.getResponsibledepartment());
                }
                if (pbssubmanage.getRelevantdepartment() != null) {
                    existingPbssubmanage.setRelevantdepartment(pbssubmanage.getRelevantdepartment());
                }
                if (pbssubmanage.getType() != null) {
                    existingPbssubmanage.setType(pbssubmanage.getType());
                }
                if (pbssubmanage.getStarttime() != null) {
                    existingPbssubmanage.setStarttime(pbssubmanage.getStarttime());
                }
                if (pbssubmanage.getEndtime() != null) {
                    existingPbssubmanage.setEndtime(pbssubmanage.getEndtime());
                }
                if (pbssubmanage.getSecretlevel() != null) {
                    existingPbssubmanage.setSecretlevel(pbssubmanage.getSecretlevel());
                }
                if (pbssubmanage.getAuditStatus() != null) {
                    existingPbssubmanage.setAuditStatus(pbssubmanage.getAuditStatus());
                }

                return existingPbssubmanage;
            })
            .map(pbssubmanageRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pbssubmanage> findAll() {
        log.debug("Request to get all Pbssubmanages");
        return pbssubmanageRepository.findAll();
    }

    /**
     *  Get all the pbssubmanages where Pbsmanage is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Pbssubmanage> findAllWherePbsmanageIsNull() {
        log.debug("Request to get all pbssubmanages where Pbsmanage is null");
        return StreamSupport.stream(pbssubmanageRepository.findAll().spliterator(), false)
            .filter(pbssubmanage -> pbssubmanage.getPbsmanage() == null)
            .toList();
    }

    /**
     *  Get all the pbssubmanages where Wbsmanage is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Pbssubmanage> findAllWhereWbsmanageIsNull() {
        log.debug("Request to get all pbssubmanages where Wbsmanage is null");
        return StreamSupport.stream(pbssubmanageRepository.findAll().spliterator(), false)
            .filter(pbssubmanage -> pbssubmanage.getWbsmanage() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pbssubmanage> findOne(String id) {
        log.debug("Request to get Pbssubmanage : {}", id);
        return pbssubmanageRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Pbssubmanage : {}", id);
        pbssubmanageRepository.deleteById(id);
    }
}
