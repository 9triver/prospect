package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.FundsEstimation;
import com.cvicse.jy1.repository.FundsEstimationRepository;
import com.cvicse.jy1.service.FundsEstimationService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.FundsEstimation}.
 */
@Service
@Transactional
public class FundsEstimationServiceImpl implements FundsEstimationService {

    private static final Logger log = LoggerFactory.getLogger(FundsEstimationServiceImpl.class);

    private final FundsEstimationRepository fundsEstimationRepository;

    public FundsEstimationServiceImpl(FundsEstimationRepository fundsEstimationRepository) {
        this.fundsEstimationRepository = fundsEstimationRepository;
    }

    @Override
    public FundsEstimation save(FundsEstimation fundsEstimation) {
        log.debug("Request to save FundsEstimation : {}", fundsEstimation);
        return fundsEstimationRepository.save(fundsEstimation);
    }

    @Override
    public FundsEstimation update(FundsEstimation fundsEstimation) {
        log.debug("Request to update FundsEstimation : {}", fundsEstimation);
        return fundsEstimationRepository.save(fundsEstimation);
    }

    @Override
    public Optional<FundsEstimation> partialUpdate(FundsEstimation fundsEstimation) {
        log.debug("Request to partially update FundsEstimation : {}", fundsEstimation);

        return fundsEstimationRepository
            .findById(fundsEstimation.getId())
            .map(existingFundsEstimation -> {
                if (fundsEstimation.getName() != null) {
                    existingFundsEstimation.setName(fundsEstimation.getName());
                }
                if (fundsEstimation.getSource() != null) {
                    existingFundsEstimation.setSource(fundsEstimation.getSource());
                }
                if (fundsEstimation.getUnit() != null) {
                    existingFundsEstimation.setUnit(fundsEstimation.getUnit());
                }
                if (fundsEstimation.getNumber() != null) {
                    existingFundsEstimation.setNumber(fundsEstimation.getNumber());
                }
                if (fundsEstimation.getUnitprice() != null) {
                    existingFundsEstimation.setUnitprice(fundsEstimation.getUnitprice());
                }
                if (fundsEstimation.getMaterialfee() != null) {
                    existingFundsEstimation.setMaterialfee(fundsEstimation.getMaterialfee());
                }
                if (fundsEstimation.getSpecialfee() != null) {
                    existingFundsEstimation.setSpecialfee(fundsEstimation.getSpecialfee());
                }
                if (fundsEstimation.getOutsourcingfee() != null) {
                    existingFundsEstimation.setOutsourcingfee(fundsEstimation.getOutsourcingfee());
                }
                if (fundsEstimation.getTotalbudgetprice() != null) {
                    existingFundsEstimation.setTotalbudgetprice(fundsEstimation.getTotalbudgetprice());
                }

                return existingFundsEstimation;
            })
            .map(fundsEstimationRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FundsEstimation> findAll() {
        log.debug("Request to get all FundsEstimations");
        return fundsEstimationRepository.findAll();
    }

    public Page<FundsEstimation> findAllWithEagerRelationships(Pageable pageable) {
        return fundsEstimationRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FundsEstimation> findOne(String id) {
        log.debug("Request to get FundsEstimation : {}", id);
        return fundsEstimationRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete FundsEstimation : {}", id);
        fundsEstimationRepository.deleteById(id);
    }
}
