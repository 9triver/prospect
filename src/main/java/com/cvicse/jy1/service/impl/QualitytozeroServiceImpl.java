package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.Qualitytozero;
import com.cvicse.jy1.repository.QualitytozeroRepository;
import com.cvicse.jy1.service.QualitytozeroService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.Qualitytozero}.
 */
@Service
@Transactional
public class QualitytozeroServiceImpl implements QualitytozeroService {

    private static final Logger log = LoggerFactory.getLogger(QualitytozeroServiceImpl.class);

    private final QualitytozeroRepository qualitytozeroRepository;

    public QualitytozeroServiceImpl(QualitytozeroRepository qualitytozeroRepository) {
        this.qualitytozeroRepository = qualitytozeroRepository;
    }

    @Override
    public Qualitytozero save(Qualitytozero qualitytozero) {
        log.debug("Request to save Qualitytozero : {}", qualitytozero);
        return qualitytozeroRepository.save(qualitytozero);
    }

    @Override
    public Qualitytozero update(Qualitytozero qualitytozero) {
        log.debug("Request to update Qualitytozero : {}", qualitytozero);
        return qualitytozeroRepository.save(qualitytozero);
    }

    @Override
    public Optional<Qualitytozero> partialUpdate(Qualitytozero qualitytozero) {
        log.debug("Request to partially update Qualitytozero : {}", qualitytozero);

        return qualitytozeroRepository
            .findById(qualitytozero.getId())
            .map(existingQualitytozero -> {
                if (qualitytozero.getWorkbagid() != null) {
                    existingQualitytozero.setWorkbagid(qualitytozero.getWorkbagid());
                }
                if (qualitytozero.getBelongwbsid() != null) {
                    existingQualitytozero.setBelongwbsid(qualitytozero.getBelongwbsid());
                }
                if (qualitytozero.getOutsourcingcontractid() != null) {
                    existingQualitytozero.setOutsourcingcontractid(qualitytozero.getOutsourcingcontractid());
                }
                if (qualitytozero.getQualityproblemid() != null) {
                    existingQualitytozero.setQualityproblemid(qualitytozero.getQualityproblemid());
                }
                if (qualitytozero.getQualityproblemname() != null) {
                    existingQualitytozero.setQualityproblemname(qualitytozero.getQualityproblemname());
                }
                if (qualitytozero.getProblemhappentime() != null) {
                    existingQualitytozero.setProblemhappentime(qualitytozero.getProblemhappentime());
                }
                if (qualitytozero.getProblemresponsibleperson() != null) {
                    existingQualitytozero.setProblemresponsibleperson(qualitytozero.getProblemresponsibleperson());
                }
                if (qualitytozero.getProblemresponsibleunit() != null) {
                    existingQualitytozero.setProblemresponsibleunit(qualitytozero.getProblemresponsibleunit());
                }
                if (qualitytozero.getProducttype() != null) {
                    existingQualitytozero.setProducttype(qualitytozero.getProducttype());
                }
                if (qualitytozero.getProductname() != null) {
                    existingQualitytozero.setProductname(qualitytozero.getProductname());
                }
                if (qualitytozero.getProblemphenomenon() != null) {
                    existingQualitytozero.setProblemphenomenon(qualitytozero.getProblemphenomenon());
                }
                if (qualitytozero.getProblemtype() != null) {
                    existingQualitytozero.setProblemtype(qualitytozero.getProblemtype());
                }
                if (qualitytozero.getQualitylevel() != null) {
                    existingQualitytozero.setQualitylevel(qualitytozero.getQualitylevel());
                }
                if (qualitytozero.getZerotype() != null) {
                    existingQualitytozero.setZerotype(qualitytozero.getZerotype());
                }
                if (qualitytozero.getProblemreasonanalysis() != null) {
                    existingQualitytozero.setProblemreasonanalysis(qualitytozero.getProblemreasonanalysis());
                }
                if (qualitytozero.getProblemreasoncategory() != null) {
                    existingQualitytozero.setProblemreasoncategory(qualitytozero.getProblemreasoncategory());
                }
                if (qualitytozero.getTakemeasures() != null) {
                    existingQualitytozero.setTakemeasures(qualitytozero.getTakemeasures());
                }
                if (qualitytozero.getOnebyonecategory() != null) {
                    existingQualitytozero.setOnebyonecategory(qualitytozero.getOnebyonecategory());
                }
                if (qualitytozero.getVerificationeffect() != null) {
                    existingQualitytozero.setVerificationeffect(qualitytozero.getVerificationeffect());
                }
                if (qualitytozero.getQualityprojectreport() != null) {
                    existingQualitytozero.setQualityprojectreport(qualitytozero.getQualityprojectreport());
                }
                if (qualitytozero.getQualitytozeroreport() != null) {
                    existingQualitytozero.setQualitytozeroreport(qualitytozero.getQualitytozeroreport());
                }
                if (qualitytozero.getReviewopinion() != null) {
                    existingQualitytozero.setReviewopinion(qualitytozero.getReviewopinion());
                }
                if (qualitytozero.getImplementationverificationtable() != null) {
                    existingQualitytozero.setImplementationverificationtable(qualitytozero.getImplementationverificationtable());
                }
                if (qualitytozero.getAuditStatus() != null) {
                    existingQualitytozero.setAuditStatus(qualitytozero.getAuditStatus());
                }

                return existingQualitytozero;
            })
            .map(qualitytozeroRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Qualitytozero> findAll() {
        log.debug("Request to get all Qualitytozeros");
        return qualitytozeroRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Qualitytozero> findOne(Integer id) {
        log.debug("Request to get Qualitytozero : {}", id);
        return qualitytozeroRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete Qualitytozero : {}", id);
        qualitytozeroRepository.deleteById(id);
    }
}
