package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.SystemLevel;
import com.cvicse.jy1.repository.SystemLevelRepository;
import com.cvicse.jy1.service.SystemLevelService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.SystemLevel}.
 */
@Service
@Transactional
public class SystemLevelServiceImpl implements SystemLevelService {

    private static final Logger log = LoggerFactory.getLogger(SystemLevelServiceImpl.class);

    private final SystemLevelRepository systemLevelRepository;

    public SystemLevelServiceImpl(SystemLevelRepository systemLevelRepository) {
        this.systemLevelRepository = systemLevelRepository;
    }

    @Override
    public SystemLevel save(SystemLevel systemLevel) {
        log.debug("Request to save SystemLevel : {}", systemLevel);
        return systemLevelRepository.save(systemLevel);
    }

    @Override
    public SystemLevel update(SystemLevel systemLevel) {
        log.debug("Request to update SystemLevel : {}", systemLevel);
        return systemLevelRepository.save(systemLevel);
    }

    @Override
    public Optional<SystemLevel> partialUpdate(SystemLevel systemLevel) {
        log.debug("Request to partially update SystemLevel : {}", systemLevel);

        return systemLevelRepository
            .findById(systemLevel.getId())
            .map(existingSystemLevel -> {
                if (systemLevel.getName() != null) {
                    existingSystemLevel.setName(systemLevel.getName());
                }

                return existingSystemLevel;
            })
            .map(systemLevelRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SystemLevel> findAll() {
        log.debug("Request to get all SystemLevels");
        return systemLevelRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SystemLevel> findOne(Integer id) {
        log.debug("Request to get SystemLevel : {}", id);
        return systemLevelRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete SystemLevel : {}", id);
        systemLevelRepository.deleteById(id);
    }
}
