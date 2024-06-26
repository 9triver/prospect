package com.cvicse.service.impl;

import com.cvicse.domain.Humanresources;
import com.cvicse.repository.HumanresourcesRepository;
import com.cvicse.service.HumanresourcesService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Humanresources}.
 */
@Service
@Transactional
public class HumanresourcesServiceImpl implements HumanresourcesService {

    private final Logger log = LoggerFactory.getLogger(HumanresourcesServiceImpl.class);

    private final HumanresourcesRepository humanresourcesRepository;

    public HumanresourcesServiceImpl(HumanresourcesRepository humanresourcesRepository) {
        this.humanresourcesRepository = humanresourcesRepository;
    }

    @Override
    public Humanresources save(Humanresources humanresources) {
        log.debug("Request to save Humanresources : {}", humanresources);
        return humanresourcesRepository.save(humanresources);
    }

    @Override
    public Humanresources update(Humanresources humanresources) {
        log.debug("Request to update Humanresources : {}", humanresources);
        return humanresourcesRepository.save(humanresources);
    }

    @Override
    public Optional<Humanresources> partialUpdate(Humanresources humanresources) {
        log.debug("Request to partially update Humanresources : {}", humanresources);

        return humanresourcesRepository
            .findById(humanresources.getId())
            .map(existingHumanresources -> {
                if (humanresources.getName() != null) {
                    existingHumanresources.setName(humanresources.getName());
                }
                if (humanresources.getOutdeportment() != null) {
                    existingHumanresources.setOutdeportment(humanresources.getOutdeportment());
                }
                if (humanresources.getIndeportment() != null) {
                    existingHumanresources.setIndeportment(humanresources.getIndeportment());
                }
                if (humanresources.getAdjusttime() != null) {
                    existingHumanresources.setAdjusttime(humanresources.getAdjusttime());
                }
                if (humanresources.getProjectname() != null) {
                    existingHumanresources.setProjectname(humanresources.getProjectname());
                }
                if (humanresources.getDeportment() != null) {
                    existingHumanresources.setDeportment(humanresources.getDeportment());
                }
                if (humanresources.getProjectleader() != null) {
                    existingHumanresources.setProjectleader(humanresources.getProjectleader());
                }
                if (humanresources.getSecretlevel() != null) {
                    existingHumanresources.setSecretlevel(humanresources.getSecretlevel());
                }
                if (humanresources.getAuditStatus() != null) {
                    existingHumanresources.setAuditStatus(humanresources.getAuditStatus());
                }

                return existingHumanresources;
            })
            .map(humanresourcesRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Humanresources> findAll() {
        log.debug("Request to get all Humanresources");
        return humanresourcesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Humanresources> findOne(String id) {
        log.debug("Request to get Humanresources : {}", id);
        return humanresourcesRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Humanresources : {}", id);
        humanresourcesRepository.deleteById(id);
    }
}
