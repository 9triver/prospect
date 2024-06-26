package com.cvicse.service.impl;

import com.cvicse.domain.Progressplanreturns;
import com.cvicse.repository.ProgressplanreturnsRepository;
import com.cvicse.service.ProgressplanreturnsService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Progressplanreturns}.
 */
@Service
@Transactional
public class ProgressplanreturnsServiceImpl implements ProgressplanreturnsService {

    private final Logger log = LoggerFactory.getLogger(ProgressplanreturnsServiceImpl.class);

    private final ProgressplanreturnsRepository progressplanreturnsRepository;

    public ProgressplanreturnsServiceImpl(ProgressplanreturnsRepository progressplanreturnsRepository) {
        this.progressplanreturnsRepository = progressplanreturnsRepository;
    }

    @Override
    public Progressplanreturns save(Progressplanreturns progressplanreturns) {
        log.debug("Request to save Progressplanreturns : {}", progressplanreturns);
        return progressplanreturnsRepository.save(progressplanreturns);
    }

    @Override
    public Progressplanreturns update(Progressplanreturns progressplanreturns) {
        log.debug("Request to update Progressplanreturns : {}", progressplanreturns);
        return progressplanreturnsRepository.save(progressplanreturns);
    }

    @Override
    public Optional<Progressplanreturns> partialUpdate(Progressplanreturns progressplanreturns) {
        log.debug("Request to partially update Progressplanreturns : {}", progressplanreturns);

        return progressplanreturnsRepository
            .findById(progressplanreturns.getId())
            .map(existingProgressplanreturns -> {
                if (progressplanreturns.getPlanstarttime() != null) {
                    existingProgressplanreturns.setPlanstarttime(progressplanreturns.getPlanstarttime());
                }
                if (progressplanreturns.getPlanendtime() != null) {
                    existingProgressplanreturns.setPlanendtime(progressplanreturns.getPlanendtime());
                }
                if (progressplanreturns.getReturnstime() != null) {
                    existingProgressplanreturns.setReturnstime(progressplanreturns.getReturnstime());
                }

                return existingProgressplanreturns;
            })
            .map(progressplanreturnsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Progressplanreturns> findAll() {
        log.debug("Request to get all Progressplanreturns");
        return progressplanreturnsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Progressplanreturns> findOne(String id) {
        log.debug("Request to get Progressplanreturns : {}", id);
        return progressplanreturnsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Progressplanreturns : {}", id);
        progressplanreturnsRepository.deleteById(id);
    }
}
