package com.cvicse.service.impl;

import com.cvicse.domain.Annualplan;
import com.cvicse.repository.AnnualplanRepository;
import com.cvicse.service.AnnualplanService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Annualplan}.
 */
@Service
@Transactional
public class AnnualplanServiceImpl implements AnnualplanService {

    private final Logger log = LoggerFactory.getLogger(AnnualplanServiceImpl.class);

    private final AnnualplanRepository annualplanRepository;

    public AnnualplanServiceImpl(AnnualplanRepository annualplanRepository) {
        this.annualplanRepository = annualplanRepository;
    }

    @Override
    public Annualplan save(Annualplan annualplan) {
        log.debug("Request to save Annualplan : {}", annualplan);
        return annualplanRepository.save(annualplan);
    }

    @Override
    public Annualplan update(Annualplan annualplan) {
        log.debug("Request to update Annualplan : {}", annualplan);
        return annualplanRepository.save(annualplan);
    }

    @Override
    public Optional<Annualplan> partialUpdate(Annualplan annualplan) {
        log.debug("Request to partially update Annualplan : {}", annualplan);

        return annualplanRepository
            .findById(annualplan.getId())
            .map(existingAnnualplan -> {
                if (annualplan.getAnnualplanname() != null) {
                    existingAnnualplan.setAnnualplanname(annualplan.getAnnualplanname());
                }
                if (annualplan.getYear() != null) {
                    existingAnnualplan.setYear(annualplan.getYear());
                }
                if (annualplan.getSecretlevel() != null) {
                    existingAnnualplan.setSecretlevel(annualplan.getSecretlevel());
                }
                if (annualplan.getCreatorname() != null) {
                    existingAnnualplan.setCreatorname(annualplan.getCreatorname());
                }
                if (annualplan.getStatus() != null) {
                    existingAnnualplan.setStatus(annualplan.getStatus());
                }
                if (annualplan.getAuditStatus() != null) {
                    existingAnnualplan.setAuditStatus(annualplan.getAuditStatus());
                }

                return existingAnnualplan;
            })
            .map(annualplanRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Annualplan> findAll() {
        log.debug("Request to get all Annualplans");
        return annualplanRepository.findAll();
    }

    /**
     *  Get all the annualplans where Cycleplan is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Annualplan> findAllWhereCycleplanIsNull() {
        log.debug("Request to get all annualplans where Cycleplan is null");
        return StreamSupport.stream(annualplanRepository.findAll().spliterator(), false)
            .filter(annualplan -> annualplan.getCycleplan() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Annualplan> findOne(String id) {
        log.debug("Request to get Annualplan : {}", id);
        return annualplanRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Annualplan : {}", id);
        annualplanRepository.deleteById(id);
    }
}
