package com.cvicse.service.impl;

import com.cvicse.domain.Fundsmanagement;
import com.cvicse.repository.FundsmanagementRepository;
import com.cvicse.service.FundsmanagementService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Fundsmanagement}.
 */
@Service
@Transactional
public class FundsmanagementServiceImpl implements FundsmanagementService {

    private final Logger log = LoggerFactory.getLogger(FundsmanagementServiceImpl.class);

    private final FundsmanagementRepository fundsmanagementRepository;

    public FundsmanagementServiceImpl(FundsmanagementRepository fundsmanagementRepository) {
        this.fundsmanagementRepository = fundsmanagementRepository;
    }

    @Override
    public Fundsmanagement save(Fundsmanagement fundsmanagement) {
        log.debug("Request to save Fundsmanagement : {}", fundsmanagement);
        return fundsmanagementRepository.save(fundsmanagement);
    }

    @Override
    public Fundsmanagement update(Fundsmanagement fundsmanagement) {
        log.debug("Request to update Fundsmanagement : {}", fundsmanagement);
        return fundsmanagementRepository.save(fundsmanagement);
    }

    @Override
    public Optional<Fundsmanagement> partialUpdate(Fundsmanagement fundsmanagement) {
        log.debug("Request to partially update Fundsmanagement : {}", fundsmanagement);

        return fundsmanagementRepository
            .findById(fundsmanagement.getId())
            .map(existingFundsmanagement -> {
                if (fundsmanagement.getName() != null) {
                    existingFundsmanagement.setName(fundsmanagement.getName());
                }
                if (fundsmanagement.getDescription() != null) {
                    existingFundsmanagement.setDescription(fundsmanagement.getDescription());
                }
                if (fundsmanagement.getStarttime() != null) {
                    existingFundsmanagement.setStarttime(fundsmanagement.getStarttime());
                }
                if (fundsmanagement.getEndtime() != null) {
                    existingFundsmanagement.setEndtime(fundsmanagement.getEndtime());
                }

                return existingFundsmanagement;
            })
            .map(fundsmanagementRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Fundsmanagement> findAll() {
        log.debug("Request to get all Fundsmanagements");
        return fundsmanagementRepository.findAll();
    }

    /**
     *  Get all the fundsmanagements where Comprehensivecontrol is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Fundsmanagement> findAllWhereComprehensivecontrolIsNull() {
        log.debug("Request to get all fundsmanagements where Comprehensivecontrol is null");
        return StreamSupport.stream(fundsmanagementRepository.findAll().spliterator(), false)
            .filter(fundsmanagement -> fundsmanagement.getComprehensivecontrol() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Fundsmanagement> findOne(String id) {
        log.debug("Request to get Fundsmanagement : {}", id);
        return fundsmanagementRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Fundsmanagement : {}", id);
        fundsmanagementRepository.deleteById(id);
    }
}
