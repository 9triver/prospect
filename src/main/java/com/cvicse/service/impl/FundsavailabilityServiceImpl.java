package com.cvicse.service.impl;

import com.cvicse.domain.Fundsavailability;
import com.cvicse.repository.FundsavailabilityRepository;
import com.cvicse.service.FundsavailabilityService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Fundsavailability}.
 */
@Service
@Transactional
public class FundsavailabilityServiceImpl implements FundsavailabilityService {

    private final Logger log = LoggerFactory.getLogger(FundsavailabilityServiceImpl.class);

    private final FundsavailabilityRepository fundsavailabilityRepository;

    public FundsavailabilityServiceImpl(FundsavailabilityRepository fundsavailabilityRepository) {
        this.fundsavailabilityRepository = fundsavailabilityRepository;
    }

    @Override
    public Fundsavailability save(Fundsavailability fundsavailability) {
        log.debug("Request to save Fundsavailability : {}", fundsavailability);
        return fundsavailabilityRepository.save(fundsavailability);
    }

    @Override
    public Fundsavailability update(Fundsavailability fundsavailability) {
        log.debug("Request to update Fundsavailability : {}", fundsavailability);
        return fundsavailabilityRepository.save(fundsavailability);
    }

    @Override
    public Optional<Fundsavailability> partialUpdate(Fundsavailability fundsavailability) {
        log.debug("Request to partially update Fundsavailability : {}", fundsavailability);

        return fundsavailabilityRepository
            .findById(fundsavailability.getId())
            .map(existingFundsavailability -> {
                if (fundsavailability.getFundsid() != null) {
                    existingFundsavailability.setFundsid(fundsavailability.getFundsid());
                }
                if (fundsavailability.getYear() != null) {
                    existingFundsavailability.setYear(fundsavailability.getYear());
                }
                if (fundsavailability.getBudgit() != null) {
                    existingFundsavailability.setBudgit(fundsavailability.getBudgit());
                }
                if (fundsavailability.getFunding() != null) {
                    existingFundsavailability.setFunding(fundsavailability.getFunding());
                }

                return existingFundsavailability;
            })
            .map(fundsavailabilityRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Fundsavailability> findAll() {
        log.debug("Request to get all Fundsavailabilities");
        return fundsavailabilityRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Fundsavailability> findOne(String id) {
        log.debug("Request to get Fundsavailability : {}", id);
        return fundsavailabilityRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Fundsavailability : {}", id);
        fundsavailabilityRepository.deleteById(id);
    }
}
