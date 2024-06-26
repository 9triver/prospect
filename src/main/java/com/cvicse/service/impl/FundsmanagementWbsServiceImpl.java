package com.cvicse.service.impl;

import com.cvicse.domain.FundsmanagementWbs;
import com.cvicse.repository.FundsmanagementWbsRepository;
import com.cvicse.service.FundsmanagementWbsService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.FundsmanagementWbs}.
 */
@Service
@Transactional
public class FundsmanagementWbsServiceImpl implements FundsmanagementWbsService {

    private final Logger log = LoggerFactory.getLogger(FundsmanagementWbsServiceImpl.class);

    private final FundsmanagementWbsRepository fundsmanagementWbsRepository;

    public FundsmanagementWbsServiceImpl(FundsmanagementWbsRepository fundsmanagementWbsRepository) {
        this.fundsmanagementWbsRepository = fundsmanagementWbsRepository;
    }

    @Override
    public FundsmanagementWbs save(FundsmanagementWbs fundsmanagementWbs) {
        log.debug("Request to save FundsmanagementWbs : {}", fundsmanagementWbs);
        return fundsmanagementWbsRepository.save(fundsmanagementWbs);
    }

    @Override
    public FundsmanagementWbs update(FundsmanagementWbs fundsmanagementWbs) {
        log.debug("Request to update FundsmanagementWbs : {}", fundsmanagementWbs);
        return fundsmanagementWbsRepository.save(fundsmanagementWbs);
    }

    @Override
    public Optional<FundsmanagementWbs> partialUpdate(FundsmanagementWbs fundsmanagementWbs) {
        log.debug("Request to partially update FundsmanagementWbs : {}", fundsmanagementWbs);

        return fundsmanagementWbsRepository
            .findById(fundsmanagementWbs.getId())
            .map(existingFundsmanagementWbs -> {
                if (fundsmanagementWbs.getWbsspare1() != null) {
                    existingFundsmanagementWbs.setWbsspare1(fundsmanagementWbs.getWbsspare1());
                }
                if (fundsmanagementWbs.getWbsspare2() != null) {
                    existingFundsmanagementWbs.setWbsspare2(fundsmanagementWbs.getWbsspare2());
                }
                if (fundsmanagementWbs.getWbsspare3() != null) {
                    existingFundsmanagementWbs.setWbsspare3(fundsmanagementWbs.getWbsspare3());
                }
                if (fundsmanagementWbs.getWbsspare4() != null) {
                    existingFundsmanagementWbs.setWbsspare4(fundsmanagementWbs.getWbsspare4());
                }
                if (fundsmanagementWbs.getWbsspare5() != null) {
                    existingFundsmanagementWbs.setWbsspare5(fundsmanagementWbs.getWbsspare5());
                }

                return existingFundsmanagementWbs;
            })
            .map(fundsmanagementWbsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FundsmanagementWbs> findAll() {
        log.debug("Request to get all FundsmanagementWbs");
        return fundsmanagementWbsRepository.findAll();
    }

    /**
     *  Get all the fundsmanagementWbs where Fundsmanagement is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FundsmanagementWbs> findAllWhereFundsmanagementIsNull() {
        log.debug("Request to get all fundsmanagementWbs where Fundsmanagement is null");
        return StreamSupport.stream(fundsmanagementWbsRepository.findAll().spliterator(), false)
            .filter(fundsmanagementWbs -> fundsmanagementWbs.getFundsmanagement() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FundsmanagementWbs> findOne(String id) {
        log.debug("Request to get FundsmanagementWbs : {}", id);
        return fundsmanagementWbsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete FundsmanagementWbs : {}", id);
        fundsmanagementWbsRepository.deleteById(id);
    }
}
